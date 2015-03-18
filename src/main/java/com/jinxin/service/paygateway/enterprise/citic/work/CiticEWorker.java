package com.jinxin.service.paygateway.enterprise.citic.work;

import java.util.Iterator;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.jinxin.model.enterprise.EnterpriseSendDataBean;
import com.jinxin.model.online.OlineSendDataBean;
import com.jinxin.service.paygateway.EnterpriseWorker;
import com.jinxin.service.paygateway.OnlineWorker;
import com.jinxin.service.paygateway.enterprise.citic.util.CiticESendBean;
import com.jinxin.service.paygateway.online.cmbc.util.CmbcSendBean;
import java.io.IOException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;


/**
 * 中信网关请求类
 * 
 * @author zhouyiqing
 * 
 */
@Component
public class CiticEWorker  implements EnterpriseWorker{
	 private static final Logger logger = Logger.getLogger(CiticEWorker.class);
	// 1秒等于1000毫秒的单位
	    private static final int TIME_UNIT = 1000;
	    private static final int READ_TIME_OUT = 60 * 1000;
	    /**
	     * 给工行发送请求，把返回的信息转换成map并返回
	     * 
	     * @param sendXml
	     * @param sendBean
	     * @return
	     */
	    public String send(Map<String, String> params, EnterpriseSendDataBean sendBean) {
	    	CiticESendBean citicESendBean = null;
	        if (sendBean instanceof CiticESendBean) {
	        	citicESendBean = (CiticESendBean) sendBean;
	        } else {
	            throw new RuntimeException("sendBean的类型不为IcbcSendBean");
	        }
	        String result="";
	        HttpClient httpclient = new HttpClient();
	        PostMethod post = new PostMethod(citicESendBean.getServiceUrl());
	        try {

	            Iterator<String> iterator = params.keySet().iterator();
	            while (iterator.hasNext()) {
	                String key = iterator.next();
	                NameValuePair nvp = new NameValuePair();
	                nvp.setName(key);
	                nvp.setValue(params.get(key));
	                post.addParameter(nvp);
	            }
	            
	            // 设置通信超时时间为20秒（正常通信时间为1-2秒）
	            post.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, READ_TIME_OUT);

	            int returnInt = httpclient.executeMethod(post);
	            logger.info("post result = " + returnInt);
	            result = post.getResponseBodyAsString();
	            logger.info("call back result=" + result);
	            
	        } catch (HttpException he) {
	            he.printStackTrace();
	        } catch (IOException ie) {
	            ie.printStackTrace();
	        } catch (Exception ee) {
	            ee.printStackTrace();
	        } finally {
	            // 释放连接
	            post.releaseConnection();
	        }
	        
	        return result;
	    }
}
