package com.jinxin.service.common.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.dao.BaseDaoI;
import com.jinxin.model.persistence.AgentMer;
import com.jinxin.model.persistence.MerRate;
import com.jinxin.service.common.MerRateServiceI;

@Service("merRateService")
@Scope("prototype")
public class MerRateServiceImpl implements MerRateServiceI {
	private static final Logger logger = Logger.getLogger(MerRateServiceImpl.class);
	@Autowired
	private BaseDaoI baseDao;
	@Override
	public MerRate getMerRate(long mrMerNo,long mrGwNo,long chaCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("mrMerNo",  mrMerNo);
		params.put("mrGwNo",  mrGwNo);
    	params.put("mrChaCode",  chaCode);
    	MerRate merRate = (MerRate) baseDao.get("from MerRate t where  t.mrMerNo = :mrMerNo and t.mrGwNo = :mrGwNo and t.mrChaCode = :mrChaCode ",params);
		return merRate;
	}

	@Override
	public OperateResult saveMerRate(MerRate channel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OperateResult saveOrUpdateMerRate(MerRate channel) {
		// TODO Auto-generated method stub
		return null;
	}

}
