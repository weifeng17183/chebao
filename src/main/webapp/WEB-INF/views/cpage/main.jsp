<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/head3.jsp" %>
</head>
<frameset id="parentFrameset" rows="60,*" cols="*" frameborder="no" border="0" framespacing="0">
	<frame id="headerFrame" name="headerFrame" src="${base}/admin/page/cheader" frameborder="no" scrolling="no" noresize="noresize" />
	<frameset id="mainFrameset" name="mainFrameset" cols="152,6,*" frameborder="no" border="0" framespacing="0">
		<frame id="menuFrame" name="menuFrame" src="${base}/admin/page/cmenu/order" frameborder="no" noresize="noresize" />
		<frame id="middleFrame" name="middleFrame" src="${base}/admin/page/middle" frameborder="no" noresize="noresize" />
		<frame id="mainFrame" name="mainFrame" src="${base}/admin/page/cindex" frameborder="no" noresize="noresize" />
	</frameset>
</frameset>
<noframes>
	<body>
		noframes
	</body>
</noframes>
</html>