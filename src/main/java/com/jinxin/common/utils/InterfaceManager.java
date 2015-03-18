package com.jinxin.common.utils;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.model.BankReturnBean;
import com.jinxin.model.ConstantsBean;
import com.jinxin.model.persistence.AgentMer;
import com.jinxin.model.persistence.ChaCardtype;
import com.jinxin.model.persistence.Channel;
import com.jinxin.model.persistence.Gateway;
import com.jinxin.model.persistence.InformalTraderecord;
import com.jinxin.model.persistence.MerChannel;
import com.jinxin.model.persistence.MerRate;
import com.jinxin.model.persistence.MerTranElement;
import com.jinxin.model.persistence.Merchant;
import com.jinxin.model.persistence.Rate;
import com.jinxin.model.persistence.Traderecord;
import com.jinxin.model.persistence.TranTraderecord;
import com.jinxin.model.persistence.UnNormalProcess;
import com.jinxin.service.common.AgentMerServiceI;
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
import com.jinxin.service.common.TraderecordServiceI;
import com.jinxin.service.common.TranTraderecordServiceI;
import com.jinxin.service.common.UnNormalProcessServiceI;
import com.jinxin.service.paygateway.PayGatewayProxy;

public class InterfaceManager {
	
	private static final Logger logger = Logger.getLogger(InterfaceManager.class);
	
	
	private UnNormalProcessServiceI unNormalProcessService;
	
	private TraderecordServiceI traderecordService;
	
	private InformalTraderecordServiceI informalTraderecordService;
	
	private CommonServiceI commonService;
	
	private MerChannelServiceI merChannelService;
		
	private AgentMerServiceI agentMerService;
	
	private RateServiceI rateService;
	
	private ChannelServiceI channelService;
	
	private MerRateServiceI merRateService;
	
	private GatewayServiceI gatewayService;
	
	private MerRiskElementServiceI merRiskElementService;
	
	private MerTranElementServiceI merTranElementService;
	
	private TranTraderecordServiceI tranTraderecordService;
	
	private MerchantServiceI merchantService;
	
	
	private static InterfaceManager im = new InterfaceManager();

    private InterfaceManager() {
    }

    public static InterfaceManager getInstance() {
        if (null == im) {
            im = new InterfaceManager();
        }
        return im;
    }
    
