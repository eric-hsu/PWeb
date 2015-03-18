package com.jinxin.common.rsa;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;

/** 
 *  
 * @author hp 
 * 排序 
 */  
  
public class RandomFile {  
      
	//要生成多少个随机数  
    int num = 16;  
    int[] suiji = new int[num];  
      
    //产生随机数组  
    public void ProduceNum(){  
        for(int i=0;i<num;i++){  
            int j = (int)(Math.random()*100);  
            suiji[i] = j;  
        }  
    }  
  
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
    	KeyGenerator keygen;
		try {
			keygen = KeyGenerator.getInstance("AES");
			SecureRandom random = new SecureRandom();
			System.out.println(random.toString());
			keygen.init(random);
			Key key = keygen.generateKey();
			System.out.println(random.toString());
			System.out.println(key.toString());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }  
  
}  