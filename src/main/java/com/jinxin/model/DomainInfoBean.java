package com.jinxin.model;

import java.sql.Clob;


/**
 * 
 * <p>Title: </p>
 * <p>Description: 网关接入号绑定支付域名Bean</p>
 * <p>Copyright: jinxin (c) 2013 版权</p>
 * <p>Company: </p>
 * @author 
 * @version V1.0 
 * @date 2013-7-5下午02:43:31
 */
public class DomainInfoBean implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/** 域名名称 **/
	private String domainName = "";
	
	/** 域名跳转url **/
	private String domainUrl = "";
	
	/** 域名IP **/
	private String domainIp = "";
	
	/** 帮助网站 **/
	private String helpWebSite = "";
	
	/** 客服电话 **/
	private String tel = "";

	/** 传真 **/
	private String fax = "";

	/** 客服邮箱 **/
	private String csEmail = "";

	/** 邮件域名 **/
	private String fromName = "";

	/** 邮件标题 **/
	private String emailTitle = "";

	/** 感谢语 **/
	private String thxChoose = "";
	
	/** 订单标识 **/
	private String cttOrderNo = "";
	
	/** 账单显示 **/
	private String acquirer = "";

	/** 邮箱显示名称 **/
	private String showNick = "";

	/** 发送邮件账号 **/
	private String sendEmail = "";

	/** 发送邮件密码 **/
	private String sendPwd = "";

	/** 发送邮件端口 **/
	private int sendPort;

	/** 发送邮件Host **/
	private String sendHost = "";

	/** 发送邮件协议 **/
	private String sendPortocol = "";

	/** 邮件主题 **/
	private String emailSubject = "";
	
	/** 邮件内容 **/
	private Clob emailContent;

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getDomainUrl() {
		return domainUrl;
	}

	public void setDomainUrl(String domainUrl) {
		this.domainUrl = domainUrl;
	}

	public String getDomainIp() {
		return domainIp;
	}

	public void setDomainIp(String domainIp) {
		this.domainIp = domainIp;
	}

	public String getHelpWebSite() {
		return helpWebSite;
	}

	public void setHelpWebSite(String helpWebSite) {
		this.helpWebSite = helpWebSite;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getCsEmail() {
		return csEmail;
	}

	public void setCsEmail(String csEmail) {
		this.csEmail = csEmail;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getEmailTitle() {
		return emailTitle;
	}

	public void setEmailTitle(String emailTitle) {
		this.emailTitle = emailTitle;
	}

	public String getThxChoose() {
		return thxChoose;
	}

	public void setThxChoose(String thxChoose) {
		this.thxChoose = thxChoose;
	}

	public String getCttOrderNo() {
		return cttOrderNo;
	}

	public void setCttOrderNo(String cttOrderNo) {
		this.cttOrderNo = cttOrderNo;
	}

	public String getAcquirer() {
		return acquirer;
	}

	public void setAcquirer(String acquirer) {
		this.acquirer = acquirer;
	}

	public String getShowNick() {
		return showNick;
	}

	public void setShowNick(String showNick) {
		this.showNick = showNick;
	}

	public String getSendEmail() {
		return sendEmail;
	}

	public void setSendEmail(String sendEmail) {
		this.sendEmail = sendEmail;
	}

	public String getSendPwd() {
		return sendPwd;
	}

	public void setSendPwd(String sendPwd) {
		this.sendPwd = sendPwd;
	}

	public int getSendPort() {
		return sendPort;
	}

	public void setSendPort(int sendPort) {
		this.sendPort = sendPort;
	}

	public String getSendHost() {
		return sendHost;
	}

	public void setSendHost(String sendHost) {
		this.sendHost = sendHost;
	}

	public String getSendPortocol() {
		return sendPortocol;
	}

	public void setSendPortocol(String sendPortocol) {
		this.sendPortocol = sendPortocol;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public Clob getEmailContent() {
		return emailContent;
	}

	public void setEmailContent(Clob emailContent) {
		this.emailContent = emailContent;
	}
}