    public OperateResult create(HttpServletRequest request) {
    	
    	OperateResult operateResult = new OperateResult();
    	operateResult.setSuccess(true);
    	try{
    		/*******************************************1, 订单请求参数校验******************************************************/
    		
    		operateResult  = CheckDataUtil.verifyPayRequestData(request);
			if(!operateResult.isSuccess()){
				return operateResult;
			}
			
			/*******************************************2, 商户,接入号校验*****************************************************/
			
			Gateway gateway = gatewayService.getGateway(request.getParameter("merNo"), request.getParameter("gatewayNo"));
			Merchant merchant = merchantService.getMerchant(request.getParameter("merNo"));
			operateResult = CheckDataUtil.verifyGateway(gateway,merchant);
			if(!operateResult.isSuccess()){
				return operateResult;
			}
			
			/*******************************************3, 签名验证*****************************************************/
			
			operateResult = CheckDataUtil.verifyPaySignInfo(request, gateway);
			if(!operateResult.isSuccess()){
				return operateResult;
			}
			
			if(!"CNY".equals(request.getParameter("currency"))){
//				Rate rate = rateService.getRate(request.getParameter("currency"), "CNY","1");
//				if(rate == null){
					operateResult.setSuccess(false);
					operateResult.setMessage("I0023");
					return operateResult;
//				}
			}
			String chaType=request.getParameter("chaType");
			String bankCode=request.getParameter("bankCode");
			String cardType=request.getParameter("cardType");
			/*******************************************4, 测试的网关接入号只能接入测试接口********************************/
			if(ConstantsBean.STATUS_TEST ==  gateway.getGwStatus()){
				InformalTraderecord informalTraderecord = informalTraderecordService.getformalTraderecordByMerOrderNo(request.getParameter("merNo"),request.getParameter("gatewayNo"),request.getParameter("orderNo"));
				if(informalTraderecord!=null){
					operateResult.setSuccess(false);
					operateResult.setMessage("I0061");
					operateResult.setTrNo(informalTraderecord.getTtrNo());
					return operateResult;
				}else{
					informalTraderecord = new InformalTraderecord();
					informalTraderecord.setTtrNo(CommonUtil.getTradeNo());
					informalTraderecord.setTtrDatetime(new Date());
					informalTraderecord.setTtrMerNo(request.getParameter("merNo"));
					informalTraderecord.setTtrGwNo(request.getParameter("gatewayNo"));
					informalTraderecord.setTtrMerOrderno(request.getParameter("orderNo"));
					informalTraderecord.setTtrCurrency(request.getParameter("currency"));
					informalTraderecord.setTtrAmount(request.getParameter("amount"));
					informalTraderecord.setTtrStatus(1);
					List<MerChannel> merChannelList = new ArrayList<MerChannel>();
					merChannelList = merChannelService.getMerChannel(request.getParameter("merNo"),request.getParameter("gatewayNo"));
					
					if(!StringUtils.isBlank(chaType)){
						
						Channel channel = channelService.getChannel(chaType,cardType,bankCode);
						if(channel==null){
							operateResult.setSuccess(false);
							operateResult.setMessage("I0084");
							return operateResult;
						}
						MerRate merRate = merRateService.getMerRate(Long.valueOf(request.getParameter("merNo")),Long.valueOf(request.getParameter("gatewayNo")),channel.getChaCode());
						informalTraderecord.setTtrMrTradeRate(String.valueOf(merRate.getMrTradeRate()));
						informalTraderecord.setTtrMrReseverRate(String.valueOf(merRate.getMrReserverRate()));
						informalTraderecord.setTtrBwBankcurrency(channel.getChaFeecurrency());
						informalTraderecord.setTtrBwBankamout(String.valueOf(channel.getChaFeeamount()));
						informalTraderecord.setTtrBankcode(channel.getBank().getBankCode());
						informalTraderecord.setTtrChaCode(String.valueOf(channel.getChaCode()));
					}

					informalTraderecord.setTtrRateValue("0");
					informalTraderecord.setTtrReturnurl(request.getParameter("backNotifyUrl"));
					informalTraderecord.setTtrPageReturnurl(request.getParameter("frontNotifyUrl"));
					logger.info(informalTraderecord.toString());
					operateResult = informalTraderecordService.saveInformalTraderecord(informalTraderecord);
					operateResult.setTrNo(informalTraderecord.getTtrNo());
					
					return operateResult;
				}
				
			}
			
			/*******************************************5, 正式的商户网关接入处理接口***************************************/
			//获取绑定的通道的信息以及扣率信息
			List<MerChannel> merChannelList =  merChannelService.getMerChannel(request.getParameter("merNo"),request.getParameter("gatewayNo"));
			if(merChannelList.size()==0){
				operateResult.setSuccess(false);
				operateResult.setMessage("C0001");
				return operateResult;
			}
			
			Traderecord traderecord  = traderecordService.getTraderecordByMerOrderNo(request.getParameter("merNo"),request.getParameter("gatewayNo"),request.getParameter("orderNo"));
			if(traderecord !=null){
				if(traderecord.getTrStatus() == 1){
					operateResult.setSuccess(false);
					operateResult.setMessage("I0061");
					return operateResult;
				}
				
				if(traderecord.getTrAmount() != (new BigDecimal(request.getParameter("amount")))){
					traderecord.setTrAmount(new BigDecimal(request.getParameter("amount")));
				}
				
				if(!StringUtils.isBlank(chaType)){
					
					Channel channel = channelService.getChannel(chaType,cardType,bankCode);
					if(channel==null){
						operateResult.setSuccess(false);
						operateResult.setMessage("I0084");
						return operateResult;
					}
					
					//判断通道是否生效
					if(channel.getChaEffectDate() !=null){
						boolean effect = channel.getChaEffectDate().before(new Date());
						if(!effect){
							operateResult.setSuccess(false);
							operateResult.setMessage("I0084");
							return operateResult;
						};
					}
					
					boolean flag = false;
					for(MerChannel merChannel:merChannelList){
						if(merChannel.getMcChaCode()==channel.getChaCode()){
							flag=true;
							break;
						}
					}
					
					if(!flag){
						operateResult.setSuccess(false);
			    		operateResult.setMessage("I0084");
						return operateResult;
					}
						
					MerRate merRate = merRateService.getMerRate(Long.valueOf(request.getParameter("merNo")),Long.valueOf(request.getParameter("gatewayNo")),channel.getChaCode());
					traderecord.setTrTradeRate(merRate.getMrTradeRate());
					traderecord.setTrSppCurrency(merRate.getMrFeecurrency());
					traderecord.setTrSpp(merRate.getMrFeeamount());
					
					traderecord.setTrReseverRate(merRate.getMrReserverRate());
					traderecord.setTrRateValue(new BigDecimal(0.1));
					traderecord.setTrBankCode(channel.getBank().getBankCode());
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

					traderecord.setTrStatus(-1);
					traderecord.setTrPaystarttime(new Date());
				}
				traderecord.setTrIp(CommonUtil.getIpAddr(request));
				operateResult = traderecordService.saveOrUpdateTraderecord(traderecord);
				operateResult.setTrNo(traderecord.getTrNo());
				return operateResult;
			}else{
		    	traderecord = new Traderecord();
				traderecord.setTrNo(CommonUtil.getTradeNo());  //流水号，自动生成
				traderecord.setTrMerOrderno(request.getParameter("orderNo")); //商户订单号
				traderecord.setTrMerNo(Long.valueOf(request.getParameter("merNo")));  //商户号
				traderecord.setTrGwNo(Long.valueOf(request.getParameter("gatewayNo")));  //网关接入号
				traderecord.setTrCurrency(request.getParameter("currency"));  //商户支付币种
				traderecord.setTrAmount(new BigDecimal(request.getParameter("amount")));  //商户支付金额
				traderecord.setTrStatus(-2);
				traderecord.setTrRateValue(new BigDecimal(1));
				traderecord.setTrBankcurrency(request.getParameter("currency"));
				traderecord.setTrBankamout(new BigDecimal(0));
				
				if(!StringUtils.isBlank(chaType)){
					
					Channel channel = channelService.getChannel(chaType,cardType,bankCode);
					if(channel==null){
						operateResult.setSuccess(false);
						operateResult.setMessage("I0084");
						return operateResult;
					}
					
					//判断通道是否生效
					if(channel.getChaEffectDate() !=null){
						boolean effect = channel.getChaEffectDate().before(new Date());
						if(!effect){
							operateResult.setSuccess(false);
							operateResult.setMessage("I0084");
							return operateResult;
						};
					}
					
					boolean flag = false;
					for(MerChannel merChannel:merChannelList){
						if(merChannel.getMcChaCode()==channel.getChaCode()){
							flag=true;
							break;
						}
					}
					
					if(!flag){
						operateResult.setSuccess(false);
			    		operateResult.setMessage("I0084");
						return operateResult;
					}
				
						MerRate merRate = merRateService.getMerRate(Long.valueOf(request.getParameter("merNo")),Long.valueOf(request.getParameter("gatewayNo")),channel.getChaCode());
						traderecord.setTrTradeRate(merRate.getMrTradeRate());
						traderecord.setTrSppCurrency(merRate.getMrFeecurrency());
						traderecord.setTrSpp(merRate.getMrFeeamount());
						traderecord.setTrReseverRate(merRate.getMrReserverRate());
						traderecord.setTrBankCode(channel.getBank().getBankCode());
						traderecord.setTrChaCode(channel.getChaCode());
						traderecord.setTrStatus(-1);
						if(channel.getChaCurrency().indexOf(request.getParameter("currency")) != -1){
							traderecord.setTrRateValue(new BigDecimal(1));
							traderecord.setTrBankcurrency(request.getParameter("currency"));
							//traderecord.setTrBankamout(new BigDecimal(request.getParameter("amount")));
						}else{
							boolean flagRate = false;
							String []currencys = channel.getChaCurrency().split(",");
							for(String currency:currencys){
								//根据原始币种 目标币种获取汇率
								Rate rate = rateService.getRate(request.getParameter("currency"), currency,"1");
								//如果有获取到汇率则立即返回
								if(rate != null){
									traderecord.setTrRateValue(rate.getRateValue());
									traderecord.setTrBankcurrency(currency);
									BigDecimal orderAmount = new BigDecimal(request.getParameter("amount"));
									BigDecimal ratevalue = rate.getRateValue();
									//traderecord.setTrBankamout(orderAmount.multiply(ratevalue));
									flagRate = true;
									break;
								}
							}
							if(!flagRate){
								operateResult.setSuccess(false);
								operateResult.setMessage("C0005");//币种未设置汇率
								return operateResult;
							}
						}

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
						
						traderecord.setTrPaystarttime(new Date());
					
				}
				//根据网关接入号获取代理商信息
				AgentMer agentMer = agentMerService.getAgentMer(request.getParameter("merNo"),request.getParameter("gatewayNo"));
				if(agentMer!=null){
					traderecord.setTrAgentNo(agentMer.getAgent().getAgentNo());
					traderecord.setTrAgentRate(agentMer.getAmRate());
					traderecord.setTrAgentSPPCurrency(agentMer.getAmFeeCurrency());
					traderecord.setTrAgentSPP(agentMer.getAmFee());
					traderecord.setTrFEEFailAgent(agentMer.getAmFeeFail());
					traderecord.setTrFEESuccessAgent(agentMer.getAmFeeSuccess());
					traderecord.setTrFEESuccessAfterAgent(agentMer.getAmFeeSuccessAfter());
					traderecord.setTrIsBackAgent(agentMer.getAmIsBack());
					traderecord.setTrIsBackAfterAgent(agentMer.getAmIsBackAfter());
				}
				
				

				traderecord.setTrReturnurl(request.getParameter("backNotifyUrl"));
				traderecord.setTrPageReturnurl(request.getParameter("frontNotifyUrl"));
				traderecord.setTrWebsite(request.getHeader("referer"));//来源网址增加rsa值 paramBean.getInterfaceParamBean().getWebSite()+","+paramBean.getTradeInfoBean().getRsaValue()
				traderecord.setTrSubmiturl(request.getRequestURL().toString());//paramBean.getSubmitUrl()
				traderecord.setTrIp(CommonUtil.getIpAddr(request));
				traderecord.setTrInfType(3);
				traderecord.setTrRemark(request.getParameter("remark"));
				traderecord.setTrDatetime(new Date());
		
				/******************************************************************************************/
				//根据商户号、网关接入号获取绑定的交易监控
				//保存交易监控
				List<MerTranElement> tranList = new ArrayList<MerTranElement>();
				tranList = merTranElementService.getMerTranElement(request.getParameter("merNo"),request.getParameter("gatewayNo"));
				if(tranList==null){
					tranList = merTranElementService.getMerTranElement(request.getParameter("merNo"),"0");
					if(tranList==null){
						tranList = merTranElementService.getMerTranElement("0","0");
					}
				}
				operateResult = merTranElementService.riskControl(tranList,traderecord,ConstantsBean.TRAN_POSITION_1);
				if(!operateResult.isSuccess()){
					operateResult.setMessage("系统异常，请稍后再试！");
					return operateResult;
				}
				if(Integer.parseInt(operateResult.getMessage())>0){
					TranTraderecord tranTraderecord = new TranTraderecord();
					tranTraderecord.setTtrNo(traderecord.getTrNo());
					tranTraderecord.setTtrMerNo(String.valueOf(traderecord.getTrMerNo()));
					tranTraderecord.setTtrGwNo(String.valueOf(traderecord.getTrGwNo()));
					tranTraderecord.setTtrMerOrderno(traderecord.getTrMerOrderno());
					tranTraderecord.setTtrCurrency(traderecord.getTrCurrency());
					tranTraderecord.setTtrAmount(String.valueOf(traderecord.getTrAmount()));
					tranTraderecord.setTtrStatus(1);
					tranTraderecord.setTtrReturnurl(traderecord.getTrReturnurl());
					tranTraderecord.setTtrSubmiturl(traderecord.getTrSubmiturl());
					tranTraderecord.setTtrIpaddress(traderecord.getTrIp());
					tranTraderecord.setTtrLevel(operateResult.getMessage());
					tranTraderecord.setWebsite(traderecord.getTrWebsite());
					tranTraderecord.setTtrRemark(traderecord.getTrRemark());
					tranTraderecord.setTtrDatetime(new Date());
					tranTraderecordService.saveTranTraderecord(tranTraderecord);
				}
				logger.info(traderecord.toString());
				operateResult = traderecordService.saveTraderecord(traderecord);
				operateResult.setTrNo(traderecord.getTrNo());
				return operateResult;
			}
    	}catch(Exception e){
    		e.printStackTrace();
    		logger.error(e.getMessage());
    		operateResult.setSuccess(false);
    		operateResult.setMessage(e.getMessage());
    	}

    	return operateResult;
    }
    
