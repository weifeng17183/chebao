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
	<div class="body">
		<form id="validateForm" name="member" action="${base}/admin/role/save"
			method="post">
			<input type="hidden" name="roleId" value="${role.roleId}" />
			<div class="bar">
				<span style="font-weight: bold;">&nbsp;&nbsp;&nbsp;&nbsp;订单信息</span>
			</div>
			<table class="inputTable">
				<tr>
					<th>客户名称:</th>
					<td>${order.user.name}</td>
				</tr>
				<tr>
					<th>客户联系电话:</th>
					<td>${order.user.mobileNum}</td>
				</tr>
				<tr>
					<th>客户车牌号码:</th>
					<td>${order.carInfo.carNumber}</td>
				</tr>
				<tr>
					<th>客户维修车型:</th>
					<td>${order.carInfo.carName}</td>
				</tr>
				<tr>
					<th>客户接车地址:</th>
					<td>${order.userAddress.province}${order.userAddress.city}${order.userAddress.area}${order.userAddress.street}
					</td>
				</tr>
					<tr>
					<th>客户还车地址:</th>
					<td>${order.backAddress.province}${order.backAddress.city}${order.backAddress.area}${order.backAddress.street}
					</td>
				</tr>
				<tr>
					<th>客户备注:</th>
					<td>${order.memo}</td>
				</tr>
				<tr>
					<th>下单时间:</th>
					<td><span><fmt:formatDate value="${order.createTime}"
								pattern="yyyy-MM-dd HH:mm:ss" /> </span></td>
				</tr>
				<tr>
					<th>接车时间:</th>
					<td><span><fmt:formatDate value="${order.updateTime}"
								pattern="yyyy-MM-dd HH:mm:ss" /> </span></td>
				</tr>
			</table>
			<table class="inputTable">
				<tr>
					<th>接车员拍摄图片:</th>
					<td><c:if test="${order.imageList.size()>0}">
							<c:forEach items="${order.imageList}" var="orderImage">
								<img width="200px" height="200px"
									src="${base}/${orderImage.orderImageUrl}" />
							</c:forEach>
						</c:if></td>
				</tr>
			</table>
			<div class="bar">
				<span style="font-weight: bold;">&nbsp;&nbsp;&nbsp;&nbsp;已选项目</span>
			</div>
			<div class="findOForders">
				<div class="category">
					<div class="categoryList bj">
						<dl id="dl">
							<dt>
								<span>项目名称</span><span>单价</span><span>折扣</span><span>小计</span>
							</dt>
							<c:forEach items="${order.itemList}" var="item">
								<dd id=" ${item.productId}">
									<span> ${item.productName} </span> <span>${item.price}</span>
									<c:if test="${item.discount==null}">
										<span>&nbsp;</span>
									</c:if>
									<span>${item.discount}</span> <span>${item.itemAmount}</span>
								</dd>
							</c:forEach>
						</dl>
					</div>
				</div>
			</div>
			<div class="buttonArea">
				<input type="button" class="formButton"
					onclick="window.history.back(); return false;" value="返      回"
					hidefocus />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</div>
		</form>
	</div>
</body>
</html>