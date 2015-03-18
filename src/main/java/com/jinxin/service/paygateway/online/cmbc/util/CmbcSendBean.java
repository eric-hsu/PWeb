package com.jinxin.service.paygateway.online.cmbc.util;

import com.jinxin.model.online.OlineSendDataBean;

/**
 * @className:AcnSendBean.java
 * @classDescription:
 * @author:erichu
 * @createTime:2014-6-26 下午4:12:18
 */

public class CmbcSendBean extends OlineSendDataBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8475384697579529640L;

	//支付加密信息
	private String  decrydata;
		
	//查询或退款加密信息
	private String cryptograph;

	public String getDecrydata() {
		return decrydata;
	}

	public void setDecrydata(String decrydata) {
		this.decrydata = decrydata;
	}

	public String getCryptograph() {
		return cryptograph;
	}

	public void setCryptograph(String cryptograph) {
		this.cryptograph = cryptograph;
	}
}
