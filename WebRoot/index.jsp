<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<title>东方标准团购</title>
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
</head>
<body>
	<!-- 静态包含头部 -->
	<%@ include file="head.jsp"%>
	
<!-- 如果为空，则跳转到goods.action -->

<c:if test="${goods eq null}"><jsp:forward page="goods.action"></jsp:forward></c:if>

	<div id="mainBody">
		<div class="container">
			<div class="row">

				<div class="span9" style="width: 100%;">

					<h4>商品</h4>
					<ul class="thumbnails">
						<c:forEach items="${goods}" var="good" varStatus="vs">
							<li class="span3">
								<div class="thumbnail">
									<a href="goods_todetailgood.action?gid=${good.id }"><img src="${good.goodspic }"
										style="height: 200px;" alt=""> </a>
									<div class="caption">
										<h5>${good.name}</h5>
										<p>${good.details }</p>

										<h4 style="text-align:center">
											<a class="btn" href="goods_todetailgood.action?gid=${good.id }">去购买 <i class="icon-shopping-cart"></i>
											</a> <span class="btn btn-primary" >¥${good.price}
											</span>
										</h4>
										<c:if test="${sessionScope.user.type == 1 }">
										<h4 style="text-align:center">
											<a class="btn btn-primary" href="goods_toupdgood.action?gid=${good.id }">更新商品  
											</a>  
										</h4></c:if>
									</div>
								</div></li>
						</c:forEach>
					</ul>

				</div>
			</div>
			<div style="width: 100%;">
			<div class="page_t r c-b-page" style="margin: 0px auto;text-align:center">
				<a href="goods.action?pageCurr=1&typeid=${sessionScope.typeid} ">首页</a>
				<c:if test="${pageCurr > 1 }">
					<a href="goods.action?pageCurr=${pageCurr-1}&typeid=${sessionScope.typeid}">上一页</a>
				</c:if>
				<c:if test="${pageCurr < pageSum }">
					<a href="goods.action?pageCurr=${pageCurr+1}&typeid=${sessionScope.typeid}">下一页</a>
				</c:if>
				<a href="goods.action?pageCurr=${pageSum}&typeid=${sessionScope.typeid}">末页</a>

			</div></div>
		</div>
	</div>
	<!-- 静态包含头部 -->
	<%@ include file="footer.jsp"%>

</body>
</html>