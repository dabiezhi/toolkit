package com.bloom.bloomutils.core.utils;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author taosy
 * @version V1.0
 * @since 2022-01-07 10:24
 */
public class DateUtil {

    public final static String    YYYY_MM_DD_HH_MM      = "yyyy-MM-dd HH:mm";
    public final static String    YYYY_MM_DD_HH_MM_SS   = "yyyy-MM-dd HH:mm:ss";
    public final static String    DEFAULT_DATE_FORMAT   = YYYY_MM_DD_HH_MM_SS;
    public final static String    YYYYMMDDHHMM          = "yyyyMMddHHmm";
    public final static String    YYYYMMDDHHMMSS        = "yyyyMMddHHmmss";
    public final static String    YYYY_MM_DD            = "yyyy-MM-dd";
    public final static String    YYYYMMDD              = "yyyyMMdd";
    public final static String    YYYYMM                = "yyyyMM";
    public final static String    YYYY_MM               = "yyyy年MM月";
    public final static String    YYYY_MM_DD_CHAR       = "yyyy年MM月dd日";
    public final static String    MM_DD_                = "MM月dd日";
    public final static String    YYYY_MM_DD_HHmm       = "yyyy年MM月dd日 HH:mm";
    public final static String    YYYY_MM_DD_00_00_00   = "yyyy-MM-dd 00:00:00";
    public final static String    YYYY_MM_DD_23_59_59   = "yyyy-MM-dd 23:59:59";
    public final static String    YYYY_MM_DD_T_HH_MM_SS = "yyyy-MM-dd'T'HH:mm:ss";

    private static final String[] WEEK_DAYS             = { "周日", "周一", "周二", "周三", "周四", "周五",
                                                            "周六" };

