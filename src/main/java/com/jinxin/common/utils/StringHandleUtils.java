package com.jinxin.common.utils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.Assert;

/**
 * <p>
 * Title: 公用类
 * </p>
 * <p>
 * Description:
 * </p>
 * 对一些接收String类型的进行处理,比如随机数等
 * <p>
 * Copyright: jinxin (c) 2013 版权
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author
 * @version V1.0
 * @date 2013-6-27下午03:10:15
 */
public class StringHandleUtils {

	private static final Log logger = LogFactory
	.getLog(StringHandleUtils.class);
	
	/**
	 * 
	 * @author  
	 * @Title getRandomCode
	 * @Time: 2013-6-28下午03:04:21
	 * @Description: 
	 * @return: String 
	 * @throws: 
	 * @param len 获取返回随机数长度
	 * @param isLetterDigit 是否必须包含字母和数字
	 * @return
	 */
	public static String getRandomCode(int len, boolean isLetterDigit) {
		int number;
		char code = 0;
		String checkCode = "";
		Assert.isTrue(len > 1, "长度不能小于1位");
		Random random = new Random();
		boolean isLetter = false;
		boolean isDigit = false;
		for (int i = 0; i < len; i++) {
			number = random.nextInt(1000);
			if (number % 2 == 0) {
				isDigit = true;
				code = (char) ('0' + (char) (number % 10));
			} else if (number % 3 == 0) {
				isLetter = true;
				code = (char) ('A' + (char) (number % 26));
			} else {
				isLetter = true;
				code = (char) ('a' + (char) (number % 26));
			}
			checkCode += code + "";
		}
		if(isLetterDigit) {
			String temp = checkCode.substring(0, checkCode.length()-1);
			number = random.nextInt(1000);
			if(!isLetter) {
				checkCode = temp.concat(String.valueOf((char) ('A' + (char) (number % 26))));
			} else if (!isDigit) {
				checkCode = temp.concat(String.valueOf((char) ('0' + (char) (number % 10))));
			}
		}
		return checkCode;
	}

	/**
	 * @author
	 * @Title: getProperties
	 * @Description:  根据配置的key得到相应的value
	 * @return 
	 * @throws SystemException
	 */
	private static Properties properties ;  
	public  static Properties  getProperties(){
		try {
			if(properties == null){					
					properties = PropertiesLoaderUtils.loadAllProperties("/commonPropertyFile.properties");				
			}
		} catch (IOException e) {			
			logger.error(StringHandleUtils.getExceptionInfo(e));
		}
		return properties;	
	}
	
	/**
	 * 
	 * @author  
	 * @Title sql_inj
	 * @Time: 2013-9-27上午09:47:38
	 * @Description: 防SQL注入参数判断 
	 * @return: boolean 
	 * @throws: 
	 * @param str
	 * @return
	 */
	public static boolean sql_inj(String str) 
	 {
	    String inj_str = "'|and|exec|execute|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|;|or|-|+|,|like";
	    String inj_stra[] = inj_str.split("|");
	    for (int i=0 ; i < inj_stra.length ; i++ )
	    {
	        if (str.indexOf(inj_stra[i])>=0)
	        {
	            return true;
	        }
	    }
	    return false;
	 }
	
	/**
	 * 
	 * @author  
	 * @Title getExceptionInfo
	 * @Time: 2013-11-19下午12:15:11
	 * @Description: 获取异常堆栈信息
	 * @return: String 
	 * @throws: 
	 * @param t
	 * @return
	 */
	public static String getExceptionInfo(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter out = new PrintWriter(sw);
		t.printStackTrace(out);
		out.close();
		return sw.toString();
	}
	
	/**
	 * 
	 * @author  
	 * @Title null2Zero
	 * @Time: 2013-12-8下午12:09:22
	 * @Description: 空数据对象转换为0 
	 * @return: T 
	 * @throws: 
	 * @param <T>
	 * @param numObj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T null2Zero(boolean isBigDecimal, T numObj) {
		return (T) (numObj == null ? (isBigDecimal ? BigDecimal.ZERO : 0) : numObj);
	}
	
	/**
	 * 
	 * @author  
	 * @Title number2String
	 * @Time: 2013-1-29下午02:22:55
	 * @Description: 金额数字公司格式化处理
	 * @return: String 
	 * @throws: 
	 * @param num
	 * @return
	 */
	public static String number2CurrencyString(Object num) {
		return (new DecimalFormat("###,###0.00")).format(num);
	}
	
}
