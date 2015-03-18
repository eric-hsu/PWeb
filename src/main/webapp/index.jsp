<%@ page contentType="text/html;charset=UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<form name="toPps" action="${ctx}/payment/orderRequest.do" method="get">
</form>
<script>
	toPps.submit();
</script>