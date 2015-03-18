package com.jinxin.service.paygateway.online.icbc.util;

import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

public class Test {
	private static final Logger logger = Logger.getLogger(Test.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url ="http://119.147.217.148/PWeb/icbcController/backNotify.do";
		String  notifyData= "PD94bWwgIHZlcnNpb249IjEuMCIgZW5jb2Rpbmc9IkdCSyIgc3RhbmRhbG9uZT0ibm8iID8+PEIyQ1Jlcz48aW50ZXJmYWNlTmFtZT5JQ0JDX1BFUkJBTktfQjJDPC9pbnRlcmZhY2VOYW1lPjxpbnRlcmZhY2VWZXJzaW9uPjEuMC4wLjExPC9pbnRlcmZhY2VWZXJzaW9uPjxvcmRlckluZm8+PG9yZGVyRGF0ZT4yMDE0MDkyMDE1MTMzNTwvb3JkZXJEYXRlPjxjdXJUeXBlPjAwMTwvY3VyVHlwZT48bWVySUQ+NDAwMEVDMjQwODY4MjU8L21lcklEPjxzdWJPcmRlckluZm9MaXN0PjxzdWJPcmRlckluZm8+PG9yZGVyaWQ+MjAxNDA5MDExNTEzMzMzNTI1MTg8L29yZGVyaWQ+PGFtb3VudD4xPC9hbW91bnQ+PGluc3RhbGxtZW50VGltZXM+MTwvaW5zdGFsbG1lbnRUaW1lcz48bWVyQWNjdD40MDAwMDIzMDI5MjAwMTI0OTQ2PC9tZXJBY2N0Pjx0cmFuU2VyaWFsTm8+SEZHMDAwMDA0NzMyNjczMzMzPC90cmFuU2VyaWFsTm8+PC9zdWJPcmRlckluZm8+PC9zdWJPcmRlckluZm9MaXN0Pjwvb3JkZXJJbmZvPjxjdXN0b20+PHZlcmlmeUpvaW5GbGFnPjA8L3ZlcmlmeUpvaW5GbGFnPjxKb2luRmxhZz48L0pvaW5GbGFnPjxVc2VyTnVtPjwvVXNlck51bT48L2N1c3RvbT48YmFuaz48VHJhbkJhdGNoTm8+PC9UcmFuQmF0Y2hObz48bm90aWZ5RGF0ZT4yMDE0MDkyMDE1MTMwNzwvbm90aWZ5RGF0ZT48dHJhblN0YXQ+MTwvdHJhblN0YXQ+PGNvbW1lbnQ+vbvS17PJuaajoTwvY29tbWVudD48L2Jhbms+PC9CMkNSZXM+";
		String  signMsg = "KAVEGIkUb76EhqR3xxDbFQSf3S2Oa1HbdPymmgYEBBAWdU8DDz7Sia4ml5gnQbMj22/YRCJ8H7g0lP95Qi21LmstFIHZLWDbWraq94zsNe26wMsLlhO7IJjP0f5lh6RW1dWBOoCozylynKhcOZcZoR1WnAPfLUkKHefjnNhQxbM=";
		HttpClient httpclient = new HttpClient();
        PostMethod method = new PostMethod(url);
        method.addRequestHeader( "Content-Type","application/x-www-form-urlencoded; charset=utf-8"); 
        try {
        	 NameValuePair[] nameValuePair = new NameValuePair[2];
             nameValuePair[0] = new NameValuePair("notifyData", notifyData);
             nameValuePair[1] = new NameValuePair("signMsg", signMsg);
             method.setRequestBody(nameValuePair);
             method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60 * 1000);
             int returnInt = httpclient.executeMethod(method);
             String charSet =  method.getResponseCharSet();
             logger.info("charSet="+charSet+"   post result = " + returnInt);
             String result = method.getResponseBodyAsString();
             result = new String(result.getBytes(charSet), "UTF-8");  
             if(returnInt==200){
            	if(!"s".equals(result)){
            		logger.info("操作失败！");
            	}else{
            		 logger.info("操作成功");
            	}
             }else{
            	 logger.info("同步信息发送失败，返回码："+returnInt);
             }
        } catch (Exception ee) {
            ee.printStackTrace();
       	 	logger.info("同步信息发送失败，返回码："+ee.getMessage());
        } finally {
            // 释放连接
        	method.releaseConnection();
        }
	}

}
