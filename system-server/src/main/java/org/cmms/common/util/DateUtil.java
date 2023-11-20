package org.cmms.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * User: Erakor
 * Date: 12-6-7
 */
public class DateUtil {
    public static final String DEFAULT_DATE_FORMAT_SHOW = "yyyy-MM-dd HH:mm:ss";
    public static final int TIME_SEC = 1000;
    public static final int TIME_MI = 60 * TIME_SEC;
    public static final int TIME_HOUR = 60 * TIME_MI;
    public static final long TIME_DAY = 24 * TIME_HOUR;
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String DEFAULT_TIME_FORMAT_SHOW = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DAY_FORMAT_SHOW = "yyyy-MM-dd";

    public static String currentDateTimeFileName() {
        return formatDateTime("yyyyMMddHHmmssSSS");
    }
    //private final static Logger log = LoggerFactory.getLogger(DateUtil.class);

    public final static long ONE_DAY_SECONDS = 86400;

    /*
     * private static DateFormat dateFormat = null; private static DateFormat
     * longDateFormat = null; private static DateFormat dateWebFormat = null;
     */
    public final static String shortFormat = "yyyyMMdd";
    public final static String longFormat = "yyyyMMddHHmmss";
    public final static String webFormat = "yyyy-MM-dd";
    public final static String webMonthFormat = "yyyy-MM";
    public final static String webHourFormat = "yyyy-MM-dd HH";
    public final static String timeFormat = "HHmmss";
    public final static String monthFormat = "yyyyMM";
    public final static String chineseDtFormat = "yyyy年MM月dd日";
    public final static String newFormat = "yyyy-MM-dd HH:mm:ss";
    public final static String noSecondFormat = "yyyy-MM-dd HH:mm";
    public final static long ONE_DAY_MILL_SECONDS = 86400000;
    public final static String[] formatList = new String[]{
            webFormat, shortFormat, longFormat, chineseDtFormat, webMonthFormat, webHourFormat, timeFormat, monthFormat, newFormat, noSecondFormat
    };


    public static Date string2Date(String strDate, String pattern) {
        if (strDate == null || strDate.equals("")) {
            throw new RuntimeException("str date null");
        }
        if (pattern == null || pattern.equals("")) {
            pattern = DateUtil.webFormat;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = null;

        try {
            date = sdf.parse(strDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }
    public static DateFormat getNewDateFormat(String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);

        df.setLenient(false);
        return df;
    }

    public static String format(Date date, String format) {
        if (date == null) {
            return null;
        }

        return new SimpleDateFormat(format).format(date);
    }

    public static Date parseAllDateFormat(String sData) {
        for (String f : formatList) {
            try {
                return parseDateNoTime(sData, f);
            } catch (Throwable t) {

            }
        }
        return null;
    }

    public static Date parseDateNoTime(String sDate) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(shortFormat);

        if ((sDate == null) || (sDate.length() < shortFormat.length())) {
            throw new ParseException("length too little", 0);
        }

        if (!StringUtils.isNumeric(sDate)) {
            throw new ParseException("not all digit", 0);
        }

        return dateFormat.parse(sDate);
    }

    public static Date parseDateNoTime(String sDate, String format) throws ParseException {
        if (StringUtils.isBlank(format)) {
            throw new ParseException("Null format. ", 0);
        }

        DateFormat dateFormat = new SimpleDateFormat(format);

        if ((sDate == null) || (sDate.length() < format.length())) {
            throw new ParseException("length too little", 0);
        }

        return dateFormat.parse(sDate);
    }

    public static Date parseDateNoTimeWithDelimit(String sDate, String delimit)
            throws ParseException {
        sDate = sDate.replaceAll(delimit, "");

        DateFormat dateFormat = new SimpleDateFormat(shortFormat);

        if ((sDate == null) || (sDate.length() != shortFormat.length())) {
            throw new ParseException("length not match", 0);
        }

        return dateFormat.parse(sDate);
    }

    public static Date parseDateLongFormat(String sDate) {
        DateFormat dateFormat = new SimpleDateFormat(longFormat);
        Date d = null;

        if ((sDate != null) && (sDate.length() == longFormat.length())) {
            try {
                d = dateFormat.parse(sDate);
            } catch (ParseException ex) {
                return null;
            }
        }

        return d;
    }

    public static Date parseDateNewFormat(String sDate) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(newFormat);
        Date d = dateFormat.parse(sDate);
        return d;
    }

