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
	<div class="bar">大类列表&nbsp;总记录数: ${page.totalCount} (共${page.totalPage}页)</div>
	<div class="body" style="position: relative;">
	<form id="listForm" action="${base}/admin/stuffType/list" method="post" style="display: inline">
		<div class="listBar">
			<a href="${base}/admin/stuffType/add" class="formButton">添加大类</a>
		</div>

		<table id="listTable" class="listTable">
			<tr>
				<th><span>编号</span></th>
				<th><span>大类名称</span></th>
				<th><span>操作</span></th>
			</tr>
			<c:forEach items="${list}" var="stuffType">
				<tr>
					<td>${stuffType.stuffTypeId}</td>
					<td>${stuffType.stuffTypeName}</td>
					<td><a
						href="${base}/admin/stuffType/edit?stuffTypeId=${stuffType.stuffTypeId}"
						title="[修改]">[修改]</a> | <a
							onclick="if(confirm('确定删除此大类吗?')==false)return false;"
							href="${base}/admin/stuffType/delete?stuffTypeId=${stuffType.stuffTypeId}"
							title="[删除]">[删除]</a></td>
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