package com.jinxin.service.paygateway.online.hxb.util.hxbUtil;

import java.util.regex.Pattern;

public class Test3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	/*	String rex = "[1-9]{1}[0-9]{0,2}";
		String s = "2";
		if(s.matches(rex)){
			System.out.println("OK,PASS");
		}else{
			System.out.println("Error");
		}
		*/
		/*String str = "";
		if(Test3.IsNumber(str)){
			System.out.println("OK......");
		}
		*/
		/*String signFlag = "01";
		if(signFlag.matches("[0-2]{1}")){
			System.out.println("OK,PASS");
		}else{
			System.out.println("OMG,ERROR!");
		}*/
//		String s = "20120812";
	//	String s2 = s.substring(0,8);
	//	System.out.println(s2);
		String type = "12";
		if(type.matches("[0-9]{1}")){
			System.out.println("OK,PASS");
		}else{
			System.out.println("OMG,ERROR");
		}
	}
	
	public static boolean IsNumber(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

}
