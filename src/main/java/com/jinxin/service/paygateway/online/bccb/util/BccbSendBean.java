package com.jinxin.service.paygateway.online.bccb.util;

import com.jinxin.model.online.OlineSendDataBean;

/**
 * @className:AcnSendBean.java
 * @classDescription:
 * @author:erichu
 * @createTime:2014-6-26 下午4:12:18
 */

public class BccbSendBean extends OlineSendDataBean{
	
	private String netType;
	
	private String merReqData;

	public String getNetType() {
		return netType;
	}

	public void setNetType(String netType) {
		this.netType = netType;
	}

	public String getMerReqData() {
		return merReqData;
	}

	public void setMerReqData(String merReqData) {
		this.merReqData = merReqData;
	}
	
	
}