    public OperateResult query(HttpServletRequest request) {
    	OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(true);
		BankReturnBean bankReturnBean = new BankReturnBean();
		bankReturnBean.setReq(request);
		
		
		/*************************************1, 订单请求参数校验******************************************************/
		
		operateResult  = CheckDataUtil.verifyQueryRequestData(request);
		if(!operateResult.isSuccess()){
			return operateResult;
		}
		
		/*******************************************2, 商户校验*****************************************************/
		
		Gateway gateway = gatewayService.getGateway(request.getParameter("merNo"), request.getParameter("gatewayNo"));
		Merchant merchant = merchantService.getMerchant(request.getParameter("merNo"));
		operateResult = CheckDataUtil.verifyGateway(gateway,merchant);
		if(!operateResult.isSuccess()){
			return operateResult;
		}
		
		/*******************************************3, 签名验证*****************************************************/
		
		operateResult = CheckDataUtil.verifyQuerySignInfo(request, gateway);
		if(!operateResult.isSuccess()){
			return operateResult;
		}
		
		String  orderNo = request.getParameter("orderNo");
		//2 订单查询
		//测试查询，直接查询InformalTraderecord
		if(ConstantsBean.STATUS_TEST ==  gateway.getGwStatus()){
			InformalTraderecord informalTraderecord = informalTraderecordService.getformalTraderecordByMerOrderNo(request.getParameter("merNo"), request.getParameter("gatewayNo"),orderNo);
			if(informalTraderecord==null){
				operateResult.setSuccess(false);
				operateResult.setMessage("I0071");
				logger.info("查询请求，流水不存在="+operateResult.isSuccess()+",返回码："+operateResult.getMessage());
				return operateResult;
			}
			return operateResult;
		}
		
		Traderecord traderecord = traderecordService.getTraderecordByMerOrderNo(request.getParameter("merNo"), request.getParameter("gatewayNo"),orderNo);
		if(traderecord==null){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0071");
			logger.info("查询请求，订单不存在："+operateResult.isSuccess()+",返回码："+operateResult.getMessage());
			return operateResult;
		}
		String merNoq = String.valueOf(gateway.getMerchant().getMerNo());
		String merNot =String.valueOf(traderecord.getTrMerNo());
		if(!merNoq.equals(merNot)){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0071");
			logger.info("查询请求，订单不存在："+operateResult.isSuccess()+",返回码："+operateResult.getMessage());
			return operateResult;
		}
		
		//3 支付成功或支付失败，则直接返回
		if(traderecord.getTrStatus() == 1 || traderecord.getTrStatus() == 0){
			return operateResult;
		}	

		String bankCode = traderecord.getTrBankCode();
		if(StringUtils.isBlank(bankCode)){
			logger.info("未发现选择支付方式动作，直接返回支付状态，订单号："+traderecord.getTrNo()+",状态："+traderecord.getTrStatus());
			return operateResult;
		}
		
		//根据支付渠道获取网关代理
		String operateMode = bankCode+"Service";;
		Channel channel = channelService.getChannel(String.valueOf(traderecord.getTrChaCode()));
		if("2".equals(channel.getChaType())){
			operateMode = bankCode+"EService";
		}
		logger.info("operateMode="+operateMode);
		PayGatewayProxy proxy = commonService.findPayGatewayProxy(operateMode);
		if(proxy==null){
			operateResult.setSuccess(false);
			operateResult.setMessage("I0073");
			logger.info("查询请求，网关代理不存在="+operateResult.isSuccess()+",返回码："+operateResult.getMessage());
			return operateResult;
		}
		
		//5,网关查询
		operateResult = proxy.query(traderecord, bankReturnBean);
		
		if(operateResult.isSuccess()){
			if(!"".equals(bankReturnBean.getResponseTrdeNo())){
				if(ConstantsBean.TRADE_STATUS_SUCCESS==Integer.valueOf(bankReturnBean.getResponseStatus())){
					traderecord.setTrStatus(1);
				}else if(ConstantsBean.TRADE_STATUS_FAIL==Integer.valueOf(bankReturnBean.getResponseStatus())){
					traderecord.setTrStatus(0);
				}else{
					traderecord.setTrStatus(-1);
				}

				traderecord.setTrBankorderno(bankReturnBean.getResponseBankNo());
				traderecord.setTrBankreturncode(bankReturnBean.getResponseCode());
				traderecord.setTrBankinfo(bankReturnBean.getResponseInfo());
				traderecord.setTrBatchno(bankReturnBean.getResponseBatchNo());
				traderecord.setTrNo(bankReturnBean.getResponseTrdeNo());
				
				traderecordService.saveOrUpdateTraderecord(traderecord);
			}
		}
    	return operateResult;
    }
    
