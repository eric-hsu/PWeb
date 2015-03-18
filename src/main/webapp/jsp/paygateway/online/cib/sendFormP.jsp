<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head></head>
<body>
<label>兴业银行B2C手机支付接口：</label>
<form name="payForm" action="https://payment.cib.com.cn/payment/entry.action" method="post" accept-charset="utf-8">
		<input type="text" value="cib.netpay.mb2c" name="service"><br>
		<input type="text" value="01" name="ver"><br>
		<input type="text" value="330423" name="mrch_no"><br>
		<input type="text" value="无" name="sub_mrch"><br>
		<input type="text" value="21245215324533" name="ord_no"><br>
		<input type="text" value="20141023" name="ord_date"><br>
		<input type="text" value="CNY" name="cur"><br>
		<input type="text" value="1000.00" name="ord_amt"><br>
		<input type="text" value="" name="ord_ip"><br>
		<input type="text" value="E327457CE6F4CD6B7E9AFD9059F7E801" name="mac"><br>
		<input type="submit" name="提交">
</form>
</body>
</html>