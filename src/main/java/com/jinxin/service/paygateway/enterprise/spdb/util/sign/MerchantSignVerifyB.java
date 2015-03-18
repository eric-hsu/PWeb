package com.jinxin.service.paygateway.enterprise.spdb.util.sign;

import com.sun.net.ssl.internal.ssl.Provider;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.security.KeyStore;
import java.security.Security;
import java.util.ResourceBundle;

public class MerchantSignVerifyB {
	private static KeyStore _$2466;
	  private static ResourceBundle _$2530;

	  public static String merchantSignData(String data)
	  {
	    String password = _$2530.getString("key_password");
	    String keyAlias = _$2530.getString("key_alias");

	    KeyStoreUtil ks = null;
	    try {
	      ks = new KeyStoreUtil(_$2466);
	    } catch (Exception e) {
	      return null;
	    }

	    System.err.println("alias_key is !!!!!!!!!!!!!!" + keyAlias);

	    System.err.println("password is!!!!!!!!!!!!!!!!" + password);

	    return ks.signDataRSA(keyAlias, password, data);
	  }

	  public static String merchantSignData_ABA(String data)
	  {
	    String password = _$2530.getString("key_password");
	    String keyAlias = _$2530.getString("key_alias");

	    KeyStoreUtil_ABA ks = null;
	    try {
	      ks = new KeyStoreUtil_ABA(_$2466);
	    } catch (Exception e) {
	      return null;
	    }

	    return ks.signDataRSA(keyAlias, password, data);
	  }

	  public static boolean merchantVerifyPayGate(String signData, String orgData)
	  {
	    KeyStoreUtil ks = null;
	    try {
	      ks = new KeyStoreUtil(_$2466);
	    } catch (Exception e) {
	      return false;
	    }

	    return ks.verifyDataRSA("paygate_cert", signData, orgData);
	  }

	  public static boolean merchantVerifyPayGate_ABA(String signData, String orgData)
	  {
	    KeyStoreUtil_ABA ks = null;
	    try {
	      ks = new KeyStoreUtil_ABA(_$2466);
	    } catch (Exception e) {
	      return false;
	    }

	    return ks.verifyDataRSA("paygate_cert", signData, orgData);
	  }

	  public static boolean merchantVerifySelf(String signData, String orgData)
	  {
	    String alias = _$2530.getString("key_alias");

	    KeyStoreUtil ks = null;
	    try {
	      ks = new KeyStoreUtil(_$2466);
	    } catch (Exception e) {
	      return false;
	    }

	    return ks.verifyDataRSA(alias, signData, orgData);
	  }

	  public static boolean merchantVerifySelf_ABA(String signData, String orgData)
	  {
	    String keyAlias = _$2530.getString("key_alias");
	    KeyStoreUtil_ABA ks = null;
	    try {
	      ks = new KeyStoreUtil_ABA(_$2466);
	    } catch (Exception e) {
	      return false;
	    }

	    return ks.verifyDataRSA(keyAlias, signData, orgData);
	  }

	  public static void main(String[] args)
	  {
	    String ori = "orderId=000760|seqNo=10825000073|merchantDate=2003-08-25|userId=yyb01";

	    String sign = merchantSignData_ABA(ori);
	    boolean correct = merchantVerifySelf_ABA(sign, ori);
	    System.out.println("ori=" + ori);
	    System.out.println("sign=" + sign);
	    System.out.println("correct=" + correct);
	  }

	  static
	  {
	    System.err.println("Debug==> begin");
	    Security.addProvider(new Provider());

	    _$2530 = ResourceBundle.getBundle("spdb_merchantB");
	    String cafile = _$2530.getString("cafile");
	    String store_password = _$2530.getString("store_password");
	    System.err.println("Debug==> cafile=" + cafile);
	    InputStream inputStream = null;
	    try {
	      inputStream = new FileInputStream(cafile);
	    } catch (FileNotFoundException e) {
	      System.err.println("Error in MerSignVerify CA File Not Found");
	      e.printStackTrace();
	      System.err.println("Debug==> file not found exception");
	    }
	    System.err.println("Debug==> end");

	    char[] pass = store_password == null ? null : store_password.toCharArray();
	    try
	    {
	      _$2466 = KeyStore.getInstance("JKS");
	      _$2466.load(inputStream, pass);
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
	 }
}
