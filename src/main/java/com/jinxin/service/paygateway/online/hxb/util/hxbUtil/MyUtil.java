package com.jinxin.service.paygateway.online.hxb.util.hxbUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyUtil {
	public static void main(String[] args) {
		System.out.println(getCurrentDate());

	}
	/***
	 * eq
	 * @param file
	 */
	public static void printGetandSet(File file){
		if(!file.exists()){
			System.out.println(file.getName() + "文件不存在,请检查！");
		}else{
			BufferedReader br = null;
			List<String> propertyList = null;
			List<String> functionList = null;
			List<String> resultList = null;
			List<String> conList = null;
			try {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				String line = null;
				propertyList = new ArrayList<String>();
				functionList = new ArrayList<String>();
				resultList = new ArrayList<String>();
				conList = new ArrayList<String>();
				
				resultList.add("public String getResult() throws Exception {\n");
				resultList.add("StringBuffer res = super.result();\n");
				while((line = br.readLine()) != null){
					if(line.equals("")){
						break;
					}
					String[] arr = line.split("\\s+");
					String first = arr[1].substring(0, 1);
					String field = first.toLowerCase() + arr[1].substring(1);
					String property = "private String " + field + ";" + "//" + arr[0];
					propertyList.add(property);
					if(arr.length < 3){
						System.out.println(arr[0] + ",字段信息不完整，可能未指定长度哦");
						break;
					}else if(arr.length >= 3){
						String getStr = "public String get" + arr[1] + "() throws Exception {\n";
						getStr += "if (this." + field + "== null || \"\".equals(this." + field + "))\n";
						getStr += "this.set" + arr[1] + "(\"\");\n";
						getStr += "return " + field + ";\n";
						getStr += "}\n";
						functionList.add(getStr);
						
						String setStr = "public void set" + arr[1] + "(String " + field + ") throws Exception {\n";
						setStr += "this." + field + "= StringUtil.fixField(" + field + ", " + arr[3] + ");\n";
						setStr += "}\n";
						functionList.add(setStr);
						
						String getResult = "res.append(this.get" + arr[1] + "());";
						resultList.add(getResult);
						
						String con = "this." + field + " = StringUtil.Trim(super.getBody(" + arr[3] + "));";
						conList.add(con);
					}
											
				}
				
				resultList.add("System.out.println(\"======【响应前置报文】========【\" + res + \"】\");");
				resultList.add("return res.toString();\n");
				resultList.add("}\n");
				
				conList.add("\nSystem.out.println(super.getRes());");
				
				for(int i=0;i<propertyList.size();i++){
					System.out.println(propertyList.get(i));
				}
				System.out.println("\n\n\n");
				for(int i=0;i<functionList.size();i++){
					System.out.println(functionList.get(i));
				}
				
				System.out.println("//-------以下是继承父类方法getResult中的部分内容------");
				for(int i=0;i<resultList.size();i++){
					System.out.println(resultList.get(i));
				}
				
				System.out.println("\n\n\n");
				
				for(int i=0;i<conList.size();i++){
					System.out.println(conList.get(i));
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(br != null){
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public static void sysoutFormat(File file){
		if(!file.exists()){
			System.out.println(file.getName() + "文件不存在,请检查！");
		}else{
			BufferedReader br = null;
			List<String> list = null;
			
			try {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				String line = null;
				list = new ArrayList<String>();
				
				while((line = br.readLine()) != null){
					if(line.equals("")){
						break;
					}
					String[] arr = line.split("\\s+");
					String first = arr[1].substring(0, 1);
					String field = first.toLowerCase() + arr[1].substring(1);
					String one = "private String " + field + ";" + "//" + arr[0];
					list.add(one);
										
				}
				for(int i=0;i<list.size();i++){
					System.out.println(list.get(i));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(br != null){
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public static void createJSP(File file){
		if(!file.exists()){
			System.out.println(file.getName() + "文件不存在,请检查！");
		}else{
			BufferedReader br = null;
			List<String> list = null;
			
			try {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				String line = null;
				list = new ArrayList<String>();
				list.add("<%@ page language=\"java\" contentType=\"text/html; charset=gbk\" pageEncoding=\"gbk\"%>");
				list.add("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
				list.add("<html>\n<head>");
				list.add("  <link href=\"css/login.css\" rel=\"stylesheet\" type=\"text/css\" />");
				list.add("  <link href=\"css/page_bottom.css\" rel=\"stylesheet\" type=\"text/css\" />");
				list.add("</head>");
				list.add("  <body style=\"margin-top:50px\">");
				list.add("    <center><div style=\"font-size:18px;color:black\"><p>低风险支付首次验证</p><br/></div></center>");
				list.add("    <center><a href=\"index.jsp\">回到首页</a></center>");
				list.add("    <div class=\"fill_message\">");
				list.add("    <div style=\"font-size:12px;color:red\"><p>请填写以下信息再点击确定</p><br/></div>");
				list.add("    <form name=\"ctl00\" method=\"post\" action=\"\" id=\"f\">");
				list.add("      <table class=\"tab_login\" >");
				while((line = br.readLine()) != null){
					if(line.equals("")){
						break;
					}
					String[] arr = line.split("\\s+");
					String first = arr[1].substring(0, 1);
					String field = first.toLowerCase() + arr[1].substring(1);
					list.add("       <tr>");
					list.add("         <td valign=\"top\" class=\"w1\">");
					list.add("          " + arr[0] + ":");
					list.add("         </td>");
					list.add("         <td>");
					list.add("           <input name=\"" + field + "\" type=\"text\" id=\"managername\" class=\"text_input\" />");
					list.add("         </td>");	
					list.add("       </tr>");
				}
				list.add("      </table><br/><br/>");
				list.add("      <div class=\"login_in\">");
				list.add("        <input id=\"btnClientRegister\" class=\"button_1\" name=\"submit\" type=\"submit\" value=\"确定\"/>");
				list.add("        <input id=\"btnClientRegister\" class=\"button_1\" name=\"reset\"  type=\"reset\" value=\"重置\"/>");
				list.add("      </div>");
				list.add("    </form>");
				list.add("    </div>");
				list.add("    <center style=\"margin-bottom:20\"><a href=\"index.jsp\" target=\"_self\">回到首页</a></center>");
				list.add("    <br/><br/>");
				list.add("  </body>");
				list.add("</html>");
				for(int i=0;i<list.size();i++){
					System.out.println(list.get(i));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(br != null){
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public static void createXMLString(File file){
		if(!file.exists()){
			System.out.println(file.getName() + "文件不存在,请检查！");
		}else{
			BufferedReader br = null;
	//		List<String> list = null;
			
			try {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				String line = null;
	//			list = new ArrayList<String>();
				String s = "String s = \"<HXE>\" + head + \"<Body>";
				while((line = br.readLine()) != null){
					if(line.equals("")){
						break;
					}
					String[] arr = line.split("\\s+");
					String first = arr[1].substring(0, 1);
					String field2 = first.toLowerCase() + arr[1].substring(1);
					String field = arr[1];
					s += "\t\"<" + field + ">\" + " + field2 + " + \"</" + field + ">\" + \n";
										
				}
				s += "\t\"</Body></HXE>\";";
				s += "\n\tSystem.out.println(s);";
				System.out.println("String now = MyUtil.getCurrentDate();");
				System.out.println("String head = \"<Head><Identification>111</Identification><Channel>123</Channel><TrnxCode>ME001</TrnxCode><TrnxDatetime>\" + now + \"</TrnxDatetime></Head>\";");
				System.out.println(s);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(br != null){
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public static String getCurrentDate(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String s = sdf.format(date);
		return s;
	}

	public static void sysFormat(File file){
		if(!file.exists()){
			System.out.println(file.getName() + "文件不存在,请检查！");
		}else{
			BufferedReader br = null;
			List<String> list = null;
			
			try {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				String line = null;
				list = new ArrayList<String>();
				
				while((line = br.readLine()) != null){
					if(line.equals("")){
						break;
					}
					String[] arr = line.split("\\s+");
					String column = arr[0].replaceAll("<", "").replaceAll(">", "").trim();
					String first = column.substring(0, 1);
					String field = first.toLowerCase() + column.substring(1);
					String one = "private String " + field + ";" + "//" + arr[arr.length - 1].trim();
					list.add(one);
										
				}
				for(int i=0;i<list.size();i++){
					System.out.println(list.get(i));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(br != null){
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public static void createXMLString1(File file){
		if(!file.exists()){
			System.out.println(file.getName() + "文件不存在,请检查！");
		}else{
			BufferedReader br = null;
	//		List<String> list = null;
			
			try {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				String line = null;
	//			list = new ArrayList<String>();
				String s = "String s = \"<HXE>\" + head + \"<Body>";
				while((line = br.readLine()) != null){
					if(line.equals("")){
						break;
					}
					String[] arr = line.split("\\s+");
					String column = arr[0].replaceAll("<", "").replaceAll(">", "").trim();
					String first = column.substring(0, 1);
					String field2 = first.toLowerCase() + column.substring(1);
					String field = column;
					s += "\t\"<" + field + ">\" + " + field2 + " + \"</" + field + ">\" + \n";
										
				}
				s += "\t\"</Body></HXE>\";";
				s += "\n\tSystem.out.println(s);";
				System.out.println("String now = MyUtil.getCurrentDate();");
				System.out.println("String head = \"<Head><Identification>111</Identification><Channel>123</Channel><TrnxCode>ME001</TrnxCode><TrnxDatetime>\" + now + \"</TrnxDatetime></Head>\";");
				System.out.println(s);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(br != null){
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
