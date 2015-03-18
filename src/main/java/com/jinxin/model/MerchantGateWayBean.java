package com.jinxin.model;

/**
 * 
 * <p>Title: </p>
 * <p>Description: 商户号以及网关接入号信息Bean</p>
 * <p>Copyright: jinxin (c) 2013 版权</p>
 * <p>Company: </p>
 * @author 
 * @version V1.0 
 * @date 2013-7-5下午02:43:31
 */
public class MerchantGateWayBean implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/** 商户号状态 **/
	private int merNoStatus;
	
	/** 网关接入号状态 **/
	private int gatewayNoStatus;

	/** 网关接入号Key **/
	private String gatewayKey;
	
	/** 网关接入号绑定的接口类型 **/
	private int gatewayInfType;
	
	/** 网关接入号是否VT商户 **/
	private int gatewayIsVt;
	
	/** 网关接入号返回商户模式 **/
	private int gatewayReturnModel;

	public int getMerNoStatus() {
		return merNoStatus;
	}

	public void setMerNoStatus(int merNoStatus) {
		this.merNoStatus = merNoStatus;
	}

	public int getGatewayNoStatus() {
		return gatewayNoStatus;
	}

	public void setGatewayNoStatus(int gatewayNoStatus) {
		this.gatewayNoStatus = gatewayNoStatus;
	}

	public String getGatewayKey() {
		return gatewayKey;
	}

	public void setGatewayKey(String gatewayKey) {
		this.gatewayKey = gatewayKey;
	}

	public int getGatewayInfType() {
		return gatewayInfType;
	}

	public void setGatewayInfType(int gatewayInfType) {
		this.gatewayInfType = gatewayInfType;
	}

	public int getGatewayIsVt() {
		return gatewayIsVt;
	}

	public void setGatewayIsVt(int gatewayIsVt) {
		this.gatewayIsVt = gatewayIsVt;
	}

	public int getGatewayReturnModel() {
		return gatewayReturnModel;
	}

	public void setGatewayReturnModel(int gatewayReturnModel) {
		this.gatewayReturnModel = gatewayReturnModel;
	}
}