    public static String getStringDateFromUnix(long unixTime) {
        Date unixDate = new Date(unixTime);
        return parseTime(unixDate);
    }

    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(new Date());
    }

    public static String getCurrentTimeDefault() {
        return getCurrentTime(YYYY_MM_DD_HH_MM_SS);
    }

    public static String getCurrentTime(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }

    public static String getDayTime() {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_00_00_00);
        return sdf.format(new Date());
    }

    public static String getDateStamp() {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
        return sdf.format(new Date());
    }

    public static Date formatDate(String dateStr) {
        return formatDate(dateStr, YYYY_MM_DD);
    }

    public static Date formatTime(String dateStr) {
        return formatDate(dateStr, YYYY_MM_DD_HH_MM_SS);
    }

    public static Date formatDate(String dateStr, String format) {
        if (StringUtils.isEmpty(dateStr) || StringUtils.isEmpty(format)) {
            return null;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date date = sdf.parse(dateStr);
            return date;
        } catch (ParseException e) {
            return null;
        }
    }

    public static String parseDate(Date date) {
        return parseDate(date, YYYY_MM_DD);
    }

    public static String parseDate(Date date, String format) {
        if (null == date || StringUtils.isEmpty(format)) {
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String parseTime(Date date) {
        return parseDate(date, YYYY_MM_DD_HH_MM_SS);
    }

    public static boolean checkDateStrValid(String dateStr, String format) {
        if (StringUtils.isEmpty(dateStr) || StringUtils.isEmpty(format)) {
            return false;
        }

        Date date = formatDate(dateStr, format);
        if (null == date) {
            return false;
        }

        String newDateStr = parseDate(date, format);
        return dateStr.equals(newDateStr);
    }

    public static boolean checkDateStrValid(String dateStr) {
        if (StringUtils.isEmpty(dateStr)) {
            return false;
        }

        Date date = formatDate(dateStr, YYYY_MM_DD);
        if (null == date) {
            return false;
        }

        String newDateStr = parseDate(date, YYYY_MM_DD);
        return dateStr.equals(newDateStr);
    }

    /**
     * 在指定时间的基础上增加 days 后的日期
     *
     * @param days
     * @return
     */
    public static Date addDay(int days, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();
    }

    /**
     * 在指定时间的基础上增加 hours 后的小时
     */
    public static Date addHour(int hours, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }

    /**
     * 在指定时间的基础上增加 hours 后的小时
     */
    public static Date addSecond(int second, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * 在指定时间的基础上增加 minuted 后的小时
     */
    public static Date addMin(int mins, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, mins);
        return calendar.getTime();
    }

    /**
     * 在当前时间的基础上增加 days 后的日期
     *
     * @param days
     * @return
     */
    public static Date addDay(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();
    }

    public static Long getTimeStampFromDate(String dateStr, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(dateStr).getTime() / 1000;
        } catch (ParseException e) {
        }
        return null;
    }

    /**
     * 在指定时间的基础上增加 days 后的日期
     */
    public static Date addMonth(int month, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    public static Date addYear(int year, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, year);
        return calendar.getTime();
    }

    public static int getMonthSpace(Date startTime, Date endTime) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(endTime);
        c2.setTime(startTime);

        int result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);

        return result == 0 ? 1 : Math.abs(result);

    }

    public static Date getLastDayOfMonth(String date) {
        Date firstDate = formatDate(date + "-01");
        if (null == firstDate) {
            return null;
        }

        Date endDate = DateUtil.addMonth(1, firstDate);
        return DateUtil.addDay(-1, endDate);
    }

    /**
     * 返回当前日期的最后一秒时间点
     */
    public static Date getStartTimeOfDay(Date date) {
        String dateStr = parseDate(date) + " 00:00:00";
        return formatTime(dateStr);
    }

    /**
     * 返回加减日期后的日期0点时间
     */
    public static Date getStartTimeOfDay(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, days);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 返回加减日期后的日期0点时间
     */
    public static Date getEndTimeOfDay(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, days);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * 返回当前日期的最后一秒时间点
     */
    public static Date getEndTimeOfDay(Date date) {
        String dateStr = parseDate(date) + " 23:59:59";
        return formatTime(dateStr);
    }

    /**
     * 获取两日期之间相差天数
     */
    public static long twoDateBetweenDays(Date startDate, Date endDate) {
        return (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);
    }

    /**
     * 校验日期是否相同
     */
    public static boolean isSameDate(Date date1, Date date2) {
        return parseTime(date1).equals(parseTime(date2));
    }

    /**
     * 校验time是否在某个start和end时间段内
     */
    public static boolean botween(Date time, Date start, Date end) {
        return time.compareTo(start) >= 0 && time.compareTo(end) <= 0;
    }

    /**
     * 获取上n个小时整点小时时间
     */
    public static String getLastHourTime(Date date, int n) {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        ca.set(Calendar.HOUR_OF_DAY, ca.get(Calendar.HOUR_OF_DAY) - n);
        date = ca.getTime();
        return sdf.format(date);
    }

    /**
     * 获取当前时间的整点小时时间
     */
    public static String getCurrHourTime(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        date = ca.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        return sdf.format(date);
    }

    public static String getTimeInterval(Date date) {
        Date cal = getWeekDate(date);
        return parseDate(cal, YYYY_MM_DD_00_00_00);
    }

    private static Date getWeekDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal.getTime();
    }

    /**
     * 获取本周的周一零点的时间
     */
    public static String getStartTimeOfCurrentWeek() {
        return getTimeInterval(new Date());
    }

    /**
     * 获取本周的周一零点的时间
     */
    public static Date getWeekStart() {
        return getWeekDate(new Date());
    }

    public static int getDay(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int day = ca.get(Calendar.DAY_OF_MONTH);
        return day;
    }

    public static int getWeek(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int week = ca.get(Calendar.WEEK_OF_YEAR);
        return week;
    }

    public static int getMonth(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int month = ca.get(Calendar.MONTH);
        return month + 1;
    }

    public static int getYear(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int year = ca.get(Calendar.YEAR);
        return year;
    }

    /**
     * 获取一天的开始时间 00:00:00.000
     */
    public static Date getStartOfDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取一天的结束 23:59:59.999
     */
    public static Date getEndOfDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    /**
     * 获取上个月的第一天 0点0时0分
     */
    public static Date getStartTimeOfLastMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1);
        int last = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, last);
        return getStartOfDay(cal.getTime());
    }

    /**
     * 获取上个月的最后一天 23:59:59.999
     */
    public static Date getEndTimeOfLastMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1);
        int last = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, last);
        return getEndOfDay(cal.getTime());
    }

    public static Date getFirstDayDateOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int last = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, last);
        return cal.getTime();
    }

    public static Date getLastDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int last = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, last);
        return cal.getTime();

    }

    public static String formatSeconds(int callDurationSeconds) {
        int hours = 0;
        int minitus = 0;
        int seconds = 0;
        if (callDurationSeconds >= 60 * 60) {
            hours = callDurationSeconds / 60 * 60;
            callDurationSeconds = callDurationSeconds % 60 * 60;
        }
        if (callDurationSeconds >= 60) {
            minitus = callDurationSeconds / 60;
            seconds = callDurationSeconds % 60;
        }
        if (callDurationSeconds >= 0) {
            seconds = callDurationSeconds % 60;
        }
        if (hours > 0) {
            return paddingZero(hours) + ":" + paddingZero(minitus) + ":" + paddingZero(seconds);
        } else {
            return paddingZero(minitus) + ":" + paddingZero(seconds);
        }
    }

    private static String paddingZero(int number) {
        if (number < 10) {
            return "0" + number;
        } else {
            return "" + number;
        }
    }

    /**
     * 取得季度月
     */
    public static int[] getSeasonDate(Date date) {
        int[] season = new int[3];

        int nSeason = getSeason(date);
        if (nSeason == 1) {// 第一季度
            season[0] = 1;
            season[1] = 2;
            season[2] = 3;
        } else if (nSeason == 2) {// 第二季度
            season[0] = 4;
            season[1] = 5;
            season[2] = 6;
        } else if (nSeason == 3) {// 第三季度
            season[0] = 7;
            season[1] = 8;
            season[2] = 9;
        } else if (nSeason == 4) {// 第四季度
            season[0] = 10;
            season[1] = 11;
            season[2] = 12;
        }
        return season;
    }

    /**
     *
     * 1 第一季度 2 第二季度 3 第三季度 4 第四季度
     */
    public static int getSeason(Date date) {

        int season = 0;

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
                season = 1;
                break;
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                season = 2;
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
                season = 3;
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                season = 4;
                break;
            default:
                break;
        }
        return season;
    }

    /**
     *
     * 1 第一季度 2 第二季度 3 第三季度 4 第四季度
     */
    public static int getSeason(String months) {
        int season = 0;
        int month = Integer.valueOf(months) - 1;
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
                season = 1;
                break;
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                season = 2;
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
                season = 3;
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                season = 4;
                break;
            default:
                break;
        }
        return season;
    }

    /**
     * 根据日期获取当天是周几
     * @param datetime 日期
     * @return 周几
     */
    public static String dateStrToWeek(String datetime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date date;
        try {
            date = sdf.parse(datetime);
            cal.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return WEEK_DAYS[w];
    }

    /**
     * 根据日期获取当天是周几
     * @param datetime 日期
     * @return 周几
     */
    public static String dateToWeek(Date datetime) {
        if (datetime == null) {
            return "";
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(datetime);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return WEEK_DAYS[w];
    }

}
