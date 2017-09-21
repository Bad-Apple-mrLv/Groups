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
<title>产品详细</title>
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
				<div class="span9" style="width: 100%;">
					<ul class="breadcrumb">
						<li><a href="index.jsp">主页</a> <span class="divider">/</span>
						</li>
						 
						<li class="active">产品明细</li>
					</ul>
					<div class="row">
						<div id="gallery" class="span3">
							<a href="${goods.goodspic}"
								title=""> <img
								src="${goods.goodspic}" style="width:100%"
								alt="Fujifilm FinePix S2950 Digital Camera"> </a>



						</div>
						<div class="span6">
							<h3>${goods.name}</h3>

							<hr class="soft">
							
								<div class="control-group">
									<label class="control-label"><span>$${goods.price}</span> </label>
									<div class="controls">
										<!-- <input type="number" class="span1" placeholder="Qty."> -->
										<span id="span_id" style="display: none;">${goods.id}</span>
										<button id="addshopcar"
											class="btn btn-large btn-primary pull-right">
											加入购物车<i class=" icon-shopping-cart"></i>
										</button>
										
									</div>
								</div>
								<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
										<script type="text/javascript">
										$(function(){
											$("#addshopcar").click(function(){
													 $.ajax({
													  	url:"goods_addshopcar.action",
														data:"gid="+$("#span_id").html()+"&s="+new Date().getTime(),
													  success:function(result){
														  var results=eval("("+result+")");
														  if(results.head=="ok"){
															alert(results.body);  
														  }
													  }
												  });
											});
											
										});
										
										</script>
							
							<p>描述：${goods.details }</p>
							<hr class="soft">
						</div>

					</div>
				</div>
				<!-- <div class="tab-pane fade active in" id="home">
					<h4>Product Information</h4>
					<table class="table table-bordered">
						<tbody>
							<tr class="techSpecRow">
								<th colspan="2">Product Details</th>
							</tr>
							<tr class="techSpecRow">
								<td class="techSpecTD1">Brand:</td>
								<td class="techSpecTD2">Fujifilm</td>
							</tr>
							<tr class="techSpecRow">
								<td class="techSpecTD1">Model:</td>
								<td class="techSpecTD2">FinePix S2950HD</td>
							</tr>
							<tr class="techSpecRow">
								<td class="techSpecTD1">Released on:</td>
								<td class="techSpecTD2">2011-01-28</td>
							</tr>
							<tr class="techSpecRow">
								<td class="techSpecTD1">Dimensions:</td>
								<td class="techSpecTD2">5.50" h x 5.50" w x 2.00" l, .75
									pounds</td>
							</tr>
							<tr class="techSpecRow">
								<td class="techSpecTD1">Display size:</td>
								<td class="techSpecTD2">3</td>
							</tr>
						</tbody>
					</table>

				</div> -->
			</div>
		</div>
	</div>
	<!-- MainBody End ============================= -->
	<!-- 静态包含头部 -->
	<%@ include file="footer.jsp"%>

</body>
</html>