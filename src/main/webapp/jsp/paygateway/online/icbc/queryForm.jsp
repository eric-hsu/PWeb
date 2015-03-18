<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head></head>
<body>
<Form id=payForm action="https://corporbank3.dccnet.com.cn/servlet/ICBCINBSEBusinessServlet"  method=post>
<input type=hidden name="APIName" value="EAPI">
<input type=hidden name="APIVersion" value="001.001.002.001">
<input type=hidden name="MerReqData" value=" <?xml version="1.0" encoding="GBK" standalone="no"?><ICBCAPI><in><orderNum>20140829102319257327</orderNum><tranDate>20140920101500</tranDate><ShopCode>4000EC24086825</ShopCode><ShopAccount>4000023029200124946</ShopAccount></in></ICBCAPI>">
</Form>  
<script type="text/javascript">
	payForm.submit();
</script>
</body>
</html>