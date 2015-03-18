package com.jinxin.service.paygateway.enterprise.citic.util;

import java.io.IOException;
import java.util.Properties;

import com.jinxin.service.paygateway.online.abc.util.AbcConstants;


/**
 * @className:Constants.java
 * @classDescription:
 * @author:erichu
 * @createTime:2014-6-26 下午3:20:47
 */

public class CiticEConstants {

	//支付地址 测试：https://ec.test.bank.ecitic.com/cec/gateway.do    正式https://b2b.bank.ecitic.com/cec/gateway.do
	public static final String SERVICEURL = "https://ec.test.bank.ecitic.com/cec/gateway.do";
	
	//查询地址 测试：https://b2b.test.bank.ecitic.com/WebDLink/b2bqryorderbyno.do  正式：https://b2b.bank.ecitic.com/WebDLink/b2bqryorderbyno.do
	public static final String QUERYURL = "https://b2b.test.bank.ecitic.com/WebDLink/b2bqryorderbyno.do";

	//接口版本号
	public static final  String VERSION="3.0";
	
	//收款账号
	public static final  String PAYEEACCNO="7111010182600250700";
	
	//交易号必输。ECGTPODR，用于描述业务功能类型。
	public static final  String BIZCODE="ECGTPODR";
	
	//交易号必输。ECGTPODR，用于描述业务功能类型。
	public static final  String USERNAME="sfsh006";
	
	//商户编号
	public static final  String MCTNO="10001444";//?

	//通知类型必输。用于描述接收支付结果信息的通知方式1 - 浏览器重定向；2 - 浏览器重定向，并有服务器点对点通讯
	public static final String  NTFTYPE = "2";
	
	//应答类型必输。用于描述支付通知应答的方式。默认为不需要应答。0 - 不需要应答。收到以HTTP协议响应码200则认为通知成功，其他为通知失败；1 - 需要应答。收到支付成功通知表示成功，否则触发重发
	public static final String RSPTYPE = "0";
	//必输。数值单位为分钟。
	public static final String TRANPERIOD = "3";

	//业务编号 必输。00：第三方支付
	public static final String BSNNO = "00";

	//币种，必输。ISO 4217中字母货币代码。
	public static final String CRYCODE = "CNY";

}
