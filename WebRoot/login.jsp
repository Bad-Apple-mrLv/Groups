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
<title>团购</title>
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
	<div id="mainBody">
		<div class="container">
			<div class="row">
				<!-- Sidebar ================================================== -->

				<!-- Sidebar end=============================================== -->
				<div class="span9" style="width: 100%;">
					<ul class="breadcrumb">
						<li><a href="index.jsp">主页</a> <span class="divider">/</span>
						</li>
						<li class="active">登录</li>
					</ul>
					<h3>登录</h3>
					<hr class="soft">

					<div class="row">
						<div class="span4"></div>
						<div class="span1">&nbsp;</div>
						<div class="span4" style="margin-left: inherit;">
							<div class="well">
								<h5>登录框</h5>
								<form action="user_login.action">
									<div class="control-group">
										<label class="control-label" for="userName">用户名</label>
										<div class="controls">
											<input class="span3" type="text" id="inputname" name="username"
												placeholder="name">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="inputPassword1">密码</label>
										<div class="controls">
											<input type="password" class="span3" name="userpwd" id="inputPassword1"
												placeholder="Password">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" style="color: red;">${requestScope.msg }</label>
									</div>
									<div class="control-group">
										<div class="controls">
											<button type="submit" class="btn">登录</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
	<!-- MainBody End ============================= -->
	<!-- 静态包含头部 -->
	<%@ include file="footer.jsp"%>

</body>
</html>