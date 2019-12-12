package com.jzhl.frame01.common.utils;

import java.lang.management.ManagementFactory;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期通用方法调用
 * @author xiaobin
 */
public class DateUtil{

    /**
     * 格式 yyyy
     */
    public static String YYYY = "yyyy";

    /**
     * 格式 yyyy-MM
     */
    public static String YYYY_MM = "yyyy-MM";

    /**
     * 格式 yyyy-MM-dd
     */
    public static String YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * 格式 yyyyMMdd
     */
    public static String YYYYMMDD = "yyyyMMdd";

    /**
     * 格式 yyyy/MM/dd
     */
    public static String YYYYMMDD_X = "yyyy/MM/dd";

    /**
     * 格式 yyyyMMddHHmmss
     */
    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /**
     * 格式 yyyyMMddHHmmssSSS
     */
    public static String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

    /**
     * 格式 yyyy-MM-dd HH:mm:ss
     */
    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当前时间 按照指定格式
     * @param format      时间格式
     * @author xiaobin
     */
    public static String nowDate(String format) throws Exception{
        try {
            SimpleDateFormat df = new SimpleDateFormat(format);
            return df.format(new Date());
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 按照指定格式将指定日期转化成字符串
     * @param format  时间格式
     * @param date     时间
     * @return String
     * @author xiaobin
     */
    public static String dateToStr(String format, Date date) throws Exception{
        try {
            return new SimpleDateFormat(format).format(date);
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 将字符串按照指定格式转换成日期格式
     * @param format   日期格式
     * @param ts       字符串【时间字符串】
     * @return Date
     * @throws Exception
     * @author xiaobin
     */
    public static Date strToDate(String format, String ts) throws Exception {
        try {
            return new SimpleDateFormat(format).parse(ts);
        }catch (Exception e) {
            throw e;
        }
    }

    /**
     * 按照指定时间格式将时间戳转换为时间
     * @param timeStamp   时间戳
     * @param format      时间格式
     * @return String
     * @author xiaobin
     */
    public static String timeStampToDate(Integer timeStamp, String format) throws Exception{
        try {
            SimpleDateFormat sdf=new SimpleDateFormat(format);
            String sd = sdf.format(new Date(timeStamp));
            return sd;
        }catch (Exception e){
            throw e;
        }
    }


    /**
     * 获取服务器启动时间
     * @author xiaobin
     */
    public static Date getServerStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }


    /**
     * 计算两个时间差
     * @param timeOne  小的时间
     * @param timeTwo  大的时间
     * @param type     获取的时间类型 【天 day】【小时 hour】【分钟 min】【秒 sec】【all 0天0小时9分组0秒】
     * @return String
     * @author xiaobin
     */
    public static String getTimeDiff(Date timeOne, Date timeTwo, String type){
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = Math.abs(timeOne.getTime() - timeTwo.getTime());
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        long sec = diff % nd % nh % nm / ns;

        long dayAll = day;
        long hourAll = day * 24 + hour;
        long minAll = day * 24 * 60 +  hour * 60 + min;
        long secAll = day * 24 * 60 * 60 +  hour * 60 * 60 + min * 60;

        String returnStr = "";
        switch (type){
            case "day":
                returnStr = Math.abs(dayAll) +"";
                break;
            case "hour":
                returnStr = Math.abs(hourAll) +"";
                break;
            case "min":
                returnStr = Math.abs(minAll) +"";
                break;
            case "sec":
                returnStr = Math.abs(secAll) +"";
                break;
            case "all":
                returnStr = Math.abs(day) + "天" + Math.abs(hour) + "小时" + Math.abs(min) + "分" + Math.abs(sec) + "秒";
                break;
            default:
                returnStr = "";
                break;
        }

        return returnStr;
    }


    /**
     * 比较两个时间的大小
     * @param timeOne 前者时间
     * @param timeTwo 后者时间
     * @return 【1 前者大于或者  -1 后者大于前者 0 相等】
     * @author xiaobin
     */
    public static int compareDate(Date timeOne, Date timeTwo) throws Exception{
        try {
            if (timeOne.getTime() > timeTwo.getTime()) {
                return 1;
            } else if (timeOne.getTime() < timeTwo.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 获取指定时间几年前时间
     * @param d       指定时间
     * @param year    年数
     * @return Date
     * @author xiaobin
     */
    public static Date getYearBefore(Date d,int year){
        Calendar now =Calendar.getInstance();
        now.setTime(d);
        now.add(Calendar.YEAR, -year);
        return now.getTime();
    }

    /**
     * 获取指定时间几年后时间
     * @param d       指定时间
     * @param year    年数
     * @return Date
     * @author xiaobin
     */
    public static Date getYearAfter(Date d,int year){
        Calendar now =Calendar.getInstance();
        now.setTime(d);
        now.add(Calendar.YEAR, year);
        return now.getTime();
    }

    /**
     * 获取指定时间几个前后时间
     * @param d       指定时间
     * @param month   月数
     * @return Date
     * @author xiaobin
     */
    public static Date getMonthBefore(Date d,int month){
        Calendar now =Calendar.getInstance();
        now.setTime(d);
        now.add(Calendar.MONTH, -month);
        return now.getTime();
    }

    /**
     * 获取指定时间几个月后时间
     * @param d       指定时间
     * @param month   月数
     * @return Date
     * @author xiaobin
     */
    public static Date getMonthAfter(Date d,int month){
        Calendar now =Calendar.getInstance();
        now.setTime(d);
        now.add(Calendar.MONTH, month);
        return now.getTime();
    }



    /**
     * 获取指定时间几天前时间
     * @param d       指定时间
     * @param day     天数
     * @return Date
     * @author xiaobin
     */
    public static Date getDayBefore(Date d,int day){
        Calendar now =Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE,now.get(Calendar.DATE)-day);
        return now.getTime();
    }

    /**
     * 获取指定时间几天后时间
     * @param d       指定时间
     * @param day     天数
     * @return Date
     * @author xiaobin
     */
    public static Date getDayAfter(Date d,int day){
        Calendar now =Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE,now.get(Calendar.DATE)+day);
        return now.getTime();
    }


    /**
     * 获取指定时间几小时前时间
     * @param d       指定时间
     * @param hour    小时
     * @return Date
     * @author xiaobin
     */
    public static Date getHourBefore(Date d,int hour){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        /* HOUR_OF_DAY 指示一天中的小时 */
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - hour);
        return calendar.getTime();
    }

    /**
     * 获取指定时间几小时后时间
     * @param d       指定时间
     * @param hour    小时
     * @return Date
     * @author xiaobin
     */
    public static Date getHourAfter(Date d,int hour){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        /* HOUR_OF_DAY 指示一天中的小时 */
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hour);
        return calendar.getTime();
    }

    /**
     * 获取指定时间几分钟后时间
     * @param d       指定时间
     * @param min     分钟数
     * @return Date
     * @author xiaobin
     */
    public static Date getMinAfter(Date d,int min){
        Calendar now =Calendar.getInstance();
        now.setTime(d);
        now.add(Calendar.MINUTE, min);
        return now.getTime();
    }


    /**
     * 得到几分钟前的时间
     * @param d       指定时间
     * @param min     分钟数
     * @return Date
     * @author xiaobin
     */
    public static Date getMinBefore(Date d,int min){
        Calendar now =Calendar.getInstance();
        now.setTime(d);
        now.add(Calendar.MINUTE, -min);
        return now.getTime();
    }

    /**
     * 获取指定时间几秒后时间
     * @param d       指定时间
     * @param second  秒数
     * @return Date
     * @author xiaobin
     */
    public static Date getSecondAfter(Date d,int second){
        Calendar now =Calendar.getInstance();
        now.setTime(d);
        now.add(Calendar.SECOND, second);
        return now.getTime();
    }

    /**
     * 得到几秒前的时间
     * @param d       指定时间
     * @param second  秒数
     * @return Date
     * @author xiaobin
     */
    public static Date getSecondBefore(Date d,int second){
        Calendar now =Calendar.getInstance();
        now.setTime(d);
        now.add(Calendar.SECOND, -second);
        return now.getTime();
    }

    /**
     * 判断指定时间是否在某时间区间内，注意时间格式要一致
     * @param compareTime 比较时间
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return boolean 【false 不在区间内  true 在区间内】
     * @author xiaobin
     */
    public static boolean isEffectiveDate(Date compareTime, Date startTime, Date endTime) {
        if (compareTime.getTime() == startTime.getTime() || compareTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(compareTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }


}
