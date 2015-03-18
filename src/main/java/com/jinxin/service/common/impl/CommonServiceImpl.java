package com.jinxin.service.common.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jinxin.common.utils.SpringContextUtil;
import com.jinxin.service.common.CommonServiceI;
import com.jinxin.service.paygateway.PayGatewayProxy;

@Service("commonService")
@Scope("prototype")
public class CommonServiceImpl implements CommonServiceI {

	@Override
	public PayGatewayProxy findPayGatewayProxy(String operateMode) {
		return (PayGatewayProxy) SpringContextUtil.getBean(operateMode);
	}

}
