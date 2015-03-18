package com.jinxin.service.paygateway.online.hxb.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jinxin.common.utils.DateTimeUtils;
import com.jinxin.common.utils.StaticMapUtil;
import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.model.BankReturnBean;
import com.jinxin.model.BankTradeInfoBean;
import com.jinxin.model.ConstantsBean;
import com.jinxin.model.persistence.Traderecord;
import com.jinxin.service.paygateway.OnlineWorker;
import com.jinxin.service.paygateway.online.OnlinePayGatewayProxy;
import com.jinxin.service.paygateway.online.hxb.util.HxbConstants;
import com.jinxin.service.paygateway.online.hxb.util.HxbSendBean;
import com.jinxin.service.paygateway.online.hxb.util.hxbUtil.MyUtil;
import com.jinxin.service.paygateway.online.hxb.util.hxbUtil.SecurityUtil;
import com.jinxin.service.paygateway.online.hxb.util.hxbUtil.XMLDocument;

/**
 * @className:HxbPayGatewayProxyImpl
 * @classDescription:
 * @author:erichu
 * @createTime:2014-6-26 下午4:06:00
 */
@Service("hxbService")
@Scope("prototype")
public class HxbPayGatewayProxyImpl extends OnlinePayGatewayProxy{
	private static final Logger logger = Logger.getLogger(HxbPayGatewayProxyImpl.class);
	
	@Autowired
	private OnlineWorker hxbWorker;
	
