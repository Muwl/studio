package com.mu.smarthome.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import android.net.ParseException;
import android.text.format.DateFormat;

/**
 * TimeUtils
 * 
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2013-8-24
 */
public class TimeUtils {

	public static final SimpleDateFormat DATE_FORMAT_DATE = new SimpleDateFormat(
			"yyyy-MM-dd");
	public static final SimpleDateFormat DATE_FORMAT_DATE2 = new SimpleDateFormat(
			"yyyy-M-d");

	public static Date getDateByStr(String dd) {

		Date date;
		try {
			date = DATE_FORMAT_DATE.parse(dd);
		} catch (java.text.ParseException e) {
			date = null;
			e.printStackTrace();
		}
		return date;
	}

	public static String getStringByString(String dd) {

		Date date;
		try {
			date = DATE_FORMAT_DATE2.parse(dd);
		} catch (java.text.ParseException e) {
			date = null;
			e.printStackTrace();
		}
		return getDate(date);
	}

	/**
	 * 将现在时间转换成 yyyy-MM-dd
	 * 
	 * @return 现在时间的yyyy-MM-dd
	 */
	public static String getDate() {
		return DATE_FORMAT_DATE.format(new Date());
	}

	/**
	 * 将Date 转换 yyyy-mm-dd输出
	 * 
	 * @param date
	 * @return
	 */
	public static String getDate(Date date) {
		return DATE_FORMAT_DATE.format(date);
	}

	public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
		return dateFormat.format(new Date(timeInMillis));
	}

	/**
	 * 通过年和月转换成 yyyy-MM-dd
	 * 
	 * @param year
	 * @param month
	 * @return yyyy-MM-dd
	 */
	public static String getDateByYAM(String year, String month) {
		if (Integer.valueOf(month) < 10) {
			return (year + "-0" + month + "-" + "01");
		} else {
			return (year + "-" + month + "-" + "01");
		}
	}

	/**
	 * 获取月和
	 * 
	 * @param date
	 * @return
	 */
	public static String getString(String date) {
		LogManager.LogShow("date", date, LogManager.ERROR);
		Date date2 = getDateByStr(date);
		date = DATE_FORMAT_DATE2.format(date2);
		String year = date.substring(0, 4);
		String monthday = date.substring(5);
		return monthday + "\n(" + year + ")";
	}

	/**
	 * 获取当前时间的小
	 * 
	 * @return
	 */
	public static int getHours() {
		Calendar ca = Calendar.getInstance();
		return ca.get(Calendar.HOUR_OF_DAY);
	}

}