package com.jinxin.model.offline;

import java.io.Serializable;

/**
 * 离线发送请求的对象的父类
 * 
 * @author eric
 * 
 */
public class OfflineSendDataBean implements Serializable{
    private static final long serialVersionUID = 4496297859785033269L;

    // 任务单号
    private Long orderId;

    // 操作员
    private String operator;

    public Long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
    public String getOperator() {
        return operator;
    }

	public void setOperator(String operator) {
        this.operator = operator;
    }
}
