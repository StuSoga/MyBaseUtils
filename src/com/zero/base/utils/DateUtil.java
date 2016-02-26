package com.zero.base.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zero on 16/2/20.
 */
public class DateUtil {

	public static final String DATE_TIME="yyyy-MM-dd HH:mm:ss";

	public static String format(Date date,String pattern){

		if(date==null){
			return "";
		}

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		return simpleDateFormat.format(date);
	}

	public static Date parse(String date,String pattern) throws ParseException {
		DateFormat simpleDateFormat=new SimpleDateFormat(pattern);
		return simpleDateFormat.parse(date);
	}

	/**
	 * 返回一天最早的时候
	 * @param date
	 * @return
	 */
	public static Date getEarliest(Date date){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		setCalendarTime(calendar,0,0,0,0);
		return calendar.getTime();
	}

	/**
	 * 返回一天最晚的时候
	 * @param date
	 * @return
	 */
	public static Date getLastest(Date date){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		setCalendarTime(calendar,23,59,59,59);
		return calendar.getTime();
	}

	/**
	 * 返回N天之后的时间
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date afterDay(Date date,int day){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR,day);
		return calendar.getTime();
	}

	private static void setCalendarTime(Calendar calendar,int hour,int minute,int second,int millisecond){
		calendar.set(Calendar.HOUR_OF_DAY,hour);
		calendar.set(Calendar.MINUTE,minute);
		calendar.set(Calendar.SECOND,second);
		calendar.set(Calendar.MILLISECOND,millisecond);
	}
}
