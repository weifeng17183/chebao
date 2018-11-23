<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/common/head3.jsp"%>
</head>
<body class="menu" style="width: 130px">
	<div>
		<dl>
			<dt>
				<span>订单管理&nbsp;</span>
			</dt>
			<dd>
				<a href="${base}/admin/corder/list" target="mainFrame">订单列表</a>
			</dd>
		</dl>
		<dl>
			<dt>
				<span>财务管理&nbsp;</span>
			</dt>
			<dd>
				<a href="${base}/admin/corder/settlement" target="mainFrame">已付款订单结算</a>
			</dd>
		</dl>
		<dl>
			<dt>
				<span>客户管理&nbsp;</span>
			</dt>
			<dd>
				<a href="${base}/admin/cuser/list?userType=0" target="mainFrame">客户列表</a>
			</dd>
			<dd>
				<a href="${base}/admin/cuser/add" target="mainFrame">添加客户</a>
			</dd>
		</dl>
		<shiro:hasAnyPermission name="admin:list,admin:add">
			<dl>
				<dt>
					<span>文员管理&nbsp;</span>
				</dt>
				<shiro:hasPermission name="admin:list">
					<dd>
						<a href="${base}/admin/cadmin/factorylist" target="mainFrame">文员列表</a>
					</dd>
				</shiro:hasPermission>
				<shiro:hasPermission name="admin:add">
					<dd>
						<a href="${base}/admin/cadmin/addfactory" target="mainFrame">添加文员</a>
					</dd>
				</shiro:hasPermission>
			</dl>
		</shiro:hasAnyPermission>
	</div>
</body>
</html>