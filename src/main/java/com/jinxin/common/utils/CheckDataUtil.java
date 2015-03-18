package com.jinxin.common.utils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.model.ConstantsBean;
import com.jinxin.model.persistence.Gateway;
import com.jinxin.model.persistence.Merchant;
import com.jinxin.model.persistence.Traderecord;


/**
 * @className:CheckDataUtil.java
 * @classDescription:
 * @author:erichu
 * @createTime:2014-6-23 下午4:14:26
 */

public class CheckDataUtil {

	public static OperateResult verifyGateway(Gateway gateway,Merchant merchant) {
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(true);
		if(gateway==null || merchant==null){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0078");
			return operateResult;
		}
		//判断商户合同起始日期
		if(merchant.getMerPactstdate() != null){
			if(merchant.getMerPactstdate().after(new Date())){
				operateResult.setSuccess(false);
				operateResult.setMessage("I0081");
				return operateResult;
			}
		}
		//判断商户合同结束日期
		if(merchant.getMerPactendate() != null){
			if(merchant.getMerPactendate().before(new Date())){
				operateResult.setSuccess(false);
				operateResult.setMessage("I0081");
				return operateResult;
			}
		}
		//判断商户号、网关接入号是否正确
		if(StringUtils.isBlank(gateway.getGwMd5key())){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0004");
			return operateResult;
		}
		
		//商户状态未激活
		if(ConstantsBean.STATUS_INACTIVE == gateway.getMerchant().getMerStatus()){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0005");
			return operateResult;
		}
		
		//商户状态停用
		if(ConstantsBean.STATUS_STOP == gateway.getMerchant().getMerStatus()){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0006");
			return operateResult;
		}
		
		//商户状态删除
		if(ConstantsBean.STATUS_DEL == gateway.getMerchant().getMerStatus()){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0007");
			return operateResult;
		}
		
		//商户状态异常
		if(!(ConstantsBean.STATUS_FORMAL == gateway.getMerchant().getMerStatus())){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0008");
			return operateResult;
		}
		
		//网关状态未激活
		if(ConstantsBean.STATUS_INACTIVE == gateway.getGwStatus()){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0009");
			return operateResult;
		}
		
		//网关状态停用
		if(ConstantsBean.STATUS_STOP == gateway.getGwStatus()){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0010");
			return operateResult;
		}
		
		//网关状态删除
		if(ConstantsBean.STATUS_DEL == gateway.getGwStatus()){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0011");
			return operateResult;
		}
		
		//网关状态异常
		if(!(ConstantsBean.STATUS_FORMAL == gateway.getGwStatus())
				&& !(ConstantsBean.STATUS_TEST == gateway.getGwStatus())){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0012");
			return operateResult;
		}

		return operateResult;
	}
	

