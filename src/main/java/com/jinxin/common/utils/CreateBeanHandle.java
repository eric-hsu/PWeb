package com.jinxin.common.utils;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.jinxin.model.persistence.AgentMer;
import com.jinxin.model.persistence.MerChannel;
import com.jinxin.model.persistence.Traderecord;

/**
 * @className:CreateBeanHandle.java
 * @classDescription:
 * @author:erichu
 * @createTime:2014-6-26 下午1:58:33
 */

public class CreateBeanHandle {


	public static Traderecord createTraderecordBean(HttpServletRequest request,List<MerChannel> merChannelList,AgentMer agentMer){
		Traderecord traderecord = new Traderecord();
		

		return traderecord;
	}
}
