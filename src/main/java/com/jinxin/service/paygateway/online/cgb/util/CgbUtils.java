package com.jinxin.service.paygateway.online.cgb.util;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import com.jinxin.common.utils.DateTimeUtils;
import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.model.BankReturnBean;
import com.jinxin.model.ConstantsBean;
import com.jinxin.service.paygateway.online.icbc.util.IcbcConstants;
import com.jinxin.service.paygateway.online.icbc.util.SSL.SSLSendAndRec;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;


public class CgbUtils {

	public static OperateResult sign(String signstr,String key){
		OperateResult operateResult= new OperateResult();
		operateResult.setSuccess(false);
       String sign ="";
	   try
	   {
		   	InputStream istream=CgbConstants.class.getResourceAsStream(CgbConstants.PRIVATEKEY);
		   	ObjectInputStream p = new ObjectInputStream(istream);
		   	PrivateKey privatekey = (PrivateKey)p.readObject();
		   	p.close();
	    	  
	        Signature signature = Signature.getInstance("DSA");
	        signature.initSign(privatekey);
	        MessageDigest messagedigest = MessageDigest.getInstance("SHA");
	        byte[] abyte0 = messagedigest.digest(signstr.getBytes());

	        signature.update(abyte0);
	        byte[] abyte1 = signature.sign();
	        
	        StringBuffer stringbuffer = new StringBuffer();
	        for (int i = 0; i < abyte1.length; i++)
	        {
	          String s = Integer.toHexString(abyte1[i] & 0xFF);
	          if (s.length() != 2)
	            stringbuffer.append('0').append(s);
	          else {
	            stringbuffer.append(s);
	          }
	        }
	        sign =  new String(stringbuffer);
	     
	        operateResult.setSuccess(true);
	        operateResult.setMessage(sign);
	    }
	    catch (Exception e)
	    {
	      operateResult.setMessage(e.getMessage());
	    }
		
		return operateResult;
	}
	
	public static OperateResult verify(String signdata,String sign,String key){
		OperateResult operateResult= new OperateResult();
		operateResult.setSuccess(false);
		boolean ret = false;
		try
	    {
			InputStream istream=CgbConstants.class.getResourceAsStream(CgbConstants.PUBLICKEY);
		   	ObjectInputStream p = new ObjectInputStream(istream);
	        PublicKey publickey = (PublicKey)p.readObject();
	        Signature signature = Signature.getInstance("SHA/DSA");
	        signature.initVerify(publickey);

	        MessageDigest messagedigest = MessageDigest.getInstance("SHA");
	        byte[] abyte0 = messagedigest.digest(signdata.getBytes());
	        signature.update(abyte0);
	        byte[] abyte1 = hexToByte(sign);
	        
	        ret = signature.verify(abyte1);
	        operateResult.setSuccess(ret);
	    }catch (Exception e){
	      operateResult.setMessage(e.getMessage());
	    }
		
		return operateResult;
	}
	
	  public static byte[] hexToByte(String s)
	  {
	    int i = s.length() / 2;
	    byte[] abyte0 = new byte[i];
	    for (int j = 0; j < i; j++)
	    {
	      String s1 = s.substring(j * 2, j * 2 + 2);
	      abyte0[j] = (byte)Integer.parseInt(s1, 16);
	    }

	    return abyte0;
	  }
	
	
	public static OperateResult resultParam(String result,BankReturnBean bankReturnBean){
		OperateResult operateResult= new OperateResult();
		operateResult.setSuccess(false);
		
		String[] resultarray = result.split(":");
		if(resultarray.length<2){
			operateResult.setMessage(resultarray[0]);
			return operateResult;
		}
		
		String resultstr = resultarray[1];
		String status = resultstr.substring(0,1);
		String[] data = resultstr.split("\\|\\|");
		//订单号
		String orderid = data[0];
		//订单金额
		String amount = data[1];
		//订单日期
		String orderdate = data[2];
		//订单状态
		String success  = data[3];
		//银行流水号
		String sequence = data[4];
		//银行签名信息
		String sign = data[5];
		//商户号
		String merchid = data[6];
		
		String signdata = "orderid"+orderid+"amount"+amount+"orderdate"+orderdate+"success"+success+"sequence"+sequence+"merchid"+merchid;
		operateResult = CgbUtils.verify(signdata, sign, "");
		if(!operateResult.isSuccess()){
			return operateResult;
		}
		

		 bankReturnBean.setResponseBankNo(sequence);
		 bankReturnBean.setResponseTrdeNo(orderid);
		 bankReturnBean.setResponseBatchNo("");
		 bankReturnBean.setResponseInfo("");
		 bankReturnBean.setResponseCode(success);
		 bankReturnBean.setResponseAmount(Double.valueOf(amount));
		 bankReturnBean.setResponseTradeTime(DateTimeUtils.parseTimestamp(orderdate));
		 bankReturnBean.setResponseQueryNo("");

		
		if("RC000".equals(success)){
			bankReturnBean.setResponseStatus(ConstantsBean.TRADE_STATUS_SUCCESS);
		}else if("RC001".equals(success)){
			bankReturnBean.setResponseStatus(ConstantsBean.TRADE_STATUS_FAIL);
		}else{
			bankReturnBean.setResponseStatus(ConstantsBean.TRADE_STATUS_PAY_ING);
		}
		
		return operateResult;
	}
	
	
	public static void main(String args[]){
		
		String result = "order_status:1900123456||0.05||2012-09-12||RC000||200000111083||302c021***4f97||000400004500021";
		
		String[] resultarray = result.split(":");
		String resultstr = resultarray[1];
		
		String status = resultstr.substring(0,1);
		

		System.out.println("resultstr="+resultstr+",status="+status); 
		
		
		String[] data = resultstr.split("\\|\\|");
		
		for(int i = 0; i <data.length; i++){
			System.out.println(data[i]);
		}
		System.out.println("data="+data[0]); 
		
		
		//订单号
		String orderid = data[0];
		//订单金额
		String amount = data[1];
		//订单日期
		String orderdate = data[2];
		//订单状态
		String success  = data[3];
		//银行流水号
		String sequence = data[4];
		//银行签名信息
		String sign = data[5];
		//商户号
		String merchid = data[6];
		
		String signdata = "orderid"+orderid+"amount"+amount+"orderdate"+orderdate+"success"+success+"sequence"+sequence+"merchid"+merchid;
		System.out.println(signdata); 
	}
	
}
