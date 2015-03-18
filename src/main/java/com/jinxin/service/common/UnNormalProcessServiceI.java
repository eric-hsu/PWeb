package com.jinxin.service.common;

import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.model.persistence.UnNormalProcess;

/**
 * 
 * <p>Title: </p>
 * <p>Description: 接口service方法</p>
 * <p>Copyright: jinxin (c) 2013 版权</p>
 * <p>Company: </p>
 * @author 
 * @version V1.0 
 * @date 2013-6-27下午03:23:12
 */
public interface UnNormalProcessServiceI {
	
	
	public UnNormalProcess getUnNormalProcess(String trNo);
	
	
	public OperateResult saveUnNormalProcess(UnNormalProcess unNormalProcess);
	
	
	public OperateResult saveOrUpdateUnNormalProcess(UnNormalProcess unNormalProcess);
}