	/**
     * 支付前的操作，用于在线支付(页面支付)
     * 
     * @param TOrder
     *            任务单对象
     * @return
     */
	public OperateResult prefixPayment(Traderecord traderecord,HttpServletRequest request) {
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(true);
		HxbSendBean sendDataBean = new HxbSendBean();
		try{
			
			String pweb_server_url= StaticMapUtil.paramMap.get("pweb_server_url");
			
			//0 个人，1单位
			String custType=HxbConstants.custType;
			//商户编码
			String merchantId =HxbConstants.merchantId;
			//二级商户编码
			String subMerchantId="";
			String orderNo =traderecord.getTrNo();
			
			String orderAmt="";
			DecimalFormat df = new DecimalFormat("#");
	    	BigDecimal samount = traderecord.getTrAmount().multiply(new BigDecimal("100"));
	    	orderAmt = df.format(samount);

			//人民币
			String currency=HxbConstants.currency;
			// 01 一般支付 06预授权 10冻结 29电话支付
			String trnxType = HxbConstants.trnxType;
			//会员号，选填
			String memberId ="";
			String freezeEdDate="";
			String unFreezeEdDate ="";
			String remark1="";
			String remark2=pweb_server_url+"/hxbController/frontNotify.do";
			
			String now = MyUtil.getCurrentDate();

			String head = "<Head><Identification>"+HxbConstants.Identification+"</Identification><Channel>"+HxbConstants.Channel+"</Channel><TrnxCode>"+HxbConstants.PayTrnxCode+"</TrnxCode><TrnxDatetime>" + now + "</TrnxDatetime></Head>";
			String s = "<HXE>" + head + "<Body><CustType>" + custType + "</CustType>" + 
				"<MerchantId>" + merchantId + "</MerchantId>" + 
				"<SubMerchantId>" + subMerchantId + "</SubMerchantId>" + 
				"<OrderNo>" + orderNo + "</OrderNo>" + 
				"<OrderAmt>" + orderAmt + "</OrderAmt>" + 
				"<Currency>" + currency + "</Currency>" + 
				"<TrnxType>" + trnxType + "</TrnxType>" + 
				"<MemberId>" + memberId + "</MemberId>" + 
				"<FreezeEdDate>" + freezeEdDate + "</FreezeEdDate>" + 
				"<UnFreezeEdDate>" + unFreezeEdDate + "</UnFreezeEdDate>" + 
				"<Remark1>" + remark1 + "</Remark1>" + 
				"<Remark2>" + remark2 + "</Remark2>" + 
				"</Body></HXE>";
			logger.info("封装订单请求报文:"+s);
			try{
				XMLDocument xmlDoc = new XMLDocument(s);
				String ss = xmlDoc.getFormatDocument(" ").toString();
				String sign = SecurityUtil.sign(s);
				if(!sign.equals("0")){
					operateResult.setSuccess(false);
					operateResult.setMessage("【" + sign + "】签名失败,请咨询管理人员！");
					logger.info("【" + sign + "】签名失败,请咨询管理人员！");
					return operateResult;
				}
				logger.info("签名上面订单报文得到:"+SecurityUtil.getSignMessage());
				String signMessage = SecurityUtil.ncClientHttp(SecurityUtil.getSignMessage());//发送后返回的响应信息
				logger.info("发起NC安全HTTP服务请求结果:"+signMessage);
				String[] str2 = signMessage.split("\r\n\r\n");
				String verifysign = "";
				if(str2 != null && str2.length > 1){
					if(str2[0].indexOf("200 OK") >= 0){
						verifysign = SecurityUtil.verifysign(str2[1]);
					}else{
						operateResult.setSuccess(false);
						operateResult.setMessage("请求失败，请联系管理人员!");
						logger.info("请求失败，请联系管理人员!");
						return operateResult;
					}

				}else{
					operateResult.setSuccess(false);
					operateResult.setMessage("请求失败，请联系管理人员!");
					logger.info("请求失败，请联系管理人员!");
					return operateResult;
				}
				if(!verifysign.equals("0")){
					operateResult.setSuccess(false);
					operateResult.setMessage("验签失败,请咨询管理人员！");
					logger.info("验签失败,请咨询管理人员！");
					return operateResult;
				}

				String allMessage = new String(SecurityUtil.getSicString());
				logger.info("验证签名信息:"+allMessage);
				XMLDocument xml = new XMLDocument(allMessage);
				String respCode = xml.getValue("RespCode").toString();
				String trnxDT = xml.getValue("TrnxDatetime").toString();
				String trnxCode = xml.getValue("TrnxCode").toString();
				String errorMessage = xml.getValue("RespDescp").toString();
				
				if(!"000000".equals(respCode)){
					operateResult.setSuccess(false);
					operateResult.setMessage(errorMessage+",响应码["+respCode+"]");
					logger.info(errorMessage+",响应码["+respCode+"]");
					return operateResult;
				}
				
				if(xml.getValue("Body") != null){
					String s2 = xml.getValue("Body").toString();
					XMLDocument xml2 = new XMLDocument(s2);
					String url = xml.getValue("URL").toString();
					logger.info("respCode="+respCode+",trnxDT="+trnxDT+",trnxCode="+trnxCode+",errorMessage="+errorMessage+",posturl="+url);
					if(StringUtils.isBlank(url)){
						operateResult.setSuccess(false);
						operateResult.setMessage("地址信息为空！");
						logger.info("地址信息为空！");
						return operateResult;
					}
					sendDataBean.setServiceUrl(url);
				}else{
					operateResult.setSuccess(false);
					operateResult.setMessage("没有收到响应的报文体Body信息");
					logger.info("没有收到响应的报文体Body信息");
					return operateResult;
				}
			}catch(Exception e){
				operateResult.setMessage("OMG,系统异常，请求失败，请咨询管理人员!! "+e.getMessage());
				operateResult.setSuccess(false);
				return operateResult;
			}

			String sign = "";
			sendDataBean.setSign(sign);
			request.setAttribute("sendDataBean", sendDataBean);
		}catch(Exception e){
			operateResult.setMessage(e.getMessage());
			operateResult.setSuccess(false);
			return operateResult;
		}
		return operateResult;
	}

