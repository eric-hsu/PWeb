package com.jinxin.service.common;

import com.jinxin.service.paygateway.PayGatewayProxy;

public interface CommonServiceI {
	/**
	 * 
	 * @author  eric
	 * @Title findPayGatewayProxy
	 * @Time: 2011-7-8下午02:28:25
	 * @Description: 获取网关代理
	 * @return: 
	 * @throws: 
	 * @return
	 */
	public PayGatewayProxy findPayGatewayProxy(String operateMode);
}
