package com.jinxin.model;

/**
 * 
 * <p>Title: </p>
 * <p>Description: 返回结果Bean</p>
 * <p>Copyright: jinxin (c) 2013 版权</p>
 * <p>Company: </p>
 * @author 
 * @version V1.0 
 * @date 2013-4-29上午10:26:37
 */
public class ReturnBean implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	/** 返回代码,默认返回0000 **/
	private String returnCode = ConstantsBean.SUCCESS_CODE;

	/** 
	 * 返回商户代码,默认返回0000
	 * desciption:添加此参数处理当与银行交易成功时，
	 * 但后台产生系统异常（如未添加发送邮件信息），对外仍显示交易成功 
	 * modify by peifang on 2013-02-08 11:13
	 */
	private String returnCodeOut;
	
	/** 返回信息(对内) **/
	private String returnInfoIn;
	
	/** 返回信息(对外:显示银行交易信息) **/
	private String returnInfoOut;
	
	/** 返回跳转地址 **/
	private String returnUrl;
	
	/** 返回值 **/
	private Object returnValue;
	
	/** 监控级别 */
	private String level;
	
	/** 监控元素ID */
	private int teId;

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnInfoIn() {
		return returnInfoIn;
	}

	public void setReturnInfoIn(String returnInfoIn) {
		this.returnInfoIn = returnInfoIn;
	}

	public String getReturnInfoOut() {
		return returnInfoOut;
	}

	public void setReturnInfoOut(String returnInfoOut) {
		this.returnInfoOut = returnInfoOut;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public Object getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(Object returnValue) {
		this.returnValue = returnValue;
	}

	public String getReturnCodeOut() {
		return returnCodeOut;
	}

	public void setReturnCodeOut(String returnCodeOut) {
		this.returnCodeOut = returnCodeOut;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public int getTeId() {
		return teId;
	}

	public void setTeId(int teId) {
		this.teId = teId;
	}
	
	
}
