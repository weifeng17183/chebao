<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
	<head>
		<%@include file="/common/head.jsp"%>
		<meta charset="UTF-8">
		<title>登录</title>
		<!-- Link Swiper's CSS -->
		<link rel="stylesheet" href="${base}/views/website/css/common.css?j=0" />
		<link rel="stylesheet" href="${base}/views/website/css/reset.css" />
		<!--[if lt IE9]> 
		<script src="js/html5.js"></script>
		<![endif]-->
		<script type="text/javascript">
			$().ready(function() {
		
				var $loginForm = $("#loginForm");
				var $username = $("#mobileNum");
				var $password = $("#password");
				/* var $captcha = $("#captcha");
				var $captchaImage = $("#captchaImage");
				 */
		
				// 提交表单验证,记住登录用户名
				$loginForm.submit(function() {
					if ($username.val() == "") {
						$.dialog({
							type : "warn",
							content : "请输入您的用户名!",
							modal : true,
							autoCloseTime : 3000
						});
						return false;
					}
					if ($password.val() == "") {
						$.dialog({
							type : "warn",
							content : "请输入您的密码!",
							modal : true,
							autoCloseTime : 3000
						});
						return false;
					}
				});
		
				var message = "${message}";
				if (message != null && message!="") {
					$.dialog({
						type : "error",
						content : message,
						modal : true,
						autoCloseTime : 3000
					});
				}
			});
		</script>
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
					<li class="item"><a class="item-nav activity" href="register.html">免费注册</a></li>
					<li class="item"><a class="item-nav" href="login.html">登录</a></li>
				</ul><!--end header nav-->
			</div>
		</header>
		<!--end header-->
		<!--start content-->
		<section>
			<form id="loginForm" action="${base}/website/login" class="form-box" method="post">
				<h3 class="title-h3">登录</h3>
				<div class="form-plane">
					<div class="input-login"><input class="phonetext" id="mobileNum" name="mobileNum" type="text" placeholder="手机号码"/></div>
					<div class="input-login"><input class="pwdtext" id="password" name="password" type="password" placeholder="用户密码"/></div>
					<div class="input-login-chek"><input type="checkbox" /><label>记住密码</label><a href="find-pwd.html" class="forgetLog">忘记密码</a></div>
					<div class="logo-box"><input type="submit" class="btn-logo" value="登 录" hidefocus /></div>
					<a href="#" class="number">没有账号?</a>
					<a href="register.html" class="register">立即注册</a>
				</div>
			</form>
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