    /**
     * 计算当前时间几小时之后的时间
     *
     * @param date
     * @param hours
     * @return
     */
    public static Date addHours(Date date, long hours) {
        return addMinutes(date, hours * 60);
    }

    /**
     * 计算当前时间几分钟之后的时间
     *
     * @param date
     * @param minutes
     * @return
     */
    public static Date addMinutes(Date date, long minutes) {
        return addSeconds(date, minutes * 60);
    }

    /**
     * @param date1
     * @param secs
     * @return
     */

    public static Date addSeconds(Date date1, long secs) {
        return new Date(date1.getTime() + (secs * 1000));
    }

    /**
     * 判断输入的字符串是否为合法的小时
     *
     * @param hourStr
     * @return true/false
     */
    public static boolean isValidHour(String hourStr) {
        if (!StringUtils.isEmpty(hourStr) && StringUtils.isNumeric(hourStr)) {
            int hour = new Integer(hourStr).intValue();

            if ((hour >= 0) && (hour <= 23)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 判断输入的字符串是否为合法的分或秒
     *
     * @param str minuteStr
     * @return true/false
     */
    public static boolean isValidMinuteOrSecond(String str) {
        if (!StringUtils.isEmpty(str) && StringUtils.isNumeric(str)) {
            int hour = new Integer(str).intValue();

            if ((hour >= 0) && (hour <= 59)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 取得新的日期
     *
     * @param date1 日期
     * @param days  天数
     * @return 新的日期
     */
    public static Date addDays(Date date1, long days) {
        return addSeconds(date1, days * ONE_DAY_SECONDS);
    }

    public static String getTomorrowDateString(String sDate) throws ParseException {
        Date aDate = parseDateNoTime(sDate);

        aDate = addSeconds(aDate, ONE_DAY_SECONDS);

        return getDateString(aDate);
    }

    public static String getTomorrowWebDate(String sDate) throws ParseException {
        String tomorrowShortDate = getTomorrowDateString(convertWeb2ShortFormat(sDate));

        return convert2WebFormat(tomorrowShortDate);
    }

    public static String getLongDateString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(longFormat);

        return getDateString(date, dateFormat);
    }

    public static String getNewFormatDateString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(newFormat);
        return getDateString(date, dateFormat);
    }

    public static String getDateString(Date date, DateFormat dateFormat) {
        if (date == null || dateFormat == null) {
            return null;
        }

        return dateFormat.format(date);
    }

    public static String getYesterDayDateString(String sDate) throws ParseException {
        Date aDate = parseDateNoTime(sDate);

        aDate = addSeconds(aDate, -ONE_DAY_SECONDS);

        return getDateString(aDate);
    }

    /**
     * @return 当天的时间格式化为"yyyyMMdd"
     */
    public static String getDateString(Date date) {
        DateFormat df = getNewDateFormat(shortFormat);

        return df.format(date);
    }

    public static String getWebDateString(Date date) {
        DateFormat dateFormat = getNewDateFormat(webFormat);

        return getDateString(date, dateFormat);
    }

    public static String getNoSecondDateString(Date date) {
        DateFormat dateFormat = getNewDateFormat(noSecondFormat);

        return getDateString(date, dateFormat);
    }

    /**
     * 取得“X年X月X日”的日期格式
     *
     * @param date
     * @return
     */
    public static String getChineseDateString(Date date) {
        DateFormat dateFormat = getNewDateFormat(chineseDtFormat);

        return getDateString(date, dateFormat);
    }

    public static String getTodayString() {
        DateFormat dateFormat = getNewDateFormat(shortFormat);

        return getDateString(new Date(), dateFormat);
    }

    public static String getTimeString(Date date) {
        DateFormat dateFormat = getNewDateFormat(timeFormat);

        return getDateString(date, dateFormat);
    }

    public static String getBeforeDayString(int days) {
        Date date = new Date(System.currentTimeMillis() - (ONE_DAY_MILL_SECONDS * days));
        DateFormat dateFormat = getNewDateFormat(shortFormat);

        return getDateString(date, dateFormat);
    }

    /**
     * 取得两个日期间隔秒数（日期1-日期2）
     *
     * @param one 日期1
     * @param two 日期2
     * @return 间隔秒数
     */
    public static long getDiffSeconds(Date one, Date two) {
        Calendar sysDate = new GregorianCalendar();

        sysDate.setTime(one);

        Calendar failDate = new GregorianCalendar();

        failDate.setTime(two);
        return (sysDate.getTimeInMillis() - failDate.getTimeInMillis()) / 1000;
    }

    public static long getDiffMinutes(Date one, Date two) {
        Calendar sysDate = new GregorianCalendar();

        sysDate.setTime(one);

        Calendar failDate = new GregorianCalendar();

        failDate.setTime(two);
        return (sysDate.getTimeInMillis() - failDate.getTimeInMillis()) / (60 * 1000);
    }

    /**
     * 取得两个日期的间隔天数
     *
     * @param one
     * @param two
     * @return 间隔天数
     */
    public static long getDiffDays(Date one, Date two) {
        Calendar sysDate = new GregorianCalendar();

        sysDate.setTime(one);

        Calendar failDate = new GregorianCalendar();

        failDate.setTime(two);
        return (sysDate.getTimeInMillis() - failDate.getTimeInMillis()) / (24 * 60 * 60 * 1000);
    }

    public static String getBeforeDayString(String dateString, int days) {
        Date date;
        DateFormat df = getNewDateFormat(shortFormat);

        try {
            date = df.parse(dateString);
        } catch (ParseException e) {
            date = new Date();
        }

        date = new Date(date.getTime() - (ONE_DAY_MILL_SECONDS * days));

        return df.format(date);
    }

    public static boolean isValidShortDateFormat(String strDate) {
        if (strDate.length() != shortFormat.length()) {
            return false;
        }

        try {
            Integer.parseInt(strDate); //---- 避免日期中输入非数字 ----
        } catch (Exception NumberFormatException) {
            return false;
        }

        DateFormat df = getNewDateFormat(shortFormat);

        try {
            df.parse(strDate);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    public static boolean isValidShortDateFormat(String strDate, String delimiter) {
        String temp = strDate.replaceAll(delimiter, "");

        return isValidShortDateFormat(temp);
    }


    public static long  computerTime(String updateTime,String createTime)
    {
        Date date = new Date();
        String endDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        if(org.cmms.common.util.StringUtils.isNotEmpty(updateTime)) {
            Temporal temporal = LocalDate.parse(updateTime);
            Temporal temporal2 = LocalDate.parse(endDate);
            long ll = ChronoUnit.MONTHS.between(temporal, temporal2);
            return ll;
        }else {
            Temporal temporal = LocalDate.parse(createTime);
            Temporal temporal2 = LocalDate.parse(endDate);
            long ll = ChronoUnit.MONTHS.between(temporal, temporal2);
            return ll;
        }
    }

    /**
     * 判断表示时间的字符是否为符合yyyyMMddHHmmss格式
     *
     * @param strDate
     * @return
     */
    public static boolean isValidLongDateFormat(String strDate) {
        if (strDate.length() != longFormat.length()) {
            return false;
        }

        try {
            Long.parseLong(strDate); //---- 避免日期中输入非数字 ----
        } catch (Exception NumberFormatException) {
            return false;
        }

        DateFormat df = getNewDateFormat(longFormat);

        try {
            df.parse(strDate);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    /**
     * 判断表示时间的字符是否为符合yyyyMMddHHmmss格式
     *
     * @param strDate
     * @param delimiter
     * @return
     */
    public static boolean isValidLongDateFormat(String strDate, String delimiter) {
        String temp = strDate.replaceAll(delimiter, "");

        return isValidLongDateFormat(temp);
    }

    public static String getShortDateString(String strDate) {
        return getShortDateString(strDate, "-|/");
    }

    public static String getShortDateString(String strDate, String delimiter) {
        if (StringUtils.isBlank(strDate)) {
            return null;
        }

        String temp = strDate.replaceAll(delimiter, "");

        if (isValidShortDateFormat(temp)) {
            return temp;
        }

        return null;
    }

    public static String getShortFirstDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        Date dt = new Date();

        cal.setTime(dt);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        DateFormat df = getNewDateFormat(shortFormat);

        return df.format(cal.getTime());
    }

    public static String getWebTodayString() {
        DateFormat df = getNewDateFormat(webFormat);

        return df.format(new Date());
    }

    public static String getWebFirstDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        Date dt = new Date();

        cal.setTime(dt);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        DateFormat df = getNewDateFormat(webFormat);

        return df.format(cal.getTime());
    }

    public static String convert(String dateString, DateFormat formatIn, DateFormat formatOut) {
        try {
            Date date = formatIn.parse(dateString);

            return formatOut.format(date);
        } catch (ParseException e) {
            //log.warn("convert() --- orign date error: " + dateString);
            return "";
        }
    }

    public static String convertWeb2ShortFormat(String dateString) {
        DateFormat df1 = getNewDateFormat(webFormat);
        DateFormat df2 = getNewDateFormat(shortFormat);

        return convert(dateString, df1, df2);
    }

    public static String convert2WebFormat(String dateString) {
        dateString = StringUtils.defaultString(dateString);
        DateFormat df1 = getNewDateFormat(shortFormat);
        DateFormat df2 = getNewDateFormat(webFormat);

        return convert(dateString, df1, df2);
    }

    public static String convert2ChineseDtFormat(String dateString) {
        DateFormat df1 = getNewDateFormat(shortFormat);
        DateFormat df2 = getNewDateFormat(chineseDtFormat);

        return convert(dateString, df1, df2);
    }

    public static String convertFromWebFormat(String dateString) {
        DateFormat df1 = getNewDateFormat(shortFormat);
        DateFormat df2 = getNewDateFormat(webFormat);

        return convert(dateString, df2, df1);
    }

    public static boolean webDateNotLessThan(String date1, String date2) {
        DateFormat df = getNewDateFormat(webFormat);

        return dateNotLessThan(date1, date2, df);
    }

    /**
     * @param date1
     * @param date2
     * @param format dateWebFormat2
     * @return
     */
    public static boolean dateNotLessThan(String date1, String date2, DateFormat format) {
        try {
            Date d1 = format.parse(date1);
            Date d2 = format.parse(date2);

            if (d1.before(d2)) {
                return false;
            } else {
                return true;
            }
        } catch (ParseException e) {
            // log.debug("dateNotLessThan() --- ParseException(" + date1 + ", " + date2 + ")");
            return false;
        }
    }

    public static String getEmailDate(Date today) {
        String todayStr;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");

        todayStr = sdf.format(today);
        return todayStr;
    }

    public static String getSmsDate(Date today) {
        String todayStr;
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日HH:mm");

        todayStr = sdf.format(today);
        return todayStr;
    }

    public static String formatTimeRange(Date startDate, Date endDate, String format) {
        if ((endDate == null) || (startDate == null)) {
            return null;
        }

        String rt = null;
        long range = endDate.getTime() - startDate.getTime();
        long day = range / DateUtils.MILLIS_PER_DAY;
        long hour = (range % DateUtils.MILLIS_PER_DAY) / DateUtils.MILLIS_PER_HOUR;
        long minute = (range % DateUtils.MILLIS_PER_HOUR) / DateUtils.MILLIS_PER_MINUTE;

        if (range < 0) {
            day = 0;
            hour = 0;
            minute = 0;
        }

        rt = format.replaceAll("dd", String.valueOf(day));
        rt = rt.replaceAll("hh", String.valueOf(hour));
        rt = rt.replaceAll("mm", String.valueOf(minute));

        return rt;
    }

    public static String formatMonth(Date date) {
        if (date == null) {
            return null;
        }

        return new SimpleDateFormat(monthFormat).format(date);
    }

    /**
     * 获取系统日期的前一天日期，返回Date
     *
     * @return
     */
    public static Date getBeforeDate() {
        Date date = new Date();

        return new Date(date.getTime() - (ONE_DAY_MILL_SECONDS));
    }

    /**
     * 获得指定时间当天起点时间
     *
     * @param date
     * @return
     */
    public static Date getDayBegin(Date date) {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        df.setLenient(false);

        String dateString = df.format(date);

        try {
            return df.parse(dateString);
        } catch (ParseException e) {
            return date;
        }
    }

    /**
     * 获得指定时间当天最末时间
     *
     * @param date
     * @return
     */
    public static Date getDayEnd(Date date) {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        df.setLenient(false);

        String dateString = df.format(date);
        dateString += "235959";

        try {
            return parseDateLongFormat(dateString);
        } catch (Exception e) {
            return date;
        }
    }

    /**
     * 判断参date上min分钟后，是否小于当前时间
     *
     * @param date
     * @param min
     * @return
     */
    public static boolean dateLessThanNowAddMin(Date date, long min) {
        return addMinutes(date, min).before(new Date());

    }

    /**
     * 是否在现在时间之前
     *
     * @param date
     * @return
     */
    public static boolean isBeforeNow(Date date) {
        if (date == null)
            return false;
        return date.compareTo(new Date()) < 0;
    }

    /**
     * 是否有效
     *
     * @param requestTime 请求时间
     * @param effectTime  生效时间
     * @param expiredTime 失效时间
     * @return
     */
    public static boolean isValidate(Date requestTime, Date effectTime, Date expiredTime) {
        if (requestTime == null || effectTime == null || expiredTime == null) {
            return false;
        }
        return effectTime.compareTo(requestTime) <= 0 && expiredTime.compareTo(requestTime) >= 0;
    }


    public static Date parseNoSecondFormat(String sDate) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(noSecondFormat);

        if ((sDate == null) || (sDate.length() < noSecondFormat.length())) {
            throw new ParseException("length too little", 0);
        }

        if (!StringUtils.isNumeric(sDate)) {
            throw new ParseException("not all digit", 0);
        }

        return dateFormat.parse(sDate);
    }

    /**
     * 判断两个日期是否是同一天
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDay(Date date1, Date date2) {
        SimpleDateFormat sdf = new SimpleDateFormat(webFormat);
        return sdf.format(date1).equals(sdf.format(date2));
    }

    /**
     * 获取系统当前时间
     *
     * @return
     */
    public static Date getCurrentTS() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        return date;
    }

    /**
     * 获取系统当前时间
     *
     * @return
     */
    public static Date getWeekBefore() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        return date;
    }

    /**
     * 获取当前时间的前几天或者后几天的日期
     *
     * @param days
     * @return
     */
    public static Date getDateNearCurrent(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, days);
        Date date = calendar.getTime();
        return date;

    }

    /**
     * 某一个月第一天
     *
     * @param date months 前后第几个月
     * @return
     * @throws ParseException
     */
    public static Date getFirstday_Month(Date date, int months) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(webFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, months);
        Date theDate = calendar.getTime();

        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = df.format(gcLast.getTime());
        StringBuffer str = new StringBuffer().append(day_first).append(" 00:00:00");
        day_first = str.toString();
        return parseDateNewFormat(day_first);
    }

    /**
     * 某一个月最后一天
     *
     * @param date months 前后第几个月
     * @return
     * @throws ParseException
     */
    public static Date getLastday_Month(Date date, int months) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, months);