	 /**
	     * 收款Notify调用代理，用于在线支付(页面通知)
	     * 
	     * @param request
	     *            从request获取变量值
	     * @return
	     * @throws Exception 
	     */
	public OperateResult frontNotifyPayment(HttpServletRequest request,HttpServletResponse response,BankReturnBean bankReturnBean) throws Exception{
		 logger.info("******开始华夏银行页面通知*********");
		 logger.info("华夏银行响应信息："+request.getParameter("hxesb"));
		 OperateResult operateResult = new OperateResult();
		 operateResult.setSuccess(true);
		 try{
			 if(StringUtils.isBlank(request.getParameter("hxesb"))){
				 operateResult.setSuccess(false);
				 operateResult.setMessage("返回数据hxesb为空！");
				 return operateResult;
			 }
			 String hxesb  = request.getParameter("hxesb");
			 String verifysign = SecurityUtil.verifysign(hxesb);
			 
			 if(!verifysign.equals("0")){
					operateResult.setSuccess(false);
					operateResult.setMessage("【" + verifysign + "】验证签名失败,请咨询管理人员！");
					logger.info("【" + verifysign + "】验证签名失败,请咨询管理人员！");
					return operateResult;
			 }
			 logger.info("verifysign=" + verifysign);
			 String	cretDN = SecurityUtil.getCretDN();
			 String	sicString = SecurityUtil.getSicString();
			 logger.info("cretDN=" + cretDN);
			 logger.info("签名华夏银行响应信息："+sicString);
			 
			 String allMessage = new String(sicString);
			 XMLDocument xml = new XMLDocument(allMessage);
			 String Identification = xml.getValue("Identification").toString();
			 String TrnxCode = xml.getValue("TrnxCode").toString();
			 String TrnxDatetime = xml.getValue("TrnxDatetime").toString();
			
			 String EPayFlwNo = xml.getValue("EPayFlwNo").toString();
			 String MerchantId = xml.getValue("MerchantId").toString();
			 String TrnxType = xml.getValue("TrnxType").toString();
			 String OrderNo = xml.getValue("OrderNo").toString();
			 String OrderAmt = xml.getValue("OrderAmt").toString();
			 String Status = xml.getValue("Status").toString();
			
			 logger.info("Identification=" + Identification);
			 logger.info("TrnxCode=" + TrnxCode);
			 logger.info("TrnxDatetime=" + TrnxDatetime);
			 logger.info("EPayFlwNo=" + EPayFlwNo);
			 logger.info("MerchantId=" + MerchantId);
			 logger.info("TrnxType=" + TrnxType);
			 logger.info("OrderNo=" + OrderNo);
			 Double amount = Double.valueOf(OrderAmt)/100;
			 logger.info("OrderAmt=" + OrderAmt+",amount="+amount);
			 logger.info("Status=" + Status);
			 
			 if("0".equals(Status)){
				 bankReturnBean.setResponseStatus(1);
			 }else{
				 bankReturnBean.setResponseStatus(0);
			 }
			 
			 BankTradeInfoBean bankTradeInfoBean = new BankTradeInfoBean();
			 bankReturnBean.setReq(request);
			 bankReturnBean.setResp(response);
			 bankReturnBean.setTradeInfoBean(bankTradeInfoBean);
			 bankReturnBean.setResponseBankNo(EPayFlwNo);
			 bankReturnBean.setResponseTrdeNo(OrderNo);
			 bankReturnBean.setResponseAmount(amount);
			 bankReturnBean.setResponseTradeTime(DateTimeUtils.parseTimestamp(TrnxDatetime));

			 
		 }catch(Exception e){
			 logger.error(e.getMessage());
		 }
		 return operateResult;
	}
	

