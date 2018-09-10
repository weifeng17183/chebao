<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
	<head>
		<%@include file="/common/head.jsp"%>
		<meta charset="UTF-8">
		<title>首页</title>
		<!-- Link Swiper's CSS -->
		<link rel="stylesheet" href="${base}/views/website/css/common.css?j=0" />
		<link rel="stylesheet" href="${base}/views/website/css/reset.css" />
		<link rel="stylesheet" href="${base}/views/website/css/help.css" />
        
		<!--[if lt IE9]> 
		<script src="js/html5.js"></script>
		<![endif]-->
	</head>
	<body>
		<!--start header-->
		<header class="public-header">
			<div class="public-container clearfloat">
				<!--start logo-->
				<div class="header-logo">
					<!--strat left 背景logo-->
					<div class="logo-l"><a href="index.html"></a></div><!--end left 背景logo-->
					<!--start left logo font-->
					<div class="logo-r"><p class="logo-font">找材宝</p><p>www.zhaocai.top</p></div><!--start right logo font-->
				</div>
				<!--end logo-->
				<!--strat header nav-->
				<ul class="header-nav">
					<li class="item"><a class="item-nav" href="${base}/website/build">发布求购</a></li>
					<li class="item"><a class="item-nav" href="${base}/website/build">团购</a></li>
					<li class="item">商家中心</li>
					<li class="item"><a class="item-nav" href="${base}/website/build">我的找材</a></li>
					<li class="item"><a class="item-nav activity" href="${base}/website/build">免费注册</a></li>
					<li class="item"><a class="item-nav" href="${base}/website/build">登录</a></li>
				</ul><!--end header nav-->
			</div>
		</header>
		<!--end header-->
		<!--start bg-->
		<div class="bg">
			<div class="public-container">
				<span class="public-container bg-nav">主页>关于我们</span>
			</div>
		</div>
		<!--end bg-->
		<!--start content-->
		<div class="helpcontent">
		<img src="${base}/views/website/img/build.jpg">
        </div>
		<!--end conent-->
	<!--start footer-->
		
		<!--end footer-->
	</body>
</html>

