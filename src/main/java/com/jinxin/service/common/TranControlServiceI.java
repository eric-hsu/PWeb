package com.jinxin.service.common;

import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.model.persistence.Traderecord;


/**
 * @className:TranControlServiceI.java
 * @classDescription:
 * @author:erichu
 * @createTime:2014-8-10 下午8:42:00
 */

public interface TranControlServiceI {
	public OperateResult maxMoneyCheck(Traderecord traderecord);
}
