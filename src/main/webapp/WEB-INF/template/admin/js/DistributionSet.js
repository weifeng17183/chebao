function DistributionSet(setting){
  this.setting = setting;
  
  this.init = function($treeCondext,callback){
    callback($treeCondext,this.setting);
  }
  

  
  
  this.saveAreaDistriSet = function($treeCondext,uri){
   	var treeObj = $.fn.zTree.getZTreeObj($treeCondext);
    var nodes = treeObj.getSelectedNodes();
    var areaIds = "";
    if(treeObj.setting.check)
  	      nodes = treeObj.getCheckedNodes(true);
  	  for(var index=0;index<nodes.length;index++){
         areaIds += nodes[index].id+";";
  	  }
  	  
  	$.ajax({
	   type: "POST",
	   url: uri,
	   data: {"areaSet.areaIds":areaIds},
	   success: function(msg){
	     if("1"==msg)
	     $.message({type: "success", content: "保存成功"});
	   }
	});
  }
  var nodeList = [];
  this.location = function(treeContext,val){
     var zTree = $.fn.zTree.getZTreeObj(treeContext);

	 zTree.cancelSelectedNode();
	 if(val.replace(/((^\s*)|(\s*$))/,'').length == 0){
		zTree.expandAll(false);
		nodeList = zTree.transformToArray(zTree.getNodes());
		for(var i=0, l=nodeList.length; i<l; i++) {
			nodeList[i].highlight = false;
			zTree.updateNode(nodeList[i]);
		}
		return;
	 }
	for(var i=0, l=nodeList.length; i<l; i++) {
		nodeList[i].highlight = false;
		zTree.updateNode(nodeList[i]);
		if(nodeList[i].isParent)
			zTree.expandNode(nodeList[i],false);
	}
     nodeList = zTree.getNodesByParamFuzzy("name", val);
	 var $treeObj = $('#'+treeContext);
	 var parentNode = null;
	 for(var i=0, l=nodeList.length; i<l; i++) {
		nodeList[i].highlight = true;
		zTree.updateNode(nodeList[i]);
		parentNode = nodeList[i];
		if(!nodeList[i].isParent && nodeList[i].pId != 0){
			parentNode = nodeList[i].getParentNode();
		}
		zTree.expandNode(parentNode, true, null, null, null);
		$treeObj.prepend($('#'+parentNode.tId));
	}
  }
}