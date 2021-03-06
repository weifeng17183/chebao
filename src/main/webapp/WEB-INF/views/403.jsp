<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/common/head.jsp"%>
<script type="text/javascript">
	$().ready(function() {
		function redirectUrl() {
			var redirectUrl = "${redirectUrl}";
			if (redirectUrl != null && redirectUrl != "") {
				window.location.href = "${base}/${redirectUrl}";
			} else {
				window.history.back();
			}
		}
		var message = "${message}";
		if (message == null || message == "") {
			message = "对不起，您没有权限请求此连接！";
		}
		$.dialog({
			type : "error",
			title : "权限提示",
			content : message,
			ok : "确定",
			okCallback : redirectUrl,
			cancelCallback : redirectUrl,
			width : 380,
			modal : true
		});
	});
</script>
</head>
<body class="error">
</body>
</html>