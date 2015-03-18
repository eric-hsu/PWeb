package com.jinxin.service.paygateway.enterprise.abc.util;

import com.jinxin.model.online.OlineSendDataBean;

/**
 * @className:AcnSendBean.java
 * @classDescription:
 * @author:erichu
 * @createTime:2014-6-26 下午4:12:18
 */

public class AbcESendBean extends OlineSendDataBean{
	private String customer;

	public String getCustomer() {
		return this.customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}
}
