package com.jinxin.model.persistence;

// default package

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * <p> Title: 记录系统监控人员的操作记录,如:测试数据库连接,测试MIGS、MOTO,ABC等通道的连接</p>
 * <p>Description: CCPS_MONITOR映射bean </p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 
 */
@Entity
@Table(name = "CCPS_MONITOR")
public class Monitor implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Fields
	@Id
	@Column(name = "MONITOR_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CCPS_MONITOR_SEQ")      
    @SequenceGenerator(name="CCPS_MONITOR_SEQ",allocationSize=1,initialValue=1, sequenceName="CCPS_MONITOR_SEQ")
	private Long monitorId;
	
	@Column(name = "MONITOR_CONTENT", nullable = false)
	private String monitorContent;
	
	@Column(name = "MONITOR_TIME", nullable = false)
	private Date monitorTime;

	public Long getMonitorId() {
		return monitorId;
	}

	public void setMonitorId(Long monitorId) {
		this.monitorId = monitorId;
	}

	public String getMonitorContent() {
		return monitorContent;
	}

	public void setMonitorContent(String monitorContent) {
		this.monitorContent = monitorContent;
	}

	public Date getMonitorTime() {
		return monitorTime;
	}

	public void setMonitorTime(Date monitorTime) {
		this.monitorTime = monitorTime;
	}


}