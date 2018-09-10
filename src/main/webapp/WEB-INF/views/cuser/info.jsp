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
</head>
<body class="input">
	<div class="bar">
		添加客户(<span style="color: red;">*</span>为必填项)
	</div>
	<div id="validateErrorContainer" class="validateErrorContainer">
		<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
		<ul></ul>
	</div>
	<div class="body">
		<div class="bar">
			<span style="font-weight: bold;">&nbsp;&nbsp;&nbsp;&nbsp;客户信息</span>
		</div>
		<table class="inputTable">
			<tr>
				<th>客户名称:</th>
				<td>${user.name }</td>
			</tr>
			<tr>
				<th>手机号码</th>
				<td>${user.mobileNum }</td>
			</tr>
			<tr>
				<th>注册时间:</th>
				<td><span><fmt:formatDate value="${user.registerDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /> </span></td>
			</tr>
			<tr>
				<th>邀请人:</th>
				<td>${user.inviter}</td>
			</tr>
			<tr>
				<th>备注:</th>
				<td>${user.memo}</td>
			</tr>
		</table>
		<div class="bar">
			<span style="font-weight: bold;">&nbsp;&nbsp;&nbsp;&nbsp;车辆信息</span>
		</div>
		<div class="findOForders">
			<div class="category">
				<div class="categoryList bj">
					<dl id="dl">
						<dt>
							<span>车牌号</span><span>车型</span><span>颜色</span><span>备注</span>
						</dt>
						<c:forEach items="${user.carInfoList}" var="item">
							<dd>
								<span> ${item.carNumber} </span>
								<c:if test="${item.carName==null}">
									<span>&nbsp;</span>
								</c:if>
								<span>${item.carName}</span>
								<c:if test="${item.colour==null}">
									<span>&nbsp;</span>
								</c:if>
								<span>${item.colour}</span>
								<c:if test="${item.memo==null}">
									<span>&nbsp;</span>
								</c:if>
								<span>${item.memo}</span>
							</dd>
						</c:forEach>
					</dl>
				</div>
			</div>
		</div>
		<div class="bar">
			<span style="font-weight: bold;">&nbsp;&nbsp;&nbsp;&nbsp;地址信息</span>
		</div>
		<div class="findOForders">
			<div class="category">
				<div class="categoryList bj">
					<dl id="dl">
						<dt>
							<span>省份</span><span>城市</span><span>区域</span><span>地址</span>
						</dt>
						<c:forEach items="${user.addressList}" var="item">
							<dd>
								<span> ${item.province} </span>
								<c:if test="${item.city==null}">
									<span>&nbsp;</span>
								</c:if>
								<span>${item.city}</span>
								<c:if test="${item.area==null}">
									<span>&nbsp;</span>
								</c:if>
								<span>${item.area}</span>
								<c:if test="${item.street==null}">
									<span>&nbsp;</span>
								</c:if>
								<span>${item.street}</span>
							</dd>
						</c:forEach>
					</dl>
				</div>
			</div>
		</div>
		<div class="buttonArea">
			<input type="button" class="formButton"
				onclick="window.history.back(); return false;" value="返  回"
				hidefocus />
		</div>
	</div>
</body>
</html>