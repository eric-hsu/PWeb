package com.jinxin.service.paygateway.online.icbc.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import com.jinxin.service.paygateway.online.icbc.util.IcbcConstants;
import com.jinxin.service.paygateway.online.icbc.util.SSL.SSLSendAndRec;

public class TestQuery {
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
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			String xml = "<?xml  version=\"1.0\" encoding=\"GBK\" standalone=\"no\" ?><ICBCAPI><in><orderNum>20140909111659990300</orderNum><tranDate>20140909s</tranDate><ShopCode>4000EC24086825</ShopCode><ShopAccount>4000023029200124946</ShopAccount></in></ICBCAPI>";
			String sengXml = URLEncoder.encode(xml);

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
                
               
                URL realUrl = new URL("https://corporbank3.dccnet.com.cn/servlet/ICBCINBSEBusinessServlet?APIName=EAPI&APIVersion=001.001.002.001&MerReqData="+sengXml);
               
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
                System.out.println(""+result);
                result = URLDecoder.decode(result, "UTF-8");
                System.out.println(""+result);

            }
            catch(Exception e)
            {
                System.out.println("发送POST请求出现异常！" + e);
                e.printStackTrace();
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
	}

}
