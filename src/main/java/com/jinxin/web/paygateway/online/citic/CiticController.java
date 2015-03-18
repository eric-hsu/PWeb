package com.jinxin.web.paygateway.online.citic;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.jinxin.common.utils.InterfaceUtils;
import com.jinxin.common.utils.StaticMapUtil;
import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.model.BankReturnBean;
import com.jinxin.model.ConstantsBean;
import com.jinxin.model.ReturnBean;
import com.jinxin.model.persistence.Channel;
import com.jinxin.model.persistence.Gateway;
import com.jinxin.model.persistence.MerChannel;
import com.jinxin.model.persistence.Rate;
import com.jinxin.model.persistence.Traderecord;
import com.jinxin.service.common.ChannelServiceI;
import com.jinxin.service.common.GatewayServiceI;
import com.jinxin.service.common.MerChannelServiceI;
import com.jinxin.service.common.RateServiceI;
import com.jinxin.service.common.TraderecordServiceI;
import com.jinxin.service.paygateway.PayGatewayProxy;
import com.jinxin.service.paygateway.online.abc.util.AbcConstants;

/**
 * @className:AbcController.java
 * @classDescription:
 * @author:erichu
 * @createTime:2014-6-26 下午11:41:58
 */
@Controller
@RequestMapping(value = "/citicController")
@Scope("prototype")
public class CiticController {
	private static final Logger logger = Logger.getLogger(CiticController.class);
	@Autowired
	private PayGatewayProxy citicService;
	@Autowired
	private TraderecordServiceI traderecordService;
	@Autowired
	private GatewayServiceI gatewayService;
	@Autowired
	private ChannelServiceI channelService;
	@Autowired
	private RateServiceI rateService;
	/**
	 * 农行支付请求接口
	 */
	@RequestMapping(value="/orderRequest")
	public String orderRequest(@ModelAttribute("traderecord")Traderecord traderecord,HttpServletRequest request, HttpServletResponse response,Model model,RedirectAttributes redirectAttributes){
		logger.info("开始兴业银行在线支付,订单号："+traderecord.getTrMerOrderno()+",流水号："+traderecord.getTrNo());
		
		OperateResult operateResult = citicService.prefixPayment(traderecord, request);
		if(!operateResult.isSuccess()){
			model.addAttribute("message", operateResult.getMessage());
			return "/common/sendError";
		}
		
		logger.info("开始页面跳转...");
		return "paygateway/online/citic/sendForm";
	}
	

