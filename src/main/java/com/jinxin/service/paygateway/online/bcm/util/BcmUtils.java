package com.jinxin.service.paygateway.online.bcm.util;

import java.io.UnsupportedEncodingException;
import com.bocom.netpay.b2cAPI.BOCOMSetting;
import com.bocom.netpay.b2cAPI.NetSignServer;

public class BcmUtils {
	public static String getSignMsg(BcmSendBean bcmSendBean) {
        String signMsg = "";
        // TODO: 拼装发送信息
        String sourceMsg = bcmSendBean.getIntefaceVersion() + "|"
                + bcmSendBean.getMerNo() + "|" + bcmSendBean.getTrNo()
                + "|" + bcmSendBean.getOrderDate() + "|"
                + bcmSendBean.getOrderTime() + "|"
                + bcmSendBean.getTranType() + "|" + bcmSendBean.getAmount()
                + "|" + bcmSendBean.getCurType() + "|"
                + bcmSendBean.getOrderContent() + "|"
                + bcmSendBean.getOrderMono() + "|"
                + bcmSendBean.getPhdFlag() + "|"
                + bcmSendBean.getNotifyType() + "|"
                + bcmSendBean.getMerURL() + "|" + bcmSendBean.getGoodsURL()
                + "|" + bcmSendBean.getJumpSeconds() + "|"
                + bcmSendBean.getPayBatchNo() + "|"
                + bcmSendBean.getProxyMerName() + "|"
                + bcmSendBean.getProxyMerType() + "|"
                + bcmSendBean.getProxyMerCredentials() + "|"
                + bcmSendBean.getNetType();
        
        sourceMsg= sourceMsg.replaceAll("null", "");
        NetSignServer nss = new NetSignServer();

        String merchantDN = BOCOMSetting.MerchantCertDN;

        try {
            nss.NSSetPlainText(sourceMsg.getBytes("GBK"));
        } catch (UnsupportedEncodingException e1) {
            signMsg = "Exception";
            e1.printStackTrace();
        }

        byte[] bSignMsg = nss.NSDetachedSign(merchantDN);

        if (0 > nss.getLastErrnum()) {
            System.out.println("ERROR:商户端签名失败");
        }

        try {
            signMsg = new String(bSignMsg, "GBK");
        } catch (UnsupportedEncodingException e) {
            signMsg = "Exception";
            e.printStackTrace();
        }
        return signMsg;
    }
	
	public static int veriySign(String signMsg, String sourceMsg) {
        int veriyCode = -1;
        NetSignServer nss = new NetSignServer();
        try {
            nss.NSDetachedVerify(signMsg.getBytes("GBK"), sourceMsg
                    .getBytes("GBK"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        veriyCode = nss.getLastErrnum();
        return veriyCode;
    }
    
}
