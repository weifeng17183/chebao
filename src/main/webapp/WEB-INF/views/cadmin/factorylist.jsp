<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/common/head3.jsp"%>
</head>
<body class="list">
	<div id="validateErrorContainer" class="validateErrorContainer">
		<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
		<ul></ul>
	</div>
	<div class="bar">文员列表&nbsp;总记录数: ${page.totalCount}
		(共${page.totalPage}页)</div>
	<div class="body" style="position: relative;">
		<div class="listBar">
			<form id="listForm" action="${base}/admin/cadmin/factorylist" method="post"
				style="display: inline">
				<label>文员名称: </label> <input type="text" name="userName"
					value="${searchBean.userName}" /> <input type="button"
					id="searchButton" class="formButton" value="搜 索" hidefocus />
				&nbsp;&nbsp; <label>每页显示: </label> <select name="pageSize"
					id="pageSize">
					<option value="10" <c:if test="${page.pageSize == 10}"> selected</c:if>>
						10</option>
					<option value="20" <c:if test="${page.pageSize == 20}"> selected</c:if>>
						20</option>
					<option value="50" <c:if test="${page.pageSize == 50}"> selected</c:if>>
						50</option>
					<option value="100"
						<c:if test="${page.pageSize == 100}"> selected</c:if>>100</option>
				</select>
		</div>

		<table id="listTable" class="listTable">
			<tr>
				<th><span>文员编号</span></th>
				<th><span>文员名称</span></th>
				<th><span>所属车厂编号</span></th>
				<th><span>所属车厂名称</span></th>
				<th><span>文员状态状态</span></th>
				<th><span>操作</span></th>
			</tr>
			<c:forEach items="${adminList}" var="admin">
				<tr>
					<td>${admin.adminId}</td>
					<td>${admin.userName}</td>
					<td>${admin.factory.id}</td>
					<td>${admin.factory.factoryName}</td>
					<td><c:choose>
							<c:when test="${admin.adminStatus ==1 }">
								<span class="green">启用</span>
							</c:when>
							<c:otherwise>
								<span class="red">禁用</span>
							</c:otherwise>
						</c:choose></td>
					<td><shiro:hasPermission name="admin:update">
							<a href="${base}/admin/cadmin/editfactory?adminId=${admin.adminId}"
								title="[编辑]">[编辑]</a>
						</shiro:hasPermission> <c:if test="${admin.adminId!=1 }">
							<shiro:hasPermission name="admin:delete">
								<a onclick="if(confirm('确定删除此文员吗?')==false)return false;"
									href="${base}/admin/cadmin/delete?adminId=${admin.adminId}"
									title="[删除]">[删除]</a>
							</shiro:hasPermission>
						</c:if></td>
				</tr>
			</c:forEach>
		</table>
		<c:choose>
			<c:when test="${adminList.size()>0}">
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