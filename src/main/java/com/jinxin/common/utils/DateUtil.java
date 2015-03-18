package com.jinxin.common.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.jinxin.model.ConstantsBean;


/**
 * 
 * <p>Title: </p>
 * <p>Description: 日期操作类</p>
 * <p>Copyright: jinxin (c) 2013 版权</p>
 * <p>Company: </p>
 * @author 
 * @version V1.0 
 * @date 2013-7-8下午05:25:36
 */
public class DateUtil
{
	/**
	 * 
	 * @author  
	 * @Title generateRecordID
	 * @Time: 2011-7-8下午05:24:12
	 * @Description: 生成年月日 时分秒毫秒 格式 
	 * @return: String 
	 * @throws: 
	 * @return
	 */
	public static String getTradeNoFromDate()
	{
		return new SimpleDateFormat(ConstantsBean.TRADE_NO_RULE, Locale.US).format(new Date());
	}
	
	/**
	 * 
	 * @author  
	 * @Title compareNowDate
	 * @Time: 2011-7-8下午05:48:41
	 * @Description: 比较传入的年月是否大于当前日期，并判断是否小于当前年增加10年
	 * @return: boolean 
	 * @throws: 
	 * @param monthYear
	 * @return
	 */
	public static boolean compareNowDate(String yearMonth){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String nowDate = sdf.format(new Date());
		
		Calendar   cal   =   Calendar.getInstance(); 
		cal.add(Calendar.YEAR, ConstantsBean.CREDIT_YEAR);
		String endDate = sdf.format(cal.getTime());
		
		return Integer.parseInt(yearMonth)>=Integer.parseInt(nowDate) && Integer.parseInt(yearMonth)<=Integer.parseInt(endDate) ;
	}

	/**
	 * 
	 * @author  
	 * @Title getDateTimeNow
	 * @Time: 2011-7-18下午03:54:00
	 * @Description: 返回当前时间精确到毫秒
	 * @return: String 
	 * @throws: 
	 * @return
	 */
	public static String getDateTimeNow(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US).format(new Date());
	}
	
	/**
	 * 
	 * @author  
	 * @Title getDateByDay
	 * @Time: 2011-9-27下午05:53:13
	 * @Description: 获取今天的开始时间
	 * @return: String 
	 * @throws: 
	 * @return
	 */
	public static String getDateByDay(){
		return new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(new Date());
	}
	
	/**
	 * 
	 * @author  
	 * @Title getDateByDay
	 * @Time: 2011-9-27下午05:53:13
	 * @Description: 获取当周的开始时间
	 * @return: String 
	 * @throws: 
	 * @return
	 */
	public static String getDateByWeek(){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
		//1 为本周的第一天，本周第一天为星期天
		//2 星期一 依次类推
		cal.set(GregorianCalendar.DAY_OF_WEEK, 1);
		return datef.format(cal.getTime());
	}
	
	/**
	 * 
	 * @author  
	 * @Title getDateByDay
	 * @Time: 2011-9-27下午05:53:13
	 * @Description: 获取当月的开始时间
	 * @return: String 
	 * @throws: 
	 * @return
	 */
	public static String getDateByMonth(){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
		cal.set(GregorianCalendar.DAY_OF_MONTH, 1);
		return datef.format(cal.getTime());
	}
	
	/**
	 * 
	 * @author  
	 * @Title getDateByDay
	 * @Time: 2011-9-27下午05:53:13
	 * @Description: 获取当月的开始时间
	 * @return: String 
	 * @throws: 
	 * @return
	 */
	public static String getDateByYear(){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
		cal.set(GregorianCalendar.DAY_OF_YEAR, 1);
		return datef.format(cal.getTime());
	}
	
	/**
	 * 
	 * @author  
	 * @Title getDateByGap
	 * @Time: 2011-10-9下午03:25:49
	 * @Description: 根据传入的时间毫秒,计算当前时间往前有时间
	 * @return: String 
	 * @throws: 
	 * @param time
	 * @return
	 */
	public static String getDateByGap(Long gapTime){
		
		//格式 年月日时分秒
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
		//当前时分秒
		long currce = System.currentTimeMillis();
		//二数相差
		long onece = currce - gapTime;
		
		Date onecetime = new Timestamp(onece);
		String onecetimevalue = formatter.format(onecetime);
		return onecetimevalue;
	}
	
	/**
	 * 
	 * @author  
	 * @Title getDateZone
	 * @Time: 2011-11-28下午04:57:59
	 * @Description: 根据交易时间，返回带有时区的格式为：dd MMM yyyy HH:mm:ss.SSS的时间
	 * @return: String 
	 * @throws: 
	 * @param date
	 * @return
	 */
	public static String getDateZone(Timestamp date){
		String time = "";
		SimpleDateFormat formats = new SimpleDateFormat("dd MMM yyyy HH:mm:ss.SSS", Locale.US);
		if(date!=null){
			time=formats.format(date);
		}
		return time + " " + ConstantsBean.TIME_ZONE;
	}
	
	public static void main(String[] args) {
		System.out.println(getDateByWeek());
	}
}
