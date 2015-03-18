package com.jinxin.model.persistence;

// default package

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * <p> Title: 记录商户各网关接入号所对应的交易扣率及保证金扣率 (针对网关接入号+银行)</p>
 * <p>Description: CCPS_MER_RATE映射bean </p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 
 */
@Entity
@Table(name = "CCPS_MER_RATE")
public class MerRate implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Fields

	@Id
	@Column(name = "MR_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CCPS_MER_RATE_SEQ")      
    @SequenceGenerator(name="CCPS_MER_RATE_SEQ",allocationSize=1,initialValue=1, sequenceName="CCPS_MER_RATE_SEQ")
	private Long mrId;
	
	// 银行记录ID
	//@ManyToOne
	//@JoinColumn(name = "MR_BANK_ID", nullable = false)
	//private Bank bank;
	// 银行代码
	@Column(name = "MR_BANK_CODE", nullable = false)
	private String mrBankCode;

	
	// 通道代码
	@Column(name = "MR_CHA_CODE", nullable = false, precision = 38, scale = 0)
	private Long mrChaCode;
	
	// 存储的是,卡种ID
	@Column(name = "MR_CARDTYPE", nullable = false, precision = 38, scale = 0)
	private Long mrCardtype;
	
	// 商户号
	@Column(name = "MR_MER_NO", nullable = false, precision = 38, scale = 0)
	private Long mrMerNo;
	
	// 网关接入号
	@Column(name = "MR_GW_NO", nullable = false, precision = 38, scale = 0)
	private Long mrGwNo;
	
	// 交易扣率
	@Column(name = "MR_TRADE_RATE", nullable = false, precision = 18)
	private BigDecimal mrTradeRate;
	
	// 保证金扣率
	@Column(name = "MR_RESERVER_RATE", nullable = false, precision = 18)
	private BigDecimal mrReserverRate;
	
	// 添加人
	@Column(name = "MR_LOGIN_NAME", nullable = false)
	private String mrLoginName;
	
	// 添加时间
	@Column(name = "MR_OPRTIME", nullable = false)
	private Date mrOprtime;
	
	@Column(name = "MR_REMARK", nullable = false)
	private String mrRemark;
	
	
	// 单笔手续费币种
	@Column(name = "MR_FEECURRENCY")
	private String mrFeecurrency;

	// 单笔手续费金额
	@Column(name = "MR_FEEAMOUNT", precision = 18)
	private BigDecimal mrFeeamount;
	
	//失败订单是否收取单笔手续费 1 :是 0:否
	@Column(name = "MR_FEE_FAIL", nullable = false, precision = 38, scale = 0)
	private Integer mrFeeFail;
	//划款前成功订单全额异常是否收取单笔手续费 1 :是 0:否
	@Column(name = "MR_FEE_SUCCESS", nullable = false, precision = 38, scale = 0)
	private Integer mrFeeSuccess;
	
	//划款后成功订单全额异常是否收取单笔手续费 1 :是 0:否
	@Column(name = "MR_FEE_SUCCESS_AFTER", nullable = false, precision = 38, scale = 0)
	private Integer mrFeeSuccessAfter;
	
	//划款前是否收取异常金额的交易手续费 1 :是 0:否
	@Column(name = "MR_IS_BACK", nullable = false, precision = 38, scale = 0)
	private Integer mrIsBack;
	
	//划款后是否收取异常金额的交易手续费 1 :是 0:否
	@Column(name = "MR_IS_BACK_AFTER", nullable = false, precision = 38, scale = 0)
	private Integer mrIsBackAfter;
	

	public Long getMrId() {
		return mrId;
	}

	public void setMrId(Long mrId) {
		this.mrId = mrId;
	}

	public String getMrBankCode() {
		return mrBankCode;
	}

	public void setMrBankCode(String mrBankCode) {
		this.mrBankCode = mrBankCode;
	}

	public Long getMrChaCode() {
		return mrChaCode;
	}

	public void setMrChaCode(Long mrChaCode) {
		this.mrChaCode = mrChaCode;
	}

	public Long getMrCardtype() {
		return mrCardtype;
	}

	public void setMrCardtype(Long mrCardtype) {
		this.mrCardtype = mrCardtype;
	}

	public Long getMrMerNo() {
		return mrMerNo;
	}

	public void setMrMerNo(Long mrMerNo) {
		this.mrMerNo = mrMerNo;
	}

	public Long getMrGwNo() {
		return mrGwNo;
	}

	public void setMrGwNo(Long mrGwNo) {
		this.mrGwNo = mrGwNo;
	}

	public BigDecimal getMrTradeRate() {
		return mrTradeRate;
	}

	public void setMrTradeRate(BigDecimal mrTradeRate) {
		this.mrTradeRate = mrTradeRate;
	}

	public BigDecimal getMrReserverRate() {
		return mrReserverRate;
	}

	public void setMrReserverRate(BigDecimal mrReserverRate) {
		this.mrReserverRate = mrReserverRate;
	}

	public String getMrLoginName() {
		return mrLoginName;
	}

	public void setMrLoginName(String mrLoginName) {
		this.mrLoginName = mrLoginName;
	}

	public Date getMrOprtime() {
		return mrOprtime;
	}

	public void setMrOprtime(Date mrOprtime) {
		this.mrOprtime = mrOprtime;
	}

	public String getMrRemark() {
		return mrRemark;
	}

	public void setMrRemark(String mrRemark) {
		this.mrRemark = mrRemark;
	}

	public String getMrFeecurrency() {
		return mrFeecurrency;
	}

	public void setMrFeecurrency(String mrFeecurrency) {
		this.mrFeecurrency = mrFeecurrency;
	}

	public BigDecimal getMrFeeamount() {
		return mrFeeamount;
	}

	public void setMrFeeamount(BigDecimal mrFeeamount) {
		this.mrFeeamount = mrFeeamount;
	}

	 
	public Integer getMrIsBackAfter() {
		return mrIsBackAfter;
	}

	public void setMrIsBackAfter(Integer mrIsBackAfter) {
		this.mrIsBackAfter = mrIsBackAfter;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getMrFeeFail() {
		return mrFeeFail;
	}

	public void setMrFeeFail(Integer mrFeeFail) {
		this.mrFeeFail = mrFeeFail;
	}

	public Integer getMrFeeSuccess() {
		return mrFeeSuccess;
	}

	public void setMrFeeSuccess(Integer mrFeeSuccess) {
		this.mrFeeSuccess = mrFeeSuccess;
	}

	public Integer getMrFeeSuccessAfter() {
		return mrFeeSuccessAfter;
	}

	public void setMrFeeSuccessAfter(Integer mrFeeSuccessAfter) {
		this.mrFeeSuccessAfter = mrFeeSuccessAfter;
	}

	public Integer getMrIsBack() {
		return mrIsBack;
	}

	public void setMrIsBack(Integer mrIsBack) {
		this.mrIsBack = mrIsBack;
	}
	
	
	
	
 

}