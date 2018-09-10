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
	<div class="bar">客户列表&nbsp;总记录数:${page.totalCount}
		(共${page.totalPage}页)</div>
	<div class="body" style="position: relative;">
		<div class="listBar">
			<form id="listForm" action="${base}/admin/user/list" method="post"
				style="display: inline">
				<label>关键字: </label> <input type="text" name="name" size="20"
					value="${users.name}" placeholder="手机号/姓名/备注" /> <input
					type="button" id="searchButton" class="formButton" value="搜 索"
					hidefocus />
		</div>
		<table id="listTable" class="listTable">
			<tr>
				<th><span>编号</span></th>
				<th><span>客户名称</span></th>
				<th><span>手机号码</span></th>
				<th><span>车牌</span></th>
				<th><span>注册时间</span></th>
				<th><span>车厂</span></th>
				<th><span>邀请人</span></th>
				<th><span>备注</span></th>
				<th><span>操作</span></th>
			</tr>
			<c:forEach items="${userList}" var="user">
				<tr>
					<td>${user.userId}</td>
					<td>${user.name}</td>
					<td>${user.mobileNum}</td>
					<td><c:if test="${user.carInfoList != null}">
							<c:forEach items="${user.carInfoList}" var="carInfo"
								varStatus="stat">
								<c:if test="${!stat.last}">
									${carInfo.carNumber},
								</c:if>
								<c:if test="${stat.last}">
									${carInfo.carNumber}
								</c:if>
							</c:forEach>
						</c:if></td>
					<td><span> <fmt:formatDate value="${user.registerDate}"
								pattern="yyyy-MM-dd HH:mm:ss" />
					</span></td>
					<td>${user.factory.factoryName}</td>
					<td>${user.inviter}</td>
					<td>${user.memo}</td>
					<td><a href="${base}/admin/user/info?userId=${user.userId}"
						title="[详情]">[详情]</a> <a
						href="${base}/admin/user/edit?userId=${user.userId}" title="[编辑]">[编辑]</a>
						<a href="${base}/admin/user/alert?userId=${user.userId}"
						title="[管理车辆]">[管理车辆]</a> <shiro:hasPermission name="user:update">
						</shiro:hasPermission></td>
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