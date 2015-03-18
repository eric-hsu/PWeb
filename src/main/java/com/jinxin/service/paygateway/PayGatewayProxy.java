package com.jinxin.service.paygateway;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.model.BankReturnBean;
import com.jinxin.model.persistence.Traderecord;

public interface PayGatewayProxy {
	/**
     * 收款前的操作，用于在线支付(页面支付)
     * 
     * @param TOrder
     *            任务单对象
     * @return
     */
    public OperateResult prefixPayment(Traderecord traderecord,HttpServletRequest request);

    
    /**
     * 收款Notify调用代理，用于在线支付(页面通知)
     * 
     * @param request
     *            从request获取变量值orderFrontNotify
     * @return
     * @throws Exception 
     */
    public OperateResult frontNotifyPayment(HttpServletRequest request,HttpServletResponse response,BankReturnBean bankReturnBean) throws Exception;
    
    
    /**
     * 收款Notify调用代理，用于在线支付(后台通知)
     * 
     * @param request
     *            从request获取变量值
     * @return
     * @throws Exception 
     */
    public OperateResult backNotifyPayment(HttpServletRequest request,HttpServletResponse response,BankReturnBean bankReturnBean) throws Exception;

    /**
     * 根据外部交易号查询（后台查询）
     * 
     * @param TOrder
     *            任务单对象
     * @return
     */
    public OperateResult query(Traderecord traderecord,BankReturnBean bankReturnBean);
    
    /**
     * 根据开始时间和结束时间查询（后台查询）
     * 
     * @param startTime
     *            开始时间
     * @param endTime
     *            结束时间
     * @return
     */
    public OperateResult query(Date startTime, Date endTime,List<BankReturnBean> list);

    /**
     * 取消交易，针对已经做了处理的任务单（后台取消）
     * 
     * @param TOrder
     *            任务单对象
     * @return
     */
    public OperateResult cancel(Traderecord traderecord,BankReturnBean bankReturnBean);
    
    /**
     * 退款，针对已经收款的任务单（后台退款）
     * 
     * @param TOrder
     *            任务单对象
     * @return
     */
    public OperateResult refund(Traderecord traderecord,BankReturnBean bankReturnBean);

    /**
     * 退款Notify调用代理（后台退款结果通知）
     * 
     * @param request
     *            从request获取变量值
     * @return GatewayResult
     */
    public OperateResult notifyRefund(HttpServletRequest request,BankReturnBean bankReturnBean);
    
}
