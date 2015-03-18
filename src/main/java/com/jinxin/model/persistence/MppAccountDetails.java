package com.jinxin.model.persistence;

// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.jinxin.common.utils.StringHandleUtils;



/**
 * <p> Title: </p>
 * <p>Description: CCPS_MPP_ ACCOUNTDETAILS映射bean </p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 
 */
@Entity
@Table(name = "CCPS_MPP_ACCOUNTDETAILS")
public class MppAccountDetails implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Fields
	@Id
	@Column(name = "MAD_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CCPS_MPP_ACCOUNTDETAILS_SEQ")      
    @SequenceGenerator(name="CCPS_MPP_ACCOUNTDETAILS_SEQ",allocationSize=1,initialValue=1, sequenceName="CCPS_MPP_ACCOUNTDETAILS_SEQ")
	private Long madId;
	
	// 与商户表关联
	@Column(name = "MAD_MER_NO", nullable = false, precision = 38, scale = 0)
	private Long madMerNo;
	
	// 银行名称
	@Column(name = "MAD_BANKNAME")
	private String madBankname;
	
	// 银行的详细地址
	@Column(name = "MAD_BANKADDRESS")
	private String madBankaddress;
	
	// 开户人姓名
	@Column(name = "MAD_HOLDER")
	private String madHolder;
	
	// 开户人详细地址
	@Column(name = "MAD_HOLDER_ADDRESS")
	private String madHolderAddress;
	
	// 银行电话号码
	@Column(name = "MAD_B_PHONE")
	private String madBPhone;
	
	// 账号
	@Column(name = "MAD_ACCOUNTNUMBER")
	private String madAccountnumber;
	
	// 银行IBAN码
	@Column(name = "MAD_IBANCODE")
	private String madIbancode;
	
	// ABA的路由号码
	@Column(name = "MAD_ABANUMBER")
	private String madAbanumber;
	
	// 1 : 美元 ; 2 : 欧元 ; 3 : 其他
	@Column(name = "MAD_T_CURRENCY", precision = 38, scale = 0)
	private Long madTCurrency;
	
	// 只有币种选择其他的时候先要填写
	@Column(name = "MAD_OTHER_CUR")
	private String madOtherCur;
	
	// 1 : 每天 ; 2 : 每周 ; 3 : 每月 ; 4:: 不详
	@Column(name = "MAD_P_ADVICE", precision = 38, scale = 0)
	private Long madPAdvice;
	
	// 1 : 网上访问事务级别的报告 ; 2 : 邮寄（地址:a运营公司地址b法定公司地址c其他）
	@Column(name = "MAD_P_ADVICETYPE", precision = 38, scale = 0)
	private Long madPAdvicetype;
	
	// 1 : 营公司地址 ; 2 : 定公司地址 ; 3 : 其他
	@Column(name = "MAD_P_MAIL", precision = 38, scale = 0)
	private Long madPMail;
	
	// 选择其他才要求填写
	@Column(name = "MAD_STREET")
	private String madStreet;
	
	// 选择其他才要求填写
	@Column(name = "MAD_CITY")
	private String madCity;
	
	// 选择其他才要求填写
	@Column(name = "MAD_COUNTRY")
	private String madCountry;

	public Long getMadId() {
		return madId;
	}

	public void setMadId(Long madId) {
		this.madId = madId;
	}

	public Long getMadMerNo() {
		return madMerNo;
	}

	public void setMadMerNo(Long madMerNo) {
		this.madMerNo = madMerNo;
	}

	public String getMadBankname() {
		return madBankname;
	}

	public void setMadBankname(String madBankname) {
		this.madBankname = madBankname;
	}

	public String getMadBankaddress() {
		return madBankaddress;
	}

	public void setMadBankaddress(String madBankaddress) {
		this.madBankaddress = madBankaddress;
	}

	public String getMadHolder() {
		return madHolder;
	}

	public void setMadHolder(String madHolder) {
		this.madHolder = madHolder;
	}

	public String getMadHolderAddress() {
		return madHolderAddress;
	}

	public void setMadHolderAddress(String madHolderAddress) {
		this.madHolderAddress = madHolderAddress;
	}

	public String getMadBPhone() {
		return madBPhone;
	}

	public void setMadBPhone(String madBPhone) {
		this.madBPhone = madBPhone;
	}

	public String getMadAccountnumber() {
		return madAccountnumber;
	}

	public void setMadAccountnumber(String madAccountnumber) {
		this.madAccountnumber = madAccountnumber;
	}

	public String getMadIbancode() {
		return madIbancode;
	}

	public void setMadIbancode(String madIbancode) {
		this.madIbancode = madIbancode;
	}

	public String getMadAbanumber() {
		return madAbanumber;
	}

	public void setMadAbanumber(String madAbanumber) {
		this.madAbanumber = madAbanumber;
	}

	public Long getMadTCurrency() {
		return StringHandleUtils.null2Zero(false, madTCurrency);
	}

	public void setMadTCurrency(Long madTCurrency) {
		this.madTCurrency = madTCurrency;
	}

	public String getMadOtherCur() {
		return madOtherCur;
	}

	public void setMadOtherCur(String madOtherCur) {
		this.madOtherCur = madOtherCur;
	}

	public Long getMadPAdvice() {
		return StringHandleUtils.null2Zero(false, madPAdvice);
	}

	public void setMadPAdvice(Long madPAdvice) {
		this.madPAdvice = madPAdvice;
	}

	public Long getMadPAdvicetype() {
		return StringHandleUtils.null2Zero(false, madPAdvicetype);
	}

	public void setMadPAdvicetype(Long madPAdvicetype) {
		this.madPAdvicetype = madPAdvicetype;
	}

	public Long getMadPMail() {
		return StringHandleUtils.null2Zero(false, madPMail);
	}

	public void setMadPMail(Long madPMail) {
		this.madPMail = madPMail;
	}

	public String getMadStreet() {
		return madStreet;
	}

	public void setMadStreet(String madStreet) {
		this.madStreet = madStreet;
	}

	public String getMadCity() {
		return madCity;
	}

	public void setMadCity(String madCity) {
		this.madCity = madCity;
	}

	public String getMadCountry() {
		return madCountry;
	}

	public void setMadCountry(String madCountry) {
		this.madCountry = madCountry;
	}

}