/**
 * create date 2009-5-6
 */
package com.jinxin.common.valueobj;

import java.util.Date;


/**
 * @author eric
 * 
 */
public class GatewayResult {

    /**
     * 交易是否成功 true 成功，false 非成功（失败，等待中，未知）
     */
    private boolean success;

    /**
     * 交易状态 -2 : 待确认 ; -1 : 待处理 ; 0 : 失败 ; 1 : 成功 ;
     */
    private String status;

    /**
     * 交易流水号（网关流水号）
     */
    private String tradeNo;

    /**
     * 交易流水号（网关流水号）
     */
    private String signinfo;

    /**
     * 网关支付通知应答信息
     */
    private String result;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getSigninfo() {
		return signinfo;
	}

	public void setSigninfo(String signinfo) {
		this.signinfo = signinfo;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
