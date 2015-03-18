package com.jinxin.service.paygateway.online.hxb.util.hxbUtil;

import java.io.IOException;
import java.util.ResourceBundle;



public class SecurityUtil {
	private  static String socketip;
	private  static String socketport;
	private  static String signport;
	private  static String url; 
	static{
		ResourceBundle rb = ResourceBundle.getBundle("Socket");
		socketip = rb.getString("ip");
		socketport = rb.getString("port");
		signport = rb.getString("port1");
		url = rb.getString("url");
	}
	
	private static String signMessage;
	private static String cretDN;
	private static String sicString;
	private static final String newline = "\r\n";
	

	public static String sign(final String message){ 
		int bodyLen = message.getBytes().length;
		StringBuffer sb = new StringBuffer();
		sb.append("POST HTTP://"+socketip+"/servlet/ABC95599.Trans HTTP/1.0");
		sb.append(newline);
		sb.append("Accept:image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/vnd.ms-powerpoint, application/vnd.ms-excel, application/msword, */*");
		sb.append(newline);
		sb.append("Referer: HTTP://"+socketip+"/personRACrtLY.htm");
		sb.append(newline);
		sb.append("Accept-Language: zh-cn");
		sb.append(newline);
		sb.append("Content-Type: INFOSEC_SIGN/1.0");
		sb.append(newline);
		sb.append("Proxy-Connection: Keep-Alive");
		sb.append(newline);
		sb.append("User-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
		sb.append(newline);
		sb.append("Host: "+socketip+":"+signport);
		sb.append(newline);
		sb.append("Content-Length: " + bodyLen);
		sb.append(newline);
		sb.append("Pragma: no-cache");
		sb.append(newline);
		sb.append(newline);
		sb.append(message);
		String result = null;
		try {
			signMessage = new String(SocketClient.sendInfo(sb.toString().getBytes(), 120*1000, socketip, Integer.parseInt(signport)));
			XMLDocument xml = new XMLDocument(signMessage);
			result = xml.getValueNoNull("result");
			signMessage = xml.getValueNoNull("sign");
		} catch (IOException e) {
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public static String verifysign(String signMessage){
		int bodyLen = signMessage.getBytes().length;
		StringBuffer sb = new StringBuffer();
		sb.append("POST HTTP://"+socketip+"/servlet/ABC95599.Trans HTTP/1.0");
		sb.append(newline);
		sb.append("Accept:image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/vnd.ms-powerpoint, application/vnd.ms-excel, application/msword, */*");
		sb.append(newline);
		sb.append("Referer: HTTP://"+socketip+"/personRACrtLY.htm");
		sb.append(newline);
		sb.append("Accept-Language: zh-cn");
		sb.append(newline);
		sb.append("Content-Type: INFOSEC_VERIFY_SIGN/1.0");
		sb.append(newline);
		sb.append("Proxy-Connection: Keep-Alive");
		sb.append(newline);
		sb.append("User-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
		sb.append(newline);
		sb.append("Host: "+socketip+":"+signport);
		sb.append(newline);
		sb.append("Content-Length: " + bodyLen);
		sb.append(newline);
		sb.append("Pragma: no-cache");
		sb.append(newline);
		sb.append(newline);
		sb.append(signMessage);
		String result = null;
		try {
			signMessage = new String(SocketClient.sendInfo(sb.toString().getBytes(), 120*1000, socketip, Integer.parseInt(signport)));
			XMLDocument xml = new XMLDocument(signMessage);
			result = xml.getValueNoNull("result");
			cretDN = xml.getValueNoNull("certdn");
			sicString = xml.getValueNoNull("sic");
		} catch (IOException e) {
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}
		return result; 
	}
		
	public static String ncClient(String message){
		int bodyLen = message.getBytes().length;
		StringBuffer sb = new StringBuffer();
		sb.append(message);
		String respMessage = null;
		try {
			respMessage = new String(SocketClient.sendInfo(sb.toString().getBytes(), 30*1000, socketip, Integer.parseInt(socketport)));			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return respMessage;
	}
	
	public static String ncClientHttp(String message){
		int bodyLen = message.getBytes().length;
		StringBuffer sb = new StringBuffer();
		sb.append("POST "+url+" HTTP/1.0");
		sb.append(newline);
		sb.append("Accept:image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/vnd.ms-powerpoint, application/vnd.ms-excel, application/msword, */*");
		sb.append(newline);
		sb.append("Referer: HTTP://"+socketip+"/personRACrtLY.htm");
		sb.append(newline);
		sb.append("Accept-Language: zh-cn");
		sb.append(newline);
		sb.append("Content-Type: INFOSEC_VERIFY_SIGN/1.0");
		sb.append(newline);
		sb.append("Proxy-Connection: Keep-Alive");
		sb.append(newline);
		sb.append("User-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
		sb.append(newline);
		sb.append("Host: "+socketip+":"+signport);
		sb.append(newline);
		sb.append("Content-Length: " + bodyLen);
		sb.append(newline);
		sb.append("Pragma: no-cache");
		sb.append(newline);
		sb.append(newline);
		sb.append(message);
		String respMessage = null;
		try {
			respMessage = new String(SocketClient.sendInfo(sb.toString().getBytes(), 30*1000, socketip, Integer.parseInt(socketport)));			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return respMessage;
	}
	
	public static String getSignMessage() {
		return signMessage;
	}

	public static String getCretDN() {
		return cretDN;
	}

	public static String getSicString() {
		return sicString;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {	
		System.out.println(SecurityUtil.ncClientHttp("aaa"));
		System.out.println(SecurityUtil.getSignMessage());
//		System.out.println(SecurityUtil.verifysign(SecurityUtil.getSignMessage()));
//		System.out.println(SecurityUtil.getCretDN());
//		System.out.println(SecurityUtil.getSicString());

//		System.out.println("@@@@"+SecurityUtil.verifysign(SecurityUtil.getSignMessage()));
//		System.out.println(SecurityUtil.getSicString());
	}

}
