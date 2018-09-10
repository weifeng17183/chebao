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
		<link rel="stylesheet" href="${base}/views/website/css/index.css" />
        
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
					<div class="logo-l"><a href="${base}/website/index"></a></div><!--end left 背景logo-->
					<!--start left logo font-->
					<div class="logo-r"><p class="logo-font">找材宝</p><p>www.zhaocai.top</p></div><!--start right logo font-->
				</div>
				<!--end logo-->
			 	<!--strat header nav-->
				<ul class="header-nav">
					<li class="item"><a class="item-nav" href="${base}/admin/releaseqiugou/releaseqiugou">发布求购</a></li>
					<li class="item"><a class="item-nav" href="Group-purchase.html">团购</a></li>
					<li class="item">商家中心</li>
					<li class="item"><a class="item-nav" href="me-material.html">我的找材</a></li>
					<li class="item"><a class="item-nav activity" href="${base}/website/register">免费注册</a></li>
					<li class="item"><a class="item-nav" href="${base}/website/login">登录</a></li>
				</ul><!--end header nav-->
			</div>
		</header>
		<!--end header-->
		<!--start content-->
		<section class="public-container">
        <article class="nav-box public-container">
             <div class="banner" id="banner" >	
				<a href="#" class="d1" style="background:url(${base}/views/website/img/001.jpg) center no-repeat;"></a>
				<a href="#" class="d1" style="background:url(${base}/views/website/img/002.jpg) center no-repeat;"></a>
				<a href="#" class="d1" style="background:url(${base}/views/website/img/003.jpg) center no-repeat;"></a>
				<div class="d2" id="banner_id">
					<ul>
						<li></li>
						<li></li>
						<li></li>
					</ul>
				</div>
			</div>
		<script type="text/javascript">banner()</script>
		</article>
		
			<article class="nav-box public-container" style="margin-top:10px;">
				<figure class="nav-banner"><a href="${base}/heel/heel?stuffTypeId=1006"><img src="${base}/views/website/img/index---banner_06.jpg" alt="index---banner_06.jpg" /></a></figure>
				<figure class="nav-banner"><a href="${base}/heel/heel?stuffTypeId=1004"><img src="${base}/views/website/img/index---banner_08.jpg" alt="index---banner_08.jpg" /></a></figure>
				<figure class="nav-banner"><a href="${base}/heel/heel?stuffTypeId=1002"><img src="${base}/views/website/img/index---banner_11.jpg" alt="index---banner_11.jpg" /></a></figure>
				<figure class="nav-banner"><a href="${base}/heel/heel?stuffTypeId=1001"><img src="${base}/views/website/img/index---banner_12.jpg" alt="index---banner_12.jpg" /></a></figure>
			</article>
		</section>
		<!--end conent-->
	<!--start footer-->
		<footer class="public-footer">
			<div class="public-container">
				<ul class="panel clearfloat">
					<li class="panel-item panel-item0">品质保证</li>
					<li class="panel-item panel-item1">质量保证</li>
					<li class="panel-item panel-item2">发货保证</li>
					<li class="panel-item panel-item3">海量保证</li>
				</ul>
				<div class="footer-box clearfloat">
					<ul class="footer-nav footer-l">
						<li class="footer-nav-item footer-title">新手指南</li>
						<li class="footer-nav-item footer-title">常见问题</li>
						<li class="footer-nav-item footer-title">采购商服务</li>
						<li class="footer-nav-item footer-title">关于我们</li>
						<li class="footer-nav-item"><a href="${base}/about/help?type=register">如何注册</a></li>
						<li class="footer-nav-item"><a href="${base}/about/help?type=buy">如何购买</a></li>
						<li class="footer-nav-item">搜商家</li>
						<li class="footer-nav-item"><a href="${base}/about/about?type=company">公司简介</a></li>
						<li class="footer-nav-item"><a href="${base}/about/help?type=purchase">如何采购</a></li>
						<li class="footer-nav-item"><a href="${base}/about/help?type=password">忘记密码</a></li>
						<li class="footer-nav-item">找产品</li>
						<li class="footer-nav-item"><a href="${base}/about/about?type=contact">联系我们</a></li>
						<li class="footer-nav-item"><a href="${base}/about/help?type=introduction">买家入门</a></li>
						<li class="footer-nav-item nav-margin"><a href="${base}/about/about?type=course">发展历程</a></li>
					</ul>
					<div class="footer-r">
						<div class="boxs">
							<img src="${base}/views/website/img/code_20.jpg" alt="code_20.jpg" />
							<p class="text">关注公众号</p>
						</div>
						<div class="boxs">
							<img src="${base}/views/website/img/code_22.jpg" alt="code_22.jpg" />
							<p class="text">下载找材宝</p>
						</div>
					</div>
				</div>
				<div class="copyright clearfloat">©2016 zhaocaibao.com 深圳市找材宝有限公司－营业执照注册号：330106000129000－粤ICP备10</div>
				<article class="bulk">
					<figure class="copyright-img"><img src="${base}/views/website/img/copyright_20.jpg" alt="copyright_20.jpg" /></figure>
					<figure class="copyright-img"><img src="${base}/views/website/img/copyright_22.jpg" alt="copyright_22.jpg" /></figure>
					<figure class="copyright-img"><img src="${base}/views/website/img/copyright_24.jpg" alt="copyright_24.jpg" /></figure>
					<figure class="copyright-img"><img src="${base}/views/website/img/copyright_26.jpg" alt="copyright_26.jpg" /></figure>
				</article>
			</div>
		</footer>
		<!--end footer-->

	</body>
</html>

