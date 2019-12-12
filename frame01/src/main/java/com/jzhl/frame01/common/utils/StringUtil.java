package com.jzhl.frame01.common.utils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类型转换【二进制 10进制 16进制 Unicode 字符串 异或等等】
 * @author xiaobin
 */
public class StringUtil {

    /**
     * 字符串转换unicode
     */
    public static String stringToUnicode(String string) {
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            // 取出每一个字符
            char c = string.charAt(i);
            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }
        return unicode.toString();
    }


    /**
     * unicode 转字符串
     */
    public static String unicodeToString(String unicode) {
        StringBuffer string = new StringBuffer();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);
            // 追加成string
            string.append((char) data);
        }
        return string.toString();
    }

    /**
     * 字符串转换成为16进制(无需Unicode编码)
     * @param str
     * @return
     */
    public static String strToHexStr(String str) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
            // sb.append(' ');
        }
        return sb.toString().trim();
    }


    /**
     * 字符串转16进制（包含汉字）【GBK】
     * @param s
     * @return
     */
    public static String chineseToHexStr(String s)
    {
        String ss = s;
        byte[] bt = new byte[0];

        try {
            bt = ss.getBytes("GBK");
        }catch (Exception e){
            e.printStackTrace();
        }
        String s1 = "";
        for (int i = 0; i < bt.length; i++)
        {
            String tempStr = Integer.toHexString(bt[i]);
            if(tempStr.length() > 2){
                tempStr = tempStr.substring(tempStr.length() - 2);
            }
            s1 = s1 + tempStr + "";
        }
        return s1.toUpperCase();
    }


    /**
     * 16进制直接转换成为字符串(无需Unicode解码)
     * @param hexStr
     * @return
     */
    public static String hexStrToStr(String hexStr) {
        String str = "0123456789ABCDEF";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }

    /**
     * @description 将16进制转换为二进制
     * @param hexStr
     * @return
     */
    public static byte[] hexStrToByte(String hexStr) {
        if (hexStr.length() < 1){
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * 将二进制转换成16进制
     * @param bytes
     * @return
     */
    public static String byteToHexStr(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }


    /**
     * 对一个字符串进行异或处理
     * @param para  需要处理的字符串
     * @return  异或结果
     */
    public static String  xorCode(String para) throws Exception{
        int length = para.length()/2;
        String[] dateArr = new String[length];
        try {
            for (int i=0;i<length;i++){
                if(i==0){
                    dateArr[0] = para.substring(0, 2);
                }
                if(i==1){
                    dateArr[1] = para.substring(2, 4);
                }
                dateArr[i] = para.substring(i*2, i*2 + 2);
            }
        } catch (Exception e) {
            throw e;
        }

        String code = "";
        for (int i = 0; i < dateArr.length-1; i++) {
            if(i == 0){
                code = xor(dateArr[i], dateArr[i+1]);
            }else{
                code = xor(code, dateArr[i+1]);
            }
        }
        return code;
    }

    private static String xor(String strHex_X,String strHex_Y){
        //将x、y转成二进制形式
        String anotherBinary=Integer.toBinaryString(Integer.valueOf(strHex_X,16));
        String thisBinary=Integer.toBinaryString(Integer.valueOf(strHex_Y,16));
        String result = "";
        //判断是否为8位二进制，否则左补零
        if(anotherBinary.length() != 8){
            for (int i = anotherBinary.length(); i <8; i++) {
                anotherBinary = "0" + anotherBinary;
            }
        }
        if(thisBinary.length() != 8){
            for (int i = thisBinary.length(); i <8; i++) {
                thisBinary = "0" + thisBinary;
            }
        }
        //异或运算
        for(int i=0;i<anotherBinary.length();i++){
            //如果相同位置数相同，则补0，否则补1
            if(thisBinary.charAt(i)==anotherBinary.charAt(i)){
                result+="0";
            }else{
                result+="1";
            }
        }
        return Integer.toHexString(Integer.parseInt(result, 2));
    }


    /**
     * 对象转字节数组【二进制】
     * @param obj
     * @return
     */
    public static byte[] objectToByte (Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray ();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }

    /**
     * 将byte字节数组转化为Object对象
     * @return
     */
    public static Object toObject(byte[] bytes){
        Object object = null;
        try {
            // 创建ByteArrayInputStream对象
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            // 创建ObjectInputStream对象
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            // 从objectInputStream流中读取一个对象
            object = objectInputStream.readObject();
            // 关闭输入流
            byteArrayInputStream.close();
            // 关闭输入流
            objectInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }


    /**
     * 将10进制转成BCD码
     * @param s
     * @return
     */
    public static byte[] strToBCDBytes(String s)
    {
        if(s.length () % 2 != 0) {
            s = "0" + s;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream ();
        char[] cs = s.toCharArray ();
        for (int i = 0;i < cs.length;i += 2) {
            int high = cs[i] - 48;
            int low = cs[i + 1] - 48;
            baos.write (high << 4 | low);
        }
        return baos.toByteArray ();
    }

    /**
     * 将BCD码转成10进制
     * @param b
     * @return
     */
    public static String bcdToString(byte[] b){
        StringBuffer sb = new StringBuffer ();
        for (int i = 0;i < b.length;i++ ) {
            int h = ((b[i] & 0xff) >> 4) + 48;
            sb.append ((char) h);
            int l = (b[i] & 0x0f) + 48;
            sb.append ((char) l);
        }
        return sb.toString () ;
    }

    /**
     * TCP 公用回写数据到客户端的方法
     * @param  //需要回写的字符串
     * @param channel
     * @param mark 用于打印/log的输出
     * <br>//channel.writeAndFlush(msg);//不行
     * <br>//channel.writeAndFlush(receiveStr.getBytes());//不行
     * <br>在netty里，进出的都是ByteBuf，楼主应确定服务端是否有对应的编码器，将字符串转化为ByteBuf
     *
     *     https://blog.csdn.net/yqwang75457/article/details/73913572
     */
    public static void writeToClient(String receiveStr, ChannelHandlerContext channel, String mark) throws Exception {
        try {
            ByteBuf bufff = Unpooled.buffer();//netty需要用ByteBuf传输
            bufff.writeBytes(hexStrToByte(receiveStr));//对接需要16进制
            channel.writeAndFlush(bufff);
            /*channel.writeAndFlush(bufff).addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    StringBuilder sb = new StringBuilder("");
                    if(mark != null){
                        sb.append("【").append(mark).append("】");
                    }
                    if (future.isSuccess()) {
                        *//*System.out.println(sb.toString()+"回写成功"+receiveStr);
                        log.info(sb.toString()+"回写成功"+receiveStr);*//*
                    } else {
                        *//*System.out.println(sb.toString()+"回写失败"+receiveStr);
                        log.error(sb.toString()+"回写失败"+receiveStr);*//*
                    }
                }
            });*/
        } catch (Exception e) {
            throw e;
            /*System.out.println("调用通用writeToClient()异常"+e.getMessage());
            log.error("调用通用writeToClient()异常：",e);*/
        }
    }

    /**
     * 10进制转16进制 高低位转换 高前 低后   并且转2字节
     * @param var0
     * @return
     */
    public static String lowHigh(int var0) {
        int var1 = 1;
        int var2 = var0 >> 8;
        int var3 = var0 & 255;
        String var4 = Integer.toHexString(var2);
        String var5 = Integer.toHexString(var3);
        if(var4.length() > 2) {
            do {
                if(var1 > 1) {
                    var2 >>= 8;
                }
                var4 = Integer.toHexString(var2 >> 8);
                var5 = var5 + Integer.toHexString(var2 & 255);
                ++var1;
            } while(var4.length() > 2);
        }
        if(var4.length() < 2) {
            var4 = "0" + var4;
        }
        if(var5.length() < 2) {
            var5 = "0" + var5;
        }
        return var5 + var4;
    }


    /**
     * 16进制转10进制
     * @param hex
     * @return
     */
    public static int hexStrToInt(String hex)
    {
        int decimalValue=0;
        for(int i=0;i<hex.length();i++)
        {
            char hexChar=hex.charAt(i);
            decimalValue=decimalValue*16+hexCharToDecimal(hexChar);
        }
        return decimalValue;
    }


    /**
     * 字节转10进制
     * @param hexChar
     * @return
     */
    public static int hexCharToDecimal(char hexChar)
    {
        if(hexChar>='A'&&hexChar<='F'){
            return 10+hexChar-'A';
        } else{
            //切记不能写成int类型的0，因为字符'0'转换为int时值为48
            return hexChar-'0';
        }
    }

    /**
     * 这次算法用了StringBuffer效率更好
     *  10 进制转 16 进制
     */
    public static String intToHex(int n) {
        StringBuilder sb = new StringBuilder(8);
        String a;
        char []b = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        while(n != 0){
            sb = sb.append(b[n%16]);
            n = n/16;
        }
        a = sb.reverse().toString();
        return a;
    }

    /**
     * 对字符串进行补0操作【字符串长度不够，补0】
     * @param str
     * @param strLength
     * @return
     */
    public static String addZeroForNum(String str, int strLength, String type) {
        int strLen = str.length();
        StringBuffer sb = null;
        while (strLen < strLength) {
            sb = new StringBuffer();
            if("left".equals(type)){
                // 左补0
                sb.append("0").append(str);
            }else if("right".equals(type)){
                //右补0
                sb.append(str).append("0");
            }else{
                return str;
            }
            str = sb.toString();
            strLen = str.length();
        }
        return str;
    }

    /**
     * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米
     * @param lng1
     * @param lat1
     * @param lng2
     * @param lat2
     * @return  double
     */
    private static final double EARTH_RADIUS = 6378137;
    private static double rad(double d)
    {
        return d * Math.PI / 180.0;
    }
    public static double GetDistance(double lng1, double lat1, double lng2, double lat2)
    {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
                Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    /**
     * 生成指定数量的随机数
     * @param len 生成的总长度
     * @param haveLetter 是否包含字母 【yes 数字 + 字母  false 全数字】
     * @return 字符串
     */
    public static String getRandomNum(int len, Boolean haveLetter) {
        int i; // 生成的随机数
        // 生成的密码的长度
        int count = 0;

		char[] str1 = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
				'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
				'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

        char[] str2 = { '1', '2', '3', '4', '5', '6', '7', '8', '9' };

        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while (count < len) {
            // 生成随机数，取绝对值，防止生成负数，
            // 生成的数最大为36-1
            if(haveLetter){
                i = Math.abs(r.nextInt(36));
                if (i >= 0 && i < str1.length) {
                    pwd.append(str1[i]);
                    count++;
                }
            }else{
                i = Math.abs(r.nextInt(9));
                if (i >= 0 && i < str2.length) {
                    pwd.append(str2[i]);
                    count++;
                }
            }
        }

        return pwd.toString();
    }

    /**
     * 通配符匹配  匹配请求路径是否匹配
     * 实例："/user/get/id=1"    通配   "/user/get/**"
     * @param
     */
    public static Boolean pathMatcher(String path, String...strings){
        for (String s : strings){
            PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:" + s);
            Boolean m = matcher.matches(Paths.get(path));
            if(m){
                return true;
            }
        }
        return false;
    }

    /**
     * 利用java原生的类实现SHA256加密
     * @param str 加密后的报文
     * "appid=1&iccid=898606182222832823&timestamp=1499675521446dla20op8ui0eujflajsdf"
     * @return
     */
    public static String getSHA256(String str){
        MessageDigest messageDigest;
        String encodestr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodestr = byteToHexStr(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodestr;
    }

    /**
     * 验证手机号码
     * @param phone
     * @return
     */
    public static boolean isPhone(String phone) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phone.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            return isMatch;
        }
    }

    /**
     * 校验EMAIL格式，真为正确
     *
     * @author
     * @date 2017-7-19
     * @param email
     * @return true 为格式正确 false 为格式错误
     */
    public static boolean emailFormat(String email) {
        boolean tag = true;
        if (!email.matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+")) {
            tag = false;
        }
        return tag;
    }

    /**
     * 正则表达式检验车牌信息
     * @param content
     * @return
     */
    public static boolean checkPlateNumberFormat(String content) {
        String pattern = "([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼]{1}(([A-HJ-Z]{1}[A-HJ-NP-Z0-9]{5})|([A-HJ-Z]{1}(([DF]{1}[A-HJ-NP-Z0-9]{1}[0-9]{4})|([0-9]{5}[DF]{1})))|([A-HJ-Z]{1}[A-D0-9]{1}[0-9]{3}警)))|([0-9]{6}使)|((([沪粤川云桂鄂陕蒙藏黑辽渝]{1}A)|鲁B|闽D|蒙E|蒙H)[0-9]{4}领)|(WJ[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼·•]{1}[0-9]{4}[TDSHBXJ0-9]{1})|([VKHBSLJNGCE]{1}[A-DJ-PR-TVY]{1}[0-9]{5})";
        return Pattern.matches(pattern, content);
    }

}
