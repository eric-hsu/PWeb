package com.jinxin.service.paygateway;

import java.util.Map;
import com.jinxin.model.enterprise.EnterpriseSendDataBean;

public interface EnterpriseWorker {
	 /**
     * 网关发送信息
     * 
     * @param sendXml
     * @param sendBean
     * @return
     */
    public String send(Map<String, String> params, EnterpriseSendDataBean sendBean);
}