    public OperateResult querybatch(HttpServletRequest request) {
    	OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(true);
		BankReturnBean bankReturnBean = new BankReturnBean();
		bankReturnBean.setReq(request);
		
		
		/*************************************1, 订单请求参数校验******************************************************/
		
		operateResult  = CheckDataUtil.verifyQuerybatchRequestData(request);
		if(!operateResult.isSuccess()){
			return operateResult;
		}
		
		/*******************************************2, 商户校验*****************************************************/
		
		Gateway gateway = gatewayService.getGateway(request.getParameter("merNo"), request.getParameter("gatewayNo"));
		Merchant merchant = merchantService.getMerchant(request.getParameter("merNo"));
		operateResult = CheckDataUtil.verifyGateway(gateway,merchant);
		if(!operateResult.isSuccess()){
			return operateResult;
		}
		
		/*******************************************3, 签名验证*****************************************************/
		
		operateResult = CheckDataUtil.verifyQuerybatchSignInfo(request, gateway);
		if(!operateResult.isSuccess()){
			return operateResult;
		}
		
    	return operateResult;
    }
    
    public OperateResult refund(HttpServletRequest request) {
    	OperateResult operateResult = new OperateResult();
    	operateResult.setSuccess(true);
    	
    	BankReturnBean bankReturnBean = new BankReturnBean();
		bankReturnBean.setReq(request);
		try{
			
	    	
    		/*************************************1, 订单请求参数校验******************************************************/
    		
    		operateResult  = CheckDataUtil.verifyRefundRequestData(request);
			if(!operateResult.isSuccess()){
				return operateResult;
			}
			
			/*******************************************2, 商户校验*****************************************************/
			
			Gateway gateway = gatewayService.getGateway(request.getParameter("merNo"), request.getParameter("gatewayNo"));
			Merchant merchant = merchantService.getMerchant(request.getParameter("merNo"));
			operateResult = CheckDataUtil.verifyGateway(gateway,merchant);
			if(!operateResult.isSuccess()){
				return operateResult;
			}
			
			/*******************************************3, 签名验证*****************************************************/
			
			operateResult = CheckDataUtil.verifyRefundSignInfo(request, gateway);
			if(!operateResult.isSuccess()){
				return operateResult;
			}
			
			//测试的网关接入号只能接入测试接口
			if(ConstantsBean.STATUS_TEST ==  gateway.getGwStatus()){
				operateResult.setSuccess(false);
				operateResult.setMessage("I0077");
				return operateResult;
			}
			
			String  orderNo = request.getParameter("orderNo");
			Traderecord traderecord = traderecordService.getTraderecordByMerOrderNo(request.getParameter("merNo"), request.getParameter("gatewayNo"),orderNo);
			
			if(traderecord ==null){
				operateResult.setSuccess(false);
				operateResult.setMessage("I0071");
				logger.info("退款请求，流水不存在="+operateResult.isSuccess()+",返回码："+operateResult.getMessage());
				return operateResult;
			}
	
			//4 状态查询
			if(traderecord.getTrStatus() != 1){
				operateResult.setSuccess(false);
				operateResult.setMessage("I0076");
				return operateResult;
			}
			
			//5 已退款（系统已退款，则不用到支付网关退款）
			if(traderecord.getTrRefundment() != null && traderecord.getTrRefundment() == 1){
				operateResult.setSuccess(true);
				operateResult.setMessage("系统状态为已退款状态，直接返回商户退款信息！一个订单只能退款一次！");
				return operateResult;
			}
			
			//6 退款金额合法校验。(request.getParameter("refundAmount")
			if(traderecord.getTrAmount().compareTo((new BigDecimal(request.getParameter("refundAmount")))) <0){
				operateResult.setSuccess(false);
				operateResult.setMessage("I0074");
				return operateResult;
			}
			
			UnNormalProcess unNormalProcess = unNormalProcessService.getUnNormalProcess(traderecord.getTrNo());
			if(unNormalProcess==null){
				unNormalProcess = new UnNormalProcess();
				unNormalProcess.setTraderecord(traderecord);
				unNormalProcess.setUpSubmitby(1);
				unNormalProcess.setUpType(1);
				unNormalProcess.setUpCurrency(traderecord.getTrCurrency());
				unNormalProcess.setUpAmount(new BigDecimal(request.getParameter("refundAmount")));
				unNormalProcess.setUpStatus(0);
				unNormalProcess.setUpLoginName("System");
				unNormalProcess.setUpOprtime(new Date());
				unNormalProcess.setUpReason(request.getParameter("refundReason"));
				unNormalProcess.setUpSettlementStatus(0);
				unNormalProcess.setUpAgentStatus(0);
				unNormalProcess.setUpIsfirst(0);
				unNormalProcess.setUpIsfirstagent(0);
				operateResult =  unNormalProcessService.saveUnNormalProcess(unNormalProcess);
				if(!operateResult.isSuccess()){
					return operateResult;
				}
			}
			
			//7 根据支付渠道查找网关代理
			String bankCode = traderecord.getTrBankCode();
			
			//根据支付渠道获取网关代理
			String operateMode = bankCode+"Service";;
			Channel channel = channelService.getChannel(String.valueOf(traderecord.getTrChaCode()));
			if("1".equals(channel.getChaType())){
				operateMode = bankCode+"EService";
			}
			
			logger.info("operateMode="+operateMode);
			PayGatewayProxy proxy = commonService.findPayGatewayProxy(operateMode);
			if(proxy==null){
				operateResult.setSuccess(false);
				operateResult.setMessage("I0073");
				logger.info("退款请求，网关代理不存在="+operateResult.isSuccess()+",返回码："+operateResult.getMessage());
				return operateResult;
			}
			traderecord.setTrRefundmentAmount(new BigDecimal(request.getParameter("refundAmount")));
			//7网关退款
		    operateResult = proxy.refund(traderecord, bankReturnBean); 
		    UnNormalProcess unNormalProcessupdate = unNormalProcessService.getUnNormalProcess(traderecord.getTrNo());
		    
		    unNormalProcessupdate.setUpExetime(new Date());
		    if(!operateResult.isSuccess()){
		    	//traderecord.setTrRefundment(0);
		    	//traderecord.setTrStatus(4);
		    	unNormalProcessupdate.setUpStatus(-1);
		    	unNormalProcessupdate.setUpRemark(operateResult.getMessage());
		    }else{
		    
		    	traderecord.setTrBankorderno(bankReturnBean.getResponseBankNo());
		    	traderecord.setTrBatchno(bankReturnBean.getResponseBatchNo());
		    	traderecord.setTrRefundmentAmount(new BigDecimal(bankReturnBean.getResponseRefundAmount()));
		    	traderecord.setTrBankdatetime(bankReturnBean.getResponseTradeTime());
			    
			    if(ConstantsBean.TRADE_STATUS_REFUND==Integer.valueOf(bankReturnBean.getResponseStatus())){
			    	traderecord.setTrRefundment(1);
			    	unNormalProcessupdate.setUpStatus(2);
				}else if(ConstantsBean.TRADE_STATUS_REFUND_ING==Integer.valueOf(bankReturnBean.getResponseStatus())){
					traderecord.setTrRefundment(2);
					unNormalProcessupdate.setUpStatus(0);
				}else if(ConstantsBean.TRADE_STATUS_REFUND_FAIL==Integer.valueOf(bankReturnBean.getResponseStatus())){
					traderecord.setTrRefundment(0);
					unNormalProcessupdate.setUpStatus(-1);
				}
			    traderecordService.saveOrUpdateTraderecord(traderecord);
		    }
		    
		    //unNormalProcessupdate.setTraderecord(traderecord);
		    unNormalProcessService.saveOrUpdateUnNormalProcess(unNormalProcessupdate);
		}catch(Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			operateResult.setSuccess(false);
			operateResult.setMessage("E9999");
		}
		return operateResult;
    }
    
