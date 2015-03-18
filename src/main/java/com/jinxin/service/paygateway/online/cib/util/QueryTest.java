package com.jinxin.service.paygateway.online.cib.util;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
  
import javax.net.ssl.SSLContext;  
import javax.net.ssl.TrustManager;  
import javax.net.ssl.X509TrustManager; 

import org.apache.commons.lang.StringUtils;
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
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import com.jinxin.service.paygateway.online.cib.work.CibWorker;

public class QueryTest {
	 private static final Logger logger = Logger.getLogger(QueryTest.class);

	 public static HttpClient newHttpsClient() {  
        try {  
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());  
            trustStore.load(null, null);  
  
            SSLSocketFactory sf = new MySSLSocketFactory(trustStore);  
            sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);  
  
            HttpParams params = new BasicHttpParams();  
            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);  
            HttpProtocolParams.setContentCharset(params, HTTP.ISO_8859_1);  
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
    
    public static void main(String[] args){
    	
//		1， 查询测试   	
//    	String service = CibConstants.QUERYSERVICE;
//		String ver = CibConstants.VERSION;
//		String mrch_no =CibConstants.MRCHNO;
//		String ord_no = "20140905115309823014";
//		
//		String signstr = "service"+service+"ver"+ver+"mrch_no"+mrch_no+"ord_no"+ord_no+CibConstants.KEY;
//		String sign = EncryptUtil.getMD5Encrypt(signstr);
//    	
//    	CibSendBean cibSendBean = new CibSendBean();
//		cibSendBean.setServiceUrl(CibConstants.QUERYURL);
//		cibSendBean.setService(service);
//		cibSendBean.setVer(ver);
//		cibSendBean.setMerNo(mrch_no);
//		cibSendBean.setTrNo(ord_no);
//		cibSendBean.setSign(sign);
//    	
//		
//		  Map<String, Object> receiveMap = new HashMap<String, Object>();
//
//	      try{
//
//	        HttpClient httpclient = newHttpsClient();
//	        //获得HttpGet对象
//	        HttpGet httpGet = null;
//	        httpGet = new HttpGet(cibSendBean.getServiceUrl()+"?service="+cibSendBean.getService()+"&ver="+cibSendBean.getVer()+"&mrch_no="+cibSendBean.getMerNo()+"&ord_no="+cibSendBean.getTrNo()+"&mac="+cibSendBean.getSign());
//	        //发送请求
//	        HttpResponse response = httpclient.execute(httpGet);
//	        //输出返回值
//	       /* InputStream is = response.getEntity().getContent();
//	        BufferedReader br = new BufferedReader(new InputStreamReader(is));
//	        String line = "";
//	        while((line = br.readLine())!=null){
//	           logger.info(line);
//	        }*/
//	        
//	        String lrcLine = null;    
//            if(response.getStatusLine().getStatusCode() == 200) {
//                lrcLine = EntityUtils.toString(
//                		response.getEntity(), HTTP.UTF_8);
//               // writer.write(lrcLine);
//               // Log.v(TAG, lrcLine);
//                logger.info(lrcLine);
//            }
//	        
//	      }catch(Exception e){
//	    	  e.printStackTrace();
//	    	  logger.info(e.getMessage());
//	      }

//		2，对账测试
    	String url=CibConstants.QUERYURL;
    	String service = "cib.netpay.receipt.payment";
		String ver = "01";
		String mrch_no ="110016";
		String begin_time="20140909111010";
		String end_time="20140909121010";
		String start_idx ="0";
		String page_size ="200";
		String signstr = "service"+service+"ver"+ver+"mrch_no"+mrch_no+"begin_time"+begin_time+"end_time"+end_time+"start_idx"+start_idx+"page_size"+page_size+CibConstants.KEY;
		String mac = EncryptUtil.getMD5Encrypt(signstr);
		
		  Map<String, Object> receiveMap = new HashMap<String, Object>();

	      try{

	        HttpClient httpclient = newHttpsClient();
	        //获得HttpGet对象
	        HttpGet httpGet = null;
	        httpGet = new HttpGet(url+"?service="+service+"&ver="+ver+"&mrch_no="+mrch_no+"&begin_time="+begin_time+"&end_time="+end_time+"&start_idx="+start_idx+"&page_size="+page_size+"&mac="+mac);
	        //发送请求
	        HttpResponse response = httpclient.execute(httpGet);

	        String lrcLine = null;    
	        if(response.getStatusLine().getStatusCode() == 200) {
	             lrcLine = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
	              //logger.info(lrcLine);
	         }
	        List<Map<String,String>> list = paraxml(lrcLine);
	        logger.info("size="+list.size());
	      }catch(Exception e){
	    	  e.printStackTrace();
	    	  logger.info(e.getMessage());
	      }
    }
    
    public static List<Map<String,String>> paraxml(String requestXml){
    	 List<Map<String,String>> list = new ArrayList<Map<String,String>>();
    	 Map<String,String> map = new HashMap<String,String>();
    	 try {
             Element rootElement = getRootElement(requestXml);
             List<Element> elementList = rootElement.elements("page");
             System.out.println("page size = "+elementList.size());
             for (Element element : elementList) {
            	 List<Element> el =  element.elements("items");
            	 System.out.println("items size = "+el.size());
            	 for(Element e2 : el){
            		 List<Element> e3 =  e2.elements("PaymentLog");
            		 System.out.println("PaymentLog size = "+e3.size());
                	 for(Element e4 : e3){
                		 List<Node> nodeList = e4.elements();
                         System.out.println("nodeList size = "+nodeList.size());
                         for (Node node : nodeList) {
                             String text = node.getText();
                             if(StringUtils.isNotEmpty(text)){
                                 text = text.trim();
                             }
                             System.out.println("====node.getName()====" + node.getName() + "====" + text);
                             map.put(node.getName(), text);
                         }
                         list.add(map);
                	 }
            	 }
            	
            	
             }
            
         } catch (Exception e) {
             e.printStackTrace();
         }
    	 return list;
    	
    }
    
    /**
     * 获取xml根节点
     * 
     * @param requestXml
     * @return
     */
    private static Element getRootElement(String requestXml) {
        Element rootElement = null;
        try {
            Document doc = DocumentHelper.parseText(requestXml);
            System.out.println(doc.asXML());
            rootElement = doc.getRootElement();
        } catch (DocumentException de) {
            de.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rootElement;
    }
}
