package com.jinxin.service.paygateway.online.citic.util.citicUtil;

import java.net.URL;
import java.security.cert.X509Certificate;
import com.jinxin.service.paygateway.online.citic.util.CiticUtils;

public class CNCBCryptoUtilTest {
	
	public CNCBCryptoUtilTest(CNCBCryptoUtil util)throws Exception{
		URL classFile = CNCBCryptoUtil.class.getResource("");
   		String cerPath = classFile.getPath().replace("%20", " ");
   		System.out.println("cerPath="+cerPath);
		String signercrt =CiticUtils.readFileToString(cerPath+"/ecclient.cer");
    	String signerkey =CiticUtils.readFileToString(cerPath+"/ecclient.key");
    	String keypasswd = CiticUtils.readFileToString(cerPath+"/ecclient.pwd");
    	util.getMethodOfSetSignerCertificate().invoke(util.getObjectOfECCryptoProcessor(), new Object[]{signercrt.getBytes()});
    	util.getMethodOfSetSignerPrivatekey().invoke(util.getObjectOfECCryptoProcessor(), new Object[]{signerkey.getBytes(), keypasswd});
    	String trustedCrt = "MIIDYzCCAkugAwIBAgIBMDANBgkqhkiG9w0BAQUFADBMMSwwKgYDVQQKDCNDSElOQSBDSVRJQyBCQU5LIENPUlBPUkFUSU9OIExJTUlURTEcMBoGA1UEAwwTQ05DQiBFQyBURVNUIFNFUlZFUjAeFw0wOTA1MjAxMTA3NDhaFw0yOTA1MTUxMTA3NDhaMEwxLDAqBgNVBAoMI0NISU5BIENJVElDIEJBTksgQ09SUE9SQVRJT04gTElNSVRFMRwwGgYDVQQDDBNDTkNCIEVDIFRFU1QgU0VSVkVSMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAx66DA5hrSuSmYj/s6dbiexVSGqswPJbo7JdDDRuyFvZYmejdJBRt0wq1TfTgxVn++sEKByOOYGgZebpOM3/mRfz9QoRm8YqMP5HXHQSNyLnUJ4JfxT5XkFRIC7Gk3eKZmv+fVxRSc09zgsGrS45MgJlQdOo3Ckv1NgV4lBDqHK0EkKthVasQ6tAD5sVQLGHodanwnYa+/4VswHnfpj7z0n6rV1zP4ZAJATNyVOSNRZK2/q7Mf61ypAXKSLTDA/ijnZor7uFMKQsU/V+elK3TeZHG+KZ3pYiG8d0ZDNVjb66Zc4JWuM613uZntt1grI1BRMjIOc2H2Lmp9lx8bJLbeQIDAQABo1AwTjAdBgNVHQ4EFgQUT0mNmQzHwVOFbRJ7dIySrHx0HJUwHwYDVR0jBBgwFoAUT0mNmQzHwVOFbRJ7dIySrHx0HJUwDAYDVR0TBAUwAwEB/zANBgkqhkiG9w0BAQUFAAOCAQEAb+bHgKuswiOdfGpGrw/UttBrwccfBzHo9N/5UYvdoAuchAA893GhQeTXjE1xycfqv3fvRulnnvB8EjwCYfFknxNL9bRwDqK0SpqtyC36h9o0Z8BgItfeehmnYzgtuUnkutyscX2bPj8SmILA5t88cUJYpAPZKvM7QlX6ClM3OCyVxnmYEJmNbXiG7Q+SxtWakPC9pK2OFzeRJGitDBypE/NusbCOqIcaSbqoLl/EuGkpDZPtNlIAtzn8j3cauCeEu4wxDZmGU7oHFPYpeXTzAuqU4sQNHUKJR2ooORdUIROASQitybY5JxaIM0GHQJCJVWH2U280pRr28nlOK02Wgw==";
    	util.getMethodOfAddTrustedCertificate().invoke(util.getObjectOfECCryptoProcessor(), new Object[]{trustedCrt.getBytes()});
	}
  
	public static byte[] sign(String message,CNCBCryptoUtil util)throws Exception{	
    	byte[] signedMessage	= (byte[])util.getMethodOfSign().invoke(util.getObjectOfECCryptoProcessor(), new Object[]{message.getBytes()});
		return signedMessage;
    }
    
	public static byte[] verify(String signedMessage,CNCBCryptoUtil util)throws Exception{
    	util.getMethodOfVerify().invoke(util.getObjectOfECCryptoProcessor(), new Object[]{signedMessage.getBytes()});   	
    	byte[] message	= (byte[])util.getMethodOfOfGetOrderMessage().invoke(util.getObjectOfECCryptoProcessor(), new Object[]{signedMessage.getBytes()});   
    	return message;
    }
   
   X509Certificate getSignerCertificate(String signedMessage,CNCBCryptoUtil util)throws Exception{
	   X509Certificate crt = (X509Certificate)util.getMethodOfGetSignerCertificate().invoke(util.getObjectOfECCryptoProcessor(), new Object[]{signedMessage.getBytes()});   
	   return crt;
   }
}