	public static OperateResult verifyPayRequestData(HttpServletRequest request) {
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(true);
		//加密字符串
		String encryptStr = new String();

		//商户订单号是否为空,不允许空格
		if(StringUtils.isBlank(request.getParameter("orderNo"))){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0017");
			return operateResult;
		}else{
			String orderNo = request.getParameter("orderNo");
			if(!orderNo.matches("[0-9A-Za-z]*")){
				operateResult.setSuccess(false);
				operateResult.setMessage("I0079");
				return operateResult;
			}
		}
		
		
		//商户订单号过长
		if(request.getParameter("orderNo").length()>50){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0018");
			return operateResult;
		}
		
		//商户订单号过短
		if(request.getParameter("orderNo").length()<5){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0018");
			return operateResult;
		}
		
		//商户订单金额不为空,不允许空格
		if(StringUtils.isBlank(request.getParameter("amount"))){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0019");
			return operateResult;
		}
		
		//商户订单金额不能为0
		if("0".equals(request.getParameter("amount"))){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0020");
			return operateResult;
		}
		
		String amount = request.getParameter("amount").toString();
		boolean  flag = true;
		for (int i=0; i<amount.length(); i++) { 
			char c = amount.charAt(i); 
			if(".".equals(String.valueOf(c))){
				flag= true;
			}else{
				if (c<'0' || '9'<c) {
					flag = false;
					break;
				}else{
					flag=true;
				}
			}
		}
		
		if(!flag){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0019");
			return operateResult;
		}
		
		//商户订单币种不能为空
		if(StringUtils.isBlank(request.getParameter("currency"))){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0022");
			return operateResult;
		}
		
		//商户号是否为空,不允许空格
		if(StringUtils.isBlank(request.getParameter("merNo"))){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0001");
			return operateResult;
		}
		

		//商户网关接入号是否为空,不允许空格
		if(StringUtils.isBlank(request.getParameter("gatewayNo"))){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0002");
			return operateResult;
		}
		
		
		//商户前台通知地址不能为空
//		if(StringUtils.isBlank(request.getParameter("frontNotifyUrl"))){
//			operateResult.setSuccess(false);
//			operateResult.setMessage("I0068");
//			return operateResult;
//		}
		
		
		//商户服务器通知地址不能为空
		if(StringUtils.isBlank(request.getParameter("backNotifyUrl"))){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0069");
			return operateResult;
		}
		
		
		//签名信息是否为空,不允许空格
		if(StringUtils.isBlank(request.getParameter("signInfo"))){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0003");
			return operateResult;
		}
		
		String bankCode=request.getParameter("bankCode");
		String chaType=request.getParameter("chaType");
		String cardType=request.getParameter("cardType");
		if(!StringUtils.isBlank(chaType)){
			
			if(StringUtils.isBlank(bankCode)){
				operateResult.setSuccess(false);
				operateResult.setMessage("I0081");
				return operateResult;
			}
			
			if(StringUtils.isBlank(chaType)){
				operateResult.setSuccess(false);
				operateResult.setMessage("I0083");
				return operateResult;
			}
			if("1".equals(chaType)){
				if(StringUtils.isBlank(cardType)){
					operateResult.setSuccess(false);
					operateResult.setMessage("I0082");
					return operateResult;
				}
			}
		}

		return operateResult;
	}
	
	/**
	 * 
	 * @author  
	 * @Title getSignInfoForCome
	 * @Time: 2011-7-6下午02:15:57
	 * @Description: 网银支付订单签名 
	 * 采用SHA-256加密方式
	 * @return: String 
	 * @throws: 
	 * @param paramBean
	 * @return
	 */
	public static OperateResult verifyPaySignInfo(HttpServletRequest request,Gateway gateway){
		
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(true);
		StringBuffer encryptStr = new StringBuffer();
		//加密类
		EncryptUtil encrypt = new EncryptUtil();
		
		//3方
		encryptStr.append(request.getParameter("merNo"));//商户号
		encryptStr.append(request.getParameter("gatewayNo"));//网关接入号
		encryptStr.append(request.getParameter("orderNo"));//订单号
		encryptStr.append(request.getParameter("currency"));//订单币种
		encryptStr.append(request.getParameter("amount"));//订单金额
		encryptStr.append(request.getParameter("backNotifyUrl"));//服务器通知返回地址

		//加密Key
		encryptStr.append(gateway.getGwMd5key());
		
		String sign =  encrypt.getSHA256Encrypt(encryptStr.toString()).toUpperCase();
		
		if(!(sign.equalsIgnoreCase(request.getParameter("signInfo")))){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0013");
			return operateResult;
		}

		return operateResult;
	}
	
