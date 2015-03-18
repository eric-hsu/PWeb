package com.jinxin.service.common;

import java.util.List;

import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.model.persistence.MerRate;
import com.jinxin.model.persistence.Rate;

/**
 * @className:RateServiceI.java
 * @classDescription:
 * @author:erichu
 * @createTime:2014-8-10 下午3:35:41
 */

public interface RateServiceI {

	public Rate getRate(String originalCurrency,String targetCurrency,String type);
	
	public List<Rate> getAllRate(String type);
	
	public OperateResult saveRate(Rate rate);
	
	public OperateResult saveOrUpdateRate(Rate rate);
}
