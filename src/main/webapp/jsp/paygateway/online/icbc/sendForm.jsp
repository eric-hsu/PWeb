<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head></head>
<body>
<form name="payForm" action="${sendDataBean.serviceUrl}" method="post">
		<input type="hidden" value="${sendDataBean.interfaceName}" name="interfaceName">
		<input type="hidden" value="${sendDataBean.interfaceVersion}" name="interfaceVersion">
		<input type="hidden" value="${sendDataBean.tranData}" name="tranData">
		<input type="hidden" value="${sendDataBean.merSignMsg}" name="merSignMsg">
		<input type="hidden" value="${sendDataBean.merCert}" name="merCert">
		页面跳转，请稍候.......
</form>
<script type="text/javascript">
	payForm.submit();
</script>
</body>
</html>