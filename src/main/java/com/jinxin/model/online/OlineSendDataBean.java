package com.jinxin.model.online;

import java.io.Serializable;
import java.util.Date;

/**
 * @author eric
 */
public abstract class OlineSendDataBean implements Serializable{
 
    /**
     * 网关服务Url 必输：Y 已在配置文件中配置默认值
     */
    private String serviceUrl;
    
    /**
     * 订单金额
     */
    private String amount;
    
    /**
     * 订单币种
     */
    private String currency;
    
    /**
     * 支付流水号
     */
    private String trNo;
    
    /**
     * 商户号
     */
    private String merNo;

    /**
     * 签名 必输：Y
     */
    private String sign;

    /**
     * 签名方式 必输：Y 已在配置文件中配置默认值
     */
    private String signType;

    /**
     * 发送时间
     */
    private Date sendDateTime;

	public String getServiceUrl() {
		return serviceUrl;
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getTrNo() {
		return trNo;
	}

	public void setTrNo(String trNo) {
		this.trNo = trNo;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public Date getSendDateTime() {
		return sendDateTime;
	}

	public void setSendDateTime(Date sendDateTime) {
		this.sendDateTime = sendDateTime;
	}

	public String getMerNo() {
		return merNo;
	}

	public void setMerNo(String merNo) {
		this.merNo = merNo;
	}
}
