package com.jinxin.common.datasource;

import com.jinxin.common.utils.SecUtil;


/**
 * 
 * <p>Title: </p>
 * <p>Description: 新建数据源对密码进行解密</p>
 * <p>Copyright: jinxin (c) 2013 版权</p>
 * <p>Company: </p>
 * @author 
 * @version V1.0 
 * @date 2013-5-27上午11:31:16
 */
public class DataSource extends org.logicalcobwebs.proxool.ProxoolDataSource {

	
	/**
	 * 解密Url
	 */
	public void setDriverUrl(String url) {
		super.setDriverUrl(SecUtil.decrypt(url));
	}
	
	/**
	 * 解密用户名
	 */
	public void setUser(String user) {
		super.setUser(SecUtil.decrypt(user));
	}

	/**
	 * 解密密码
	 */
	public void setPassword(String password) {
		super.setPassword(SecUtil.decrypt(password));
	}	
}
