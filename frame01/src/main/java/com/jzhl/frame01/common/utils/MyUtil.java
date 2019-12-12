package com.jzhl.frame01.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.jzhl.frame01.common.bean.BaseResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MyUtil extends DateUtils {

    /** 空字符串 */
    private static final String NULLSTR = "";

    /** 下划线 */
    private static final char SEPARATOR = '_';

    public static String YYYY = "yyyy";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static String DD_HH_MM_SS = "dd HH:mm:ss";

    public static String HH_MM_SS = "HH:mm:ss";

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 控制层验证处理方法
     * @param result
     * @param redirectAttributes
     * @return
     */
    public static boolean controllerValid(BindingResult result, RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                redirectAttributes.addFlashAttribute("error", error.getDefaultMessage());
            }
            return false;
        }
        return true;
    }

    public static boolean isEmpty(Integer objects)
    {
        return objects == null || objects == 0;
    }

    public static boolean isNotEmpty(Integer objects)
    {
        return objects != null && objects > 0;
    }

    /**
     * * 判断一个Collection是否为空， 包含List，Set，Queue
     *
     * @param coll 要判断的Collection
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(Collection<?> coll)
    {
        return isNull(coll) || coll.isEmpty();
    }

    /**
     * * 判断一个Collection是否非空，包含List，Set，Queue
     *
     * @param coll 要判断的Collection
     * @return true：非空 false：空
     */
    public static boolean isNotEmpty(Collection<?> coll)
    {
        return !isEmpty(coll);
    }

    /**
     * * 判断一个对象数组是否为空
     *
     * @param objects 要判断的对象数组
     ** @return true：为空 false：非空
     */
    public static boolean isEmpty(Object[] objects)
    {
        return isNull(objects) || (objects.length == 0);
    }

    /**
     * * 判断一个对象数组是否非空
     *
     * @param objects 要判断的对象数组
     * @return true：非空 false：空
     */
    public static boolean isNotEmpty(Object[] objects)
    {
        return !isEmpty(objects);
    }

    /**
     * * 判断一个Map是否为空
     *
     * @param map 要判断的Map
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(Map<?, ?> map)
    {
        return isNull(map) || map.isEmpty();
    }

    /**
     * * 判断一个Map是否为空
     *
     * @param map 要判断的Map
     * @return true：非空 false：空
     */
    public static boolean isNotEmpty(Map<?, ?> map)
    {
        return !isEmpty(map);
    }

    /**
     * * 判断一个字符串是否为空串
     *
     * @param str String
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(String str)
    {
        return isNull(str) || NULLSTR.equals(str.trim());
    }

    /**
     * * 判断一个字符串是否为非空串
     *
     * @param str String
     * @return true：非空串 false：空串
     */
    public static boolean isNotEmpty(String str)
    {
        return !isEmpty(str);
    }

    /**
     * * 判断一个对象是否为空
     *
     * @param object Object
     * @return true：为空 false：非空
     */
    public static boolean isNull(Object object)
    {
        return object == null;
    }

    /**
     * * 判断一个对象是否非空
     *
     * @param object Object
     * @return true：非空 false：空
     */
    public static boolean isNotNull(Object object)
    {
        return !isNull(object);
    }


    /**
     * API控制层验证处理方法
     * @param result
     * @return
     */
    public static BaseResult apiControllerValid(BindingResult result) throws Exception {
        try{
            if (result.hasErrors()) {
                List<ObjectError> list = result.getAllErrors();
                for (ObjectError error : list) {
                    return new BaseResult(0, error.getDefaultMessage());
                }
            }
            return new BaseResult(1, "ok");
        }catch (Exception e){
            throw e;
        }
    }


    /**
     * 验证手机号码的正确性
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
     * 分割字符串 获取List集合
     * @param str
     * @param splitStr
     * @return
     */
    public static List<String> splitString(String str,String splitStr) {
        String s = str;
        String[] s1 = s.split(splitStr);
        List<String> s2 = new ArrayList<String>();
        for (String string : s1) {
            if(StringUtils.isNotEmpty(string)){
                s2.add(string);
            }
        }
        return s2;
    }

    /**
     * 分割字符串 获取List集合
     * @param str
     * @param splitStr
     * @return
     */
    public static List<Integer> splitInt(String str,String splitStr) {
        if(StringUtils.isEmpty(str)){
            return null;
        }
        String s = str;
        String[] s1 = s.split(splitStr);
        List<Integer> s2 = new ArrayList<Integer>();
        for (String string : s1) {
            if(StringUtils.isNotEmpty(string)){
                s2.add(Integer.parseInt(string));
            }
        }
        return s2;
    }



    /**
     * 我国公民的身份证号码特点如下
     * 1.长度18位
     * 2.第1-17号只能为数字
     * 3.第18位只能是数字或者x
     * 4.第7-14位表示特有人的年月日信息
     * 请实现身份证号码合法性判断的函数，函数返回值：
     * 1.如果身份证合法返回0
     * 2.如果身份证长度不合法返回1
     * 3.如果第1-17位含有非数字的字符返回2
     * 4.如果第18位不是数字也不是x返回3
     * 5.如果身份证号的出生日期非法返回4
     *
     * @since 0.0.1
     */
    public static boolean validIdCode(String identityCode) {
        if (!identityCode.matches("\\d{17}(\\d|x|X)$")) {
            return false;
        }
        Date d = new Date();
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        int year = Integer.parseInt(df.format(d));
        if (Integer.parseInt(identityCode.substring(6, 10)) < 1900 || Integer.parseInt(identityCode.substring(6, 10)) > year) {// 7-10位是出生年份，范围应该在1900-当前年份之间
            return false;
        }
        if (Integer.parseInt(identityCode.substring(10, 12)) < 1 || Integer.parseInt(identityCode.substring(10, 12)) > 12) {// 11-12位代表出生月份，范围应该在01-12之间
            return false;
        }
        if (Integer.parseInt(identityCode.substring(12, 14)) < 1 || Integer.parseInt(identityCode.substring(12, 14)) > 31) {// 13-14位是出生日期，范围应该在01-31之间
            return false;
        }

        String[] tempA = identityCode.split("|");
        int[] a = new int[18];
        for (int i = 0; i < tempA.length - 1; i++) {
            a[i] = Integer.parseInt(tempA[i]);
        }
        int sum = 0;
        sum = (a[0] + a[10]) * 7
                + (a[1] + a[11]) * 9
                + (a[2] + a[12]) * 10
                + (a[3] + a[13]) * 5
                + (a[4] + a[14]) * 8
                + (a[5] + a[15]) * 4
                + (a[6] + a[16]) * 2
                + a[7] * 1
                + a[8] * 6
                + a[9] * 3;

        String[] v = { "1", "0", "x", "9", "8", "7", "6", "5", "4", "3", "2" }; // 校验码
        int y = sum % 11;
        if (!v[y].equalsIgnoreCase(identityCode.substring(17))) {// 第18位校验码错误
            return false;
        }
        return true;
    }

    public static final String parseDateToStr(final String format, final Date date)
    {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 日期型字符串转化为日期 格式
     */
    public static Date parseDate(Object str)
    {
        if (str == null)
        {
            return null;
        }
        try
        {
            return parseDate(str.toString(), parsePatterns);
        }
        catch (ParseException e)
        {
            return null;
        }
    }

    /**
     * 获取两个时间差  天 时 分  有天和时 不显示分
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    public static String getDateDisparity(Date startTime,Date endTime){
        try {
            //毫秒ms
            long diff = startTime.getTime() - endTime.getTime();

            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            StringBuffer sb = new StringBuffer();
            if(diffDays != 0){
                sb.append(diffDays+"天");
            }
            if(diffHours != 0){
                sb.append(diffHours+"时");
            }
            if(diffMinutes != 0 && "".equals(sb.toString())){
                sb.append(diffMinutes+"分");
            }

            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 计算两个时间段之间的 月数  例如:1.5
     * @param date1
     * @param date2
     * @return
     */
    public static Float monthsDayBetw(Date date1,Date date2){

        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        int count = 1;
        if(calendar1.get(Calendar.DATE) != 1){
            count = 0;
        }

        int year1 = calendar1.get(Calendar.YEAR);
        int year2 = calendar2.get(Calendar.YEAR);
        int month1 = calendar1.get(Calendar.MONTH);
        int month2 = calendar2.get(Calendar.MONTH);
        int day1 = calendar1.get(Calendar.DATE);
        int day2 = calendar2.get(Calendar.DATE);

        //通过年,月差计算月份差
        float months = (year2 - year1) * 12 + (month2 - month1) + count;
        //count == 0 时 需要计算天数
        if(count == 1){
            return months;
        }else{
            int day = MyUtil.getLastDay(year1,month1+1) - day1 + 1;
            //已缴费开始月最后一天计算
            return months + (day/new Float(MyUtil.getLastDay(year1,month1+1)));
        }

    }

    //获取当前月份最后一天
    public static int getLastDay(int y,int m){
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,y);
        //设置月份
        cal.set(Calendar.MONTH, m-1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        String lastDayOfMonth = sdf.format(cal.getTime());

        return Integer.parseInt(lastDayOfMonth);
    }

    public static final String getTime(){
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 获取当前时间String
     * 格式 yyyyMMdd
     * @return
     */
    public static String getNowDateYMD(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(new Date());
    }

    /**
     * 获取当前时间String
     * @return
     */
    public static String getNowDateYMD(String pattern){
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(new Date());
    }

    /**
     * String 转化 date
     * 两种格式
     * 1：yyyy-MM-dd
     * 2：yyyy-MM-dd HH:mm:ss
     * @param dateStr
     * @return
     */
    public static Date getStrChangeDate(String dateStr){
        try {
            String formatStr = "yyyy-MM-dd";
            if(dateStr.split(" ").length == 2){
                formatStr += " HH:mm:ss";
            }
            SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
            Date date = sdf.parse(dateStr);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     *
     * @return String
     */
    public static String getDate()
    {
        return dateTimeNow(YYYY_MM_DD);
    }
    public static final String dateTimeNow(final String format)
    {
        return parseDateToStr(format, new Date());
    }

    /**
     * 时间差得到 年-月-日
     * @param curr
     * @param join
     * @return
     */
    public static String getDate(Calendar curr, Calendar join){

        int day = curr.get(Calendar.DAY_OF_MONTH) - join.get(Calendar.DAY_OF_MONTH);

        int month = curr.get(Calendar.MONTH) - join.get(Calendar.MONTH);

        int year = curr.get(Calendar.YEAR) - join.get(Calendar.YEAR);

        if (day < 0) {
            month -= 1;
            curr.add(Calendar.MONTH, -1);
            day = day + curr.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        if (month < 0) {
            month = (month + 12) % 12;
            year--;
        }
        System.out.println("年龄：" + year + "年" + month + "月" + day + "天");

        String dateStr = year+"年"+month+"月"+day+"日";

        return dateStr;
    }

    /**
     * String 转换 byte[]
     * @param str
     * @return
     */
    public static byte[] strToByteArray(String str) {
        if (str == null) {
            return null;
        }
        byte[] byteArray = str.getBytes();
        return byteArray;
    }

    /**
     * byte[] 转换 String
     * @param byteArray
     * @return
     */
    public static String byteArrayToStr(byte[] byteArray) {
        if (byteArray == null) {
            return null;
        }
        String str = new String(byteArray);
        return str;
    }

    /**
     * byte[] 转换 16进账String
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 16进账String 转换 byte[]
     * @param hexString
     * @return
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    /**
     * char 转换 byte
     * @param c
     * @return
     */
    public static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * 比较两个日期之间的大小
     *
     * @param d1
     * @param d2
     * @return 前者大于后者返回true 反之false
     */
    public static boolean compareDate(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);

        int result = c1.compareTo(c2);
        if (result >= 0)
            return true;
        else
            return false;
    }

    public static void main(String[] args) throws Exception {
        HashMap<String, String> param = new HashMap<>();
        param.put("userId", "sss");

        param.put("username", "XXXX");
        String code = "1";
        String param1 = "1";
        String param2 = "1";
        String param3 = "1";
        String param4 = "1";
        String param5 = "1";

        String jsonParam = "{\"code\":"+ code +",\"param1\":\""+ param1 +"\",\"param2\":\""+ param2 +"\",\"param3\":\""+ param3 +"\",\"param4\":\""+ param4 +"\",\"param5\":\""+ param5 +"\"}";
        System.out.println(jsonParam);

//        String jsonParam = "\"{";
//        int i = 1;
//        for(Map.Entry<String, String> entry : param.entrySet()){
//            if(i == 1){
//                jsonParam  +=  "\\" + entry.getKey() +"\\":"+ entry.getValue();
//            }
//            jsonParam  +=  ",\" + " + entry.getKey() +"+ \":"+ entry.getValue();
//        }
//        jsonParam += "}";
        JSONObject pushObject = new JSONObject();
        pushObject.put("testKey01", "value01");
        pushObject.put("testKey02", "value02");

        System.out.println(pushObject.toJSONString());
    }


    public static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while( (len=inStream.read(buffer)) != -1 ){
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }

    /**
     * 转换文件大小
     * @param fileS
     * @return
     */
    public static String formetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        DecimalFormat d = new DecimalFormat("#");
        String fileSizeString = "";
        if(fileS < 1024){
            fileSizeString = d.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) +"GB";
        }
        return fileSizeString;
    }

    /**
     * 生成指定数量的随机数
     * @param len 生成的总长度
     * @return 字符串
     */
    public static String genRandomNum(int len) {
        // 35是因为数组是从0开始的，26个字母+10个数字
        final int maxNum = 10;
        int i; // 生成的随机数
        int count = 0; // 生成的密码的长度
		/*char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
				'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
				'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };*/

        char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while (count < len) {
            // 生成随机数，取绝对值，防止生成负数，

            i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1

            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count++;
            }
        }

        return pwd.toString();
    }

    /**
     * 本地图片转换Base64的方法
     * @param imgPath     
     */
    public static String ImageToBase64(String imgPath) {
        byte[] data = null;
        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(imgPath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过的字节数组字符串
        return encoder.encode(Objects.requireNonNull(data));
    }

}
