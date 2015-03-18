package com.jinxin.model;

/**
 * 
 * <p>Title: </p>
 * <p>Description: 风控信息Bean</p>
 * <p>Copyright: jinxin (c) 2013 版权</p>
 * <p>Company: </p>
 * @author 
 * @version V1.0 
 * @date 2013-7-5下午02:43:31
 */
public class RiskControlBean implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/** 风险控制名称 **/
	private String riskName;
	
	/** 风险控制执行的方法 **/
	private String riskMethod;
	
	/** 风险控制地址 **/
	private String riskUrl;

	/** 风险控制位置 **/
	private int riskPosition;
	
	public String getRiskName() {
		return riskName;
	}

	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}

	public String getRiskMethod() {
		return riskMethod;
	}

	public void setRiskMethod(String riskMethod) {
		this.riskMethod = riskMethod;
	}

	public String getRiskUrl() {
		return riskUrl;
	}

	public void setRiskUrl(String riskUrl) {
		this.riskUrl = riskUrl;
	}

	public int getRiskPosition() {
		return riskPosition;
	}

	public void setRiskPosition(int riskPosition) {
		this.riskPosition = riskPosition;
	}
}
