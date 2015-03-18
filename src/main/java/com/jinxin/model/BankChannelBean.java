package com.jinxin.model;

/**
 * 
 * <p>Title: </p>
 * <p>Description: 银行通道参数信息Bean</p>
 * <p>Copyright: jinxin (c) 2013 版权</p>
 * <p>Company: </p>
 * @author 
 * @version V1.0 
 * @date 2013-7-14下午06:27:48
 */
public class BankChannelBean implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/** 卡种 **/
	private int cardType;
	
	/** 银行ID **/
	private int bankId;
	
	/** 银行代码 **/
	private String bankCode;
	
	/** 银行支付地址 **/
	private String bankPayUrl;
	
	/** 本系统银行提交地址 **/
	private String bankReqUrl;
	
	/** 本系统银行提交地址 **/
	private boolean isDirect;
	
	/** 通道代码 **/
	private int channelCode;
	
	/** 通道商户号 **/
	private String channelMerNo;
	
	/** 通道进入码 **/
	private String channelAccessCode;
	
	/** 通道安全码 **/
	private String channelSecureCode;
	
	/** 通道扣率 **/
	private Double channelRate;
	
	/** 通道支付币种**/
	private String channelCurrency;
	
	/** 通道结算银行 **/
	private String channelSettlementBank;
	
	/** 通道是否DCC **/
	private int channelIsDcc;
	
	/** 通道是否延时通道 **/
	private int channelIsDelay;
	
	/** 通道是否支付3方 **/
	private int channelThreeParty;
	
	/** 通道是否支付2.5方 **/
	private int channelTwoFiveParty;
	
	/** CHA_FEECURRENCY 单笔手续费币种 **/
	private String channelFeeCurrency;
	
	/** CHA_FEEAMOUNT 单笔手续费金额 **/
	private Double channelFeeAmount;
			
	/** 通道是否支付2方 **/
	private int channelTwoParty;
	
	/** CHA_FEE_FAIL 失败订单是否收取手续费 1 :是 0:否 **/
	private int channelFeeFail;

	/** CHA_FEE_SUCCESS 结算前成功订单全额异常是否收取手续费 1 :是 0:否  **/
	private int channelFeeSuccess;
	
	/** CHA_FEE_SUCCESS_AFTER 结算后成功订单全额异常是否收取手续费 1 :是 0:否  **/
	private int channelFeeSuccessAfter;
	
	/** CHA_IS_BACK 结算前是否收取异常金额的手续费 1 :是 0:否	 **/
	private int channelIsBack;
	
	/** CHA_IS_BACK_AFTER 结算后是否收取异常金额的手续费 1 :是 0:否	 **/
	private int channelIsBackAfter;
	
	/** 交易扣率 **/
	private Double tradeRate;
	
	/** 保证金扣率 **/
	private Double ReserverRate;
	
	/** 网关接入号单笔手续费币种 **/
	private String merFeeCurrency;
	
	/** 网关接入号单笔手续费金额 **/
	private Double merFeeAmount;
	
	/** MR_FEE_FAIL 失败订单是否收取单笔手续费 1 :是 0:否 **/
	private int merFeeFail;
	
	/** MR_FEE_SUCCESS 划款前成功订单全额异常是否收取单笔单笔手续费 1 :是 0:否 **/
	private int merFeeSuccess;
	
	/** MR_FEE_SUCCESS_AFTER 划款后成功订单全额异常是否收取单笔单笔手续费 1 :是 0:否 **/
	private int merFeeSuccessAfter;
	
	/** MR_IS_BACK 划款前是否收取异常金额的交易手续费 1 :是 0:否 **/
	private int merIsBack;
	
	/** MR_IS_BACK_AFTER 划款后是否收取异常金额的交易手续费 1 :是 0:否 **/
	private int merIsBackAfter;	
	
	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankPayUrl() {
		return bankPayUrl;
	}

	public void setBankPayUrl(String bankPayUrl) {
		this.bankPayUrl = bankPayUrl;
	}

	public String getBankReqUrl() {
		return bankReqUrl;
	}

	public void setBankReqUrl(String bankReqUrl) {
		this.bankReqUrl = bankReqUrl;
	}

	public boolean isDirect() {
		return isDirect;
	}

	public void setDirect(boolean isDirect) {
		this.isDirect = isDirect;
	}

	public int getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(int channelCode) {
		this.channelCode = channelCode;
	}

	public String getChannelMerNo() {
		return channelMerNo;
	}

	public void setChannelMerNo(String channelMerNo) {
		this.channelMerNo = channelMerNo;
	}

	public String getChannelAccessCode() {
		return channelAccessCode;
	}

	public void setChannelAccessCode(String channelAccessCode) {
		this.channelAccessCode = channelAccessCode;
	}

	public String getChannelSecureCode() {
		return channelSecureCode;
	}

	public void setChannelSecureCode(String channelSecureCode) {
		this.channelSecureCode = channelSecureCode;
	}

	public Double getChannelRate() {
		return channelRate;
	}

	public void setChannelRate(Double channelRate) {
		this.channelRate = channelRate;
	}

	public String getChannelCurrency() {
		return channelCurrency;
	}

	public void setChannelCurrency(String channelCurrency) {
		this.channelCurrency = channelCurrency;
	}

	public String getChannelSettlementBank() {
		return channelSettlementBank;
	}

	public void setChannelSettlementBank(String channelSettlementBank) {
		this.channelSettlementBank = channelSettlementBank;
	}

	public int getChannelIsDcc() {
		return channelIsDcc;
	}

	public void setChannelIsDcc(int channelIsDcc) {
		this.channelIsDcc = channelIsDcc;
	}

	public int getChannelIsDelay() {
		return channelIsDelay;
	}

	public void setChannelIsDelay(int channelIsDelay) {
		this.channelIsDelay = channelIsDelay;
	}

	public int getChannelThreeParty() {
		return channelThreeParty;
	}

	public void setChannelThreeParty(int channelThreeParty) {
		this.channelThreeParty = channelThreeParty;
	}

	public int getChannelTwoFiveParty() {
		return channelTwoFiveParty;
	}

	public void setChannelTwoFiveParty(int channelTwoFiveParty) {
		this.channelTwoFiveParty = channelTwoFiveParty;
	}

	public int getChannelTwoParty() {
		return channelTwoParty;
	}

	public void setChannelTwoParty(int channelTwoParty) {
		this.channelTwoParty = channelTwoParty;
	}

	public int getCardType() {
		return cardType;
	}

	public void setCardType(int cardType) {
		this.cardType = cardType;
	}

	public Double getTradeRate() {
		return tradeRate;
	}

	public void setTradeRate(Double tradeRate) {
		this.tradeRate = tradeRate;
	}

	public Double getReserverRate() {
		return ReserverRate;
	}

	public void setReserverRate(Double reserverRate) {
		ReserverRate = reserverRate;
	}

	public String getChannelFeeCurrency() {
		return channelFeeCurrency;
	}

	public void setChannelFeeCurrency(String channelFeeCurrency) {
		this.channelFeeCurrency = channelFeeCurrency;
	}

	public Double getChannelFeeAmount() {
		return channelFeeAmount;
	}

	public void setChannelFeeAmount(Double channelFeeAmount) {
		this.channelFeeAmount = channelFeeAmount;
	}

	public int getChannelFeeFail() {
		return channelFeeFail;
	}

	public void setChannelFeeFail(int channelFeeFail) {
		this.channelFeeFail = channelFeeFail;
	}

	public int getChannelFeeSuccess() {
		return channelFeeSuccess;
	}

	public void setChannelFeeSuccess(int channelFeeSuccess) {
		this.channelFeeSuccess = channelFeeSuccess;
	}

	public int getChannelFeeSuccessAfter() {
		return channelFeeSuccessAfter;
	}

	public void setChannelFeeSuccessAfter(int channelFeeSuccessAfter) {
		this.channelFeeSuccessAfter = channelFeeSuccessAfter;
	}

	public int getChannelIsBack() {
		return channelIsBack;
	}

	public void setChannelIsBack(int channelIsBack) {
		this.channelIsBack = channelIsBack;
	}

	public int getChannelIsBackAfter() {
		return channelIsBackAfter;
	}

	public void setChannelIsBackAfter(int channelIsBackAfter) {
		this.channelIsBackAfter = channelIsBackAfter;
	}

	public String getMerFeeCurrency() {
		return merFeeCurrency;
	}

	public void setMerFeeCurrency(String merFeeCurrency) {
		this.merFeeCurrency = merFeeCurrency;
	}

	public Double getMerFeeAmount() {
		return merFeeAmount;
	}

	public void setMerFeeAmount(Double merFeeAmount) {
		this.merFeeAmount = merFeeAmount;
	}

	public int getMerFeeFail() {
		return merFeeFail;
	}

	public void setMerFeeFail(int merFeeFail) {
		this.merFeeFail = merFeeFail;
	}

	public int getMerFeeSuccess() {
		return merFeeSuccess;
	}

	public void setMerFeeSuccess(int merFeeSuccess) {
		this.merFeeSuccess = merFeeSuccess;
	}

	public int getMerFeeSuccessAfter() {
		return merFeeSuccessAfter;
	}

	public void setMerFeeSuccessAfter(int merFeeSuccessAfter) {
		this.merFeeSuccessAfter = merFeeSuccessAfter;
	}

	public int getMerIsBack() {
		return merIsBack;
	}

	public void setMerIsBack(int merIsBack) {
		this.merIsBack = merIsBack;
	}

	public int getMerIsBackAfter() {
		return merIsBackAfter;
	}

	public void setMerIsBackAfter(int merIsBackAfter) {
		this.merIsBackAfter = merIsBackAfter;
	}
}
