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
 * <p> Title: 记录商户充许访问的勾兑服务器IP</p>
 * <p>Description: CCPS_MER_CHECKIP映射bean </p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 
 */
@Entity
@Table(name = "CCPS_MER_CHECKIP")
public class MerCheckIp implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	@Id
	@Column(name = "MC_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CCPS_MER_CHECKIP_SEQ")      
    @SequenceGenerator(name="CCPS_MER_CHECKIP_SEQ",allocationSize=1,initialValue=1, sequenceName="CCPS_MER_CHECKIP_SEQ")
	private Long mcId;
	
	// 商户号
	@ManyToOne
	@JoinColumn(name = "MC_MER_NO", nullable = false)
	private Merchant merchant;
	
	// IP地址
	@Column(name = "MC_IP", nullable = false)
	private String mcIp;
	
	// 添加人
	@Column(name = "MC_LOGIN_NAME", nullable = false)
	private String mcLoginName;
	
	// 添加时间
	@Column(name = "MC_OPRTIME", nullable = false)
	private Date mcOprtime;
	
	@Column(name = "MC_REMARK")
	private String mcRemark;

	public Long getMcId() {
		return mcId;
	}

	public void setMcId(Long mcId) {
		this.mcId = mcId;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public String getMcIp() {
		return mcIp;
	}

	public void setMcIp(String mcIp) {
		this.mcIp = mcIp;
	}

	public String getMcLoginName() {
		return mcLoginName;
	}

	public void setMcLoginName(String mcLoginName) {
		this.mcLoginName = mcLoginName;
	}

	public Date getMcOprtime() {
		return mcOprtime;
	}

	public void setMcOprtime(Date mcOprtime) {
		this.mcOprtime = mcOprtime;
	}

	public String getMcRemark() {
		return mcRemark;
	}

	public void setMcRemark(String mcRemark) {
		this.mcRemark = mcRemark;
	}

}