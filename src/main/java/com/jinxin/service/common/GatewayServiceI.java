package com.jinxin.service.common;

import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.model.persistence.Gateway;

public interface GatewayServiceI {
	
	public Gateway getGateway(String trMerNo, String trGwNo);
	
	
	public OperateResult saveGateway(Gateway gateway);
	
	
	public OperateResult saveOrUpdateGateway(Gateway gateway);
}
