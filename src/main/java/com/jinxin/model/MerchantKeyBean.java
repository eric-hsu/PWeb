package com.jinxin.model;

/**
 * 
 * <p>Title: </p>
 * <p>Description: 银行通道参数信息Bean</p>
 * <p>Copyright: jinxin (c) 2013 版权</p>
 * <p>Company: </p>
 * @author 
 * @version V1.0 
 * @date 2013-7-14下午06:27:48
 */
public class MerchantKeyBean implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/** 商户ID **/
	private int merId;
	
	/** 公钥 **/
	private String pubKey;
	
	/** 私钥  **/
	private String priKey;

	public int getMerId() {
		return merId;
	}

	public void setMerId(int merId) {
		this.merId = merId;
	}

	public String getPubKey() {
		return pubKey;
	}

	public void setPubKey(String pubKey) {
		this.pubKey = pubKey;
	}

	public String getPriKey() {
		return priKey;
	}

	public void setPriKey(String priKey) {
		this.priKey = priKey;
	}
	
	
}
