package com.jinxin.model.persistence;

import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;



/**
 * <p>Title:</p>
 * <p>Description: CCPS_SENDMAILS映射bean</p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 
 */
@Entity
@Table(name="CCPS_SENDMAILS")

public class Sendmails  implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//自增
	 @Id 
	 @Column(name="S_ID", unique=true, nullable=false, precision=38, scale=0)
	 @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CCPS_SENDMAILS_SEQ")      
    @SequenceGenerator(name="CCPS_SENDMAILS_SEQ",allocationSize=1,initialValue=1, sequenceName="CCPS_SENDMAILS_SEQ")
     private Long SId;
	 
	 //接收邮箱
	 @Column(name="S_REVEIVEMAIL", nullable=false)
     private String SReveivemail;
	 
	 //发送邮箱
	 @Column(name="S_SENDMAIL", nullable=false)
     private String SSendmail;
	 
	 //发件人名称
	 @Column(name="S_FROMNAME")
     private String SFromname;
	 
	 
	 //发送邮箱密码
	 @Column(name="S_SENDMAILPWD", nullable=false)
     private String SSendmailpwd;
	 
	 //邮件标题
	 @Column(name="S_TITLE", nullable=false)
     private String STitle;
	 
	 //邮件内容
	 @Column(name="S_CONTENT", nullable=false) 
     private Clob SContent;
	 
	 //邮件发送的协议
	 @Column(name="S_PROTOCOL", nullable=false)
     private String SProtocol;
	 
	 //邮件发送的服务器
	 @Column(name="S_HOST", nullable=false)
     private String SHost;
	 
	 //发送附件的路径1
	 @Column(name="S_FILEPATH1")
     private String SFilepath1;
	 
	 //邮件发送的端口
	 @Column(name="S_PORT", nullable=false)
     private String SPort;
	 
	 //发送附件的路径2
	 @Column(name="S_FILEPATH2")
     private String SFilepath2;
	 
	 //-1: 未处理(过了有效期了); -2: 发送失败（尝试发送指定次数还是未成功）; 1:已处理;
	 @Column(name="S_STATUS", nullable=false, precision=38, scale=0)
     private Long SStatus;
	 
	 //如果为0 ,  则表示发送直到成功
	 @Column(name="S_MAXTIMES", nullable=false, precision=38, scale=0)
     private Long SMaxtimes;
	 
	 //已发送次数
	 @Column(name="S_TIMES", nullable=false, precision=38, scale=0)
     private Long STimes;
	 
	 //插入时间
	 @Column(name="S_CREATETIME", nullable=false)
     private Date SCreatetime;
	 
	 //如果时时发送,即插入当前时间即可
	 @Column(name="S_SENDTIME", nullable=false)
     private Date SSendtime;
	 
	 //如果超过此时间则不可再发送此邮件出去
	 @Column(name="S_EXPIREDTIME", nullable=false)
     private Date SExpiredtime;

	public Long getSId() {
		return SId;
	}

	public void setSId(Long id) {
		SId = id;
	}

	public String getSReveivemail() {
		return SReveivemail;
	}

	public void setSReveivemail(String reveivemail) {
		SReveivemail = reveivemail;
	}

	public String getSSendmail() {
		return SSendmail;
	}

	public void setSSendmail(String sendmail) {
		SSendmail = sendmail;
	}

	public String getSSendmailpwd() {
		return SSendmailpwd;
	}

	public void setSSendmailpwd(String sendmailpwd) {
		SSendmailpwd = sendmailpwd;
	}

	public String getSTitle() {
		return STitle;
	}

	public void setSTitle(String title) {
		STitle = title;
	}

	public String getSContent() {
		try {
			return (SContent != null ? (SContent.getSubString(1, (int)SContent.length())) : StringUtils.EMPTY);
		} catch (SQLException e) {
			return StringUtils.EMPTY;
		} 
	}

	public void setSContent(Clob content) {
		SContent = content;
	}

	public String getSProtocol() {
		return SProtocol;
	}

	public void setSProtocol(String protocol) {
		SProtocol = protocol;
	}

	public String getSHost() {
		return SHost;
	}

	public void setSHost(String host) {
		SHost = host;
	}

	public String getSFilepath1() {
		return SFilepath1;
	}

	public void setSFilepath1(String filepath1) {
		SFilepath1 = filepath1;
	}

	public String getSPort() {
		return SPort;
	}

	public void setSPort(String port) {
		SPort = port;
	}

	public String getSFilepath2() {
		return SFilepath2;
	}

	public void setSFilepath2(String filepath2) {
		SFilepath2 = filepath2;
	}

	public Long getSStatus() {
		return SStatus;
	}

	public void setSStatus(Long status) {
		SStatus = status;
	}

	public Long getSMaxtimes() {
		return SMaxtimes;
	}

	public void setSMaxtimes(Long maxtimes) {
		SMaxtimes = maxtimes;
	}

	public Long getSTimes() {
		return STimes;
	}

	public void setSTimes(Long times) {
		STimes = times;
	}

	public Date getSCreatetime() {
		return SCreatetime;
	}

	public void setSCreatetime(Date createtime) {
		SCreatetime = createtime;
	}

	public Date getSSendtime() {
		return SSendtime;
	}

	public void setSSendtime(Date sendtime) {
		SSendtime = sendtime;
	}

	public Date getSExpiredtime() {
		return SExpiredtime;
	}

	public void setSExpiredtime(Date expiredtime) {
		SExpiredtime = expiredtime;
	}

	/**
	 * @return the sFromname
	 */
	public String getSFromname() {
		return SFromname;
	}

	/**
	 * @param fromname the sFromname to set
	 */
	public void setSFromname(String fromname) {
		SFromname = fromname;
	}

}