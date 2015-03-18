package com.jinxin.service.paygateway.online.hxb.util.hxbUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Test4 {

	public static void main(String[] args) throws ParseException {
		String s1 = "20120801";
		String s2 = "20120701";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		long t1 = sdf.parse(s1).getTime();
		long t2 = sdf.parse(s2).getTime();
		long oneMonth = 30*24*60*60*1000l;
		if((t1 - t2) > oneMonth){
			System.out.println("不能超过一个月");
		}
	}
	public static boolean isDateValid(String startDate,String endDate) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		long t1 = sdf.parse(startDate).getTime();
		long t2 = sdf.parse(endDate).getTime();
		long oneMonth = 31*24*60*60*1000l;
		if((t2 - t1) > oneMonth){
			return false;
		}
		return true;
	}

}
