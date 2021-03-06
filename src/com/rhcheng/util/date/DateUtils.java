package com.rhcheng.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;

/*******************************************************************************
 * 日期工具类
 * 
 * <pre>
 * 日期操作工具类
 * </pre>
 * 
 * @author RhCheng
 * @version 2013-07-01
 ******************************************************************************/
public class DateUtils {
	private DateUtils() {

	}

	
	/**
	 * 格式化日日期成字符串
	 * 
	 * @author zengxiangtao
	 * **/
	public static String formatDate(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	

	/**
	 * 字符串转换成日期格式
	 * 
	 * @author zengxiangtao
	 * **/
	public static Date strToDate(String date, String pattern) {
		if (StringUtils.isBlank(date)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
	
	/**
	 * 通过add方法订制日期
	 * 
	 * java中日期操作类库：TimeZone、DateFormat、SimpleDateFormat、
	 * (首选)Calendar(lenient 和 non-lenient模式，set\add\roll——首选set与add方法)
	 * @param date
	 *            --日期
	 * @param field
	 *            --位移单位
	 * @prama offset
	 * 			  --位移数值
	 */
	public static Date addFiledOfDate(Date date, int field, int offset) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(field, offset);
		return calendar.getTime();
	}
	
	/**
	 * 下次扣费时间
	 * */
	public static Date  getPayDate(Date date) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 2);
	    calendar.set(Calendar.DAY_OF_MONTH, 2);
		return calendar.getTime();
	}
	
	
	/**
	 * 得到当前日期
	 * @param pattern
	 * @return
	 */
	public static String getNowDate(String pattern){
		return formatDate(new Date(),pattern);
	}
	
	/**
	 * 增加年份
	 * @param amount
	 * @return
	 */
	public static Date addYear(int amount){
		Calendar cd = Calendar.getInstance();
		cd.add(Calendar.YEAR, amount);
		return cd.getTime();
	}
	
	/**
	 * 增加月份
	 * @param amount
	 * @return
	 */
	public static Date addMonth(int amount){
		Calendar cd = Calendar.getInstance();
		cd.add(Calendar.MONTH, amount);
		return cd.getTime();
	}
	
	/**
	 * 增加天数
	 * @param amount
	 * @return
	 */
	public static Date addDay(int amount){
		Calendar cd = Calendar.getInstance();
		cd.add(Calendar.DAY_OF_MONTH, amount);
		return cd.getTime();
	}
	
	/**
	 * 得到毫秒数
	 * @return
	 */
	public static Long getMillis(){
		return Calendar.getInstance().getTimeInMillis();
	}
	
	/**
	 * 得到年份
	 * @return
	 */
	public static int getYear(){
		return Calendar.getInstance().get(Calendar.YEAR);
	}
	
	/**
	 * 得到月份
	 * @return
	 */
	public static int getMonth(){
		return Calendar.getInstance().get(Calendar.MONTH);
	}
	
	/**
	 * 得到天数
	 * @return
	 */
	public static int getDay(){
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 两个日期的天数差
	 * @param fromDate
	 * @param endDate
	 * @return
	 */
	public static int diffDay(Date fromDate,Date endDate){
		long diff = endDate.getTime() - fromDate.getTime();
		return (int) (diff / (24 * 3600 * 1000));
	}
	
	/**
	 * 两个日期的天数差
	 * @param fromDate
	 * @param endDate
	 * @return
	 */
	public static long diffMillis(Date fromDate,Date endDate){
		return endDate.getTime() - fromDate.getTime();
	}
	
	
	public static void main(String[] args) {
		System.out.println(DateUtils.formatDate(new Date(), "yyyyMMddHHmmss"));
	}
}
