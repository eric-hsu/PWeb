package com.jinxin.common.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: 采用AES负责字符串的加密与解密
 * </p>
 * <p>
 * Copyright: epay (c) 2013 版权
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author 
 * @version V1.0
 * @date 2013-5-27上午10:57:33
 */
public class SecUtil {

	private static final Log logger = LogFactory.getLog(SecUtil.class);
	/**
	 * key 可自定义
	 */
	private static byte[] keybytes = { 0x31, 0x32, 0x33, 0x34, 0x35, 0x50,
			0x37, 0x38, 0x39, 0x40, 0x41, 0x42, 0x43, 0x44, 0x45, 0x46 };

	/**
	 * 实例化密钥生成器
	 * @return
	 * @throws Exception
	 */
	public static byte[] initkey() throws Exception {
		// 实例化密钥生成器
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		KeyGenerator kg = KeyGenerator.getInstance("AES", "BC");
		//kg.init(256);
		kg.init(128);
		SecretKey secretKey = kg.generateKey();
		return secretKey.getEncoded();
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader reader;
		try {
			String st = "";
			do{
				if("".equals(st)) {
					System.out.println("AES加密与解密操作:");
					System.out.println("\"E\":加密 \t\"D\":解密\t\t\"Q\":退出");
					System.out.println("请输入操作代码:");
				}
				reader = new BufferedReader(new InputStreamReader(System.in));
				st = reader.readLine();
				if("E".equalsIgnoreCase(st)) {
					System.out.println("请输入待加密字符串:");
					st = reader.readLine();
					if(!"".equals(st.trim())) {
						System.out.println("加密前:" + st.trim());
						System.out.println("加密后:" + encrypt(st.trim()) + "\n\n");
					}
					st = "";
				}else if("D".equalsIgnoreCase(st)) {
					System.out.println("请输入待解密字符串:");
					st = reader.readLine();
					if(!"".equals(st.trim())) {
						System.out.println("解密前:" + st.trim());
						System.out.println("解密后:" + decrypt(st.trim()) + "\n\n");
					}
					st = "";
				}
			} while(!st.equalsIgnoreCase("Q"));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * 
	 * @author  
	 * @Title encrypt
	 * @Time: 2011-5-27上午10:57:56
	 * @Description: 加密
	 * @return: String
	 * @throws:
	 * @param value
	 * @return
	 */
	public static String encrypt(String value) {

		String s = null;

		int mode = Cipher.ENCRYPT_MODE;

		try {
			Cipher cipher = initCipher(mode);

			byte[] outBytes = cipher.doFinal(value.getBytes());

			s = String.valueOf(Hex.encodeHex(outBytes));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return s;
	}
	
	/**
	 * 
	 * @author  
	 * @Title encrypt
	 * @Time: 2011-5-27上午10:57:56
	 * @Description: 加密
	 * @return: String
	 * @throws:
	 * @param value
	 * @return
	 */
	public static String encrypt(String aesKey, String value) {

		String s = null;

		int mode = Cipher.ENCRYPT_MODE;

		try {
			byte[] keyByte = decryptBASE64(aesKey);
			Cipher cipher = initCipher(keyByte,mode);

			byte[] outBytes = cipher.doFinal(value.getBytes());

			s = String.valueOf(Hex.encodeHex(outBytes));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return s;
	}

	/**
	 * 
	 * @author  
	 * @Title decrypt
	 * @Time: 2011-5-27上午10:58:09
	 * @Description: 解密
	 * @return: String
	 * @throws:
	 * @param value
	 * @return
	 */
	public static String decrypt(String value) {
		String s = null;

		int mode = Cipher.DECRYPT_MODE;

		try {
			Cipher cipher = initCipher(mode);

			byte[] outBytes = cipher
					.doFinal(Hex.decodeHex(value.toCharArray()));

			s = new String(outBytes);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return s;
	}

	/**
	 * 
	 * @author  
	 * @Title decrypt
	 * @Time: 2011-5-27上午10:58:09
	 * @Description: 解密
	 * @return: String
	 * @throws:
	 * @param value
	 * @return
	 */
	public static String decrypt(String aesKey,String value) {
		String s = null;

		int mode = Cipher.DECRYPT_MODE;

		try {
			byte[] keyByte = decryptBASE64(aesKey);
			Cipher cipher = initCipher(keyByte,mode);

			byte[] outBytes = cipher.doFinal(Hex.decodeHex(value.toCharArray()));

			s = new String(outBytes);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return s;
	}
	
	/**
	 * 
	 * @author  
	 * @Title initCipher
	 * @Time: 2011-5-27上午10:58:47
	 * @Description: 初始化密码
	 * @return: Cipher
	 * @throws:
	 * @param mode
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 */
	private static Cipher initCipher(int mode) throws NoSuchAlgorithmException,
	NoSuchPaddingException, InvalidKeyException {
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		Key key = new SecretKeySpec(keybytes, "AES");
		cipher.init(mode, key);
		return cipher;
	}
	
	/**
	 * 
	 * @author  
	 * @Title initCipher
	 * @Time: 2011-5-27上午10:58:47
	 * @Description: 初始化密码
	 * @return: Cipher
	 * @throws:
	 * @param mode
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 */
	private static Cipher initCipher(byte[] keybytes,int mode) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException {
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		try {
			System.out.println("keybytes////////////////////////////////////////////////////");
			System.out.println(keybytes);
			Key key = new SecretKeySpec(keybytes, "AES");
			System.out.println(key);
			System.out.println("////////////////////////////////////////////////////");
			System.out.println(getKeyAsString(key));
			byte[] keyBytes = decryptBASE64(getKeyAsString(key));
			System.out.println("////////////////////////////////////////////////////");
			System.out.println(keyBytes);
			System.out.println("////////////////////////////////////////////////////");
			System.out.println(key);
			cipher.init(mode, key);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return cipher;
	}
	
	/**
	 * 将Key转换成String，用来保存到数据库
	 * @param key
	 * @return
	 */
	public static String getKeyAsString(Key key){
        byte[] keyBytes = key.getEncoded();
        BASE64Encoder b64 = new BASE64Encoder();
        return b64.encode(keyBytes);
    }
	
	/**
	 * BASE64解密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptBASE64(String key) throws Exception {
		return (new BASE64Decoder()).decodeBuffer(key);
	}

	/**
	 * BASE64加密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptBASE64(byte[] key) throws Exception {
		return (new BASE64Encoder()).encodeBuffer(key);
	}
}