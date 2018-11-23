<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/common/head.jsp"%>
</head>
<body class="list">
	<div id="validateErrorContainer" class="validateErrorContainer">
		<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
		<ul></ul>
	</div>
	<div class="bar">车厂订单汇总列表&nbsp;总记录数:${page.totalCount}
		(共${page.totalPage}页)</div>
	<div class="body" style="position: relative;">
		<div class="listBar">
			<form id="listForm" action="${base}/admin/order/collect" method="post"
				style="display: inline">
					<label>车厂: </label> <select name="factoryId">
							<option value="">请选择车厂</option>
							<c:forEach items="${factoryList}" var="factory">
								<option value="${factory.id}"
								<c:if test="${order.factoryId == factory.id}"> selected</c:if>
								>${factory.factoryName}</option>
							</c:forEach>
					</select>
					<label>下单日期:</label> <input
					type="text" name="beginDate" class="formText" style="width: 80px"
					value="<fmt:formatDate pattern="yyyy-MM-dd" value="${order.beginDate}" />"
					onclick="WdatePicker()" /> 至 <input type="text" name="endDate"
					class="formText" style="width: 80px"
					value="<fmt:formatDate pattern="yyyy-MM-dd" value="${order.endDate}" />"
					onclick="WdatePicker()" /> &nbsp;&nbsp; <input type="button"
					id="searchButton" class="formButton" value="搜 索" hidefocus />
		</div>
		<table id="listTable" class="listTable">
			<tr>
				<th><span>序号</span></th>
				<th><span>车厂编号</span></th>
				<th><span>车厂名称</span></th>
				<th><span>订单总量</span></th>
				<th><span>汇总金额</span></th>
				<th><span>车厂占比</span></th>
				<th><span>操作</span></th>
			</tr>
			<c:forEach items="${list}" var="order" varStatus="vs">
				<tr>
					<td>${vs.index+1}</td>
					<td>${order.factoryId}</td>
					<td>${order.factoryName}</td>
					<td>${order.orderCount}</td>
					<td>${order.orderAmount}</td>
					<td>¥${order.orderAmount*rate.factoryRate/100}</td>
					<td><a href="${base}/admin/order/settlement?factoryId=${order.factoryId}"
						title="[查看明细]">[查看明细]</a></td>
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