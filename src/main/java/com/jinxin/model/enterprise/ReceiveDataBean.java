package com.jinxin.model.enterprise;

import java.io.Serializable;
import java.util.Date;

/**
 * @author eric
 */
public abstract class ReceiveDataBean implements Serializable{

    /**
     * 签名方式
     */
    private String signType;

    /**
     * MD5签名
     */
    private String sign;

    /**
     * key 已在配置文件中配置默认值
     */
    private String key;

    /**
     * 验证URL 已在配置文件中配置默认值
     */
    private String serviceUrl;

    /**
     * 验证服务名称 已在配置文件中配置默认值
     */
    private String serviceName;

    /**
     * 合作伙伴ID 已在配置文件中配置默认值
     */
    private String partnerId;

    /**
     * 接收时间
     */
    private Date receiveDateTime;

    /**
     * 该交易在支付宝系统中的交易流水号
     */
    private String tradeNo;

    /**
     * 该交易在合作伙伴系统的流水号
     */
    private String outTradeNo;

    /**
     * 交易金额
     */
    private String totalFee;

    /**
     * 交易状态
     */
    private String tradeStatus;

    /**
     * 通知ID 网关通知流水号，合作伙伴可以用这个流水号询问网关该条通知的合法性
     */
    private String notifyId;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public Date getReceiveDateTime() {
        return receiveDateTime;
    }

    public void setReceiveDateTime(Date receiveDateTime) {
        this.receiveDateTime = receiveDateTime;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId;
    }

}
