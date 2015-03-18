package com.jinxin.service.paygateway.online.icbc.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import com.jinxin.common.utils.DateTimeUtils;
import com.jinxin.model.persistence.Traderecord;
import com.jinxin.service.paygateway.online.icbc.util.SSL.SSLSendAndRec;

import cn.com.infosec.icbc.ReturnValue;

public class IcbcUtils {
	private static final Logger logger = Logger.getLogger(IcbcUtils.class);
	public static String createXml(IcbcSendBean icbcSendBean){
		StringBuffer tranData=  new StringBuffer();
		tranData.append("<?xml version=\"1.0\" encoding=\"GBK\" standalone=\"no\"?>");
		tranData.append("<B2CReq>");
		tranData.append("<interfaceName>"+icbcSendBean.getInterfaceName()+"</interfaceName>");
		tranData.append("<interfaceVersion>"+icbcSendBean.getInterfaceVersion()+"</interfaceVersion>");
		tranData.append("<orderInfo>");
		tranData.append("<orderDate>"+icbcSendBean.getOrderDate()+"</orderDate>");
		tranData.append("<curType>"+icbcSendBean.getCurType()+"</curType>");
		tranData.append("<merID>"+icbcSendBean.getMerID()+"</merID>");
		tranData.append("<subOrderInfoList><subOrderInfo>");
		tranData.append("<orderid>"+icbcSendBean.getOrderid()+"</orderid>");
		tranData.append("<amount>"+icbcSendBean.getAmount()+"</amount>");
		tranData.append("<installmentTimes>"+icbcSendBean.getInstallmentTimes()+"</installmentTimes>");
		tranData.append("<merAcct>"+icbcSendBean.getMerAcct()+"</merAcct>");
		tranData.append("<goodsID>"+icbcSendBean.getGoodsID()+"</goodsID>");
		tranData.append("<goodsName>"+icbcSendBean.getGoodsName()+"</goodsName>");
		tranData.append("<goodsNum>"+icbcSendBean.getGoodsNum()+"</goodsNum>");
		tranData.append("<carriageAmt>"+icbcSendBean.getCarriageAmt()+"</carriageAmt>");
		tranData.append("</subOrderInfo></subOrderInfoList>");
		tranData.append("</orderInfo>");
		tranData.append("<custom>");
		tranData.append("<verifyJoinFlag>"+icbcSendBean.getVerifyJoinFlag()+"</verifyJoinFlag>");
		tranData.append("<Language>"+icbcSendBean.getLanguage()+"</Language>");
		tranData.append("</custom>");
		tranData.append("<message>");
		tranData.append("<creditType>"+icbcSendBean.getCreditType()+"</creditType>");
		tranData.append("<notifyType>"+icbcSendBean.getNotifyType()+"</notifyType>");
		tranData.append("<resultType>"+icbcSendBean.getResultType()+"</resultType>");
		tranData.append("<merReference>"+icbcSendBean.getMerReference()+"</merReference>");
		tranData.append("<merCustomIp>"+icbcSendBean.getMerCustomIp()+"</merCustomIp>");
		tranData.append("<goodsType>"+icbcSendBean.getGoodsType()+"</goodsType>");
		tranData.append("<merCustomID>"+icbcSendBean.getMerCustomID()+"</merCustomID>");
		tranData.append("<merCustomPhone>"+icbcSendBean.getMerCustomPhone()+"</merCustomPhone>");
		tranData.append("<goodsAddress>"+icbcSendBean.getGoodsAddress()+"</goodsAddress>");
		tranData.append("<merOrderRemark>"+icbcSendBean.getMerOrderRemark()+"</merOrderRemark>");
		tranData.append("<merHint>"+icbcSendBean.getMerHint()+"</merHint>");
		tranData.append("<remark1>"+icbcSendBean.getRemark1()+"</remark1>");
		tranData.append("<remark2>"+icbcSendBean.getRemark2()+"</remark2>");
		tranData.append("<merURL>"+icbcSendBean.getMerURL()+"</merURL>");
		tranData.append("<merVAR>"+icbcSendBean.getMerVAR()+"</merVAR>");
		tranData.append("</message>");
		tranData.append("</B2CReq>");
		return tranData.toString();
	}
	
	
	public static String createQueryXml(IcbcSendBean icbcSendBean){
		StringBuffer MerReqData=  new StringBuffer();
		MerReqData.append("<?xml version=\"1.0\" encoding=\"GBK\" standalone=\"no\"?>");
		MerReqData.append("<ICBCAPI><in>");
		MerReqData.append("<orderNum>"+icbcSendBean.getOrderid()+"</orderNum>");
		MerReqData.append("<tranDate>"+icbcSendBean.getOrderDate()+"</tranDate>");
		MerReqData.append("<ShopCode>"+icbcSendBean.getMerID()+"</ShopCode>");
		MerReqData.append("<ShopAccount>"+icbcSendBean.getMerAcct()+"</ShopAccount>");
		MerReqData.append("</in></ICBCAPI>");
		
		return MerReqData.toString();
	}

