package com.jinxin.model;

/**
 * 
 * <p>Title: </p>
 * <p>Description: 商户提交的附加元素</p>
 * <p>Copyright: jinxin (c) 2013 版权</p>
 * <p>Company: </p>
 * @author 
 * @version V1.0 
 * @date 2013-7-14上午11:33:30
 */
public class ExtraElementBean implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/** 元素中文名称 **/
	private String nameCn;
	
	/** 元素英文名称 **/
	private String nameEn;
	
	/** 元素值 **/
	private String value;
	
	/** 元素长度 **/
	private int length;
	
	/** 元素状态 **/
	private int status;

	/** 元素获取的值 **/
	private String inComeValue;

	public String getNameCn() {
		return nameCn;
	}

	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getInComeValue() {
		return inComeValue;
	}

	public void setInComeValue(String inComeValue) {
		this.inComeValue = inComeValue;
	}
}
