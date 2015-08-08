package com.ddhy.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtils {
	/**
	 * 
	 * 判断今天是否是当月第一天
	 * 
	 * @param s
	 * @return
	 */
	public static boolean firstDayOfMonth() {
		// 获得一个日历对象
		Calendar c = Calendar.getInstance();
		// 得到本月的那一天
		@SuppressWarnings("static-access")
		int today = c.get(c.DAY_OF_MONTH);
		// 然后判断是不是本月的第一天
		if (today == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * 取出当月最后一天
	 * 
	 * @param s
	 * @return
	 */
	public static Date lastDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.roll(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}
	
	/**
	 * 
	 * 与当前日期相比，超出的日期
	 * 
	 * @param date //要比较的日期
	 * @param intervalDayNum//不同日期间的间隔
	 * @return
	 */
	public static boolean beyondDiffDay(Date date, int intervalDayNum) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = f.format(date);
		String nowDateStr = f.format(new Date());
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		/* long nh = 1000*60*60;//一小时的毫秒数
		 * long nm = 1000*60;//一分钟的毫秒数
		 * long ns = 1000;//一秒钟的毫秒数 */
		long diff;
		try {
			// 获得两个时间的毫秒时间差异
			diff = f.parse(nowDateStr).getTime() - f.parse(dateStr).getTime();
			if (diff >= (nd * intervalDayNum))
				return true;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 将字符串日期转化成Date (格式：2010-07-27 21:30:00)
	 * 
	 * @param s
	 * @return
	 */
	public static Date yyyy_MM_dd_HH_mm_ss2Date(String s) {
		DateFormat formate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date restDate = null;
		try {
			restDate = formate.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return restDate;
	}
	
	/**
	 * 
	 * @param s
	 * @return
	 */
	public static String Date2yyyyMMddHHmm(Date date) {
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
		return format.format(date);
	}
	
	/**
	 * 格式化Date
	 * 
	 * @param str 如：20100206
	 * @return
	 */
	public static Date yyyyMMdd2Date(String str) {
		DateFormat formate = new SimpleDateFormat("yyyyMMdd");
		Date restDate = null;
		try {
			restDate = formate.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return restDate;
	}
	
	/**
	 * 格式化Date
	 * 
	 * @param str 如：2010-02-06
	 * @return
	 */
	public static Date yyyy_MM_dd2Date(String str) {
		DateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
		Date restDate = null;
		if (StringUtil.isBlank(str)) {
			return null;
		}
		try {
			restDate = formate.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return restDate;
	}
	
	/**
	 * 格式化Date
	 * 
	 * @param str
	 * @return
	 * @author lusar
	 */
	public static Date yyyyMMddHHmmss2Date(String str) {
		DateFormat formate = new SimpleDateFormat("yyyyMMddHHmmss");
		Date restDate = null;
		try {
			restDate = formate.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return restDate;
	}
	
	/**
	 * 格式化日期
	 * 
	 * @param date
	 * @return
	 */
	public static String MM_dd_HH_mm(Date date) {
		DateFormat format = new SimpleDateFormat("MM-dd HH:mm");
		return format.format(date);
	}
	
	/**
	 * 功能描述: 将日期格式转换成字符串<br/>
	 * 
	 * @param date
	 * @return
	 * @author
	 *         日期:Apr 24, 2012 2:33:16 PM
	 */
	public static String yyyy_MM_dd(Date date) {
		if (date == null) {
			return "";
		}
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}
	
	/**
	 * 
	 * Description : 日期格式化(如2009-01-02 10:11:11)
	 * 
	 * @param date
	 * @return
	 * @author :
	 */
	public static String yyyy_MM_dd_HH_mm_ss(Date date) {
		if (date == null) {
			return "";
		}
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}
	
	/**
	 * 
	 * Description : 日期格式化(如2009年01月02日 10时11分)
	 * 
	 * @param date
	 * @return
	 * @author : luchuan
	 * @date 2013/7/30 16:25
	 */
	public static String yyyy_MM_dd_HH_mm(Date date) {
		if (date == null) {
			return "";
		}
		DateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
		return format.format(date);
	}
	
	/**
	 * 功能描述: yyyyMMddHHmmss<br/>
	 * 
	 * @param date
	 * @return
	 * @author
	 *         日期:2013-9-1 上午11:29:06
	 */
	public static String yyyyMMddHHmmss(Date date) {
		if (date == null) {
			return "";
		}
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		return format.format(date);
	}
	
	public static void main(String[] arags) {
		// DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// System.out.println(format.format(yyyyMMdd2Date("19820426")));
		// System.out.println(yyyyMMddHHmmss(new Date()));
		
		// Date date1 = new Date();
		// Date date2 = new Date();
		// try {
		// System.out.println(daysBetween(date1, date2));
		// } catch (ParseException e) {
		// e.printStackTrace();
		// System.out.println("error");
		// }
		
		String json = "{hello\r\n world \n lwesdfo =sdkf=\nd}";
		json = json.replaceAll("\\n", "\\\n");
		json = json.replaceAll("\\r", "\\\r");
		System.out.println(json);
	}
	
	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate 较小的时间
	 * @param bdate 较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate) throws ParseException {
		long between_days = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			smdate = sdf.parse(sdf.format(smdate));
			bdate = sdf.parse(sdf.format(bdate));
			Calendar cal = Calendar.getInstance();
			cal.setTime(smdate);
			long time1 = cal.getTimeInMillis();
			cal.setTime(bdate);
			long time2 = cal.getTimeInMillis();
			between_days = (time2 - time1) / (1000 * 3600 * 24);
		} catch (Exception e) {
			throw new ParseException("解析错误,日期格式不哼却", 0);
		}
		return Integer.parseInt(String.valueOf(between_days));
	}
	
	public static int getWeekOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();// 获得一个日历
		calendar.setTime(date);
		int number = calendar.get(Calendar.DAY_OF_WEEK);// 星期表示1-7，是从星期日开始，
		// String [] str = {"","星期日","星期一","星期二","星期三","星期四","星期五","星期六",};
		number = number - 1;
		number = number == 0 ? 7 : number;
		return number;
	}
	
	/**
	 * 日期增加秒数
	 * 
	 * @param date
	 * @param second
	 * @return
	 */
	public static Date addDate(Date date, int calendarField, int amount) {
		Calendar calendar=Calendar.getInstance();  
		calendar.setTime(new Date());
		calendar.add(calendarField, amount);
		return calendar.getTime();
	}
}
