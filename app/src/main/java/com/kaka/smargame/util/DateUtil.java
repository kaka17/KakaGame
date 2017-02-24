package com.kaka.smargame.util;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@SuppressLint("SimpleDateFormat")
public class DateUtil {
    public final static String PATTERN = "yyyy-MM-dd HH:mm:ss";
    public final static String PATTERN_NOSECOND = "yyyy-MM-dd HH:mm";
    public final static String DEF_FORMAT = "yyyy-MM-dd HH:mm";
    public final static String YEAR_MONTH_DAY = "yyyy-MM-dd";
    public final static String YESTODAY = "昨天";
    public final static long ONE_MINUTE = 1000 * 60;
    public final static long ONE_HOUR = ONE_MINUTE * 60;
    public final static long ONE_DAY = ONE_HOUR * 24;

    private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static String formatTime(String date) {
        String result = "";
        try {
            SimpleDateFormat formatOld = new SimpleDateFormat(PATTERN);
            Date dateOld = formatOld.parse(date);
            SimpleDateFormat formatNew = new SimpleDateFormat("yyyy年MM月dd日 hh点mm分");
            result = formatNew.format(dateOld);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getNow(String format) {
        if (null == format || "".equals(format)) {
            format = PATTERN;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String date = sdf.format(new Date());
        return date;
    }

    public static boolean isToday(long time) {
        return getNow(YEAR_MONTH_DAY).equals(getDateStr(time, YEAR_MONTH_DAY));
    }

    public static Date stringToDate(String _date, String format) {
        if (null == format || "".equals(format)) {
            format = PATTERN;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String dateToString(Date date, String format) {
        if (null == format || "".equals(format)) {
            format = PATTERN;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        return sdf.format(date);
    }

    public static String dateToString(Date date, String format, Locale locale) {
        if (null == format || "".equals(format)) {
            format = PATTERN;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format, locale);

        return sdf.format(date);
    }

    public static Date intToDate(long lDate) {
        Date date = new Date(lDate);
        return date;
    }

    public static Date cstToDate(String dateStr) {
        DateFormat df = new SimpleDateFormat(
                "EEE MMM dd HH:mm:ss '+0800' yyyy", Locale.CHINA);// CST格式
        Date date = null;
        try {
            date = df.parse(dateStr);// parse函数进行转换
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    public static String getDateStr(long times, String format) {
        if (times == 0)
            return "";
        Date date = intToDate(times);
        return dateToString(date, format);
    }

    public static String getCurrentDateStr(String format) {
        return getDateStr(new Date().getTime(), format);
    }

    public static Date getDate(int year, int month, int weekInMonth,
                               int dayInWeek) {
        Calendar date = Calendar.getInstance();
        date.clear();
        date.set(Calendar.YEAR, year);
        date.set(Calendar.MONTH, month - 1);
        date.set(Calendar.DAY_OF_WEEK_IN_MONTH, weekInMonth);
        date.set(Calendar.DAY_OF_WEEK, dayInWeek + 1);
        return date.getTime();
    }

    public static Date getDate(int month, int weekInMonth, int dayInWeek) {
        Calendar date = Calendar.getInstance();
        int year = date.get(Calendar.YEAR);
        date.clear();
        date.set(Calendar.YEAR, year);
        date.set(Calendar.MONTH, month - 1);
        date.set(Calendar.DAY_OF_WEEK_IN_MONTH, weekInMonth);
        date.set(Calendar.DAY_OF_WEEK, dayInWeek + 1);
        return date.getTime();
    }

    public static String getDate(String format, int year, int month, int dayOfMonth) {
        Calendar date = Calendar.getInstance();
        date.clear();
        date.set(Calendar.YEAR, year);
        date.set(Calendar.MONTH, month);
        date.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        return getDateStr(date.getTime().getTime(), format);
    }

    public static String getDateTime(String format, int year, int month, int dayOfMonth, int hour, int minute) {
        Calendar date = Calendar.getInstance();
        date.clear();
        date.set(Calendar.YEAR, year);
        date.set(Calendar.MONTH, month);
        date.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        date.set(Calendar.HOUR_OF_DAY, hour);
        date.set(Calendar.MINUTE, minute);
        return getDateStr(date.getTime().getTime(), format);
    }

    public static long getDateTimeSecond(String format, int year, int month, int dayOfMonth, int hour, int minute) {
        Calendar date = Calendar.getInstance();
        date.set(Calendar.YEAR, year);
        date.set(Calendar.MONTH, month);
        date.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        date.set(Calendar.HOUR_OF_DAY, hour);
        date.set(Calendar.MINUTE, minute);
        return date.getTimeInMillis() / 1000;
    }

    public static long getDateSecond(String format, int year, int month, int dayOfMonth) {
        Calendar date = Calendar.getInstance();
        date.set(Calendar.YEAR, year);
        date.set(Calendar.MONTH, month);
        date.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        return date.getTimeInMillis() / 1000;
    }

    public static String getDate(int month, int weekInMonth, int dayInWeek,
                                 String format) {
        Date date = getDate(month, weekInMonth, dayInWeek);
        return getDateStr(date.getTime(), format);
    }

    public static String getFormatTime(String sdate) {
        Date time = null;
        //if (TimeZoneUtil.isInEasternEightZones()) {
        time = toDate(sdate);
        //} else {
        //	time = TimeZoneUtil.transformTime(toDate(sdate),
        //			TimeZone.getTimeZone("GMT+08"), TimeZone.getDefault());
        //}
        return getFormatTime(time.getTime());
    }

    private static Date toDate(String sdate) {
        try {
            return dateFormater.get().parse(sdate);
        } catch (ParseException e) {
            return new Date();
        }
    }

    public static String getFormatTime(long time) {
        if (time == -1) {
            return "";
        }
        long current = System.currentTimeMillis();
        long inteval = current - time;
        if (isToday(time)) {
            if (inteval < ONE_MINUTE) {
                return "刚刚";
            } else if (inteval < ONE_HOUR) {
                return inteval / ONE_MINUTE + "分钟前";
            } else if (inteval < ONE_DAY) {
                return inteval / ONE_HOUR + "小时前";
            }
            return "";
        } else {
            if (isTheSameYear(time)) {
                if (isYestoday(time)) {
                    return YESTODAY + " " + getDateStr(time, "HH:mm");
                }
                return getDateStr(time, "MM-dd HH:mm");
            } else {
                return getDateStr(time, "yyyy-MM-dd HH:mm");
            }
        }
    }

    private static boolean isYestoday(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        cal.add(Calendar.DAY_OF_YEAR, 1);
        return isToday(cal.getTimeInMillis());
    }

    private static boolean isTheSameYear(long time) {
//        DateTime dt = new DateTime(time);
//        DateTime now = new DateTime();
//        return dt.getYear() == now.getYear();
        return false;
    }

    public static String getIntervalHours(long begin, long end) {
        if (end < begin)
            return "";
        long between = (end - begin) / 1000;// 除以1000是为了转换成秒
        long day = between / (24 * 3600);
        long hour = between % (24 * 3600) / 3600;
        long minute = between % 3600 / 60;
        long second = between % 60 / 60;
        return (day == 0 ? "" : day + "天 ") + (hour == 0 ? "" : hour + "小时 ")
                + (minute == 0 ? "" : minute + "分 ")
                + (second == 0 ? "" : second + "秒");
    }

    public static long getHours(long finishTime, long endTime) {
        long time = finishTime - endTime;
        if (time > 0) {
            return time / (3600 * 1000);
        }
        return 0;
    }

    public static boolean isSameTimeWithoutSecond(long time, long time2) {
        return getDateStr(time, DEF_FORMAT).equals(
                getDateStr(time2, DEF_FORMAT));
    }

    public static long getDurationMinutes(long begin, long end) {
        if (end < begin)
            return 0;
        long between = (end - begin) / 1000;// 除以1000是为了转换成秒
        long minutes = between / 60;
        return minutes;
    }

    public static String getDurationMinutes(long minutes) {
        if (minutes <= 0)
            return "";
        long day = minutes / (24 * 60);
        long hour = minutes % (24 * 60) / 60;
        long minute = minutes % 60;
        StringBuilder buf = new StringBuilder();
        buf.append((day == 0 ? "" : day + "天 "));
        buf.append((hour == 0 ? "" : hour + "小时 "));
        buf.append((minute == 0 ? "" : minute + "分 "));
        return buf.toString();
    }

    public static String getWeekByDay(String dateStr) {
        Date date = stringToDate(dateStr, YEAR_MONTH_DAY);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        switch (dayOfWeek) {
            case 1:
                return "星期日";
            case 2:
                return "星期一";
            case 3:
                return "星期二";
            case 4:
                return "星期三";
            case 5:
                return "星期四";
            case 6:
                return "星期五";
            case 7:
                return "星期六";
        }
        return "";
    }

    public static int getDayBetweenDate(String start, String end) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(DateUtil.YEAR_MONTH_DAY);
            long from = df.parse(start).getTime();
            long to = df.parse(end).getTime();
            return (int) ((to - from) / (1000 * 60 * 60 * 24));
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int getDayBetweenDate(Date start, Date end) {
        long from = start.getTime();
        long to = end.getTime();
        return (int) ((to - from) / (1000 * 60 * 60 * 24));
    }

    public static String getDateString(Calendar calendar) {
        StringBuilder sb = new StringBuilder();
        sb.append(calendar.get(Calendar.YEAR));
        sb.append("-");
        sb.append(calendar.get(Calendar.MONTH) + 1);
        sb.append("-");
        sb.append(calendar.get(Calendar.DAY_OF_MONTH));
        return sb.toString();
    }
}
