package com.jinxin.service.common;

import java.util.List;

import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.model.persistence.AgentMer;
import com.jinxin.model.persistence.MerRiskElement;

public interface MerRiskElementServiceI {
	
	public List<MerRiskElement> getMerRiskElement(String mrMerNo,String mrGwNo);
	
	
	public OperateResult saveMerRiskElement(MerRiskElement agentMer);
	
	
	public OperateResult saveOrUpdateMerRiskElement(MerRiskElement agentMer);
}
