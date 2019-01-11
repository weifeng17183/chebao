<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/common/head3.jsp"%>
<style type="text/css">
.ui_multiselect01 {
	width: 160px;
	height: 280px;
	margin: 2px 2px 2px 5px;
}
</style>
<script type="text/javascript">
	$()
			.ready(
					function() {
						$("#btn")
								.click(
									function() {
										var num = $(this).attr("title");
										num = parseInt(num) + 1;
										$("#fdl")
												.append(
														"<dd id=dd"+num+" ><span><input name='itemList["+num+"].productName' type='text' class='formText' /></span><span><input type='number' step='0.01' name='itemList["+num+"].price' class='formText' /></span> <span><input type='text' name='itemList["+num+"].discount' class='formText' /></span><span><input type='button' name=dd"+num+" class='formButton deleteDD' title='delete' value='删   除'  /></span> </dd>");
										$(this).attr("title", num);
										
										$(".deleteDD").click(function(){
											var ddid = $(this).attr("name");
											/* alert($(this).attr("name")); */
											$("#"+ddid).remove();
										});
									});

						var $allChecked = $("#validateForm .roleAuthorityList");
						$allChecked
								.click(function() {
									var $this = $(this);
									if ($(this).prop("checked")) {
										$
												.ajax({
													type : "POST",
													url : "${base}/admin/product/selectDetail",
													dataType : "json",
													data : "id="
															+ $(this).val(),
													success : function(result) {
														if (result.error == 0) {
															var item = result.data;
															if (item.discount == null) {
																$("#dl")
																		.append(
																				"<dd id="+item.id+"><span>"
																						+ item.productName
																						+ "</span><span>"
																						+ item.price
																						+ "</span><span>100%</span><span>"
																						+ item.price
																						+ "</span</dd>");
															} else {
																$("#dl")
																		.append(
																				"<dd id="+item.id+"><span>"
																						+ item.productName
																						+ "</span><span>"
																						+ item.price
																						+ "</span><span>"
																						+ item.discount*100/10
																						+ "</span><span>"
																						+ item.price
																						* item.discount
																						* 0.1
																						+ "</span</dd>");
															}
														}
													}
												})
									} else {
										$("#" + $(this).val()).remove();
									}
								});
					})
</script>
</head>
<body class="input admin">
	<div class="bar"><c:if test="${order.payStatus==2}">评估</c:if><c:if test="${order.payStatus==3}">修改</c:if>订单</div>
	<div id="validateErrorContainer" class="validateErrorContainer">
		<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
		<ul></ul>
	</div>
	<div class="body">
		<form id="validateForm" name="member"
			action="${base}/admin/corder/saveOrder" method="post">
			<input type="hidden" name="id" value="${order.id}" />
			<div class="bar">
				<span style="font-weight: bold;">&nbsp;&nbsp;&nbsp;&nbsp;客户信息</span>
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
				<span style="font-weight: bold;">&nbsp;&nbsp;&nbsp;&nbsp;非标项目</span>
			</div>
			<div class="findOForders">
				<div class="category">
					<input type="button" class="formButton" id="btn" title="0"
						value="新   增" />
					<div class="categoryList bj">
						<dl id="fdl">
							<dt>
								<span>非标项目描述:</span><span>非标项目价格:</span><span>非标项目折扣:</span>
							</dt>
							<dd>
								<span><input name="itemList[0].productName" type="text"
									class="formText" /></span><span><input type="number" step="0.01"
									name="itemList[0].price" class="formText" /></span> <span><input
									type="text" name="itemList[0].discount" class="formText" /></span>
							</dd>
						</dl>
					</div>
				</div>
			</div>
			
			<div class="bar">
				<span style="font-weight: bold;">&nbsp;&nbsp;&nbsp;&nbsp;已选项目</span>
			</div>
			<table class="inputTable">
				<c:forEach items="${list}" var="pt">
					<tr>
						<th><a href="#">${pt.stuffTypeName}: </a></th>
						<td><c:forEach items="${pt.productList}" var="product">
								<label> <input type="checkbox" name="productIds"
									class="roleAuthorityList" value="${product.id}"
									<c:if test="${product.isChecked }">checked</c:if>
									<c:if test="${product.isDisabled }">disabled="disabled"</c:if> />${product.productName}
								</label>
							</c:forEach></td>
					</tr>
				</c:forEach>
			</table>
			<div class="findOForders">
				<div class="category">
					<div class="categoryList bj">
						<dl id="dl">
							<dt>
								<span>项目名称</span><span>单价</span><span>折扣</span><span>小计</span>
							</dt>
							<c:forEach items="${order.itemList}" var="item">
								<dd id="${item.productId}">
									<span> ${item.productName} </span> <span>${item.price}</span>
									<c:if test="${item.discount==null}">
										<span>100%</span>
									</c:if>
									<c:if test="${item.discount!=null}">
									<span>${item.discount*100/10}%</span></c:if> <span>${item.itemAmount}</span>
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
				<input type="submit" class="formButton" value="保       存" hidefocus />&nbsp;&nbsp;
			</div>
		</form>
	</div>
</body>
</html>