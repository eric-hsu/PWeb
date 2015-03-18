package com.jinxin.service.common.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.dao.BaseDaoI;
import com.jinxin.model.persistence.Channel;
import com.jinxin.service.common.ChannelServiceI;

@Service("channelService")
@Scope("prototype")
public class ChannelServiceImpl implements ChannelServiceI {
	private static final Logger logger = Logger.getLogger(ChannelServiceImpl.class);
	@Autowired
	private BaseDaoI baseDao;
	@Override
	public Channel getChannel(String chaCode) {
		Map<String, Object> params = new HashMap<String, Object>();
    	params.put("chaCode",  Long.valueOf(chaCode));
    	Channel channel = (Channel) baseDao.get("from Channel t where  t.chaCode = :chaCode ",params);
		return channel;
	}
	
	@Override
	public Channel getChannel(String chaType,String cardType,String bankCode){

		Map<String, Object> params = new HashMap<String, Object>();
    	StringBuffer querychannelsql = new StringBuffer("from Channel t where 1=1 and t.chaStauts = 1 ");
    	if(StringUtils.isNotBlank(chaType)){
    		params.put("chaType",  chaType);
    		querychannelsql.append(" and t.chaType = :chaType ");
    	}else{
    		return null;
    	}
    	if("1".equals(chaType)){
	    	if(StringUtils.isNotBlank(cardType)){
	    		params.put("cardType",  cardType);
	    		querychannelsql.append(" and t.chaCardSupportType = :cardType ");
	    	}else{
	    		return null;
	    	}
    	}
    	if(StringUtils.isNotBlank(bankCode)){
    		params.put("bankCode",  bankCode);
    		querychannelsql.append(" and t.bank.bankCode = :bankCode ");
    	}else{
    		return null;
    	}

    	Channel channel = (Channel) baseDao.get(querychannelsql.toString(),params);
		return channel;
	}
	
	@Override
	public OperateResult saveChannel(Channel channel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OperateResult saveOrUpdateChannel(Channel channel) {
		// TODO Auto-generated method stub
		return null;
	}

}
