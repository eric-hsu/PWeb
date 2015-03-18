package com.jinxin.model;

/**
 * 
 * <p>Title: </p>
 * <p>Description: 元素Bean:黑名单元素以及风控元素</p>
 * <p>Copyright: jinxin (c) 2013 版权</p>
 * <p>Company: </p>
 * @author 
 * @version V1.0 
 * @date 2013-9-29下午06:14:58
 */
public class ElementBean implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	/** 元素ID **/
	private int beId;
	
	/** 元素名称 **/
	private String beName;
	
	/** 元素代码 **/
	private String beCode;

	public int getBeId() {
		return beId;
	}

	public void setBeId(int beId) {
		this.beId = beId;
	}

	public String getBeName() {
		return beName;
	}

	public void setBeName(String beName) {
		this.beName = beName;
	}

	public String getBeCode() {
		return beCode;
	}

	public void setBeCode(String beCode) {
		this.beCode = beCode;
	}
}
