<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/common/head.jsp"%>
<style type="text/css">
.raceShow {
	border: solid 1px #ccc;
	background-color: white;
	position: absolute;
	display: none;
	width: 450px;
	height: 150px;
	padding: 5px;
	top: 18%;
	left: 25%;
}
</style>
<script type="text/javascript">
	$(function() {
		//弹出层
		$(".bShowDiv").click(function(event) {
			var orderId = this.title;
			$("#orderId").val(orderId);
			$("#editPop").show(300);
		});
		//隐藏层
		$("#close").click(function(event) {
			$("#editPop").hide(300);
		});

		//弹出层
		$(".bShowDiv1").click(function(event) {
			var orderId = this.title;
			$("#orderId1").val(orderId);
			$("#editPop1").show(300);
		});
		//隐藏层
		$("#close1").click(function(event) {
			$("#editPop1").hide(300);
		});
	});
</script>
</head>
<body class="list">
	<div id="validateErrorContainer" class="validateErrorContainer">
		<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
		<ul></ul>
	</div>
	<div class="bar">订单列表&nbsp;总记录数:${page.totalCount}
		(共${page.totalPage}页)</div>
	<div class="body" style="position: relative;">
		<div class="listBar">
			<form id="listForm" action="${base}/admin/order/list" method="post"
				style="display: inline">
				<input type="hidden" name="carStatus" value="1" /> <label>客户:
				</label> <input type="text" name="user.name" size="20"
					value="${order.user.name}" placeholder="客户手机号/客户姓名" /> <label>车牌:
				</label> <input type="text" name="carInfo.carNumber" size="20"
					value="${order.carInfo.carNumber}" placeholder="" /> <label>订单号:
				</label> <input type="text" name="orderId" size="20"
					value="${order.orderId}" placeholder="" /> <label>状态: </label> <select
					name="payStatus">
					<option value="">请选择状态</option>
					<option value="1"
						<c:if test="${order.payStatus == 1}"> selected</c:if>>已下单-待接车</option>
					<option value="2"
						<c:if test="${order.payStatus == 2}"> selected</c:if>>已接车-待评估</option>
					<option value="3"
						<c:if test="${order.payStatus == 3}"> selected</c:if>>已评估-待确认</option>
					<option value="4"
						<c:if test="${order.payStatus == 4}"> selected</c:if>>已确认-待检修</option>
					<option value="5"
						<c:if test="${order.payStatus == 5}"> selected</c:if>>已检修-待付款</option>
					<option value="6"
						<c:if test="${order.payStatus == 6}"> selected</c:if>>已付款-待还车</option>
					<option value="7"
						<c:if test="${order.payStatus == 7}"> selected</c:if>>已还车-已完成</option>
				</select> <label>下单日期:</label> <input type="text" name="beginDate"
					class="formText" style="width: 80px"
					value="<fmt:formatDate pattern="yyyy-MM-dd" value="${order.beginDate}" />"
					onclick="WdatePicker()" /> 至 <input type="text" name="endDate"
					class="formText" style="width: 80px"
					value="<fmt:formatDate pattern="yyyy-MM-dd" value="${order.endDate}" />"
					onclick="WdatePicker()" /> &nbsp;&nbsp; <input type="button"
					id="searchButton" class="formButton" value="搜 索" hidefocus />
		</div>
		<table id="listTable" class="listTable">
			<tr>
				<th><span>订单</span></th>
				<th><span>下单时间</span></th>
				<th><span>车牌号码</span></th>
				<th><span>联系号码</span></th>
				<th><span>联系人</span></th>
				<th><span>项目信息</span></th>
				<th><span>金额</span></th>
				<th><span>状态</span></th>
				<th><span>车厂</span></th>
				<th><span>接车员</span></th>
				<th><span>操作</span></th>
			</tr>
			<c:forEach items="${list}" var="order">
				<tr>
					<td>${order.orderId}</td>
					<td><span><fmt:formatDate value="${order.createTime}"
								pattern="yyyy-MM-dd HH:mm:ss" /> </span></td>
					<td>${order.carInfo.carNumber}</td>
					<td>${order.user.mobileNum}</td>
					<td>${order.user.name}</td>
					<td><c:if test="${order.itemList != null}">
							<c:forEach items="${order.itemList}" var="orderItem"
								varStatus="stat">
								<c:if test="${!stat.last}">
									${orderItem.productName},
								</c:if>
								<c:if test="${stat.last}">
									${orderItem.productName}
								</c:if>
							</c:forEach>
						</c:if></td>
					<td>¥${order.amount}</td>
					<td><c:choose>
							<c:when test="${order.payStatus==1}">
								已下单-待接车
							</c:when>
							<c:when test="${order.payStatus==2}">
								已接车-待评估
							</c:when>
							<c:when test="${order.payStatus==3}">
								已评估-待确认
							</c:when>
							<c:when test="${order.payStatus==4}">
								已确认-待检修
							</c:when>
							<c:when test="${order.payStatus==5}">
								已检修-待付款
							</c:when>
							<c:when test="${order.payStatus==6}">
								已付款-待还车
							</c:when>
							<c:when test="${order.payStatus==7}">
								已还车-已完成
							</c:when>
						</c:choose></td>
					<td>${order.factory.factoryName}</td>
					<td>${order.orderUser.name}</td>
					<td><a href="${base}/admin/order/info?id=${order.id}"
						title="[查看]">[查看]</a> <shiro:hasPermission name="order:update">
							<c:if test="${order.factoryId==null}">
								<a href="#" title="${order.id}" class="bShowDiv1">[设置车厂]</a>
							</c:if>
							<c:if test="${order.payStatus==1}">
								<a href="#" title="${order.id}" class="bShowDiv">[设置接车员]</a>
							</c:if>
							<c:if test="${order.payStatus==2}">
								<a href="${base}/admin/order/edit?id=${order.id}" title="[评估]">[评估]</a>
							</c:if>
							<c:if test="${order.payStatus==3}">
								<a href="${base}/admin/order/edit?id=${order.id}" title="[修改]">[修改]</a>
							</c:if>
							<c:if test="${order.payStatus==4}">
								<a onclick="if(confirm('确定车辆检修完成?')==false)return false;"
									href="${base}/admin/order/confirm?id=${order.id}"
									title="[检修完成]">[检修完成]</a>
							</c:if>
						</shiro:hasPermission></td>
				</tr>
			</c:forEach>
		</table>
		<c:choose>
			<c:when test="${list.size()>0}">
				<div class="pagerBar">
					<div>
						<div class="pager">
							<%@include file="/WEB-INF/views/pager.jsp"%>
						</div>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<div class="noRecord">没有找到任何记录!</div>
			</c:otherwise>
		</c:choose>
		</form>
	</div>
	<div class="input" style="background-color: white;">
		<div id="editPop" class="raceShow">
			<div class="div" style="width: 100%; height: 100%; overflow-y: auto;">
				<div class="cStatistics">
					<span></span>设置接车员
				</div>
				<form id="validateForm" name="member"
					action="${base}/admin/order/save" method="post">
					<input type="hidden" name="id" value="" id="orderId" />
					<table class="inputTable">
						<tr>
							<th></th>
							<td></td>
						</tr>
						<tr>
							<th>接车员</th>
							<td><select name="orderUserId">
									<option value="">请选择接车员</option>
									<c:forEach items="${userList}" var="user">
										<option value="${user.userId}">${user.name}</option>
									</c:forEach>
							</select></td>
						</tr>
					</table>
					<br>
						<div class="buttonArea">
							<input type="submit" class="formButton" value="确  定" hidefocus />
							<input type="button" class="formButton" value="关  闭" id="close"
								hidefocus />
						</div>
				</form>
			</div>
		</div>
	</div>

	<div class="input" style="background-color: white;">
		<div id="editPop1" class="raceShow">
			<div class="div" style="width: 100%; height: 100%; overflow-y: auto;">
				<div class="cStatistics">
					<span></span>设置车厂
				</div>
				<form id="validateForm" name="member"
					action="${base}/admin/order/saveFactory" method="post">
					<input type="hidden" name="id" value="" id="orderId1" />
					<table class="inputTable">
						<tr>
							<th></th>
							<td></td>
						</tr>
						<tr>
							<th>接车员</th>
							<td><select name="factoryId">
									<option value="">请选择车厂</option>
									<c:forEach items="${factoryList}" var="factory">
										<option value="${factory.id}">${factory.factoryName}</option>
									</c:forEach>
							</select></td>
						</tr>
					</table>
					<br>
						<div class="buttonArea">
							<input type="submit" class="formButton" value="确  定" hidefocus />
							<input type="button" class="formButton" value="关  闭" id="close1"
								hidefocus />
						</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>