	/**
     * 根据外部交易号查询（后台查询）
     * 
     * @param TOrder
     *            任务单对象
     * @return
     */
	 public OperateResult query(Traderecord traderecord,BankReturnBean bankReturnBean){
		logger.info("******开始华夏银行离线查询*********");
	    logger.info("查询流水号："+traderecord.getTrNo());
	    	
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(true);
		try{
			
			String now = MyUtil.getCurrentDate();
			String merchantId = HxbConstants.merchantId;
			String searchType = HxbConstants.searchType;
			String orderNo = traderecord.getTrNo();
			String remark1="";
			String remark2="";
			
			String head = "<Head><Identification>"+HxbConstants.Identification+"</Identification><Channel>"+HxbConstants.Channel+"</Channel><TrnxCode>"+HxbConstants.QueryTrnxCode+"</TrnxCode><TrnxDatetime>" + now + "</TrnxDatetime></Head>";
			String s = "<HXE>" + head + "<Body><MerchantId>" + merchantId + "</MerchantId>" + 
					"<TrnxType>" + HxbConstants.trnxType + "</TrnxType>" + 
				"<SearchType>" + searchType + "</SearchType>" + 
				"<OrderNo>" + orderNo + "</OrderNo>" + 
				"<Remark1>" + remark1 + "</Remark1>" + 
				"<Remark2>" + remark2 + "</Remark2>" + 
				"</Body></HXE>";

				XMLDocument xmlDoc = new XMLDocument(s);
				String ss = xmlDoc.getFormatDocument(" ").toString();
				String sign = SecurityUtil.sign(s);
				if(!sign.equals("0")){
					operateResult.setSuccess(false);
					operateResult.setMessage("【" + sign + "】签名失败,请咨询管理人员！");
					//签名失败！
					return operateResult;
				}
				
				String signMessage = SecurityUtil.ncClientHttp(SecurityUtil.getSignMessage());//发送后返回的响应信息
				String[] str2 = signMessage.split("\r\n\r\n");
				String verifysign = "";
				if(str2 != null && str2.length > 1){
					if(str2[0].indexOf("200 OK") >= 0){
						verifysign = SecurityUtil.verifysign(str2[1]);
					}else{
						operateResult.setSuccess(false);
						operateResult.setMessage("请求失败，请联系管理人员!");
						//签名失败！
						return operateResult;
					}
				}else{
					operateResult.setSuccess(false);
					operateResult.setMessage("请求失败，请联系管理人员!");
					//签名失败！
					return operateResult;
				}
				
				if(!verifysign.equals("0")){
					operateResult.setSuccess(false);
					operateResult.setMessage("【" + verifysign + "】验签失败,请咨询管理人员！");
					//签名失败！
					return operateResult;
				}

				String allMessage = new String(SecurityUtil.getSicString());
				XMLDocument xml = new XMLDocument(allMessage);
				String respCode = xml.getValue("RespCode").toString();
				String trnxDT = xml.getValue("TrnxDatetime").toString();
				String trnxCode = xml.getValue("TrnxCode").toString();
				String errorMessage = xml.getValue("RespDescp").toString();
				logger.info("respCode="+respCode+",trnxDT="+trnxDT+",trnxCode="+trnxCode+",errorMessage="+errorMessage);
				if(xml.getValue("Body") != null){
					String s2 = xml.getValue("Body").toString();
					XMLDocument xml2 = new XMLDocument(s2);
					String respMsg = xml2.getFormatDocument(" ").toString();
					logger.info("respMsg="+respMsg);
					String Status = xml2.getValue("Status").toString();
					String OrderNo = xml2.getValue("OrderNo").toString();
					String TrnxFlwNo = xml2.getValue("TrnxFlwNo").toString();
					String OrderDT = xml2.getValue("OrderDT").toString();
					String OrderAmt = xml2.getValue("OrderAmt").toString();
					if("0".equals(Status)){
						bankReturnBean.setResponseStatus(1);
					}else if("1".equals(Status)){
						bankReturnBean.setResponseStatus(0);
					}else{
						bankReturnBean.setResponseStatus(2);
					}
					
					Double amount = Double.valueOf(OrderAmt)/100;
					bankReturnBean.setResponseTrdeNo(OrderNo);
					bankReturnBean.setResponseCode(trnxCode);
					bankReturnBean.setResponseAmount(amount);
					bankReturnBean.setResponseTradeTime(DateTimeUtils.parseTimestamp(OrderDT, "yyyyMMddHHmmss"));
					
				}else{
					operateResult.setSuccess(false);
					operateResult.setMessage(errorMessage);
					//签名失败！
					return operateResult;
				}

			logger.info("华夏网关查询结果，流水号："+traderecord.getTrNo()+",支付金额："+bankReturnBean.getResponseAmount()+",支付状态返回码："+bankReturnBean.getResponseStatus());
		}catch(Exception e){
			operateResult.setSuccess(false);
			operateResult.setMessage("系统异常，信息："+e.getMessage());
			logger.info(e.getMessage());
			return operateResult;
		}
		return operateResult;
	}

	 
	 /**
     * 退款，针对已经收款的任务单（后台退款）
     * 
     * @param TOrder
     *            任务单对象
     * @return
     */
    public OperateResult refund(Traderecord traderecord,BankReturnBean bankReturnBean){
    	OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(true);
		String now = MyUtil.getCurrentDate();
		String refundOrder = traderecord.getTrNo();
		String orderAmt="";
		DecimalFormat df = new DecimalFormat("#");
	    BigDecimal samount = traderecord.getTrRefundmentAmount().multiply(new BigDecimal("100"));
	    orderAmt = df.format(samount);
		String remark1 = "";
		String remark2 = "";
		 
		String head = "<Head><Identification>"+HxbConstants.Identification+"</Identification><Channel>"+HxbConstants.Channel+"</Channel><TrnxCode>"+HxbConstants.RefundTrnxCode+"</TrnxCode><TrnxDatetime>" + now + "</TrnxDatetime></Head>";
		String s = "<HXE>" + head + "<Body><CustType>" + HxbConstants.custType + "</CustType>" + 
				"<MerchantId>" + HxbConstants.merchantId + "</MerchantId>" + 
				"<TrnxType>" + HxbConstants.refundtrnxType + "</TrnxType>" + 
				"<InitialOrdNo>" + refundOrder + "</InitialOrdNo>" + 
				"<BacAmt>" + orderAmt + "</BacAmt>" + 
				"<Remark1>" + remark1 + "</Remark1>" + 
				"<Remark2>" + remark2 + "</Remark2>" + 
				"</Body></HXE>";
		try{
			XMLDocument xmlDoc = new XMLDocument(s);
			String ss = xmlDoc.getFormatDocument(" ").toString();
			System.out.println("发送报文：\n" + ss);

			String sign = SecurityUtil.sign(s);
			if(!sign.equals("0")){
				operateResult.setSuccess(false);
				operateResult.setMessage("【" + sign + "】签名失败,请咨询管理人员！");
				//签名失败！
				return operateResult;
			}
			System.out.println("=========签名信息【" + SecurityUtil.getSignMessage() + "】========");
			String signMessage = SecurityUtil.ncClientHttp(SecurityUtil.getSignMessage());//发送后返回的响应信息
			String[] str2 = signMessage.split("\r\n\r\n");
			String verifysign = "";
			if(str2 != null && str2.length > 1){
				if(str2[0].indexOf("200 OK") >= 0){
					verifysign = SecurityUtil.verifysign(str2[1]);
				}else{
					operateResult.setSuccess(false);
					operateResult.setMessage("请求失败，请联系管理人员!");
					return operateResult;
				}

			}else{
				operateResult.setSuccess(false);
				operateResult.setMessage("请求失败，请联系管理人员!");
				return operateResult;
			}
			if(!verifysign.equals("0")){
				operateResult.setSuccess(false);
				operateResult.setMessage("【" + verifysign + "】验签失败,请咨询管理人员！");
				return operateResult;
			}

			String allMessage = new String(SecurityUtil.getSicString());
			XMLDocument xml = new XMLDocument(allMessage);
			String respCode = xml.getValue("RespCode").toString();
			String trnxDT = xml.getValue("TrnxDatetime").toString();
			String trnxCode = xml.getValue("TrnxCode").toString();
			String errorMessage = xml.getValue("RespDescp").toString();
			if(xml.getValue("Body") != null){
				String s2 = xml.getValue("Body").toString();
				XMLDocument xml2 = new XMLDocument(s2);
				String respMsg = xml2.getFormatDocument(" ").toString();
				logger.info("respMsg="+respMsg);
				
				String EPayFlwNo = xml2.getValue("EPayFlwNo").toString();
				String InitialOrdNo = xml2.getValue("InitialOrdNo").toString();
				String Status = xml2.getValue("Status").toString();
				// 交易状态 -2 : 待确认 ; -1 : 待处理 ; 0 : 支付失败 ; 1 : 成功 ; 2 : 支付中 ; 3 ：已退款； 4 : 退款中 ； 5 :  退款失败   ； 6 ：已取消 ； 取消失败 
				if("0".equals(Status)){
					bankReturnBean.setResponseStatus(ConstantsBean.TRADE_STATUS_REFUND);
				}else if("1".equals(Status)){
					bankReturnBean.setResponseStatus(ConstantsBean.TRADE_STATUS_REFUND_FAIL);
				}else{
					bankReturnBean.setResponseStatus(ConstantsBean.TRADE_STATUS_REFUND_ING);
				}
				double refundamount = Double.valueOf(String.valueOf(traderecord.getTrRefundmentAmount()));
				bankReturnBean.setResponseTrdeNo(InitialOrdNo);
				bankReturnBean.setResponseInfo(trnxCode);
				bankReturnBean.setResponseBankNo(EPayFlwNo);
				bankReturnBean.setResponseCode(trnxCode);
				bankReturnBean.setResponseRefundAmount(refundamount);
				
			}else{
				operateResult.setSuccess(false);
				operateResult.setMessage("没有收到响应的报文体Body信息");
				return operateResult;
			}
		}catch(Exception e){
			operateResult.setSuccess(false);
			operateResult.setMessage("系统异常，"+e.getMessage());
			return operateResult;
		}
		 
		return operateResult;
	}

