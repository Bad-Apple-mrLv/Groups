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
						<li class="active">${act=="upd"?'更新商品':'增加商品'}</li>
					</ul>
					<h3>${act=="upd"?'更新商品':'增加商品'}</h3>
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
						<form id="from" class="form-horizontal" method="post"
							enctype="multipart/form-data" action="AddGood">
							<c:if test="${act == 'upd'}">
								<input type="hidden" name="goodsid" value="${goods.id}">
								<input type="hidden" name="act" value="upd">
							</c:if>
							<h4>商品信息</h4>


							<div class="control-group">
								<label class="control-label">商品类型 <sup>*</sup> </label>
								<div class="controls">
									<select class="span1" name="type">
										<c:forEach items="${applicationScope.types }"
											var="goodsType">
											<option value="${goodsType.id }" ${goodsType.id == goods.type?'selected="selected"':''}  >${goodsType.typename}</option>

										</c:forEach>
									</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputFname1">商品名：<sup>*</sup>
								</label>
								<div class="controls">
									<input type="text" id="username" name="name" placeholder="Name" value="${goods.name}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="input_price">价钱：<sup>*</sup>
								</label>
								<div class="controls">
									<input type="text" id="input_price" name="price" placeholder="price" value="${goods.price}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="input_details">商品描述：<sup>*</sup>
								</label>
								<div class="controls">
									<input type="text" id="input_details" name="details" placeholder="details" value="${goods.details}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="tuangoucount">库存：<sup>*</sup>
								</label>
								<div class="controls">
									<input type="text" id="tuangoucount" name="tuangoucount" placeholder="tuangoucount" value="${goods.tuangoucount}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="picture">商品图片：<sup>*</sup>
								</label>
								<div class="controls">
									<input type="file" id="picture" name="goodspic" placeholder="picture" value="${goods.goodspic}">
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
								<label class="control-label" style="color: red;">
									${requestScope.msg} </label>
							</div>
							<div class="control-group">
								<div class="controls">
									<input class="btn btn-large btn-success" type="submit"
										value="提交">
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
	
</SCRIPT>
</html>