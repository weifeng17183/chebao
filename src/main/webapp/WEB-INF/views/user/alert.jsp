<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/common/head.jsp"%>
<link href="${base}/template/admin/css/management.css" rel="stylesheet"
	type="text/css" />
<link href="${base}/template/admin/css/reset.css" rel="stylesheet"
	type="text/css" />
<style type="text/css">
.raceShow {
	border: solid 1px #ccc;
	background-color: white;
	position: absolute;
	display: none;
	width: 500px;
	height: 400px;
	padding: 5px;
	top: 13%;
	left: 25%;
}
</style>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	$(function() {
		//弹出层
		$("#btnShowDiv").click(function(event) {
			$("#racePop").show(300);
		});
		//隐藏层
		$(".aClose").click(function(event) {
			$("#racePop").hide(300);
		});
		//弹出层
		$(".bShowDiv").click(function(event) {
			var carId = this.title;
			$.ajax({
				type : "POST",
				url : "${base}/admin/user/carInfo",
				data : {
					"carId" : carId
				},
				success : function(result) {
					$("#carId").val(result.data.carId);
					$("#carNumber").val(result.data.carNumber);
					$("#engineNumber").val(result.data.engineNumber);
					$("#frameNumber").val(result.data.frameNumber);
					$("#mileage").val(result.data.mileage);
					$("#carName").val(result.data.carName);
					$("#colour").val(result.data.colour);
					$("#insuranceCompany").val(result.data.insuranceCompany);
					$("#insuranceNum").val(result.data.insuranceNum);
				}
			})
			$("#editPop").show(300);
		});
		//隐藏层
		$(".bClose").click(function(event) {
			$("#editPop").hide(300);
		});
	});
</script>
</head>
<body class="input" style="background-color: white;">
	<div class="findOForders">
		<!--找材订单统计-->
		<!--找材分类统计-->
		<div class="category">
			<div class="categoryTit">
				<div class="cStatistics">
					<span></span>车辆信息
				</div>
				<div class="time">
					<input type="button" value="添加车辆" id="btnShowDiv"
						class="formButton" style="line-height: 30px; margin-right: 20%;" />
				</div>
			</div>
			<div class="categoryList bj">
				<dl>
					<dt>
						<span>车牌号</span><span>车型</span><span>颜色</span><span>发动机号</span> <span>车架号</span><span>公里数</span><span>保险公司</span><span>保险单号</span>
						<span>操作</span>
					</dt>
					<c:forEach items="${carList}" var="car">
						<dd>
							<span>${car.carNumber}</span> 
							<span><c:if test="${car.carName == null}">&nbsp;</c:if>${car.carName}</span> 
							<span><c:if test="${car.colour == null || car.colour ==''}">&nbsp;</c:if>${car.colour}</span> 
							<span><c:if test="${car.engineNumber == null || car.engineNumber ==''}">&nbsp;</c:if>${car.engineNumber}</span>
							<span><c:if test="${car.frameNumber == null || car.frameNumber ==''}">&nbsp;</c:if>${car.frameNumber}</span>
							<span><c:if test="${car.mileage == null}">&nbsp;</c:if>${car.mileage}</span>
							<span><c:if test="${car.insuranceCompany == null}">&nbsp;</c:if>${car.insuranceCompany}</span>
							<span><c:if test="${car.insuranceNum == null}">&nbsp;</c:if>${car.insuranceNum}</span>
							<span> <a href="#" title="${car.carId}" class="bShowDiv">[编辑]</a></span>
						</dd>
					</c:forEach>
				</dl>
			</div>
		</div>
	</div>

	<!-- <input type="button" value="弹出层" id="btnShowDiv" /> -->
	<div id="racePop" class="raceShow">
		<div class="div" style="width: 100%; height: 100%; overflow-y: auto;">
			<a class="aClose" style="cursor: pointer; float: right;">[关闭]</a>
			<div class="cStatistics">
				<span></span>添加车辆
			</div>
			<form id="validateForm" name="member"
				action="${base}/admin/user/addCar" method="post">
				<input type="hidden" name="userId" value="${users.userId}" />
				<table class="inputTable">
					<tr>
						<th></th>
						<td></td>
					</tr>
					<tr>
						<th>车牌号:</th>
						<td><input type="text" name="carNumber" class="formText"
							value="" /></td>
					</tr>
					<tr>
						<th>车型:</th>
						<td><input type="text" name="carName" class="formText"
							value="" /></td>
					</tr>
					<tr>
						<th>颜色:</th>
						<td><input type="text" name="colour" class="formText"
							value="" /></td>
					</tr>
					<tr>
						<th>发动机号:</th>
						<td><input type="text" name="engineNumber" class="formText"
							value="" /></td>
					</tr>
					<tr>
						<th>车架号:</th>
						<td><input type="text" name="frameNumber" class="formText"
							value="" /></td>
					</tr>
					<tr>
						<th>公里数:</th>
						<td><input type="text" name="mileage" class="formText"
							value="" /></td>
					</tr>
					<tr>
						<th>保险公司:</th>
						<td><input type="text" name="insuranceCompany"
							class="formText" value="" /></td>
					</tr>
					<tr>
						<th>保险单号:</th>
						<td><input type="text" name="insuranceNum" class="formText"
							value="" /></td>
					</tr>
				</table>
				<div class="buttonArea">
					<input type="submit" class="formButton" value="确  定" hidefocus />
				</div>
			</form>
		</div>
	</div>

	<div id="editPop" class="raceShow">
		<div class="div" style="width: 100%; height: 100%; overflow-y: auto;">
			<a class="bClose" style="cursor: pointer; float: right;">[关闭]</a>
			<div class="cStatistics">
				<span></span>修改车辆
			</div>
			<form id="validateForm" name="member"
				action="${base}/admin/user/updateCar" method="post">
				<input type="hidden" name="userId" value="${users.userId}" id="userId" /> <input
					type="hidden" name="carId" value="" id="carId" />
				<table class="inputTable">
					<tr>
						<th></th>
						<td></td>
					</tr>
					<tr>
						<th>车牌号:</th>
						<td><input type="text" name="carNumber" id="carNumber"  class="formText"
							value="" /></td>
					</tr>
					<tr>
						<th>车型:</th>
						<td><input type="text" name="carName" id="carName"  class="formText"
							value="" /></td>
					</tr>
					<tr>
						<th>颜色:</th>
						<td><input type="text" name="colour" id="colour"  class="formText"
							value="" /></td>
					</tr>
					<tr>
						<th>发动机号:</th>
						<td><input type="text" name="engineNumber" id="engineNumber"  class="formText"
							value="" /></td>
					</tr>
					<tr>
						<th>车架号:</th>
						<td><input type="text" name="frameNumber" id="frameNumber"  class="formText"
							value="" /></td>
					</tr>
					<tr>
						<th>公里数:</th>
						<td><input type="text" name="mileage" id="mileage"  class="formText"
							value="" /></td>
					</tr>
					<tr>
						<th>保险公司:</th>
						<td><input type="text" name="insuranceCompany" id="insuranceCompany"  class="formText" value="" /></td>
					</tr>
					<tr>
						<th>保险单号:</th>
						<td><input type="text" name="insuranceNum" id="insuranceNum" class="formText"
							value="" /></td>
					</tr>
				</table>
				<div class="buttonArea">
					<input type="submit" class="formButton" value="确  定" hidefocus />
				</div>
			</form>
		</div>
	</div>
</body>
</html>