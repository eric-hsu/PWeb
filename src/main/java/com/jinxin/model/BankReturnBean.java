package com.jinxin.model;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * <p>Title: </p>
 * <p>Description: 存储银行返回信息的bean</p>
 * <p>Copyright: jinxin (c) 2013 版权</p>
 * <p>Company: </p>
 * @author 
 * @version V1.0 
 * @date 2013-7-27下午02:39:59
 */
public class BankReturnBean implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	// 交易状态 -2 : 待确认 ; -1 : 待处理 ; 0 : 支付失败 ; 1 : 成功 ; 2 : 支付中 ; 3 ：已退款； 4 : 退款中 ； 5 :  退款失败   ； 6 ：已取消 ； 取消失败 
	private int responseStatus;
	
	/** 返回代码 **/
	private String responseCode = "";
	
	/** 返回信息 **/
	private String responseInfo = "";
	
	/** 返回交易金额 **/
	private Double responseAmount;
	
	/** 返回交易金额 **/
	private Double responsePayAmount;
	
	/** 返回交易金额 **/
	private Double responseRefundAmount;
	
	/** 通知方式  0：URL 页面通知1：服务器通知）**/
	private String responseType;
	
	/** 交易时间 **/
	private Timestamp responseTradeTime;
	
	/** 返回系统流水订单号 **/
	private String responseTrdeNo = "";
	
	/** 返回银行订单号 **/
	private String responseBankNo = "";
	
	/** 返回银行查询号 **/
	private String responseQueryNo = "";
	
	/** 返回银行授权号 **/
	private String responseAuthorizeId = "";
	
	/** 返回银行批次号 **/
	private String responseBatchNo = "";
	
	/** 返回银行终端号 **/
	private String responseTerminalNo = "";

	/** 交易信息 **/
	private BankTradeInfoBean tradeInfoBean;
	
	/** 银行通道信息 **/
	private BankChannelBean bankChannel;
	
	/** 发送邮件域名信息 **/
	private DomainInfoBean domainInfo;
	
	/** request **/
	private HttpServletRequest req = null;
	
	/** response **/
	private HttpServletResponse resp = null;
	
	public int getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(int responseStatus) {
		this.responseStatus = responseStatus;
	}

	public String getResponseQueryNo() {
		return responseQueryNo;
	}

	public void setResponseQueryNo(String responseQueryNo) {
		this.responseQueryNo = responseQueryNo;
	}

	public String getResponseTerminalNo() {
		return responseTerminalNo;
	}

	public void setResponseTerminalNo(String responseTerminalNo) {
		this.responseTerminalNo = responseTerminalNo;
	}


	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseInfo() {
		return responseInfo;
	}

	public void setResponseInfo(String responseInfo) {
		this.responseInfo = responseInfo;
	}

	public String getResponseBankNo() {
		return responseBankNo;
	}

	public void setResponseBankNo(String responseBankNo) {
		this.responseBankNo = responseBankNo;
	}

	public String getResponseAuthorizeId() {
		return responseAuthorizeId;
	}

	public void setResponseAuthorizeId(String responseAuthorizeId) {
		this.responseAuthorizeId = responseAuthorizeId;
	}

	public String getResponseBatchNo() {
		return responseBatchNo;
	}

	public void setResponseBatchNo(String responseBatchNo) {
		this.responseBatchNo = responseBatchNo;
	}

	public String getResponseTrdeNo() {
		return responseTrdeNo;
	}

	public void setResponseTrdeNo(String responseTrdeNo) {
		this.responseTrdeNo = responseTrdeNo;
	}

	public BankTradeInfoBean getTradeInfoBean() {
		return tradeInfoBean;
	}

	public void setTradeInfoBean(BankTradeInfoBean tradeInfoBean) {
		this.tradeInfoBean = tradeInfoBean;
	}

	public HttpServletRequest getReq() {
		return req;
	}

	public void setReq(HttpServletRequest req) {
		this.req = req;
	}

	public HttpServletResponse getResp() {
		return resp;
	}

	public void setResp(HttpServletResponse resp) {
		this.resp = resp;
	}

	public BankChannelBean getBankChannel() {
		return bankChannel;
	}

	public void setBankChannel(BankChannelBean bankChannel) {
		this.bankChannel = bankChannel;
	}

	public DomainInfoBean getDomainInfo() {
		return domainInfo;
	}

	public void setDomainInfo(DomainInfoBean domainInfo) {
		this.domainInfo = domainInfo;
	}

	public Double getResponseAmount() {
		return responseAmount;
	}

	public void setResponseAmount(Double responseAmount) {
		this.responseAmount = responseAmount;
	}

	public Timestamp getResponseTradeTime() {
		return responseTradeTime;
	}

	public void setResponseTradeTime(Timestamp responseTradeTime) {
		this.responseTradeTime = responseTradeTime;
	}

	public String getResponseType() {
		return responseType;
	}

	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}

	public Double getResponseRefundAmount() {
		return responseRefundAmount;
	}

	public void setResponseRefundAmount(Double responseRefundAmount) {
		this.responseRefundAmount = responseRefundAmount;
	}

	public Double getResponsePayAmount() {
		return responsePayAmount;
	}

	public void setResponsePayAmount(Double responsePayAmount) {
		this.responsePayAmount = responsePayAmount;
	}
}
