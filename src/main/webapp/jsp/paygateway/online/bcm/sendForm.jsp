<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head></head>
<body>
<form name="toComm" action="${sendDataBean.serviceUrl}" method="post">
			<input type="hidden"  name="interfaceVersion" value="${sendDataBean.intefaceVersion}"/>
			<input type="hidden" name="merID" value="${sendDataBean.merNo}"/>
			<input type="hidden" name="orderid" value="${sendDataBean.trNo}"/>
			<input type="hidden" name="orderDate" value="${sendDataBean.orderDate}"/>
			<input type="hidden" name="orderTime" value="${sendDataBean.orderTime}"/>
			<input type="hidden" name="tranType" value="${sendDataBean.tranType}"/>
			<input type="hidden" name="amount" value="${sendDataBean.amount}"/>
			<input type="hidden" name="curType" value="${sendDataBean.curType}"/>
			<input type="hidden" name="orderContent" value="${sendDataBean.orderContent}"/>
			<input type="hidden" name="orderMono" value="${sendDataBean.orderMono}"/>
			<input type="hidden" name="phdFlag" value="${sendDataBean.phdFlag}"/>
			<input type="hidden" name="notifyType" value="${sendDataBean.notifyType}"/>
			<input type="hidden" name="merURL" value="${sendDataBean.merURL}"/>
			<input type="hidden" name="goodsURL" value="${sendDataBean.goodsURL}"/>
			<input type="hidden" name="jumpSeconds" value="${sendDataBean.jumpSeconds}"/>
			<input type="hidden" name="payBatchNo" value="${sendDataBean.payBatchNo}"/>
			<input type="hidden" name="proxyMerName" value="${sendDataBean.proxyMerName}"/>
			<input type="hidden" name="proxyMerType" value="${sendDataBean.proxyMerType}"/>
			<input type="hidden" name="proxyMerCredentials" value="${sendDataBean.proxyMerCredentials}"/>
			<input type="hidden" name="netType" value="${sendDataBean.netType}"/>
			<input type="hidden" name="merSignMsg" value="${sendDataBean.sign}"/>
			页面跳转，请稍候........
</form>
</body>
<script type="text/javascript">
	toComm.submit();
</script>
</html>