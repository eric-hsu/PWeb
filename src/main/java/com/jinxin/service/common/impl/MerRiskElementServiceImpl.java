package com.jinxin.service.common.impl;

import java.util.ArrayList;
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
import com.jinxin.model.persistence.MerRiskElement;
import com.jinxin.service.common.MerRiskElementServiceI;

@Service("merRiskElementService")
@Scope("prototype")
public class MerRiskElementServiceImpl implements MerRiskElementServiceI {
	private static final Logger logger = Logger.getLogger(AgentMerServiceImpl.class);
	@Autowired
	private BaseDaoI baseDao;
	
	@Override
	public List<MerRiskElement> getMerRiskElement(String mrMerNo, String mrGwNo) {
		Map<String, Object> params = new HashMap<String, Object>();
    	params.put("mrMerNo", Long.valueOf(mrMerNo));
    	params.put("mrGwNo", Long.valueOf(mrGwNo));
    	List<MerRiskElement> riskList = new ArrayList<MerRiskElement>();
    	
    	riskList = (List<MerRiskElement>) baseDao.find("from MerRiskElement t where  t.mrMerNo = :mrMerNo and t.mrGwNo = :mrGwNo",params);
		return riskList;
	}

	@Override
	public OperateResult saveMerRiskElement(MerRiskElement agentMer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OperateResult saveOrUpdateMerRiskElement(MerRiskElement agentMer) {
		// TODO Auto-generated method stub
		return null;
	}

}
