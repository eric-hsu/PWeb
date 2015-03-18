package com.jinxin.common.rsa;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import com.jinxin.common.utils.SecUtil;
import com.jinxin.model.MerchantKeyBean;


/**
 * 从JKS文件中得到公钥、私钥
 * @author Administrator
 *
 */
public class JKSTesting {
	/**
	 * 
	 * @param keyStoreFile（密钥库文件）
	 * @param storeFilePass（密钥库密码）
	 * @param keyAlias（公钥别名）
	 * @return
	 */
	public static PublicKey getPublicKey(String keyStoreFile,
			String storeFilePass, String keyAlias) {
		// 读取密钥是所要用到的工具类
		KeyStore ks;

		// 公钥类所对应的类
		PublicKey pubkey = null;
		try {

			// 得到实例对象
			ks = KeyStore.getInstance("JKS");
			FileInputStream fin;
			try {

				// 读取JKS文件
				fin = new FileInputStream(keyStoreFile);
				try {
					// 读取公钥
					ks.load(fin, storeFilePass.toCharArray());
					java.security.cert.Certificate cert = ks.getCertificate(keyAlias);
					pubkey = cert.getPublicKey();
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				} catch (CertificateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		return pubkey;
	}

	/**
	 * 获取私钥
	 * @param keyStoreFile（密钥库）
	 * @param storeFilePass（密钥密码）
	 * @param keyAlias（私钥别名）
	 * @param keyAliasPass（私钥密码）
	 * @return
	 */
	public static PrivateKey getPrivateKey(String keyStoreFile,
			String storeFilePass, String keyAlias, String keyAliasPass) {
		KeyStore ks;
		PrivateKey prikey = null;
		try {
			ks = KeyStore.getInstance("JKS");
			FileInputStream fin;
			try {
				fin = new FileInputStream(keyStoreFile);
				try {
					try {
						ks.load(fin, storeFilePass.toCharArray());
						// 先打开文件
						prikey = (PrivateKey) ks.getKey(keyAlias,
								keyAliasPass.toCharArray());
						// 通过别名和密码得到私钥
					} catch (UnrecoverableKeyException e) {
						e.printStackTrace();
					} catch (CertificateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		return prikey;
	}

	public static void main(String[] args) {
		PublicKey publicKey;
		PrivateKey privateKey;
		
		publicKey = getPublicKey("d:\\123.jks", "password", "深圳易派支付科技有限公司-测试 (itruschina cn enterprise subscriber ca-3)");
		privateKey = getPrivateKey("d:\\123.jks", "password", "深圳易派支付科技有限公司-测试 (itruschina cn enterprise subscriber ca-3)","password");
		System.out.println(publicKey.toString());
		System.out.println(privateKey.toString());
		System.out.println("***********************************************************************");
		
		System.out.println("公钥："+RSACoder.getKeyAsString(publicKey));
		
		System.out.println("私钥："+RSACoder.getKeyAsString(privateKey));
		
		//保存到数据库//
	}
}