        //上个月最后一天
        calendar.add(Calendar.MONTH, 1);    //加一个月
        calendar.set(Calendar.DATE, 1);        //设置为该月第一天
        calendar.add(Calendar.DATE, -1);    //再减一天即为上个月最后一天
        String day_last = df.format(calendar.getTime());
        StringBuffer endStr = new StringBuffer().append(day_last).append(" 23:59:59");
        day_last = endStr.toString();
        return parseDateNewFormat(day_last);
    }
    /**
     * 某一个月最后一天
     *
     * @param date months 前后第几个月
     * @return
     * @throws ParseException
     */
    public static Date getLastDay(Date date) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //上个月最后一天
        calendar.add(Calendar.MONTH, 1);    //加一个月
        calendar.set(Calendar.DATE, 1);        //设置为该月第一天
        calendar.add(Calendar.DATE, -1);    //再减一天即为上个月最后一天
        String day_last = df.format(calendar.getTime());
        return parseDateFormat(day_last,"yyyy-MM-dd");
    }

    /**
     * 某一年第一个月
     *
     * @param date years 前后第几年
     * @return
     * @throws ParseException
     */
    public static Date getFirstMonth_Year(Date date, int years) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(webFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, years);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = df.format(calendar.getTime());
        StringBuffer str = new StringBuffer().append(day_first).append(" 00:00:00");
        day_first = str.toString();
        return parseDateNewFormat(day_first);
    }

    /**
     * 某一个年最后一月
     *
     * @param date years 前后第几年
     * @return
     * @throws ParseException
     */
    public static Date getLastMonth_Year(Date date, int years) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, years);
        calendar.set(Calendar.MONTH, 11);
        calendar.add(Calendar.MONTH, 1);    //加一个月
        calendar.set(Calendar.DATE, 1);        //设置为该月第一天
        calendar.add(Calendar.DATE, -1);    //再减一天即为上个月最后一天
        String day_last = df.format(calendar.getTime());
        StringBuffer endStr = new StringBuffer().append(day_last).append(" 23:59:59");
        day_last = endStr.toString();
        return parseDateNewFormat(day_last);
    }

    public enum TypeTime {
        TIME_SEC(DateUtil.TIME_SEC),
        TIME_MI(DateUtil.TIME_MI),
        TIME_HOUR(DateUtil.TIME_HOUR),
        TIME_DAY(DateUtil.TIME_DAY),;
        public final long time;

        TypeTime(long time) {
            this.time = time;
        }

        public static TypeTime valueOf(long time) {
            for (TypeTime tt : values()) {
                if (time == tt.time) return tt;
            }
            return null;
        }
    }


    /**
     * 计算指定时间间隔的下一个时间点
     *
     * @param now      当前时间
     * @param split    时间间隔
     * @param typeTime 时间单位
     * @return 下一次时间点值
     */
    public static long cellNextTime(long now, int split, TypeTime typeTime) {
        int mod = (int) ((now / typeTime.time) % split);
        return ((now / typeTime.time) + (split - mod)) * typeTime.time;
    }

    public static Date parseDateFormat(String sDate, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date d = null;

        if ((sDate != null) && (sDate.length() == format.length())) {
            try {
                d = dateFormat.parse(sDate);
            } catch (ParseException ex) {
                ex.printStackTrace();
                return null;
            }
        }

        return d;
    }

    /**
     * 计算指定时间间隔的当前的时间点
     *
     * @param now      当前时间
     * @param split    时间间隔
     * @param typeTime 时间单位
     * @return 下一次时间点值
     */
    public static long cellNowTime(long now, int split, TypeTime typeTime) {
        int mod = (int) ((now / typeTime.time) % split);
        return (((now - (split * typeTime.time)) / typeTime.time) + (split - mod)) * typeTime.time;
    }

