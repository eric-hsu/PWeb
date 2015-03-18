<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head></head>
<body>
<form name="orderinfo" action="${sendDataBean.serviceUrl}" method="post" accept-charset="utf-8">
		<input type="hidden" value="${sendDataBean.decrydata}" name="orderinfo">
		页面跳转，请稍候.......
</form>
<script type="text/javascript">
	orderinfo.submit();
</script>
</body>
</html>