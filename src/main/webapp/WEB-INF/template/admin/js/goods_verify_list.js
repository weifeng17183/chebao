$().ready( function() {
	$.ajaxSetup({ 
		  async: false 
	}); 
	var contextPath=$("#contextPath").val();
	function processFullEdit(_self,title,url){
		_self.toFullEdit({
			winOps : {
				title :  title 
			},
			wid : "showDiv",				
			wfname : "wfname",
			url :  url+"&tk="+(new Date()).getTime()  
		});
	}
	
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
	
	function processFlow(_self,title,data){
		if($.isFunction(data)){
			data = data();
		}
		_self.confirm(title, '您确定要'+title+'数据吗?', function(r){
			if (r){
				_self.request({
					data : data
				},function(rs){
					if(rs.success){
						_self.showMsg(title,rs.msg);
						if(title=='删除'){
							_self.querySearch();
						}
						else{
							_self.pp.doClose();
							_self.pp.querySearch();
						}
					}else{
						if(title=='删除'){
							_self.showMsg(title,rs.msg);
						}
						else{
							_self.pp.showMsg(title,rs.msg);
						}
					}
				});
			}
		});
	};
	
	 var util = com.utils.Utils;
	var crudOptions = {
			back:function(){
				window.history.go(-1);
			},
			doCloseChild:function(){
				winObject.pp.doClose();
			},
			checkFn:function(){
				var chk="";
				if($("[name='ids']:checked").length==0){
					alert("请选中待审核记录");
					return ;
				}
				$("[name='ids']:checked").each(function(){
					if(chk==""){
						chk+="'"+$(this).val()+"'";
					}
					else{
						chk+=","+"'"+$(this).val()+"'";
					}
				});
				var params=$("#param").val();
				var param="{\"idchk\":\""+chk+"\",\"param\":\""+params+"\"}";
				param=eval('('+param+')');
				var url=contextPath+"/admin/goods!checkDeal.action";
				$.post(url,param,function(json){
					window.location.href=contextPath+"/admin/goods!verify.action?param="+params;
				});
			},
			checkFnDetail:function(){
				var chk="'"+$("#chk").val()+"'";
				var param="{\"idchk\":\""+chk+"\",\"param\":\""+$("#param").val()+"\"}";
				param=eval('('+param+')');
				var url=contextPath+"/admin/goods!checkDeal.action";
				$.post(url,param,function(json){
					window.location.href=contextPath+"/admin/goods!verify.action?param="+$("#param").val();
				});
			},
			unCheckFn:function(){
				var approval=$("#approval").val();
				var subVal=approval.replace(/[\r\n]/g, "\\r\\n"); 
				var param="{";
				param+="\"param\":"+"\""+$("#param").val()+"\"";
				param+=",\"idchk\":"+"\""+$("#idchk").val()+"\"";
				param+=",\"approval\":"+"\""+subVal+"\"";
				param+="}";
				param=eval('('+param+')');
				var url=contextPath+"/admin/goods!unCheckDeal.action?1=1";
				$.post(url,param,function(json){
					json=eval('('+json+')');
					if(json.status=="success"){
						winObject.doCloseChild();
						$.messager.alert("提示框",json.message);
						parent.location.href=contextPath+"/admin/goods!verify.action?param="+$("#param").val();
					}
					else{
							
					}
				});
			},
			showApproveDetail:function(){
				var chk="'"+$("#chk").val()+"'";
				var param=$("#param").val();
				var url=contextPath+"/admin/goods!unCheckPre.action?1=1&param="+param+"&idchk="+chk;
				processFixedEdit(this,"审核意见",url,200,100); 
			},
			showVerifyWindow:function(){
				var chk="";
				if($("[name='ids']:checked").length==0){
					alert("请选中待审核记录");
					return ;
				}
				$("[name='ids']:checked").each(function(){
					if(chk==""){
						chk+="'"+$(this).val()+"'";
					}
					else{
						chk+=","+"'"+$(this).val()+"'";
					}
				});
				var param=$("#param").val();
				var url=contextPath+"/admin/goods!unCheckPre.action?1=1&param="+param+"&idchk="+chk;
				processFixedEdit(this,"审核意见",url,200,100); 
			}
	};
	
		
   winObject = com.utils.Utils.createCrud({			
		crudOptions : crudOptions
	});
   var pageType=$("#pageType").val();
   if(pageType=='child'){
	   winObject.pp = parent.winObject;
   }
   
});

function reloadGoods() {
	window.location.reload();
}