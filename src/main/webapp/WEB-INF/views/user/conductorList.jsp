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
	<div class="bar">接车员列表&nbsp;总记录数:${page.totalCount}
		(共${page.totalPage}页)</div>
	<div class="body" style="position: relative;">
		<div class="listBar">
			<form id="listForm" action="${base}/admin/user/conductorList?userType=1" method="post"
				style="display: inline">
				<label>关键字: </label> <input type="text" name="name" size="20"
					value="${users.name}" placeholder="手机号/姓名/备注" /> <input
					type="button" id="searchButton" class="formButton" value="搜 索"
					hidefocus /> &nbsp;&nbsp; <label>每页显示: </label> <select
					name="pageSize" id="pageSize">
					<option value="15" <c:if test="${page.pageSize == 15}"> selected</c:if>>
						15</option>
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
				<th><span>编号</span></th>
				<th><span>接车员名称</span></th>
				<th><span>手机号码</span></th>
				<th><span>注册时间</span></th>
				<!-- <th><span>车厂</span></th -->>
				<th><span>状态</span></th>
				<th><span>备注</span></th>
				<th><span>操作</span></th>
			</tr>
			<c:forEach items="${userList}" var="user">
				<tr>
					<td>${user.userId}</td>
					<td>${user.name}</td>
					<td>${user.mobileNum}</td>
					<td><span> <fmt:formatDate value="${user.registerDate}"
								pattern="yyyy-MM-dd HH:mm:ss" />
					</span></td>
					<%-- <td>${user.factory.factoryName}</td> --%>
					<td>
					<c:if test="${user.userStates==1}"><span style="color: green;">可用</span></c:if>
					<c:if test="${user.userStates==0}"><span style="color: red;">停用</span></c:if>
					</td>
					<td>${user.memo}</td>
					<td><shiro:hasPermission name="user:update">
					
							<c:if test="${user.userStates==1}"><a href="${base}/admin/user/saveConductor?userId=${user.userId}&userStates=0"
								title="[停用]">[停用]</a></c:if>
							<c:if test="${user.userStates==0}"><a href="${base}/admin/user/saveConductor?userId=${user.userId}&userStates=1"
								title="[启用]">[启用]</a></c:if>
							<a href="${base}/admin/user/editConductor?userId=${user.userId}"
								title="[编辑]">[编辑]</a>
						</shiro:hasPermission>
				</tr>
			</c:forEach>
		</table>
		<c:choose>
			<c:when test="${userList.size()>0}">
				<div class="pagerBar">
					<div>
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