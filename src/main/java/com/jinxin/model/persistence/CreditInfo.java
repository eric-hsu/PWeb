package com.jinxin.model.persistence;

// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: 记录交易信息的持卡人的信息实体类
 * </p>
 * <p>
 * Copyright:
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author
 * @version
 * @date
 */
@Entity
@Table(name = "CCPS_CREDITINFO")
public class CreditInfo implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	// 记录ID	
	@Column(name = "CI_ID", nullable = false, precision = 38, scale = 0)
	private String ciId;
	
	// Fields
	// 关联交易信息表的流水订单号
	@Column(name = "CI_TR_NO", unique = true, nullable = false)	
	private String ciTrNo;
	


	public void setCiId(String ciId) {
		this.ciId = ciId;
	}

	// 邮件
	@Column(name = "CI_EMAIL")
	private String ciEmail;

	// 电话
	@Column(name = "CI_TEL")
	private String ciTel;

	// 名
	@Column(name = "CI_FIRSTNAME")
	private String ciFirstname;

	// 姓
	@Column(name = "CI_LASTNAME")
	private String ciLastname;

	// 所在国家
	@Column(name = "CI_COUNTRY")
	private String ciCountry;

	// 所在州
	@Column(name = "CI_STATE")
	private String ciState;

	// 城市
	@Column(name = "CI_CITY")
	private String ciCity;

	// 邮政编码
	@Column(name = "CI_ZIPCODE")
	private String ciZipcode;

	// 移动电话
	@Column(name = "CI_CELLPNONE")
	private String ciCellpnone;

	// 地址
	@Column(name = "CI_ADDRESS")
	private String ciAddress;

	// 持卡人IP地址
	@Column(name = "CI_IPADDRESS")
	private String ciIpaddress;

	// 持卡人IP地址所在国家
	@Column(name = "CI_IPCOUNTRY")
	private String ciIpcountry;
	
	// 商户IP地址
	@Column(name = "CI_MER_IPADDRESS")
	private String ciMerIpaddress;

	// 发卡行
	@Column(name = "CI_ISSUINGBANK")
	private String ciIssuingbank;

	// 发卡行电话
	@Column(name = "CI_ISSUINGBANKTEL")
	private String ciIssuingbanktel;

	// 由银行返回
	@Column(name = "CI_CARDCOUNTRY")
	private String ciCardcountry;

	// 支付成功后发送邮件的支付域名
	@Column(name = "CI_FROMNAME")
	private String ciFromname;

	// 支付成功后发送邮件的账单显示地址
	@Column(name = "CI_ACQUIRER")
	private String ciAcquirer;

	// 是否发送过邮件
	@Column(name = "CI_ISSENDEMAIL", precision = 38, scale = 0)
	private Long ciIssendemail;

	// 操作系统
	@Column(name = "CI_OS")
	private String ciOs;

	// 浏览器
	@Column(name = "CI_BROWER")
	private String ciBrower;

	// 语言
	@Column(name = "CI_LANG")
	private String ciLang;

	// 时区
	@Column(name = "CI_TIMEZONE")
	private String ciTimezone;

	// 分辨率
	@Column(name = "CI_RESOLUTION")
	private String ciResolution;

	// COOKIEID
	@Column(name = "CI_COOKIEID")
	private String ciCookieid;

	// 旧cookie信息
	@Column(name = "CI_OLD_COOKIE")
	private String ciOldCookie;

	// 新旧cookie是否一致
	@Column(name = "CI_COOKIE_FLAG", precision = 38, scale = 0)
	private Long ciCookieFlag;

	// SHA256
	@Column(name = "CI_SHA256")
	private String ciSha256;

	// 截断卡号
	@Column(name = "CI_CARDNOPART")
	private String ciCardnopart;

	// 卡种
	@Column(name = "CI_CARDTYPE", precision = 38, scale = 0)
	private Long ciCardtype;

	// 备注
	@Column(name = "CI_REMARK")
	private String ciRemark;

    @Transient
    private String cVV;//持卡人cvv
    @Transient
    private String validity;//有效期

	public String getCVV() {
		return cVV;
	}

	public void setCVV(String cvv) {
		cVV = cvv;
	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

	/**
	 * @return the ciTrNo
	 */
	public String getCiTrNo() {
		return ciTrNo;
	}

	/**
	 * @param ciTrNo the ciTrNo to set
	 */
	public void setCiTrNo(String ciTrNo) {
		this.ciTrNo = ciTrNo;
	}

	/**
	 * @return the ciEmail
	 */
	public String getCiEmail() {
		return ciEmail;
	}

	/**
	 * @param ciEmail the ciEmail to set
	 */
	public void setCiEmail(String ciEmail) {
		this.ciEmail = ciEmail;
	}

	/**
	 * @return the ciTel
	 */
	public String getCiTel() {
		return ciTel;
	}

	/**
	 * @param ciTel the ciTel to set
	 */
	public void setCiTel(String ciTel) {
		this.ciTel = ciTel;
	}

	/**
	 * @return the ciFirstname
	 */
	public String getCiFirstname() {
		return ciFirstname;
	}

	/**
	 * @param ciFirstname the ciFirstname to set
	 */
	public void setCiFirstname(String ciFirstname) {
		this.ciFirstname = ciFirstname;
	}

	/**
	 * @return the ciLastname
	 */
	public String getCiLastname() {
		return ciLastname;
	}

	/**
	 * @param ciLastname the ciLastname to set
	 */
	public void setCiLastname(String ciLastname) {
		this.ciLastname = ciLastname;
	}

	/**
	 * @return the ciCountry
	 */
	public String getCiCountry() {
		return ciCountry;
	}

	/**
	 * @param ciCountry the ciCountry to set
	 */
	public void setCiCountry(String ciCountry) {
		this.ciCountry = ciCountry;
	}

	/**
	 * @return the ciState
	 */
	public String getCiState() {
		return ciState;
	}

	/**
	 * @param ciState the ciState to set
	 */
	public void setCiState(String ciState) {
		this.ciState = ciState;
	}

	/**
	 * @return the ciCity
	 */
	public String getCiCity() {
		return ciCity;
	}

	/**
	 * @param ciCity the ciCity to set
	 */
	public void setCiCity(String ciCity) {
		this.ciCity = ciCity;
	}

	/**
	 * @return the ciZipcode
	 */
	public String getCiZipcode() {
		return ciZipcode;
	}

	/**
	 * @param ciZipcode the ciZipcode to set
	 */
	public void setCiZipcode(String ciZipcode) {
		this.ciZipcode = ciZipcode;
	}

	/**
	 * @return the ciCellpnone
	 */
	public String getCiCellpnone() {
		return ciCellpnone;
	}

	/**
	 * @param ciCellpnone the ciCellpnone to set
	 */
	public void setCiCellpnone(String ciCellpnone) {
		this.ciCellpnone = ciCellpnone;
	}

	/**
	 * @return the ciAddress
	 */
	public String getCiAddress() {
		return ciAddress;
	}

	/**
	 * @param ciAddress the ciAddress to set
	 */
	public void setCiAddress(String ciAddress) {
		this.ciAddress = ciAddress;
	}

	/**
	 * @return the ciIpaddress
	 */
	public String getCiIpaddress() {
		return ciIpaddress;
	}

	/**
	 * @param ciIpaddress the ciIpaddress to set
	 */
	public void setCiIpaddress(String ciIpaddress) {
		this.ciIpaddress = ciIpaddress;
	}

	/**
	 * @return the ciIpcountry
	 */
	public String getCiIpcountry() {
		return ciIpcountry;
	}

	/**
	 * @param ciIpcountry the ciIpcountry to set
	 */
	public void setCiIpcountry(String ciIpcountry) {
		this.ciIpcountry = ciIpcountry;
	}

	/**
	 * @return the ciMerIpaddress
	 */
	public String getCiMerIpaddress() {
		return ciMerIpaddress;
	}

	/**
	 * @param ciMerIpaddress the ciMerIpaddress to set
	 */
	public void setCiMerIpaddress(String ciMerIpaddress) {
		this.ciMerIpaddress = ciMerIpaddress;
	}

	/**
	 * @return the ciIssuingbank
	 */
	public String getCiIssuingbank() {
		return ciIssuingbank;
	}

	/**
	 * @param ciIssuingbank the ciIssuingbank to set
	 */
	public void setCiIssuingbank(String ciIssuingbank) {
		this.ciIssuingbank = ciIssuingbank;
	}

	/**
	 * @return the ciIssuingbanktel
	 */
	public String getCiIssuingbanktel() {
		return ciIssuingbanktel;
	}

	/**
	 * @param ciIssuingbanktel the ciIssuingbanktel to set
	 */
	public void setCiIssuingbanktel(String ciIssuingbanktel) {
		this.ciIssuingbanktel = ciIssuingbanktel;
	}

	/**
	 * @return the ciCardcountry
	 */
	public String getCiCardcountry() {
		return ciCardcountry;
	}

	/**
	 * @param ciCardcountry the ciCardcountry to set
	 */
	public void setCiCardcountry(String ciCardcountry) {
		this.ciCardcountry = ciCardcountry;
	}

	/**
	 * @return the ciFromname
	 */
	public String getCiFromname() {
		return ciFromname;
	}

	/**
	 * @param ciFromname the ciFromname to set
	 */
	public void setCiFromname(String ciFromname) {
		this.ciFromname = ciFromname;
	}

	/**
	 * @return the ciAcquirer
	 */
	public String getCiAcquirer() {
		return ciAcquirer;
	}

	/**
	 * @param ciAcquirer the ciAcquirer to set
	 */
	public void setCiAcquirer(String ciAcquirer) {
		this.ciAcquirer = ciAcquirer;
	}

	/**
	 * @return the ciIssendemail
	 */
	public Long getCiIssendemail() {
		return ciIssendemail;
	}

	/**
	 * @param ciIssendemail the ciIssendemail to set
	 */
	public void setCiIssendemail(Long ciIssendemail) {
		this.ciIssendemail = ciIssendemail;
	}

	/**
	 * @return the ciOs
	 */
	public String getCiOs() {
		return ciOs;
	}

	/**
	 * @param ciOs the ciOs to set
	 */
	public void setCiOs(String ciOs) {
		this.ciOs = ciOs;
	}

	/**
	 * @return the ciBrower
	 */
	public String getCiBrower() {
		return ciBrower;
	}

	/**
	 * @param ciBrower the ciBrower to set
	 */
	public void setCiBrower(String ciBrower) {
		this.ciBrower = ciBrower;
	}

	/**
	 * @return the ciLang
	 */
	public String getCiLang() {
		return ciLang;
	}

	/**
	 * @param ciLang the ciLang to set
	 */
	public void setCiLang(String ciLang) {
		this.ciLang = ciLang;
	}

	/**
	 * @return the ciTimezone
	 */
	public String getCiTimezone() {
		return ciTimezone;
	}

	/**
	 * @param ciTimezone the ciTimezone to set
	 */
	public void setCiTimezone(String ciTimezone) {
		this.ciTimezone = ciTimezone;
	}

	/**
	 * @return the ciResolution
	 */
	public String getCiResolution() {
		return ciResolution;
	}

	/**
	 * @param ciResolution the ciResolution to set
	 */
	public void setCiResolution(String ciResolution) {
		this.ciResolution = ciResolution;
	}

	/**
	 * @return the ciCookieid
	 */
	public String getCiCookieid() {
		return ciCookieid;
	}

	/**
	 * @param ciCookieid the ciCookieid to set
	 */
	public void setCiCookieid(String ciCookieid) {
		this.ciCookieid = ciCookieid;
	}

	/**
	 * @return the ciOldCookie
	 */
	public String getCiOldCookie() {
		return ciOldCookie;
	}

	/**
	 * @param ciOldCookie the ciOldCookie to set
	 */
	public void setCiOldCookie(String ciOldCookie) {
		this.ciOldCookie = ciOldCookie;
	}

	/**
	 * @return the ciCookieFlag
	 */
	public Long getCiCookieFlag() {
		return ciCookieFlag;
	}

	/**
	 * @param ciCookieFlag the ciCookieFlag to set
	 */
	public void setCiCookieFlag(Long ciCookieFlag) {
		this.ciCookieFlag = ciCookieFlag;
	}

	/**
	 * @return the ciSha256
	 */
	public String getCiSha256() {
		return ciSha256;
	}

	/**
	 * @param ciSha256 the ciSha256 to set
	 */
	public void setCiSha256(String ciSha256) {
		this.ciSha256 = ciSha256;
	}

	/**
	 * @return the ciCardnopart
	 */
	public String getCiCardnopart() {
		return ciCardnopart;
	}

	/**
	 * @param ciCardnopart the ciCardnopart to set
	 */
	public void setCiCardnopart(String ciCardnopart) {
		this.ciCardnopart = ciCardnopart;
	}

	/**
	 * @return the ciCardtype
	 */
	public Long getCiCardtype() {
		return ciCardtype;
	}

	/**
	 * @param ciCardtype the ciCardtype to set
	 */
	public void setCiCardtype(Long ciCardtype) {
		this.ciCardtype = ciCardtype;
	}

	/**
	 * @return the ciRemark
	 */
	public String getCiRemark() {
		return ciRemark;
	}

	/**
	 * @param ciRemark the ciRemark to set
	 */
	public void setCiRemark(String ciRemark) {
		this.ciRemark = ciRemark;
	}

	/**
	 * @return the ciId
	 */
	public String getCiId() {
		return ciId;
	}

}