	public static void main(String[] args){
		StringBuffer encryptStr = new StringBuffer();
		//加密类
		EncryptUtil encrypt = new EncryptUtil();
		
		//3方
		encryptStr.append("12538");//商户号
		encryptStr.append("12538001");//网关接入号
		encryptStr.append("12345678");//订单号
		encryptStr.append("CNY");//订单币种
		encryptStr.append("1");//订单金额
		encryptStr.append("http://119.147.217.148/PWeb/payment/backNotifyResult.do");//服务器通知返回地址
		//加密Key
		encryptStr.append("826014141468z6");
		String sign =  encrypt.getSHA256Encrypt(encryptStr.toString()).toUpperCase();
		System.out.println(sign);
	}
	
	
	/**
	 * 
	 * @author  
	 * @Title getSignInfoForCome
	 * @Time: 2011-7-6下午02:15:57
	 * @Description: 网银退款订单签名 
	 * 采用SHA-256加密方式
	 * @return: String 
	 * @throws: 
	 * @param paramBean
	 * @return
	 */
	public static OperateResult verifyRefundSignInfo(HttpServletRequest request,Gateway gateway){
		
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(true);
		StringBuffer encryptStr = new StringBuffer();
		//加密类
		EncryptUtil encrypt = new EncryptUtil();
		
		//3方
		encryptStr.append(request.getParameter("merNo"));//商户号
		encryptStr.append(request.getParameter("gatewayNo"));//网关接入号
		encryptStr.append(request.getParameter("orderNo"));//流水号

		//加密Key
		encryptStr.append(gateway.getGwMd5key());
		
		String sign =  encrypt.getSHA256Encrypt(encryptStr.toString()).toUpperCase();
		
		if(!(sign.equalsIgnoreCase(request.getParameter("signInfo")))){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0013");
			return operateResult;
		}

		return operateResult;
	}
	
	/**
	 * 
	 * @author  
	 * @Title getSignInfoForCome
	 * @Time: 2011-7-6下午02:15:57
	 * @Description: 网银退款订单签名 
	 * 采用SHA-256加密方式
	 * @return: String 
	 * @throws: 
	 * @param paramBean
	 * @return
	 */
	public static OperateResult verifySynSignInfo(HttpServletRequest request,String trNos,String key){
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(true);
		StringBuffer encryptStr = new StringBuffer();
		//加密类
		EncryptUtil encrypt = new EncryptUtil();
		
		//3方
		encryptStr.append(trNos);//订单号
		encryptStr.append(key);//秘钥


		String sign =  encrypt.getSHA256Encrypt(encryptStr.toString()).toUpperCase();
		
		if(!(sign.equalsIgnoreCase(request.getParameter("signInfo")))){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0013");
			return operateResult;
		}

		return operateResult;
	}

	
	/**
	 * 
	 * @author  
	 * @Title getSignInfoForCome
	 * @Time: 2011-7-6下午02:15:57
	 * @Description: 网银查询订单签名 
	 * 采用SHA-256加密方式
	 * @return: String 
	 * @throws: 
	 * @param paramBean
	 * @return
	 */
	public static OperateResult verifyQuerySignInfo(HttpServletRequest request,Gateway gateway){
		
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(true);
		StringBuffer encryptStr = new StringBuffer();
		//加密类
		EncryptUtil encrypt = new EncryptUtil();
		
		//3方
		encryptStr.append(request.getParameter("merNo"));//商户号
		encryptStr.append(request.getParameter("gatewayNo"));//网关接入号
		encryptStr.append(request.getParameter("orderNo"));//流水号
		
		//加密Key
		encryptStr.append(gateway.getGwMd5key());
		
		String sign =  encrypt.getSHA256Encrypt(encryptStr.toString()).toUpperCase();
		
		if(!(sign.equalsIgnoreCase(request.getParameter("signInfo")))){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0013");
			return operateResult;
		}

		return operateResult;
	}

	/**
	 * 
	 * @author  
	 * @Title getSignInfoForCome
	 * @Time: 2011-7-6下午02:15:57
	 * @Description: 网银查询订单签名 
	 * 采用SHA-256加密方式
	 * @return: String 
	 * @throws: 
	 * @param paramBean
	 * @return
	 */
	public static OperateResult verifyQuerybatchSignInfo(HttpServletRequest request,Gateway gateway){
		
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(true);
		StringBuffer encryptStr = new StringBuffer();
		//加密类
		EncryptUtil encrypt = new EncryptUtil();
		
		//3方
		encryptStr.append(request.getParameter("merNo"));//商户号
		encryptStr.append(request.getParameter("gatewayNo"));//网关接入号
		encryptStr.append(request.getParameter("tradeDate"));//流水号
		
		//加密Key
		encryptStr.append(gateway.getGwMd5key());
		
		String sign =  encrypt.getSHA256Encrypt(encryptStr.toString()).toUpperCase();
		
		if(!(sign.equalsIgnoreCase(request.getParameter("signInfo")))){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0013");
			return operateResult;
		}

		return operateResult;
	}
	

