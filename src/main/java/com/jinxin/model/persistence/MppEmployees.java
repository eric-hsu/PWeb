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
 * <p>Description: CCPS_MPP_ EMPLOYEES映射bean </p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 
 */
@Entity
@Table(name = "CCPS_MPP_EMPLOYEES")
public class MppEmployees implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Fields
	@Id
	@Column(name = "MPE_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CCPS_MPP_EMPLOYEES_SEQ")      
    @SequenceGenerator(name="CCPS_MPP_EMPLOYEES_SEQ",allocationSize=1,initialValue=1, sequenceName="CCPS_MPP_EMPLOYEES_SEQ")
	private Long mpeId;
	
	// 与商户表关联
	@Column(name = "MPE_MER_NO", nullable = false, precision = 38, scale = 0)
	private Long mpeMerNo;
	
	// 员工数量
	@Column(name = "MPE_NUMBER")
	private String mpeNumber;
	
	// 客服人员数量
	@Column(name = "MPE_CS_NUMBER")
	private String mpeCsNumber;
	
	// 财务职员数量
	@Column(name = "MPE_FINANCE_NUMBER")
	private String mpeFinanceNumber;
	
	// 销售人员数量
	@Column(name = "MPE_SALES_NUMBER")
	private String mpeSalesNumber;
	
	// 技术人员数量
	@Column(name = "MPE_TECHNICAL_NUMBER")
	private String mpeTechnicalNumber;
	
	// 其他人员数量
	@Column(name = "MPE_OTHERS_NUMBER")
	private String mpeOthersNumber;
	
	// 客服姓名
	@Column(name = "MPE_CS_NAME")
	private String mpeCsName;
	
	// 客服办公室电话
	@Column(name = "MPE_CS_TEL")
	private String mpeCsTel;
	
	// 客服电子邮箱
	@Column(name = "MPE_CS_EMAIL")
	private String mpeCsEmail;
	
	// 客服传真
	@Column(name = "MPE_CS_FAX")
	private String mpeCsFax;
	
	// 1:通过电子邮件 ; 2:办公时间服务热线 ; 3: 24小时服务热线
	@Column(name = "MPE_CS_SUPPORT", precision = 38, scale = 0)
	private Long mpeCsSupport;
	
	// 财政姓名
	@Column(name = "MPE_F_NAME")
	private String mpeFName;
	
	// 财政办公室电话
	@Column(name = "MPE_F_TEL")
	private String mpeFTel;
	
	// 财政电子邮箱
	@Column(name = "MPE_F_EMAIL")
	private String mpeFEmail;
	
	// 财政传真
	@Column(name = "MPE_F_FAX")
	private String mpeFFax;
	
	// 销售姓名
	@Column(name = "MPE_S_NAME")
	private String mpeSName;
	
	// 销售办公室电话
	@Column(name = "MPE_S_TEL")
	private String mpeSTel;
	
	// 销售电子邮箱
	@Column(name = "MPE_S_EMAIL")
	private String mpeSEmail;
	
	// 销售传真
	@Column(name = "MPE_S_FAX")
	private String mpeSFax;
	
	// 技术姓名
	@Column(name = "MPE_T_NAME")
	private String mpeTName;
	
	// 技术办公室电话
	@Column(name = "MPE_T_TEL")
	private String mpeTTel;
	
	// 技术电子邮箱
	@Column(name = "MPE_T_EMAIL")
	private String mpeTEmail;
	
	// 技术传真
	@Column(name = "MPE_T_FAX")
	private String mpeTFax;

	public Long getMpeId() {
		return mpeId;
	}

	public void setMpeId(Long mpeId) {
		this.mpeId = mpeId;
	}

	public Long getMpeMerNo() {
		return mpeMerNo;
	}

	public void setMpeMerNo(Long mpeMerNo) {
		this.mpeMerNo = mpeMerNo;
	}

	public String getMpeNumber() {
		return mpeNumber;
	}

	public void setMpeNumber(String mpeNumber) {
		this.mpeNumber = mpeNumber;
	}

	public String getMpeCsNumber() {
		return mpeCsNumber;
	}

	public void setMpeCsNumber(String mpeCsNumber) {
		this.mpeCsNumber = mpeCsNumber;
	}

	public String getMpeFinanceNumber() {
		return mpeFinanceNumber;
	}

	public void setMpeFinanceNumber(String mpeFinanceNumber) {
		this.mpeFinanceNumber = mpeFinanceNumber;
	}

	public String getMpeSalesNumber() {
		return mpeSalesNumber;
	}

	public void setMpeSalesNumber(String mpeSalesNumber) {
		this.mpeSalesNumber = mpeSalesNumber;
	}

	public String getMpeTechnicalNumber() {
		return mpeTechnicalNumber;
	}

	public void setMpeTechnicalNumber(String mpeTechnicalNumber) {
		this.mpeTechnicalNumber = mpeTechnicalNumber;
	}

	public String getMpeOthersNumber() {
		return mpeOthersNumber;
	}

	public void setMpeOthersNumber(String mpeOthersNumber) {
		this.mpeOthersNumber = mpeOthersNumber;
	}

	public String getMpeCsName() {
		return mpeCsName;
	}

	public void setMpeCsName(String mpeCsName) {
		this.mpeCsName = mpeCsName;
	}

	public String getMpeCsTel() {
		return mpeCsTel;
	}

	public void setMpeCsTel(String mpeCsTel) {
		this.mpeCsTel = mpeCsTel;
	}

	public String getMpeCsEmail() {
		return mpeCsEmail;
	}

	public void setMpeCsEmail(String mpeCsEmail) {
		this.mpeCsEmail = mpeCsEmail;
	}

	public String getMpeCsFax() {
		return mpeCsFax;
	}

	public void setMpeCsFax(String mpeCsFax) {
		this.mpeCsFax = mpeCsFax;
	}

	public Long getMpeCsSupport() {
		return StringHandleUtils.null2Zero(false, mpeCsSupport);
	}

	public void setMpeCsSupport(Long mpeCsSupport) {
		this.mpeCsSupport = mpeCsSupport;
	}

	public String getMpeFName() {
		return mpeFName;
	}

	public void setMpeFName(String mpeFName) {
		this.mpeFName = mpeFName;
	}

	public String getMpeFTel() {
		return mpeFTel;
	}

	public void setMpeFTel(String mpeFTel) {
		this.mpeFTel = mpeFTel;
	}

	public String getMpeFEmail() {
		return mpeFEmail;
	}

	public void setMpeFEmail(String mpeFEmail) {
		this.mpeFEmail = mpeFEmail;
	}

	public String getMpeFFax() {
		return mpeFFax;
	}

	public void setMpeFFax(String mpeFFax) {
		this.mpeFFax = mpeFFax;
	}

	public String getMpeSName() {
		return mpeSName;
	}

	public void setMpeSName(String mpeSName) {
		this.mpeSName = mpeSName;
	}

	public String getMpeSTel() {
		return mpeSTel;
	}

	public void setMpeSTel(String mpeSTel) {
		this.mpeSTel = mpeSTel;
	}

	public String getMpeSEmail() {
		return mpeSEmail;
	}

	public void setMpeSEmail(String mpeSEmail) {
		this.mpeSEmail = mpeSEmail;
	}

	public String getMpeSFax() {
		return mpeSFax;
	}

	public void setMpeSFax(String mpeSFax) {
		this.mpeSFax = mpeSFax;
	}

	public String getMpeTName() {
		return mpeTName;
	}

	public void setMpeTName(String mpeTName) {
		this.mpeTName = mpeTName;
	}

	public String getMpeTTel() {
		return mpeTTel;
	}

	public void setMpeTTel(String mpeTTel) {
		this.mpeTTel = mpeTTel;
	}

	public String getMpeTEmail() {
		return mpeTEmail;
	}

	public void setMpeTEmail(String mpeTEmail) {
		this.mpeTEmail = mpeTEmail;
	}

	public String getMpeTFax() {
		return mpeTFax;
	}

	public void setMpeTFax(String mpeTFax) {
		this.mpeTFax = mpeTFax;
	}

}