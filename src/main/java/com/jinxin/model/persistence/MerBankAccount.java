package com.jinxin.model.persistence;

// default package

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

/**
 * <p> Title: 记录商户已绑定的银行帐户信息</p>
 * <p>Description: CCPS_MER_BANKACCOUNT映射bean </p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 
 */
@Entity
@Table(name = "CCPS_MER_BANKACCOUNT")
public class MerBankAccount implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "MBA_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CCPS_MER_BANKACCOUNT_SEQ")      
    @SequenceGenerator(name="CCPS_MER_BANKACCOUNT_SEQ",allocationSize=1,initialValue=1, sequenceName="CCPS_MER_BANKACCOUNT_SEQ")
	private Long mbaId;
	
	// 商户号
	@ManyToOne
	@JoinColumn(name = "MBA_MER_NO", nullable = false)
	private Merchant merchant;
	
	// 开户银行
	@Column(name = "MBA_BANKNAME", nullable = false)
	private String mbaBankname;
	
	// 开户用户名
	@Column(name = "MBA_BANKHOLDER", nullable = false)
	private String mbaBankholder;
	
	// 开户帐号
	@Column(name = "MBA_BANKACCOUNT", nullable = false)
	private String mbaBankaccount;
	
	// 开户银行地址
	@Column(name = "MBA_BANDADD")
	private String mbaBandadd;
	
	// 开户账号所对应的币种
	@Column(name = "MBA_BANKCURRENCY", nullable = false)
	private String mbaBankcurrency;
	
	// 1 : 是 ;0 :否
	@Column(name = "MBA_ISDEFAULT", nullable = false, precision = 38, scale = 0)
	private Long mbaIsdefault;
	
	// 添加人
	@Column(name = "MBA_LOGIN_NAME")
	private String mbaLoginName;
	
	// 添加时间
	@Column(name = "MBA_OPRTIME", nullable = false)
	private Date mbaOprtime;
	
	// -2未提交;-1:待审核;1 :审核成功;0 审核失败
	@Column(name = "MBA_ISCHECK", nullable = false)
	private Long mbaIscheck;
	
	@Column(name = "MBA_REMARK")
	private String mbaRemark;

	public MerBankAccount() {
	}
	
	public MerBankAccount(Long mbaId) {
		this.mbaId = mbaId;
	}

	public Long getMbaId() {
		return mbaId;
	}

	public void setMbaId(Long mbaId) {
		this.mbaId = mbaId;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public String getMbaBankname() {
		return mbaBankname;
	}

	public void setMbaBankname(String mbaBankname) {
		this.mbaBankname = mbaBankname;
	}

	public String getMbaBankholder() {
		return mbaBankholder;
	}

	public void setMbaBankholder(String mbaBankholder) {
		this.mbaBankholder = mbaBankholder;
	}

	public String getMbaBankaccount() {
		return mbaBankaccount;
	}

	public void setMbaBankaccount(String mbaBankaccount) {
		this.mbaBankaccount = mbaBankaccount;
	}

	public String getMbaBandadd() {
		return mbaBandadd;
	}

	public void setMbaBandadd(String mbaBandadd) {
		this.mbaBandadd = mbaBandadd;
	}

	public String getMbaBankcurrency() {
		return mbaBankcurrency;
	}

	public void setMbaBankcurrency(String mbaBankcurrency) {
		this.mbaBankcurrency = mbaBankcurrency;
	}

	public Long getMbaIsdefault() {
		return mbaIsdefault;
	}

	public void setMbaIsdefault(Long mbaIsdefault) {
		this.mbaIsdefault = mbaIsdefault;
	}

	public String getMbaLoginName() {
		return mbaLoginName;
	}

	public void setMbaLoginName(String mbaLoginName) {
		this.mbaLoginName = mbaLoginName;
	}

	public Date getMbaOprtime() {
		return mbaOprtime;
	}

	public void setMbaOprtime(Date mbaOprtime) {
		this.mbaOprtime = mbaOprtime;
	}

	public String getMbaRemark() {
		return mbaRemark;
	}

	public void setMbaRemark(String mbaRemark) {
		this.mbaRemark = mbaRemark;
	}

	public Long getMbaIscheck() {
		return mbaIscheck;
	}

	public void setMbaIscheck(Long mbaIscheck) {
		this.mbaIscheck = mbaIscheck;
	}

}