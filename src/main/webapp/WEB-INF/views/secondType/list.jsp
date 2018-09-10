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
	<div class="bar">小类列表&nbsp;总记录数: ${page.totalCount}
		(共${page.totalPage}页)</div>
	<div class="body" style="position: relative;">
		<form id="listForm" action="${base}/admin/secondType/list"
			method="post" style="display: inline">
			<div class="listBar">
				<label>关键字搜索: </label> <input type="text" name="secondTypeName"
					value="${searchBean.secondTypeName}" /> <label>大类: </label> <select
					name="stuffTypeId">
					<option value="">全部</option>
					<c:forEach items="${stuffTypeList}" var="stuffType">
						<option value="${stuffType.stuffTypeId }"
							<c:if test="${stuffType.stuffTypeId == searchBean.stuffTypeId}"> selected</c:if>>${stuffType.stuffTypeName }</option>
					</c:forEach>
				</select> <input type="button" id="searchButton" class="formButton"
					value="搜 索" hidefocus /> &nbsp;&nbsp; <label>每页显示: </label> <select
					name="pageSize" id="pageSize">
					<option value="10" <c:if test="${pageSize == 10}"> selected</c:if>>
						10</option>
					<option value="20" <c:if test="${pageSize == 20}"> selected</c:if>>
						20</option>
					<option value="50" <c:if test="${pageSize == 50}"> selected</c:if>>
						50</option>
					<option value="100"
						<c:if test="${pageSize == 100}"> selected</c:if>>100</option>
				</select>
			</div>

			<table id="listTable" class="listTable">
				<tr>
					<th><span>编号</span></th>
					<th><span>小类名称</span></th>
					<th><span>所属大类</span></th>
					<th><span>操作</span></th>
				</tr>
				<c:forEach items="${list}" var="secondType">
					<tr>
						<td>${secondType.secondTypeId}</td>
						<td>${secondType.secondTypeName}</td>
						<td>${secondType.stuffType.stuffTypeName}</td>
						<td><a
							href="${base}/admin/secondType/edit?secondTypeId=${secondType.secondTypeId}"
							title="[修改]">[修改]</a> | <a
							onclick="if(confirm('确定删除此小类吗?')==false)return false;"
							href="${base}/admin/secondType/delete?secondTypeId=${secondType.secondTypeId}"
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