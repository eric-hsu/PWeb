package com.jinxin.service.paygateway.online.bcm.work;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import com.jinxin.model.online.OlineSendDataBean;
import com.jinxin.service.paygateway.OnlineWorker;
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
 * 交通银行请求类
 * 
 * @author zhouyiqing
 * 
 */
@Component
public class BcmWorker  implements OnlineWorker{
    private static final Logger logger = Logger.getLogger(BcmWorker.class);
    // 1秒等于1000毫秒的单位
    private static final int TIME_UNIT = 1000;

    /**
     * 给交通银行发送请求，把返回的信息转换成map并返回
     * 
     * @param sendXml
     * @param sendBean
     * @return
     */
    public String send(Map<String, String> params, OlineSendDataBean sendBean) {
    	
        return "";
    }
}
