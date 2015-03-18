package com.jinxin.common.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;


/**
 * 
 * @author ivan
 * 
 */
public class PayCallBack implements Runnable {
    // 日志
    /**
     * Automatically generated variable: TIME_MIN
     */
    private static final int TIME_MIN = 60000;
    private static final int READ_TIME_OUT = 60 * 1000;
    private static final Logger LOG = Logger.getLogger(PayCallBack.class);

    private static final String XMLSTR = "xmlStr";

    private Map<String, String> map = new HashMap<String, String>();

    /**
     * 最大轮询次数
     */
    private int cycleCount = 5;

    /**
     * 回调Url
     */
    private String redirectURL;
    /**
     * 回调xml格式字符串
     */
    private String postXml;


    public PayCallBack() {

    }

    public PayCallBack(String redirectURL, String postXml) {
        this.redirectURL = redirectURL;
        this.postXml = postXml;
    }


    public void run() {
        LOG.info("redirectURL=" + redirectURL);
        HttpClient httpclient = new HttpClient();
        PostMethod post = new PostMethod(redirectURL);
        try {
            NameValuePair[] nameValuePair = new NameValuePair[1];
            String poststr = URLEncoder.encode(this.postXml, "UTF-8");
            nameValuePair[0] = new NameValuePair(XMLSTR, poststr);
            LOG.info("postxml=" + poststr);
            post.setRequestBody(nameValuePair);
            // 设置通信超时时间为20秒（正常通信时间为1-2秒）
            post.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, READ_TIME_OUT);
            // 最多轮询cycleCount次
            for (int i = 0; i < cycleCount; i++) {
                // 循环次数间隔，第i*60秒
                Thread.sleep(i * TIME_MIN);
                int returnInt = httpclient.executeMethod(post);
                LOG.info("post result = " + returnInt);
                String result = post.getResponseBodyAsString();
                LOG.info("call back result=" + result);
                // 如果连接成功，循环结束
                if ("S".equals(result)) {
                    LOG.info("回调成功1");
                    break;
                }else if(returnInt == 200 && !"F".equals(result)){
                    LOG.info("回调成功2");
                    break;
                }
            }
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
    
    
    public static void main(String[] args){
    	
    	
         HttpClient httpclient = new HttpClient();
         PostMethod post = new PostMethod("http://172.18.2.15:80/PWeb/payment/backNotifyResult.do");
         try {
        	 
        	 String ip = InetAddress.getLocalHost().getHostAddress();
        	 LOG.info("ip=" + ip);
        	 
        	 String poststr = "%3C%3Fxml+version%3D%221.0%22+encoding%3D%22UTF-8%22%3F%3E%0A%3Crespon%3E%3CmerNo%3E10000%3C%2FmerNo%3E%3CgatewayNo%3E10000001%3C%2FgatewayNo%3E%3CtradeNo%3E20141105180336939150%3C%2FtradeNo%3E%3CorderNo%3E20141105180335671452%3C%2ForderNo%3E%3CorderAmount%3E0.01%3C%2ForderAmount%3E%3CorderCurrency%3ECNY%3C%2ForderCurrency%3E%3CorderStatus%3E1%3C%2ForderStatus%3E%3CorderInfo%3E%E6%94%AF%E4%BB%98%E6%BC%94%E7%A4%BA%E8%AE%A2%E5%8D%95%3C%2ForderInfo%3E%3CsignInfo%3ED4BC7651B02186840BCD3B86AFADB1B02D5B896950BF978BFFCE298DDB70439C%3C%2FsignInfo%3E%3Cremark%3E%E6%94%AF%E4%BB%98%E6%BC%94%E7%A4%BA%E8%AE%A2%E5%8D%95%3C%2Fremark%3E%3C%2Frespon%3E";
             NameValuePair[] nameValuePair = new NameValuePair[1];
             nameValuePair[0] = new NameValuePair(XMLSTR, poststr);
             LOG.info("postxml=" + poststr);
             post.setRequestBody(nameValuePair);
             // 设置通信超时时间为20秒（正常通信时间为1-2秒）
             post.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, READ_TIME_OUT);
             // 最多轮询cycleCount次
             for (int i = 0; i < 4; i++) {
                 // 循环次数间隔，第i*60秒
                 Thread.sleep(i * TIME_MIN);
                 int returnInt = httpclient.executeMethod(post);
                 LOG.info("post result = " + returnInt);
                 String result = post.getResponseBodyAsString();
                 LOG.info("call back result=" + result);
                 // 如果连接成功，循环结束
                 if ("S".equals(result)) {
                     LOG.info("回调成功1");
                     break;
                 }else if(returnInt == 200 && !"F".equals(result)){
                     LOG.info("回调成功2");
                     break;
                 }
             }
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
