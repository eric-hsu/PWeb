package com.jinxin.common.valueobj;

/**
 * @className:OperateResult.java
 * @classDescription:
 * @author:erichu
 * @createTime:2014-6-23 下午4:19:38
 */

public class OperateResult {
	private boolean success;
	private String message;
	private String trNo;
	public boolean isSuccess() {
		return this.success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return this.message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public String getTrNo() {
		return this.trNo;
	}

	public void setTrNo(String trNo) {
		this.trNo = trNo;
	}

}
