<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/common/head.jsp"%>
</head>
<body class="list">
	<div id="validateErrorContainer" class="validateErrorContainer">
		<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
		<ul></ul>
	</div>
	<div class="bar">角色列表&nbsp;总记录数: ${page.totalCount}
		(共${page.totalPage}页)</div>
	<form id="listForm" action="${base}/admin/role/list" method="post"
		style="display: inline">
		<div class="body" style="position: relative;">
			<div class="listBar">
				<a href="${base}/admin/role/add" class="formButton">新增角色</a>
			</div>

			<table id="listTable" class="listTable">
				<tr>
					<th><span>角色编号</span></th>
					<th><span>角色编码</span></th>
					<th><span>角色名称</span></th>
					<th><span>角色描述</span></th>
					<th><span>操作</span></th>
				</tr>
				<c:forEach items="${list}" var="role">
					<tr>
						<td>${role.roleId}</td>
						<td>${role.roleName}</td>
						<td>${role.roleSign}</td>
						<td>${role.roleDesc}</td>
						<td><a href="${base}/admin/role/edit?id=${role.roleId}">[修改]</a>
							<c:if test="${role.roleId!=1 }">
								<a onclick="if(confirm('确定删除此角色吗?')==false)return false;"
									href="${base}/admin/role/delete?id=${role.roleId}" title="[删除]">[删除]</a>
							</c:if></td>
					</tr>
				</c:forEach>
			</table>
			<c:choose>
				<c:when test="${list.size()>0}">
					<div class="pagerBar">
						<div class="pager">
							<%@include file="/WEB-INF/views/pager.jsp"%>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="noRecord">没有找到任何记录!</div>
				</c:otherwise>
			</c:choose>
	</form>
	</div>
</body>
</html>