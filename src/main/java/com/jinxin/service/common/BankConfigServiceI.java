package com.jinxin.service.common;

import java.util.List;

import com.jinxin.model.BankConfig;
import com.jinxin.model.persistence.TBankTag;

/**
 * @className:BankconifgI.java
 * @classDescription:
 * @author:erichu
 * @createTime:2014-6-27 下午4:33:12
 */

public interface BankConfigServiceI {
	public List<BankConfig> findBankConfig(String merNo,String gwNo);
}
