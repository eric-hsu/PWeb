package com.jinxin.model;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: </p>
 * <p>Description: 接收商户传过来的参数Bean:包含三个接口的</p>
 * <p>Copyright: jinxin (c) 2013 版权</p>
 * <p>Company: </p>
 * @author 
 * @version V1.0 
 * @date 2013-4-29下午02:17:53
 */
public class InterfaceParamBean implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
		
	/** 商户号 **/
	private String merNo;
	
	/** 网关接入号 **/
	private String gatewayNo;
	
	/** 商户订单号 **/
	private String orderNo;
	
	private String type; //1 表示测试订单    2  表示正式订单
	
	/** 商户订单号 **/
	private String tradeNo;
	
	/** 商户订单金额 **/
	private String orderAmount;
	
	/** 商户订单币种 **/
	private String orderCurrency;
	
	/** 商户订单提交地址 **/
	private String webSite;	
	
	/** 商户订单返回地址 **/
	private String returnUrl;
	
	/** 信用卡卡号 **/
	private String cardNo;
	
	/** 信用卡有效月份 **/
	private String cardExpireMonth;
	
	/** 信用卡有效年份 **/
	private String cardExpireYear;
	
	/** 信用卡安全码 **/
	private String cardSecurityCode;
	
	/** 信用卡发卡行 **/
	private String issuingBank;
	
	/** 持卡人名 **/
	private String firstName;
	
	/** 持卡人姓 **/
	private String lastName;
	
	/** 持卡人邮箱 **/
	private String email;
	
	/** 持卡人IP，2方商户传送过来 **/
	private String ip;
	
	/** 商户IP:2方时持卡人IP由商户传送过来 **/
	private String merIp;
	
	/** 持卡人IP值 **/
	private long ipValue;
	
	/** 持卡人IP **/
	private String ipCountry;
	
	/** 持卡人电话 **/
	private String phone;
	
	/** 持卡人国家 **/
	private String country;
	
	/** 持卡人所在省(州) **/
	private String state;
	
	/** 持卡人城市 **/
	private String city;
	
	/** 持卡人地址 **/
	private String address;
	
	/** 持卡人邮编 **/
	private String zip;
	
	/** 加密值 **/
	private String signInfo;
	
	/** 订单备注 **/
	private String remark;
	
	/** 商户附加参数 **/
	private List<ExtraElementBean> extraParam = new ArrayList<ExtraElementBean>();
	
	public String getMerNo() {
		return merNo;
	}
	public void setMerNo(String merNo) {
		this.merNo = merNo;
	}
	public String getGatewayNo() {
		return gatewayNo;
	}
	public void setGatewayNo(String gatewayNo) {
		this.gatewayNo = gatewayNo;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getOrderCurrency() {
		return orderCurrency;
	}
	public void setOrderCurrency(String orderCurrency) {
		this.orderCurrency = orderCurrency;
	}
	public String getWebSite() {
		return webSite;
	}
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
	public String getReturnUrl() {
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getCardExpireMonth() {
		return cardExpireMonth;
	}
	public void setCardExpireMonth(String cardExpireMonth) {
		this.cardExpireMonth = cardExpireMonth;
	}
	public String getCardExpireYear() {
		return cardExpireYear;
	}
	public void setCardExpireYear(String cardExpireYear) {
		this.cardExpireYear = cardExpireYear;
	}
	public String getCardSecurityCode() {
		return cardSecurityCode;
	}
	public void setCardSecurityCode(String cardSecurityCode) {
		this.cardSecurityCode = cardSecurityCode;
	}
	public String getIssuingBank() {
		return issuingBank;
	}
	public void setIssuingBank(String issuingBank) {
		this.issuingBank = issuingBank;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getSignInfo() {
		return signInfo;
	}
	public void setSignInfo(String signInfo) {
		this.signInfo = signInfo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<ExtraElementBean> getExtraParam() {
		return extraParam;
	}
	public void setExtraParam(List<ExtraElementBean> extraParam) {
		this.extraParam = extraParam;
	}
	public long getIpValue() {
		return ipValue;
	}
	public void setIpValue(long ipValue) {
		this.ipValue = ipValue;
	}
	public String getIpCountry() {
		return ipCountry;
	}
	public void setIpCountry(String ipCountry) {
		this.ipCountry = ipCountry;
	}
	public String getMerIp() {
		return merIp;
	}
	public void setMerIp(String merIp) {
		this.merIp = merIp;
	}

	public String getTradeNo() {
		return this.tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
