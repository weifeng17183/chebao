<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/common/head.jsp"%>
<style type="text/css">
.ui_multiselect01 {
	width: 160px;
	height: 280px;
	margin: 2px 2px 2px 5px;
}
</style>
</head>
<body class="input admin">
	<div class="bar">
		<c:if test="${permission.permissionId!=null}">修改权限</c:if>
		<c:if test="${permission.permissionId==null}">添加权限</c:if>
		(<span style="color: red;">*</span>为必填项)
	</div>
	<div id="validateErrorContainer" class="validateErrorContainer">
		<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
		<ul></ul>
	</div>
	<div class="body">
		<form id="validateForm" name="member"
			action="${base}/admin/permission/save" method="post">
			<input type="hidden" name="permissionId"
				value="${permission.permissionId}" />
			<table class="inputTable">
				<tr>
					<th>权限编码:</th>
					<td><input type="text" name="permissionName"
						value="${permission.permissionName}" /></td>
				</tr>
				<tr>
					<th>权限名称:</th>
					<td><input type="text" name="permissionSign"
						value="${permission.permissionSign}" /></td>
				</tr>
				<tr>
					<th>访问路径:</th>
					<td><input type="text" name="path" value="${permission.path}" /></td>
				</tr>
				<tr>
					<th>权限分类:</th>
					<td><select name="titleId">
							<c:forEach items="${list }" var="permissionTitle">
								<option value="${permissionTitle.titleId }"
									<c:if test="${permissionTitle.titleId == permission.titleId}"> selected</c:if>>${permissionTitle.titleName }</option>
							</c:forEach>
					</select></td>
				</tr>
			</table>
			<div class="buttonArea">
				<input type="submit" class="formButton" value="确  定" hidefocus />&nbsp;&nbsp;
				<input type="button" class="formButton"
					onclick="window.history.back(); return false;" value="返  回"
					hidefocus />
			</div>
		</form>
	</div>
</body>
</html>