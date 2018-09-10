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
			message = "您的操作已成功!"
		}
		$.dialog({
			type : "success",
			title : "操作提示",
			content : '<font size=15px;>'+message+'</font>',
			ok : "确定",
			okCallback : redirectUrl,
			cancelCallback : redirectUrl,
			width : 680,
			modal : true
		});

	});
</script>
</head>
<body class="success">
</body>
</html>