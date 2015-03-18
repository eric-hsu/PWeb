package com.jinxin.service.paygateway.online.cib.util;

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

public class CibUtils {
	public static OperateResult paraxml(String xmlStr,BankReturnBean bankReturnBean){
		OperateResult operateResult= new OperateResult();
		operateResult.setSuccess(false);
		try{
			Map<String, String> resultMap=null;
			if(xmlStr.contains("Order")){
				resultMap = getQuerySuccessMap(xmlStr);
			}else{
				resultMap = getQueryFailMap(xmlStr);
				operateResult.setMessage(resultMap.get("msg")+"["+resultMap.get("code")+"]");
				return operateResult;
			}
			//商户代号，定长6位
			String merchantNo = resultMap.get("merchantNo");
			//订单编号，同一商户号下，所有订单的编号必须唯一。
			String orderNo = resultMap.get("orderNo");
			//商户名称，中文。
			String merchantName = resultMap.get("merchantName");
			//二级商户名称，支付时由商户系统送入。
			String subMerchantName = resultMap.get("subMerchantName");
			//订单生成日期，格式yyyyMMdd，由商户送入。
			String orderDate = resultMap.get("orderDate");
			//订单金额，小数点2位，单位：元。
			String orderAmount = resultMap.get("orderAmount");
			//订单币种，使用ISO4217标准货币代码，目前支持：CNY
			String currency = resultMap.get("currency");
			//订单状态，0-待支付；1-已支付；2-已结算；3-已撤销；4-部分退款；5-全额退款。
			String orderStatus = resultMap.get("orderStatus");
			//已退款金额，小数点2位，单位：元，已退款金额≤订单金额。
			String refundAmount = resultMap.get("refundAmount");
			//状态最后更新时间，格式为yyyyMMddhhmmss
			String updateTime = resultMap.get("updateTime");
			
			bankReturnBean.setResponseBankNo("");
			bankReturnBean.setResponseTrdeNo(orderNo);
			bankReturnBean.setResponseBatchNo("");
			bankReturnBean.setResponseInfo("");
			bankReturnBean.setResponseCode("");
			bankReturnBean.setResponseAmount(Double.valueOf(orderAmount));
			bankReturnBean.setResponseTradeTime(DateTimeUtils.parseTimestamp(updateTime, "yyyyMMddHHmmss"));
			bankReturnBean.setResponseQueryNo("");
			bankReturnBean.setResponseType("");
			if(!StringUtils.isBlank(refundAmount)){
				bankReturnBean.setResponseRefundAmount(Double.valueOf(refundAmount));
			}
			//orderStatus 订单状态，0-待支付；1-已支付；2-已结算；3-已撤销；4-部分退款；5-全额退款。
			if("0".equals(orderStatus)){
				bankReturnBean.setResponseStatus(ConstantsBean.TRADE_STATUS_PROCESS);
			}else if("1".equals(orderStatus)){
				bankReturnBean.setResponseStatus(ConstantsBean.TRADE_STATUS_SUCCESS);
			}else if("2".equals(orderStatus)){
				bankReturnBean.setResponseStatus(ConstantsBean.TRADE_STATUS_SUCCESS);
			}else if("3".equals(orderStatus)){
				bankReturnBean.setResponseStatus(ConstantsBean.TRADE_STATUS_REFUND);
			}else if("4".equals(orderStatus)){
				bankReturnBean.setResponseStatus(ConstantsBean.TRADE_STATUS_REFUND);
			}else if("5".equals(orderStatus)){
				bankReturnBean.setResponseStatus(ConstantsBean.TRADE_STATUS_REFUND);
			}
			operateResult.setSuccess(true);
		}catch(Exception e){
			e.printStackTrace();
			operateResult.setMessage(e.getMessage());
		}
		
		return operateResult;
	}
	

	/**
     * 解析客户发送过来的xml字串
     * 
     * @param request
     * @param requestXml
     *            xml字串
     * @return
     */
    private static Map<String, String> getQuerySuccessMap(String requestXml) {
        Map<String, String> taskInfoMap = new HashMap<String, String>();
        try {
            Element rootElement = getRootElement(requestXml);
            List<Element> elementList = rootElement.elements("Order");
            System.out.println(elementList.size());
            for (Element element : elementList) {
               
                List<Node> nodeList = element.elements();
                System.out.println(nodeList.size());
                for (Node node : nodeList) {
                    String text = node.getText();
                    if(StringUtils.isNotEmpty(text)){
                        text = text.trim();
                    }
                    System.out.println("====node.getName()====" + node.getName() + "====" + text);
                    taskInfoMap.put(node.getName(), text);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return taskInfoMap;
    }
    
    
    /**
     * 解析客户发送过来的xml字串
     * 
     * @param request
     * @param requestXml
     *            xml字串
     * @return
     */
    private static Map<String, String> getQueryFailMap(String requestXml) {
        Map<String, String> taskInfoMap = new HashMap<String, String>();
        try {
        	
        	requestXml = requestXml.replace("<![", "");
        	requestXml = requestXml.replace("]>", "");
        	
            Element rootElement = getRootElement(requestXml);
            String code = rootElement.element("code").getText();
            String msg = rootElement.element("msg").getText();
            taskInfoMap.put("code", code);
            taskInfoMap.put("msg", msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return taskInfoMap;
    }
    
    
    /**
     * 获取xml根节点
     * 
     * @param requestXml
     * @return
     */
    private static Element getRootElement(String requestXml) {
        Element rootElement = null;
        try {
            Document doc = DocumentHelper.parseText(requestXml);
            System.out.println(doc.asXML());
            rootElement = doc.getRootElement();
        } catch (DocumentException de) {
            de.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rootElement;
    }
    
    
	
	public static void main(String[] args){
		String  xmlStr = "<netpay_err> <code>10201</code> <msg><![CDATA[[NP-CS-7C3B9-2E2]璁㈠崟涓嶅瓨鍦╙]></msg></netpay_err>";
		Map<String, String> tradeList = getQueryFailMap(xmlStr);

		System.out.println(tradeList.size()+",code"+tradeList.get("code")+",msg"+tradeList.get("msg"));
		 
	}
	
}
