package com.jinxin.model;

/**
 * 
 * <p>Title: </p>
 * <p>Description: maxmind设置bean</p>
 * <p>Copyright: jinxin (c) 2013 版权</p>
 * <p>Company: </p>
 * @author 
 * @version V1.0 
 * @date 2013-9-17下午03:25:15
 */
public class MaxMindBean implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	/** 是否检测风险分数 **/
	private int isCheckScores = 0;
	
	/** 通过风险分数 **/
	private double passScores;

	public int getIsCheckScores() {
		return isCheckScores;
	}

	public void setIsCheckScores(int isCheckScores) {
		this.isCheckScores = isCheckScores;
	}

	public double getPassScores() {
		return passScores;
	}

	public void setPassScores(double passScores) {
		this.passScores = passScores;
	}
}
