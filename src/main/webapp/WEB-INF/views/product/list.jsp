<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/common/head.jsp"%>
<script type="text/javascript">
	$().ready(function() {

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
<body class="list">
	<div id="validateErrorContainer" class="validateErrorContainer">
		<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
		<ul></ul>
	</div>
	<div class="bar">项目列表&nbsp;总记录数: ${page.totalCount}
		(共${page.totalPage}页)</div>
	<div class="body" style="position: relative;">
		<form id="listForm" action="${base}/admin/product/list" method="post"
			style="display: inline">
			<div class="listBar">
				<label>关键字搜索: </label> <input type="text" name="productName"
					value="${product.productName}" /> <label>大类: </label> <select
					name="stuffTypeId" id="stuffTypeId">
					<option value="">请选择大类</option>
					<c:forEach items="${stuffTypeList}" var="stuffType">
						<option value="${stuffType.stuffTypeId }"
							<c:if test="${stuffType.stuffTypeId == product.stuffTypeId}"> selected</c:if>>${stuffType.stuffTypeName }</option>
					</c:forEach>
				</select> <label>小类: </label> <select name="secondTypeId" id="secondTypeId">
					<option value=''>请选择小类</option>
				</select> <input type="button" id="searchButton" class="formButton"
					value="搜 索" hidefocus /> &nbsp;&nbsp; <label>每页显示: </label> <select
					name="pageSize" id="pageSize">
					<option value="15" <c:if test="${pageSize == 15}"> selected</c:if>>
						15</option>
					<option value="20" <c:if test="${pageSize == 20}"> selected</c:if>>
						20</option>
					<option value="50" <c:if test="${pageSize == 50}"> selected</c:if>>
						50</option>
					<option value="100"
						<c:if test="${pageSize == 100}"> selected</c:if>>100</option>
				</select>
			</div>
			<table id="listTable" class="listTable">
				<tr>
					<th><span>编号</span></th>
					<th><span>项目名称</span></th>
					<th><span>价格</span></th>
					<th><span>折扣</span></th>
					<th><span>大类</span></th>
					<th><span>小类</span></th>
					<th><span>创建时间</span></th>
					<th><span>修改时间</span></th>
					<th><span>操作</span></th>
				</tr>
				<c:forEach items="${list}" var="product">
					<tr>
						<td>${product.id}</td>
						<td>${product.productName}</td>
						<td>￥${product.price}</td>
						<td><c:if test="${product.discount!=null}">${product.discount}折</c:if>
						<c:if test="${product.discount==null}">无折扣</c:if>
						</td>
						<td>${product.stuffType.stuffTypeName}</td>
						<td>${product.secondType.secondTypeName}</td>
						<td><span> <fmt:formatDate
									value="${product.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
						</span></td>
						<td><span> <fmt:formatDate
									value="${product.updateTime}" pattern="yyyy-MM-dd HH:mm:ss" />
						</span></td>
						<td><a href="${base}/admin/product/edit?id=${product.id}"
							title="[修改]">[修改]</a>  <a
							onclick="if(confirm('确定删除此项目吗?')==false)return false;"
							href="${base}/admin/product/delete?productId=${product.id}"
							title="[删除]">[删除]</a></td>
					</tr>
				</c:forEach>
			</table>
			<c:choose>
				<c:when test="${list.size()>0}">
					<div class="pagerBar">
						<div class="pager">
							<%@include file="/WEB-INF/views/pager.jsp"%>
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