<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>购物车</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<!--Less styles -->
<!-- Other Less css file //different less files has different color scheam
	<link rel="stylesheet/less" type="text/css" href="themes/less/simplex.less">
	<link rel="stylesheet/less" type="text/css" href="themes/less/classified.less">
	<link rel="stylesheet/less" type="text/css" href="themes/less/amelia.less">  MOVE DOWN TO activate
	-->
<!--<link rel="stylesheet/less" type="text/css" href="themes/less/bootshop.less">
	<script src="themes/js/less.js" type="text/javascript"></script> -->

<!-- Bootstrap style -->
<link id="callCss" rel="stylesheet"
	href="themes/bootshop/bootstrap.min.css" media="screen">
<link href="themes/css/base.css" rel="stylesheet" media="screen">
<!-- Bootstrap style responsive -->
<link href="themes/css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="themes/css/font-awesome.css" rel="stylesheet"
	type="text/css">
<!-- Google-code-prettify -->
<link href="themes/js/google-code-prettify/prettify.css"
	rel="stylesheet">
<!-- fav and touch icons -->
<link rel="shortcut icon" href="themes/images/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="themes/images/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="themes/images/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="themes/images/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="themes/images/ico/apple-touch-icon-57-precomposed.png">
<style type="text/css" id="enject"></style>
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript">

    $(function(){

    });


</script>

</head>
<body>
	<!-- 静态包含头部 -->
	<%@ include file="head.jsp"%>
	<div id="mainBody">
		<div class="container">
		
  <c:if test="${ hmac  ne null}">
		   <!-- 提交给易宝支付 -->
<form action="https://www.yeepay.com/app-merchant-proxy/node" method="post">
	<h2>订单号：${p2_Order  } ,金额 ： ${p3_Amt }</h2>
	<input type="hidden" name="pd_FrpId" value="${pd_FrpId }" />
	<input type="hidden" name="p0_Cmd" value="${p0_Cmd }" />
	<input type="hidden" name="p1_MerId" value="${p1_MerId }" />
	<input type="hidden" name="p2_Order" value="${p2_Order }" />
	<input type="hidden" name="p3_Amt" value="${p3_Amt }" />
	<input type="hidden" name="p4_Cur" value="${p4_Cur }" />
	<input type="hidden" name="p5_Pid" value="${p5_Pid }" />
	<input type="hidden" name="p6_Pcat" value="${p6_Pcat }" />
	<input type="hidden" name="p7_Pdesc" value="${p7_Pdesc }" />
	<input type="hidden" name="p8_Url" value="${p8_Url }" />
	<input type="hidden" name="p9_SAF" value="${p9_SAF }" />
	<input type="hidden" name="pa_MP" value="${pa_MP }" />
	<input type="hidden" name="pr_NeedResponse" value="${pr_NeedResponse }" />
	<input type="hidden" name="hmac" value="${hmac }" />
	
	<input type="submit" value="支付" />
</form>
		
		</c:if>		
		
		</div>
	</div>
	<!-- MainBody End ============================= -->
	<!-- 静态包含头部 -->
<%@ include file="footer.jsp" %>

</body>
</html>