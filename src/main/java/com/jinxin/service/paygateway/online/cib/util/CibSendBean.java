package com.jinxin.service.paygateway.online.cib.util;

import com.jinxin.model.online.OlineSendDataBean;

/**
 * @className:AcnSendBean.java
 * @classDescription:
 * @author:erichu
 * @createTime:2014-6-26 下午4:12:18
 */

public class CibSendBean extends OlineSendDataBean{
	
	private String service;
	
	private String ver;
	
	private String sub_mrch;

	private String ord_ip;

	private String ord_date;
	
	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

	public String getSub_mrch() {
		return sub_mrch;
	}

	public void setSub_mrch(String sub_mrch) {
		this.sub_mrch = sub_mrch;
	}

	public String getOrd_ip() {
		return ord_ip;
	}

	public void setOrd_ip(String ord_ip) {
		this.ord_ip = ord_ip;
	}

	public String getOrd_date() {
		return ord_date;
	}

	public void setOrd_date(String ord_date) {
		this.ord_date = ord_date;
	}
}
