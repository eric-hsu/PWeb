package com.jinxin.service.paygateway.enterprise.cib.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * <p>Title: </p>
 * <p>Description: 加密类</p>
 * <p>Copyright: jinxin (c) 2013 版权</p>
 * <p>Company: </p>
 * @author 
 * @version V1.0 
 * @date 2013-6-10下午02:26:39
 */
public class EncryptUtilE {


    /**
     * 
     * @author  
     * @Title getMD5Encrypt
     * @Time: 2011-6-10下午02:29:14
     * @Description: MD5加密
     * @return: String 
     * @throws: 
     * @param strSrc
     * @return
     */
    public static String getMD5Encrypt(String strSrc) {
    	StringBuffer buf = new StringBuffer();
    	try {
	    	MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(strSrc.getBytes("UTF-8"));
			byte b[] = md.digest();
			int i;
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
    	} catch (NoSuchAlgorithmException e) {
    		e.printStackTrace();
    	} catch (UnsupportedEncodingException e) {
    		e.printStackTrace();
    	}
    	return buf.toString().toUpperCase();
    }
    
    
    public static void main(String[] args) {
    	
		System.out.println(getMD5Encrypt("abcd"));
		
	}
}
