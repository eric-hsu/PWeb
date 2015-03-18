package com.jinxin.service.paygateway;

import java.util.Map;
import com.jinxin.model.online.OlineSendDataBean;

public interface OnlineWorker {
	 /**
     * 网关发送信息
     * 
     * @param sendXml
     * @param sendBean
     * @return
     */
    public String send(Map<String, String> params, OlineSendDataBean sendBean);
}
