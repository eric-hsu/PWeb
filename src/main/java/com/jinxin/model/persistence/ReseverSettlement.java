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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * <p>Title:</p>
 * <p>Description: CCPS_RESEVER_SETTLEMENT映射bean</p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 
 */
@Entity
@Table(name="CCPS_RESEVER_SETTLEMENT")

public class ReseverSettlement  implements java.io.Serializable {    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//自增(1000000,1);批次号ID
	@Id 
    @Column(name="RS_ID", unique=true, nullable=false, precision=38, scale=0)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CCPS_RESEVER_SETTLEMENT_SEQ")      
    @SequenceGenerator(name="CCPS_RESEVER_SETTLEMENT_SEQ",allocationSize=1,initialValue=1, sequenceName="CCPS_RESEVER_SETTLEMENT_SEQ")
     private Long rsId;
	
	//商户号;
	@Column(name="RS_MER_NO", nullable=false, precision=38, scale=0)
     private Long rsMerNo;
	
	//开户银行;
	@Column(name="RS_BANKNAME", nullable=false)
     private String rsBankname;
	
	//开户用户名;
	@Column(name="RS_BANKHOLDER", nullable=false)
     private String rsBankholder;
	
	//开户帐号;
	@Column(name="RS_BANKACCOUNT", nullable=false)
     private String rsBankaccount;
	
	//开户账号所对应的币种;
	@Column(name="RS_BANKCURRENCY", nullable=false)
     private String rsBankcurrency;
	
