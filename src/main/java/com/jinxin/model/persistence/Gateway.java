package com.jinxin.model.persistence;

// default package

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.jinxin.common.utils.ParamChecking;

/**
 * 
 * <p>Title: </p>
 * <p>Description:商户网关接入号信息表bean</p>
 * <p>Copyright: jinxin (c) 2013 版权</p>
 * <p>Company: </p>
 * @author 
 * @version V1.0 
 * @date 2013-6-28上午09:26:40
 */
@Entity
@Table(name = "CCPS_GATEWAY")
public class Gateway implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	// 网关接入号
	@Id
	@Column(name = "GW_NO", unique = true, nullable = false, precision = 38, scale = 0)
	private Long gwNo;

	// 商户号
	@ManyToOne
	@JoinColumn(name = "GW_MER_NO", nullable = false)
	private Merchant merchant;

	// 记录ID
	@Column(name = "GW_ID")
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CCPS_GATEWAY_SEQ")
	//@SequenceGenerator(name = "CCPS_GATEWAY_SEQ", allocationSize = 1, initialValue = 1, sequenceName = "CCPS_GATEWAY_SEQ")
	// @Formula("(select CCPS_GATEWAY_SEQ.nextval from dual)")
     private Long gwId;

	

	// MD5Key5
	@Column(name = "GW_MD5KEY", nullable = false)
	private String gwMd5key;

	// 状态
	@Column(name = "GW_STATUS", nullable = false, precision = 38, scale = 0)
	private Long gwStatus;

	// 绑定接口类型
	@Column(name = "GW_INF_TYPE", precision = 38, scale = 0)
	private Long gwInfType;

	// 是否发送邮件给商户
	@Column(name = "GW_MAILTOMER", nullable = false, precision = 38, scale = 0)
	private Long gwMailtomer;

	// 是否发送邮件给持卡人
	@Column(name = "GW_MAILTOHOLDER", nullable = false, precision = 38, scale = 0)
	private Long gwMailtoholder;

	// 是否VT商户
	@Column(name = "GW_ISVT", nullable = false, precision = 38, scale = 0)
	private Long gwIsvt;
	// 发生错误后的返回类型 1: 直接返回给商户 2:返回给持卡人的同时返回给商户 3:返回给持卡人后再返回给商户
	@Column(name = "GW_RETURN_MODEL", nullable = false, precision = 38, scale = 0)
	private Long gwReturnModel;
	
	



	// 操作人账号
	@Column(name = "GW_LOGIN_NAME")
	private String gwLoginName;

	// 创建时间
	@Column(name = "GW_CREATETIME")
	private Date gwCreatetime;

	// 备注
	@Column(name = "GW_REMARK")
	private String gwRemark;

	/**
	 * @return the gwNo
	 */
	public Long getGwNo() {
		return gwNo;
	}

	/**
	 * @param gwNo the gwNo to set
	 */
	public void setGwNo(Long gwNo) {
		this.gwNo = gwNo;
	}

	/**
	 * @return the merchant
	 */
	public Merchant getMerchant() {
		return merchant;
	}

	/**
	 * @param merchant the merchant to set
	 */
	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	/**
	 * @return the gwId
	 */
	public Long getGwId() {
		return gwId;
	}

	/**
	 * @param gwId the gwId to set
	 */
	public void setGwId(Long gwId) {
		this.gwId = gwId;
	}

	/**
	 * @return the gwMd5key
	 */
	public String getGwMd5key() {
		return gwMd5key;
	}

	/**
	 * @param gwMd5key the gwMd5key to set
	 */
	public void setGwMd5key(String gwMd5key) {
		this.gwMd5key = ParamChecking.str_null_to_empty(gwMd5key);
	}

	/**
	 * @return the gwStatus
	 */
	public Long getGwStatus() {
		return gwStatus;
	}

	/**
	 * @param gwStatus the gwStatus to set
	 */
	public void setGwStatus(Long gwStatus) {
		this.gwStatus = gwStatus;
	}

	/**
	 * @return the gwInfType
	 */
	public Long getGwInfType() {
		return gwInfType;
	}

	/**
	 * @param gwInfType the gwInfType to set
	 */
	public void setGwInfType(Long gwInfType) {
		this.gwInfType = gwInfType;
	}

	/**
	 * @return the gwMailtomer
	 */
	public Long getGwMailtomer() {
		return gwMailtomer;
	}

	/**
	 * @param gwMailtomer the gwMailtomer to set
	 */
	public void setGwMailtomer(Long gwMailtomer) {
		this.gwMailtomer = gwMailtomer;
	}

	/**
	 * @return the gwMailtoholder
	 */
	public Long getGwMailtoholder() {
		return gwMailtoholder;
	}

	/**
	 * @param gwMailtoholder the gwMailtoholder to set
	 */
	public void setGwMailtoholder(Long gwMailtoholder) {
		this.gwMailtoholder = gwMailtoholder;
	}

	/**
	 * @return the gwIsvt
	 */
	public Long getGwIsvt() {
		return gwIsvt;
	}

	/**
	 * @param gwIsvt the gwIsvt to set
	 */
	public void setGwIsvt(Long gwIsvt) {
		this.gwIsvt = gwIsvt;
	}

	/**
	 * @return the gwReturnModel
	 */
	public Long getGwReturnModel() {
		return gwReturnModel;
	}

	/**
	 * @param gwReturnModel the gwReturnModel to set
	 */
	public void setGwReturnModel(Long gwReturnModel) {
		this.gwReturnModel = gwReturnModel;
	}

	/**
	 * @return the gwLoginName
	 */
	public String getGwLoginName() {
		return gwLoginName;
	}

	/**
	 * @param gwLoginName the gwLoginName to set
	 */
	public void setGwLoginName(String gwLoginName) {
		this.gwLoginName = gwLoginName;
	}

	/**
	 * @return the gwCreatetime
	 */
	public Date getGwCreatetime() {
		return gwCreatetime;
	}

	/**
	 * @param gwCreatetime the gwCreatetime to set
	 */
	public void setGwCreatetime(Date gwCreatetime) {
		this.gwCreatetime = gwCreatetime;
	}

	/**
	 * @return the gwRemark
	 */
	public String getGwRemark() {
		return gwRemark;
	}

	/**
	 * @param gwRemark the gwRemark to set
	 */
	public void setGwRemark(String gwRemark) {
		this.gwRemark = ParamChecking.str_null_to_empty(gwRemark);
	}

	// Constructors

	

}