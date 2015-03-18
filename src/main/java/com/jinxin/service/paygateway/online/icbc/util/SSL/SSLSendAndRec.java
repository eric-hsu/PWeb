/*
 * Created on 2006-2-19
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.jinxin.service.paygateway.online.icbc.util.SSL;

/**
 * @author xiezhichao
 *  
 * this class is used to send and receive message through SSL
 */
import java.net.URL;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.protocol.Protocol;

public class SSLSendAndRec {

	/**
	 * 
	 */
	public SSLSendAndRec() {
		super();
	}

	public static void main(String[] args) throws Exception{
		String url = "https://corporbank3.dccnet.com.cn/servlet/ICBCINBSEBusinessServlet";
		
		Protocol authhttps = new Protocol("https",  
//AuthSSLProtocolSocketFactory参数含义：证书库，证书库，本地端口（建立https连接时使用的本地端口，需要空闲端口）

			    new AuthSSLProtocolSocketFactory(

			        new URL("file:///E:/银行接口文档/工行银行/B2C/Icbc.jks"), "password",

			        new URL("file:///E:/银行接口文档/工行银行/B2C/Icbc.jks"), "password"), 8443);

			HttpClient client = new HttpClient();
//对方ip或域名，端口(一般是443)，protocol对象
//			client.getHostConfiguration().setHost("114.225.225.101", 8443, authhttps);
			//client.getHostConfiguration().setHost("83.252.30.98",8890);
			/*只能使用相对路径*/
			client.getHostConfiguration().setHost("corporbank3.dccnet.com.cn", 443, authhttps);
			PostMethod httpget = new PostMethod("/servlet/ICBCINBSEBusinessServlet"); //   /servlet/ICBCINBSEBusinessServlet
			httpget.setParameter("APIName","EAPI");
			httpget.setParameter("APIVersion","001.001.005.001");
			//String sengmsg = URLEncoder.encode("<?xml  version=\"1.0\" encoding=\"GBK\" standalone=\"no\" ?><ICBCAPI><in><orderNum>A0011</orderNum><tranDate>20061103</tranDate><ShopCode>0200EC20000071</ShopCode><ShopAccount>0200029109000025233</ShopAccount></in></ICBCAPI>");
			httpget.setParameter("MerReqData","<?xml  version=\"1.0\" encoding=\"GBK\" standalone=\"no\" ?><ICBCAPI><in><orderNum>A0011</orderNum><tranDate>20061103</tranDate><ShopCode>0200EC20000071</ShopCode><ShopAccount>0200029109000025233</ShopAccount></in></ICBCAPI>");
			
			client.executeMethod(httpget);
			
			System.out.println("server responding body :" + httpget.getResponseBodyAsString());
			
			System.out.println("server responding code :" + httpget.getStatusLine().toString());
	}
}
