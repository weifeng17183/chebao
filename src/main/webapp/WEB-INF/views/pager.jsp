<script type="text/javascript">
$().ready( function() {
	
	var $pager = $("#pager");
	$pager.pager({
		pagenumber: ${page.currentPage},
		pagecount: ${page.totalPage},
		buttonClickCallback: $.gotoPage
	});

})
</script>
<span id="pager"></span>
<input type="hidden" name="currentPage" id="pageNumber" value="${page.currentPage}" />
<%-- <input type="hidden" name="pager.orderBy" id="orderBy" value="${pager.orderBy}" />
<input type="hidden" name="pager.order" id="order" value="${pager.order}" /> --%>