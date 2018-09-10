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

		var id = "${product.id}";

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
				"productName" : {
					required : true,
					remote : "${base}/admin/product/checkName?id=" + id
				},
				"price" : {
					required : true,
					number : true
				},
				"discount" : {
					number : true
				},
				"stuffTypeId" : "required",
				"secondTypeId" : "required"
			},

			messages : {
				"productName" : {
					required : "请填写项目名称",
					remote : "项目名称已存在"
				},
				"price" : {
					required : "请输入金额",
					number : "请输入数字"
				},
				"discount" : {
					number : "请输入数字"
				},
				"stuffTypeId" : "请选择大类",
				"secondTypeId" : "请选择小类"
			},
			submitHandler : function(form) {
				$(form).find(":submit").attr("disabled", true);
				form.submit();
			}
		});

		var stuffTypeId = "${product.stuffTypeId}";
		var secondTypeId = "${product.secondTypeId}";
		if (stuffTypeId != null && stuffTypeId != "") {
			listByState(stuffTypeId, secondTypeId);
		}

		$("#stuffTypeId").change(function() {
			if ($(this).val() != null) {
				var stuffTypeId = $(this).val();
				listByState(stuffTypeId, null);
			}
		});
	});
	function listByState(stuffTypeId, secondTypeId) {
		$("#secondTypeId").children().remove();
		$("#secondTypeId").append("<option value=''>请选择小类</option>");
		$.ajax({
			type : "POST",
			url : "${base}/admin/product/getSecondTypeList",
			dataType : "json",
			data : "stuffTypeId=" + stuffTypeId,
			success : function(result) {
				if (result.error == 0) {
					var list = result.data.list;
					$(list).each(
							function(i, n) {
								if (secondTypeId == n.secondTypeId) {
									$("#secondTypeId").append(
											"<option value="+n.secondTypeId+" selected>"
													+ n.secondTypeName
													+ "</option>");
								} else {
									$("#secondTypeId").append(
											"<option value="+n.secondTypeId+">"
													+ n.secondTypeName
													+ "</option>");
								}
							});
				}
			}
		})
	}
</script>
</head>
<body class="input">
	<div class="bar">
		<c:choose>
			<c:when test="${product.id==null}">
				添加项目
			</c:when>
			<c:otherwise>
				编辑项目
			</c:otherwise>
		</c:choose>
		(<span style="color: red;">*</span>为必填项)
	</div>
	<div id="validateErrorContainer" class="validateErrorContainer">
		<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
		<ul></ul>
	</div>
	<form id="validateForm" name="member"
		action="${base}/admin/product/save" method="post">
		<input type="hidden" name="id" value="${product.id}" />
		<div class="bar">
			<span style="font-weight: bold;">&nbsp;&nbsp;&nbsp;&nbsp;项目信息</span>
		</div>
		<table class="inputTable">
			<tr>
				<th>项目名称:</th>
				<td><input type="text" name="productName" class="formText"
					value="${product.productName}" /> <label class="requireField">*</label>
				</td>
			</tr>
			<tr>
				<th>价格:</th>
				<td><input type="text" name="price" class="formText"
					value="${product.price}" /> <label class="requireField">*</label>
				</td>
			</tr>
			<tr>
				<th>折扣:</th>
				<td><input type="text" name="discount" class="formText"
					value="${product.discount}" />折</td>
			</tr>
			<tr>
				<th>大类:</th>
				<td><select name="stuffTypeId" id="stuffTypeId">
						<option value="">请选择大类</option>
						<c:forEach items="${stuffTypeList}" var="stuffType">
							<option value="${stuffType.stuffTypeId }"
								<c:if test="${stuffType.stuffTypeId == product.stuffTypeId}"> selected</c:if>>${stuffType.stuffTypeName}</option>
						</c:forEach>
				</select> <label class="requireField">*</label></td>
			</tr>
			<tr>
				<th>小类:</th>
				<td><select name="secondTypeId" id="secondTypeId">
						<option value=''>请选择小类</option>
				</select> <label class="requireField">*</label></td>
			</tr>
		</table>
		<div class="buttonArea">
			<input type="submit" class="formButton" value="确  定" hidefocus />&nbsp;&nbsp;
			<input type="button" class="formButton"
				onclick="window.history.back(); return false;" value="返  回"
				hidefocus />
		</div>
	</form>
</body>
</html>