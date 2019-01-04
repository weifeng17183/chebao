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

						var secondTypeId = "${secondType.secondTypeId}";

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
										"factoryName" : {
											required : true
										},
										"factoryContract" : {
											required : true
										},
										"factoryMobile" : {
											required : true,
											minlength : 11,
											number : true,
											maxlength : 11
										}
									},

									messages : {
										"factoryName" : {
											required : "请填写车厂名称"
										},
										"factoryContract" : {
											required : "请填写车厂联系人"
										},
										"factoryMobile" : {
											required : "请填写车厂联系电话",
											number : "手机号只允许数字",
											minlength : "手机号必须等于11",
											maxlength : "手机号必须等于11"
										}
									},
									submitHandler : function(form) {
										$(form).find(":submit").attr(
												"disabled", true);
										form.submit();
									}
								});
					});
</script>
</head>
<body class="input">
	<div class="bar">
		修改车厂资料(<span style="color: red;">*</span>为必填项)
	</div>
	<div id="validateErrorContainer" class="validateErrorContainer">
		<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
		<ul></ul>
	</div>
	<div class="body">
		<form id="validateForm" name="member"
			action="${base}/admin/factory/save" method="post">
			<input type="hidden" name="id" value="${factory.id}" />
			<div class="bar">
				<span style="font-weight: bold;">&nbsp;&nbsp;&nbsp;&nbsp;车厂信息</span>
			</div>
			<table class="inputTable">
				<tr>
					<th>车厂名称:</th>
					<td><input type="text" name="factoryName"
						value="${factory.factoryName}" class="formText" /><label
						class="requireField">*</label></td>

				</tr>
				<tr>
					<th>车厂联系人:</th>
					<td><input type="text" name="factoryContract"
						value="${factory.factoryContract}" class="formText" /><label
						class="requireField">*</label></td>

				</tr>
				<tr>
					<th>车厂联系电话:</th>
					<td><input type="text" name="factoryMobile"
						value="${factory.factoryMobile}" class="formText" /><label
						class="requireField">*</label></td>

				</tr>
				<tr>
					<th>车厂地址:</th>
					<td><textarea name="factoryAdress" class="formTextarea valid">${factory.factoryAdress}</textarea>
					</td>
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