	//划款金额;
	@Column(name="RS_AMOUNT", nullable=false, precision=18)
     private BigDecimal rsAmount;
//	
//	//交易起始时间;
//	@Temporal(TemporalType.TIMESTAMP)
//    @Column(name="RS_STARTDATE", length=7)
//     private Date rsStartdate;
//	
//	//交易结束时间;
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name="RS_ENDDATE", length=7)
//     private Date rsEnddate;
    
    //制表人;
    @Column(name="RS_CREATEACCOUNT", nullable=false)
     private String rsCreateaccount;
    
    //制表时间;
    @Column(name="RS_CREATETIME", nullable=false)
     private Date rsCreatetime;
    
    //审核人账号
    @Column(name="RS_CHECKACCOUNT")
     private String rsCheckaccount;
    
    //审核时间
    @Column(name="RS_CHECKTIME")
     private Date rsChecktime;
    
    //复核人账号
    @Column(name="RS_RECHECKACCOUNT")
     private String rsRecheckaccount;
    
    //复核时间
    @Column(name="RS_RECHECKTIME")
     private Date rsRechecktime;
    
    //划款人;
    @Column(name="RS_SETACOUNT")
     private String rsSetacount;
    
    //划款时间;
    @Column(name="RS_SETTIME")
     private Date rsSettime;
    
    //从哪个银行划出;
    @Column(name="RS_SETBANK")
     private String rsSetbank;
    
    //实际划款币种;
    @Column(name="RS_SETCURRENCY")
     private String rsSetcurrency;
    
    //财务划给商户的款,扣除其他费用的金额;
    @Column(name="RS_FACT_AMOUNT", precision=18)
     private BigDecimal rsFactAmount;
    
   //交易欠款币种;
    @Column(name="RS_TRADEDEBT_CURRENCY")
     private String rsTradeDebtCurrency;
    
    //交易欠款
    @Column(name="RS_TRADEDEBT", precision=18)
     private BigDecimal rsTradeDebt;
    
    //划款备注;
    @Column(name="RS_REMRAK")
     private String rsRemrak;
    
    //-3已取消;-2 复核失败 -1:  审核失败; 0: 待处理;  1 : 已审核待复核 ;  2:已复核待处理 3 :已处理;
    @Column(name="RS_STATUS", nullable=false, precision=38, scale=0)
     private Integer rsStatus;
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="reseverSettlement")
     private Set<ReseverSettDetail> reseverSettDetails = new HashSet<ReseverSettDetail>(0);

	public Long getRsId() {
		return rsId;
	}

	public void setRsId(Long rsId) {
		this.rsId = rsId;
	}

	public Long getRsMerNo() {
		return rsMerNo;
	}

	public void setRsMerNo(Long rsMerNo) {
		this.rsMerNo = rsMerNo;
	}

	public String getRsBankname() {
		return rsBankname;
	}

	public void setRsBankname(String rsBankname) {
		this.rsBankname = rsBankname;
	}

	public String getRsBankholder() {
		return rsBankholder;
	}

	public void setRsBankholder(String rsBankholder) {
		this.rsBankholder = rsBankholder;
	}

	public String getRsBankaccount() {
		return rsBankaccount;
	}

	public void setRsBankaccount(String rsBankaccount) {
		this.rsBankaccount = rsBankaccount;
	}

	public String getRsBankcurrency() {
		return rsBankcurrency;
	}

	public void setRsBankcurrency(String rsBankcurrency) {
		this.rsBankcurrency = rsBankcurrency;
	}

	public BigDecimal getRsAmount() {
		return rsAmount;
	}

	public void setRsAmount(BigDecimal rsAmount) {
		this.rsAmount = rsAmount;
	}
	public String getRsCreateaccount() {
		return rsCreateaccount;
	}

	public void setRsCreateaccount(String rsCreateaccount) {
		this.rsCreateaccount = rsCreateaccount;
	}

	public Date getRsCreatetime() {
		return rsCreatetime;
	}

	public void setRsCreatetime(Date rsCreatetime) {
		this.rsCreatetime = rsCreatetime;
	}

	public String getRsCheckaccount() {
		return rsCheckaccount;
	}

	public void setRsCheckaccount(String rsCheckaccount) {
		this.rsCheckaccount = rsCheckaccount;
	}

	public Date getRsChecktime() {
		return rsChecktime;
	}

	public void setRsChecktime(Date rsChecktime) {
		this.rsChecktime = rsChecktime;
	}

	public String getRsRecheckaccount() {
		return rsRecheckaccount;
	}

	public void setRsRecheckaccount(String rsRecheckaccount) {
		this.rsRecheckaccount = rsRecheckaccount;
	}

	public Date getRsRechecktime() {
		return rsRechecktime;
	}

	public void setRsRechecktime(Date rsRechecktime) {
		this.rsRechecktime = rsRechecktime;
	}

	public String getRsSetacount() {
		return rsSetacount;
	}

	public void setRsSetacount(String rsSetacount) {
		this.rsSetacount = rsSetacount;
	}

	public Date getRsSettime() {
		return rsSettime;
	}

	public void setRsSettime(Date rsSettime) {
		this.rsSettime = rsSettime;
	}

	public String getRsSetbank() {
		return rsSetbank;
	}

	public void setRsSetbank(String rsSetbank) {
		this.rsSetbank = rsSetbank;
	}

	public String getRsSetcurrency() {
		return rsSetcurrency;
	}

	public void setRsSetcurrency(String rsSetcurrency) {
		this.rsSetcurrency = rsSetcurrency;
	}

	public BigDecimal getRsFactAmount() {
		return rsFactAmount;
	}

	public void setRsFactAmount(BigDecimal rsFactAmount) {
		this.rsFactAmount = rsFactAmount;
	}

	
	public String getRsTradeDebtCurrency() {
		return rsTradeDebtCurrency;
	}

	public void setRsTradeDebtCurrency(String rsTradeDebtCurrency) {
		this.rsTradeDebtCurrency = rsTradeDebtCurrency;
	}

	public BigDecimal getRsTradeDebt() {
		return rsTradeDebt;
	}

	public void setRsTradeDebt(BigDecimal rsTradeDebt) {
		this.rsTradeDebt = rsTradeDebt;
	}

	public String getRsRemrak() {
		return rsRemrak;
	}

	public void setRsRemrak(String rsRemrak) {
		this.rsRemrak = rsRemrak;
	}

	public Integer getRsStatus() {
		return rsStatus;
	}

	public void setRsStatus(Integer rsStatus) {
		this.rsStatus = rsStatus;
	}

	public Set<ReseverSettDetail> getReseverSettDetails() {
		return reseverSettDetails;
	}

	public void setReseverSettDetails(Set<ReseverSettDetail> reseverSettDetails) {
		this.reseverSettDetails = reseverSettDetails;
	}

    

}