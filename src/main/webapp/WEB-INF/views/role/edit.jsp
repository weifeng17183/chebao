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
<script type="text/javascript">
	$().ready(function() {
		var $allChecked = $("#validateForm .allChecked");
		$allChecked.click(function() {
			var $this = $(this);
			var $thisCheckbox = $this.parent().parent().find(":checkbox");
			if ($thisCheckbox.filter(":checked").size() > 0) {
				$thisCheckbox.attr("checked", false);
			} else {
				$thisCheckbox.attr("checked", true);
			}
			return false;
		});
	})
</script>
</head>
<body class="input admin">
	<div class="bar">
		<c:if test="${role.roleId!=null}">修改角色</c:if>
		<c:if test="${role.roleId==null}">添加角色</c:if>
		(<span style="color: red;">*</span>为必填项)
	</div>
	<div id="validateErrorContainer" class="validateErrorContainer">
		<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
		<ul></ul>
	</div>
	<div class="body">
		<form id="validateForm" name="member" action="${base}/admin/role/save"
			method="post">
			<input type="hidden" name="roleId" value="${role.roleId}" />
			<table class="inputTable">
				<tr>
					<th>角色编码:</th>
					<td><input type="text" name="roleName"
						value="${role.roleName}" /></td>
				</tr>
				<tr>
					<th>角色名称:</th>
					<td><input type="text" name="roleSign"
						value="${role.roleSign}" /></td>
				</tr>
				<tr>
					<th>角色描述:</th>
					<td><textarea name="roleDesc" class="formTextarea">${role.roleDesc}</textarea></td>
				</tr>
				<tr>
					<th>角色权限:</th>
					<td></td>
				</tr>
				<c:forEach items="${list}" var="pt">
					<tr>
						<th><a href="#" class="allChecked" title="点击全选此类权限">${pt.titleName}:
						</a></th>
						<td><c:forEach items="${pt.permissionList}" var="permission">
								<label> <input type="checkbox" name="permissionIds"
									class="roleAuthorityList" value="${permission.permissionId}"
									<c:if test="${permission.isChecked }">checked</c:if> />${permission.permissionSign}
								</label>
							</c:forEach></td>
					</tr>
				</c:forEach>
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