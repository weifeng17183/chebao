var handleGoodsCategory = {
		options :{windId:"",inputId:"goodsTypeInput",url:"",clickId:"",displayType:"tree",titleId:"",title:"",treeId:"treeDemo"},
		isload : false,
		isTree : true,
		buttonHide : false,
		zNodes : [],
		onInitSuccess : "",
		savePreVal :"",
		checkedIds : "goodsTypeVal",
		checkedIds4Brand : "goodsBrandVal",
		 setting : {
				view: {
					selectedMulti: false
				},
				  data: {
						simpleData: {
							enable: true,
							idKey: "id",
							pIdKey: "pId",
							rootPId: 0
						}
					},
					async: {
						enable: true,
						otherParam: ["goodsTypeIds", ""],
						url:""
					},
					callback: {
						onCheck: goodsTreeOnCheck,
						onAsyncError:goodsTreeOnAsyncError,
						onAsyncSuccess:goodsTreeOnAsyncSuccess
					},
					view: {
				fontCss: getFontCss
			}
		    },
				
		    openGoodsTreeTemp : function (object,$treeTemp,$goodsTree,e){
		    	 var src_offset = $(e.target).offset(); 
		    	$treeTemp.css({left:src_offset.left+30,top:src_offset.top});
				var $buttons = $('#buttonArea');
				if(handleGoodsCategory.buttonHide)
					$buttons.find(":input").hide();
				else
					$buttons.find(":input").show();
		    	if(object.displayType!="tree"){
		    		if($treeTemp.css("display")=="none" && handleGoodsCategory.options.url==object.url){
		    			$treeTemp.show("500",function(){
		    				$(this).css("display","block");
		    			});
		    		}else if($treeTemp.css("display")=="none" && handleGoodsCategory.options.url!=object.url){
		    			$goodsTree.children().remove();
		    			handleGoodsCategory.options = $.extend(handleGoodsCategory.options,object);
		    			$treeTemp.show("500",function(){
		    				$("#"+handleGoodsCategory.options.titleId).text(handleGoodsCategory.options.title);
		    				$(this).css("display","block");
		    				handleGoodsCategory.displayContext($goodsTree);
		    			});
		    		}else if($treeTemp.css("display")=="block" && handleGoodsCategory.options.url!=object.url){
		    			$goodsTree.children().remove();
		    			handleGoodsCategory.options = $.extend(handleGoodsCategory.options,object);
		    			$treeTemp.show("500",function(){
		    				$("#"+handleGoodsCategory.options.titleId).text(handleGoodsCategory.options.title);
		    				handleGoodsCategory.displayContext($goodsTree);
		    			});
		    			
		    		}
		    	}else{
					var checkeds = $("#"+handleGoodsCategory.checkedIds).attr("value");
					var key = $("#key");
					key.bind("focus", focusKey).bind("blur", blurKey).bind("propertychange", searchNode).bind("input", searchNode);
				if($treeTemp.css("display")=="none" && handleGoodsCategory.options.url==object.url){
						$treeTemp.show("500",function(){
							$(this).css("display","block");
						});
				}else if($treeTemp.css("display")=="none" && handleGoodsCategory.options.url!=object.url){
					$goodsTree.children().remove();
					handleGoodsCategory.options = $.extend(handleGoodsCategory.options,object);
					return $treeTemp.show("500",function(){
						$("#"+handleGoodsCategory.options.titleId).text(handleGoodsCategory.options.title);
						$(this).css("display","block");
						handleGoodsCategory.isload = true;
						handleGoodsCategory.setting.async.url = handleGoodsCategory.options.url;
						handleGoodsCategory.setting.async.otherParam[1] = checkeds;
						var key = $("#key");
						key.val('');
						$.fn.zTree.init($goodsTree,handleGoodsCategory.setting,handleGoodsCategory.zNodes,handleGoodsCategory.onInitSuccess);
					});
				}else if($treeTemp.css("display")=="block" && handleGoodsCategory.options.url!=object.url){
					$goodsTree.children().remove();
					handleGoodsCategory.options = $.extend(handleGoodsCategory.options,object);
					$treeTemp.show("500",function(){
						$("#"+handleGoodsCategory.options.titleId).text(handleGoodsCategory.options.title);
						handleGoodsCategory.setting.async.url = handleGoodsCategory.options.url;
						handleGoodsCategory.setting.async.otherParam[1]=$("#"+handleGoodsCategory.checkedIds).attr("value");
						$.fn.zTree.init($goodsTree,handleGoodsCategory.setting,handleGoodsCategory.zNodes,handleGoodsCategory.onInitSuccess);
					});
					
				}
		      }
		    },
		    displayContext : function($htmlTag){
		    	$.ajax({
					url:handleGoodsCategory.options.url,
					success:function(result){
		    		var objects = eval('('+result+')');
		    		var html ="";
		    		var checked4BrandVal = $("#"+handleGoodsCategory.checkedIds4Brand).attr("value");
		    		for(var index in objects){
		    			html += '<li class="mark"><input type="Checkbox" name="brand" ';
		    			if(checked4BrandVal!="" && checked4BrandVal.indexOf(objects[index].id)>-1)
		    				html += 'checked';
		    			
		    			html +=' ref="'+objects[index].name+'" value="'+objects[index].id+'">'+objects[index].name+'</li>';
		    		}
		    		$htmlTag.append(html);
		    		$("input[type='checkbox']",$htmlTag).live('click',function(){
		    			handleGoodsCategory.fillInputVal($htmlTag);
		    		});
		    		if(handleGoodsCategory.onInitSuccess){
						handleGoodsCategory.onInitSuccess();
					}
		    	}
				});
		    },
		    fillInputVal : function($parent){
		    	var fillVal = "";
		    	$("input:checked",$parent).each(function(){
		    		//if($(this).attr("checked")){
		    			fillVal +=$(this).attr("ref")+";";
		    		//}
		    	});
		    	$("#"+handleGoodsCategory.options.inputId).attr("value","").attr("value",fillVal.substr(0,fillVal.length-1));
		    },
		    childTreeCheckedIds : new Array(),
		    treeCheckedIds : new Array(),
		    treeCheckedNames : new Array(),
		    classicalCheckIds : new Array(),
		    classicalCheckNames : new Array(),
		    getGoodsCategoryChecked : function(goodsTree){
		    	if(handleGoodsCategory.options.displayType!="tree"){
		    		$("input:checked",$("#"+goodsTree)).each(function(){
		    			//if($(this).attr("checked")){
		    				handleGoodsCategory.classicalCheckIds.push($(this).attr("value"));
		    			//}
		    		});
		    	}else{
		    	  var treeObj = $.fn.zTree.getZTreeObj(goodsTree);
				  var nodes = treeObj.getSelectedNodes();
				  if(treeObj.setting.check)
		    	      nodes = treeObj.getCheckedNodes(true);
		    	  for(var index=0;index<nodes.length;index++){
		    	  	if(!nodes[index].isParent){
		    	  		handleGoodsCategory.childTreeCheckedIds.push(nodes[index].id);
		    	  	}
		    		  handleGoodsCategory.treeCheckedIds.push(nodes[index].id);
		    		  handleGoodsCategory.treeCheckedNames.push(nodes[index].name);
		    		 // alert("ID: "+nodes[index].id+" Name: "+nodes[index].name);
		    	  }
		    	}
		    	handleGoodsCategory.closeGoodsTreeTemp($("#"+handleGoodsCategory.options.windId));
		    },
		    getGoodsCheckedId : function(){
		    	return handleGoodsCategory.treeCheckedIds;
		    },
		    getGoodsCheckedName : function(){
		    	return handleGoodsCategory.treeCheckedNames;
		    },
		    closeGoodsTreeTemp : function($treeTemp,$cancelBut){
		    	$treeTemp.hide("500",function(){
		    		$(this).attr("display","none");
		    		if($cancelBut!="" && $cancelBut!=null){
		    			$("#"+handleGoodsCategory.checkedIds).attr("value","");
			    		if(handleGoodsCategory.options.displayType=="tree"){
			    			handleGoodsCategory.options.url = "";
			    			handleGoodsCategory.treeCheckedIds.length = 0;
			    			handleGoodsCategory.savePreVal = "";
			    		}else{
			    			$("input[type='checkbox']",$treeTemp).attr("checked","");
			    			handleGoodsCategory.classicalCheckIds.length = 0;
			    		}
			    		$("#"+handleGoodsCategory.options.inputId).attr("value","");
		    		}
		    	});
		       $("#masking4goods").animate({opacity:0},500,function(){
                 $(this).css({display:"none"});
               });
		    }
         
};
function goodsTreeOnCheck(event, treeId, treeNode) {

	var $goodsInput = $("#"+handleGoodsCategory.options.inputId);
	var fillVal = "";
//	fillVal +=treeNode.name+";";
//	if(treeNode.checked){
//		handleGoodsCategory.savePreVal += fillVal;
//	}else{
//		handleGoodsCategory.savePreVal = handleGoodsCategory.savePreVal.replace(fillVal,"");
//	}
	
	 var treeObj = $.fn.zTree.getZTreeObj(handleGoodsCategory.options.treeId);
	  var nodes = treeObj.getCheckedNodes(true);
	  
	  for(var index=0;index<nodes.length;index++){
		  fillVal += nodes[index].name+";";
	  }
	$goodsInput.attr("value","").attr("value",fillVal.substr(0,fillVal.length-1));
	
};
function goodsTreeOnAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown){
}
function goodsTreeOnAsyncSuccess(event, treeId, treeNode, msg){
}
function getFontCss(treeId, treeNode) {
	return (!!treeNode.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
}
function focusKey(e) {
var key = $("#key");
	if (key.hasClass("empty")) {
			key.removeClass("empty");
		}
	}
function blurKey(e) {
var key = $("#key");
	if (key.get(0).value === "") {
		key.addClass("empty");
	}
}
var nodeList = [];
function searchNode(e) {
	var key = $("#key");
	var zTree = $.fn.zTree.getZTreeObj(handleGoodsCategory.options.treeId);
	if(!zTree) return;
	var value = $.trim(key.get(0).value);
			//zTree.expandAll(false);
			zTree.cancelSelectedNode();
	if (key.hasClass("empty")) {
		value = "";
	}
	if (value === "") {
		if(handleGoodsCategory.isTree){
		zTree.expandAll(false);
		nodeList = zTree.transformToArray(zTree.getNodes());
		for( var i=0, l=nodeList.length; i<l; i++) {
			nodeList[i].highlight = false;
			zTree.updateNode(nodeList[i]);
		}
		}
		return;
	}
	updateNodes(false);
	nodeList = zTree.getNodesByParamFuzzy("name", value);
	updateNodes(true);
}
function updateNodes(highlight) {
	var zTree = $.fn.zTree.getZTreeObj(handleGoodsCategory.options.treeId);
	var item = "";
	var $treeDemo = $('#'+handleGoodsCategory.options.treeId);
	if(nodeList && nodeList.length > 0){
	for( var i=0, l=nodeList.length; i<l; i++) {
		nodeList[i].highlight = highlight;
		zTree.updateNode(nodeList[i]);
		if(handleGoodsCategory.isTree){
			if(highlight){
				zTree.expandNode(nodeList[i], true, null, null, null);
				zTree.selectNode(nodeList[i],false);
			}
			//zTree.expandNode(nodeList[i],highlight,false,false,false);
		}else{
			item = $('#'+nodeList[i].tId);
			$treeDemo.prepend(item);
		}
	}}else{
		zTree.expandAll(false);
	}
}
function filter(node) {
	return !node.isParent && node.isFirstNode;
}