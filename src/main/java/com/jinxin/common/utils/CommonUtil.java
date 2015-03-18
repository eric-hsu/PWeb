package com.jinxin.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

/**
 * @className:StaticMapUtil.java
 * @classDescription:
 * @author:erichu
 * @createTime:2014-6-26 下午11:24:27
 */

public class CommonUtil {
	  // 获取交易流水号
	public static String getTradeNo(){

		//根据 年月日  时分秒 毫秒生成记录号
		String tradeNo = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.US).format(new Date());;
		//生成5位随机数
		//防止出现小于5位的随机数
		String randomCode = String.valueOf(Math.random()).substring(2,5);
		return tradeNo+randomCode;
	}
	
	/**
	 * 
	 * @author  
	 * @Title getIpAddr
	 * @Time: 2011-11-25下午04:33:18
	 * @Description: 获取真实IP地址，防止代理
	 * @return: String 
	 * @throws: 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {         
		String ip = request.getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {            
			ip = request.getHeader("Proxy-Client-IP");        
		}   
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {            
			ip = request.getHeader("WL-Proxy-Client-IP");         
		}        
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {             
			ip = request.getRemoteAddr();        
		}
		return ip;   
	}

}
