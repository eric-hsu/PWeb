package com.jinxin.common.utils;

import java.util.Hashtable;
import java.util.Map;

/**
 * 
 * <p>Title: </p>
 * <p>Description: 数据检验类</p>
 * <p>Copyright: jinxin (c) 2013 版权</p>
 * <p>Company: </p>
 * @author 
 * @version V1.0 
 * @date 2013-7-15下午02:41:38
 */
public class ParamCheck {

	public ParamCheck() {
	}

	/**
	 * 
	 * @author 
	 * @Title: legalityCheck
	 * @Description: TODO(判断字符串中是否有"&", "<", ">", "\"", "'"符号)
	 * @param @param param
	 * @param @return    设定文件
	 * @return boolean    返回类型
	 * @throws
	 * @param param
	 * @return
	 */
	public static boolean legalityCheck(String param) {
		for (int i = 0; i < illegalParam.length; i++) {
			int flag = param.indexOf(illegalParam[i]);
			if (flag != -1)
				return true;
		}

		return false;
	}

	/**
	 * 
	 * @author 
	 * @Title: check
	 * @Description: TODO(判断字符是否为空值或null)
	 * @param @param param
	 * @param @return    设定文件
	 * @return boolean    返回类型
	 * @throws
	 * @param param
	 * @return
	 */
	public static boolean check(String param) {
		return param == null || param.trim().length() == 0;
	}

	/**
	 * 
	 * @author 
	 * @Title: check
	 * @Description: TODO(判断字符数组是否为空值或null)
	 * @param @param param
	 * @param @return    设定文件
	 * @return boolean    返回类型
	 * @throws
	 * @param param
	 * @return
	 */
	public static boolean check(String param[]) {
		return param == null || param.length == 0;
	}

	/**
	 * 
	 * @author 
	 * @Title: check
	 * @Description: TODO(判断对象是否为null)
	 * @param @param obj
	 * @param @return    设定文件
	 * @return boolean    返回类型
	 * @throws
	 * @param obj
	 * @return
	 */
	public static boolean check(Object obj) {
		return obj == null;
	}

	/**
	 * 
	 * @author 
	 * @Title: trim
	 * @Description: TODO(给字符数组去空格)
	 * @param @param param
	 * @param @return    设定文件
	 * @return String[]    返回类型
	 * @throws
	 * @param param
	 * @return
	 */
	public static String[] trim(String param[]) {
		for (int i = 0; i < param.length; i++)
			param[i] = param[i].trim();

		return param;
	}

	/**
	 * 
	 * @author 
	 * @Title: exchangeParam
	 * @Description: TODO(转换符号"&", "<", ">", "\"", "'"为"&amp;", "&lt;", "&gt;", "&quot;")
	 * @param @param param
	 * @param @return    设定文件
	 * @return String    返回类型
	 * @throws
	 * @param param
	 * @return
	 */
	public static String exchangeParam(String param) {
		for (int i = 0; i < illegalParam.length; i++) {
			int flag = param.indexOf(illegalParam[i]);
			if (flag != -1) {
				String legalString = (String) paramMap.get(illegalParam[i]);
				param = param.replaceAll(illegalParam[i], legalString);
			}
		}

		return param;
	}

	/**
	 * 
	 * @author 
	 * @Title: exchangeStrParam
	 * @Description: TODO(字符串去空格并转换特殊字符"&", "<", ">", "\"", "'")
	 * @param @param paramValue
	 * @param @return    设定文件
	 * @return String    返回类型
	 * @throws
	 * @param paramValue
	 * @return
	 */
	public static String exchangeStrParam(String paramValue) {
		if (!check(paramValue)) {
			paramValue = exchangeParam(paramValue);
			paramValue = paramValue.trim();
		} else {
			paramValue = "";
		}
		return paramValue;
	}

	/**
	 * 
	 * @author 
	 * @Title: exchangeStrParam
	 * @Description: TODO(转换字符数组为空时转为0否则转换特殊字符)
	 * @param @param paramValue
	 * @param @return    设定文件
	 * @return String[]    返回类型
	 * @throws
	 * @param paramValue
	 * @return
	 */
	public static String[] exchangeStrParam(String paramValue[]) {
		if (!check(paramValue)) {
			for (int i = 0; i < paramValue.length; i++)
				paramValue[i] = exchangeParam(paramValue[i]);

			paramValue = trim(paramValue);
		} else {
			paramValue = new String[0];
		}
		return paramValue;
	}

	/**
	 * 
	 * @author 
	 * @Title: isNum
	 * @Description: TODO(判断字符是否为数字)
	 * @param @param param
	 * @param @return    设定文件
	 * @return boolean    返回类型
	 * @throws
	 * @param param
	 * @return
	 */
	public static boolean isNum(String param) {
		if (check(param))
			return false;
		if (param.indexOf("-") != param.lastIndexOf("-")
				|| param.indexOf(".") != param.lastIndexOf("."))
			return false;
		if (param.length() == 1
				&& (param.equalsIgnoreCase("-") || param.equalsIgnoreCase(".")))
			return false;
		if (param.length() >= 2 && param.startsWith("-")
				&& param.substring(1, 2).equalsIgnoreCase("."))
			return false;
		for (int i = 0; i < param.length(); i++) {
			String sNum = param.substring(i, i + 1);
			if (i == 0 && sNum.equals("."))
				return false;
			if (i != 0 && sNum.equals("-"))
				return false;
			int flag = "-.0123456789".indexOf(sNum);
			if (flag == -1)
				return false;
		}

		return true;
	}

