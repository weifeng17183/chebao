<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/common/head.jsp"%>
<script type="text/javascript">
	$().ready(function() {

		var $menuItem = $("#menu .menuItem");
		var $previousMenuItem;

		$menuItem.click(function() {
			var $this = $(this);
			if ($previousMenuItem != null) {
				$previousMenuItem.removeClass("current");
			}
			$previousMenuItem = $this;
			$this.addClass("current");
		})

	})
</script>
</head>
<body class="header">
	<div class="body">
		<div class="bodyLeft">
			<div class="logo"></div>
		</div>
		<div class="bodyRight">
			<div class="link">
				<span class="welcome"> <strong><shiro:principal></shiro:principal></strong>&nbsp;您好!&nbsp;
				</span> <a href="${base}/admin/page/index" target="mainFrame">后台首页</a> | <a
					class="profile"
					href="${base}/admin/admin/reloadPass?adminId=${loginAdmin.adminId}"
					target="mainFrame">个人资料</a> | <a class="logout"
					href="${base}/logout" target="_top">退出</a>
			</div>
			<div id="menu" class="menu">
				<ul>
					<%-- <li class="menuItem"><a href="${base}/admin/page/menu/order"
						target="menuFrame" hidefocus>接车管理</a></li>
					<li class="menuItem"><a href="${base}/admin/page/menu/user"
						target="menuFrame" hidefocus>客户管理</a></li>
					<shiro:hasAnyPermission
						name="product:list,product:add,stuffType:list,stuffType:add,secondType:list,secondType:add">
						<li class="menuItem"><a
							href="${base}/admin/page/menu/product" target="menuFrame"
							hidefocus>项目管理</a></li>
					</shiro:hasAnyPermission>
					<li class="menuItem"><a href="${base}/admin/page/menu/finance"
						target="menuFrame" hidefocus>财务管理</a></li>
					<shiro:hasAnyPermission
						name="admin:list,admin:add,role:list,permission:list">
						<li class="menuItem"><a href="${base}/admin/page/menu/system"
							target="menuFrame" hidefocus>系统管理</a></li>
					</shiro:hasAnyPermission> --%>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>