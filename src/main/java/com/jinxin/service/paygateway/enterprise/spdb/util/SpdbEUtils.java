package com.jinxin.service.paygateway.enterprise.spdb.util;

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

public class SpdbEUtils {
	
	   /**
     * 解析客户发送过来的xml字串
     * 
     * @param request
     * @param requestXml
     *            xml字串
     * @return
     */
    public static Map<String, String> getTradeList(String requestXml) {
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
		String  xmlStr = "<netpay_resp><Order><merchantNo>110004</merchantNo><orderNo>20110110165455000001</orderNo><merchantName>支付宝</merchantName><subMerchantName>麦包包</subMerchantName><orderDate>20110110</orderDate><orderAmount>1234.56</orderAmount><currency>01</currency><orderStatus>1</orderStatus><refundAmount>234.56</refundAmount><updateTime>20110110143845</updateTime></Order></netpay_resp>";
		Map<String, String> tradeList = getTradeList(xmlStr);
		System.out.println(tradeList.size());
		 
	}
	
}
