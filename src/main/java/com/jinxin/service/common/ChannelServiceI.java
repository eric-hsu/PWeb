package com.jinxin.service.common;

import java.util.List;

import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.model.persistence.Channel;
import com.jinxin.model.persistence.MerChannel;

public interface ChannelServiceI {

	public Channel getChannel(String chaCode);
	
	public Channel getChannel(String chaType,String cardType,String bankCode);
	
	
	public OperateResult saveChannel(Channel channel);
	
	
	public OperateResult saveOrUpdateChannel(Channel channel);
}
