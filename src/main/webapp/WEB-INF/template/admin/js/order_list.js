var $goodsUpMarketBtn = $("#goodsUpMarketBtn");// 商品上架按钮
var $goodsDownMarketBtn = $("#goodsDownMarketBtn");// 商品下架按钮
var $isRecommendBtn = $("#isRecommendBtn");// 商品推荐按钮
var $isNotRecommendBtn = $("#isNotRecommendBtn");// 取消商品推荐按钮
var $isHeadRecommendBtn = $("#isHeadRecommendBtn");// 商品置顶推荐按钮
var $isNotHeadRecommendBtn = $("#isNotHeadRecommendBtn");// 取消商品置顶推荐按钮
var $allCheck = $("#listTable input.allCheck");// 全选复选框
$().ready( function() {
	var contextPath=$("#contextPath").val();
	
	function reloadGoods() {
		window.location.reload();
	}
	
	$.ajaxSetup({ 
		  async: false 
	}); 
	function processFixedEdit(_self,title,url,width,height){
		_self.toEdit({
			winOps : {
				title :  title 

			},
			wid : "showDiv",				
			wfname : "wfname",
			url :  url+"&tk="+(new Date()).getTime()
			
		});
	}
	
	
	var util = com.utils.Utils;
	var crudOptions = {
			showApproveDetail:function(){
			var chk="";
			$("[name='ids']:checked").each(function(){
				if(chk==""){
					chk+="'"+$(this).val()+"'";
				}
				else{
					chk+=","+"'"+$(this).val()+"'";
				}
			});
			var url=contextPath+"/admin/goods!downGoodsPre.action?1=1&idchk="+chk;
			processFixedEdit(this,"下架原因",url,200,100); 
			},
			downGoods:function(){
				var pageTypes= parent.document.getElementById("pageType").value;
				var downMemo=$("#downMemo").val();
				var subVal=downMemo.replace(/[\r\n]/g, "\\r\\n"); 
				var param="{";
				param+="\"idchk\":"+"\""+$("#idchk").val()+"\"";
				param+=",\"downMemo\":"+"\""+subVal+"\"";
				param+="}";
				param=eval('('+param+')');
				var url=contextPath+"/admin/goods!downGoodsAdmin.action?1=1";
				$.post(url,param,function(json){
					json=eval('('+json+')');
					if(json.status=="success"){
						winObject.doCloseChild();
						$.messager.alert("提示框",json.message);
						parent.location.href=contextPath+"/admin/goods!adminList.action?pageType="+pageTypes;
					}
					else{
						$.messager.alert("提示框","操作失败！");	
					}
				});
			},
			doCloseChild:function(){
				winObject.pp.doClose();
			}
			
	};
	
		
   winObject = com.utils.Utils.createCrud({			
		crudOptions : crudOptions
	});
   var pageType=$("#pageType").val();
   if(pageType=='child'){
	   winObject.pp = parent.winObject;
   }
	
	
	
	
	// 批量商品推荐
	$isRecommendBtn.click( function() {
		var $idsCheckedCheck = $("#listTable input[name='ids']:checked");
		$.ajax({
			url: "goods!makeGoodsIsRecommend.action",
			data: $idsCheckedCheck.serialize(),
			type: "POST",
			dataType: "json",
			cache: false,
			success: function(data) {
				if (data.status == "success") {
					window.location.reload();
					$allCheck.attr("checked", false);
					$idsCheckedCheck.attr("checked", false);
					$.dialog({type: "warn", content: data.message, modal: true, autoCloseTime: 3000});
				}
			}
		});
	});
	
	// 批量取消商品推荐
	$isNotRecommendBtn.click( function() {
		var $idsCheckedCheck = $("#listTable input[name='ids']:checked");
		$.ajax({
			url: "goods!makeGoodsIsNotRecommend.action",
			data: $idsCheckedCheck.serialize(),
			type: "POST",
			dataType: "json",
			cache: false,
			success: function(data) {
				if (data.status == "success") {
					window.location.reload();
					$allCheck.attr("checked", false);
					$idsCheckedCheck.attr("checked", false);
					$.dialog({type: "warn", content: data.message, modal: true, autoCloseTime: 3000});
				}
			}
		});
	});
	
	// 批量商品置顶推荐
	$isHeadRecommendBtn.click( function() {
		var $idsCheckedCheck = $("#listTable input[name='ids']:checked");
		$.ajax({
			url: "goods!makeGoodsIsHeadRecommend.action",
			data: $idsCheckedCheck.serialize(),
			type: "POST",
			dataType: "json",
			cache: false,
			success: function(data) {
				if (data.status == "success") {
					window.location.reload();
					$allCheck.attr("checked", false);
					$idsCheckedCheck.attr("checked", false);
					$.dialog({type: "warn", content: data.message, modal: true, autoCloseTime: 3000});
				}
			}
		});
	});
	
	// 批量取消商品置顶推荐
	$isNotHeadRecommendBtn.click( function() {
		var $idsCheckedCheck = $("#listTable input[name='ids']:checked");
		$.ajax({
			url: "goods!makeGoodsIsNotHeadRecommend.action",
			data: $idsCheckedCheck.serialize(),
			type: "POST",
			dataType: "json",
			cache: false,
			success: function(data) {
				if (data.status == "success") {
					window.location.reload();
					$allCheck.attr("checked", false);
					$idsCheckedCheck.attr("checked", false);
					$.dialog({type: "warn", content: data.message, modal: true, autoCloseTime: 3000});
				}
			}
		});
	});
	
	
	/***绑定事件****/
	$("#isBtnNew").bind("click",function(){
		importantFunc(0);
	});
	$("#goodsDownMarketBtn").bind("click",goodsDownMarketBtnFun);
	$("#goodsUpMarketBtn").bind("click",goodsUpMarketBtnFun);
	
	$("#isBtnNewCancel").bind("click",function(){
		importantFunc(1);
	});
	
	$("#isHot").bind("click",function(){
		importantFunc(2);
	});
	
	$("#isHotCancel").bind("click",function(){
		importantFunc(3);
	});
	
	$("#isBtnBestNew").bind("click",function(){
		importantFunc(4);
	});
	
	$("#isBtnBestCancel").bind("click",function(){
		importantFunc(5);
	});
	$("#isBtnNew3c").bind("click",function(){
		importantFunc(6);
	});
	$("#isBtnNewHome").bind("click",function(){
		importantFunc(7);
	});
	
	
	
});
/***
 * 批量商品下架
 */
