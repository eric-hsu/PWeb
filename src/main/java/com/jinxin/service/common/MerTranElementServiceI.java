package com.jinxin.service.common;

import java.util.List;

import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.model.persistence.MerTranElement;
import com.jinxin.model.persistence.Traderecord;

public interface MerTranElementServiceI {
	
	public List<MerTranElement> getMerTranElement(String mrMerNo,String mrGwNo);
	
	
	public OperateResult riskControl(List<MerTranElement> list,Traderecord traderecord,int position);


	public OperateResult saveMerTranElement(MerTranElement agentMer);


	public OperateResult saveOrUpdateMerTranElement(MerTranElement agentMer);
}	
