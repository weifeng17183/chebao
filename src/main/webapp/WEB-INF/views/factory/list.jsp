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
	<div class="bar">车厂列表&nbsp;总记录数:${page.totalCount}
		(共${page.totalPage}页)</div>
	<div class="body" style="position: relative;">
		<div class="listBar">
			<form id="listForm" action="${base}/admin/factory/list" method="post"
				style="display: inline">
				<label>关键字: </label> <input type="text" name="factoryName" size="20"
					value="${factory.factoryName}" placeholder="车厂名称" /> <input
					type="button" id="searchButton" class="formButton" value="搜 索"
					hidefocus />
		</div>
		<table id="listTable" class="listTable">
			<tr>
				<th><span>编号</span></th>
				<th><span>车厂名称</span></th>
				<th><span>车厂联系人</span></th>
				<th><span>车厂联系号码</span></th>
				<th><span>车厂地址</span></th>
				<th><span>操作</span></th>
			</tr>
			<c:forEach items="${factoryList}" var="factory">
				<tr>
					<td>${factory.id}</td>
					<td>${factory.factoryName}</td>
					<td>${factory.factoryContract}</td>
					<td>${factory.factoryMobile}</td>
					<td>${factory.factoryAdress}</td>
					<td><a
						href="${base}/admin/factory/edit?factoryId=${factory.id}"
						title="[编辑]">[编辑]</a></td>
				</tr>
			</c:forEach>
		</table>
		<c:choose>
			<c:when test="${factoryList.size()>0}">
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