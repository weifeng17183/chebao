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
				"password" : {
					required : true
				}
			},

			messages : {
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
	<div class="bar">输入超级管理员密码才可查看</div>
	<div id="validateErrorContainer" class="validateErrorContainer">
		<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
		<ul></ul>
	</div>
	<div class="body">
		<form id="validateForm" name="member" action="${base}/admin/rate/pass"
			method="post">
			<table class="inputTable">
				<tr>
					<th>超级管理员密码:</th>
					<td><input type="password" name="password" value=""
						class="formText" /><label class="requireField">*</label></td>
				</tr>
			</table>
			<div class="buttonArea">
				<input type="button" class="formButton"
					onclick="window.history.back(); return false;" value="返  回"
					hidefocus /> <input type="submit" class="formButton" value="确  定"
					hidefocus />&nbsp;&nbsp;
			</div>
		</form>
	</div>
</body>
</html>