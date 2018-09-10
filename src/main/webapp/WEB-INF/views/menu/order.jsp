<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/common/head.jsp"%>
</head>
<body class="menu" style="width: 130px">
	<div>
		<shiro:hasAnyPermission name="order:list,order:relist">
			<dl>
				<dt>
					<span>订单管理&nbsp;</span>
				</dt>
				<shiro:hasPermission name="order:relist">
					<dd>
						<a href="${base}/admin/order/list" target="mainFrame">订单列表</a>
					</dd>
				</shiro:hasPermission>
			</dl>
		</shiro:hasAnyPermission>
		<dl>
			<dt>
				<span>财务管理&nbsp;</span>
			</dt>
			<dd>
				<a href="${base}/admin/order/settlement" target="mainFrame">已付款订单结算</a>
			</dd>
		</dl>
		<shiro:hasAnyPermission name="product:list,product:add">
			<dl>
				<dt>
					<span>项目管理&nbsp;</span>
				</dt>
				<shiro:hasPermission name="product:list">
					<dd>
						<a href="${base}/admin/product/list" target="mainFrame">项目列表</a>
					</dd>
				</shiro:hasPermission>
				<shiro:hasPermission name="product:add">
					<dd>
						<a href="${base}/admin/product/add" target="mainFrame">添加项目</a>
					</dd>
				</shiro:hasPermission>
			</dl>
		</shiro:hasAnyPermission>
		<shiro:hasAnyPermission
			name="stuffType:list,stuffType:add,secondType:list,secondType:add">
			<dl>
				<dt>
					<span>项目类别管理&nbsp;</span>
				</dt>
				<shiro:hasPermission name="stuffType:list">
					<dd>
						<a href="${base}/admin/stuffType/list" target="mainFrame">大类列表</a>
					</dd>
				</shiro:hasPermission>
				<shiro:hasPermission name="stuffType:add">
					<dd>
						<a href="${base}/admin/stuffType/add" target="mainFrame">添加大类</a>
					</dd>
				</shiro:hasPermission>
				<shiro:hasPermission name="secondType:list">
					<dd>
						<a href="${base}/admin/secondType/list" target="mainFrame">小类列表</a>
					</dd>
				</shiro:hasPermission>
				<shiro:hasPermission name="secondType:add">
					<dd>
						<a href="${base}/admin/secondType/add" target="mainFrame">添加小类</a>
					</dd>
				</shiro:hasPermission>
			</dl>
		</shiro:hasAnyPermission>
		<shiro:hasAnyPermission name="factory:list">
			<dl>
				<dt>
					<span>车厂管理&nbsp;</span>
				</dt>
				<shiro:hasPermission name="factory:list">
					<dd>
						<a href="${base}/admin/factory/list" target="mainFrame">车厂列表</a>
					</dd>
				</shiro:hasPermission>
				<dd>
					<a href="${base}/admin/factory/add" target="mainFrame">添加车厂</a>
				</dd>
			</dl>
		</shiro:hasAnyPermission>
		<shiro:hasAnyPermission name="user:list,user:add">
			<dl>
				<dt>
					<span>客户管理&nbsp;</span>
				</dt>
				<shiro:hasPermission name="user:list">
					<dd>
						<a href="${base}/admin/user/list?userType=0" target="mainFrame">客户列表</a>
					</dd>
				</shiro:hasPermission>
				<shiro:hasPermission name="user:add">
					<dd>
						<a href="${base}/admin/user/add" target="mainFrame">添加客户</a>
					</dd>
				</shiro:hasPermission>
			</dl>
		</shiro:hasAnyPermission>
		<shiro:hasAnyPermission name="conductor:list,conductor:add">
			<dl>
				<dt>
					<span>接车员管理&nbsp;</span>
				</dt>
				<shiro:hasPermission name="conductor:list">
					<dd>
						<a href="${base}/admin/user/conductorList?userType=1"
							target="mainFrame">接车员列表</a>
					</dd>
				</shiro:hasPermission>
				<shiro:hasPermission name="conductor:add">
					<dd>
						<a href="${base}/admin/user/addConductor" target="mainFrame">添加接车员</a>
					</dd>
				</shiro:hasPermission>
			</dl>
		</shiro:hasAnyPermission>
		<shiro:hasAnyPermission name="rate:edit">
			<dl>
				<dt>
					<span>分成管理&nbsp;</span>
				</dt>
				<dd>
					<a href="${base}/admin/rate/enter" target="mainFrame">比例修改</a>
				</dd>
			</dl>
		</shiro:hasAnyPermission>
		<shiro:hasAnyPermission
			name="admin:list,admin:add,role:list,permission:list">
			<dl>
				<dt>
					<span>管理员管理&nbsp;</span>
				</dt>
				<shiro:hasPermission name="admin:list">
					<dd>
						<a href="${base}/admin/admin/list" target="mainFrame">平台管理员列表</a>
					</dd>
				</shiro:hasPermission>
				<shiro:hasPermission name="admin:add">
					<dd>
						<a href="${base}/admin/admin/add" target="mainFrame">添加平台管理员</a>
					</dd>
				</shiro:hasPermission>
				<shiro:hasPermission name="admin:list">
					<dd>
						<a href="${base}/admin/admin/factorylist" target="mainFrame">车厂管理员列表</a>
					</dd>
				</shiro:hasPermission>
				<shiro:hasPermission name="admin:add">
					<dd>
						<a href="${base}/admin/admin/addfactory" target="mainFrame">添加车厂管理员</a>
					</dd>
				</shiro:hasPermission>
			<%-- 	<shiro:hasPermission name="role:list">
					<dd>
						<a href="${base}/admin/role/list" target="mainFrame">角色管理</a>
					</dd>
				</shiro:hasPermission>
				<shiro:hasPermission name="permission:list">
					<dd>
						<a href="${base}/admin/permission/list" target="mainFrame">权限管理</a>
					</dd>
				</shiro:hasPermission> --%>
			</dl>
		</shiro:hasAnyPermission>
	</div>
</body>
</html>