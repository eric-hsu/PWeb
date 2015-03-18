<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head></head>
<body>
<form name="payForm" action="${sendDataBean.serviceUrl}" method="post" accept-charset="utf-8">
		<input type="hidden" value="${sendDataBean.merNo}" name="merchid">
		<input type="hidden" value="${sendDataBean.trNo}" name="orderid">
		<input type="hidden" value="${sendDataBean.amount}" name="amount">
		<input type="hidden" value="${sendDataBean.sign}" name="sign">
		<input type="hidden" value="${sendDataBean.returnurl}" name="returl">
		页面跳转，请稍候.......
</form>
<script type="text/javascript">
	payForm.submit();
</script>
</body>
</html>