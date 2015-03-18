package com.jinxin.service.paygateway.online.icbc.util;

import java.io.IOException;
import java.util.Properties;

import com.jinxin.service.paygateway.online.abc.util.AbcConstants;


/**
 * @className:Constants.java
 * @classDescription:
 * @author:erichu
 * @createTime:2014-6-26 下午3:20:47
 */

public class IcbcConstants {

	//支付地址 测试：https://210.82.37.103/servlet/ICBCINBSEBusinessServlet    正式 https://b2c.icbc.com.cn/servlet/ICBCINBSEBusinessServlet  https://mybank3.dccnet.com.cn/servlet/NewB2cMerPayReqServlet
	public static final String SERVICEURL = "https://mybank3.dccnet.com.cn/servlet/NewB2cMerPayReqServlet";
	
	//支付地址 测试:https://210.82.37.108/servlet/ICBCINBSEBusinessServlet    正式: https://corporbank.icbc.com.cn/servlet/ICBCINBSEBusinessServlet
	public static final String QUERYURL = "https://corporbank3.dccnet.com.cn/servlet/ICBCINBSEBusinessServlet";

	//上线修改  必输，每笔订单一个，可以相同；商户入账账号，只能交易时指定。（商户付给银行手续费的账户，可以在开户的时候指定，也可以用交易指定方式；用交易指定方式则使用此商户账号）
	public static final  String  MERACCT="4000023029200124946";
	//上线修改 必输，唯一确定一个商户的代码，由商户在工行开户时，由工行告知商户。
	public static final String  MERID = "4000EC24086825";//4000EC24086825  4000EC24107841
	
	//上线修改 cert公钥名称
	public static final String   CERTNAME = "test03.crt";
	//上线修改 key私钥名称
	public static final String   KEYNAME = "test03.key";
	//上线修改  私钥test03.key秘钥
	public static final String   PASSWORD = "12345678";
	//上线修改ssl pfx证书名称
	public static final String   PFXNAME = "test03.pfx";
	//上线修改ssl test03.pfx证书密钥
	public static final String   PFXPASSWORD = "123456";
		
	//接口名称
	public static final  String  INTERFACENAME="ICBC_PERBANK_B2C";

	//接口版本号 1.0.0.11（分期付款接口）    1.0.0.3
	public static final  String  INTERFACEVERSION="1.0.0.11";

	//必输， 取值“1”：客户支付时，网银判断该客户是否与商户联名，是则按上送金额扣帐，否则展现未联名错误； 取值“0”：不检验客户是否与商户联名，按上送金额扣帐。
	public static final String   VERIFYJOINFLAG = "0";
	
	//选输，默认为中文版取值：“EN_US”为英文版；取值：“ZH_CN”或其他为中文版。注意：大小写敏感。
	public static final String  LANGUAGE = "ZH_CN";

	//必输，用来区分一笔支付的币种，目前工行只支持使用人民币（001）支付。 取值： “001”
	public static final String  CURTYPE = "001";

	//必输，每笔订单一个；取值：1、3、6、9、12、18、24；1代表全额付款，必须为以上数值，否则订单校验不通过。
	public static final String  INSTALLMENTTIMES = "1";
	
	//必输默认“2”。取值范围为0、1、2，其中0表示仅允许使用借记卡支付，1表示仅允许使用信用卡支付，2表示借记卡和信用卡都能对订单进行支付
	public static final String  CREDITTYPE = "2";
	
	//必输在交易转账处理完成后把交易结果通知商户的处理模式。取值“HS”：在交易完成后实时将通知信息以HTTP协议POST方式，主动发送给商户，发送地址为商户端随订单数据提交的接收工行支付结果的URL即表单中的merURL字段；取值“AG”：在交易完成后不通知商户。商户需使用浏览器登录工行的B2C商户服务网站，或者使用工行提供的客户端程序API主动获取通知信息。
	public static final String   NOTIFYTYPE = "HS";
	
	//选输取值“0”：无论支付成功或者失败，银行都向商户发送交易通知信息；取值“1”，银行只向商户发送交易成功的通知信息。只有通知方式为HS时此值有效，如果使用AG方式，可不上送此项，但签名数据中必须包含此项，取值可为空。
	public static final String   RESULTTYPE = "0";
	
	//必输，每笔订单一个；
	public static final String    GOODSNAME = "jinxinDefualGoods";

}
