<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 导入标签库 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Bootshop online Shopping cart</title>
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
	<div id="header">
		<div class="container">
			<div id="welcomeLine" class="row">
				<div class="span6">
					<c:if test="${sessionScope.user !=null }">
					 欢迎你,<strong>${sessionScope.user.username }</strong><a href="user_logout.action"><span class="btn btn-mini btn-primary"><i class=""></i>注销 </span> </a>
					</c:if>
				</div>
				<div class="span6">

					<div class="pull-right">
						<c:if test="${sessionScope.user !=null }">
							<a href="shopcar.jsp"><span class="btn btn-mini btn-primary"><i
									class="icon-shopping-cart icon-white"></i>购物车 </span> </a>
						</c:if>
					</div>
				</div>
			</div>
			<!-- Navbar ================================================== -->
			<div id="logoArea" class="navbar">
				<a id="smallScreen" data-target="#topMenu" data-toggle="collapse"
					class="btn btn-navbar"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> </a>
				<div class="navbar-inner">
					<a class="brand" href="index.jsp"><img
						src="themes/images/logo.png" alt="Bootsshop"> </a>
					<form class="form-inline navbar-search" method="get"
						action="goods.action">
						<select class="srchTxt" name="typeid">
							<option value="0">全部</option>
							<c:forEach items="${applicationScope.types}" var="type">
								<c:choose>
									<c:when test="${type.id eq sessionScope.typeid}">
										<option value="${type.id}" selected="selected">${type.typename }</option>
									</c:when>
									<c:otherwise>
										<option value="${type.id}">${type.typename }</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
						<button type="submit" id="submitButton" class="btn btn-primary">查询</button>
					</form>
					<ul id="topMenu" class="nav pull-right">
						<c:if test="${sessionScope.user ==null }">
							<li class=""><a href="login.jsp" role="button"
								style="padding-right:0"><span
									class="btn btn-large btn-success">登录</span> </a> <!--  <div id="login" class="modal hide fade in" tabindex="-1"
								role="dialog" aria-labelledby="login" aria-hidden="false">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">×</button>
									<h3>Login Block</h3>
								</div>
								<div class="modal-body">
									<form class="form-horizontal loginFrm">
										<div class="control-group">
											<input type="text" id="inputEmail" placeholder="Email">
										</div>
										<div class="control-group">
											<input type="password" id="inputPassword"
												placeholder="Password">
										</div>
										<div class="control-group">
											<label class="checkbox"> <input type="checkbox">
												Remember me </label>
										</div>
									</form>
									<button type="submit" class="btn btn-success">Sign in</button>
									<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
								</div>
							</div>   -->
							</li>


							<li class=""><a href="regist.jsp" role="button"
								style="padding-right:0"><span
									class="btn btn-large btn-success">注册</span> </a></li>
						</c:if>
						<!-- 判断是否管理员 -->
						<c:if test="${sessionScope.user.type == 1 }">
							<li class=""><a href="addgood.jsp" role="button"
								style="padding-right:0"><span
									class="btn btn-large btn-success">增加商品</span> </a></li>
						</c:if>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- Header End====================================================================== -->
</body>
</html>