//    public static void main(String[] args) throws Exception {
//
//        while (true) {
//            long t = System.currentTimeMillis();
//            System.out.println(formatDateTime(t) + ">>" + formatDateTime(cellNowTime(t, 1, TypeTime.TIME_MI)) + ">>" + formatDateTime(cellNextTime(t, 1, TypeTime.TIME_MI)));
//            Thread.sleep(2345);
//        }
//    }

    /**
     * 计算指定时间间隔的下一个时间点
     * 单位为秒
     *
     * @param now   当前时间
     * @param split 时间间隔
     * @return 下一次时间点值
     */
    public static long cellNextTimeSec(long now, int split) {
        return cellNextTime(now, split, TypeTime.TIME_SEC);
    }

    public static String currentDateTime() {
        return formatDateTime("yyyy-MM-dd HH:mm:ss.SSS");
    }

    /**
     * 格式化n月之前的时间
     *
     * @param month
     * @param patter
     * @return
     */
    public static String formatFrontMonthDateTime(int month, String patter) {
        return formatDateTime(patter, getFrontMonthTime(month));
    }

    public static String formatFrontDayDateTime(int day, String patter) {
        return formatDateTime(patter, getFrontDayTime(day));
    }
    public static String formatDateTime(String patter) {
        return formatDateTime(patter, System.currentTimeMillis());
    }
