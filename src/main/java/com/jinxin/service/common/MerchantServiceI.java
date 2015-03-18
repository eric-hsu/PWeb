package com.jinxin.service.common;

import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.model.persistence.MerRate;
import com.jinxin.model.persistence.Merchant;

/**
 * @className:MerchantServiceI.java
 * @classDescription:
 * @author:erichu
 * @createTime:2014-8-10 上午11:22:45
 */

public interface MerchantServiceI {
	
	public Merchant getMerchant(String merNo);
	
	
	public OperateResult saveMerchant(Merchant merchant);
	
	
	public OperateResult saveOrUpdateMerchant(Merchant merchant);
}
