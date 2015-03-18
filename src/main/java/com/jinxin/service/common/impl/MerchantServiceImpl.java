package com.jinxin.service.common.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.dao.BaseDaoI;
import com.jinxin.model.persistence.MerRate;
import com.jinxin.model.persistence.Merchant;
import com.jinxin.service.common.MerchantServiceI;

/**
 * @className:MerchantServiceImpl.java
 * @classDescription:
 * @author:erichu
 * @createTime:2014-8-10 上午11:23:57
 */
@Service("merchantService")
@Scope("prototype")
public class MerchantServiceImpl implements MerchantServiceI {
	private static final Logger logger = Logger.getLogger(MerchantServiceImpl.class);
	@Autowired
	private BaseDaoI baseDao;
	
	/** 
	 * @Description:  TODO(这里用一句话描述这个方法的作用) 
	 * @param:                
	 * @author        erichu
	 * @Date          2014-8-10 上午11:23:57 
	 */
	public Merchant getMerchant(String merNo) {
		Map<String, Object> params = new HashMap<String, Object>();
    	params.put("merNo",  Long.valueOf(merNo));
    	Merchant merchant = (Merchant) baseDao.get("from Merchant t where  t.merNo = :merNo ",params);
		return merchant;
	}

	/** 
	 * @Description:  TODO(这里用一句话描述这个方法的作用) 
	 * @param:                
	 * @author        erichu
	 * @Date          2014-8-10 上午11:23:57 
	 */
	public OperateResult saveMerchant(Merchant merchant) {
		// TODO Auto-generated method stub
		return null;
	}

	/** 
	 * @Description:  TODO(这里用一句话描述这个方法的作用) 
	 * @param:                
	 * @author        erichu
	 * @Date          2014-8-10 上午11:23:57 
	 */
	public OperateResult saveOrUpdateMerchant(Merchant merchant) {
		// TODO Auto-generated method stub
		return null;
	}

}
