package com.jinxin.service.paygateway.online.icbc.util;

import com.jinxin.model.online.OlineSendDataBean;

/**
 * @className:AcnSendBean.java
 * @classDescription:
 * @author:erichu
 * @createTime:2014-6-26 下午4:12:18
 */

public class IcbcSendBean extends OlineSendDataBean{
	
	private String interfaceName;
	
	private String interfaceVersion;
	
	private String tranData;

	private String merSignMsg;

	private String merCert;
	
	//必输，格式为：YYYYMMDDHHmmss目前要求在银行系统当前时间的前后十分钟范围内，否则判定交易时间非法。
	private String orderDate;
	
	//必输，每笔订单都需要有不同的订单号；客户支付后商户网站产生的一个唯一的定单号，该订单号应该在相当长的时间内不重复。工行通过订单号加订单日期来唯一确认一笔订单的重复性。
	private String orderid;

	//必输，每笔订单一个；客户支付订单的总金额，一笔订单一个，以分为单位。不可以为零，必需符合金额标准。
	private String amount;
	
	//必输，每笔订单一个；取值：1、3、6、9、12、18、24；1代表全额付款，必须为以上数值，否则订单校验不通过。
	private String installmentTimes;
	
	//必输，每笔订单一个，可以相同；商户入账账号，只能交易时指定。（商户付给银行手续费的账户，可以在开户的时候指定，也可以用交易指定方式；用交易指定方式则使用此商户账号）
	private String merAcct;
	
	//选输，每笔订单一个；
	private String goodsID;
	
	//必输，每笔订单一个；
	private String goodsName;
	
	//选输，每笔订单一个；
	private String goodsNum;
	//选输，每笔订单一个；注：银行支付页面不会向客户显示该项内容
	private String carriageAmt;
	
	//必输， 取值“1”：客户支付时，网银判断该客户是否与商户联名，是则按上送金额扣帐，否则展现未联名错误；取值“0”：不检验客户是否与商户联名，按上送金额扣帐。
	private String verifyJoinFlag;
	
	//选输，默认为中文版取值：“EN_US”为英文版；取值：“ZH_CN”或其他为中文版。注意：大小写敏感。
	private String Language;
	
	//必输，用来区分一笔支付的币种，目前工行只支持使用人民币（001）支付。取值： “001”
	private String curType;
	
	//必输，唯一确定一个商户的代码，由商户在工行开户时，由工行告知商户。
	private String merID;
	
	//必输默认“2”。取值范围为0、1、2，其中0表示仅允许使用借记卡支付，1表示仅允许使用信用卡支付，2表示借记卡和信用卡都能对订单进行支付
	private String creditType;
	
	//必输在交易转账处理完成后把交易结果通知商户的处理模式。取值“HS”：在交易完成后实时将通知信息以HTTP协议POST方式，主动发送给商户，发送地址为商户端随订单数据提交的接收工行支付结果的URL即表单中的merURL字段；取值“AG”：在交易完成后不通知商户。商户需使用浏览器登录工行的B2C商户服务网站，或者使用工行提供的客户端程序API主动获取通知信息。
	private String notifyType;
	
	//选输取值“0”：无论支付成功或者失败，银行都向商户发送交易通知信息；取值“1”，银行只向商户发送交易成功的通知信息。只有通知方式为HS时此值有效，如果使用AG方式，可不上送此项，但签名数据中必须包含此项，取值可为空。
	private String resultType;
	
	//选输，上送商户网站域名（例如：pay.某B2C商城.com），支持首字段通配符（例如：*.某B2C商城.com）。如果上送，工行会在客户支付订单时，校验商户上送域名与客户跳转工行支付页面之前网站域名的一致性
	private String merReference;
	
	//选输，工行在支付页面显示该信息。注意：1、使用IPV4格式。2、上送的是客户端的公网IP。当商户reference项送空时，该项必输。
	private String merCustomIp;
	
	//选输，若输入：取值“0”：虚拟商品；取值“1”，实物商品。
	private String goodsType;
	
	//选输，工行在支付页面显示该信息
	private String merCustomID;
	
	//选输，工行在支付页面显示该信息。
	private String merCustomPhone;
	
	//选输，工行在支付页面显示该信息。
	private String goodsAddress;
	
	//选输，工行在支付页面显示该信息。
	private String merOrderRemark;
	
	//选输
	private String merHint;
	
	//选输 单位：字节
	private String remark1;
	
	//选输 单位：字节
	private String remark2;
	
	//必输必须合法的URL，交易结束，将客户引导到商户的此url，即通过客户浏览器post交易结果信息到商户的此URL注意：该URL应使用http协议（不能使用https协议），端口号应为80或不指定。
	private String merURL;
	
	//选输商户自定义，当返回银行结果时，作为一个隐藏域变量，商户可以用此变量维护session等等。由客户端浏览器支付完成后提交通知结果时是明文传输，建议商户对此变量使用额外安全防范措施，如签名、base64
	private String merVAR;
	
