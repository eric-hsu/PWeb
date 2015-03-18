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

/**
 * <p> Title: 记录商户各网关接入号 充许支付网址的限定(针对网关接入号)</p>
 * <p>Description: CCPS_MER_WEBSIT_LIMIT映射bean </p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 
 */
@Entity
@Table(name = "CCPS_MER_WEBSIT_LIMIT")
public class MerWebsitLimit implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Fields

	@Id
	@Column(name = "WL_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CCPS_MER_WEBSIT_LIMIT_SEQ")      
    @SequenceGenerator(name="CCPS_MER_WEBSIT_LIMIT_SEQ",allocationSize=1,initialValue=1, sequenceName="CCPS_MER_WEBSIT_LIMIT_SEQ")
	private Long wlId;
	
	// 商户号
	@Column(name = "WL_MER_NO", nullable = false, precision = 38, scale = 0)
	private Long wlMerNo;
	
	// 网关接入号,若非0表示对具体网关接入号限制,否则作用于整个商户号
	@Column(name = "WL_GW_NO", nullable = false, precision = 38, scale = 0)
	private Long wlGwNo;
	
	// 支付网址
	@Column(name = "WL_WEBSIT", nullable = false)
	private String wlWebsit;
	
	// -4:删除,-3:停用,-2:复核失败,-1:审核失败,0:待处理,1:已审核,2:已复核（正常）
	@Column(name = "WL_STATUS", nullable = false, precision = 38, scale = 0)
	private Long wlStatus;
	
	// 产品
	@Column(name = "WL_GOODS")
	private String wlGoods;
	
	// 币种
	@Column(name = "WL_CURRENCY")
	private String wlCurrency;
	
	// 平均金额
	@Column(name = "WL_AVGAMOUNT", precision = 18)
	private BigDecimal wlAvgamount;
	
	// 审核人账号
	@Column(name = "WL_CHECK_NAME")
	private String wlCheckName;
	
	// 审核时间
	@Column(name = "WL_CHECK_OPRTIME")
	private Date wlCheckOprtime;
	
	// 复核人账号
	@Column(name = "WL_RECHECK_NAME")
	private String wlRecheckName;
	
	// 复核时间
	@Column(name = "WL_RECHECK_OPRTIME")
	private Date wlRecheckOprtime;
	
	// 1.商户提交 2:后台提交
	@Column(name = "WL_SUBMIT")
	private String wlSubmit;
	
	// 添加人或、删除人、或停用人
	@Column(name = "WL_LOGIN_NAME", nullable = false)
	private String wlLoginName;
	
	// 操作时间
	@Column(name = "WL_OPRTIME", nullable = false)
	private Date wlOprtime;
	
	@Column(name = "WL_REMARK")
	private String wlRemark;

	public MerWebsitLimit() {
	}

	public MerWebsitLimit(Long wlId) {
		this.wlId = wlId;
	}

	public Long getWlId() {
		return wlId;
	}

	public void setWlId(Long wlId) {
		this.wlId = wlId;
	}

	public Long getWlMerNo() {
		return wlMerNo;
	}

	public void setWlMerNo(Long wlMerNo) {
		this.wlMerNo = wlMerNo;
	}

	public Long getWlGwNo() {
		return wlGwNo;
	}

	public void setWlGwNo(Long wlGwNo) {
		this.wlGwNo = wlGwNo;
	}

	public String getWlWebsit() {
		return wlWebsit;
	}

	public void setWlWebsit(String wlWebsit) {
		this.wlWebsit = wlWebsit;
	}

	public Long getWlStatus() {
		return wlStatus;
	}

	public void setWlStatus(Long wlStatus) {
		this.wlStatus = wlStatus;
	}

	public String getWlGoods() {
		return wlGoods;
	}

	public void setWlGoods(String wlGoods) {
		this.wlGoods = wlGoods;
	}

	public String getWlCurrency() {
		return wlCurrency;
	}

	public void setWlCurrency(String wlCurrency) {
		this.wlCurrency = wlCurrency;
	}

	public BigDecimal getWlAvgamount() {
		return wlAvgamount;
	}

	public void setWlAvgamount(BigDecimal wlAvgamount) {
		this.wlAvgamount = wlAvgamount;
	}

	public String getWlCheckName() {
		return wlCheckName;
	}

	public void setWlCheckName(String wlCheckName) {
		this.wlCheckName = wlCheckName;
	}

	public Date getWlCheckOprtime() {
		return wlCheckOprtime;
	}

	public void setWlCheckOprtime(Date wlCheckOprtime) {
		this.wlCheckOprtime = wlCheckOprtime;
	}

	public String getWlRecheckName() {
		return wlRecheckName;
	}

	public void setWlRecheckName(String wlRecheckName) {
		this.wlRecheckName = wlRecheckName;
	}

	public Date getWlRecheckOprtime() {
		return wlRecheckOprtime;
	}

	public void setWlRecheckOprtime(Date wlRecheckOprtime) {
		this.wlRecheckOprtime = wlRecheckOprtime;
	}

	public String getWlSubmit() {
		return wlSubmit;
	}

	public void setWlSubmit(String wlSubmit) {
		this.wlSubmit = wlSubmit;
	}

	public String getWlLoginName() {
		return wlLoginName;
	}

	public void setWlLoginName(String wlLoginName) {
		this.wlLoginName = wlLoginName;
	}

	public Date getWlOprtime() {
		return wlOprtime;
	}

	public void setWlOprtime(Date wlOprtime) {
		this.wlOprtime = wlOprtime;
	}

	public String getWlRemark() {
		return wlRemark;
	}

	public void setWlRemark(String wlRemark) {
		this.wlRemark = wlRemark;
	}

}