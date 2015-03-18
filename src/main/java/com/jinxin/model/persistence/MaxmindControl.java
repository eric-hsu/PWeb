package com.jinxin.model.persistence;

// default package

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * <p> Title: 商户各网关接入号与MAXMIND返回元素之间的设定(针对网关接入号)</p>
 * <p>Description: CCPS_MAXMIND_CONTROL映射bean </p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 
 */
@Entity
@Table(name = "CCPS_MAXMIND_CONTROL", uniqueConstraints = @UniqueConstraint(columnNames = "MC_GW_NO"))
public class MaxmindControl implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields
	
	@Id
	@Column(name = "MC_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CCPS_MAXMIND_CONTROL_SEQ")      
    @SequenceGenerator(name="CCPS_MAXMIND_CONTROL_SEQ",allocationSize=1,initialValue=1, sequenceName="CCPS_MAXMIND_CONTROL_SEQ")
	private Long mcId;
	
	// 商户号
	@Column(name = "MC_MER_NO", nullable = false, precision = 38, scale = 0)
	private Long mcMerNo;
	
	// 网关接入号,若非0表示对具体网关接入号限制,否则作用于整个商户号
	@Column(name = "MC_GW_NO", unique = true, nullable = false, precision = 38, scale = 0)
	private Long mcGwNo;
	
	// 1 : 是; 0 : 否
	@Column(name = "MC_CHECK_SCORES", nullable = false, precision = 38, scale = 0)
	private int mcCheckScores;
	
	// 1 : 是; 0 : 否
	@Column(name = "MC_CHECK_IP", nullable = false, precision = 38, scale = 0)
	private int mcCheckIp;
	
	// 1 : 是; 0 : 否
	@Column(name = "MC_CHECK_EMAIL", nullable = false, precision = 38, scale = 0)
	private int mcCheckEmail;
	
	// 1 : 是; 0 : 否
	@Column(name = "MC_CHECK_USERNAME", nullable = false, precision = 38, scale = 0)
	private int mcCheckUsername;
	
	// 1 : 是; 0 : 否
	@Column(name = "MC_CHECK_PROXY", nullable = false, precision = 38, scale = 0)
	private int mcCheckProxy;
	
	// 1 : 是; 0 : 否
	@Column(name = "MC_CHECK_BINCOUNTRY", nullable = false, precision = 38, scale = 0)
	private int mcCheckBincountry;
	
	// 1 : 是; 0 : 否
	@Column(name = "MC_CHECK_OUTER", nullable = false, precision = 38, scale = 0)
	private int mcCheckOuter;
	
	// 风险通过分数
	@Column(name = "MC_PASS_SCORES", nullable = false, precision = 18)
	private float mcPassScores;
	
	// 匿名代理风险分数
	@Column(name = "MC_PROXY_SCORES", nullable = false, precision = 18)
	private float mcProxyScores;
	
	// 发卡行国家是否匹配风险分数
	@Column(name = "MC_BINCOUNTY_SCORES", nullable = false, precision = 18)
	private float mcBincountyScores;
	
	// 跨国交易风险距离
	@Column(name = "MC_OUTER_SCORES", nullable = false, precision = 18)
	private float mcOuterScores;
	
	// 添加人
	@Column(name = "MC_LOGIN_NAME", nullable = false)
	private String mcLoginName;
	
	// 操作人姓名
	@Column(name = "MC_ADMIN_NAME", nullable = false)
	private String mcAdminName;
	
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

	public Long getMcMerNo() {
		return mcMerNo;
	}

	public void setMcMerNo(Long mcMerNo) {
		this.mcMerNo = mcMerNo;
	}

	public Long getMcGwNo() {
		return mcGwNo;
	}

	public void setMcGwNo(Long mcGwNo) {
		this.mcGwNo = mcGwNo;
	}

	public int getMcCheckScores() {
		return mcCheckScores;
	}

	public void setMcCheckScores(int mcCheckScores) {
		this.mcCheckScores = mcCheckScores;
	}

	public int getMcCheckIp() {
		return mcCheckIp;
	}

	public void setMcCheckIp(int mcCheckIp) {
		this.mcCheckIp = mcCheckIp;
	}

	public int getMcCheckEmail() {
		return mcCheckEmail;
	}

	public void setMcCheckEmail(int mcCheckEmail) {
		this.mcCheckEmail = mcCheckEmail;
	}

	public int getMcCheckUsername() {
		return mcCheckUsername;
	}

	public void setMcCheckUsername(int mcCheckUsername) {
		this.mcCheckUsername = mcCheckUsername;
	}

	public int getMcCheckProxy() {
		return mcCheckProxy;
	}

	public void setMcCheckProxy(int mcCheckProxy) {
		this.mcCheckProxy = mcCheckProxy;
	}

	public int getMcCheckBincountry() {
		return mcCheckBincountry;
	}

	public void setMcCheckBincountry(int mcCheckBincountry) {
		this.mcCheckBincountry = mcCheckBincountry;
	}

	public int getMcCheckOuter() {
		return mcCheckOuter;
	}

	public void setMcCheckOuter(int mcCheckOuter) {
		this.mcCheckOuter = mcCheckOuter;
	}

	public float getMcPassScores() {
		return mcPassScores;
	}

	public void setMcPassScores(float mcPassScores) {
		this.mcPassScores = mcPassScores;
	}

	public float getMcProxyScores() {
		return mcProxyScores;
	}

	public void setMcProxyScores(float mcProxyScores) {
		this.mcProxyScores = mcProxyScores;
	}

	public float getMcBincountyScores() {
		return mcBincountyScores;
	}

	public void setMcBincountyScores(float mcBincountyScores) {
		this.mcBincountyScores = mcBincountyScores;
	}

	public float getMcOuterScores() {
		return mcOuterScores;
	}

	public void setMcOuterScores(float mcOuterScores) {
		this.mcOuterScores = mcOuterScores;
	}

	public String getMcLoginName() {
		return mcLoginName;
	}

	public void setMcLoginName(String mcLoginName) {
		this.mcLoginName = mcLoginName;
	}

	public String getMcAdminName() {
		return mcAdminName;
	}

	public void setMcAdminName(String mcAdminName) {
		this.mcAdminName = mcAdminName;
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