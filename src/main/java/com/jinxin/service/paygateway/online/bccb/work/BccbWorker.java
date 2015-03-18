package com.jinxin.service.paygateway.online.bccb.work;

import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import com.jinxin.model.online.OlineSendDataBean;
import com.jinxin.service.paygateway.OnlineWorker;
import com.jinxin.service.paygateway.online.cib.util.CibSendBean;

/**
 * 北京银行网关请求类
 * 
 * @author zhouyiqing
 * 
 */
@Component
public class BccbWorker  implements OnlineWorker{
    private static final Logger logger = Logger.getLogger(BccbWorker.class);
    // 1秒等于1000毫秒的单位
    private static final int TIME_UNIT = 1000;

    /**
     * 给北京银行发送请求，把返回的信息转换成map并返回
     * 
     * @param sendXml
     * @param sendBean
     * @return
     */
    public String send(Map<String, String> params, OlineSendDataBean sendBean) {
    	CibSendBean cibSendBean = null;

        return "";
    }
}
