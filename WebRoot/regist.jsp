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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>注册</title>
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
<SCRIPT type=text/javascript src="js/jquery-1.8.3.js"></SCRIPT>


</head>
<body>
	<!-- 静态包含头部 -->
	<%@ include file="head.jsp"%>
	<div id="mainBody">
		<div class="container">
			<div class="row">
				<!-- Sidebar ================================================== -->

				<!-- Sidebar end=============================================== -->
				<div class="span9" style="  width: 100%; ">
					<ul class="breadcrumb">
						<li><a href="index.jsp">主页</a> <span class="divider">/</span>
						</li>
						<li class="active">注册</li>
					</ul>
					<h3>注册</h3>
					<div class="well">
						<!--
	<div class="alert alert-info fade in">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>Lorem Ipsum is simply dummy</strong> text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s
	 </div>
	<div class="alert fade in">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>Lorem Ipsum is simply dummy</strong> text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s
	 </div>
	 <div class="alert alert-block alert-error fade in">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>Lorem Ipsum is simply</strong> dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s
	 </div> -->
						<form id="from" class="form-horizontal"
							action="user_regist.action">
							<h4>个人信息 </h4>

							<div class="control-group">
								<label class="control-label" for="inputFname1">用户名：<sup>*</sup>
								</label>
								<div class="controls">
									<input type="text" id="username" name="user.username"
										placeholder="Name">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="pwd">密码：<sup>*</sup> </label>
								<div class="controls">
									<input type="password" id="pwd" name="user.userpwd"
										placeholder="password">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="pwd2">确认密码：<sup>*</sup>
								</label>
								<div class="controls">
									<input type="password" id="pwd2" name="pwd2"
										placeholder="password">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="realname">真实名字：<sup>*</sup>
								</label>
								<div class="controls">
									<input type="text" id="realname" name="user.realname"
										placeholder="realname">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="input_email">注册类型：<sup>*</sup>
								</label>
								<div class="controls">
									<input type="radio" id="input_type" name="user.type" value="0" checked="checked">个人
									<input type="radio" id="input_type" name="user.type" value="1">商家
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputPassword1">电话号码：<sup>*</sup>
								</label>
								<div class="controls">
									<input type="text" id="inputPassword1" name="user.phone"
										placeholder="phone">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="input_email">邮箱：<sup>*</sup>
								</label>
								<div class="controls">
									<input type="text" id="input_email" name="user.email"
										placeholder="Email">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="city">所在城市：<sup>*</sup>
								</label>
								<div class="controls">
									<input type="text" id="city" name="user.address"
										placeholder="city">
								</div>
							</div>
							<!-- <div class="control-group">
								<label class="control-label" for="yan">验证码：<sup>*</sup>
								</label>
								<div class="controls">
									<input type="text" id="yan" name="yan" placeholder="validate">
								</div>
							</div> -->
							<div class="control-group">
								<label class="control-label" style="color: red;"> ${requestScope.msg} </label>
							</div>
							<div class="control-group">
								<div class="controls">
									<input class="btn btn-large btn-success" type="submit"
										value="Register">
								</div>
							</div>
						</form>

					</div>

				</div>
			</div>
		</div>
	</div>
	<!-- MainBody End ============================= -->
	<!-- 静态包含头部 -->
	<%@ include file="footer.jsp"%>
	<span id="themesBtn"></span>

</body>
<SCRIPT type=text/javascript src="js/jquery.validate.js"></SCRIPT>
<SCRIPT language=javascript type=text/javascript>
	$(function() {
		$("#from").validate({
			rules : {
				'user.username' : {
					required : true,
				},
				'user.userpwd' : {
					required : true,
					minlength : 5
				},
				pwd2 : {
					required : true,
					minlength : 5,
					equalTo : "#pwd"
				},
				'user.email' : {
					required : true,
					email : true
				},
				'user.phone' : {
					required : true,
					rangelength : [ 13, 13 ]
				},
				'user.address' : {
					required : true,
				},
				'user.realname' : {
					required : true,
				}

			},
			messages : {
				'user.username' : {
					required : "请输入用户名",
				},
				'user.userpwd' : {
					required : "请输入密码",
					minlength : "密码不能小于{5}个字符"
				},
				pwd2 : {
					required : "请输入确认密码",
					minlength : "确认密码不能小于5个字符",
					equalTo : "两次输入密码不一致不一致"
				},
				'user.email' : {
					required : "请输入Email地址",
					email : "请输入正确的email地址"
				},
				'user.phone' : {
					required : "请输入手机号码",
					rangelength : "长度为13位"
				},
				'user.address' : {
					required : "请输入所在城市",
				},
				'user.realname' : {
					required : "请输入真是名字",
				}

			}
		});

	});
</SCRIPT>
</html>