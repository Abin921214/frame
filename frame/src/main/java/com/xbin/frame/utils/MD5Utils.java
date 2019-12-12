package com.xbin.frame.utils;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密解密工具类
 * 普通加密 与  盐加密
 * @author xiaobin
 */
public class MD5Utils {

    /**
     * 获取MD5加密
     * @param pwd 需要加密的字符串
     * @return String 字符串 加密后的字符串
     */
    public static String getPwd(String pwd) {
        try {
            // 创建加密对象
            MessageDigest digest = MessageDigest.getInstance("md5");

            // 调用加密对象的方法，加密的动作已经完成
            byte[] bs = digest.digest(pwd.getBytes());
            // 接下来，我们要对加密后的结果，进行优化，按照mysql的优化思路走
            // mysql的优化思路：
            // 第一步，将数据全部转换成正数：
            String hexString = "";
            for (byte b : bs) {
                // 第一步，将数据全部转换成正数：
                // 解释：为什么采用b&255
                /*
                 * b:它本来是一个byte类型的数据(1个字节) 255：是一个int类型的数据(4个字节)
                 * byte类型的数据与int类型的数据进行运算，会自动类型提升为int类型 eg: b: 1001 1100(原始数据)
                 * 运算时： b: 0000 0000 0000 0000 0000 0000 1001 1100 255: 0000
                 * 0000 0000 0000 0000 0000 1111 1111 结果：0000 0000 0000 0000
                 * 0000 0000 1001 1100 此时的temp是一个int类型的整数
                 */
                int temp = b & 255;
                // 第二步，将所有的数据转换成16进制的形式
                // 注意：转换的时候注意if正数>=0&&<16，那么如果使用Integer.toHexString()，可能会造成缺少位数
                // 因此，需要对temp进行判断
                if (temp < 16 && temp >= 0) {
                    // 手动补上一个“0”
                    hexString = hexString + "0" + Integer.toHexString(temp);
                } else {
                    hexString = hexString + Integer.toHexString(temp);
                }
            }
            return hexString;
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 普通MD5加密 01
     */
    public static String getStrMD5(String inStr) {
        // 获取MD5实例
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println(e.toString());
            return "";
        }

        // 将加密字符串转换为字符数组
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        // 开始加密
        for (int i = 0; i < charArray.length; i++){
            byteArray[i] = (byte) charArray[i];
        }
        byte[] digest = md5.digest(byteArray);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            int var = digest[i] & 0xff;
            if (var < 16){
                sb.append("0");
            }
            sb.append(Integer.toHexString(var));
        }
        return sb.toString();
    }

    /**
     * 普通MD5加密 02
     */
    public static String getStrrMD5(String password) {

        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            byte strTemp[] = password.getBytes("UTF-8");
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte md[] = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 15];
                str[k++] = hexDigits[byte0 & 15];
            }

            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * MD5双重解密
     */
    public static String getconvertMD5(String inStr) {
        char[] charArray = inStr.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            charArray[i] = (char) (charArray[i] ^ 't');
        }
        String str = String.valueOf(charArray);
        return str;
    }

    /**
     * 使用Apache的Hex类实现Hex(16进制字符串和)和字节数组的互转
     */
    private static String md5Hex(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(str.getBytes());
            return new String(new Hex().encode(digest));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
            return "";
        }
    }

    /**
     * 加盐MD5加密
     */
    public static String getSaltMD5(String password, String Salt) {

        password = md5Hex(password + Salt);
        return password;
    }

    /**
     * 验证加盐后是否和原文一致
     */
    public static boolean getSaltverifyMD5(String password, String pwd, String Salt) {
        return md5Hex(password + Salt).equals(pwd);
    }


}

