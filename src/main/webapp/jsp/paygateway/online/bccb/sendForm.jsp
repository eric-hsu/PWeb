<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head></head>
<body>
<form name="payForm" action="${sendDataBean.serviceUrl}" method="post" accept-charset="utf-8">
		<input type="hidden" value="${sendDataBean.netType}" name="netType">
		<input type="hidden" value="${sendDataBean.merReqData}" name="merReqData">
		页面跳转，请稍候.......
</form>
<script type="text/javascript">
	payForm.submit();
</script>
</body>
</html>