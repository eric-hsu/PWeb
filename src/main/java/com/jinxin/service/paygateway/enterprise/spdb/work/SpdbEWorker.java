package com.jinxin.service.paygateway.enterprise.spdb.work;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.jinxin.model.online.OlineSendDataBean;
import com.jinxin.service.paygateway.OnlineWorker;
import com.jinxin.service.paygateway.enterprise.spdb.util.SpdbESendBean;
import com.jinxin.service.paygateway.online.spdb.util.SpdbSendBean;

/**
 * 浦发网关请求类
 * 
 * @author pengy
 * 
 */
@Component
public class SpdbEWorker  implements OnlineWorker{
    private static final Logger logger = Logger.getLogger(SpdbEWorker.class);
    // 1秒等于1000毫秒的单位
    private static final int TIME_UNIT = 1000;
    private static final int READ_TIME_OUT = 60 * 1000;

    /**
     * 给建行发送请求，把返回的信息转换成map并返回
     * 
     * @param sendXml
     * @param sendBean
     * @return
     */
    public String send(Map<String, String> params, OlineSendDataBean sendBean) {
    	SpdbESendBean spdbSendBean = null;

        // 把sendBean转换成ISpdbSendBean
        if (sendBean instanceof SpdbESendBean) {
        	spdbSendBean = (SpdbESendBean) sendBean;
        } else {
            throw new RuntimeException("sendBean的类型不为SpdbESendBean");
        }

        String result="";
        HttpClient httpclient = new HttpClient();
        PostMethod post = new PostMethod(spdbSendBean.getServiceUrl());
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
