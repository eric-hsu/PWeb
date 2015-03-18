package com.jinxin.service.common;

import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.model.persistence.AgentMer;
import com.jinxin.model.persistence.Traderecord;

public interface AgentMerServiceI {
	
	public AgentMer getAgentMer(String merchant,String amGwNo);
	
	
	public OperateResult saveAgentMer(AgentMer agentMer);
	
	
	public OperateResult saveOrUpdateAgentMer(AgentMer agentMer);
}