	//选输工银e支付注册标志，标识客户在支付该笔订单时，是否使用订单指定的下述信息注册工银e支付。0-否，1-是
	private String e_isMerFlag;
	
	//选输
	private String e_Name;
	
	//选输如果工银e支付注册标志e_isMerFlag上送1，则此字段必输
	private String e_TelNum;
	
	//选输0:身份证    1:护照      2:军官证    3:士兵证    4:回乡证（港澳台往来通行证）    5:临时身份证6:户口本    7:其他      9:警官证    12:外国人永久居住证21:边民证 
	private String e_CredType;
	
	//选输
	private String e_CredNum;
	
	
	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getInterfaceVersion() {
		return interfaceVersion;
	}

	public void setInterfaceVersion(String interfaceVersion) {
		this.interfaceVersion = interfaceVersion;
	}

	public String getTranData() {
		return tranData;
	}

	public void setTranData(String tranData) {
		this.tranData = tranData;
	}

	public String getMerSignMsg() {
		return merSignMsg;
	}

	public void setMerSignMsg(String merSignMsg) {
		this.merSignMsg = merSignMsg;
	}

	public String getMerCert() {
		return merCert;
	}

	public void setMerCert(String merCert) {
		this.merCert = merCert;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getInstallmentTimes() {
		return installmentTimes;
	}

	public void setInstallmentTimes(String installmentTimes) {
		this.installmentTimes = installmentTimes;
	}

	public String getMerAcct() {
		return merAcct;
	}

	public void setMerAcct(String merAcct) {
		this.merAcct = merAcct;
	}

	public String getGoodsID() {
		return goodsID;
	}

	public void setGoodsID(String goodsID) {
		this.goodsID = goodsID;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(String goodsNum) {
		this.goodsNum = goodsNum;
	}

	public String getCarriageAmt() {
		return carriageAmt;
	}

	public void setCarriageAmt(String carriageAmt) {
		this.carriageAmt = carriageAmt;
	}

	public String getVerifyJoinFlag() {
		return verifyJoinFlag;
	}

	public void setVerifyJoinFlag(String verifyJoinFlag) {
		this.verifyJoinFlag = verifyJoinFlag;
	}

	public String getLanguage() {
		return Language;
	}

	public void setLanguage(String language) {
		Language = language;
	}

	public String getCurType() {
		return curType;
	}

	public void setCurType(String curType) {
		this.curType = curType;
	}

	public String getMerID() {
		return merID;
	}

	public void setMerID(String merID) {
		this.merID = merID;
	}

	public String getCreditType() {
		return creditType;
	}

	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}

	public String getNotifyType() {
		return notifyType;
	}

	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}

	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	public String getMerReference() {
		return merReference;
	}

	public void setMerReference(String merReference) {
		this.merReference = merReference;
	}

	public String getMerCustomIp() {
		return merCustomIp;
	}

	public void setMerCustomIp(String merCustomIp) {
		this.merCustomIp = merCustomIp;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public String getMerCustomID() {
		return merCustomID;
	}

	public void setMerCustomID(String merCustomID) {
		this.merCustomID = merCustomID;
	}

	public String getMerCustomPhone() {
		return merCustomPhone;
	}

	public void setMerCustomPhone(String merCustomPhone) {
		this.merCustomPhone = merCustomPhone;
	}

	public String getGoodsAddress() {
		return goodsAddress;
	}

	public void setGoodsAddress(String goodsAddress) {
		this.goodsAddress = goodsAddress;
	}

	public String getMerOrderRemark() {
		return merOrderRemark;
	}

	public void setMerOrderRemark(String merOrderRemark) {
		this.merOrderRemark = merOrderRemark;
	}

	public String getMerHint() {
		return merHint;
	}

	public void setMerHint(String merHint) {
		this.merHint = merHint;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getMerURL() {
		return merURL;
	}

	public void setMerURL(String merURL) {
		this.merURL = merURL;
	}

	public String getMerVAR() {
		return merVAR;
	}

	public void setMerVAR(String merVAR) {
		this.merVAR = merVAR;
	}

	public String getE_isMerFlag() {
		return e_isMerFlag;
	}

	public void setE_isMerFlag(String e_isMerFlag) {
		this.e_isMerFlag = e_isMerFlag;
	}

	public String getE_Name() {
		return e_Name;
	}

	public void setE_Name(String e_Name) {
		this.e_Name = e_Name;
	}

	public String getE_TelNum() {
		return e_TelNum;
	}

	public void setE_TelNum(String e_TelNum) {
		this.e_TelNum = e_TelNum;
	}

	public String getE_CredType() {
		return e_CredType;
	}

	public void setE_CredType(String e_CredType) {
		this.e_CredType = e_CredType;
	}

	public String getE_CredNum() {
		return e_CredNum;
	}

	public void setE_CredNum(String e_CredNum) {
		this.e_CredNum = e_CredNum;
	}
}
