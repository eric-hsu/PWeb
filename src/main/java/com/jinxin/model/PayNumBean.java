package com.jinxin.model;

/**
 * 
 * <p>Title: </p>
 * <p>Description: 支付次数Bean</p>
 * <p>Copyright: jinxin (c) 2013 版权</p>
 * <p>Company: </p>
 * @author 
 * @version V1.0 
 * @date 2013-9-29下午06:14:58
 */
public class PayNumBean implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	/** 元素ID **/
	private long payId;
	
	/** 支付时间（毫秒） **/
	private long payTime ;
	
	/** 支付成功次数 **/
	private long paySucCount;
	
	/** 支付总次数 **/
	private long payTotCount;

	public long getPayTime() {
		return payTime;
	}

	public void setPayTime(long payTime) {
		this.payTime = payTime;
	}

	public long getPaySucCount() {
		return paySucCount;
	}

	public void setPaySucCount(long paySucCount) {
		this.paySucCount = paySucCount;
	}

	public long getPayTotCount() {
		return payTotCount;
	}

	public void setPayTotCount(long payTotCount) {
		this.payTotCount = payTotCount;
	}

	public long getPayId() {
		return payId;
	}

	public void setPayId(long payId) {
		this.payId = payId;
	}
}
