<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/common/head.jsp"%>
<script type="text/javascript">
	$()
			.ready(
					function() {
						var $validateErrorContainer = $("#validateErrorContainer");
						var $validateErrorLabelContainer = $("#validateErrorContainer ul");
						var $validateForm = $("#validateForm");

						var $tab = $("#tab");

						var adminId = "${admin.adminId}";

						// Tab效果
						$tab.tabs(".tabContent", {
							tabs : "input"
						});

						// 表单验证
						$validateForm
								.validate({
									wrapper : "li",
									errorClass : "validateError",
									ignoreTitle : true,
									errorPlacement : function(error, element) {
										var wrapHtm = '<label rel="tips"  style="color:red;">';
										wrapHtm += $(error).text();
										wrapHtm += '</label>'
										var $tips = $("label[rel='tips']", $(
												element).parent());
										if ($tips.size() > 0) {
											$tips.replaceWith($(wrapHtm));
										} else {
											$(wrapHtm).appendTo(
													$(element).parent());
										}
									},

									success : function(label) {
									},
									rules : {
										"userName" : {
											required : true,
											username : true,
											minlength : 2,
											maxlength : 20,
											remote : "${base}/admin/admin/checkUserName?adminId="
													+ adminId
										},
										"password" : {
											required : true,
											minlength : 4,
											maxlength : 20
										},
										"rePassword" : {
											required : true,
											equalTo : "#password"
										},
										"adminStatus" : "required"
									},

									messages : {
										"userName" : {
											required : "请填写用户名",
											username : "用户名只允许包含中文、英文、数字和下划线",
											minlength : "用户名长度必须大于等于2",
											maxlength : "用户名长度必须小于等于20",
											remote : "用户名已存在"
										},
										"password" : {
											required : "请填写密码",
											minlength : "密码必须大于等于4",
											maxlength : "密码必须小于等于20"
										},
										"rePassword" : {
											required : "请填写重复密码",
											equalTo : "两次密码输入不一致"
										},
										"adminStatus" : "请选择状态"
									},
									submitHandler : function(form) {
										$(form).find(":submit").attr(
												"disabled", true);
										form.submit();
									}
								});
					});
</script>
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
		<c:if test="${admin.adminId!=null}">编辑管理员</c:if>
		<c:if test="${admin.adminId==null}">添加管理员</c:if>
		(<span style="color: red;">*</span>为必填项)
	</div>
	<div id="validateErrorContainer" class="validateErrorContainer">
		<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
		<ul></ul>
	</div>
	<div class="body">
		<form id="validateForm" name="member"
			action="${base}/admin/admin/save" method="post">
			<input type="hidden" name="adminId" value="${admin.adminId}" />
			<table class="inputTable">
				<tr>
					<th>用户昵称:</th>
					<c:if test="${admin.adminId!=null}">
						<td>${admin.userName}</td>
						<input type="hidden" name="userName" value="${admin.userName}" />
					</c:if>
					<c:if test="${admin.adminId==null}">
						<td><input type="text" name="userName" class="formText" /><label
							class="requireField">*</label></td>
					</c:if>

				</tr>
				<tr>
					<th>密码:</th>
					<td><input id="password" type="password" name="password"
						value="${admin.password }" class="formText" title="密码长度只允许在4-20之间" /><label
						class="requireField">*</label></td>
				</tr>
				<tr>
					<th>重复密码:</th>
					<td><input type="password" name="rePassword" class="formText"
						value="${admin.password }" /><label class="requireField">*</label></td>
				</tr>
				<shiro:hasAnyPermission name="admin:add,admin:update">
					<tr>
						<th>角色:</th>
						<td><c:forEach items="${roleList}" var="role">
								<c:if test="${role.roleId!=1 && role.roleId!=13}">
									<label> <input type="checkbox" name="roleIds"
										value="${role.roleId}"
										<c:if test="${role.isChecked}">checked</c:if> />${role.roleSign}
									</label>
								</c:if>
							</c:forEach> <label class="requireField">*</label></td>
					</tr>
					<c:if test="${admin.adminId!=1 }">
						<tr>
							<th>状态:</th>
							<td><label><input type="radio" name="adminStatus"
									value="1" checked
									<c:if test="${admin.adminStatus ==1 }">checked</c:if> />正常</label> <label><input
									type="radio" name="adminStatus" value="2"
									<c:if test="${admin.adminStatus ==2 }">checked</c:if> />禁用</label><label
								class="requireField">*</label></td>
						</tr>
					</c:if>
				</shiro:hasAnyPermission>
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