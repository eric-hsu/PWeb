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
import com.jinxin.model.persistence.MerChannel;
import com.jinxin.model.persistence.UnNormalTraderecord;
import com.jinxin.service.common.MerChannelServiceI;
@Service("merChannelService")
@Scope("prototype")
public class MerChannelServiceImpl implements MerChannelServiceI {
	private static final Logger logger = Logger.getLogger(MerChannelServiceImpl.class);
	@Autowired
	private BaseDaoI baseDao;
	
	@Override
	public List<MerChannel> getMerChannel(String mcMerNo,String mcGwNo) {
		Map<String, Object> params = new HashMap<String, Object>();
    	params.put("mcMerNo", Long.valueOf(mcMerNo));
    	params.put("mcGwNo", Long.valueOf(mcGwNo));
    	List list = new ArrayList();
    	list = (List) baseDao.find("from MerChannel t where  t.mcMerNo = :mcMerNo and  t.mcGwNo = :mcGwNo ",params);
		return list;
	}

	@Override
	public OperateResult saveMerChannel(MerChannel merChannel) {
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(true);
		Long count =  (Long) baseDao.save(merChannel);
		if(count<1){
			operateResult.setSuccess(false);
		}
		return operateResult;
	}

	@Override
	public OperateResult saveOrUpdateMerChannel(MerChannel merChannel) {
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(true);
		baseDao.saveOrUpdate(merChannel);
		return operateResult;
	}

}
