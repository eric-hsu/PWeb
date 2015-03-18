package com.jinxin.service.common;

import java.util.List;
import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.model.persistence.MerRate;

public interface MerRateServiceI {

	public MerRate getMerRate(long mrMerNo,long mrGwNo,long chaCode);
	
	
	public OperateResult saveMerRate(MerRate channel);
	
	
	public OperateResult saveOrUpdateMerRate(MerRate channel);
}
