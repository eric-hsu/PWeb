package com.jinxin.service.common.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.dao.BaseDaoI;
import com.jinxin.model.persistence.Gateway;
import com.jinxin.model.persistence.InformalTraderecord;
import com.jinxin.service.common.GatewayServiceI;

@Service("gatewayService")
@Scope("prototype")
public class GatewayServiceImpl implements GatewayServiceI {
	@Autowired
	private BaseDaoI baseDao;
	
	@Override
	public Gateway getGateway(String trMerNo, String trGwNo) {
		Map<String, Object> params0 = new HashMap<String, Object>();
    	params0.put("merNo", Long.valueOf(trMerNo));
    	params0.put("gwNo", Long.valueOf(trGwNo));
    	Gateway gateway = (Gateway) baseDao.get("from Gateway t where  t.merchant.merNo = :merNo and t.gwNo = :gwNo ",params0);
    	return gateway;
	}

	@Override
	public OperateResult saveGateway(Gateway gateway) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OperateResult saveOrUpdateGateway(Gateway gateway) {
		// TODO Auto-generated method stub
		return null;
	}

}
