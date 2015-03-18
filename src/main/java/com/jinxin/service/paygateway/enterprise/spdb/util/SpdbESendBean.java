package com.jinxin.service.paygateway.enterprise.spdb.util;

import java.math.BigDecimal;

import com.jinxin.model.online.OlineSendDataBean;

/**
 * @className:AcnSendBean.java
 * @classDescription:
 * @author:erichu
 * @createTime:2014-6-26 下午4:12:18
 */

public class SpdbESendBean extends OlineSendDataBean{

		private static final long serialVersionUID = 102542204551456L;

		//交易缩写
		public    String tranAbbr 		=	"";
		//企业客户号
		public  	String MasterID 			= 	"";	
		//商户日期时间
		public  	String MercDtTm 		= 	"";
		//定单号
		public  	String TermSsn			=	"";
		//原交易清算日期
		public  	String OSttDate			=	""; 
		//原网关流水
		public  	String OAcqSsn			=	"";
		//商户号
		public  	String MercCode			=	"";
		//终端号
		public  	String TermCode			=	"";
		//交易金额
		public  	String TranAmt			=	"";
		//交易备注1
		public  	String Remark1				=	"";
		//交易备注2
		public  	String Remark2				=	"";
		//支付交易中，接收交易结果的url，如：http://aaa.bbb.ccc/receive.asp 
		public  	String MercUrl				=	"";
		//订单生产时的IP地址
		public  	String Ip			=	"";
		//二级商户类别
		public  	String SubMercFlag			=	"";
		//二级商户名
		public  	String SubMercName			=	"";
		//二级商品名
		public  	String SubMercGoodsName			=	"";
		//拼装的字符串签名
		public		String sign				="";
		//拼装的原始字符串签名
		public		String signstr			="";
		//网银地址：
		public	    String serviceUrl = "";
		
		public String getServiceUrl() {
			return serviceUrl;
		}
		public void setServiceUrl(String serviceUrl) {
			this.serviceUrl = serviceUrl;
		}
		public String getTranAbbr() {
			return tranAbbr;
		}
		public void setTranAbbr(String tranAbbr1) {
			tranAbbr = tranAbbr1;
		}
		public String getMasterID() {
			return MasterID;
		}
		public void setMasterID(String masterID) {
			MasterID = masterID;
		}
		public String getMercDtTm() {
			return MercDtTm;
		}
		public void setMercDtTm(String mercDtTm) {
			MercDtTm = mercDtTm;
		}
		public String getTermSsn() {
			return TermSsn;
		}
		public void setTermSsn(String termSsn) {
			TermSsn = termSsn;
		}
		public String getOSttDate() {
			return OSttDate;
		}
		public void setOSttDate(String oSttDate) {
			OSttDate = oSttDate;
		}
		public String getOAcqSsn() {
			return OAcqSsn;
		}
		public void setOAcqSsn(String oAcqSsn) {
			OAcqSsn = oAcqSsn;
		}
		public String getMercCode() {
			return MercCode;
		}
		public void setMercCode(String mercCode) {
			MercCode = mercCode;
		}
		public String getTermCode() {
			return TermCode;
		}
		public void setTermCode(String termCode) {
			TermCode = termCode;
		}
		public String getTranAmt() {
			return TranAmt;
		}
		public void setTranAmt(String itranAmt) {
			TranAmt = itranAmt;
		}
		public String getRemark1() {
			return Remark1;
		}
		public void setRemark1(String remark1) {
			Remark1 = remark1;
		}
		public String getRemark2() {
			return Remark2;
		}
		public void setRemark2(String remark2) {
			Remark2 = remark2;
		}
		public String getMercUrl() {
			return MercUrl;
		}
		public void setMercUrl(String mercUrl) {
			MercUrl = mercUrl;
		}
		public String getIp() {
			return Ip;
		}
		public void setIp(String ip) {
			Ip = ip;
		}
		public String getSubMercFlag() {
			return SubMercFlag;
		}
		public void setSubMercFlag(String subMercFlag) {
			SubMercFlag = subMercFlag;
		}
		public String getSubMercName() {
			return SubMercName;
		}
		public void setSubMercName(String subMercName) {
			SubMercName = subMercName;
		}
		public String getSubMercGoodsName() {
			return SubMercGoodsName;
		}
		public void setSubMercGoodsName(String subMercGoodsName) {
			SubMercGoodsName = subMercGoodsName;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		public String getSign() {
			return sign;
		}
		public void setSign(String sign) {
			this.sign = sign;
		}
		public String getSignstr() {
			return signstr;
		}
		public void setSignstr(String signstr) {
			this.signstr = signstr;
		}
		
		
}