	@Override
	public OperateResult query(Date startTime, Date endTime,
			List<BankReturnBean> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OperateResult cancel(Traderecord traderecord, BankReturnBean bankReturnBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OperateResult notifyRefund(HttpServletRequest request,
			BankReturnBean bankReturnBean) {
		// TODO Auto-generated method stub
		return null;
	}
	
	 public OperateResult backNotifyPayment(HttpServletRequest request,HttpServletResponse response,BankReturnBean bankReturnBean) throws Exception{
		 logger.info("******开始华夏银行服务器通知*********");
		 logger.info("通知信息："+request.getParameter("hxesb"));
		 OperateResult operateResult = new OperateResult();
		 operateResult.setSuccess(true);
		 try{
			 if(StringUtils.isBlank(request.getParameter("hxesb"))){
				 operateResult.setSuccess(false);
				 operateResult.setMessage("返回数据hxesb为空！");
				 return operateResult;
			 }
			 String hxesb  = request.getParameter("hxesb");
			 String verifysign = SecurityUtil.verifysign(hxesb);
			 
			 if(!verifysign.equals("0")){
					operateResult.setSuccess(false);
					operateResult.setMessage("【" + verifysign + "】验证签名失败,请咨询管理人员！");
					logger.info("【" + verifysign + "】验证签名失败,请咨询管理人员！");
					return operateResult;
			 }
			 logger.info("verifysign=" + verifysign);
			 String	cretDN = SecurityUtil.getCretDN();
			 String	sicString = SecurityUtil.getSicString();
			 logger.info("cretDN=" + cretDN);
			 logger.info("sicString=" + sicString);
			 
			 
			 String allMessage = new String(sicString);
			 XMLDocument xml = new XMLDocument(allMessage);
			 String Identification = xml.getValue("Identification").toString();
			 String TrnxCode = xml.getValue("TrnxCode").toString();
			 String TrnxDatetime = xml.getValue("TrnxDatetime").toString();
			
			 String EPayFlwNo = xml.getValue("EPayFlwNo").toString();
			 String MerchantId = xml.getValue("MerchantId").toString();
			 String TrnxType = xml.getValue("TrnxType").toString();
			 String OrderNo = xml.getValue("OrderNo").toString();
			 String OrderAmt = xml.getValue("OrderAmt").toString();
			 String Status = xml.getValue("Status").toString();
			
			 logger.info("Identification=" + Identification);
			 logger.info("TrnxCode=" + TrnxCode);
			 logger.info("TrnxDatetime=" + TrnxDatetime);
			 logger.info("EPayFlwNo=" + EPayFlwNo);
			 logger.info("MerchantId=" + MerchantId);
			 logger.info("TrnxType=" + TrnxType);
			 logger.info("OrderNo=" + OrderNo);
			 Double amount = Double.valueOf(OrderAmt)/100;
			 logger.info("OrderAmt=" + OrderAmt+",amount="+amount);
			 logger.info("Status=" + Status);
			 
			 if("0".equals(Status)){
				 bankReturnBean.setResponseStatus(1);
			 }else{
				 bankReturnBean.setResponseStatus(0);
			 }
			 
			 BankTradeInfoBean bankTradeInfoBean = new BankTradeInfoBean();
			 bankReturnBean.setReq(request);
			 bankReturnBean.setResp(response);
			 bankReturnBean.setTradeInfoBean(bankTradeInfoBean);
			 bankReturnBean.setResponseBankNo(EPayFlwNo);
			 bankReturnBean.setResponseTrdeNo(OrderNo);
			 bankReturnBean.setResponseAmount(amount);
			 bankReturnBean.setResponseTradeTime(DateTimeUtils.parseTimestamp(TrnxDatetime));

		 }catch(Exception e){
			 logger.error(e.getMessage());
		 }
		 return operateResult;
	 }
}
