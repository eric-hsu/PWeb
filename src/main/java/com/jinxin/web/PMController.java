/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.jinxin.web;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.jinxin.common.utils.CommonUtil;
import com.jinxin.common.utils.InterfaceManager;
import com.jinxin.common.utils.InterfaceUtils;
import com.jinxin.common.utils.StaticMapUtil;
import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.common.web.BaseContronller;
import com.jinxin.model.BankConfig;
import com.jinxin.model.ConstantsBean;
import com.jinxin.model.persistence.Channel;
import com.jinxin.model.persistence.Gateway;
import com.jinxin.model.persistence.InformalTraderecord;
import com.jinxin.model.persistence.MerChannel;
import com.jinxin.model.persistence.MerRate;
import com.jinxin.model.persistence.Merchant;
import com.jinxin.model.persistence.Rate;
import com.jinxin.model.persistence.SysSysset;
import com.jinxin.model.persistence.TBankTag;
import com.jinxin.model.persistence.Traderecord;
import com.jinxin.service.common.AgentMerServiceI;
import com.jinxin.service.common.BankConfigServiceI;
import com.jinxin.service.common.ChannelServiceI;
import com.jinxin.service.common.CommonServiceI;
import com.jinxin.service.common.GatewayServiceI;
import com.jinxin.service.common.InformalTraderecordServiceI;
import com.jinxin.service.common.MerChannelServiceI;
import com.jinxin.service.common.MerRateServiceI;
import com.jinxin.service.common.MerRiskElementServiceI;
import com.jinxin.service.common.MerTranElementServiceI;
import com.jinxin.service.common.MerchantServiceI;
import com.jinxin.service.common.RateServiceI;
import com.jinxin.service.common.SysSyssetServiceI;
import com.jinxin.service.common.TraderecordServiceI;
import com.jinxin.service.common.TranTraderecordServiceI;
import com.jinxin.service.common.UnNormalProcessServiceI;

/**
 * 主题Controller
 * @author ERICHU
 * @version 2014-06-24
 */
@Controller
@RequestMapping(value = "/payment")
@Scope("prototype")
public class PMController extends BaseContronller{

	private static final Logger logger = Logger.getLogger(PMController.class);
	
	@Autowired
	private UnNormalProcessServiceI unNormalProcessService;
	
	@Autowired
	private TraderecordServiceI traderecordService;
	
	@Autowired
	private MerChannelServiceI merChannelService;
	
	@Autowired
	private AgentMerServiceI agentMerService;
	
	@Autowired
	private TranTraderecordServiceI tranTraderecordService;
	
	@Autowired
	private MerchantServiceI merchantService;
	
	@Autowired
	private RateServiceI rateService;
	
	@Autowired
	private GatewayServiceI gatewayService;
	
	@Autowired
	private ChannelServiceI channelService;

	@Autowired
	private CommonServiceI commonService;
	
	@Autowired
	private MerRateServiceI merRateService;
	
	@Autowired
	private MerRiskElementServiceI merRiskElementService;
	
	@Autowired
	private MerTranElementServiceI merTranElementService;
	
	@Autowired
	private InformalTraderecordServiceI informalTraderecordService;

	@Autowired
	private BankConfigServiceI bankConfigService;
	
	@Autowired
	private  SysSyssetServiceI sysSyssetService;

