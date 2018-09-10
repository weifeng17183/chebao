<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/common/head3.jsp"%>
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
			<form id="listForm" action="${base}/admin/corder/list" method="post"
				style="display: inline">
				<input type="hidden" name="carStatus" value="1" /> <label>客户:
				</label> <input type="text" name="user.name" size="20"
					value="${order.user.name}" placeholder="客户手机号/客户姓名" /> <label>车牌:
				</label> <input type="text" name="carInfo.carNumber" size="20"
					value="${order.carInfo.carNumber}" placeholder="" /> <label>订单号:
				</label> <input type="text" name="orderId" size="20"
					value="${order.orderId}" placeholder="" /><label>下单日期:</label> <input type="text" name="beginDate"
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
				<th><span>邀请人</span></th>
				<th><span>订单总金额</span></th>
				<th><span>车厂占比</span></th>
				<th><span>平台占比</span></th>
				<th><span>推荐人占比</span></th>
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
					<td>${order.user.inviter}</td>
					<td>¥${order.amount}</td>
					<td>¥${order.amount*rate.factoryRate/100}</td>
					<td>¥${order.amount*rate.platformRate/100}</td>
					<td>¥${order.amount*rate.invaterRate/100}</td>
					<td><a href="${base}/admin/order/info?id=${order.id}"
						title="[查看]">[查看]</a></td>
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
</body>
</html>