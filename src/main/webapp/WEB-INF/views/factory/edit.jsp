<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/common/head.jsp"%>
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