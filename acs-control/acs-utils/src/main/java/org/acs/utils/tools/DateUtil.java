package org.acs.utils.tools;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


public class DateUtil {

	private static String DEFAULT_TIMEZONE = "GMT+8";
	private static String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
	/**
	  * 转换时间时区
	  * @param convertString  需要转的时间字符串
	  * @param format  格式话字符串 例如yyyy-MM-dd HH:mm:ss
	  * @param sourceTimeZone 源时间时区
	  * @param targetTimeZone 目标时间时区
	  * @return
	  * @throws ParseException
	  */
	 public static Date ConverDateGMT(String convertString,String format,String sourceTimeZone,String targetTimeZone)
	 {
		 try {
		  Date date=null;
	
		  if(isEmpty(sourceTimeZone)){
		   sourceTimeZone = DEFAULT_TIMEZONE;
		  }
		  
		  if(isEmpty(targetTimeZone)){
		   targetTimeZone = DEFAULT_TIMEZONE;
		  }
		  if(isEmpty(format)){
		   format = DEFAULT_FORMAT;
		  }
		  SimpleDateFormat sdf = new SimpleDateFormat(format);
		  //获取传入的时间值
		  Long time;
		
			time = new Date(sdf.parse(convertString).getTime()).getTime();
		
		  //获取源时区时间相对的GMT时间
		  Long sourceRelativelyGMT=time-TimeZone.getTimeZone(sourceTimeZone).getRawOffset();
		  //GMT时间+目标时间时区的偏移量获取目标时间
		  Long targetTime=sourceRelativelyGMT+TimeZone.getTimeZone(targetTimeZone).getRawOffset();
		  date= new Date(targetTime);
		  return date;
		 } catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return null;
	 }
	 
	public static boolean isEmpty(String value) {
		  boolean emptyFlg = false;
		  if (null == value || value.trim().length() <= 0) {
		   emptyFlg = true;
		  }
		  return emptyFlg;
	}
	//字符串转日期
	public static Date StringToDate(String value,String format){
		//"yyyy-MM-dd HH:mm"
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		if("".endsWith(value)||value==null)return null;
		try {
			return sdf.parse(value);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	//日期转字符串
	public static String DateToString(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String str = sdf.format(date);
		return str;
	}
	
	//日期操作,type 值0时加 其他值时减,单位为天
	public static Date countDate(Date cs_searchTime,int differ,int type){
		Date resultDate = null;
		Calendar calendar=Calendar.getInstance();   
		calendar.setTime(cs_searchTime);
		if(type==0){
			 calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+differ);
			 resultDate = calendar.getTime();
		}else{
			 calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)-differ);
			 resultDate = calendar.getTime();
		}
		System.out.println("resultDate:=="+resultDate);
		return resultDate;
	}
	//日期操作,type 值0时加 其他值时减,单位为小时
	public static Date countHours(Date cs_searchTime,int differ,int type){
		Date resultDate = null;
		Calendar calendar=Calendar.getInstance();   
		calendar.setTime(cs_searchTime);
		if(type==0){
			 calendar.set(Calendar.HOUR_OF_DAY,calendar.get(Calendar.HOUR_OF_DAY)+differ);
			 resultDate = calendar.getTime();
		}else{
			 calendar.set(Calendar.HOUR_OF_DAY,calendar.get(Calendar.HOUR_OF_DAY)-differ);
			 resultDate = calendar.getTime();
		}
		System.out.println("resultDate:=="+resultDate);
		return resultDate;
	}
	//日期操作,type值0时加 其他值时减,单位为分钟
	public static Date countMinute(Date cs_searchTime,int differ,int type){
		Date resultDate = null;
		Calendar calendar=Calendar.getInstance();   
		calendar.setTime(cs_searchTime);
		if(type==0){
			 calendar.set(Calendar.MINUTE,calendar.get(Calendar.MINUTE)+differ);
			 resultDate = calendar.getTime();
		}else{
			 calendar.set(Calendar.MINUTE,calendar.get(Calendar.MINUTE)-differ);
			 resultDate = calendar.getTime();
		}
		System.out.println("resultDate:=="+resultDate);
		return resultDate;
	}
	//日期操作,type值0时加 其他值时减,单位为秒
	public static Date countSecond(Date cs_searchTime,int differ,int type){
			Date resultDate = null;
			Calendar calendar=Calendar.getInstance();   
			calendar.setTime(cs_searchTime);
			if(type==0){
				 calendar.set(Calendar.SECOND,calendar.get(Calendar.SECOND)+differ);
				 resultDate = calendar.getTime();
			}else{
				 calendar.set(Calendar.SECOND,calendar.get(Calendar.SECOND)-differ);
				 resultDate = calendar.getTime();
			}
			System.out.println("resultDate:=="+resultDate);
			return resultDate;
	}
	//根据时间得到小时
	public static int getHourForDate(Date time){
		int hour = 0;
		Calendar calendar=Calendar.getInstance();   
		calendar.setTime(time);
		hour = calendar.get(Calendar.HOUR_OF_DAY);
		return hour;
	}
	//根据时间得到分钟
	public static int getMinuteForDate(Date time){
		int minute = 0;
		Calendar calendar=Calendar.getInstance();   
		calendar.setTime(time);
		minute = calendar.get(Calendar.MINUTE);
		return minute;
	}
	//对时间列表进行排序
	public static List<Date> sortDateList(List<Date> dateList){
		Date[] dateSet=new Date[dateList.size()];  
        for(int i=0;i<dateList.size();i++){  
        	dateSet[i]=(Date)dateList.get(i);  
        }
		Date temp = null;
		for(int i=dateList.size()-1;i>0;i--){
			for(int j=0;j<i;j++){
				if(dateSet[j+1].before(dateSet[j])){
					temp=dateSet[j];
					dateSet[j]=dateSet[j+1];
					dateSet[j+1]=temp;
				}
			}
		}
		dateList = Arrays.asList(dateSet);
		return dateList;
	}
	public static Date formatByMongod(String time){
		time = time.replace("T", " ").replace("Z", " ").trim();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(time);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
