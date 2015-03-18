package com.jinxin.service.paygateway.online.cgb.work;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

import com.jinxin.model.online.OlineSendDataBean;
import com.jinxin.service.paygateway.OnlineWorker;
import com.jinxin.service.paygateway.online.cgb.util.CgbSendBean;
import com.jinxin.service.paygateway.online.cib.util.CibSendBean;
import com.jinxin.service.paygateway.online.cib.work.CibWorker;

/**
 * @className:CgbWorker.java
 * @classDescription:
 * @author:erichu
 * @createTime:2014-6-26 下午4:14:23
 */
@Component
public class CgbWorker implements OnlineWorker{
	 private static final Logger logger = Logger.getLogger(CgbWorker.class);
	@Override
	public String send(Map<String, String> params, OlineSendDataBean sendBean) {
		CgbSendBean cgbSendBean = null;

        // 把sendBean转换成IcbcSendBean
        if (sendBean instanceof CgbSendBean) {
        	cgbSendBean = (CgbSendBean) sendBean;
        } else {
            throw new RuntimeException("sendBean的类型不为IcbcSendBean");
        }
        String result = "";
    	HttpClient httpclient = new HttpClient();
		PostMethod method = new PostMethod(cgbSendBean.getServiceUrl());
		method.addRequestHeader( "Content-Type","application/x-www-form-urlencoded; charset=utf-8"); 
        try{
        	 NameValuePair[] nameValuePair = new NameValuePair[4];
             nameValuePair[0] = new NameValuePair("merchid", cgbSendBean.getMerNo());
             nameValuePair[1] = new NameValuePair("orderid", cgbSendBean.getTrNo());
             nameValuePair[2] = new NameValuePair("returl", cgbSendBean.getReturnurl());
             nameValuePair[3] = new NameValuePair("sign", cgbSendBean.getSign());
             method.setRequestBody(nameValuePair);
             // 设置通信超时时间为20秒（正常通信时间为1-2秒）
             method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60 * 1000);
             int returnInt = httpclient.executeMethod(method);
             String charSet =  method.getResponseCharSet();
             logger.info("charSet="+charSet+"   post result = " + returnInt);
             result = method.getResponseBodyAsString();
             result = new String(result.getBytes(charSet), "UTF-8");  
             if(returnInt==200){
            	 logger.info("查询信息发送成功，返回信息："+result);
             }else{
            	 logger.info("查询信息发送失败，返回码："+returnInt);
             }
        } catch (Exception ee) {
            logger.info("查询信息发送失败，返回码："+ee.getMessage());
        } finally {
            // 释放连接
        	method.releaseConnection();
        }
        
        logger.info(result.toString());
	   
        return result.toString();
	}

}
