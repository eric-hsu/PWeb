package com.jinxin.model;

/**
 * 
 * <p>Title: </p>
 * <p>Description: 交易监控信息Bean</p>
 * <p>Copyright: jinxin (c) 2013 版权</p>
 * <p>Company: </p>
 * @author 
 * @version V1.0 
 * @date 2013-7-5下午02:43:31
 */
public class TranControlBean implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/** 交易监控名称 **/
	private String tranName;
	
	/** 交易监控执行的方法 **/
	private String tranMethod;
	
	/** 交易监控地址 **/
	private String tranUrl;

	/** 交易监控位置 **/
	private int tranPosition;

	public String getTranName() {
		return tranName;
	}

	public void setTranName(String tranName) {
		this.tranName = tranName;
	}

	public String getTranMethod() {
		return tranMethod;
	}

	public void setTranMethod(String tranMethod) {
		this.tranMethod = tranMethod;
	}

	public String getTranUrl() {
		return tranUrl;
	}

	public void setTranUrl(String tranUrl) {
		this.tranUrl = tranUrl;
	}

	public int getTranPosition() {
		return tranPosition;
	}

	public void setTranPosition(int tranPosition) {
		this.tranPosition = tranPosition;
	}
	
	
}