	/**
	 * 
	 * @author 
	 * @Title: isPositiveInteger
	 * @Description: TODO(判断字符是否为0到9数字)
	 * @param @param param
	 * @param @return    设定文件
	 * @return boolean    返回类型
	 * @throws
	 * @param param
	 * @return
	 */
	public static boolean isPositiveInteger(String param) {
		if (check(param))
			return false;
		if (param.indexOf("-") != -1)
			return false;
		if (param.indexOf(".") != -1)
			return false;
		for (int i = 0; i < param.length(); i++) {
			String sNum = param.substring(i, i + 1);
			int flag = "0123456789".indexOf(sNum);
			if (flag == -1)
				return false;
		}

		return true;
	}

	/**
	 * 
	 * @author 
	 * @Title: isNum
	 * @Description: TODO(判断数组中的值是否为数字)
	 * @param @param param
	 * @param @return    设定文件
	 * @return boolean    返回类型
	 * @throws
	 * @param param
	 * @return
	 */
	public static boolean isNum(String param[]) {
		if (check(param))
			return false;
		for (int i = 0; i < param.length; i++) {
			boolean flag = isNum(param[i]);
			if (!flag)
				return false;
		}

		return true;
	}

	/**
	 * 
	 * @author 
	 * @Title: numToChange
	 * @Description: TODO(判断字符数组中是否为数字,将不是数字的值换成0)
	 * @param @param param
	 * @param @return    设定文件
	 * @return boolean    返回类型
	 * @throws
	 * @param param
	 * @return
	 */
	public static boolean numToChange(String param[]) {
		boolean return_flag = true;
		for (int i = 0; i < param.length; i++) {
			boolean flag = isNum(param[i]);
			if (!flag) {
				param[i] = "0";
				return_flag = false;
			}
		}

		return return_flag;
	}

	/**
	 * 
	 * @author 
	 * @Title: str_to_int
	 * @Description: TODO(将字符串转为int)
	 * @param @param param
	 * @param @return    设定文件
	 * @return int    返回类型
	 * @throws
	 * @param param
	 * @return
	 */
	public static int str_to_int(String param) {
		return Integer.parseInt(param);
	}

	/**
	 * 
	 * @author 
	 * @Title: str_to_int
	 * @Description: TODO(将字符数组中的值换成int)
	 * @param @param param
	 * @param @return    设定文件
	 * @return int[]    返回类型
	 * @throws
	 * @param param
	 * @return
	 */
	public static int[] str_to_int(String param[]) {
		int temp_int[] = new int[param.length];
		if (isNum(param)) {
			for (int i = 0; i < param.length; i++)
				temp_int[i] = Integer.parseInt(param[i]);

			return temp_int;
		} else {
			return null;
		}
	}
	/**
	 * 
	 * @author 
	 * @Title: str_to_float
	 * @Description: TODO(将字符串换成float)
	 * @param @param param
	 * @param @return    设定文件
	 * @return float    返回类型
	 * @throws
	 * @param param
	 * @return
	 */
	public static float str_to_float(String param) {
		return Float.parseFloat(param);
	}

	/**
	 * 
	 * @author 
	 * @Title: str_to_boolean
	 * @Description: TODO(将字符数组换成float)
	 * @param @param param
	 * @param @return    设定文件
	 * @return boolean[]    返回类型
	 * @throws
	 * @param param
	 * @return
	 */
	public static boolean[] str_to_boolean(String param[]) {
		boolean b_param[] = new boolean[param.length];
		for (int i = 0; i < b_param.length; i++)
			b_param[i] = (Boolean.valueOf(param[i])).booleanValue();

		return b_param;
	}
	
	/**
	 * 
	 * @author  
	 * @Title isEmail
	 * @Time: 2011-7-8下午05:17:23
	 * @Description: TODO(判断是否email)
	 * @return: boolean 
	 * @throws: 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email){
		String reg = "\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
		if (email.matches(reg)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * @author  
	 * @Title isPlusAmount
	 * @Time: 2011-7-8下午05:17:23
	 * @Description: TODO(判断是否2位小数、大于0的金额)
	 * @return: boolean 
	 * @throws: 
	 * @param email
	 * @return
	 */
	public static boolean isPlusAmount(String amount){
		String reg = "^[0-9]*\\.*[0-9]{0,2}$";
		if (amount.matches(reg)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * @author 
	 * @Title: str_null_to_empty
	 * @Description: 将字符串null转为空
	 * @param @param param
	 * @param @return    设定文件
	 * @return String    返回类型
	 * @throws
	 * @param param
	 * @return
	 */
	public static String str_null_to_empty(String param){
		return param==null?"":param;
	}

	public static final String NUMBER = "-.0123456789";
	public static final String INTNUMBER = "-0123456789";
	public static final String POSITIVEINTNUMBER = "0123456789";
	public static final Map<String, String> paramMap;
	private static String illegalParam[] = { "&", "<", ">", "\"", "'" };
	private static String macroParam[] = { "&amp;", "&lt;", "&gt;", "&quot;",
			"" };

	static {
		paramMap = new Hashtable<String, String>();
		for (int i = 0; i < illegalParam.length; i++)
			paramMap.put(illegalParam[i], macroParam[i]);

	}
}
