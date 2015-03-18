package com.jinxin.model.persistence;

// default package

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * <p> Title: </p>
 * <p>Description: TranSettlement映射bean </p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 2013623
 */
@Entity
@Table(name = "CCPS_TRAN_SETTLEMENT")
public class TranSettlement implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// Fields
	// tsId 自增(1000,1);批次号
	@Id
	@Column(name = "TS_ID", unique = true, nullable = false, precision = 38, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CCPS_TRAN_SETTLEMENT_SEQ")
	@SequenceGenerator(name = "CCPS_TRAN_SETTLEMENT_SEQ", allocationSize=1,initialValue=1, sequenceName = "CCPS_TRAN_SETTLEMENT_SEQ")
	private Long tsId;

	// 商户号;
	@Column(name = "TS_MER_NO", nullable = false, precision = 38, scale = 0)
	private Long tsMerNo;

	// 商户的开户银行;
	@Column(name = "TS_BANKNAME", nullable = false)
	private String tsBankname;

	// 商户的开户用户名;
	@Column(name = "TS_BANKHOLDER", nullable = false)
	private String tsBankholder;

	// 商户的开户帐号;
	@Column(name = "TS_BANKACCOUNT", nullable = false)
	private String tsBankaccount;

	// 开户账号所对应的币种;
	@Column(name = "TS_BANKCURRENCY", nullable = false)
	private String tsBankcurrency;

	// 划款金额;
	@Column(name = "TS_AMOUNT", nullable = false, precision = 18)
	private BigDecimal tsAmount;

	// 制表人;
	@Column(name = "TS_CREATEACCOUNT", nullable = false)
	private String tsCreateaccount;

	// 制表时间;
	@Column(name = "TS_CREATETIME", nullable = false)
	private Date tsCreatetime;

	// 审核人账号
	@Column(name = "TS_CHECKACCOUNT")
	private String tsCheckaccount;

	// 审核时间
	@Column(name = "TS_CHECKTIME")
	private Date tsChecktime;

	// 复核人
	@Column(name = "TS_RECHECKACCOUNT")
	private String tsRecheckaccount;

	// 复核时间
	@Column(name = "TS_RECHECKTIME")
	private Date tsRechecktime;

	// 状态:-3已取消;-2 复核失败 -1: 审核失败; 0: 待处理; 1 : 已审核待复核 ; 2:已复核待处理 3 :已处理;
	@Column(name = "TS_STATUS", nullable = false, precision = 38, scale = 0)
	private Integer tsStatus;

	// 划款人;
	@Column(name = "TS_SETTACOUNT")
	private String tsSettacount;

	// 划款时间;
	@Column(name = "TS_SETTTIME")
	private Date tsSetttime;

	// 指从本公司划出银行,即从哪个银行划出;
	@Column(name = "TS_SETTBANK")
	private String tsSettbank;

	// 实际划款币种;
	@Column(name = "TS_HKCURRENCY")
	private String tsHkcurrency;

	// 财务划给商户的款,扣除其他费用的金额;
	@Column(name = "TS_FACT_AMOUNT", precision = 18)
	private BigDecimal tsFactAmount;

	// 拒付罚金;
	@Column(name = "TS_PROTEST_AMOUNT", precision = 18)
	private BigDecimal tsProtestAmount;

	// 拒付处理费;
	@Column(name = "TS_PROTEST_FEE", precision = 18)
	private BigDecimal tsProtestFee;

	// 内扣金额;
	@Column(name = "TS_NK_AMOUNT", precision = 18)
	private BigDecimal tsNkAmount;

	// 其它扣款;
	@Column(name = "TS_OTHER_AMOUNT", precision = 18)
	private BigDecimal tsOtherAmount;

	// 备注
	@Column(name = "TS_REMRAK")
	private String tsRemrak;

	//
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tranSettlement")
	private Set<TranSettDetail> tranSettDetails = new HashSet<TranSettDetail>(0);

	public Long getTsId() {
		return tsId;
	}

	public void setTsId(Long tsId) {
		this.tsId = tsId;
	}

	public Long getTsMerNo() {
		return tsMerNo;
	}

	public void setTsMerNo(Long tsMerNo) {
		this.tsMerNo = tsMerNo;
	}

	public String getTsBankname() {
		return tsBankname;
	}

	public void setTsBankname(String tsBankname) {
		this.tsBankname = tsBankname;
	}

	public String getTsBankholder() {
		return tsBankholder;
	}

	public void setTsBankholder(String tsBankholder) {
		this.tsBankholder = tsBankholder;
	}

	public String getTsBankaccount() {
		return tsBankaccount;
	}

	public void setTsBankaccount(String tsBankaccount) {
		this.tsBankaccount = tsBankaccount;
	}

	public String getTsBankcurrency() {
		return tsBankcurrency;
	}

	public void setTsBankcurrency(String tsBankcurrency) {
		this.tsBankcurrency = tsBankcurrency;
	}

	public BigDecimal getTsAmount() {
		return tsAmount;
	}

	public void setTsAmount(BigDecimal tsAmount) {
		this.tsAmount = tsAmount;
	}

	public String getTsCreateaccount() {
		return tsCreateaccount;
	}

	public void setTsCreateaccount(String tsCreateaccount) {
		this.tsCreateaccount = tsCreateaccount;
	}

	public Date getTsCreatetime() {
		return tsCreatetime;
	}

	public void setTsCreatetime(Date tsCreatetime) {
		this.tsCreatetime = tsCreatetime;
	}

	public String getTsCheckaccount() {
		return tsCheckaccount;
	}

	public void setTsCheckaccount(String tsCheckaccount) {
		this.tsCheckaccount = tsCheckaccount;
	}

	public Date getTsChecktime() {
		return tsChecktime;
	}

	public void setTsChecktime(Date tsChecktime) {
		this.tsChecktime = tsChecktime;
	}

	public String getTsRecheckaccount() {
		return tsRecheckaccount;
	}

	public void setTsRecheckaccount(String tsRecheckaccount) {
		this.tsRecheckaccount = tsRecheckaccount;
	}

	public Date getTsRechecktime() {
		return tsRechecktime;
	}

	public void setTsRechecktime(Date tsRechecktime) {
		this.tsRechecktime = tsRechecktime;
	}

	public Integer getTsStatus() {
		return tsStatus;
	}

	public void setTsStatus(Integer tsStatus) {
		this.tsStatus = tsStatus;
	}

	public String getTsSettacount() {
		return tsSettacount;
	}

	public void setTsSettacount(String tsSettacount) {
		this.tsSettacount = tsSettacount;
	}

	public Date getTsSetttime() {
		return tsSetttime;
	}

	public void setTsSetttime(Date tsSetttime) {
		this.tsSetttime = tsSetttime;
	}

	public String getTsSettbank() {
		return tsSettbank;
	}

	public void setTsSettbank(String tsSettbank) {
		this.tsSettbank = tsSettbank;
	}

	public String getTsHkcurrency() {
		return tsHkcurrency;
	}

	public void setTsHkcurrency(String tsHkcurrency) {
		this.tsHkcurrency = tsHkcurrency;
	}

	public BigDecimal getTsFactAmount() {
		return tsFactAmount;
	}

	public void setTsFactAmount(BigDecimal tsFactAmount) {
		this.tsFactAmount = tsFactAmount;
	}

	public BigDecimal getTsProtestAmount() {
		return tsProtestAmount;
	}

	public void setTsProtestAmount(BigDecimal tsProtestAmount) {
		this.tsProtestAmount = tsProtestAmount;
	}

	public BigDecimal getTsProtestFee() {
		return tsProtestFee;
	}

	public void setTsProtestFee(BigDecimal tsProtestFee) {
		this.tsProtestFee = tsProtestFee;
	}

	public BigDecimal getTsNkAmount() {
		return tsNkAmount;
	}

	public void setTsNkAmount(BigDecimal tsNkAmount) {
		this.tsNkAmount = tsNkAmount;
	}

	public BigDecimal getTsOtherAmount() {
		return tsOtherAmount;
	}

	public void setTsOtherAmount(BigDecimal tsOtherAmount) {
		this.tsOtherAmount = tsOtherAmount;
	}

	public String getTsRemrak() {
		return tsRemrak;
	}

	public void setTsRemrak(String tsRemrak) {
		this.tsRemrak = tsRemrak;
	}

	public Set<TranSettDetail> getTranSettDetails() {
		return tranSettDetails;
	}

	public void setTranSettDetails(Set<TranSettDetail> tranSettDetails) {
		this.tranSettDetails = tranSettDetails;
	}

}