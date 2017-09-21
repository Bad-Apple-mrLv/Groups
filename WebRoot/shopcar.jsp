<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
</head>
<body>
	<!-- 静态包含头部 -->
	<%@ include file="head.jsp"%>
	<c:if test="${sessionScope.shopcar eq null}">
		<jsp:forward page="goods_shopcar.action"></jsp:forward>
	</c:if>
	<div id="mainBody">
		<div class="container">
			<div class="row">
				<!-- Sidebar ================================================== -->

				<!-- Sidebar end=============================================== -->
				<div class="span9" style="width: 100%;">
					<ul class="breadcrumb">
						<li><a href="index.jsp">主页</a> <span class="divider">/</span>
						</li>
						<li class="active">购物车</li>
					</ul>
					<h3>购物车</h3>
					<hr class="soft">


					<table class="table table-bordered">
						<thead>
							<tr>
								<th>图片</th>	
								<th>名字</th>
								<th>数量</th>
								<th>单价	</th>
								<th>总价</th>
							</tr>
						</thead>
						<tbody>
						<c:set var="sum" value="0"></c:set>
						<c:forEach items="${sessionScope.shopcar }" var="shoplan">
							<tr>
								<td><img width="60" src="${shoplan.value.goods.goodspic }"
									alt="">
								</td>
								<td>${shoplan.value.goods.name}</td>
								<td>
									<div class="input-append">
										<input class="span1" style="max-width:34px" placeholder="${shoplan.value.count}"
											id="appendedInputButtons" size="16" type="text">
										<button  id="${shoplan.key}" class="btn_sub" type="button">
											<i class="icon-minus"></i>
										</button>
										<button id="${shoplan.key}"  class="btn_add" type="button">
											<i class="icon-plus"></i>
										</button>
										<button  id="${shoplan.key}" class="btn_remove btn-danger " type="button ">
											<i class="icon-remove icon-white"></i>
										</button>
									</div>
								</td>
								<td>$${shoplan.value.goods.price}</td>
								<td>$${shoplan.value.goods.price*shoplan.value.count}</td>			
							</tr>
							<c:set var="sum" value="${sum+shoplan.value.goods.price*shoplan.value.count}"></c:set>
						</c:forEach>
							<tr>
								<td colspan="4" style="text-align:right"><strong>总价
										</strong>
								</td>
								<td class="label label-important" style="display:block"><strong>
									<!-- 如何精确到小数点后一位 -->
										$${sum} </strong>
								</td>
							</tr>
							
						</tbody>
					</table>
             <span style="color:red">${msg}</span>





					<a href="goods_order.action?sum=${sum}" class="btn btn-large pull-right">提交订单 <i
						class="icon-arrow-right"></i> </a>

				</div>
			</div>
		</div>
	</div>
	<!-- MainBody End ============================= -->
	<!-- 静态包含头部 -->
<%@ include file="footer.jsp" %>
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript">
$(function(){
	$(".btn_sub").click(function(){
		window.location.href="/Groups/goods_subshopcar.action?gid="+this.id;
	});
	$(".btn_add").click(function(){
		window.location.href="/Groups/goods_addgoods.action?gid="+this.id;
	});
	$(".btn_remove").click(function(){
		window.location.href="/Groups/goods_removegoods.action?gid="+this.id;
	});
});

</script>
</body>
</html>