public static String formatDateTime(String patter, Date d){
        if(d==null)return "";
        return formatDateTime(patter,d.getTime());
    }
    public static String formatDateTime(String patter, long time) {
        if (patter == null || patter.trim().equals("")) return "";
        Date d = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat(patter);
        return format.format(d);
    }

    public static String formatDateTime(long time) {
        if (time == 0) return "";
        return formatDateTime(DEFAULT_DATE_FORMAT, time);
    }  public static String formatDateTime(Date d) {
        if (d == null) return "";
        return formatDateTime(DEFAULT_DATE_FORMAT, d.getTime());
    }
    /**
     * 给现有日期加减月份
     *
     * @param date months 加减的月份
     * @return
     * @throws ParseException
     */
    public static Date addMonth(Date date, int months) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, months);
        return parseDateFormat(formatDateTime(shortFormat,calendar.getTime().getTime()),shortFormat) ;
    }
    /**
     * 上一个月最后一天
     *
     * @param
     * @return
     * @throws ParseException
     */
    public static String getLastDayString(String date) throws ParseException {
        Date d = parseDateFormat(date, shortFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        //上个月最后一天
        calendar.add(Calendar.MONTH, 1);    //加一个月
        calendar.set(Calendar.DATE, 1);        //设置为该月第一天
        calendar.add(Calendar.DATE, -1);    //再减一天即为上个月最后一天
        return formatDateTime(shortFormat,calendar.getTime().getTime());
    }
    /**
     * 给现有日期加减天数
     *
     * @param date day 天数
     * @return
     * @throws ParseException
     */
    public static String addDay(Date date, int day) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return formatDateTime(shortFormat,calendar.getTime().getTime());
    }
    /**
     * 给现有日期加减天数
     *
     * @param date day 天数
     * @return
     * @throws ParseException
     */
    public static String addDayOther(Date date, int day,String patter) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        if (StringUtils.isBlank(patter)){
            patter=shortFormat;
        }
        return formatDateTime(patter,calendar.getTime().getTime());
    }
    /**
     * 获取前n天的时间
     *
     * @param day
     * @return
     */
    public static long getFrontDayTime(int day) {
        long time = System.currentTimeMillis();
        return time - day * TIME_DAY;
    }

    /**
     * 获取前n月的时间
     *
     * @param month
     * @return
     */
    public static long getFrontMonthTime(int month) {
        long time = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - month);
        return calendar.getTime().getTime();
    }
   /**
     * 获取前n月的时间
     *
     * @param month
     * @return
     */
    public static long getFrontMonthTime(long time,int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - month);
        return calendar.getTime().getTime();
    }

    /**
     * 获取前n月的时间
     *
     * @param year
     * @return
     */
    public static long getFrontYearTime(long time,int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - year);
        return calendar.getTime().getTime();
    }

    /**
     * 取传入时间月的第一天
     *
     * @param date
     * @return
     */
    public static Date getMonthBeginDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        calendar1.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
        calendar1.set(Calendar.DAY_OF_MONTH, 1);
        calendar1.set(Calendar.HOUR, 0);
        calendar1.set(Calendar.MINUTE, 0);
        calendar1.set(Calendar.SECOND, 0);
        calendar1.set(Calendar.MILLISECOND, 0);
        return parseDateFormat(formatDateTime("yyyy-MM-dd", calendar1.getTimeInMillis()), "yyyy-MM-dd");
    }

    public static Date getQBeginDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        int m = calendar.get(Calendar.MONTH);
        int mq = monthq[m];
        calendar1.set(Calendar.MONTH, mq - 1);
        calendar1.set(Calendar.DAY_OF_MONTH, 1);
        calendar1.set(Calendar.HOUR, 0);
        calendar1.set(Calendar.MINUTE, 0);
        calendar1.set(Calendar.SECOND, 0);
        calendar1.set(Calendar.MILLISECOND, 0);
        return parseDateFormat(formatDateTime("yyyy-MM-dd", calendar1.getTimeInMillis()), "yyyy-MM-dd");
    }

    public static Date getYBeginDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        calendar1.set(Calendar.MONTH, 0);
        calendar1.set(Calendar.DAY_OF_MONTH, 1);
        calendar1.set(Calendar.HOUR, 0);
        calendar1.set(Calendar.MINUTE, 0);
        calendar1.set(Calendar.SECOND, 0);
        calendar1.set(Calendar.MILLISECOND, 0);
        return parseDateFormat(formatDateTime("yyyy-MM-dd", calendar1.getTimeInMillis()), "yyyy-MM-dd");
    }

    private static String monthStr(int month) {
        if (month < 10) return "0" + month;
        return String.valueOf(month);
    }

    /**
     * * 取传入时间月的最后一天
     *
     * @param date
     * @return
     */
    public static Date getMonthEndDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        calendar1.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
        calendar1.set(Calendar.DAY_OF_MONTH, 1);
        calendar1.set(Calendar.HOUR, 0);
        calendar1.set(Calendar.MINUTE, 0);
        calendar1.set(Calendar.SECOND, 0);
        calendar1.set(Calendar.MILLISECOND, 0);
        calendar1.set(Calendar.DAY_OF_MONTH, monthday[calendar.get(Calendar.MONTH) + 1]);
        return parseDateFormat(formatDateTime("yyyy-MM-dd", calendar1.getTimeInMillis()), "yyyy-MM-dd");
    }

    public static Date getQEndDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        int m = calendar.get(Calendar.MONTH);
        calendar1.set(Calendar.MONTH, monthqe[m] - 1);
        calendar1.set(Calendar.DAY_OF_MONTH, 1);
        calendar1.set(Calendar.HOUR, 0);
        calendar1.set(Calendar.MINUTE, 0);
        calendar1.set(Calendar.SECOND, 0);
        calendar1.set(Calendar.MILLISECOND, 0);
        calendar1.set(Calendar.DAY_OF_MONTH, monthday[monthqe[m]]);
        return parseDateFormat(formatDateTime("yyyy-MM-dd", calendar1.getTimeInMillis()), "yyyy-MM-dd");
    }

    public static Date getYEndDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        calendar1.set(Calendar.MONTH, 11);
        calendar1.set(Calendar.DAY_OF_MONTH, 31);
        calendar1.set(Calendar.HOUR, 0);
        calendar1.set(Calendar.MINUTE, 0);
        calendar1.set(Calendar.SECOND, 0);
        calendar1.set(Calendar.MILLISECOND, 0);
        return parseDateFormat(formatDateTime("yyyy-MM-dd", calendar1.getTimeInMillis()), "yyyy-MM-dd");
    }

    static int monthday[] = new int[]{
            31,//0
            31,//1
            28,//2
            31,//3
            30,//4
            31,//5
            30,//6
            31,//7
            31,//8
            30,//9
            31,//10
            30,//11
            31,//112
    };
    static int monthq[] = new int[]{
            1,//1
            1,//2
            1,//3
            4,//4
            4,//5
            4,//6
            7,//7
            7,//8
            7,//9
            10,//10
            10,//11
            10,//12
    };
    static int monthqe[] = new int[]{
            3,//1
            3,//2
            3,//3
            6,//4
            6,//5
            6,//6
            9,//7
            9,//8
            9,//9
            12,//10
            12,//11
            12,//12
    };


    public static void main(String[] args) {

        try{
            System.out.println(getMonthBeginDay(new Timestamp(System.currentTimeMillis())));
           // System.out.println(getYEndDay(parseAllDateFormat(DateUtil.formatDateTime("yyyy"))));

            //getYEndDay
//            Date d = getMonthEndDay(getFirstday_Month(parseDateNoTime("20160801"), -1));
//            System.out.println(DateUtil.formatDateTime("yyyyMMdd", d.getTime()));
        }catch (Throwable te){
            te.printStackTrace();
        }

    }
}
