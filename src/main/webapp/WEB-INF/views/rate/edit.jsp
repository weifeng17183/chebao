<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/common/head.jsp"%>
<script type="text/javascript">
	$().ready(function() {

		var $validateErrorContainer = $("#validateErrorContainer");
		var $validateErrorLabelContainer = $("#validateErrorContainer ul");
		var $validateForm = $("#validateForm");

		var $tab = $("#tab");

		var adminId = "${editAdmin.adminId}";

		// Tab效果
		$tab.tabs(".tabContent", {
			tabs : "input"
		});

		// 表单验证
		$validateForm.validate({
			wrapper : "li",
			errorClass : "validateError",
			ignoreTitle : true,
			errorPlacement : function(error, element) {
				var wrapHtm = '<label rel="tips"  style="color:red;">';
				wrapHtm += $(error).text();
				wrapHtm += '</label>'
				var $tips = $("label[rel='tips']", $(element).parent());
				if ($tips.size() > 0) {
					$tips.replaceWith($(wrapHtm));
				} else {
					$(wrapHtm).appendTo($(element).parent());
				}
			},

			success : function(label) {
			},
			rules : {
				"factoryRate" : {
					required : true,
					number : true
				},
				"platformRate" : {
					required : true,
					number : true
				},
				"invaterRate" : {
					required : true,
					number : true
				},
				"password" : {
					required : true
				}
			},

			messages : {
				"factoryRate" : {
					required : "请填写车厂比例",
					number : "请输入数字"
				},
				"platformRate" : {
					required : "请填写平台比例",
					number : "请输入数字"
				},
				"invaterRate" : {
					required : "请填写邀请人比例",
					number : "请输入数字"
				},
				"password" : {
					required : "请填写密码"
				}
			},
			submitHandler : function(form) {
				$(form).find(":submit").attr("disabled", true);
				form.submit();
			}
		});

	});
</script>
</head>
<body class="input admin">
	<div class="bar">
		修改分成比例 (<span style="color: red;">*</span>为必填项)
	</div>
	<div id="validateErrorContainer" class="validateErrorContainer">
		<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
		<ul></ul>
	</div>
	<div class="body">
		<form id="validateForm" name="member"
			action="${base}/admin/rate/updateRate" method="post">
			<table class="inputTable">
				<tr>
					<th>车厂比例:</th>
					<td><input type="text" name="factoryRate" class="formText"
						value="${entity.factoryRate }" />% <label class="requireField">*</label></td>
				</tr>
				<tr>
					<th>平台比列:</th>
					<td><input type="text" name="platformRate" class="formText"
						value="${entity.platformRate }" />% <label class="requireField">*</label></td>
				</tr>
				<tr>
					<th>邀请人比例:</th>
					<td><input type="text" name="invaterRate" class="formText"
						value="${entity.invaterRate }" />% <label class="requireField">*</label></td>
				</tr>
				<tr>
					<th>超级管理员密码:</th>
					<td><input type="password" name="password" value=""
						class="formText" /><label class="requireField">*</label></td>
				</tr>
			</table>
			<div class="buttonArea">
				<input type="submit" class="formButton" value="确  定" hidefocus />&nbsp;&nbsp;
			</div>
		</form>
	</div>
</body>
</html>