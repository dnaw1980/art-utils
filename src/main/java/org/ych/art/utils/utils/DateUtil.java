package org.ych.art.utils.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {

    /**
     * 日期格式：yyyy-MM-dd
     */
    final static public String DATE_FMT = "yyyy-MM-dd";
    final static private DateTimeFormatter DATE_FMTER = DateTimeFormatter.ofPattern(DATE_FMT);

    /**
     * 日期格式：yyyy年MM月dd日
     */
    final static public String DATE_FMT_CN = "yyyy年MM月dd日";
    final static private DateTimeFormatter DATE_FMTER_CN = DateTimeFormatter.ofPattern(DATE_FMT_CN);

    /**
     * 日期格式：dd
     */
    final static public String DAY_FMT = "dd";
    final static private DateTimeFormatter DAY_FMTER = DateTimeFormatter.ofPattern(DAY_FMT);

    /**
     * 时间格式：HH:mm:ss
     */
    final static public String TIME_FMT = "HH:mm:ss";
    final static private DateTimeFormatter TIME_FMTER = DateTimeFormatter.ofPattern(TIME_FMT);

    /**
     * 日期时间格式：yyyy-MM-dd HH:mm:ss
     */
    final static public String DATETIME_FMT = "yyyy-MM-dd HH:mm:ss";
    final static private DateTimeFormatter DATETIME_FMTER = DateTimeFormatter.ofPattern(DATETIME_FMT);

    /**
     * 日期时间格式（压缩型）：yyyyMMddHHmmss
     */
    final static public String COMPACT_DATETIME_FMT = "yyyyMMddHHmmss";
    final static private DateTimeFormatter COMPACT_DATETIME_FMTER = DateTimeFormatter.ofPattern(COMPACT_DATETIME_FMT);

    /**
     * 日期格式（压缩型）：yyyyMMdd
     */
    final static public String COMPACT_DATE_FMT = "yyyyMMdd";
    final static private DateTimeFormatter COMPACT_DATE_FMTER = DateTimeFormatter.ofPattern(COMPACT_DATE_FMT);

    /**
     * 时间格式（压缩型）：HHmmss
     */
    final static public String COMPACT_TIME_FMT = "HHmmss";
    final static private DateTimeFormatter COMPACT_TIME_FMTER = DateTimeFormatter.ofPattern(COMPACT_TIME_FMT);

    /**
     * 返回当前日期类型
     *
     * @return
     */
    public static java.sql.Date getDate() {
        return java.sql.Date.valueOf(LocalDate.now());
    }

    /**
     * 按给定毫秒数<br>
     * 返回当前时间日期类型
     *
     * @return
     */
    public static java.sql.Date getDate(long time) {
        return new java.sql.Date(time);
    }

    /**
     * 返回给定串 按 yyyy-MM-dd 的日期型
     *
     * @param strDt
     * @return
     */
    public static java.sql.Date getDate(String strDt) {
        return java.sql.Date.valueOf(LocalDate.parse(strDt, DATE_FMTER));
    }

    /**
     * 返回给定串 按 HH:mm:ss 的时间型
     *
     * @param strDt
     * @return
     */
    public static java.sql.Time getTime(String strDt) {
        return java.sql.Time.valueOf(LocalTime.parse(strDt, TIME_FMTER));
    }

    /**
     * 返回给定串 按 yyyy-MM-dd HH:mm:ss 的日期时间型
     *
     * @param strDt
     * @return
     */
    public static Timestamp getDateTime(String strDt) {
        return Timestamp.valueOf(LocalDateTime.parse(strDt, DATETIME_FMTER));
    }

    /**
     * 返回给定串 按 yyyyMMdd 的日期型
     *
     * @param strDt
     * @return
     */
    public static java.sql.Date getCompactDate(String strDt) {
        return java.sql.Date.valueOf(LocalDate.parse(strDt, COMPACT_DATE_FMTER));
    }

    /**
     * 返回给定串 按 HHmmss 的时间型
     *
     * @param strDt
     * @return
     */
    public static java.sql.Time getCompactTime(String strDt) {
        return java.sql.Time.valueOf(LocalTime.parse(strDt, COMPACT_TIME_FMTER));
    }

    /**
     * 返回给定串 按 yyyyMMddHHmmss 的日期时间型
     *
     * @param strDt
     * @return
     */
    public static Timestamp getCompactDateTime(String strDt) {
        return Timestamp.valueOf(LocalDateTime.parse(strDt, COMPACT_DATETIME_FMTER));
    }

    /**
     * LocalDateTime 转换成 java.util.Date
     *
     * @param dt
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime dt) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = dt.atZone(zone).toInstant();
        Date date = Date.from(instant);

        return date;
    }

    /**
     * java.util.Date to LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();

        return localDateTime;
    }

    /**
     * 返回当前时间的时间类型
     *
     * @return
     */
    public static java.sql.Time getTime() {
        return java.sql.Time.valueOf(LocalTime.now());
    }

    /**
     * 按给定秒数<br>
     * 返回当前时间的时间类型
     *
     * @return
     */
    public static java.sql.Time getTime(long time) {
        return new java.sql.Time(time);
    }

    /**
     * 返回当前时间的时间戳类型
     *
     * @return
     */
    public static Timestamp getDateTime() {
        return Timestamp.valueOf(LocalDateTime.now());
    }

    /**
     * 按给定秒数<br>
     * 返回当前时间的时间戳类型
     *
     * @return
     */
    public static Timestamp getDateTime(long time) {
        return new Timestamp(time);
    }

    /**
     * 按给定日期串和日期串格式，返回时间戳
     *
     * @param strDt 日期串
     * @param dtFmt 日期串格式
     * @return
     * @throws ParseException
     */
    public static Timestamp getDateTime(String strDt, String dtFmt) throws ParseException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dtFmt);
        return Timestamp.valueOf(LocalDateTime.parse(strDt, dateTimeFormatter));
    }

    /**
     * 给定日期，和日期格式，返回串
     *
     * @param dt        给定日期
     * @param fmtString 串格式
     * @return
     */
    public static String dateToString(Timestamp dt, String fmtString) {
        return new SimpleDateFormat(fmtString).format(dt);
    }

    /**
     * 给定日期，和日期格式，返回串
     *
     * @param dt        给定日期
     * @param fmtString 串格式
     * @return
     */
    public static String dateToString(java.sql.Date dt, String fmtString) {
        return new SimpleDateFormat(fmtString).format(dt);
    }

    /**
     * 给定日期，和日期格式，返回串
     *
     * @param dt        给定日期
     * @param fmtString 串格式
     * @return
     */
    public static String dateToString(java.util.Date dt, String fmtString) {
        return new SimpleDateFormat(fmtString).format(dt);
    }

    /**
     * 给定时间，和时间格式，返回串
     *
     * @param tm        给定时间
     * @param fmtString 串格式
     * @return
     */
    public static String dateToString(java.sql.Time tm, String fmtString) {
        return new SimpleDateFormat(fmtString).format(tm);
    }

    /**
     * 给定日期，和日期格式，返回串
     *
     * @param dt        给定日期
     * @param fmtString 串格式
     * @return
     */
    public static String utilDateToString(Date dt, String fmtString) {
        return new SimpleDateFormat(fmtString).format(dt);
    }

    /**
     * 给定日期类型，返回yyyy-MM-dd串
     *
     * @param dt
     * @return
     */
    public static String dateToString(java.sql.Date dt) {
        return new SimpleDateFormat(DATE_FMT).format(dt);
    }

    /**
     * 给定日期类型，返回yyyy年MM月dd日串
     *
     * @param dt
     * @return
     */
    public static String dateToStrCn(java.sql.Date dt) {
        return new SimpleDateFormat(DATE_FMT_CN).format(dt);
    }


    /**
     * 当前日期按给定格式，返回串
     *
     * @param fmtString 串格式
     * @return
     */
    public static String dateToString(String fmtString) {
        return new SimpleDateFormat(fmtString).format(new Date());
    }


    /**
     * 给定日期，返回yyyy-MM-dd串
     *
     * @param dt
     * @return
     */
    public static String dateToString(Date dt) {
        return new SimpleDateFormat(DATE_FMT).format(dt);
    }

    /**
     * 给定日期，返回日dd串
     *
     * @param dt
     * @return
     */
    public static String getCompactDay(Date dt) {
        return new SimpleDateFormat(DAY_FMT).format(dt);
    }

    /**
     * 给定时间，返回HH:mm:ss串
     *
     * @param tm
     * @return
     */
    public static String timeToString(java.sql.Time tm) {
        return new SimpleDateFormat(TIME_FMT).format(tm);
    }

    /**
     * 给定时间戳，返回yyyy-MM-dd HH:mm:ss串
     *
     * @param tms
     * @return
     */
    public static String datetimeToString(Timestamp tms) {
        return new SimpleDateFormat(DATETIME_FMT).format(tms);
    }

    /**
     * 返回本月第一天
     *
     * @return
     */
    public static java.sql.Date getTheMonthFirst() {
        LocalDate ld = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
//        Calendar c = Calendar.getInstance();
//        c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        return java.sql.Date.valueOf(ld);
    }

    /**
     * 返回给定日期当月第一天
     *
     * @param date
     * @return
     */
    public static java.sql.Date getTheMonthFirst(Date date) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天

        return new java.sql.Date(c.getTime().getTime());
    }

    /**
     * 返回前月第一天
     *
     * @return
     */
    public static java.sql.Date getLastMonthFirst() {

        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天

        return new java.sql.Date(c.getTime().getTime());
    }

    /**
     * 返回下月第一天
     *
     * @return
     */
    public static java.sql.Date getNextMonthFirst() {

        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 1);
        c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天

        return new java.sql.Date(c.getTime().getTime());
    }


    /**
     * 取开始和结束日期之间的天数
     *
     * @param dtBegin 开始日期
     * @param dtEnd   结束日期
     * @return
     */
    public static int dateSub(Date dtBegin, Date dtEnd) {

        Calendar cal = Calendar.getInstance();
        long miBegin = 0, miEnd = 0, rsMi = 0;

        cal.setTime(dtBegin);
        miBegin = cal.getTimeInMillis();

        cal.setTime(dtEnd);
        miEnd = cal.getTimeInMillis();

        rsMi = miEnd - miBegin;

        rsMi /= 1000 * 60 * 60 * 24;

        return (int) rsMi;

    }

    /**
     * 取开始和结束日期之间的天数
     *
     * @param dtBegin 开始日期
     * @param dtEnd   结束日期
     * @return
     */
    public static int dateSub(java.sql.Date dtBegin, java.sql.Date dtEnd) {
        return dateSub(new Date(dtBegin.getTime()), new Date(dtEnd.getTime()));
    }

    /**
     * 获取两个日期相差几个月
     *
     * @param start
     * @param end
     * @return
     */
    public static int monthSub(Date start, Date end) {

        if (start.after(end)) {
            Date t = start;
            start = end;
            end = t;
        }

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        Calendar temp = Calendar.getInstance();
        temp.setTime(end);
        temp.add(Calendar.DATE, 1);
        int year = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        int month = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
        if ((startCalendar.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month + 1;
        } else if ((startCalendar.get(Calendar.DATE) != 1) && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month;
        } else if ((startCalendar.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) != 1)) {
            return year * 12 + month;
        } else {
            return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
        }

    }

    /**
     * 获取两个日期相差几个月
     *
     * @param start
     * @param end
     * @return
     */
    public static int monthSub(java.sql.Date start, java.sql.Date end) {
        return monthSub(new Date(start.getTime()), new Date(end.getTime()));
    }

    /**
     * 返回 yyyy-MM-dd'T'HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String getISO8601Timestamp(Timestamp date) {
        // 不设置时区，否则是格林尼治时间,或者设置成东八区
        TimeZone tz = TimeZone.getTimeZone("GMT+8:00");// ("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        df.setTimeZone(tz);
        String nowAsISO = df.format(date);
        return nowAsISO;
    }

    /**
     * 给定日期月份第一天
     *
     * @param month
     * @return
     */
    public static Date getMonth(Date month) {

        Calendar ca = Calendar.getInstance();
        ca.setTime(month);
        ca.set(Calendar.DAY_OF_MONTH, 1);

        return ca.getTime();
    }

    /**
     * 返回当前月份
     *
     * @return
     */
    public static int getMonth() {
        return LocalDate.now().getMonthValue();
    }

    /**
     * 返回当前年份
     *
     * @return
     */
    public static int getYear() {
        return LocalDate.now().getYear();
    }

    /**
     * 给定日期月份下一个月第一天
     *
     * @param month
     * @param md
     * @return
     */
    public static Date addMonth(Date month, int md) {

        Calendar ca = Calendar.getInstance();
        ca.setTime(month);
        ca.set(Calendar.DAY_OF_MONTH, 1);
        ca.add(Calendar.MONTH, md);

        return ca.getTime();
    }

    /**
     * 给定日期月份第md个月
     *
     * @param month
     * @param md
     * @return
     */
    public static Date addNomalMonth(Date month, int md) {

        Calendar ca = Calendar.getInstance();
        ca.setTime(month);
        ca.add(Calendar.MONTH, md);

        return ca.getTime();
    }

    /**
     * 给定日期月份下一个月第一天
     *
     * @param month
     * @param md
     * @return
     */
    public static java.sql.Date addMonth(java.sql.Date month, int md) {

        Calendar ca = Calendar.getInstance();
        ca.setTime(month);
        ca.set(Calendar.DAY_OF_MONTH, 1);
        ca.add(Calendar.MONTH, md);

        return new java.sql.Date(ca.getTime().getTime());
    }

    /**
     * 给定日期月份第md个月
     *
     * @param month
     * @param md
     * @return
     */
    public static java.sql.Date addNomalMonth(java.sql.Date month, int md) {

        Calendar ca = Calendar.getInstance();
        ca.setTime(month);
        ca.add(Calendar.MONTH, md);

        return new java.sql.Date(ca.getTime().getTime());
    }

    /**
     * 返回给定年的所有月份
     *
     * @param year
     * @return
     */
    public static Date[] getMonthForYear(Date year) {

        final int MS_OF_YEAR = 12;

        Date[] mArr = new Date[MS_OF_YEAR];

        Calendar ca = Calendar.getInstance();
        ca.setTime(year);

        ca.set(Calendar.DAY_OF_YEAR, 1);
        for (int i = 0; i < MS_OF_YEAR; i++) {

            Date cm = ca.getTime();
            mArr[i] = cm;
            ca.add(Calendar.MONTH, 1);
        }

        return mArr;
    }

    /**
     * 根据给定生日，计算当前年龄，周岁
     *
     * @param brthDay
     * @return
     */
    public static int caculAge(Date brthDay) {

        Date theMonthFirst = new Date();
        int age = 0;

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(brthDay);
        c2.setTime(theMonthFirst);
        c2.set(Calendar.DATE, 1);

        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);

        age = year2 - year1;

        c1.add(Calendar.YEAR, age);

        if (c1.compareTo(c2) < 0) {
            age -= 1;
        }

        return age;
    }

    /**
     * 日期加 天数
     *
     * @param srcDate 天数
     * @return
     */
    public static Date dateAdd(Date srcDate, int step) {

        Calendar srcCal = Calendar.getInstance();
        srcCal.setTime(srcDate);
        srcCal.add(Calendar.DATE, step);

        return srcCal.getTime();
    }

    /**
     * 日期加 天数
     *
     * @param srcDate 天数
     * @return
     */
    public static java.sql.Date dateAdd(java.sql.Date srcDate, int step) {

        Calendar srcCal = Calendar.getInstance();
        srcCal.setTime(srcDate);
        srcCal.add(Calendar.DATE, step);

        return new java.sql.Date(srcCal.getTime().getTime());
    }

    /**
     * 时间戳加 分钟
     *
     * @param srcDatetime 分钟
     * @return
     */
    public static java.sql.Timestamp minuteAdd(java.sql.Timestamp srcDatetime, int step) {

        Calendar srcCal = Calendar.getInstance();
        srcCal.setTime(srcDatetime);
        srcCal.add(Calendar.MINUTE, step);

        return new java.sql.Timestamp(srcCal.getTime().getTime());
    }

    /**
     * @param @return    设定文件 
     * @return long    返回类型 
     */
    public static String getDateUnix(Date beginDate) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String t = df.format(beginDate);
        long epoch = 0L;
        try {
            epoch = df.parse(t).getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return epoch + "";
    }


    /**
     * 通过年龄计算生日（按当前时间）
     *
     * @param age
     * @return
     */
    public static java.sql.Date ageToBirth(int age) {

        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, -age);// 减去年龄对应的年份

        return new java.sql.Date(c.getTime().getTime());

    }

    /**
     * 通过年龄计算生日（按给定时间）
     *
     * @param age
     * @param dt
     * @return
     */
    public static java.sql.Date ageToBirth(int age, java.sql.Date dt) {

        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.YEAR, -age);// 减去年龄对应的年份

        return new java.sql.Date(c.getTime().getTime());

    }


    /**
     * 通过生日计算年龄（到当前时间）
     *
     * @param birth
     * @return
     */
    private static int caculAge(java.sql.Date birth, java.sql.Date utilDt) {

        // 当前时间
        Calendar cUtil = Calendar.getInstance();
        if (utilDt != null) {
            cUtil.setTime(utilDt);
        }
        // 生日时间
        Calendar cBirth = Calendar.getInstance();
        cBirth.setTime(birth);

        int age = cUtil.get(Calendar.YEAR) - cBirth.get(Calendar.YEAR);

        int birthM = cBirth.get(Calendar.MONTH);
        int currM = cUtil.get(Calendar.MONTH);

        int birthD = cBirth.get(Calendar.DATE);
        int currD = cUtil.get(Calendar.DATE);

        // 看看过没过生日，要是没过，还得减一天
        if ((currM < birthM) || (currM == birthM && currD < birthD)) {
            age--;
        }

        return age;

    }

    /**
     * 通过生日计算年龄（到当前时间）
     *
     * @param birth
     * @return
     */
    public static int birthToAge(java.sql.Date birth) {
        return caculAge(birth, null);
    }

    /**
     * 通过生日计算年龄（到给定时间）
     *
     * @param birth
     * @param utilDt
     * @return
     */
    public static int birthToAge(java.sql.Date birth, java.sql.Date utilDt) {
        return caculAge(birth, utilDt);
    }

}
