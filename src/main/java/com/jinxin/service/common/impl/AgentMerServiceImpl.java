package com.jinxin.service.common.impl;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.dao.BaseDaoI;
import com.jinxin.model.persistence.AgentMer;
import com.jinxin.service.common.AgentMerServiceI;

@Service("agentMerService")
@Scope("prototype")
public class AgentMerServiceImpl implements AgentMerServiceI {
	private static final Logger logger = Logger.getLogger(AgentMerServiceImpl.class);
	@Autowired
	private BaseDaoI baseDao;
	
	@Override
	public AgentMer getAgentMer(String merchant,String amGwNo) {
		Map<String, Object> params = new HashMap<String, Object>();
    	params.put("merNo",  Long.valueOf(merchant));
    	params.put("amGwNo",  Long.valueOf(amGwNo));
    	AgentMer agentMer = (AgentMer) baseDao.get("from AgentMer t where  t.merchant.merNo = :merNo and t.amGwNo = :amGwNo",params);
		return agentMer;
	}

	@Override
	public OperateResult saveAgentMer(AgentMer agentMer) {
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(true);
		Long count =  (Long) baseDao.save(agentMer);
		if(count<1){
			operateResult.setSuccess(false);
		}
		return operateResult;
	}

	@Override
	public OperateResult saveOrUpdateAgentMer(AgentMer agentMer) {
		// TODO Auto-generated method stub
		return null;
	}

}
