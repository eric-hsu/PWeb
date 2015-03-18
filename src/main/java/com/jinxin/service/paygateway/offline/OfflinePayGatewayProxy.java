package com.jinxin.service.paygateway.offline;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.jinxin.common.valueobj.GatewayResult;
import com.jinxin.model.Card;
import com.jinxin.model.online.OlineSendDataBean;
import com.jinxin.model.persistence.Traderecord;

/**
 * 网关代理 功能：封装调用网关时的sendBean和网关返回时的receiveBean
 * 
 * @author eric
 */
public interface OfflinePayGatewayProxy {

    /**
     * 收款支付操作
     * 
     * @param TOrder
     *            任务单对象
     * @return
     */
    public GatewayResult cardPay(Traderecord traderecord,Card card);

    /**
     * 收款Notify调用代理，用于在线支付和快钱(页面支付后台通知)
     * 
     * @param request
     *            从request获取变量值
     * @return
     */
    public GatewayResult notifyPayment(HttpServletRequest request);

    /**
     * 根据外部交易号查询（后台查询）
     * 
     * @param TOrder
     *            任务单对象
     * @return
     */
    public GatewayResult query(Traderecord traderecord);

    /**
     * 根据开始时间和结束时间查询（后台查询）
     * 
     * @param startTime
     *            开始时间
     * @param endTime
     *            结束时间
     * @return
     */
    public List<GatewayResult> query(Date startTime, Date endTime);

    /**
     * 取消交易，针对已经做了处理的任务单（后台取消）
     * 
     * @param TOrder
     *            任务单对象
     * @return
     */
    public GatewayResult cancel(Traderecord traderecord);
    
    /**
     * 退款，针对已经收款的任务单（后台退款）
     * 
     * @param TOrder
     *            任务单对象
     * @return
     */
    public GatewayResult refund(Traderecord traderecord);

    /**
     * 退款Notify调用代理（后台退款结果通知）
     * 
     * @param request
     *            从request获取变量值
     * @return GatewayResult
     */
    public GatewayResult notifyRefund(HttpServletRequest request);
}
