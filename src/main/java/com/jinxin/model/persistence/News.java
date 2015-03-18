package com.jinxin.model.persistence;
// default package

import java.sql.Clob;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



/**
 * <p>Title:</p>
 * <p>Description: CCPS_NEWS映射bean</p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 
 */
@Entity
@Table(name="CCPS_NEWS")

public class News  implements java.io.Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//自增
	@Id 
    @Column(name="NEWS_ID", unique=true, nullable=false, precision=38, scale=0)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CCPS_NEWS_SEQ")      
    @SequenceGenerator(name="CCPS_NEWS_SEQ",allocationSize=1,initialValue=1, sequenceName="CCPS_NEWS_SEQ")
     private Long newsId;
	
	//新闻公告标题
	@Column(name="NEWS_TITLE_CN", nullable=false)
     private String newsTitleCn;
	
	 //新闻公告内容
	 @Lob
	 @Basic(fetch = FetchType.LAZY) 
	 @Column(name = "NEWS_CONTENT_CN",columnDefinition="Clob",length=10000,nullable = false)
     private Clob newsContentCn;
	 
	 //新闻公告标题(英文)
	 @Column(name="NEWS_TITLE_EN", nullable=false)
     private String newsTitleEn;
	 
	 //新闻公告内容(英文)
	 @Lob
	 @Basic(fetch = FetchType.LAZY) 
	 @Column(name = "NEWS_CONTENT_EN",columnDefinition="Clob",length=10000,nullable = false)
     private Clob newsContentEn;
	 
	 //1 : 系统后台公告 ; 2 :  商户后台公告 ;
	 @Column(name="NEWS_TYPE", nullable=false, precision=38, scale=0)
     private int newsType;
	 
	 //公告状态:0  待发布 1 已发布 -1 删除;
	 @Column(name="NEWS_STATUS", nullable=false, precision=38, scale=0)
     private int newsStatus;
	 
	 //失效日期
	 @Column(name="NEWS_EXPIRYDATE")
     private Date newsExpirydate;
	 
	 //生效日期
	 @Column(name="NEWS_EFFECTIVEDATE", nullable=false)
     private Date newsEffectivedate;
	 
	 //发布人账号
	 @Column(name="NEWS_SEND_NAME")
     private String newsSendName;
	 
	 //发布时间
	 @Column(name="NEWS_SEND_TIME")
     private Date newsSendTime;
	 
	 //添加人
	 @Column(name="NEWS_LOGIN_NAME", nullable=false)
     private String newsLoginName;
	 
	 //添加时间
	 @Column(name="NEWS_OPRTIME", nullable=false)
     private Date newsOprtime;
	 
	 //备注
	 @Column(name="NEWS_REMARK")
     private String newsRemark;

	public Long getNewsId() {
		return newsId;
	}

	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}

	public String getNewsTitleCn() {
		return newsTitleCn;
	}

	public void setNewsTitleCn(String newsTitleCn) {
		this.newsTitleCn = newsTitleCn;
	}

	public Clob getNewsContentCn() {
		return newsContentCn;
	}

	public void setNewsContentCn(Clob newsContentCn) {
		this.newsContentCn = newsContentCn;
	}

	public String getNewsTitleEn() {
		return newsTitleEn;
	}

	public void setNewsTitleEn(String newsTitleEn) {
		this.newsTitleEn = newsTitleEn;
	}

	public Clob getNewsContentEn() {
		return newsContentEn;
	}

	public void setNewsContentEn(Clob newsContentEn) {
		this.newsContentEn = newsContentEn;
	}

	public int getNewsType() {
		return newsType;
	}

	public void setNewsType(int newsType) {
		this.newsType = newsType;
	}

	public Date getNewsExpirydate() {
		return newsExpirydate;
	}

	public void setNewsExpirydate(Date newsExpirydate) {
		this.newsExpirydate = newsExpirydate;
	}

	public String getNewsLoginName() {
		return newsLoginName;
	}

	public void setNewsLoginName(String newsLoginName) {
		this.newsLoginName = newsLoginName;
	}

	public Date getNewsOprtime() {
		return newsOprtime;
	}

	public void setNewsOprtime(Date newsOprtime) {
		this.newsOprtime = newsOprtime;
	}

	public String getNewsRemark() {
		return newsRemark;
	}

	public void setNewsRemark(String newsRemark) {
		this.newsRemark = newsRemark;
	}

	public int getNewsStatus() {
		return newsStatus;
	}

	public void setNewsStatus(int newsStatus) {
		this.newsStatus = newsStatus;
	}

	public Date getNewsEffectivedate() {
		return newsEffectivedate;
	}

	public void setNewsEffectivedate(Date newsEffectivedate) {
		this.newsEffectivedate = newsEffectivedate;
	}

	public String getNewsSendName() {
		return newsSendName;
	}

	public void setNewsSendName(String newsSendName) {
		this.newsSendName = newsSendName;
	}

	public Date getNewsSendTime() {
		return newsSendTime;
	}

	public void setNewsSendTime(Date newsSendTime) {
		this.newsSendTime = newsSendTime;
	}
	 
}