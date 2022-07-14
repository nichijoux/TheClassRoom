package com.zh.tcr.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期操作工具类
 *
 */
public class DateUtil {

    private static final String dateFormat = "yyyy-MM-dd";
    private static final String timeFormat = "HH:mm:ss";

    /**
     * 格式化日期
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(date);

    }

    /**
     * 格式化日期
     *
     * @param date
     * @return
     */
    public static String formatTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
        return sdf.format(date);

    }

    public static Date parseTime(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 比对日期与时间大小
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static boolean dateCompare(Date beginDate, Date endDate) {
        if(null == beginDate || null == endDate) return false;
        return endDate.getTime() > beginDate.getTime();
    }

    /**
     * 比对日期与时间大小
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static boolean timeCompare(Date beginDate, Date endDate) {
        Calendar instance1 = Calendar.getInstance();
        instance1.setTime(beginDate); //设置时间为当前时间
        instance1.set(Calendar.YEAR, 0);
        instance1.set(Calendar.MONTH, 0);
        instance1.set(Calendar.DAY_OF_MONTH, 0);

        Calendar instance2 = Calendar.getInstance();
        instance2.setTime(endDate); //设置时间为当前时间
        instance2.set(Calendar.YEAR, 0);
        instance2.set(Calendar.MONTH, 0);
        instance2.set(Calendar.DAY_OF_MONTH, 0);
        return true;
    }

}
