<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/common/head3.jsp"%>
<script>
	function windowHeight() {
		var de = document.documentElement;
		return self.innerHeight || (de && de.clientHeight)
				|| document.body.clientHeight;
	}
	window.onload = window.onresize = function() {
		var wh = windowHeight();
		document.getElementById("master_c").style.height = (wh
				- document.getElementById("header").offsetHeight
				- document.getElementById("footer").offsetHeight - 50)
				+ "px";
	}
</script>

</head>
<body class="index">
	<div class="body">


		<div id="master" class="master">
			<div id="header"></div>
			<div id="master_c" class="master_c">
				<div class="master_wel">
					<img src="${base}/template/admin/images/logo.png" alt="车宝管家" /> <span
						class="master_txt">欢迎使用车宝管家汽修中心系统！</span>
				</div>
			</div>
			<div id="footer"></div>

		</div>
		<p class="copyright" style="float: center;">粤ICP备 17159272号版权所有 ©
			2018-2020 广州恒昌行汽车服务有限公司</p>
	</div>
</body>
</html>