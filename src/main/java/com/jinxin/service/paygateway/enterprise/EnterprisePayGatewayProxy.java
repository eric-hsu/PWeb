package com.jinxin.service.paygateway.enterprise;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.jinxin.common.valueobj.GatewayResult;
import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.model.BankReturnBean;
import com.jinxin.model.Card;
import com.jinxin.model.ReturnBean;
import com.jinxin.model.online.OlineSendDataBean;
import com.jinxin.service.paygateway.OnlineWorker;
import com.jinxin.service.paygateway.PayGatewayProxy;

/**
 * 网关代理 功能：封装调用网关时的sendBean和网关返回时的receiveBean
 * 
 * @author eric
 */
public abstract class EnterprisePayGatewayProxy implements PayGatewayProxy{
	 // 发送工作类

  
}
