<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head></head>
<body>
	<form action="${returnMerchant.returnUrl}" method="post" name="send">
		    <input type="hidden" name="merNo" value="${returnMerchant.merNo}" />
		    <input type="hidden" name="gatewayNo" value="${returnMerchant.gatewayNo}" />
		    <input type="hidden" name="tradeNo" value="${returnMerchant.tradeNo}" />
		    <input type="hidden" name="orderNo" value="${returnMerchant.orderNo}" />
		    <input type="hidden" name="orderAmount" value="${returnMerchant.orderAmount}" />
		    <input type="hidden" name="orderCurrency" value="${returnMerchant.orderCurrency}" />
		    <input type="hidden" name="orderStatus" value="${returnMerchant.orderStatus}" />
		    <input type="hidden" name="orderInfo" value="${returnMerchant.orderInfo}" />
		    <input type="hidden" name="signInfo" value="${returnMerchant.signInfo}" />
		    <input type="hidden" name="remark" value="${returnMerchant.remark}" />
		</form>
<script type="text/javascript">
	send.submit();
</script>
</body>
</html>