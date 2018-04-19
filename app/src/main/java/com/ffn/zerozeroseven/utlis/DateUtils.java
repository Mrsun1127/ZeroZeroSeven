package com.ffn.zerozeroseven.utlis;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期工具类
 *
 */
public class DateUtils {
	
	public static Date date = null;
	
	public static DateFormat dateFormat = null;

	public static Calendar calendar = null;
	public static String FORMATE_YEAR_MONTH="yyyyMM";
	/**
	 * 没有分隔符简写 如：20131201
	 */
	public static String FORMATE_SHORT_MINI="yyyyMMdd";
	public static String FORMATE_MM_DD="MM.dd";
	/**
	 * 没有分隔符简写 如：20131201231530
	 */
	public static String FORMATE_LONG_MINI="yyyyMMddHHmmss";
	public static String FORMATE_LONG_MIDDLE = "yyyyMMddHHmm";
	
	/**
	 * 英文简写（默认）如：2013-12-01
	 */
	public static String FORMAT_SHORT = "yyyy-MM-dd";
	
	/**
	 * 英文全称 如：2012-12-01 23:15:06
	 */
	public static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 英文全称 如：2012-12-01 23:15
	 */
	public static String FORMAT_MIDDLE = "yyyy-MM-dd HH:mm";
	/**
	 * 精确到毫秒的完整时间 如：yyyy-MM-dd HH:mm:ss.S
	 */
	public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";

	/**
	 * 中文简写 如：2012年12月01日
	 */
	public static String FORMAT_SHORT_CN = "yyyy年MM月dd日";

	/**
	 * 中文全称 如：2012年12月01日 23时15分06秒
	 */
	public static String FORMAT_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";
	/**
	 * 中文全称 如: 2012年12月01日 23时15分
	 */
	public static String FORMAT_MIDDLE_CN = "yyyy年MM月dd日 HH时mm分";
	/**
	 * 精确到毫秒的完整中文时间
	 */
	public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";
	
	public static String FORMAT_UTC = "yyyy-MM-dd'T'HH:mm:ss'Z'";	
	public static String FORMAT_SHORT_TIME = "HH:mm";
	public static String FORMAT_MONTH_DAY = "MM月dd日 HH:mm";
	public static String FORMAT_NUMBER_DATE = "MM.dd HH:mm";
	
	/**
	 * 英文全称 如：2012-12-01 23:15
	 */
	public static String FORMAT_MIDDLE_HH = "yyyy-MM-dd HH";
	/**
	 * 获得默认的 date pattern
	 */
	public static String getDatePattern() {
		return FORMAT_LONG;
	}

	/**
	 * 根据预设格式返回当前日期
	 * 
	 * @return
	 */
	public static String getNow() {
		return format(new Date());
	}

