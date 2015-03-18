package com.jinxin.common.utils;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.model.ConstantsBean;
import com.jinxin.model.ReturnMerchantBean;
import com.jinxin.model.persistence.Gateway;
import com.jinxin.model.persistence.InformalTraderecord;
import com.jinxin.model.persistence.Traderecord;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class InterfaceUtils {

	/**
	 * 
	 * @author  eric
	 * @Title frontNotifyMer
	 * @Time: 2011-7-8下午02:28:25
	 * @Description: 创建返回xml
	 * @return: 
	 * @throws: 
	 * @return
	 */
	public static String createReturnMerXml(Traderecord traderecord,OperateResult operateResult,Gateway gateway,String type){
		ReturnMerchantBean returnMerchant = new ReturnMerchantBean();
		String postXml="";
		if(operateResult.isSuccess()){
			returnMerchant.setMerNo(String.valueOf(traderecord.getTrMerNo()));
			returnMerchant.setGatewayNo(String.valueOf(traderecord.getTrGwNo()));
			returnMerchant.setTradeNo(traderecord.getTrNo());
			returnMerchant.setOrderNo(traderecord.getTrMerOrderno());
			returnMerchant.setOrderCurrency(traderecord.getTrCurrency());
			returnMerchant.setOrderAmount(String.valueOf(traderecord.getTrAmount()));
			returnMerchant.setOrderStatus(traderecord.getTrStatus());
			returnMerchant.setRefundStatus(traderecord.getTrRefundment());
			returnMerchant.setRefundAmount(String.valueOf(traderecord.getTrRefundmentAmount()));
			returnMerchant.setRemark(traderecord.getTrRemark());
			returnMerchant.setSignKey(gateway.getGwMd5key());
			returnMerchant.setSignInfo(getSignInfoForOut(returnMerchant));
			postXml = returnQuerytResultByXml(returnMerchant,operateResult,type);
		}else{
			returnMerchant.setRemark(operateResult.getMessage());
			postXml = returnQuerytResultByXml(returnMerchant,operateResult,type);
		}
		return postXml;
	}
	
	/**
	 * 
	 * @author  
	 * @Title getSignInfoForOut
	 * @Time: 2011-12-1下午02:56:52
	 * @Description: 构造返回商户signInfo
	 * @return: String 
	 * @throws: 
	 * @param returnMerchant
	 * @return
	 */
	public static String getSignBatchInfoForOut(ReturnMerchantBean returnMerchant){
		StringBuffer encryptStr = new StringBuffer();
		//加密类
		EncryptUtil encrypt = new EncryptUtil();
		
		//商户号+网关接入号+系统流水号+商户订单号+商户网关key
		encryptStr.append(returnMerchant.getMerNo());
		encryptStr.append(returnMerchant.getGatewayNo());
		encryptStr.append(returnMerchant.getSignKey());
		
		return encrypt.getSHA256Encrypt(encryptStr.toString()).toUpperCase();
	}
	
	/**
	 * 
	 * @author  
	 * @Title getSignInfoForOut
	 * @Time: 2011-12-1下午02:56:52
	 * @Description: 构造返回商户signInfo
	 * @return: String 
	 * @throws: 
	 * @param returnMerchant
	 * @return
	 */
	public static String getSignInfoForOut(ReturnMerchantBean returnMerchant){
		StringBuffer encryptStr = new StringBuffer();
		//加密类
		EncryptUtil encrypt = new EncryptUtil();
		
		//商户号+网关接入号+系统流水号+商户订单号+商户网关key
		encryptStr.append(returnMerchant.getMerNo());
		encryptStr.append(returnMerchant.getGatewayNo());
		encryptStr.append(returnMerchant.getTradeNo());
		encryptStr.append(returnMerchant.getOrderNo());
		encryptStr.append(returnMerchant.getSignKey());
		
		return encrypt.getSHA256Encrypt(encryptStr.toString()).toUpperCase();
	}
	
	
	/**
	 * 
	 * @author  
	 * @Title returnMerchantByXml
	 * @Time: 2011-12-12下午04:27:33
	 * @Description: 通过xml返回商户信息，查询结果返回
	 * @return: String 
	 * @throws: 
	 * @param returnBean
	 * @return
	 */
	public static String returnQuerytResultByXml(ReturnMerchantBean returnBean,OperateResult operateResult,String type){
		
		//创建
		Document document = DocumentHelper.createDocument();

		Element data = null;
		
		data = document.addElement("respon");
		data.addElement("type").addText(type);
		if(operateResult.isSuccess()){
			data.addElement("success").addText("S");
			if(!StringUtils.isBlank(returnBean.getMerNo())){
				data.addElement("merNo").addText(returnBean.getMerNo());
			}
			if(!StringUtils.isBlank(returnBean.getGatewayNo())){
				data.addElement("gatewayNo").addText(returnBean.getGatewayNo());
			}
			if(!StringUtils.isBlank(returnBean.getTradeNo())){
				data.addElement("tradeNo").addText(returnBean.getTradeNo());
			}

			if(!StringUtils.isBlank(returnBean.getOrderNo())){
				data.addElement("orderNo").addText(returnBean.getOrderNo());
			}
			
			if(!StringUtils.isBlank(returnBean.getOrderAmount())){
				data.addElement("orderAmount").addText(returnBean.getOrderAmount());
			}
			if(!StringUtils.isBlank(returnBean.getOrderCurrency())){
				data.addElement("orderCurrency").addText(returnBean.getOrderCurrency());
			}

			data.addElement("refundAmount").addText(returnBean.getRefundAmount());
			data.addElement("refundStatus").addText(String.valueOf(returnBean.getRefundStatus()));
			data.addElement("orderStatus").addText(String.valueOf(returnBean.getOrderStatus()));
			
			if(!StringUtils.isBlank(returnBean.getSignInfo())){
				data.addElement("signInfo").addText(returnBean.getSignInfo());
			}

		}else{
			data.addElement("success").addText("F");
			if(!StringUtils.isBlank(operateResult.getMessage())){
				data.addElement("remark").addText(operateResult.getMessage());
			}
		}
		
		return document.asXML();
	}
	
	
	/**
	 * 
	 * @author  eric
	 * @Title frontNotifyMer
	 * @Time: 2011-7-8下午02:28:25
	 * @Description: 创建测试返回xml
	 * @return: 
	 * @throws: 
	 * @return
	 */
	public static String createTestReturnMerXml(InformalTraderecord traderecord,OperateResult operateResult,Gateway gateway,String type){
		ReturnMerchantBean returnMerchant = new ReturnMerchantBean();
		String postXml="";
		if(operateResult.isSuccess()){
			returnMerchant.setMerNo(String.valueOf(traderecord.getTtrMerNo()));
			returnMerchant.setGatewayNo(String.valueOf(traderecord.getTtrGwNo()));
			returnMerchant.setTradeNo(traderecord.getTtrNo());
			returnMerchant.setOrderNo(traderecord.getTtrMerOrderno());
			returnMerchant.setOrderCurrency(traderecord.getTtrCurrency());
			returnMerchant.setOrderAmount(String.valueOf(traderecord.getTtrAmount()));
			returnMerchant.setOrderStatus(traderecord.getTtrStatus());
			returnMerchant.setRemark(traderecord.getTtrRemark());
			returnMerchant.setSignKey(gateway.getGwMd5key());
			returnMerchant.setSignInfo(getSignInfoForOut(returnMerchant));
			postXml = returnQuerytResultByXml(returnMerchant,operateResult,type);
		}else{
			returnMerchant.setRemark(operateResult.getMessage());
			postXml = returnQuerytResultByXml(returnMerchant,operateResult,type);
		}
		
		return postXml;
	}
	
	/**
	 * 
	 * @author  eric
	 * @Title frontNotifyMer
	 * @Time: 2011-7-8下午02:28:25
	 * @Description: 创建测试返回xml
	 * @return: 
	 * @throws: 
	 * @return
	 */
	public static String createTestBatchReturnMerXml(List<InformalTraderecord> list,OperateResult operateResult,Gateway gateway,String type){
		String postXml="";

		List<ReturnMerchantBean> returnDataList = new ArrayList<ReturnMerchantBean>();
		for(int i=0;i<list.size();i++){

			InformalTraderecord  informalTraderecord = list.get(i);
			ReturnMerchantBean returnMerchant = new ReturnMerchantBean();
			
			returnMerchant.setMerNo(String.valueOf(informalTraderecord.getTtrMerNo()));
			returnMerchant.setGatewayNo(String.valueOf(informalTraderecord.getTtrGwNo()));
			returnMerchant.setTradeNo(informalTraderecord.getTtrNo());
			returnMerchant.setOrderNo(informalTraderecord.getTtrMerOrderno());
			returnMerchant.setOrderCurrency(informalTraderecord.getTtrCurrency());
			returnMerchant.setOrderAmount(String.valueOf(informalTraderecord.getTtrAmount()));
			returnMerchant.setOrderStatus(informalTraderecord.getTtrStatus());
			returnMerchant.setRemark(informalTraderecord.getTtrRemark());
			returnMerchant.setSignKey(gateway.getGwMd5key());
			returnDataList.add(returnMerchant);
		}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
		postXml = returnQueryBatchtResultByXml(returnDataList,operateResult);   
		return postXml;
	}
	
	
	/**
	 * 
	 * @author  eric
	 * @Title frontNotifyMer
	 * @Time: 2011-7-8下午02:28:25
	 * @Description: 创建返回xml
	 * @return: 
	 * @throws: 
	 * @return
	 */
	public static String createReturnBatchMerXml(List<Traderecord> list,OperateResult operateResult,Gateway gateway,String type){
		
		String postXml="";
		List<ReturnMerchantBean> returnDataList = new ArrayList<ReturnMerchantBean>();
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				ReturnMerchantBean returnMerchant = new ReturnMerchantBean();
				Traderecord traderecord = list.get(i);
				returnMerchant.setMerNo(String.valueOf(traderecord.getTrMerNo()));
				returnMerchant.setGatewayNo(String.valueOf(traderecord.getTrGwNo()));
				returnMerchant.setTradeNo(traderecord.getTrNo());
				returnMerchant.setOrderNo(traderecord.getTrMerOrderno());
				returnMerchant.setOrderCurrency(traderecord.getTrCurrency());
				returnMerchant.setOrderAmount(String.valueOf(traderecord.getTrAmount()));
				returnMerchant.setOrderStatus(traderecord.getTrStatus());
				returnMerchant.setRefundStatus(traderecord.getTrRefundment());
				returnMerchant.setRefundAmount(String.valueOf(traderecord.getTrRefundmentAmount()));
				returnMerchant.setRemark(traderecord.getTrRemark());
				returnMerchant.setSignKey(gateway.getGwMd5key());
				returnMerchant.setSignInfo(getSignInfoForOut(returnMerchant));
				returnDataList.add(returnMerchant);
			}
		}
		postXml = returnQueryBatchtResultByXml(returnDataList,operateResult);   
		return postXml;
	}
	
	
	/**
	 * 
	 * @author  
	 * @Title returnMerchantByXml
	 * @Time: 2011-12-12下午04:27:33
	 * @Description: 通过xml返回商户信息，查询结果返回
	 * @return: String 
	 * @throws: 
	 * @param returnBean
	 * @return
	 */
	public static String returnQueryBatchtResultByXml(List<ReturnMerchantBean> returnDataList,OperateResult operateResult){
		Element root = DocumentHelper.createElement("response");  
        Document document = DocumentHelper.createDocument(root);  
        
        Element successElement = root.addElement("success");  
        if(operateResult.isSuccess()){
        	successElement.addElement("status").addText("S");
        	successElement.addElement("message").addText("操作成功");  
        }else{
        	successElement.addElement("status").addText("F");
        	successElement.addElement("message").addText(operateResult.getMessage());  
        }
       
        Element dataElement = root.addElement("list");  

        for(int i=0;i<returnDataList.size();i++){
			
        	ReturnMerchantBean returnMerchantBean = returnDataList.get(i);	
        	dataElement.addElement("row");
	        Element ele = (Element) dataElement.elements().get(i);
	        ele.addElement("tradeNo").addText(returnMerchantBean.getTradeNo());
	        ele.addElement("orderNo").addText(returnMerchantBean.getOrderNo()); 
	        ele.addElement("orderAmount").addText(returnMerchantBean.getOrderAmount());
	        ele.addElement("orderCurrency").addText(returnMerchantBean.getOrderCurrency());
	        ele.addElement("orderStatus").addText(String.valueOf(returnMerchantBean.getOrderStatus()));
	        ele.addElement("refundStatus").addText(String.valueOf(returnMerchantBean.getRefundStatus()));
	        ele.addElement("refundAmount").addText(returnMerchantBean.getRefundAmount());
		}
		
		return document.asXML().toString();
	}
	

	/**
	 * 
	 * @author  eric
	 * @Title backNotifyMer
	 * @Time: 2011-7-8下午02:28:25
	 * @Description: 测试商户后台通知
	 * @return: 
	 * @throws: 
	 * @return
	 */
	public static OperateResult testBackNotifyMer(InformalTraderecord informalTraderecord,Gateway gateway){
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(true);
		try {
			//构造返回商户Bean
			ReturnMerchantBean returnMerchant = new ReturnMerchantBean();
			returnMerchant.setReturnUrl(informalTraderecord.getTtrReturnurl());
			returnMerchant.setMerNo(informalTraderecord.getTtrMerNo());
			returnMerchant.setGatewayNo(informalTraderecord.getTtrGwNo());
			returnMerchant.setTradeNo(informalTraderecord.getTtrNo());
			returnMerchant.setOrderNo(informalTraderecord.getTtrMerOrderno());
			returnMerchant.setOrderCurrency(informalTraderecord.getTtrCurrency());
			returnMerchant.setOrderAmount(informalTraderecord.getTtrAmount());
			//交易状态
			int tradeStatus = Integer.valueOf(informalTraderecord.getTtrStatus());
			returnMerchant.setOrderStatus(tradeStatus);
			returnMerchant.setOrderInfo(informalTraderecord.getTtrRemark());

			//sign info，如果网关接入号Key为空的话，则不返回sign info
			String signKey = gateway.getGwMd5key();
			if(StringUtils.isNotBlank(signKey)){
				//添加signKey
				returnMerchant.setSignKey(signKey);
				//添加signInfo
				returnMerchant.setSignInfo(getSignInfoForOut(returnMerchant));
			}
			returnMerchant.setRemark(informalTraderecord.getTtrRemark());
			
			String postXml = returnMerchantByXml(returnMerchant);
			if("".equals(postXml)){
				operateResult.setSuccess(false);
				operateResult.setMessage("系统异常.");
				return operateResult;
			}
			String postUrl = returnMerchant.getReturnUrl();
			
			//异步通知商户
			PayCallBack payCallBack = new PayCallBack(postUrl,postXml);
	        Thread thread = new Thread(payCallBack);
	        thread.start();
		} catch (Exception e) {
			e.printStackTrace();
			operateResult.setSuccess(false);
			operateResult.setMessage(e.getMessage());
		}
		return operateResult;
	}
	
	

	/**
	 * 
	 * @author  
	 * @Title returnMerchantByXml
	 * @Time: 2011-12-12下午04:27:33
	 * @Description: 通过xml返回商户信息，后台通知支付结果
	 * @return: String 
	 * @throws: 
	 * @param returnBean
	 * @return
	 */
	public static String returnMerchantByXml(ReturnMerchantBean returnBean){
		
		//创建
		Document document = DocumentHelper.createDocument();
		try{
			Element data = null;
			
			data = document.addElement("respon");
	
			//构造返回参数
			if(!StringUtils.isBlank(returnBean.getMerNo())){
				data.addElement("merNo").addText(returnBean.getMerNo());
			}
			if(!StringUtils.isBlank(returnBean.getGatewayNo())){
				data.addElement("gatewayNo").addText(returnBean.getGatewayNo());
			}
			if(!StringUtils.isBlank(returnBean.getTradeNo())){
				data.addElement("tradeNo").addText(returnBean.getTradeNo());
			}
			if(!StringUtils.isBlank(returnBean.getOrderNo())){
				data.addElement("orderNo").addText(returnBean.getOrderNo());
			}
			if(!StringUtils.isBlank(returnBean.getOrderAmount())){
				data.addElement("orderAmount").addText(returnBean.getOrderAmount());
			}
			if(!StringUtils.isBlank(returnBean.getOrderCurrency())){
				data.addElement("orderCurrency").addText(returnBean.getOrderCurrency());
			}
			data.addElement("orderStatus").addText(String.valueOf(returnBean.getOrderStatus()));

			if(!StringUtils.isBlank(returnBean.getOrderInfo())){
				data.addElement("orderInfo").addText(returnBean.getOrderInfo());
			}
			
			if(!StringUtils.isBlank(returnBean.getSignInfo())){
				data.addElement("signInfo").addText(returnBean.getSignInfo());
			}
			
			if(!StringUtils.isBlank(returnBean.getRemark())){
				data.addElement("remark").addText(returnBean.getRemark());
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
		return document.asXML();
	}
	
	

	/**
	 * 
	 * @author  eric
	 * @Title frontNotifyMer
	 * @Time: 2011-7-8下午02:28:25
	 * @Description: 测试商户前段通知
	 * @return: 
	 * @throws: 
	 * @return
	 */
	public static OperateResult testFrontNotifyMer(InformalTraderecord informalTraderecord, Gateway gateway,HttpServletRequest request, HttpServletResponse response){
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(true);
		try {
			//构造返回商户Bean
			ReturnMerchantBean returnMerchant = new ReturnMerchantBean();
			returnMerchant.setReturnUrl(informalTraderecord.getTtrPageReturnurl());
			returnMerchant.setMerNo(informalTraderecord.getTtrMerNo());
			returnMerchant.setGatewayNo(informalTraderecord.getTtrGwNo());
			returnMerchant.setTradeNo(informalTraderecord.getTtrNo());
			returnMerchant.setOrderNo(informalTraderecord.getTtrMerOrderno());
			returnMerchant.setOrderCurrency(informalTraderecord.getTtrCurrency());
			returnMerchant.setOrderAmount(String.valueOf(informalTraderecord.getTtrAmount()));
			//交易状态
			int tradeStatus = Integer.valueOf(informalTraderecord.getTtrStatus());
			returnMerchant.setOrderStatus(tradeStatus);
			returnMerchant.setOrderInfo(informalTraderecord.getTtrBankinfoIn());
			//sign info，如果网关接入号Key为空的话，则不返回sign info
			String signKey = gateway.getGwMd5key();
			if(StringUtils.isNotBlank(signKey)){
				//添加signKey
				returnMerchant.setSignKey(signKey);
				//添加signInfo
				returnMerchant.setSignInfo(getSignInfoForOut(returnMerchant));
			}
			returnMerchant.setRemark(informalTraderecord.getTtrRemark());
			
		
			request.setAttribute(ConstantsBean.SESSION_RETURN_MERCHANT, returnMerchant);
		} catch (Exception e) {
			e.printStackTrace();
			operateResult.setSuccess(false);
			operateResult.setMessage(e.getMessage());
		}
		
		return operateResult;
	}
	
	

	/**
	 * 
	 * @author  eric
	 * @Title backNotifyMer
	 * @Time: 2011-7-8下午02:28:25
	 * @Description: 后台通知
	 * @return: 
	 * @throws: 
	 * @return
	 */
	public static OperateResult backNotifyMer(Traderecord traderecord, Gateway gateway){
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(true);
		try {
			//构造返回商户Bean
			ReturnMerchantBean returnMerchant = new ReturnMerchantBean();
			returnMerchant.setReturnUrl(traderecord.getTrReturnurl());
			returnMerchant.setMerNo(String.valueOf(traderecord.getTrMerNo()));
			returnMerchant.setGatewayNo(String.valueOf(traderecord.getTrGwNo()));
			returnMerchant.setTradeNo(traderecord.getTrNo());
			returnMerchant.setOrderNo(traderecord.getTrMerOrderno());
			returnMerchant.setOrderCurrency(traderecord.getTrCurrency());
			returnMerchant.setOrderAmount(String.valueOf(traderecord.getTrAmount()));
			//交易状态
			int tradeStatus = Integer.valueOf(traderecord.getTrStatus());
			returnMerchant.setOrderStatus(tradeStatus);
			returnMerchant.setOrderInfo(traderecord.getTrRemark());
			//sign info，如果网关接入号Key为空的话，则不返回sign info
			String signKey = gateway.getGwMd5key();
			if(StringUtils.isNotBlank(signKey)){
				//添加signKey
				returnMerchant.setSignKey(signKey);
				//添加signInfo
				returnMerchant.setSignInfo(getSignInfoForOut(returnMerchant));
			}
			returnMerchant.setRemark(traderecord.getTrRemark());
			
			String postXml = returnMerchantByXml(returnMerchant);
			String postUrl = returnMerchant.getReturnUrl();
			
			//异步通知商户
			PayCallBack payCallBack = new PayCallBack(postUrl,postXml);
	        Thread thread = new Thread(payCallBack);
	        thread.start();
		} catch (Exception e) {
			e.printStackTrace();
			operateResult.setSuccess(false);
			operateResult.setMessage(e.getMessage());
		}
		return operateResult;
	}
	

	/**
	 * 
	 * @author  eric
	 * @Title frontNotifyMer
	 * @Time: 2011-7-8下午02:28:25
	 * @Description: 前段通知
	 * @return: 
	 * @throws: 
	 * @return
	 */
	public static  OperateResult frontNotifyMer(Traderecord traderecord, Gateway gateway,HttpServletRequest request, HttpServletResponse response){
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(true);
		try {
			
			//构造返回商户Bean
			ReturnMerchantBean returnMerchant = new ReturnMerchantBean();
			returnMerchant.setReturnUrl(traderecord.getTrPageReturnurl());
			returnMerchant.setMerNo(String.valueOf(traderecord.getTrMerNo()));
			returnMerchant.setGatewayNo(String.valueOf(traderecord.getTrGwNo()));
			returnMerchant.setTradeNo(traderecord.getTrNo());
			returnMerchant.setOrderNo(traderecord.getTrMerOrderno());
			returnMerchant.setOrderCurrency(traderecord.getTrCurrency());
			returnMerchant.setOrderAmount(String.valueOf(traderecord.getTrAmount()));
			//交易状态
			int tradeStatus = Integer.valueOf(traderecord.getTrStatus());
			returnMerchant.setOrderStatus(tradeStatus);
			returnMerchant.setOrderInfo(traderecord.getTrBankinfo());
			//sign info，如果网关接入号Key为空的话，则不返回sign info
			String signKey = gateway.getGwMd5key();
			if(StringUtils.isNotBlank(signKey)){
				//添加signKey
				returnMerchant.setSignKey(signKey);
				//添加signInfo
				returnMerchant.setSignInfo(getSignInfoForOut(returnMerchant));
			}
			returnMerchant.setRemark(traderecord.getTrRemark());
			
			request.setAttribute(ConstantsBean.SESSION_RETURN_MERCHANT, returnMerchant);
		} catch (Exception e) {
			e.printStackTrace();
			operateResult.setSuccess(false);
			operateResult.setMessage(e.getMessage());
		}
		
		return operateResult;
	}
	
	
}
