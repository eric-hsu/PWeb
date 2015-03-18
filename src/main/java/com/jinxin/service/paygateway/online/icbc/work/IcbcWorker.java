package com.jinxin.service.paygateway.online.icbc.work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.jinxin.model.online.OlineSendDataBean;
import com.jinxin.service.paygateway.OnlineWorker;
import com.jinxin.service.paygateway.online.cib.util.CibSendBean;
import com.jinxin.service.paygateway.online.cmbc.util.CmbcSendBean;
import com.jinxin.service.paygateway.online.icbc.impl.IcbcPayGatewayProxyImpl;
import com.jinxin.service.paygateway.online.icbc.util.IcbcConstants;
import com.jinxin.service.paygateway.online.icbc.util.IcbcSendBean;
import com.jinxin.service.paygateway.online.icbc.util.IcbcUtils;
import com.jinxin.service.paygateway.online.icbc.util.SSL.AuthSSLProtocolSocketFactory;
import com.jinxin.service.paygateway.online.icbc.util.SSL.SSLSendAndRec;

/**
 * 工行网关请求类
 * 
 * @author zhouyiqing
 * 
 */

@Component
public class IcbcWorker  implements OnlineWorker{
    private static final Logger logger = Logger.getLogger(IcbcWorker.class);
    // 1秒等于1000毫秒的单位
    private static final int TIME_UNIT = 1000;
    /**
     * 所有主机默认通过
     */
	private static HostnameVerifier hnv = new HostnameVerifier() {
          public boolean verify(String hostname, SSLSession session) {
           return true;
          }
   };
   /**
    * 关键在这信任所有证书
    */      
   private static TrustManager[] trustAllCerts = new TrustManager[] {
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    return;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    return;
                }
            }//X509TrustManager
   };//TrustManager[]
   
    /**
     * 给工行发送请求，把返回的信息转换成map并返回
     * 
     * @param sendXml
     * @param sendBean
     * @return
     */
   public String send(Map<String, String> params, OlineSendDataBean sendBean) {
    	
    	IcbcSendBean icbcSendBean = null;
        if (sendBean instanceof CmbcSendBean) {
        	icbcSendBean = (IcbcSendBean) sendBean;
        } else {
            throw new RuntimeException("sendBean的类型不为IcbcSendBean");
        }
        

		String sendXml = URLEncoder.encode(IcbcUtils.createQueryXml(icbcSendBean));
        //set up a connection
        SSLSocketFactory ssf=null;

        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try
        {
            //init context
            SSLContext ctx=SSLContext.getInstance("TLS");           
            KeyManagerFactory kmf=KeyManagerFactory.getInstance("SunX509");
            TrustManagerFactory tmf=TrustManagerFactory.getInstance("SunX509");           
            KeyStore ks=KeyStore.getInstance("PKCS12");

            //load keystore

            InputStream pfxStream=SSLSendAndRec.class.getResourceAsStream(IcbcConstants.PFXNAME);

            ks.load(pfxStream,IcbcConstants.PFXPASSWORD.toCharArray());
           
            kmf.init(ks,IcbcConstants.PFXPASSWORD.toCharArray());
           
            ctx.init(kmf.getKeyManagers(),trustAllCerts,null);
           
            ssf=ctx.getSocketFactory();
           
            HttpsURLConnection.setDefaultSSLSocketFactory(ssf);

            HttpsURLConnection.setDefaultHostnameVerifier(hnv);
            
           
            URL realUrl = new URL(IcbcConstants.QUERYURL+"?APIName=EAPI&APIVersion=001.001.002.001&MerReqData="+sendXml);
           
            //打开和URL之间的连接
            HttpsURLConnection conn = (HttpsURLConnection) realUrl.openConnection();

            //设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
           
            //发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            //发送请求参数
            //out.print(sengmsg);
            //flush输出流的缓冲
            out.flush();
            //定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
            new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine())!= null)
            {
                result += "" + line;
            }
            result = URLDecoder.decode(result, "UTF-8");
            logger.info(result);

        }
        catch(Exception e)
        {
            e.printStackTrace();
            logger.error(e);
            throw new RuntimeException("工行查询请求Http异常[" + e.getMessage() + "]");
        }
        //使用finally块来关闭输出流、输入流
        finally
        {
            try
            {
                if (out != null)
                {
                    out.close();
                }
                if (in != null)
                {
                    in.close();
                }
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        return result;
    }
}