	/**
	 * 农行支付页面通知接口
	 */
	@RequestMapping(value="/frontNotify")
	public String frontNotify(HttpServletRequest request, HttpServletResponse response,Model model,RedirectAttributes redirectAttributes){
		BankReturnBean bankReturnBean = new BankReturnBean();
		String returnUrl = "";
		try {
			
			//1 处理网关通知结果
			OperateResult operateResult = citicService.frontNotifyPayment(request,response,bankReturnBean);
			
			if(!operateResult.isSuccess()){
				model.addAttribute("message", operateResult.getMessage());
				return "/common/notifyerror";
			}
			
			//订单信息获取
			
			Traderecord traderecord = traderecordService.getTraderecord(bankReturnBean.getResponseTrdeNo());
			
			if(traderecord==null){
				model.addAttribute("message", "不存在该订单");
				return "/common/notifyerror";
			}
			
			traderecord.setTrBankorderno(bankReturnBean.getResponseBankNo());
			traderecord.setTrBankdatetime(new Date());
			traderecord.setTrBanktradetime(bankReturnBean.getResponseTradeTime());
			traderecord.setTrQueryno(bankReturnBean.getResponseBankNo());
			traderecord.setTrBatchno(bankReturnBean.getResponseBatchNo());
			traderecord.setTrBankreturncode(bankReturnBean.getResponseCode());
			traderecord.setTrBankinfo(bankReturnBean.getResponseInfo());
			
			traderecord.setTrBankcurrency(traderecord.getTrCurrency());
			if(bankReturnBean.getResponseAmount() !=null){
				traderecord.setTrBankamout(new BigDecimal(bankReturnBean.getResponseAmount()));
			}else{
				traderecord.setTrBankamout(traderecord.getTrAmount());
			}
			if(ConstantsBean.TRADE_STATUS_SUCCESS==Integer.valueOf(bankReturnBean.getResponseStatus())){
				traderecord.setTrStatus(1);
			}else if(ConstantsBean.TRADE_STATUS_FAIL==Integer.valueOf(bankReturnBean.getResponseStatus())){
				traderecord.setTrStatus(0);
			}else{
				traderecord.setTrStatus(-2);
			}
			
			operateResult = traderecordService.saveOrUpdateTraderecord(traderecord);
			
			if(!operateResult.isSuccess()){
				model.addAttribute("message", operateResult.getMessage());
				return "/common/notifyerror";
			}
			
			Gateway gateway = gatewayService.getGateway(String.valueOf(traderecord.getTrMerNo()), String.valueOf(traderecord.getTrGwNo()));
			
			if(gateway==null){
				model.addAttribute("message", "商户信息错误！");
				return "/common/notifyerror";
			}
			

			InterfaceUtils.frontNotifyMer(traderecord, gateway,request,response);
			
			if(StringUtils.isBlank(traderecord.getTrPageReturnurl())){
				return "common/paySuccess";
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return "tradeManage/sendtomer";
	}
	
	

	/**
	 * 兴业银行支付服务器通知接口
	 */
	@RequestMapping(value="/backNotify")
	public void backNotify(HttpServletRequest request, HttpServletResponse response,Model model,RedirectAttributes redirectAttributes){
		BankReturnBean bankReturnBean = new BankReturnBean();
		String returnUrl = "";
		try {
			
			//1 处理网关通知结果
			OperateResult operateResult = citicService.backNotifyPayment(request,response,bankReturnBean);
			
			if(!operateResult.isSuccess()){
				logger.info(operateResult.getMessage());
				return;
			}
			
			//订单信息获取
			
			Traderecord traderecord = traderecordService.getTraderecord(bankReturnBean.getResponseTrdeNo());
			
			if(traderecord==null){
				logger.info("不存在该订单");
				return;
			}
			
			traderecord.setTrBankorderno(bankReturnBean.getResponseBankNo());
			traderecord.setTrBankdatetime(new Date());
			traderecord.setTrBanktradetime(bankReturnBean.getResponseTradeTime());
			traderecord.setTrQueryno(bankReturnBean.getResponseBankNo());
			traderecord.setTrBatchno(bankReturnBean.getResponseBatchNo());
			traderecord.setTrBankreturncode(bankReturnBean.getResponseCode());
			traderecord.setTrBankinfo(bankReturnBean.getResponseInfo());
			
			traderecord.setTrBankcurrency(traderecord.getTrCurrency());
			if(bankReturnBean.getResponseAmount() !=null){
				traderecord.setTrBankamout(new BigDecimal(bankReturnBean.getResponseAmount()));
			}else{
				traderecord.setTrBankamout(traderecord.getTrAmount());
			}
			if(ConstantsBean.TRADE_STATUS_SUCCESS==Integer.valueOf(bankReturnBean.getResponseStatus())){
				traderecord.setTrStatus(1);
			}else if(ConstantsBean.TRADE_STATUS_FAIL==Integer.valueOf(bankReturnBean.getResponseStatus())){
				traderecord.setTrStatus(0);
			}else{
				traderecord.setTrStatus(-2);
			}
			
			operateResult = traderecordService.saveOrUpdateTraderecord(traderecord);
			
			if(!operateResult.isSuccess()){
				logger.info(operateResult.getMessage());
				return;
			}
			
			Gateway gateway = gatewayService.getGateway(String.valueOf(traderecord.getTrMerNo()), String.valueOf(traderecord.getTrGwNo()));
			
			if(gateway==null){
				logger.info("商户信息错误！");
				return;
			}
			

			InterfaceUtils.backNotifyMer(traderecord, gateway);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}