    public OperateResult synOrderBankStatus(HttpServletRequest request) {
    	OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(false);
		BankReturnBean bankReturnBean = new BankReturnBean();
		try{
			String trNos = request.getParameter("trNos");
			if(StringUtils.isBlank(trNos)){
				logger.info("同步订单号为空");
				operateResult.setMessage("同步订单号为空");
				return operateResult;
			}
			trNos = URLDecoder.decode(trNos);

			operateResult = CheckDataUtil.verifySynSignInfo(request,trNos,"jinxin777");
			if(!operateResult.isSuccess()){
				logger.info("同步签名错误");
				operateResult.setMessage("同步签名错误");
				return operateResult;
			}
			
			String[] trNoArray = trNos.split(",");
			if(trNoArray.length ==0 ){
				logger.info("同步订单号为空");
				operateResult.setMessage("同步订单号为空");
				return operateResult;
			}
			logger.info("同步订单号："+trNoArray.toString());
			operateResult.setSuccess(true);

			for(int i=0;i<trNoArray.length;i++){
				String trNo = trNoArray[i];
				if(StringUtils.isBlank(trNo)){
					continue;
				}
				Traderecord traderecord = traderecordService.getTraderecord(trNo);
				if(traderecord==null){
					continue;
				}
				String bankCode = traderecord.getTrBankCode();
				if(StringUtils.isBlank(bankCode)){
					logger.info("未发现选择支付方式动作，直接返回支付状态，订单号："+traderecord.getTrNo()+",状态："+traderecord.getTrStatus());
					continue;
				}
				
				//根据支付渠道获取网关代理
				String operateMode = bankCode+"Service";;
				Channel channel = channelService.getChannel(String.valueOf(traderecord.getTrChaCode()));
				if("2".equals(channel.getChaType())){
					operateMode = bankCode+"EService";
				}
				
				logger.info("operateMode="+operateMode);
				PayGatewayProxy proxy = commonService.findPayGatewayProxy(operateMode);
				if(proxy==null){
					operateResult.setSuccess(false);
					operateResult.setMessage("I0073");
					logger.info("查询请求，网关代理不存在="+operateResult.isSuccess()+",返回码："+operateResult.getMessage());
					continue;
				}
				
				//5,网关查询
				operateResult = proxy.query(traderecord, bankReturnBean);
				
				if(operateResult.isSuccess()){
					if(!"".equals(bankReturnBean.getResponseTrdeNo())){
						if(ConstantsBean.TRADE_STATUS_SUCCESS==Integer.valueOf(bankReturnBean.getResponseStatus())){
							traderecord.setTrStatus(1);
						}else if(ConstantsBean.TRADE_STATUS_FAIL==Integer.valueOf(bankReturnBean.getResponseStatus())){
							traderecord.setTrStatus(0);
						}else{
							traderecord.setTrStatus(-1);
						}
	
						traderecord.setTrBankorderno(bankReturnBean.getResponseBankNo());
						traderecord.setTrBankreturncode(bankReturnBean.getResponseCode());
						traderecord.setTrBankinfo(bankReturnBean.getResponseInfo());
						traderecord.setTrBatchno(bankReturnBean.getResponseBatchNo());
						traderecord.setTrNo(bankReturnBean.getResponseTrdeNo());
						traderecordService.saveOrUpdateTraderecord(traderecord);
					}
				}
			}
		}catch(Exception e){
			logger.error("订单同步异常："+e.getMessage());
			e.printStackTrace();
			operateResult.setMessage("订单同步异常："+e.getMessage());
		}
		return operateResult;
    }

