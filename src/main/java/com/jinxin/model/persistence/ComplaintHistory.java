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
import javax.persistence.Transient;

/**
 * 
 * <p>Title: </p>
 * <p>Description: 交易订单投诉历史表</p>
 * <p>Copyright: jinxin (c) 2013 版权</p>
 * <p>Company: </p>
 * @author 
 * @version V1.0 
 * @date 2013-8-6上午09:36:22
 */
@Entity
@Table(name = "CCPS_COMPLAINT_HISTORY")
public class ComplaintHistory implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	// 自增（1,1）主键
	@Id
	@Column(name = "CH_ID", unique = true, nullable = false, precision = 38, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CCPS_COMPLAINT_HISTORY_SEQ")
	@SequenceGenerator(name = "CCPS_COMPLAINT_HISTORY_SEQ", allocationSize = 1, initialValue = 1, sequenceName = "CCPS_COMPLAINT_HISTORY_SEQ")
	private Long chId;
	// 流水订单号
	@ManyToOne
	@JoinColumn(name = "CH_COM_TR_NO", nullable = false)
	private Complaint complaint;
	// 1告知交易结果,2索赔,3汇率差,4退款,5解释退款周期,6公司内部查询中,7一次通知,8二次通知,9三次通知
	// 跟进情况
	@Column(name = "CH_FOLLOW", precision = 38, scale = 0)
	private Integer chFollow;

	// 商户反馈 1:无回复,2:和持卡人协商,3:投诉解决,4:等待商户解决
	@Column(name = "CH_FEEDBACK", precision = 38, scale = 0)
	private Integer chFeedback;

	// 1:第一次投诉,2:第二次投诉,3:第三次投诉 投诉次数
	@Column(name = "CH_COM_TIMES", precision = 38, scale = 0)
	private Integer chComTimes;
	// 投诉类型 1:电话,2:邮件,3:官网 4其它
	@Column(name = "CH_COM_TYPE", precision = 38, scale = 0)
	private Integer chComType;
	// 投诉时间
	@Column(name = "CH_DATE", nullable = false)
	private Date chDate;
	// 投诉原因 
	
	//1.	查询交易状态,
	//2.	更改订单信息,
	//3.	多扣款,
	//4.	重扣款,
	//5.	取消交易,
	//6.	退款金额不对,
	//7.	未授权交易,
	//8.	货物问题,
	//9.	未收到货,
	//10.	订单未显示成功付款,
	//11.	退款未到账,12.	其他

	@Column(name = "CH_REASON", nullable = false, precision = 38, scale = 0)
	private Integer chReason;

	// 1.严重2.一般3.轻微 投诉等级
	@Column(name = "CH_COM_LEVEL", nullable = false, precision = 38, scale = 0)
	private Integer chComLevel;
	// 索赔时间
	@Column(name = "CH_CLAIMANT_DATE")
	private Date chClaimantDate;
	// 投诉关闭时间
	@Column(name = "CH_CLOSE_DATE")
	private Date chCloseDate;
	@Column(name = "CH_STATUS", nullable = false, precision = 38, scale = 0)
	// 1:待处理,2:已关闭 投诉状态
	private Integer chStatus;
	
	// 操作人
	@Column(name = "CH_LOGIN_NAME")
	private String chLoginName;
	// 操作时间
	@Column(name = "CH_OPER_DATE")
	private Date chOperDate;
	@Column(name = "CH_REMARK")
	private String chRemark;
	
	@Transient
	private String chFollowVal; //跟进值
	@Transient
	private String chFeedbackVal; //商户反馈值
	@Transient
	private String chComTimesVal; //投诉次数
	@Transient
	private String chComTypeVal;//投诉方式
	@Transient
	private String chReasonVal;//投诉原因
	@Transient
	private String chComLevelVal;//投诉等级
	@Transient
	private String chStatusVal;//投诉状态

	 
	public Long getChId() {
		return chId;
	}
 
	public void setChId(Long chId) {
		this.chId = chId;
	}
 
	public Complaint getComplaint() {
		return complaint;
	}
	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}

	public Integer getChFollow() {
		return chFollow;
	}

	public void setChFollow(Integer chFollow) {
		this.chFollow = chFollow;
	}

	public Integer getChFeedback() {
		return chFeedback;
	}

	public void setChFeedback(Integer chFeedback) {
		this.chFeedback = chFeedback;
	}

	public Integer getChComTimes() {
		return chComTimes;
	}

	public void setChComTimes(Integer chComTimes) {
		this.chComTimes = chComTimes;
	}

	public Integer getChComType() {
		return chComType;
	}

	public void setChComType(Integer chComType) {
		this.chComType = chComType;
	}

	 

	public Integer getChReason() {
		return chReason;
	}

	public void setChReason(Integer chReason) {
		this.chReason = chReason;
	}

	public Integer getChComLevel() {
		return chComLevel;
	}

	public void setChComLevel(Integer chComLevel) {
		this.chComLevel = chComLevel;
	}

	 

	public Date getChDate() {
		return chDate;
	}

	public void setChDate(Date chDate) {
		this.chDate = chDate;
	}

	public Date getChClaimantDate() {
		return chClaimantDate;
	}

	public void setChClaimantDate(Date chClaimantDate) {
		this.chClaimantDate = chClaimantDate;
	}

	public Date getChCloseDate() {
		return chCloseDate;
	}

	public void setChCloseDate(Date chCloseDate) {
		this.chCloseDate = chCloseDate;
	}

	public Integer getChStatus() {
		return chStatus;
	}

	public void setChStatus(Integer chStatus) {
		this.chStatus = chStatus;
	}

	public String getChLoginName() {
		return chLoginName;
	}

	public void setChLoginName(String chLoginName) {
		this.chLoginName = chLoginName;
	}

	 

	public Date getChOperDate() {
		return chOperDate;
	}

	public void setChOperDate(Date chOperDate) {
		this.chOperDate = chOperDate;
	}

	public String getChRemark() {
		return chRemark;
	}

	public void setChRemark(String chRemark) {
		this.chRemark = chRemark;
	}

	public String getChFollowVal() {
		return chFollowVal;
	}

	public void setChFollowVal(String chFollowVal) {
		this.chFollowVal = chFollowVal;
	}

	public String getChFeedbackVal() {
		return chFeedbackVal;
	}

	public void setChFeedbackVal(String chFeedbackVal) {
		this.chFeedbackVal = chFeedbackVal;
	}

	public String getChComTimesVal() {
		return chComTimesVal;
	}

	public void setChComTimesVal(String chComTimesVal) {
		this.chComTimesVal = chComTimesVal;
	}

	public String getChComTypeVal() {
		return chComTypeVal;
	}

	public void setChComTypeVal(String chComTypeVal) {
		this.chComTypeVal = chComTypeVal;
	}

	public String getChReasonVal() {
		return chReasonVal;
	}

	public void setChReasonVal(String chReasonVal) {
		this.chReasonVal = chReasonVal;
	}

	public String getChComLevelVal() {
		return chComLevelVal;
	}

	public void setChComLevelVal(String chComLevelVal) {
		this.chComLevelVal = chComLevelVal;
	}

	public String getChStatusVal() {
		return chStatusVal;
	}

	public void setChStatusVal(String chStatusVal) {
		this.chStatusVal = chStatusVal;
	}

	 

}