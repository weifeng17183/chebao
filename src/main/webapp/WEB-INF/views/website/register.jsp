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
        
		<!--[if lt IE9]> 
		<script src="js/html5.js"></script>
		<![endif]-->
		<script type="text/javascript">
			$().ready(function() {
		
				var $loginForm = $("#loginForm");
				var $username = $("#mobileNum");
				var $password = $("#password");
				var $confirmPwd = $("#confirmPwd");
				var $verify = $("#verify");
				
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
					if ($confirmPwd.val() == null) {
						$.dialog({
							type : "warn",
							content : "请再次输入您的密码!",
							modal : true,
							autoCloseTime : 3000
						});
						return false;
					}
					if ($confirmPwd.val() != $password.val()) {
						$.dialog({
							type : "warn",
							content : "两次密码不一致，请重新输入!",
							modal : true,
							autoCloseTime : 3000
						});
						return false;
					}
					if ($verify.val() == null) {
						$.dialog({
							type : "warn",
							content : "请输入你的验证码!",
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
				
				
				$("#sendVerifyCode").click(function(){
					var _this=$(this);
					_this.attr("disabled",true);  //发送成功禁用按钮
					//发送ajax请求
					$.ajax({
						url:"${base}/website/sendVerifyCode",
						dataType:"json",  //响应格式
						type:"POST", 
						data:{mobileNum:$("#mobileNum").val()},  //data:这个请求传到后台的参数,以json格式
						success:function(data){
							if(data.success){
								var sec=10;
								var time=window.setInterval(function(){ //定时器
									sec--;
									if(sec > 0){
										_this.text(sec+"秒重新发送");
									}else{
										//去掉定时间器
										window.	clearInterval(time);
										_this.text("重新发行验证码");
										_this.attr("disabled",false);
									}
								},1000);
							}else{
								$.messager.popup(data.msg);
								_this.attr("disabled",false);
							}
						}
					})
				});
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
					<li class="item"><a class="item-nav activity" href="${base}/website/register">免费注册</a></li>
					<li class="item"><a class="item-nav" href="${base}/website/login">登录</a></li>
				</ul><!--end header nav-->
			</div>
		</header>
		<!--end header-->
		<!--start content-->
		<div class="bg">
			<div class="public-container">
				<span class="public-container bg-nav">主页>注册</span>
			</div>
		</div>
		<!--end bg-->
		<!--start content-->
		<section>
			<form action="${base}/website/registerinfo" class="form-box" method="post" id="loginForm">
				<h3 class="title-h3">注册</h3>
				<div class="form-plane">
					<div class="input-item"><label>手机号码</label><input id="mobileNum" class="phone" name="mobileNum" type="text" placeholder="请输入手机号码"/></div>
					<div class="input-item"><label>短信验证码</label><input id="verify"  class="verify" type="text" placeholder="请输入短信验证码"/><a id="sendVerifyCode" href="javascript:;" class="verify-btn">获取验证码</a></div>
					<div class="input-item"><label>创建密码</label><input id="password" class="news-wd" name="password" type="password" placeholder="请输入密码"/></div>
					<div class="input-item"><label>确认密码</label><input id="confirmPwd" type="password" name="confirmPwd" class="affirm-pwd" placeholder="请再次输入密码"/></div>
					<div class="input-item-chek"><input type="checkbox" /><label>已阅读并同意</label><a href="#" class="manual">《找材宝用户协议》</a></div>
					<div class="logo-box"><input type="submit" class="btn-logo" value="立即注册" hidefocus /></div>
					<a href="#" class="number">已有账号?</a>
					<a href="login.html" class="register">登录</a>
				</div>
			</form>
			<!--用户协议-->
			<div class="agreement public-container">
				 <div class="close-icon">
				 	<span class="">找材宝用户协议</span>
				 	<img class="close" src="img/addtu.png">
				 </div>
				<textarea class="deal">用户协议暂无</textarea>
			</div><!--用户协议-->
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

