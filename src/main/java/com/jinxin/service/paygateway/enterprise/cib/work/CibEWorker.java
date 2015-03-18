package com.jinxin.service.paygateway.enterprise.cib.work;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.jinxin.model.enterprise.EnterpriseSendDataBean;
import com.jinxin.model.online.OlineSendDataBean;
import com.jinxin.service.paygateway.EnterpriseWorker;
import com.jinxin.service.paygateway.OnlineWorker;
import com.jinxin.service.paygateway.enterprise.cib.util.CibESendBean;
import com.jinxin.service.paygateway.online.cib.util.CibSendBean;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;  
import java.net.UnknownHostException;  
import java.security.KeyManagementException;  
import java.security.KeyStore;  
import java.security.KeyStoreException;  
import java.security.NoSuchAlgorithmException;  
import java.security.UnrecoverableKeyException;  
import java.security.cert.CertificateException;  
import java.security.cert.X509Certificate;  
import java.util.HashMap;
import java.util.Map;
  
import javax.net.ssl.SSLContext;  
import javax.net.ssl.TrustManager;  
import javax.net.ssl.X509TrustManager; 
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;  
import org.apache.http.client.HttpClient;  
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;  
import org.apache.http.conn.scheme.PlainSocketFactory;  
import org.apache.http.conn.scheme.Scheme;  
import org.apache.http.conn.scheme.SchemeRegistry;  
import org.apache.http.conn.ssl.SSLSocketFactory;  
import org.apache.http.impl.client.DefaultHttpClient;  
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;  
import org.apache.http.params.BasicHttpParams;  
import org.apache.http.params.HttpConnectionParams;  
import org.apache.http.params.HttpParams;  
import org.apache.http.params.HttpProtocolParams;  
import org.apache.http.protocol.HTTP;  
import org.apache.http.util.EntityUtils;


/**
 * 兴业网关请求类
 * 
 * @author zhouyiqing
 * 
 */
@Component
public class CibEWorker  implements EnterpriseWorker{
    private static final Logger logger = Logger.getLogger(CibEWorker.class);
    // 1秒等于1000毫秒的单位
    private static final int TIME_UNIT = 1000;

    /**
     * 给工行发送请求，把返回的信息转换成map并返回
     * 
     * @param sendXml
     * @param sendBean
     * @return
     */
    public String send(Map<String, String> params, EnterpriseSendDataBean sendBean) {
    	CibESendBean cibESendBean = null;
        Map<String, Object> receiveMap = new HashMap<String, Object>();
        String receiveXml = "";   
        // 把sendBean转换成IcbcSendBean
        if (sendBean instanceof CibESendBean) {
        	cibESendBean = (CibESendBean) sendBean;
        } else {
            throw new RuntimeException("sendBean的类型不为IcbcSendBean");
        }
	    try{

	        HttpClient httpclient = newHttpsClient();
	        //获得HttpGet对象
	        HttpGet httpGet = null;
	        httpGet = new HttpGet(cibESendBean.getServiceUrl()+"?service="+cibESendBean.getService()+"&ver="+cibESendBean.getVer()+"&mrch_no="+cibESendBean.getMerNo()+"&ord_no="+cibESendBean.getTrNo()+"&mac="+cibESendBean.getSign());
	        //发送请求
	        HttpResponse response = httpclient.execute(httpGet);

	         
            if(response.getStatusLine().getStatusCode() == 200) {
            	receiveXml = EntityUtils.toString(
                		response.getEntity(), HTTP.UTF_8);
                logger.info(receiveXml);
            }
            
	        receiveMap.put("receiveXml", receiveXml.toString());
	        logger.info(receiveXml.toString());
	     }catch(Exception e){
	    	  e.printStackTrace();
	    	  logger.info(e.getMessage());
	     }
        return receiveXml.toString();
    }

    public static HttpClient newHttpsClient() {  
        try {  
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());  
            trustStore.load(null, null);  
  
            SSLSocketFactory sf = new MySSLSocketFactory(trustStore);  
            sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);  
  
            HttpParams params = new BasicHttpParams();  
            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);  
            HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);  
            HttpConnectionParams.setConnectionTimeout(params, 10000);  
            HttpConnectionParams.setSoTimeout(params, 10000);  
  
            SchemeRegistry registry = new SchemeRegistry();  
            registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));  
            registry.register(new Scheme("https", sf, 443));  
  
            ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);  
  
            return new DefaultHttpClient(ccm, params);  
        } catch (Exception e) {  
            return new DefaultHttpClient();  
        }  
    }  
  
    private static class MySSLSocketFactory extends SSLSocketFactory {  
        SSLContext sslContext = SSLContext.getInstance("TLS");  
  
        public MySSLSocketFactory(KeyStore truststore)  
                throws NoSuchAlgorithmException, KeyManagementException,  
                KeyStoreException, UnrecoverableKeyException {  
            super(truststore);  
  
            TrustManager tm = new X509TrustManager() {  
                public void checkClientTrusted(X509Certificate[] chain, String authType)  
                        throws CertificateException {  
                }  
  
                public void checkServerTrusted(X509Certificate[] chain, String authType)  
                        throws CertificateException {  
                }  
  
                public X509Certificate[] getAcceptedIssuers() {  
                    return null;  
                }  
            };  
  
            sslContext.init(null, new TrustManager[] { tm }, null);  
        }  
  
        @Override  
        public Socket createSocket(Socket socket, String host, int port, boolean autoClose)  
                throws IOException, UnknownHostException {  
            return sslContext.getSocketFactory().createSocket(socket, host, port, autoClose);  
        }  
  
        @Override  
        public Socket createSocket() throws IOException {  
            return sslContext.getSocketFactory().createSocket();  
        }  
    }

}
