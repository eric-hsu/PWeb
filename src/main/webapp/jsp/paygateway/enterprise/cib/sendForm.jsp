<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head></head>
<body>
<form name="payForm" action="${sendDataBean.serviceUrl}" method="post" accept-charset="utf-8">
		<input type="hidden" value="${sendDataBean.service}" name="service">
		<input type="hidden" value="${sendDataBean.ver}" name="ver">
		<input type="hidden" value="${sendDataBean.merNo}" name="mrch_no">
		<input type="hidden" value="${sendDataBean.sub_mrch}" name="sub_mrch">
		<input type="hidden" value="${sendDataBean.trNo}" name="ord_no">
		<input type="hidden" value="${sendDataBean.ord_date}" name="ord_date">
		<input type="hidden" value="${sendDataBean.currency}" name="cur">
		<input type="hidden" value="${sendDataBean.amount}" name="ord_amt">
		<input type="hidden" value="${sendDataBean.ord_ip}" name="ord_ip">
		<input type="hidden" value="${sendDataBean.sign}" name="mac">
		页面跳转，请稍候.......
</form>
<script type="text/javascript">
	payForm.submit();
</script>
</body>
</html>