	public static OperateResult verifyRefundRequestData(HttpServletRequest request){
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(true);

		//商户不能为空
		if(StringUtils.isBlank(request.getParameter("orderNo"))){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0070");
			return operateResult;
		}
		//退款金额不能为空
		if(StringUtils.isBlank(request.getParameter("refundAmount"))){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0075");
			return operateResult;
		}
		//币种不能为空
		if(StringUtils.isBlank(request.getParameter("currency"))){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0022");
			return operateResult;
		}
		
		
		//商户号是否为空,不允许空格
		if(StringUtils.isBlank(request.getParameter("merNo"))){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0001");
			return operateResult;
		}
		

		//商户网关接入号是否为空,不允许空格
		if(StringUtils.isBlank(request.getParameter("gatewayNo"))){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0002");
			return operateResult;
		}
		
		//签名信息是否为空,不允许空格
		if(StringUtils.isBlank(request.getParameter("signInfo"))){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0003");
			return operateResult;
		}
		
		return operateResult;
	}
	
	
	public static OperateResult verifyRefundStatus(Traderecord traderecord,HttpServletRequest request){
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(true);

		//1 状态查询
		if(traderecord.getTrStatus() != 1){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0076");
			return operateResult;
		}
		
		//6 退款金额合法校验。(request.getParameter("refundAmount")
		if(traderecord.getTrAmount().compareTo((new BigDecimal(request.getParameter("refundAmount")))) <0){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0074");
			return operateResult;
		}
				
		return operateResult;
	}
	

	public static OperateResult verifyQueryRequestData(HttpServletRequest request){
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(true);
		//加密字符串
		String encryptStr = new String();
		
		//商户订单号不能为空
		if(StringUtils.isBlank(request.getParameter("orderNo"))){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0070");
			return operateResult;
		}
		
		//商户号是否为空,不允许空格
		if(StringUtils.isBlank(request.getParameter("merNo"))){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0001");
			return operateResult;
		}
		
		//商户网关接入号是否为空,不允许空格
		if(StringUtils.isBlank(request.getParameter("gatewayNo"))){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0002");
			return operateResult;
		}
		
		//签名信息是否为空,不允许空格
		if(StringUtils.isBlank(request.getParameter("signInfo"))){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0003");
			return operateResult;
		}
		return operateResult;
	}
	
	public static OperateResult verifyQuerybatchRequestData(HttpServletRequest request){
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(true);
		//加密字符串
		String encryptStr = new String();
		
		//对账日期不能为空
		if(StringUtils.isBlank(request.getParameter("tradeDate"))){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0085");
			return operateResult;
		}
		
		boolean isDateFormat = isOK(request.getParameter("tradeDate"));
		if(!isDateFormat){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0085");
			return operateResult;
		}
		
		//商户号是否为空,不允许空格
		if(StringUtils.isBlank(request.getParameter("merNo"))){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0001");
			return operateResult;
		}
		
		//商户网关接入号是否为空,不允许空格
		if(StringUtils.isBlank(request.getParameter("gatewayNo"))){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0002");
			return operateResult;
		}
		
		//签名信息是否为空,不允许空格
		if(StringUtils.isBlank(request.getParameter("signInfo"))){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0003");
			return operateResult;
		}
		return operateResult;
	}
	
	public static boolean isOK(String str){
		java.text.SimpleDateFormat format=new java.text.SimpleDateFormat("yyyy-MM-dd");
		try{
			format.parse(str);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	public static boolean digitCheck(String input) {
	    for(int i = 0; i < input.length(); i++) {
	        char c = input.charAt(i);
	        if( (c < '0' || c > '9') ) {
	        return false;
	        }
	    }
	    return true;
	}
}