	/**
	 * 1，业务订单网银支付三方支付接口入口()。
	 * @param request
	 * @param response
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value="/orderRequest")
	public String orderRequest(HttpServletRequest request, HttpServletResponse response,Model model,RedirectAttributes redirectAttributes){
		try{
			// 1、获取本机服务请求IP地址
			logger.info("订单请求********************商户订单来源地址:"+request.getRequestURL().toString()+",客户下单ip："+CommonUtil.getIpAddr(request)+"*******************");
			this.innitSysSysset();
			InterfaceManager im = InterfaceManager.getInstance();
			im.setTraderecordService(traderecordService);
			im.setUnNormalProcessService(unNormalProcessService);
			im.setInformalTraderecordService(informalTraderecordService);
			im.setAgentMerService(agentMerService);
			im.setMerChannelService(merChannelService);
			im.setGatewayService(gatewayService);
			im.setChannelService(channelService);
			im.setMerRateService(merRateService);
			im.setMerRiskElementService(merRiskElementService);
			im.setMerTranElementService(merTranElementService);
			im.setCommonService(commonService);
			im.setRateService(rateService);
			im.setTranTraderecordService(tranTraderecordService);
			im.setMerchantService(merchantService);
			//1,订单请求处理
			OperateResult operateResult = im.create(request);
			logger.info("订单处理结果:"+operateResult.isSuccess()+",返回码："+operateResult.getMessage()+",流水号："+operateResult.getTrNo());

			if(!operateResult.isSuccess()){
				model.addAttribute("message", operateResult.getMessage());
				return "/common/mererror";
			}
			//2,商户信息获取
			Gateway gateway = gatewayService.getGateway(request.getParameter("merNo"), request.getParameter("gatewayNo"));
			Merchant merchant = merchantService.getMerchant(request.getParameter("merNo"));
			//3.1,测试商户测试订单获取
			if(ConstantsBean.STATUS_TEST ==  gateway.getGwStatus()){
				logger.info("跳转到测试订单支付成功完成页面...");
				InformalTraderecord informalTraderecord = informalTraderecordService.getInformalTraderecord(operateResult.getTrNo());
				if(informalTraderecord==null){
					model.addAttribute("message", "I0071");
					return "/common/mererror";
				}
				model.addAttribute("informalTraderecord", informalTraderecord);
				return "/tradeManage/testComplete";
			}
			
			//3.2,正式商户正式订单获取
			Traderecord traderecord  = traderecordService.getTraderecord(operateResult.getTrNo());
			if(traderecord==null){
				model.addAttribute("message", "I0071");
				return "/common/mererror";
			}
			
			model.addAttribute("traderecord", traderecord);
			model.addAttribute("merchant", merchant);
			
			String payType=request.getParameter("payType");
			String cardType=request.getParameter("cardType");
			String isquick=request.getParameter("isquick");
			String bankCode=request.getParameter("bankCode");
			
			//4,商户已选择支付方式处理
			if(!StringUtils.isBlank(bankCode)){
				
				//企业网银
				if("1".equals(payType)){
					String controllerE =bankCode+"EController";
					redirectAttributes.addFlashAttribute("traderecord", traderecord);
					return "redirect:/"+controllerE+"/orderRequest.do";
				}
				
				//个人网银支付
				if("0".equals(payType)){
					//快捷支付（0），网银支付（1）；
					if("0".equals(isquick)){
						if("1".equals(cardType)){
							logger.info("跳转到信用卡快捷支付信息填写页面...");
							return "/paygateway/offline/creditcard/creditcard";
						}else if("0".equals(cardType)){
							logger.info("跳转到借记卡快捷支付信息填写页面...");
							return "/paygateway/offline/debitcard/debitcard";
						}
					}
					
					if("1".equals(isquick)){
						Map<String, String> controllermap = StaticMapUtil.getOnlineControllerMap();
						String controller = controllermap.get("opm"+bankCode);
						if(controller==null){
							model.addAttribute("message", "系统提示：暂不支持该支付方式！");
							return "/common/mererror";
						}
						redirectAttributes.addFlashAttribute("traderecord", traderecord);
			            return "redirect:/"+controller+"/orderRequest.do";
					}
				}
			}
			
			//5,商户未选择支付方式处理，获取页面支付方式配置信息(支付卡配置，bank数据)
			List<BankConfig> list = bankConfigService.findBankConfig(request.getParameter("merNo"), request.getParameter("gatewayNo"));
			if(list.size() == 0 ){
				model.addAttribute("message", "C0001");
				logger.info("商户未配置支付渠道");
				return "/common/mererror";
			}
			
			StaticMapUtil.bankConfig(list, model);
		}catch(Exception e){
			logger.info(e.getMessage());
			e.printStackTrace();
			//addMessage(redirectAttributes, "系统异常，错误信息："+e.getMessage());
			//return "redirect:/payment/orderTest.do?repage";
			model.addAttribute("message", e.getMessage());
			return "/common/mererror";
		}
		logger.info("跳转到支付中心支付方式选择页面...");
		return "/tradeManage/paycenter";
	}
	
	/**
	 * 2，三方支付，选择支付方式后提交支付入口
	 * @param request
	 * @param response
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value="/orderPay")
	public String orderPay(HttpServletRequest request, HttpServletResponse response,Model model,RedirectAttributes redirectAttributes){
		logger.info("选择支付方式********************客户下单ip："+CommonUtil.getIpAddr(request)+"******************************");

		OperateResult operateResult = new OperateResult();
		//1,基础信息获取
		String chaType=request.getParameter("chaType");
		String bankcode=request.getParameter("bankcode");
		String cardType = request.getParameter("cardType");
		
		String trNo = request.getParameter("trNo");
		
		logger.info("流水号："+trNo+",支付类型："+chaType+",卡类型："+cardType+",支付银行编码："+bankcode+"");
		if(StringUtils.isBlank(bankcode) || StringUtils.isBlank(chaType) || StringUtils.isBlank(trNo)){
			model.addAttribute("message", "信息不合法！");
			logger.info("信息不合法！");
			return "/common/sysError";
		}
		//2,订单获取
		Traderecord traderecord = traderecordService.getTraderecord(trNo);
		if(traderecord==null){
			model.addAttribute("message", "不存在该订单记录，请重新下单支付！");
			logger.info("不存在该订单记录，请重新下单支付！！");
			return "/common/sysError";
		}
		if(traderecord.getTrStatus()==1){
			model.addAttribute("message", "订单已支付成功，不能重复支付！");
			logger.info("订单已支付成功，不能重复支付！");
			return "/common/sysError";
		}
		traderecord.setTrStatus(-1);
		traderecord.setTrPaystarttime(new Date());
		
		// 通道类型：0 个人网银支付 ， 1 企业网银支付 1 借记卡，2 信用卡 
		if("personalPay".equals(chaType)){
			chaType="0";
		}else if("quickPay".equals(chaType)){
			chaType="1";
			if("Creditcard".equals(cardType)){
				cardType="1";
			}else if("Debitcard".equals(cardType)){
				cardType="0";
			}else{
				model.addAttribute("message", "输入异常。");
				return "/common/sysError";
			}
		}else if("enterprisePay".equals(chaType)){
			chaType="2";
		}else{
			model.addAttribute("message", "输入异常。");
			return "/common/sysError";
		}
		
		Channel channel = channelService.getChannel(chaType,cardType,bankcode);
		
		if(channel==null){
			model.addAttribute("message", "通道异常，请更换使用其他付款方式支付");
			return "/common/sysError";
		}
		//判断通道是否生效
		if(channel.getChaEffectDate() !=null){
			boolean effect = channel.getChaEffectDate().before(new Date());
			if(!effect){
				model.addAttribute("message", "通道异常。");
				return "/common/sysError";
			}
		}
		
		MerRate merRate = merRateService.getMerRate(traderecord.getTrMerNo(),traderecord.getTrGwNo(),channel.getChaCode());
		traderecord.setTrTradeRate(merRate.getMrTradeRate());
		traderecord.setTrSppCurrency(merRate.getMrFeecurrency());
		traderecord.setTrSpp(merRate.getMrFeeamount());
		traderecord.setTrReseverRate(merRate.getMrReserverRate());
		traderecord.setTrRateValue(new BigDecimal(0.1));
		traderecord.setTrBankCode(channel.getBank().getBankCode());//
		traderecord.setTrChaCode(channel.getChaCode());
		traderecord.setTrBankSPPCurrency(channel.getChaFeecurrency());
		traderecord.setTrBankSPP(channel.getChaFeeamount());
		traderecord.setTrChaSettBank(channel.getChaSettlementBank());
		traderecord.setTrFEEFailMER(merRate.getMrFeeFail());
		traderecord.setTrFEESuccessMER(merRate.getMrFeeSuccess());
		traderecord.setTrFEESuccessAfterMER(merRate.getMrFeeSuccessAfter());
		traderecord.setTrIsBackMER(merRate.getMrIsBack());
		traderecord.setTrIsBackAfterMER(merRate.getMrIsBackAfter());
		traderecord.setTrFEEFailCHA(channel.getChaFeeFail().intValue());
		traderecord.setTrFEESuccessCHA(channel.getChaFeeSuccess().intValue());
		traderecord.setTrFEESuccessAfterCHA(channel.getChaFeeSuccessAfter().intValue());
		traderecord.setTrIsBackCHA(channel.getChaIsBack().intValue());
		traderecord.setTrIsBackAfterCHA(channel.getChaIsBackAfter().intValue());
		
		if(channel.getChaCurrency().indexOf(traderecord.getTrCurrency()) != -1){
			traderecord.setTrRateValue(new BigDecimal(1));
			traderecord.setTrBankcurrency(traderecord.getTrCurrency());
			//traderecord.setTrBankamout(traderecord.getTrAmount());
		}else{
			String []currencys = channel.getChaCurrency().split(",");
			for(String currency:currencys){
				//根据原始币种 目标币种获取汇率
				Rate rate = rateService.getRate(traderecord.getTrCurrency(), currency,"1");
				//如果有获取到汇率则立即返回
				if(rate != null){
					traderecord.setTrRateValue(rate.getRateValue());
					traderecord.setTrBankcurrency(currency);
					BigDecimal orderAmount = traderecord.getTrAmount();
					BigDecimal ratevalue = rate.getRateValue();
					//traderecord.setTrBankamout(orderAmount.multiply(ratevalue));
					break;
				}
			}
		}
			
		
		//4,更新交易记录
		operateResult = traderecordService.saveOrUpdateTraderecord(traderecord);
		if(!operateResult.isSuccess()){
			model.addAttribute("message", "系统异常，请联系管理员。");
			return "/common/sysError";
		}
		
		model.addAttribute("traderecord", traderecord);
		//企业网银处理方式
		if("2".equals(chaType)){
			String controllerE =bankcode+"EController";
			redirectAttributes.addFlashAttribute("traderecord", traderecord);
			return "redirect:/"+controllerE+"/orderRequest.do";
		}
		
		//个人网银处理方式
		if("0".equals(chaType)){
			redirectAttributes.addFlashAttribute("traderecord", traderecord);
			String controllerCI = bankcode+"Controller";
            return "redirect:/"+controllerCI+"/orderRequest.do";
			
		}
		//快捷支付
		if("1".equals(chaType)){
			if("0".equals(cardType)){
				return "/paygateway/offline/debitcard/debitcard";
			}else if("1".equals(cardType)){
				return "/paygateway/offline/creditcard/creditcard";
			}
		}
		return "/common/sysError";
	}
	
	
	/**
	 * 3，退款接口
	 * @param request
	 * @param response
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value="/refundRequest")
	public void refundRequest(HttpServletRequest request, HttpServletResponse response,Model model,RedirectAttributes redirectAttributes){
		logger.info("商户退款请求,商户号："+request.getParameter("merNo")+",网关接入号："+request.getParameter("gatewayNo")+",订单号："+request.getParameter("orderNo")+",币种："+request.getParameter("currency")+",退款金额："+request.getParameter("refundAmount"));
		InterfaceManager im = InterfaceManager.getInstance();
		im.setTraderecordService(traderecordService);
		im.setUnNormalProcessService(unNormalProcessService);
		im.setInformalTraderecordService(informalTraderecordService);
		im.setCommonService(commonService);
		im.setGatewayService(gatewayService);
		im.setMerchantService(merchantService);
		im.setChannelService(channelService);
		//退款管理处理
		OperateResult operateResult = im.refund(request);
		
	    String responseXml="";

	    //返回退款处理结果
	    if(operateResult.isSuccess()){
	    		Gateway gateway = gatewayService.getGateway(request.getParameter("merNo"),request.getParameter("gatewayNo"));
	    		//交易记录查询
	    		String  orderNo = request.getParameter("orderNo");
				Traderecord traderecord = traderecordService.getTraderecordByMerOrderNo(request.getParameter("merNo"), request.getParameter("gatewayNo"),orderNo);
	    		responseXml = InterfaceUtils.createReturnMerXml(traderecord,operateResult,gateway, ConstantsBean.NOTIFY_TYPE_REFUND);
		}else{
			responseXml = InterfaceUtils.createReturnMerXml(null,operateResult,null, ConstantsBean.NOTIFY_TYPE_REFUND);
		}
	    
		try{
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
		    logger.info("退款结果返回，refund responseXml==" + responseXml);
            out.print(responseXml);
            out.flush();
            out.close();
			
		}catch(Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * 4，查询同步接口
	 * @param request
	 * @param response
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value="/queryRequest")
	public void queryRequest(HttpServletRequest request, HttpServletResponse response,Model model,RedirectAttributes redirectAttributes){
		logger.info("商户在线查询,商户号："+request.getParameter("merNo")+",网关接入号："+request.getParameter("gatewayNo")+"流水号："+request.getParameter("tradeNo"));
		InterfaceManager im = InterfaceManager.getInstance();
		im.setTraderecordService(traderecordService);
		im.setUnNormalProcessService(unNormalProcessService);
		im.setInformalTraderecordService(informalTraderecordService);
		im.setCommonService(commonService);
		im.setGatewayService(gatewayService);
		im.setMerchantService(merchantService);
		im.setChannelService(channelService);
		//查询管理处理
		OperateResult operateResult = im.query(request);
		
		logger.info("operateResult success:" + operateResult.isSuccess()+",message:"+operateResult.getMessage());
	
		String responseXml="";
		if(operateResult.isSuccess()){
			
			Gateway gateway = gatewayService.getGateway(request.getParameter("merNo"),request.getParameter("gatewayNo"));
			if(ConstantsBean.STATUS_TEST ==  gateway.getGwStatus()){
				InformalTraderecord traderecord = informalTraderecordService.getformalTraderecordByMerOrderNo(request.getParameter("merNo"),request.getParameter("gatewayNo"),request.getParameter("orderNo"));
				responseXml = InterfaceUtils.createTestReturnMerXml(traderecord,operateResult,gateway, ConstantsBean.NOTIFY_TYPE_QUERY);
			}else{
				Traderecord traderecord = traderecordService.getTraderecordByMerOrderNo(request.getParameter("merNo"),request.getParameter("gatewayNo"),request.getParameter("orderNo"));
				responseXml = InterfaceUtils.createReturnMerXml(traderecord,operateResult,gateway,ConstantsBean.NOTIFY_TYPE_QUERY);
			}
		}else{
			responseXml = InterfaceUtils.createReturnMerXml(null,operateResult, null,ConstantsBean.NOTIFY_TYPE_QUERY);
		}
		
		try{
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
		    logger.info("查询结果返回，query responseXml==" + responseXml);
            out.print(responseXml);
            out.flush();
            out.close();
			
		}catch(Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * 5，同步接口
	 * @param request
	 * @param response
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value="/synOrderBankStatus")
	public void synOrderBankStatus(HttpServletRequest request, HttpServletResponse response,Model model,RedirectAttributes redirectAttributes){
		logger.info("系统同步,商户号："+request.getParameter("merNo")+",网关接入号："+request.getParameter("gatewayNo")+"流水号："+request.getParameter("tradeNo"));
		InterfaceManager im = InterfaceManager.getInstance();
		im.setTraderecordService(traderecordService);
		im.setUnNormalProcessService(unNormalProcessService);
		im.setInformalTraderecordService(informalTraderecordService);
		im.setCommonService(commonService);
		im.setGatewayService(gatewayService);
		im.setMerchantService(merchantService);
		im.setChannelService(channelService);
		//查询管理处理
		OperateResult operateResult = im.synOrderBankStatus(request);
		
		logger.info("operateResult success:" + operateResult.isSuccess()+",message:"+operateResult.getMessage());
	
		String responseXml="";
		if(operateResult.isSuccess()){
			responseXml="s";
		}else{
			responseXml = "原因("+operateResult.getMessage()+")";
		}
		try{
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
		    logger.info("查询结果返回，query responseXml==" + responseXml);
            out.print(responseXml);
            out.flush();
            out.close();
			
		}catch(Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * 6，对账接口
	 * @param request
	 * @param response
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value="/querybatchRequest")
	public void querybatchRequest(HttpServletRequest request, HttpServletResponse response,Model model,RedirectAttributes redirectAttributes){
		logger.info("商户在线查询,商户号："+request.getParameter("merNo")+",网关接入号："+request.getParameter("gatewayNo")+"，查询对账日期："+request.getParameter("tradeDate"));
		InterfaceManager im = InterfaceManager.getInstance();
		im.setTraderecordService(traderecordService);
		im.setUnNormalProcessService(unNormalProcessService);
		im.setInformalTraderecordService(informalTraderecordService);
		im.setCommonService(commonService);
		im.setGatewayService(gatewayService);
		im.setMerchantService(merchantService);
		im.setChannelService(channelService);
		//查询管理处理
		OperateResult operateResult = im.querybatch(request);
		
		logger.info("operateResult success:" + operateResult.isSuccess()+",message:"+operateResult.getMessage());
	
		String responseXml="";
		
			
		Gateway gateway = gatewayService.getGateway(request.getParameter("merNo"),request.getParameter("gatewayNo"));
		if(ConstantsBean.STATUS_TEST ==  gateway.getGwStatus()){
			List<InformalTraderecord> informalTraderecordList=null;
			if(operateResult.isSuccess()){
				informalTraderecordList = informalTraderecordService.getformalTraderecordByMerTradeDate(request.getParameter("merNo"),request.getParameter("gatewayNo"),request.getParameter("tradeDate"));
			}
			responseXml = InterfaceUtils.createTestBatchReturnMerXml(informalTraderecordList,operateResult,gateway, ConstantsBean.NOTIFY_TYPE_QUERY);
		}else{
			List<Traderecord> traderecordList =null;
			if(operateResult.isSuccess()){
				traderecordList = traderecordService.getTraderecordByMerTradeDate(request.getParameter("merNo"),request.getParameter("gatewayNo"),request.getParameter("tradeDate"));
			}
			responseXml = InterfaceUtils.createReturnBatchMerXml(traderecordList,operateResult,gateway,ConstantsBean.NOTIFY_TYPE_QUERY);
		}
		
		try{
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
		    logger.info("对账查询结果返回，query responseXml==" + responseXml);
            out.print(responseXml);
            out.flush();
            out.close();
			                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
		}catch(Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 3，退款接口
	 * @param request
	 * @param response
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value="/backNotifyResult")
	public void backNotifyResult(HttpServletRequest request, HttpServletResponse response,Model model,RedirectAttributes redirectAttributes){
		String param = request.getParameter("xmlStr");
		try {
			param = URLDecoder.decode(param, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		logger.info("商户后台接收服务器通知结果,xmlStr="+request.getParameter("xmlStr"));
	}
	
	/**
	 * 4，测试商户结果通知接口
	 * @param request
	 * @param response
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value="/testNotifyMer")
	public String testNotifyMer(HttpServletRequest request, HttpServletResponse response,Model model,RedirectAttributes redirectAttributes){
		InformalTraderecord informalTraderecord = informalTraderecordService.getInformalTraderecord(request.getParameter("ttrNo"));
		if(informalTraderecord == null){
			model.addAttribute("message", "订单不存在！");
			return "/common/sysError";
		}
	
		Gateway gateway = gatewayService.getGateway(informalTraderecord.getTtrMerNo(), informalTraderecord.getTtrGwNo());
		
		if(gateway==null){
			model.addAttribute("message", "商户信息错误！");
			return "/common/sysError";
		}
		if(informalTraderecord.getTtrReturnurl()==null){
			model.addAttribute("message", "服务器通知地址异常！");
			return "/common/sysError";
		}
		OperateResult operateResult = InterfaceUtils.testBackNotifyMer(informalTraderecord, gateway);

		if(!operateResult.isSuccess()){
			model.addAttribute("message", "服务器通知失败！");
			return "/common/sysError";
		}else{
			if(informalTraderecord.getTtrPageReturnurl()==null){
				model.addAttribute("message", "页面通知地址异常！");
				return "/common/sysError";
			}
			InterfaceUtils.testFrontNotifyMer(informalTraderecord, gateway,request,response);
		}
		
		return "tradeManage/sendtomer";
	}
	
	/**
	 * 3，退款接口
	 * @param request
	 * @param response
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value="/innitParams")
	public void innitParams(HttpServletRequest request, HttpServletResponse response,Model model,RedirectAttributes redirectAttributes){
		List<SysSysset> paramList =new ArrayList<SysSysset>();
		paramList = sysSyssetService.getAllSysSysset();
		StaticMapUtil.paramMap.clear();
		for(SysSysset sysSysset:paramList){
			StaticMapUtil.paramMap.put(sysSysset.getSsParaName(),sysSysset.getSsParaValue());
		}
	}
	
	public void innitSysSysset(){
		List<SysSysset> paramList =new ArrayList<SysSysset>();
		if(StaticMapUtil.paramMap.isEmpty()){
			paramList = sysSyssetService.getAllSysSysset();
			StaticMapUtil.paramMap.clear();
			for(SysSysset sysSysset:paramList){
				StaticMapUtil.paramMap.put(sysSysset.getSsParaName(),sysSysset.getSsParaValue());
			}
		}
	}
}
