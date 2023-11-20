package org.cmms.modules.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description //TODO
 * @Date $ $
 * @Author huangwb
 **/
public class DateUtils {
    /**
     * 得到几天前的时间
     *
     * @param d   时间
     * @param day 几天
     * @return 结果
     */
    public static Date getDateBefore(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }

    /**
     * 得到几天后的时间
     *
     * @param d   时间
     * @param day 几天
     * @return 结果
     */
    public static Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    public static String getDateStr(String str) {
        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddhhmmss");
            sdf1.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
            Date date = (Date) sdf1.parse(str);
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdf2.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
            return date2TimeStamp(sdf2.format(date), "yyyy-MM-dd HH:mm:ss");
        } catch (Exception e) {

        }
        return null;
    }

    public static Date getCurrentEndTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * @return java.util.Date
     * @Author huangwb
     * @Description //TODO 当月第一天
     * @Date 2020/10/28 17:08
     * @Param []
     **/
    public static Date getMonthFirst() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // 本月起始
        Calendar thisMonthFirstDateCal = Calendar.getInstance();
        thisMonthFirstDateCal.set(Calendar.DAY_OF_MONTH, 1);
        return thisMonthFirstDateCal.getTime();
    }

    /**
     * @return java.util.Date
     * @Author huangwb
     * @Description //TODO 本月月末
     * @Date 2020/10/28 17:08
     * @Param []
     **/
    public static String getMonthEnd() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // 本月末尾
        Calendar thisMonthEndDateCal = Calendar.getInstance();
        thisMonthEndDateCal.set(Calendar.DAY_OF_MONTH, thisMonthEndDateCal.getActualMaximum(Calendar.DAY_OF_MONTH));
        String thisMonthEndTime = format.format(thisMonthEndDateCal.getTime()) + " 23:59:59";
        return thisMonthEndTime;
    }

    /**
     * @return int
     * @Author huangwb
     * @Description //TODO 获取传入时间和当前时间相差多少天
     * @Date 2020/10/28 17:13
     * @Param [time1]
     **/
    public static int afterDay(String time1) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = format.parse(time1);
            Date date2 = format.parse(format.format(new Date()));
            int a = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
            return a;
        } catch (Exception e) {

        }
        return 0;
    }

    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @return
     */
    public static String getPastDate(int past,String patten) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat(patten);
        String result = format.format(today);
        return result;
    }

    public static Date getCurrentStartTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获得当前时间格式yyyyMMddHHmmss
     *
     * @return
     */
    public static String getCurrentDateTime() {
        return getCurrentDate("yyyyMMddHHmmss");
    }

    /**
     * @return String 返回值为日期,格式自定义，需要符合标准，参考Java Doc “SimpleDateFormat”
     * @description 取得当前日期的String。
     */
    public static String getCurrentDate(String aFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(aFormat);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        String tDate = simpleDateFormat.format(new Date(
                System.currentTimeMillis()));

        return tDate;
    }

    public static Date getDate(String str) {
        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddhhmmss");
            sdf1.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
            Date date = (Date) sdf1.parse(str);
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdf2.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
            return stringToDate(sdf2.format(date), "yyyy-MM-dd HH:mm:ss");
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * @return java.lang.String
     * @Author huangwb
     * @Description //TODO 获取某天之后的时间
     * @Date 2019/11/28 13:51
     * @Param [d, day]
     **/
    public static String getTimeAfter(Date d, int day) {
        Date date = new Date();//获取当前时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //  calendar.add(Calendar.YEAR, -1);//当前时间减去一年，即一年前的时间
        calendar.add(Calendar.HOUR_OF_DAY, day);//当前时间前去一个月，即一个月前的时间
        //calendar.add(Calendar.MONTH, -1);//当前时间前去一个月，即一个月前的时间
        return dateToString(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
    }

    public static String getMinuteAfter(Date d, int minute) {
        Date date = new Date();//获取当前时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return dateToString(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * @return java.lang.String
     * @Author huangwb
     * @Description //TODO 获取几分钟后的时间
     * @Date 2019/12/15 14:58
     * @Param [d, minute, patten]
     **/
    public static String getMinuteAfter(Date d, int minute, String patten) {
        Date date = new Date();//获取当前时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return dateToString(calendar.getTime(), patten);
    }

    /**
     * 取得当前时间戳（精确到秒）
     */
    public static String getCurrTimeStamp() {
        long time = System.currentTimeMillis();
        String t = String.valueOf(time / 1000);
        return t;
    }


    /**
     * 日期格式字符串转换成时间戳
     *
     * @param date_str 字符串日期
     * @param format   如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2TimeStamp(String date_str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
            return String.valueOf(sdf.parse(date_str).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Date 转化为时间戳
     *
     * @param date 时间
     * @return
     */
    public static String dateTimeStamp(Date date) {
        return String.valueOf(date.getTime() / 1000);
    }

    /**
     * 将String转化为Date
     *
     * @param str    字符串
     * @param format 格式
     * @return 结果
     */
    public static Date stringToDate(String str, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);//小写的mm表示的是分钟
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    //将String转化为Date
    public static Date stringToDate(String str) {
        return stringToDate(str, "yyyy-MM-dd");
    }


    /**
     * 将Date转化为String
     *
     * @param date   时间
     * @param format 转化的格式
     * @return 结果
     */
    public static String dateToString(Date date, String format) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        String result = sdf.format(date);
        return result;
    }

    //将时间转化为 年-月-日 的格式
    public static String dateToString(Date date) {
        return dateToString(date, "yyyy-MM-dd");
    }

    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    public static String dateToTimeStr(String dateStr, String pattern) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
            Date date = simpleDateFormat.parse(dateStr);
            return String.valueOf(date.getTime());
        } catch (Exception e) {

        }
        return String.valueOf(System.currentTimeMillis());
    }

    public static Date dateToDate(String dateStr, String pattern) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
            Date date = simpleDateFormat.parse(dateStr);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Date();
    }

    /*
     * 将时间戳转换为时间
     */
    public static Date stampToDate(String stap) {
        long lt = new Long(stap);
        return new Date(lt);
    }

    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(Date date) {
        if (date == null) {
            return null;
        }
        long ts = date.getTime();
        return String.valueOf(ts);
    }


    /**
     * 获取当前的时间字符串
     *
     * @return
     */
    public static String getCurrentDateStr() {
        return new Date().toString();
    }

    /**
     * @return boolean
     * @Author huangwb
     * @Description //TODO 判断是否是相应分钟之内
     * @Date 2020/1/7 16:07
     * @Param [updateDate, minute]
     **/
    public static boolean time(Date updateDate, int minute) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        Calendar c3 = Calendar.getInstance();
        c1.setTime(updateDate);//要判断的日期
        c2.setTime(new Date());//初始日期
        c3.setTime(new Date());//也给初始日期 把分钟加五
        c3.add(Calendar.MINUTE, minute);
        c2.add(Calendar.MINUTE, -minute);//减去五分钟
        if (c1.after(c2) && c1.before(c3)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return boolean
     * @Author huangwb
     * @Description //TODO 判断是否是相应分钟之内
     * @Date 2020/1/7 16:07
     * @Param [updateDate, minute]
     **/
    public static boolean time(Date startDate, Date updateDate, int minute) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        Calendar c3 = Calendar.getInstance();
        c1.setTime(updateDate);//要判断的日期
        c2.setTime(startDate);//初始日期
        c3.setTime(startDate);//也给初始日期 把分钟加五
        c3.add(Calendar.MINUTE, minute);
        c2.add(Calendar.MINUTE, -minute);//减去五分钟
        if (c1.after(c2) && c1.before(c3)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @Author huangwb
     * @Description //TODO 获取当前时间的时间戳
     * @Date 2019/11/28 13:51
     * @Param []
     **/
    public static Map<String, Object> getCurrentDateSimple() {
        Calendar now = Calendar.getInstance();
        Map<String, Object> map = new HashMap<>();
        //年
        map.put("year", now.get(Calendar.YEAR));
        //月
        map.put("month", now.get(Calendar.MONTH) + 1);
        //日
        map.put("day", now.get(Calendar.DAY_OF_MONTH));
        //时
        map.put("hour", now.get(Calendar.HOUR_OF_DAY));
        //分
        map.put("minute", now.get(Calendar.MINUTE));
        //秒
        map.put("second", now.get(Calendar.SECOND));
        return map;
    }

    public static String getDateStrFormat(String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return simpleDateFormat.format(new Date());
    }

    /**
     * @return java.lang.String
     * @Author huangwb
     * @Description //TODO 获取昨日的时间
     * @Date 2020/3/24 10:44
     * @Param []
     **/
    public static String getYesterdayDataStr() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        Date time = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return simpleDateFormat.format(time);
    }

    /**
     * @return java.util.List<java.lang.String>
     * @Author huangwb
     * @Description //TODO 获取当前小时属于哪个时间区间
     * @Date 2020/4/16 11:19
     * @Param [times]
     **/
    public static List<String> getCurrentHourOfSection(List<String> times) {
        List<String> newTime = new ArrayList<>();
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        int house = instance.get(Calendar.HOUR_OF_DAY);
        times.stream().forEach(data -> {
            //时间格式 14:00-15:00
            String[] timeArray = data.split("-");
            //例如14:00 拿到的就是14
            String[] prefixTimeArray = timeArray[0].split(":");
            if (house < Integer.valueOf(prefixTimeArray[0])) {
                newTime.add(data);
            }
        });
        return newTime;
    }

    /**
     * @return java.util.List<java.lang.String>
     * @Author huangwb
     * @Description //TODO 获取当前小时属于哪个时间区间
     * @Date 2020/4/16 11:19
     * @Param [times]
     **/
    public static String getCurrentHourOfSection(String time) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        int house = instance.get(Calendar.HOUR_OF_DAY);
        //时间格式 14:00-15:00
        String[] timeArray = time.split("-");
        //例如14:00 拿到的就是14
        String[] prefixTimeArray = timeArray[0].split(":");
        if (house < Integer.valueOf(prefixTimeArray[0])) {
            return time;
        }
        return null;
    }

    /**
     * @return long
     * @Author huangwb
     * @Description //TODO 判断当前与指定时间相差多少分钟
     * @Date 2020/5/14 17:29
     * @Param [nowDate, format]
     **/
    public static long getDatePoor(String estimateDate, String format) {
        SimpleDateFormat sd = new SimpleDateFormat(format);
        sd.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long diff;
        long day;
        long min;
        // 获得两个时间的毫秒时间差异
        try {
            diff = sd.parse(estimateDate).getTime() - sd.parse(dateToString(new Date(), format)).getTime();
            day = diff / nd;// 计算差多少天
            min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟
            return min;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * @return java.lang.String
     * @Author huangwb
     * @Description //TODO 获取某月的最后一天
     * @Date 2020/8/28 11:42
     * @Param [year, month]
     **/
    public static String getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return sdf.format(cal.getTime());
    }

    /**
     * @return java.lang.String
     * @Author huangwb
     * @Description //TODO 获取某年某月的第一天
     * @Date 2020/8/28 11:42
     * @Param [year, month]
     **/
    public static String getFisrtDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return sdf.format(cal.getTime());
    }

    /**
     * @return java.util.Date
     * @Author huangwb
     * @Description //TODO 年初
     * @Date 2021/3/19 20:23
     * @Param []
     **/
    public static Date getYearFirst() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.clear(Calendar.MONTH);
        c.add(Calendar.YEAR, 0);
        /*c.add(Calendar.MONTH, 0);*/
        c.set(Calendar.DAY_OF_MONTH, 1);
        //设置为1号,当前日期既为本月第一天
        //c.set(Calendar.DAY_OF_MONTH, 1);
        //将小时至0
        c.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        c.set(Calendar.MINUTE, 0);
        //将秒至0
        c.set(Calendar.SECOND, 0);
        //将毫秒至0
        c.set(Calendar.MILLISECOND, 0);
        String first = format.format(c.getTime());
        return DateUtils.stringToDate(first);
    }

    /**
     * 获取过去或者未来 任意天内的日期数组
     *
     * @param intervals intervals天内
     * @return 日期数组
     */
    public static List<String> test(int intervals,String patten) {
        List<String> pastDaysList = new ArrayList<>();
        List<String> fetureDaysList = new ArrayList<>();
        for (int i = intervals; i > 0; i--) {
            pastDaysList.add(getPastDate(i-1,patten));
            fetureDaysList.add(getFetureDate(i-1,patten));
        }
        return pastDaysList;
    }

    /**
     * 获取未来 第 past 天的日期
     *
     * @param past
     * @return
     */
    public static String getFetureDate(int past,String patten) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat(patten);
        String result = format.format(today);
        return result;
    }


    /**
     * 获取多久后过生日
     * @param csrq
     * @return
     */
    public static int getTheBirthDayForLong(Date csrq) {

        String year = DateUtils.getYear(new Date());
        DateFormat format = new SimpleDateFormat("MMdd");
        String md = format.format(csrq);
        format = new SimpleDateFormat("yyyyMMdd");
        try {
            Date csrqDq = format.parse(year+md);
            Date now = format.parse(format.format(new Date()));
            double span = DateUtils.getDaySpan(now,csrqDq);
            if(span<0) {
                Calendar currCal=Calendar.getInstance();
                currCal.setTime(csrqDq);
                currCal.add(Calendar.YEAR, 1);
                span= DateUtils.getDaySpan(now,currCal.getTime());
            }
            return (int)span;
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
    }
    /**
     * 获取某天的年份
     * @param date
     * @return
     */
    public static String getYear(Date date){
        DateFormat format = new SimpleDateFormat("yyyy");
        return format.format(date);
    }
    /**
     * date1 间隔date2 date2-date1
     * @param date1
     * @param date2
     * @return
     */
    public static double getDaySpan(Date date1,Date date2) {
        long range = date2.getTime() - date1.getTime();
        double sec =  (double) (range/1000);//相隔秒数
        double min = sec/60;
        double hour = min/60;
        double day = hour/24;
        return day;
    }
}
