package com.jinxin.service.paygateway.online.icbc.util;

import com.jinxin.model.online.OlineSendDataBean;

/**
 * @className:AcnSendBean.java
 * @classDescription:
 * @author:erichu
 * @createTime:2014-6-26 下午4:12:18
 */

public class IcbcReceiveDataBean extends OlineSendDataBean{
	
	private String interfaceName;
	
	private String interfaceVersion;
	
	private String orderDate;

	private String orderid;

	private String amount;
	
	//每笔订单一个；取值：1、3、6、9、12、18、24；1代表全额付款，必须为以上数值，否则订单校验不通过。
	private String installmentTimes;
	
	//商户收费入账账号 （只能交易时指定）。
	private String merAcct;

	//银行端指令流水号
	private String TranSerialNo;
	
	//用来区分一笔支付的币种，目前工行只支持使用人民币（001）支付。取值： “001”
	private String curType;
	
	//唯一确定一个商户的代码，由商户在工行开户时，由工行告知商户。
	private String merID;
	
	//取值“1”：客户支付时，网银判断该客户是否与商户联名，是则按上送金额扣帐，否则展现未联名错误；取值“0”：不检验客户是否与商户联名，按上送金额扣帐。
	private String verifyJoinFlag;
	
	//客户在银行端是否与商城联名标志位。1客户联名 0客户未联名
	private String JoinFlag;
	
	//联名客户在商户的会员号
	private String UserNum;
	//银行端为多笔一次提交的指令生成一个唯一的批次号
	private String TranBatchNo;
	
	//格式为：YYYYMMDDHHmmss
	private String notifyDate;
	
	//1-“交易成功，已清算”；2-“交易失败”；3-“交易可疑”
	private String tranStat;
	
	//错误描述
	private String comment;
	
	//订单指定e支付注册时返回包含该字段；客户是否使用订单指定信息完成工银e支付注册并支付订单，1-是，2-否
	private String epayRegResult;

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

	public String getTranSerialNo() {
		return TranSerialNo;
	}

	public void setTranSerialNo(String tranSerialNo) {
		TranSerialNo = tranSerialNo;
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

	public String getVerifyJoinFlag() {
		return verifyJoinFlag;
	}

	public void setVerifyJoinFlag(String verifyJoinFlag) {
		this.verifyJoinFlag = verifyJoinFlag;
	}

	public String getJoinFlag() {
		return JoinFlag;
	}

	public void setJoinFlag(String joinFlag) {
		JoinFlag = joinFlag;
	}

	public String getUserNum() {
		return UserNum;
	}

	public void setUserNum(String userNum) {
		UserNum = userNum;
	}

	public String getTranBatchNo() {
		return TranBatchNo;
	}

	public void setTranBatchNo(String tranBatchNo) {
		TranBatchNo = tranBatchNo;
	}

	public String getNotifyDate() {
		return notifyDate;
	}

	public void setNotifyDate(String notifyDate) {
		this.notifyDate = notifyDate;
	}

	public String getTranStat() {
		return tranStat;
	}

	public void setTranStat(String tranStat) {
		this.tranStat = tranStat;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getEpayRegResult() {
		return epayRegResult;
	}

	public void setEpayRegResult(String epayRegResult) {
		this.epayRegResult = epayRegResult;
	}
}
