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
 * 
 * <p>Title: </p>
 * <p>Description: 交易订单投诉记录表</p>
 * <p>Copyright: jinxin (c) 2013 版权</p>
 * <p>Company: </p>
 * @author 
 * @version V1.0 
 * @date 2013-8-6上午09:36:38
 */
@Entity
@Table(name = "CCPS_COMPLAINT")
public class Complaint implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	// 流水订单号 主键
	@Id
	@Column(name = "COM_TR_NO", unique = true, nullable = false)
	private String comTrNo;
	
	// 自增（1,1）
	@Column(name = "COM_ID", nullable = false, precision = 38, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CCPS_COMPLAINT_SEQ")
	@SequenceGenerator(name = "CCPS_COMPLAINT_SEQ", allocationSize = 1, initialValue = 1, sequenceName = "CCPS_COMPLAINT_SEQ")
	private Long comId;

	// 商户订单号;
	@Column(name = "COM_MER_ORDERNO", nullable = false)
	private String comMerOrderno;
	// 商户号;
	@Column(name = "COM_MER_NO", nullable = false, precision = 38, scale = 0)
	private Long comMerNo;
	// 网关接入号;
	@Column(name = "COM_GW_NO", nullable = false, precision = 38, scale = 0)
	private Long comGwNo;
	// 交易币种;
	@Column(name = "COM_CURRENCY", nullable = false)
	private String comCurrency;
	// 交易金额;
	@Column(name = "COM_AMOUNT", nullable = false, precision = 18)
	private BigDecimal comAmount;
	// 指插入本交易表的时间（即提交银行通道时间）
	@Column(name = "COM_DATETIME", nullable = false)
	private Date comDatetime;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "complaint")
	private Set<ComplaintHistory> complaintHistories = new HashSet<ComplaintHistory>(
			0);

	public String getComTrNo() {
		return comTrNo;
	}

	public void setComTrNo(String comTrNo) {
		this.comTrNo = comTrNo;
	}

	public Long getComId() {
		return comId;
	}

	public void setComId(Long comId) {
		this.comId = comId;
	}

	public String getComMerOrderno() {
		return comMerOrderno;
	}

	public void setComMerOrderno(String comMerOrderno) {
		this.comMerOrderno = comMerOrderno;
	}

	public Long getComMerNo() {
		return comMerNo;
	}

	public void setComMerNo(Long comMerNo) {
		this.comMerNo = comMerNo;
	}

	public Long getComGwNo() {
		return comGwNo;
	}

	public void setComGwNo(Long comGwNo) {
		this.comGwNo = comGwNo;
	}

	public String getComCurrency() {
		return comCurrency;
	}

	public void setComCurrency(String comCurrency) {
		this.comCurrency = comCurrency;
	}

	public BigDecimal getComAmount() {
		return comAmount;
	}

	public void setComAmount(BigDecimal comAmount) {
		this.comAmount = comAmount;
	}

	public Date getComDatetime() {
		return comDatetime;
	}

	public void setComDatetime(Date comDatetime) {
		this.comDatetime = comDatetime;
	}

	public Set<ComplaintHistory> getComplaintHistories() {
		return complaintHistories;
	}

	public void setComplaintHistories(Set<ComplaintHistory> complaintHistories) {
		this.complaintHistories = complaintHistories;
	}

	 
	

}