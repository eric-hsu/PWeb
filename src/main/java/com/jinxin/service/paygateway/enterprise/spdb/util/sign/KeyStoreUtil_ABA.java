package com.jinxin.service.paygateway.enterprise.spdb.util.sign;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPrivateKey;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.ResourceBundle;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class KeyStoreUtil_ABA {
	private Hashtable _$2463 = new Hashtable();

	  private Hashtable _$2464 = new Hashtable();
	  private KeyStore _$2466;
	  private InputStream _$2467;

	  public KeyStoreUtil_ABA(String keyName, String storePassword)
	    throws FileNotFoundException, IOException, NoSuchAlgorithmException, CertificateException, KeyStoreException
	  {
	    if (keyName == null) {
	      keyName = System.getProperty("JAVA_HOME") + "/jre/lib/security/cacerts";
	    }

	    try
	    {
	      this._$2467 = new FileInputStream(keyName);
	    } catch (FileNotFoundException e) {
	      System.err.println("File Not Found");
	      e.printStackTrace();
	      throw e;
	    }

	    char[] pass = storePassword == null ? null : storePassword.toCharArray();
	    try
	    {
	      this._$2466 = KeyStore.getInstance("JKS");
	      this._$2466.load(this._$2467, pass);
	    }
	    catch (KeyStoreException e1) {
	      e1.printStackTrace();
	      throw e1;
	    } catch (NoSuchAlgorithmException e2) {
	      e2.printStackTrace();
	      throw e2;
	    }

	    Enumeration enu = this._$2466.aliases();

	    while (enu.hasMoreElements()) {
	      String alias = (String)enu.nextElement();

	      if (this._$2466.isCertificateEntry(alias)) {
	        CertificateInfo ci = new CertificateInfo();
	        ci.setAlias(alias);
	        ci.setCertificate(this._$2466.getCertificate(alias));
	        ci.setCertificateChain(this._$2466.getCertificateChain(alias));
	        ci.setCreationDate(this._$2466.getCreationDate(alias));
	        this._$2464.put(alias, ci);
	      }

	      if (this._$2466.isKeyEntry(alias)) {
	        KeyInfo ki = new KeyInfo();
	        ki.setAlias(alias);
	        ki.setCreationDate(this._$2466.getCreationDate(alias));
	        this._$2463.put(alias, ki);
	      }
	    }
	  }

	  public KeyStoreUtil_ABA(KeyStore keyStore)
	    throws KeyStoreException
	  {
	    this._$2466 = keyStore;

	    Enumeration enu = keyStore.aliases();

	    while (enu.hasMoreElements()) {
	      String alias = (String)enu.nextElement();

	      if (keyStore.isCertificateEntry(alias)) {
	        CertificateInfo ci = new CertificateInfo();
	        ci.setAlias(alias);
	        ci.setCertificate(keyStore.getCertificate(alias));
	        ci.setCertificateChain(keyStore.getCertificateChain(alias));
	        ci.setCreationDate(keyStore.getCreationDate(alias));
	        this._$2464.put(alias, ci);
	      }

	      if (keyStore.isKeyEntry(alias)) {
	        KeyInfo ki = new KeyInfo();
	        ki.setAlias(alias);
	        ki.setCreationDate(keyStore.getCreationDate(alias));
	        this._$2463.put(alias, ki);
	      }
	    }
	  }

	  public static String byteToHex(byte[] inbuf)
	  {
	    StringBuffer strBuf = new StringBuffer();

	    for (int i = 0; i < inbuf.length; i++) {
	      String byteStr = Integer.toHexString(inbuf[i] & 0xFF);
	      if (byteStr.length() != 2)
	        strBuf.append('0').append(byteStr);
	      else {
	        strBuf.append(byteStr);
	      }

	    }

	    return new String(strBuf);
	  }

	  public void creatCertificateRequest()
	    throws Exception
	  {
	    KeyPairGenerator keyGen = KeyPairGenerator.getInstance("MD5withRSA");
	    keyGen.initialize(1024, new SecureRandom());
	    KeyPair pair = keyGen.generateKeyPair();
	  }

	  public void creatKeyStore()
	  {
	  }

	  public CertificateInfo getCertificateEntry(String alias)
	  {
	    return (CertificateInfo)this._$2464.get(alias);
	  }

	  public int getCertificateEntrySize()
	  {
	    return this._$2464.size();
	  }

	  public KeyInfo getKeyEntry(String alias)
	  {
	    return (KeyInfo)this._$2463.get(alias);
	  }

	  public int getKeyEntrySize()
	  {
	    return this._$2463.size();
	  }

	  public KeyStore getKeyStore()
	  {
	    return this._$2466;
	  }

	  private PrivateKey _$2511(String alias, String password)
	  {
	    if ((alias == null) || (password == null))
	      return null;
	    try
	    {
	      return (PrivateKey)this._$2466.getKey(alias, password.toCharArray());
	    } catch (Exception e) {
	      e.printStackTrace();
	    }return null;
	  }

	  public static byte[] hexToByte(String inbuf)
	  {
	    int len = inbuf.length() / 2;
	    byte[] outbuf = new byte[len];

	    for (int i = 0; i < len; i++) {
	      String tmpbuf = inbuf.substring(i * 2, i * 2 + 2);

	      outbuf[i] = (byte)Integer.parseInt(tmpbuf, 16);
	    }

	    return outbuf;
	  }

	  public static void main(String[] args)
	  {
	    KeyStoreUtil_ABA ks = null;
	    try {
	      ks = new KeyStoreUtil_ABA("c:/merchant_test.jks", "111111");
	    } catch (Exception e) {
	      e.printStackTrace();
	      return;
	    }

	    Key key = ks._$2511("merchant_key", "111111");
	    System.err.println("Key :" + key.getClass().getName());

	    if ((key instanceof RSAPrivateKey))
	      System.err.println("java.security.interfaces.RSAPrivateKey");
	  }

	  public String payGateSignData(String data)
	  {
	    ResourceBundle rb = ResourceBundle.getBundle("paygate");
	    String alias = rb.getString("alias");
	    String password = rb.getString("password");

	    return signDataRSA(alias, password, data);
	  }

	  public boolean payGateVerifyData(String mer_id, String signData, String orgData)
	  {
	    return verifyDataRSA(mer_id, signData, orgData);
	  }

	  private String _$3066(PrivateKey privateKey, String data, String alg)
	  {
	    try
	    {
	      Signature dsa = Signature.getInstance(alg);

	      dsa.initSign(privateKey);

	      dsa.update(data.getBytes());

	      byte[] sig = dsa.sign();

	      return byteToHex(sig);
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }return null;
	  }

	  private String _$3071(PrivateKey privateKey, String data, String alg)
	  {
	    Security.addProvider(new BouncyCastleProvider());

	    SecureRandom rand = new SecureRandom();
	    long cost = System.currentTimeMillis();
	    Cipher out;
	    try
	    {
	      KeyFactory fact = KeyFactory.getInstance("RSA", "ABA");
	      Cipher in = Cipher.getInstance("RSA/ECB/PKCS1Padding", "ABA");
	      out = Cipher.getInstance("RSA/ECB/PKCS1Padding", "ABA");
	      out.init(1, privateKey);
	    } catch (Exception e) {
	      System.err.println(e.getMessage());
	      e.printStackTrace(System.err);
	      System.exit(2);
	      return null;
	    }

	    ByteArrayOutputStream bOut = new ByteArrayOutputStream();

	    CipherOutputStream cOut = new CipherOutputStream(bOut, out);

	    byte[] bytes = new byte[data.length()];

	    for (int i = 0; i != bytes.length; i++) {
	      bytes[i] = (byte)data.charAt(i);
	    }

	    try
	    {
	      cOut.write(bytes);
	      cOut.close();
	    } catch (IOException e) {
	      System.err.println(e.getMessage());

	      return null;
	    }
	    bytes = bOut.toByteArray();

	    System.out.println("enc:  " + byteToHex(bytes));

	    System.out.println("Cost time " + (System.currentTimeMillis() - cost));

	    return byteToHex(bytes);
	  }

	  public String signDataRSA(String alias, String password, String data)
	  {
	    if ((alias == null) || (password == null) || (data == null)) {
	      return null;
	    }
	    PrivateKey privateKey = _$2511(alias, password);

	    if (privateKey == null) {
	      System.err.println("The alias or password is wrong");
	      return null;
	    }

	    if (!(privateKey instanceof RSAPrivateKey))
	    {
	      System.err.println("The key is not RSAPrivateKey");
	      System.err.println("The class should Run under ABA");
	    }

	    return _$3066(privateKey, data, "MD5withRSA");
	  }

	  private boolean _$3082(PublicKey publicKey, String signData, String orgData, String alg)
	  {
	    try
	    {
	      if ((publicKey == null) || (signData == null) || (orgData == null) || (alg == null))
	      {
	        System.err.println("Error:in KeyStoreUtil.verifyData() . publicKey or signData or orgData or alg is null");

	        return false;
	      }

	      Signature dsa = Signature.getInstance(alg);

	      dsa.initVerify(publicKey);

	      dsa.update(orgData.getBytes());

	      byte[] sig = hexToByte(signData);
	      return dsa.verify(sig);
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }return false;
	  }

	  public boolean verifyDataRSA(String alias, String signData, String orgData)
	  {
	    try
	    {
	      if ((alias == null) || (signData == null) || (orgData == null)) {
	        System.err.println("Error:in KeyStoreUtil.verifyDataRSA() . alias or signData or orgData is null");

	        return false;
	      }

	      System.err.println(signData);
	      System.err.println(orgData);

	      PublicKey publicKey = this._$2466.getCertificate(alias).getPublicKey();

	      return _$3082(publicKey, signData, orgData, "MD5withRSA");
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }return false;
	  }
}
