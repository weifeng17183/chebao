<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/common/head.jsp" %>
<script type="text/javascript">
$().ready(function() {

	var $main = $("#main");

	$main.click( function() {
		var mainFrameset = window.parent.window.document.getElementById("mainFrameset");
		if(mainFrameset.cols == "130,6,*") {
			mainFrameset.cols = "0,6,*";
			$main.removeClass("leftArrow");
			$main.addClass("rightArrow");
		} else {
			mainFrameset.cols = "130,6,*";
			$main.removeClass("rightArrow");
			$main.addClass("leftArrow");
		}
	})

})
</script>
<style type="text/css">
</style>
</head>
<body class="middle">
	<div id="main" class="main leftArrow"></div>
</body>
</html>