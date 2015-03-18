package com.jinxin.service.paygateway.enterprise.spdb.util;

/**
 * @className:Constants.java
 * @classDescription:
 * @author:erichu
 * @createTime:2014-6-26 下午3:20:47
 */

public class SpdbEConstants {
	
	//交易缩写
	public static   String tranAbbr 		=	"EPER";
	
	//单笔查询
	public static   String OTranAbbr 		=	"IQSR";
	
	//退款
	public static   String RTranAbbr 		=	"IPSR";
	
	//企业客户号
	public static 	String MasterID 			="2000615499";	//测试：2000615499  生产：2011622643
	//商户日期时间 "yyyyMMddhhMMss"
	public static 	String MercDtTm 		= 	"";
	//定单号
	public static 	String TermSsn			=	"";
	//原交易清算日期
	public static 	String OSttDate			=	""; 
	//原网关流水
	public static 	String OAcqSsn			=	"";
	//商户号 上线时就使用正式的商户号
	public static 	String MercCode			=	"983708160001401";//测试：983799990001301  生产：791308160000201;
	//终端号
	public static 	String TermCode			=	"00000000";
	//交易金额
	public static 	String TranAmt			=	"";
	//交易备注1
	public static 	String Remark1				=	"";
	//交易备注2
	public static 	String Remark2				=	"";
	//支付交易中，接收交易结果的url，如：http://aaa.bbb.ccc/receive.asp 
	public static 	String MercUrl				=	"http://119.147.217.149/PWeb/spdbController/frontNotify.do";//
	//订单生产时的IP地址
	public static 	String Ip			=	"";
	//二级商户类别  0-消费虚拟类  1-消费实物类 2-投资理财类
	public static 	String SubMercFlag			=	"1";
	//二级商户名
	public static 	String SubMercName			=	"jinxin";
	//二级商品名
	public static 	String SubMercGoodsName			=	"jinxinTest";

	//测试地址：http://124.74.239.32/payment/main"  生产支付地址  正试地址：https://ebank.spdb.com.cn/payment/main
	public static final String SERVICEURL = "http://124.74.239.32/payment/main";//";

}
