package com.jinxin.service.paygateway.online.citic.work;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import com.jinxin.model.online.OlineSendDataBean;
import com.jinxin.service.paygateway.OnlineWorker;
import com.jinxin.service.paygateway.online.cib.util.CibSendBean;
import com.jinxin.service.paygateway.online.citic.util.CiticSendBean;
import com.jinxin.service.paygateway.online.citic.util.citicUtil.HttpsProSocketFactory;
import java.io.IOException;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;



/**
 * 中信网关请求类
 * 
 * @author zhouyiqing
 * 
 */
@Component
public class CiticWorker  implements OnlineWorker{
	 private static final Logger logger = Logger.getLogger(CiticWorker.class);
	    // 1秒等于1000毫秒的单位
	    private static final int TIME_UNIT = 1000;
	    private static final int READ_TIME_OUT = 60 * 1000;
	    public static String encoding = "GBK";

	    private static final HttpConnectionManager connectionManager;
	    
	    private static final HttpClient client;

	    static {

	    	HttpConnectionManagerParams params = loadHttpConfFromFile();
	    	
	    	connectionManager = new MultiThreadedHttpConnectionManager();

	        connectionManager.setParams(params);

	        client = new HttpClient(connectionManager);
	    }
	    
	    private static HttpConnectionManagerParams loadHttpConfFromFile(){
			
			HttpConnectionManagerParams params = new HttpConnectionManagerParams();
	        params.setConnectionTimeout(15000);
	        params.setSoTimeout(30000);
	        params.setStaleCheckingEnabled(Boolean.parseBoolean("true"));
	        params.setTcpNoDelay(Boolean.parseBoolean("true"));
	        params.setDefaultMaxConnectionsPerHost(100);
	        params.setMaxTotalConnections(1000);
	        params.setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(0, false));
			return params;
	    }
	    /**
	     * 给中信发送请求，把返回的信息转换成map并返回
	     * 
	     * @param sendXml
	     * @param sendBean
	     * @return
	     */
	    public String send(Map<String, String> params, OlineSendDataBean sendBean) {
	    	CiticSendBean citicSendBean = null;
	    	
	    	Protocol myhttps = new Protocol("https", new HttpsProSocketFactory(), 443);
	        Protocol.registerProtocol("https", myhttps);
	         
	        // 把sendBean转换成ISpdbSendBean
	        if (sendBean instanceof CiticSendBean) {
	        	citicSendBean = (CiticSendBean) sendBean;
	        } else {
	            throw new RuntimeException("sendBean的类型不为SpdbSendBean");
	        }

	        String result="";
	        HttpClient httpclient = new HttpClient();
	        PostMethod post = new PostMethod(citicSendBean.getServiceUrl());
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
	            post.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
	            
	            int returnInt = client.executeMethod(post);
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