	public UnNormalProcessServiceI getUnNormalProcessService() {
		return unNormalProcessService;
	}

	public void setUnNormalProcessService(
			UnNormalProcessServiceI unNormalProcessService) {
		this.unNormalProcessService = unNormalProcessService;
	}

	public TraderecordServiceI getTraderecordService() {
		return traderecordService;
	}

	public void setTraderecordService(TraderecordServiceI traderecordService) {
		this.traderecordService = traderecordService;
	}

	public CommonServiceI getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonServiceI commonService) {
		this.commonService = commonService;
	}

	public InformalTraderecordServiceI getInformalTraderecordService() {
		return informalTraderecordService;
	}

	public void setInformalTraderecordService(
			InformalTraderecordServiceI informalTraderecordService) {
		this.informalTraderecordService = informalTraderecordService;
	}

	public MerChannelServiceI getMerChannelService() {
		return merChannelService;
	}

	public void setMerChannelService(MerChannelServiceI merChannelService) {
		this.merChannelService = merChannelService;
	}

	public AgentMerServiceI getAgentMerService() {
		return agentMerService;
	}

	public void setAgentMerService(AgentMerServiceI agentMerService) {
		this.agentMerService = agentMerService;
	}

	public ChannelServiceI getChannelService() {
		return channelService;
	}

	public void setChannelService(ChannelServiceI channelService) {
		this.channelService = channelService;
	}

	public MerRateServiceI getMerRateService() {
		return merRateService;
	}

	public void setMerRateService(MerRateServiceI merRateService) {
		this.merRateService = merRateService;
	}

	public MerRiskElementServiceI getMerRiskElementService() {
		return merRiskElementService;
	}

	public void setMerRiskElementService(
			MerRiskElementServiceI merRiskElementService) {
		this.merRiskElementService = merRiskElementService;
	}

	public MerTranElementServiceI getMerTranElementService() {
		return merTranElementService;
	}

	public void setMerTranElementService(
			MerTranElementServiceI merTranElementService) {
		this.merTranElementService = merTranElementService;
	}

	public GatewayServiceI getGatewayService() {
		return gatewayService;
	}

	public void setGatewayService(GatewayServiceI gatewayService) {
		this.gatewayService = gatewayService;
	}

	public RateServiceI getRateService() {
		return this.rateService;
	}

	public void setRateService(RateServiceI rateService) {
		this.rateService = rateService;
	}

	public TranTraderecordServiceI getTranTraderecordService() {
		return this.tranTraderecordService;
	}

	public void setTranTraderecordService(
			TranTraderecordServiceI tranTraderecordService) {
		this.tranTraderecordService = tranTraderecordService;
	}

	public MerchantServiceI getMerchantService() {
		return merchantService;
	}

	public void setMerchantService(MerchantServiceI merchantService) {
		this.merchantService = merchantService;
	}
}
