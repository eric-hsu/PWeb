package com.jinxin.service.common;

import java.util.List;

import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.model.persistence.MerChannel;

public interface MerChannelServiceI {

	public List<MerChannel> getMerChannel(String mcMerNo,String mcGwNo);
	
	
	public OperateResult saveMerChannel(MerChannel merChannel);
	
	
	public OperateResult saveOrUpdateMerChannel(MerChannel merChannel);
}
