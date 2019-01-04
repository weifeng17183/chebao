<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/common/head3.jsp"%>
<script type="text/javascript">
	$().ready(function() {

		var $loginForm = $("#loginForm");
		var $username = $("#username");
		var $password = $("#password");
		/* var $captcha = $("#captcha");
		var $captchaImage = $("#captchaImage");
		 */
		var $isRememberUsername = $("#isRememberUsername");

		// 判断"记住用户名"功能是否默认选中,并自动填充登录用户名
		if (getCookie("adminUsername") != null) {
			$isRememberUsername.attr("checked", true);
			$username.val(getCookie("adminUsername"));
			$password.focus();
		} else {
			$isRememberUsername.attr("checked", false);
			$username.focus();
		}
		
		

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
			/* 	if ($captcha.val() == "") {
					$.dialog({
						type : "warn",
						content : "请输入您的验证码!",
						modal : true,
						autoCloseTime : 3000
					});
					return false;
				}
			 */
			if ($isRememberUsername.attr("checked") == true) {
				var expires = new Date();
				expires.setTime(expires.getTime() + 1000 * 60 * 60 * 24 * 7);
				setCookie("adminUsername", $username.val(), expires, "");
			} else {
				removeCookie("adminUsername");
			}
			 
		});

		/* // 刷新验证码
		$captchaImage.click(function() {
			var timestamp = (new Date()).valueOf();
			var imageSrc = $captchaImage.attr("src");
			if (imageSrc.indexOf("?") >= 0) {
				imageSrc = imageSrc.substring(0, imageSrc.indexOf("?"));
			}
			imageSrc = imageSrc + "?timestamp=" + timestamp;
			$captchaImage.attr("src", imageSrc);
		}); */
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
<body class="login">
	<script type="text/javascript">
		// 登录页面若在框架内，则跳出框架
		if (self != top) {
			top.location = self.location;
		};
	</script>
	<div class="blank"></div>
	<div class="blank"></div>
	<div class="blank"></div>
	<div class="body">
		<form id="loginForm" action="${base}/clogin" method="post">
			<table class="loginTable">
				<tr>
					<td rowspan="3"><img
						src="${base}/template/admin/images/clogin_logo.gif"
						alt="车宝管家车厂端" /></td>
					<th>用户名:</th>
					<td><input type="text" id="username" name="userName" class="formText" value="${admin.userName}" /></td>
				</tr>
				<tr>
					<th>密&nbsp;&nbsp;&nbsp;码:</th>
					<td><input type="password" id="password" name="password" class="formText" value="${admin.password}" /></td>
				</tr>
				<tr></tr>
				<tr>
					<td>&nbsp;</td>
					<th>&nbsp;</th>
					<td><input type="button" class="homeButton" value=""  hidefocus /><input type="submit" class="submitButton" value="登 录" hidefocus /></td>
				</tr>
			</table>
			<div class="powered">粤ICP备 17159272号版权所有 ©
			2018-2020 广州恒昌行汽车服务有限公司</div>
		</form>
	</div>
</body>
</html>