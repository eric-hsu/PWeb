package com.jinxin.web.paygateway.offline.debitcard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.jinxin.common.utils.SpringContextUtil;
import com.jinxin.common.utils.StaticMapUtil;
import com.jinxin.common.valueobj.GatewayResult;
import com.jinxin.common.web.BaseContronller;
import com.jinxin.model.Card;
import com.jinxin.model.persistence.Traderecord;
import com.jinxin.service.common.TraderecordServiceI;
import com.jinxin.service.paygateway.offline.OfflinePayGatewayProxy;

/**
 * 借记卡支付Controller
 * @author ERICHU
 * @version 2014-06-24
 */
@Controller
@RequestMapping(value = "/debitcardController")
@Scope("prototype")
public class DebitcardController extends BaseContronller{

	private static final Logger logger = Logger.getLogger(DebitcardController.class);
	@Autowired
	private TraderecordServiceI traderecordService;
	
	@RequestMapping(value="/quickPay")
	public String quickPay(@ModelAttribute("card")Card card,HttpServletRequest request, HttpServletResponse response,Model model,RedirectAttributes redirectAttributes){
		String trMerOrderno = request.getParameter("trMerOrderno");
		String trNo = request.getParameter("trNo");
		String trAmount = request.getParameter("trAmount");
		logger.info("开始快捷支付,订单号："+trMerOrderno+",流水号："+trNo+",订单金额："+trAmount+",支付银行："+card.getBankCode()+",支付卡号："+card.getAccountNo());
		//1,卡信息校验

		//2，查询出订单信息
		Traderecord traderecord = traderecordService.getTraderecord(trNo);
		
		
		//3，相应service接口配置获取
		String operatemode = StaticMapUtil.getOfflineDebitcardServiceMap().get("opm"+card.getBankCode());
		if(operatemode==null){
			model.addAttribute("message", "暂时不支持该支付方式！");
			return "/common/sysError";
		}
		//4,提交支付
		OfflinePayGatewayProxy proxy = (OfflinePayGatewayProxy) SpringContextUtil.getBean(operatemode);
		if(proxy==null){
			model.addAttribute("message", "暂时不支持该支付方式！");
			return "/common/sysError";
		}
		GatewayResult gatewayResult = proxy.cardPay(traderecord, card);
		//5,支付结果处理
		if(!gatewayResult.isSuccess()){
			model.addAttribute("message", "网关支付失败，原因："+gatewayResult.getResult());
			return "/common/sysError";
		}
		//6,信息展示
		model.addAttribute("traderecord", traderecord);
		model.addAttribute("card", card);
		return "/paygateway/offline/creditcard/complete";
	}
}
