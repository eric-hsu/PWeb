package com.jinxin.service.paygateway.offline.creditcard.abc.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jinxin.common.valueobj.GatewayResult;
import com.jinxin.model.Card;
import com.jinxin.model.persistence.Traderecord;
import com.jinxin.service.paygateway.offline.OfflinePayGatewayProxy;

/**
 * @className:BocServiceImpl.java
 * @classDescription:
 * @author:erichu
 * @createTime:2014-6-26 下午4:06:00
 */
@Service("abcCreditcardService")
@Scope("prototype")
public class AbcCreditcardServiceImpl implements OfflinePayGatewayProxy{

	@Override
	public GatewayResult cardPay(Traderecord traderecord, Card card) {
		GatewayResult gatewayResult = new GatewayResult();
		gatewayResult.setSuccess(true);
		gatewayResult.setResult("success");
		return gatewayResult;
	}

	@Override
	public GatewayResult notifyPayment(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GatewayResult query(Traderecord tOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GatewayResult> query(Date startTime, Date endTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GatewayResult cancel(Traderecord tOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GatewayResult refund(Traderecord tOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GatewayResult notifyRefund(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
