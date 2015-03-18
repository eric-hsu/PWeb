package com.jinxin.model;

/**
 * 
 * <p>Title: </p>
 * <p>Description: 商户绑定的代理商信息bean</p>
 * <p>Copyright: jinxin (c) 2013 版权</p>
 * <p>Company: </p>
 * @author 
 * @version V1.0 
 * @date 2013-8-26下午04:33:52
 */
public class MerchantAentBean implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/** 代理商号 **/
	private int aentNo;

	/** 代理商回佣率 **/
	private Double aentRate;
	
	/** 单笔回佣币种 **/
	private String aentFeeCurreny;
	
	/** 单笔回佣金额 **/
	private Double aentFeeAmount;
	
	/** 失败订单是否收取手续费 1 :是 0:否 **/
	private int aentFeeFail;

	/** 划款前成功订单全额异常是否收取手续费 1 :是 0:否  **/
	private int aentFeeSuccess;
	
	/** 划款后成功订单全额异常是否收取手续费 1 :是 0:否  **/
	private int aentFeeSuccessAfter;
	
	/** 划款前是否收取异常金额的手续费 1 :是 0:否	 **/
	private int aentIsBack;
	
	/** 划款后是否收取异常金额的手续费 1 :是 0:否 **/
	private int aentIsBackAfter;

	public int getAentNo() {
		return aentNo;
	}

	public void setAentNo(int aentNo) {
		this.aentNo = aentNo;
	}

	public Double getAentRate() {
		return aentRate;
	}

	public void setAentRate(Double aentRate) {
		this.aentRate = aentRate;
	}

	public String getAentFeeCurreny() {
		return aentFeeCurreny;
	}

	public void setAentFeeCurreny(String aentFeeCurreny) {
		this.aentFeeCurreny = aentFeeCurreny;
	}

	public Double getAentFeeAmount() {
		return aentFeeAmount;
	}

	public void setAentFeeAmount(Double aentFeeAmount) {
		this.aentFeeAmount = aentFeeAmount;
	}

	public int getAentFeeFail() {
		return aentFeeFail;
	}

	public void setAentFeeFail(int aentFeeFail) {
		this.aentFeeFail = aentFeeFail;
	}

	public int getAentFeeSuccess() {
		return aentFeeSuccess;
	}

	public void setAentFeeSuccess(int aentFeeSuccess) {
		this.aentFeeSuccess = aentFeeSuccess;
	}

	public int getAentFeeSuccessAfter() {
		return aentFeeSuccessAfter;
	}

	public void setAentFeeSuccessAfter(int aentFeeSuccessAfter) {
		this.aentFeeSuccessAfter = aentFeeSuccessAfter;
	}

	public int getAentIsBack() {
		return aentIsBack;
	}

	public void setAentIsBack(int aentIsBack) {
		this.aentIsBack = aentIsBack;
	}

	public int getAentIsBackAfter() {
		return aentIsBackAfter;
	}

	public void setAentIsBackAfter(int aentIsBackAfter) {
		this.aentIsBackAfter = aentIsBackAfter;
	}
}
