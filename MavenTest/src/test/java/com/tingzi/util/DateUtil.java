package com.tingzi.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @Author xiexiaoting
 * @Date 2021/1/15 14:06
 * @Version 1.0
 */
public class   DateUtil {
    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    /**
     * 获取当前系统时间
     *
     * @return 字符串 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringToday() {
        final Date currentTime = new Date();
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final String dateString = formatter.format(currentTime);
        return dateString;

    }

    /**
     * 获取当前时间并减去1小时  时间格式 yyyy-MM-dd HH:mm:ss
     * @param strDate
     * @return 转换后的日期
     *
     */
    public static String parseDate(String strDate) {
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        //rightNow.add(Calendar.DATE, -1);
        rightNow.add(Calendar.HOUR, -1);
        Date dt1 = rightNow.getTime();
        strDate = sdf.format(dt1);
        return strDate;
    }

    /**
     * 获取时间戳字符串
     *
     * @param time
     * @return
     */
    public static String getStringTimestamp(String time) {
        String timestamp = null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Long longTime = sdf.parse(time).getTime();
            timestamp = Long.toString(longTime);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }



    public static void main(String[] args) {
        System.out.println(parseDate(getStringToday())); //当前时间减去一小时    时间格式
        System.out.println(DateUtil.getStringTimestamp(parseDate(getStringToday())));   //当前时间减去一小时  时间戳格式
    }

}