var goodsDownMarketBtnFun=function(){
	var idsCheckedCheck = $("#listTable input[name='ids']:checked");
	if(idsCheckedCheck.length==0){
		alert("请选择要操作的记录！");
		return ;
	}
	for(var i=0;i<idsCheckedCheck.length;i++){
		var extendVal=$(idsCheckedCheck[i]).attr("extendVal");
		var spliteVal=extendVal.split(",");
		if(!(spliteVal[1]==5)){
			alert("商品名称:\""+spliteVal[2]+"\"非上架产品不能进行操作。");
			return ;
		}
	}
	winObject.showApproveDetail();
};


/***
 * 批量商品上架
 */
var goodsUpMarketBtnFun= function() {
	var idsCheckedCheck = $("#listTable input[name='ids']:checked");
	if(idsCheckedCheck.length==0){
		alert("请选择要操作的记录！");
		return ;
	}
	for(var i=0;i<idsCheckedCheck.length;i++){
		var extendVal=$(idsCheckedCheck[i]).attr("extendVal");
		var spliteVal=extendVal.split(",");
		if(!(spliteVal[1]==4)){
			alert("商品名称:\""+spliteVal[2]+"\"非审批通过产品不能进行操作。");
			return ;
		}
	}
	$.ajax({
		url: "goods!makeGoodsIsMarket.action",
		data: idsCheckedCheck.serialize(),
		type: "POST",
		dataType: "json",
		cache: false,
		success: function(data) {
			$allCheck.attr("checked", false);
			idsCheckedCheck.attr("checked", false);
			if (data.status == "success") {
				$.dialog({type: "success", content: data.message, modal: true, autoCloseTime: 5000});
				setInterval(winObject.doQuery(),5000);
			} else if (data.status == "error") {
				$.dialog({type: "error", content: data.message, ok: "确 定", modal: true, okCallback: reloadGoods});
			}
		}
	});
};



var importantFunc=function(type){
	var idsCheckedCheck = $("#listTable input[name='ids']:checked");
	if(idsCheckedCheck.length==0){
		alert("请选择要操作的记录！");
		return ;
	}
	for(var i=0;i<idsCheckedCheck.length;i++){
		var extendVal=$(idsCheckedCheck[i]).attr("extendVal");
		var spliteVal=extendVal.split(",");
		if(!(spliteVal[1]==5)){
			alert("商品名称:"+spliteVal[2]+"非上架产品不能进行操作。");
			return ;
		}
		if(spliteVal[3]=='true'&&type==2){
			alert("商品名称:"+spliteVal[2]+"已是热销不能进行操作。");
			return ;
		}
		if(spliteVal[3]=='false'&&type==3){
			alert("商品名称:"+spliteVal[2]+"已不是热销不能进行操作。");
			return ;
		}
		if(spliteVal[4]=='true'&&(type==0||type==6||type==7)){
			alert("商品名称:"+spliteVal[2]+"已是新品不能进行操作。");
			return ;
		}
		if(spliteVal[4]==false&&(type==0||type==6||type==7)){
			alert("商品名称:"+spliteVal[2]+"已不是新品不能进行操作。");
			return ;
		}
		if(spliteVal[5]=='true'&&type==4){
			alert("商品名称:"+spliteVal[2]+"已是精品不能进行操作。");
			return ;
		}
		if(spliteVal[5]=='false'&&type==5){
			alert("商品名称:"+spliteVal[2]+"已不是精品不能进行操作。");
			return ;
		}
	}
	$.ajax({
		url: "goods!importantSomeAction.action?type="+type,
		data: idsCheckedCheck.serialize(),
		type: "POST",
		dataType: "json",
		cache: false,
		success: function(data) {
			if (data.status == "success") {
				window.location.reload();
				$allCheck.attr("checked", false);
				idsCheckedCheck.attr("checked", false);
				$.dialog({type: "warn", content: data.message, modal: true, autoCloseTime: 3000});
			}
		}
	});
};

