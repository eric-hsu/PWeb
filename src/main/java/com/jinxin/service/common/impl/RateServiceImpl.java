package com.jinxin.service.common.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.dao.BaseDaoI;
import com.jinxin.model.persistence.MerRate;
import com.jinxin.model.persistence.Rate;
import com.jinxin.service.common.RateServiceI;

/**
 * @className:RateServiceImpl.java
 * @classDescription:
 * @author:erichu
 * @createTime:2014-8-10 下午3:38:59
 */
@Service("rateService")
@Scope("prototype")
public class RateServiceImpl implements RateServiceI {
	@Autowired
	private BaseDaoI baseDao;
	@Override
	public Rate getRate(String originalCurrency, String targetCurrency,String type) {
		Map<String, Object> params = new HashMap<String, Object>();
    	params.put("rateOriginalCurrency", originalCurrency);
    	params.put("rateTargetCurrency", targetCurrency);
    	params.put("rateType", Integer.valueOf(type));
    	Rate rate = (Rate) baseDao.get("from Rate t where  t.rateOriginalCurrency = :rateOriginalCurrency and t.rateTargetCurrency= :rateTargetCurrency and t.rateType=:rateType ",params);
		return rate;
	}
	
	public List<Rate> getAllRate(String type){
		Map<String, Object> params = new HashMap<String, Object>();
    	params.put("rateType", Integer.valueOf(type));
    	List<Rate> list = (List<Rate>) baseDao.find("from Rate t where t.rateType=:rateType ",params);
		return list;
	}

	@Override
	public OperateResult saveRate(Rate rate) {
		return null;
	}

	@Override
	public OperateResult saveOrUpdateRate(Rate rate) {
		return null;
	}

}