	public static boolean verifySign(String signMsg){
		boolean flag = false;
		String tranData = signMsg;
		logger.info("工行验证签名明文："+tranData+"");
		String password = IcbcConstants.PASSWORD;
		try{
			byte[] byteSrc = tranData.getBytes();
			char[] keyPass = password.toCharArray();

			InputStream in=SSLSendAndRec.class.getResourceAsStream(IcbcConstants.CERTNAME);
			byte[] bcert = new byte[in.available()];
			in.read(bcert);
			in.close();

			InputStream in2=SSLSendAndRec.class.getResourceAsStream(IcbcConstants.KEYNAME);
			byte[] bkey = new byte[in2.available()];
			in2.read(bkey);
			in2.close();
			
		    byte[] sign =ReturnValue.sign(byteSrc,byteSrc.length,bkey,keyPass);
		    if (sign==null) {
		    	logger.info("签名失败,签名返回为空。请检查证书私钥和私钥保护口令是否正确。");
		    	return flag;
		    }else{
		    	logger.info("签名成功");
		   
			    byte[] EncSign = ReturnValue.base64enc(sign);
			    String SignMsgBase64=new String(EncSign).toString();
			    logger.info("签名信息BASE64编码："+SignMsgBase64+"");
			    
				byte[] EncCert=ReturnValue.base64enc(bcert);
				String CertBase64=new String(EncCert).toString();
				logger.info("证书公钥BASE64编码："+CertBase64.substring(0,100)+"");
			
				byte[] DecSign = ReturnValue.base64dec(EncSign);
			    if (DecSign!=null){
			    	logger.info("签名信息BASE64解码成功");
			    	byte[] DecCert = ReturnValue.base64dec(EncCert);
			    	if (DecCert!=null){
			    		logger.info("证书公钥BASE64解码成功");
			    		int a=ReturnValue.verifySign(byteSrc,byteSrc.length,DecCert,DecSign);
			    		if (a==0){
			    			flag=true;
			    			logger.info("验签成功");
			    		}else logger.info("验签失败,验证返回码："+a);	    		
			    	}else logger.info("证书BASE64解码失败");
			    }else logger.info("签名信息BASE64解码失败");	
			}
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	public static String decryptSign(String notifyData){
		String receiveXml ="";
		byte[] str = notifyData.getBytes();
		receiveXml = new String(ReturnValue.base64dec(str)).toString();
		return receiveXml;
	}

	
	public static IcbcReceiveDataBean createReceiveDataBean(String receiveXml){
		IcbcReceiveDataBean icbcReceiveDataBean = new IcbcReceiveDataBean();

		Map<String, String> map = getTradeList(receiveXml);
		icbcReceiveDataBean.setInterfaceVersion(map.get("interfaceVersion"));
		icbcReceiveDataBean.setOrderDate(map.get("orderDate"));		
		icbcReceiveDataBean.setMerID(map.get("merID"));
		icbcReceiveDataBean.setCurType(map.get("curType"));
		icbcReceiveDataBean.setJoinFlag(map.get("JoinFlag"));
		icbcReceiveDataBean.setEpayRegResult(map.get("epayRegResult"));
		icbcReceiveDataBean.setTranBatchNo(map.get("TranBatchNo"));
		icbcReceiveDataBean.setNotifyDate(map.get("notifyDate"));
		icbcReceiveDataBean.setTranSerialNo(map.get("tranSerialNo"));
		icbcReceiveDataBean.setAmount(map.get("amount"));
		icbcReceiveDataBean.setUserNum(map.get("UserNum"));
		icbcReceiveDataBean.setInterfaceName(map.get("interfaceName"));
		icbcReceiveDataBean.setVerifyJoinFlag(map.get("verifyJoinFlag"));
		icbcReceiveDataBean.setInstallmentTimes(map.get("installmentTimes"));
		icbcReceiveDataBean.setMerAcct(map.get("merAcct"));
		icbcReceiveDataBean.setTranStat(map.get("tranStat"));
		icbcReceiveDataBean.setOrderid(map.get("orderid"));
		icbcReceiveDataBean.setComment(map.get("comment"));
		
		return icbcReceiveDataBean;
	}
	
	
	  /**
     * 解析工行支付通知xml字串
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
            String interfaceName = rootElement.elementText("interfaceName");
            taskInfoMap.put("interfaceName", interfaceName);
            String interfaceVersion = rootElement.elementText("interfaceVersion");
            taskInfoMap.put("interfaceVersion", interfaceVersion);
            List<Element> elementList = rootElement.elements("orderInfo");
            for (Element element : elementList) {
                List<Node> nodeList = element.elements();
                for (Node node : nodeList) {
                	if("subOrderInfoList".equals(node.getName())){
                		 List<Element> list1=element.elements("subOrderInfoList");
                		 for (Element e1 : list1) {
                			 List<Element> list2=e1.elements("subOrderInfo");
                			 for (Element e2 : list2) {
                				 List<Node> list3=e2.elements();
                				 for (Node node111 : list3) {
                					 taskInfoMap.put(node111.getName(), node111.getText());
                				 }
                			 }
                		 }
                     }else{
	                	String text = node.getText();
	                    if(StringUtils.isNotEmpty(text)){
	                        text = text.trim();
	                    }
	                	 taskInfoMap.put(node.getName(), text);
                     }
                }  
            }
            
            List<Element> elementList1 = rootElement.elements("custom");
            for (Element element : elementList1) {
               
                List<Node> nodeList = element.elements();
                for (Node node : nodeList) {
                    String text = node.getText();
                    if(StringUtils.isNotEmpty(text)){
                        text = text.trim();
                    }
                    taskInfoMap.put(node.getName(), text);
                }
            }
            
            List<Element> elementList2 = rootElement.elements("bank");
            for (Element element : elementList2) {
               
                List<Node> nodeList = element.elements();
                for (Node node : nodeList) {
                    String text = node.getText();
                    if(StringUtils.isNotEmpty(text)){
                        text = text.trim();
                    }
                    taskInfoMap.put(node.getName(), text);
                }
            }
            List<Element> elementList3 = rootElement.elements("extend");
            for (Element element : elementList3) {
                List<Node> nodeList = element.elements();
                for (Node node : nodeList) {
                    String text = node.getText();
                    if(StringUtils.isNotEmpty(text)){
                        text = text.trim();
                    }
                    taskInfoMap.put(node.getName(), text);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return taskInfoMap;
    }
    
    /**
     * 解析工行查询同步结果xml字串
     * 
     * @param request
     * @param requestXml
     *            xml字串
     * @return
     */
    public static Map<String, String> getQueryResultMap(String receiveXml) {
    	 Map<String, String> taskInfoMap = new HashMap<String, String>(); 
    	 try {
             Element rootElement = getRootElement(receiveXml);
             List<Element> pubTag = rootElement.elements("pub");
             for (Element element : pubTag) {
                 List<Node> nodeList = element.elements();
                 for (Node node : nodeList) {
                     String text = node.getText();
                     if(StringUtils.isNotEmpty(text)){
                         text = text.trim();
                     }
                     taskInfoMap.put(node.getName(), text);
                 }
             }
             
             List<Element> inTag = rootElement.elements("in");
             for (Element element : inTag) {
                 List<Node> nodeList = element.elements();
                 for (Node node : nodeList) {
                     String text = node.getText();
                     if(StringUtils.isNotEmpty(text)){
                         text = text.trim();
                     }
                     taskInfoMap.put(node.getName(), text);
                 }
             }
             
             List<Element> outTag = rootElement.elements("out");
             for (Element element : outTag) {
                 List<Node> nodeList = element.elements();
                 for (Node node : nodeList) {
                     String text = node.getText();
                     if(StringUtils.isNotEmpty(text)){
                         text = text.trim();
                     }
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
//    	StringBuffer xml = new StringBuffer();
//		xml.append("<?xml version=\"1.0\" encoding=\"GBK\" standalone=\"no\"?>");
//		xml.append("<B2CRes><interfaceName>1</interfaceName><interfaceVersion>114</interfaceVersion><orderInfo><orderDate>2014</orderDate><curType>cny</curType><merID>441</merID>");
//		xml.append("<subOrderInfoList><subOrderInfo><orderid>141741274214141</orderid><amount>100</amount><installmentTimes>11</installmentTimes><merAcct>141</merAcct><tranSerialNo>104104010</tranSerialNo></subOrderInfo></subOrderInfoList></orderInfo>");
//		xml.append("<custom><verifyJoinFlag>1</verifyJoinFlag><JoinFlag>1</JoinFlag><UserNum>114</UserNum></custom>");
//		xml.append("<bank><TranBatchNo>123456</TranBatchNo><notifyDate>201422</notifyDate><tranStat>1</tranStat><comment>error</comment></bank>");
//		xml.append("<extend><epayRegResult>dddd</epayRegResult></extend></B2CRes>");
//		
//    	StringBuffer xml = new StringBuffer();
//		xml.append("<?xml version=\"1.0\" encoding=\"GBK\" standalone=\"no\"?>");
//		xml.append("<B2CReq><interfaceName>ICBC_PERBANK_B2C</interfaceName><interfaceVersion>1.0.0.11</interfaceVersion><orderInfo><orderDate>20100308141629</orderDate><curType>001</curType><merID>0200EC20001119</merID><subOrderInfoList><subOrderInfo><orderid>201003081416291</orderid><amount>1</amount><installmentTimes>1</installmentTimes><merAcct>0200026009018372212</merAcct><goodsID>001</goodsID><goodsName>威尼熊</goodsName><goodsNum>2</goodsNum><carriageAmt>20</carriageAmt></subOrderInfo></subOrderInfoList></orderInfo>");
//		xml.append("<custom><verifyJoinFlag>0</verifyJoinFlag><Language>ZH_CN</Language></custom><message><creditType>2</creditType><notifyType>AG</notifyType><resultType>1</resultType><merReference>localhost</merReference><merCustomIp>127.0.0.1</merCustomIp><goodsType>1</goodsType><merCustomID>123456</merCustomID><merCustomPhone>13466780886</merCustomPhone><goodsAddress>三里屯</goodsAddress><merOrderRemark>防欺诈接口专用</merOrderRemark><merHint>请保留包装</merHint><remark1></remark1><remark2></remark2><merURL>http://localhost:80/EbizSimulate/emulator/Newb2c_Pay_Mer.jsp</merURL><merVAR>test</merVAR></message>");
//		xml.append("<extend><e_isMerFlag>1</e_isMerFlag><e_Name>屿鸦顶</e_Name><e_TelNum>13814444444</e_TelNum><e_CredType>0</e_CredType><e_CredNum>128568580210620</e_CredNum></extend></B2CReq>");
//		
		
//		StringBuffer queryResultxml = new StringBuffer();
//		queryResultxml.append("<?xml version=\"1.0\" encoding=\"GBK\" standalone=\"no\"?>");
//		queryResultxml.append("<ICBCAPI><pub><APIName>接口名称</APIName><APIVersion>接口版本号</APIVersion></pub>");
//		queryResultxml.append("<in><orderNum>订单号</orderNum><tranDate>交易日期</tranDate><ShopCode>商家号码</ShopCode><ShopAccount>商城账号</ShopAccount></in>");
//		queryResultxml.append("<out><tranSerialNum>指令序号</tranSerialNum><tranStat>订单处理状态</tranStat><bankRem>指令错误信息</bankRem><amount>订单总金额</amount><currType>支付币种</currType><tranTime>返回通知日期时间</tranTime><ShopAccount>商城账号</ShopAccount><PayeeName>商城户名</PayeeName><JoinFlag>校验联名标志</JoinFlag><MerJoinFlag>商城联名标志</MerJoinFlag><CustJoinFlag>客户联名标志</CustJoinFlag><CustJoinNum>联名会员号</CustJoinNum><CertID>商户签名证书id</CertID></out></ICBCAPI>");
//
//		Map<String, Object> map = getQueryResultMap(queryResultxml.toString());
//		Set<String> key = map.keySet();
//        for (Iterator it = key.iterator(); it.hasNext();) {
//            String s = (String) it.next();
//            System.out.println(s+"========"+map.get(s));
//        } 
    	
    	SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        System.out.println(format.format(date));
    	
    	
//    	String tranData = "this is a test";
//		System.out.println("<font face='Arial' size='4' color='Green'>明文：</font>"+tranData+"<br>");
//		String password = "12345678";
//		try{
//			byte[] byteSrc = tranData.getBytes();
//			char[] keyPass = password.toCharArray();
//			
//			FileInputStream in1 = new FileInputStream("F:\\bank\\icbc\\cer\\test03.crt");
//			byte[] bcert = new byte[in1.available()];
//			in1.read(bcert);
//			in1.close();
//			FileInputStream in2 = new FileInputStream("F:\\bank\\icbc\\cer\\test03.key");
//			byte[] bkey = new byte[in2.available()];
//			in2.read(bkey);
//			in2.close();
//			
//
//		    byte[] sign =ReturnValue.sign(byteSrc,byteSrc.length,bkey,keyPass);
//		    if (sign==null) {
//		    	System.out.println("<font face='Arial' size='4' color='Red'>签名失败,签名返回为空。<br>请检查证书私钥和私钥保护口令是否正确。</font><br>");
//		    }else{
//		    	System.out.println("<font face='Arial' size='4' color='Green'>签名成功</font><br>");
//		   
//			    byte[] EncSign = ReturnValue.base64enc(sign);
//			    String SignMsgBase64=new String(EncSign).toString();
//			    System.out.println("<font face='Arial' size='4' color='Green'>签名信息BASE64编码：</font>"+SignMsgBase64.substring(0,100)+"...<br>");
//			    
//				byte[] EncCert=ReturnValue.base64enc(bcert);
//				String CertBase64=new String(EncCert).toString();
//				System.out.println("<font face='Arial' size='4' color='Green'>证书公钥BASE64编码：</font>"+CertBase64.substring(0,100)+"...<br>");
//			
//				byte[] DecSign = ReturnValue.base64dec(EncSign);
//			    if (DecSign!=null){
//			    	System.out.println("<font face='Arial' size='4' color='Green'>签名信息BASE64解码成功</font><br>");
//			    	byte[] DecCert = ReturnValue.base64dec(EncCert);
//			    	if (DecCert!=null){
//			    		System.out.println("<font face='Arial' size='4' color='Green'>证书公钥BASE64解码成功</font><br>");
//			    		int a=ReturnValue.verifySign(byteSrc,byteSrc.length,DecCert,DecSign);
//			    		if (a==0) System.out.println("<font face='Arial' size='4' color='Green'>验签成功</font><br>");
//			    		else System.out.println("<font face='Arial' size='4' color='Red'>验签失败<br>验证返回码：</font><br>"+a);	    		
//			    	}else System.out.println("<font face='Arial' size='4' color='Red'>证书BASE64解码失败</font><br>");
//			    }else System.out.println("<font face='Arial' size='4' color='Red'>签名信息BASE64解码失败</font><br>");	
//			}
//			
//		}catch (Exception e){
//			e.printStackTrace();
//		}
    }
}