	/**
	 * 根据用户格式返回当前日期
	 * 
	 * @param format
	 * @return
	 */
	public static String getNow(String format) {
		return format(new Date(), format);
	}
	/**
	 * 获取前n天的日期
	 * @param index
	 * @return
	 */
	public static String getPreDate(int n,String format) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, 0 - n); // 
		return format(calendar.getTime(), format);
	}
	/**
	 * 使用预设格式格式化日期
	 * 
	 * @param date
	 * @return
	 */
	public static String format(Date date) {
		return format(date, getDatePattern());
	}

	/**
	 * 使用用户格式格式化日期
	 * 
	 * @param date
	 *            日期
	 * @param pattern
	 *            日期格式
	 * @return
	 */
	public static String format(Date date, String pattern) {
	        String returnValue= "";
	        if (date != null) {
	            SimpleDateFormat df = new SimpleDateFormat(pattern);
	            returnValue = df.format(date);
	        }
	        return (returnValue);
	    }

	/**
	 * 使用预设格式提取字符串日期
	 * 
	 * @param strDate
	 *            日期字符串
	 * @return
	 */
	public static Date parse(String strDate) {
		return parse(strDate, getDatePattern());
	}

	/**
	 * 使用用户格式提取字符串日期
	 * 
	 * @param strDate
	 *            日期字符串
	 * @param pattern
	 *            日期格式
	 * @return
	 */
	public static Date parse(String strDate, String pattern) {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		try {
			return df.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 在日期上增加数个整月
	 * 
	 * @param date
	 *            日期
	 * @param n
	 *            要增加的月数
	 * @return
	 */
	public static Date addMonth(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, n);
		return cal.getTime();
	}

	/**
	 * 在日期上增加天数
	 * 
	 * @param date
	 *            日期
	 * @param n
	 *            要增加的天数
	 * @return
	 */
	public static Date addDay(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, n);
		return cal.getTime();
	}
	public static Date addHour(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, n);
		return cal.getTime();
	}
	/**
	 * 获取距现在某一小时的时刻
	 * 
	 * @param format
	 *            格式化时间的格式
	 * @param h
	 *            距现在的小时 例如：h=-1为上一个小时，h=1为下一个小时
	 * @return
	 */
	public static String getpreHour(String format, int h) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = new Date();
		date.setTime(date.getTime() + h * 60 * 60 * 1000);
		return sdf.format(date);
	}

	/**
	 * 获取时间戳
	 */
	public static String getTimeString() {
		SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);
		Calendar calendar = Calendar.getInstance();
		return df.format(calendar.getTime());
	}

	/**
	 * 获取日期年份
	 * 
	 * @param date
	 *            日期
	 * @return
	 */
	public static String getYearString(Date date) {
		return format(date).substring(0, 4);
	}

	/**
	 * 功能描述：返回月
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回月份
	 */
	public static int getMonth(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}
	
	/**
	 * 功能描述：返回年
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回日份
	 */
	public static int getYear(Date date){
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 功能描述：返回日
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回日份
	 */
	public static int getDay(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 功能描述：返回小时
	 * 
	 * @param date
	 *            日期
	 * @return 返回小时
	 */
	public static int getHour(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 功能描述：返回分
	 * 
	 * @param date
	 *            日期
	 * @return 返回分钟
	 */
	public static int getMinute(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MINUTE);
	}

	/**
	 * 返回秒钟
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回秒钟
	 */
	public static int getSecond(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.SECOND);
	}

	/**
	 * 功能描述：返回毫
	 * 
	 * @param date
	 *            日期
	 * @return 返回毫
	 */
	public static long getMillis(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getTimeInMillis();
	}

	/**
	 * 按默认格式的字符串距离今天的天数
	 * 
	 * @param date
	 *            日期字符串
	 * @return
	 */
	public static int countDays(String date) {
		long t = Calendar.getInstance().getTime().getTime();
		Calendar c = Calendar.getInstance();
		c.setTime(parse(date));
		long t1 = c.getTime().getTime();
		return (int) (t / 1000 - t1 / 1000) / 3600 / 24;
	}

	/**
	 * 按用户格式字符串距离今天的天数
	 * 
	 * @param date
	 *            日期字符串
	 * @param format
	 *            日期格式
	 * @return
	 */
	public static int countDays(String date, String format) {
		long t = Calendar.getInstance().getTime().getTime();
		Calendar c = Calendar.getInstance();
		c.setTime(parse(date, format));
		long t1 = c.getTime().getTime();
		return (int) (t / 1000 - t1 / 1000) / 3600 / 24;
	}
	
	/**
	 * 按用户格式字符串获取两日间之间的天数
	 * 
	 * @param date1
	 *            日期字符串
	 *  @param date1
	 *            日期字符串
	 * @param format
	 *            日期格式
	 * @return
	 */
	public static int countTwoDays(String date1, String date2, String format) {
		
		Calendar c = Calendar.getInstance();
		c.setTime(parse(date1, format));
		long t = c.getTime().getTime();
		c.setTime(parse(date2,format));
		long t1 = c.getTime().getTime();
		int n = (int) (t / 1000 - t1 / 1000) / 3600 / 24;
		return Math.abs(n);
	}
	
	/**
	 * 获取前n天的日期
	 * @param index
	 * @return
	 */
	public static Date getPreDate(int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, 0 - n); // 
		return calendar.getTime();
	}
	
	/**
	 * 获取前n小时的日期
	 * @param index
	 * @return
	 */
	public static Date getPreHour(int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.HOUR, 0 - n); // 
		return calendar.getTime();
	}
	/**
	 * 获取前n小时的日期
	 * @param index
	 * @return
	 */
	public static Date getPreMin(int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MINUTE, 0 - n); // 
		return calendar.getTime();
	}
	/**
	 * 获取指定日期前n天的日期
	 * @param n
	 * @param date
	 * @return
	 */
	public static Date getPreDate(int n,Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 0 - n); // 
		return calendar.getTime();
	}
	
	/**
	 * 获取指定日期前n小时的日期
	 * @param n
	 * @param date
	 * @return
	 */
	public static Date getPreHour(int n,Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, 0 - n); // 
		return calendar.getTime();
	}
	
	/**
	 * 获取两个日期之间的日期
	 * @param start 开始日期
	 * @param end 结束日期
	 * @return 日期集合
	 */
	public static List<Date> getBetweenDates(Date start, Date end) {
	    List<Date> result = new ArrayList<Date>();
	    Calendar tempStart = Calendar.getInstance();
	    tempStart.setTime(start);
	    tempStart.add(Calendar.DAY_OF_YEAR, 0);
	    
	    Calendar tempEnd = Calendar.getInstance();
	    tempEnd.setTime(end);
	    tempEnd.add(Calendar.DAY_OF_YEAR, 1);
	    while (tempStart.before(tempEnd)) {
	        result.add(tempStart.getTime());
	        tempStart.add(Calendar.DAY_OF_YEAR, 1);
	    }
	    return result;
	}
	
	
	public static void main(String[] args) {
		String str = "2017-05-12";
		Integer count = DateUtils.countDays(str, DateUtils.FORMAT_SHORT);
		System.out.println(count);
	}
	
}
