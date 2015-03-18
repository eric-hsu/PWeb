package com.jinxin.service.paygateway.online.cmbc.work;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import Union.JnkyServer;

import com.jinxin.model.online.OlineSendDataBean;
import com.jinxin.service.paygateway.OnlineWorker;
import com.jinxin.service.paygateway.online.cib.util.CibSendBean;
import com.jinxin.service.paygateway.online.cmbc.util.CmbcConstants;
import com.jinxin.service.paygateway.online.cmbc.util.CmbcSendBean;

/**
 * 华夏网关请求类
 * 
 * @author zhouyiqing
 * 
 */
@Component
public class CmbcWorker  implements OnlineWorker{
    private static final Logger logger = Logger.getLogger(CmbcWorker.class);
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
    public String send(Map<String, String> params, OlineSendDataBean sendBean) {
    	CmbcSendBean cmbcSendBean = null;
        if (sendBean instanceof CmbcSendBean) {
        	cmbcSendBean = (CmbcSendBean) sendBean;
        } else {
            throw new RuntimeException("sendBean的类型不为IcbcSendBean");
        }
        String result="";
        HttpClient httpclient = new HttpClient();
        PostMethod post = new PostMethod(cmbcSendBean.getServiceUrl());
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
    
    
    public static void main(String[] args){
    	
    	HttpClient httpclient = new HttpClient();
        PostMethod post = new PostMethod("http://111.205.207.118:55003/epay/connectForQuery.do");
        try {
       	
        	
        	String queryinfo = "20141203092820408160|"+CmbcConstants.corpID;
			
			URL cerFile = CmbcConstants.class.getResource("1024new.cer");
		    String certFilePath = cerFile.getPath().replace("%20", " ");
		    
		    URL pfxFile = CmbcConstants.class.getResource("ceshi.pfx");
		    String pfxFilePath = pfxFile.getPath().replace("%20", " ");
		    logger.info("certFilePath=["+certFilePath+"]");

			JnkyServer jnkyserver=new JnkyServer(certFilePath, pfxFilePath, CmbcConstants.pxfpassword);
			
			String  cryptograph= jnkyserver.EnvelopData(queryinfo,"utf-8");
			

            NameValuePair[] nameValuePair = new NameValuePair[1];
            nameValuePair[0] = new NameValuePair("cryptograph", cryptograph);
            post.setRequestBody(nameValuePair);
            // 设置通信超时时间为20秒（正常通信时间为1-2秒）
            post.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, READ_TIME_OUT);

            int returnInt = httpclient.executeMethod(post);
            logger.info("post result = " + returnInt);
            String result = post.getResponseBodyAsString();
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
    }
}
