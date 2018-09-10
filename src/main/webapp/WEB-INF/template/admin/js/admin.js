/***
 *	chebao Admin JavaScript
 *
 *	http://www.chebao.com
 *
 *	Copyright © 2010 justfind.com All Rights Reserved.
 **/

justfind = {
	base: "",
	currencySign: "${setting.currencySign}",
	currencyUnit: "${setting.currencyUnit}",
	priceScale: "${setting.priceScale}",
	priceRoundType: "${setting.priceRoundType}"
};

// 编辑器
if(typeof(KE) != "undefined") {
	KE.show({
		id: "editor",
		height: "400px",
		items: ['source', '|', 'fullscreen', 'undo', 'redo', 'print', 'cut', 'copy', 'paste','plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright','justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript','superscript', '|', 'selectall', '-','title', 'fontname', 'fontsize', '|', 'textcolor', 'bgcolor', 'bold','italic', 'underline', 'strikethrough', 'removeformat', '|', 'image','flash', 'media', 'advtable', 'hr', 'emoticons', 'link', 'unlink'],
		imageUploadJson: justfind.base + "/admin/file!ajaxUpload.action",
		fileManagerJson: justfind.base + "/admin/file!ajaxBrowser.action",
		allowFileManager: true,
		autoSetDataMode: true
	});
}

// 货币格式化
function currencyFormat(price) {
	price = setScale(price, justfind.priceScale, justfind.priceRoundType);
	return justfind.currencySign + price + justfind.currencyUnit;
}

//删除左右两端的空格
String.prototype.trim=function()
{
	return this.replace(/(^\s*)(\s*$)/g, '');
}
//删除左边的空格
String.prototype.ltrim=function()
{
    return this.replace(/(^\s*)/g,"");
}
//删除右边的空格
String.prototype.rtrim=function()
{
    return this.replace(/(\s*$)/g,"");
}
//格式化字符串
String.prototype.format = function() {
    if (arguments.length == 0) {
        return this;
    }
    for (var StringFormat_s = this, StringFormat_i = 0; StringFormat_i < arguments.length; StringFormat_i++) {
        StringFormat_s = StringFormat_s.replace(new RegExp("\\{" + StringFormat_i + "\\}", "g"), arguments[StringFormat_i]);
    }
    return StringFormat_s;
}
//判断两字符串是否相同
function isSame(str1,str2){
	if(str1.length != str2.length)return false;
	if(str1 == str2)return true;
	var result = 0;
	var str1 = str1.toString();
	var str2 = str2.toString();
	for(var i = 0;i < str1.length;i++){
		result ^= (str1.charAt(i) ^ str2.charAt(i));
	}
	return result == 0;
}
function popup(popupName){
	popupName.css({"left":0+"px","top":0+ "px","display":"block"});//设置position
}
$().ready( function() {

	/* ---------- List ---------- */
	
	var $listForm = $("#listForm");// 列表表单
	if ($listForm.size() > 0) {
		var $searchButton = $("#searchButton");// 查找按钮
		var $allCheck = $("#listTable input.allCheck");// 全选复选框
		var $listTableTr = $("#listTable tr:gt(0)");
		var $idsCheck = $("#listTable input[name='ids']");// ID复选框
		var $deleteButton = $("#deleteButton");// 删除按钮
		var $pageNumber = $("#pageNumber");// 当前页码
		var $pageSize = $("#pageSize");// 每页显示数
		var $sort = $("#listTable .sort");// 排序
		var $orderBy = $("#orderBy");// 排序字段
		var $order = $("#order");// 排序方式
		var $exportButton = $("#exportButton");//导出按钮
		
		// 全选
		$allCheck.click( function() {
			var $this = $(this);
			if ($this.attr("checked")) {
				$idsCheck.attr("checked", true);
				$deleteButton.attr("disabled", false);
				$listTableTr.addClass("checked");
			} else {
				$idsCheck.attr("checked", false);
				$deleteButton.attr("disabled", true);
				$listTableTr.removeClass("checked");
			}
		});
		
		// 无复选框被选中时,删除按钮不可用
		$idsCheck.click( function() {
			var $this = $(this);
			if ($this.attr("checked")) {
				$this.parent().parent().addClass("checked");
				$deleteButton.attr("disabled", false);
			} else {
				$this.parent().parent().removeClass("checked");
				var $idsChecked = $("#listTable input[name='ids']:checked");
				if ($idsChecked.size() > 0) {
					$deleteButton.attr("disabled", false);
				} else {
					$deleteButton.attr("disabled", true)
				}
			}
		});
		
		// 批量删除
		$deleteButton.click( function() {
			var url = $(this).attr("url");
			var $idsCheckedCheck = $("#listTable input[name='ids']:checked");
			$.dialog({type: "warn", content: "您确定要删除吗?", ok: "确 定", cancel: "取 消", modal: true, okCallback: batchDelete});
			function batchDelete() {
				$.ajax({
					url: url,
					data: $idsCheckedCheck.serialize(),
					type: "POST",
					dataType: "json",
					cache: false,
					success: function(data) {
						if (data.status == "success") {
							$idsCheckedCheck.parent().parent().remove();
						}
						$deleteButton.attr("disabled", true);
						$allCheck.attr("checked", false);
						$idsCheckedCheck.attr("checked", false);
						$.message({type: data.status, content: data.message});
					}
				});
			}
		});
	
		// 查找
		$searchButton.click( function() {
			$pageNumber.val("1");
			$listForm.submit();
		});
		
		$exportButton.click( function() {
//			popup($("#div1"));
			var url = $(this).attr("url");
			$listForm.attr("action", url).submit();
		});
	
		// 每页显示数
		$pageSize.change( function() {
			$pageNumber.val("1");
			$listForm.submit();
		});
	
		// 排序
		$sort.click( function() {
			var $currentOrderBy = $(this).attr("name");
			if ($orderBy.val() == $currentOrderBy) {
				if ($order.val() == "") {
					$order.val("asc")
				} else if ($order.val() == "desc") {
					$order.val("asc");
				} else if ($order.val() == "asc") {
					$order.val("desc");
				}
			} else {
				$orderBy.val($currentOrderBy);
				$order.val("asc");
			}
			$pageNumber.val("1");
			$listForm.submit();
		});
	
		// 排序图标效果
		if ($orderBy.val() != "") {
			$sort = $("#listTable .sort[name='" + $orderBy.val() + "']");
			if ($order.val() == "asc") {
				$sort.removeClass("desc").addClass("asc");
			} else {
				$sort.removeClass("asc").addClass("desc");
			}
		}
		
		// 页码跳转
		$.gotoPage = function(id) {
			$pageNumber.val(id);
			$listForm.submit();
		}
	}
	
});