jQuery().ready(function(){
var $ = jQuery;
//设置访问命名空间	
com.utils.Utils.ns("com.utils");
var curdId=(new Date()).getTime();
/**
 * 通用的CRUD操作js类，提供新增、删除、修改和列表的基本操作
 */
com.utils.CRUD = function(config){
	this.pp = this;
	$.extend(this,config);	
	//设置table的鼠标滑动效果
	if(this.isFixTbBgc){this.util.fixTrBgc(this.dataTableId)}; 	
	this.initialize();
}

$.extend(com.utils.CRUD.prototype,{
		windows : {}, 
		controler : null,//控件控制的对象
		util : com.utils.Utils,//工具类的对象
		baseurl : "",//基本的URL	
		pp : this,//父界面crud对象
		isFixTbBgc : false,//是否增加鼠标滑动table的背景效果
		isEasyUIValidate : true,//是否使用EasyUI的校验，默认为是
		dataTableId : "",//数据table的id
		//数据保存后的callback
		saveCallback : function(params,title){
			if(params.success){
				this.pp.setTipMsg(title,params.msg||"保存成功。",false,null,false);
				this.pp.doQuery();
			}else{
				this.showMsg(title,params.msg||"保存失败。",false,null,false);
				return ;
			}			
		} ,
		//数据删除后的callback，默认为查询
		deleteCallback : function(rs,title){
			if(rs.success){
				this.pp.setTipMsg(title,rs.msg||"删除数据成功。",false,null,false);
				this.doQuery();
			}else{
				this.showMsg(title,rs.msg,null,null,false);
			}
			
		},
		beforeTip : false,//ajax调用后，立刻提示，不需要刷新后再提示
		hasTipMsg : false ,//是否有提示信息
		msgTitle : "" ,
		msgContent : "",
		queryMethod : "toList",
		deleteMethod : "doDelete",
		activeWinInfo : {
			type : "",
			win : null
		},
		minimizable : false,//是否最小化
		collapsible : false,//是否可折叠
		resizable : false,
		draggable : false
	},{
	/**
	 * 初始化
	 */
	initialize : function(){
		var $hasTipMsg = $("#has_tip_msg") ;		
		if(this.hasTipMsg){//假如有消息需要提示则提示信息
			var $msgTitle = $("#msg_title") ;
			var $msgContent = $("#msg_content") ;
			this.showMsg(this.msgTitle,this.msgContent);
			$hasTipMsg.val("") ;
			$msgTitle.val("") ;
			$msgContent.val("");
		}
	},
	/**
	 *设置提示信息
	 */
	setTipMsg : function(msgTitle,msgContent,isEasyUI,showType,isLazy){
		if(this.beforeTip){
			this.showMsg(msgTitle,msgContent,isEasyUI,showType,isLazy);
			return ;
		}
		var $hasTipMsg = $("#has_tip_msg") ;
		var $msgTitle = $("#msg_title") ;
		var $msgContent = $("#msg_content") ;

		if($msgTitle.length <= 0){
			$("form:first").append("<input type='hidden' id='msg_title' name='msgTitle'/>"+
				"<input type='hidden' id='msg_content' name='msgContent'/>"+
				"<input type='hidden' id='has_tip_msg' name='hasTipMsg'/>");
			$msgTitle = $("#msg_title") ;
		    $msgContent = $("#msg_content") ;
			$hasTipMsg = $("#has_tip_msg") ;
		}
		$msgTitle.val(msgTitle);
		$msgContent.val(msgContent);
		$hasTipMsg.val("true");
		
	},
	/**
	 * 执行动作
	 * @param 动作名
	 */
	doAction : function(actionName){
		$("#method").val(actionName);
		$("form:first").submit();
	},
	/**
	 * 新增记录
	 */
	toEdit : function(params){
		if(!this.util.isNotBlank(params.url)){
			var url= this.baseurl + "?method=toEdit&opType=" + params.opType ;
			if(params.id){
				url += "&id=" + encodeURIComponent(params.id);
			}	
			params.url = url ;
		}	
		this.renderWindow(params);
	},
	/**
	 * 保存
	 */
	doSave : function(method,title){
		//if(this.doValidate() && confirm("您确定要保存吗?")){
		if(this.doValidate()){
			var _self = this ;
			this.pp.confirm(title||"保存","您确定要保存吗?",function(f){
				if(!f) return;
				$("#method").val(method||"doSave");
				_self.request({
					data :  $("form:first").serialize() + "&isAsync=1"
				},function(rs){
					_self.saveCallback(rs,title);
				});
			});
			
		}
	},
	/**
	 * 查询
	 */
	doQuery : function(method){
		this.doAction(method||this.queryMethod);
	},
	/**
	 * 删除
	 * @param id
	 */
	doDelete : function(id,title,delFn){
		title = title || "删除数据";
		if(!this.util.isNotBlank(id)){
			var ids=[];
			$("tr[row-selected=true] td :checkbox[disabled!=true]").each(function(){
				ids.push(this.value);
			});
			if(ids.length < 1){
				this.showMsg(title,"没有选择要删除的任何数据。");
				return false ;
			}
			id = ids.join(",");
		}
		var _self = this;
		this.confirm(title, '您确定要删除数据吗?', function(r){
			if (r){
				function _delFn(param){
					_self.request({
						data : {"id" : param,"method" : _self.deleteMethod,"isAsync" : "1"}
					},function(rs){
						_self.deleteCallback(rs,title);
					});
				}
				delFn = delFn || _delFn;
				delFn(id);
			}
		});
		
	},
	/**
	 * 关闭编辑窗口
	 */
	doClose : function(){
		//this.editWindow.dialog("close");
		if(this.activeWinInfo.type == "win"){
			this.activeWinInfo.win.window("close");
		}else{
			this.activeWinInfo.win.dialog("close");
		}
		this.activeWinInfo = {};
	},
	/**
	 * 校验数据
	 */
	doValidate : function (){
		if(this.isEasyUIValidate){
			return $("form:first").form("validate");
		}
		var flag = true ;
		$(":input[required='true']").each(function(){
			var $el = $(this) ;
			if($.trim($el.val()) == ""){
				$el.focus();
				alert($el.attr("label") + "不允许为空。");
				flag = false ;
				return false ;
			}
		});
		return flag ;
	},
	/**
	 * ajax请求
	 * @param options 请求的覆盖参数
	 * @param callback 请求回调
	 */
	request : function(options,callback){
		var _self = this ;
		if(options.isShowMask !== false){
			_self.pp.util.showMask();
		}
		var conf = {
		   type: "POST",
		   //contentType : "application/x-www-form-urlencoded;charset=GBK",
		   url: _self.baseurl,
		   dataType : "json",
		   async: true,		   
		   data: {},
		   success: function(rs){
		   		_self.pp.util.hideMask();
		   		try{
			    	if(callback){
			    		callback.apply(_self,[rs]);
			    	}
		   		}finally{
		   			//_self.pp.util.hideMask();
		   		}
		   },
		   error : function(){
			  _self.pp.util.hideMask();
			  _self.pp.util.showMsg("请求后台","请求失败.");
		   }
		}
		$.extend(conf,options);
		if(!conf.url){
			_self.pp.util.showMsg("参数错误","url为空，请设置。");
		}
		$.ajax(conf);
	},
	/**
	 * 构造窗口的html
	 * @param params 构造窗体的参数 
	 * @param wid 窗口DIV的 id
	 * @param wfname 窗口中frame的 id
	 */
	createWinHtml : function(params,wid,wfname){	
		var url = params.url;		
		var winHtml = [];
		var height = params.winOps.height;
		height = height || this.util.heightFn();
		var width = params.winOps.width;
		width = width || this.util.widthFn();
		var scrolling = params.scrolling || 'no';
		winHtml.push("<div id=\""+wid+"\" style=\"padding:1px;1;0px;1px;width:"+width+"px;height:"+height+"px;display:hidden\">");
		winHtml.push("<iframe frameborder=\"0\" scrolling=\""+scrolling+"\" style='overflow:hidden;' width=\"100%\" height=\"99%\" id=\""
		+wfname+"\" name=\"" + wfname + "\"></iframe>");
		winHtml.push("</div>");
		return winHtml.join("");
	},
	/**
	 * 构造编辑窗口
	 */
	renderWindow : function(params){
		params = params ||{};
		var winType = "win";
		var wid = params.wid || winType + "Div";//编辑窗口div的id
		var wfname = params.wfname || winType + "Frame";//编辑窗口包含的iframe名称/ID	
		var _self = this ;
		var options = {
			title : "编辑数据",//编辑窗口的标题
			minimizable : _self.minimizable,//是否最小化
			collapsible : _self.collapsible,//是否可折叠
			resizable : _self.resizable
		};
		options = $.extend(options,params.winOps||{});
		params = $.extend({},params,{winOps : options});
		this.renderWin(params,winType,wid,wfname);		
	},
	/**
	 * 构造对话窗口
	 * @param params 窗口构造参数
	 */
	renderDialog : function(params){
		params = params ||{};
		var winType = "dialog";
		var wid = params.wid || winType + "Div";//对话窗口div的id
		var wfname = params.wfname || winType + "Frame";//对话窗口包含的iframe名称/ID	
		var _self = this ;
		var options = {
			title : "选择数据",
			buttons : [{
				text:'确定',
				handler:function(){
					if(frames[wfname].getSelectedInfo===undefined){
						alert("选择框没有提供getSelectedInfo方法。");
						return false;
					}
					var selectInfos = frames[wfname].getSelectedInfo();
					var selectCallback = params.selectCallback ;
					var isAllowNotSelect = params.isAllowNotSelect;//是否允许不选择点击确定
					isAllowNotSelect = isAllowNotSelect === undefined ? false : isAllowNotSelect;
					if(_self.util.isNotBlank(selectInfos) || isAllowNotSelect){
						var crs = selectCallback(selectInfos);
						 if(typeof(crs) == "string"){
							_self.pp.util.showMsg("选择记录",crs);
						}else if(crs!==false){
							_self.windows[wid].dialog('close');
						}				
					}else{
						_self.pp.util.showMsg("选择记录","没有选择任何记录。");
					}
				}
			},{
				text:'关闭',
				handler:function(){
					_self.windows[wid].dialog('close');
				}
			}]
		};
		options = $.extend(options,params.winOps||{});
		params = $.extend({},params,{winOps : options});
		this.renderWin(params,winType,wid,wfname);
	},
	/**
	 * 渲染窗口
	 * @param winType 类型
	 * @param params 构造窗体的参数 
	 * @param wid 窗口DIV的 id
	 * @param wfname 窗口中frame的 id
	 */
	renderWin : function(params,winType,wid,wfname){
		var win = this.windows[wid];
		var url = params.url;
		if(win){
			if(winType == "win"){
				win.window("open");
				$(window).resize();
			}else{
				win.dialog("open");
				$(window).resize();
			}			
		}else{
			params.winOps.heightFn = params.getHeight || this.util.heightFn
			params.winOps.height = params.winOps.heightFn();
			params.winOps.widthFn = params.getWidth || this.util.widthFn;	
			params.winOps.width = params.winOps.widthFn();	
			win = this.createWin(params,winType,wid,wfname);
		}
		this.activeWinInfo.type = winType;
		this.activeWinInfo.win = win;
		setTimeout(function(){
			$("#" + wfname).attr("src",url);
		},1);		
	},
	/**
	 * 构造窗口
	 * @param winType 类型
	 * @param params 构造窗体的参数 
	 * @param wid 窗口DIV的 id
	 * @param wfname 窗口中frame的 id
	 */
	createWin : function(params,winType,wid,wfname){		
		var url = params.url;	
		$("body").append(this.createWinHtml(params,wid,wfname));//生成窗口的div和iframe				
		var _self = this ;
		var options = {//覆盖窗口基本配置属性
			top:'10%',			
			modal : true,//模式窗口
			draggable : _self.draggable,//不拖动
			onClose : function(){//关闭选择框时，清掉frame的内容
				$("#" + wfname).attr("src","");
			}
		};
	   options = $.extend(options,params.winOps||{});
		var win = $('#'+wid) ;
		if(winType == "win"){
			$('#'+wid).window(options);
			$(window).resize(function(){
				if(win.window('options').closed){
					return ;
				}
				win.window({
					"width" : options.widthFn(),
					"height" : options.heightFn()
				});			
			});
		}else{
			win.dialog(options);
			$(window).resize(function(){
				if(win.dialog('options').closed){
					return ;
				}
				win.dialog({
					"width" : options.widthFn(),
					"height" : options.heightFn()
				});			
			});
		}
		this.windows[wid] = win;
		return win ;
		
	},
	/**
	 * 模式提示信息
	 */
	showTip : function(title,msg,type,callback){
		$.messager.alert(title,msg,type,callback);
	},
	showMsg : function (title,msg,isEasyUI,showType,isLazy){
		this.pp.util.showMsg(title,msg,isEasyUI,showType,isLazy);
	},
	/**
	 * comfirm
	 */
	confirm : function(title,msg,callback){
		//$.messager.confirm(title,msg,callback);
		//var f = ;
		this.pp.util.confirm(title,msg,callback);
	},
	/**
	 * 绑定编辑操作
	 * @param 编辑标题
	 */
	bindEdit : function(options){
		var _self = this ;
		function processOp(){
			var $label = $(this);
			var opType = $label.attr("bt");
			$label.bind("click." + opType,function(){
				var common = {
					"id" : $label.val(),
					"opType" : opType
				};
				var lt = $label.attr("tt") ;
				if($label.attr("tt")){
					if(!options.winOps){
						options.winOps = {};
					}
					options.winOps.title = lt;
				}
				_self.toEdit($.extend({},common,options));
			})
		}
		$("label[bt='modify']").each(processOp);	
		$("label[bt='view']").each(processOp);
		$("input[bt='add']").each(function(){
			var $bt = $(this);
			$bt.bind("click.add",function(){				
				_self.toEdit($.extend({"opType" : "add"},options));
				$(this).blur();
			})
		});
	},
	/**
	 * 绑定删除操作
	 * @param 删除标题
	 */
	bindDel : function(title){
		var _self = this ;
		$("label[bt='del']").each(function(){
			var $label = $(this);
			$label.bind("click.delete",function(){
				_self.doDelete($label.val(), $label.attr("delTitle") || title);
				$(this).blur();
			})
		});
	},
	getNavHtml : function(title){
		return "<span class=\"navigation\" style='position:relative;top:-2px;color:black;font-weight:normal'>"+title+"</span>";
	},
	/**
	 * 最大化并且带导航条的编辑窗口
	 */
	toFullEdit : function(params){
		params = params || {};
		var winOps = params.winOps || {};
		var title = this.getNavHtml(winOps.title||"");
		winOps = $.extend(winOps,{
				title : title,
				maximized : true,
				maximizable : false,
				closable : true,
				border : false
			});
		params.winOps = winOps ;
		this.toEdit(params);
		var win = this.windows[params.wid];
		if(!win){
			$(".panel").css({"padding":"0","background-color":"FFFFFF","border":"0px"});
		}
		$(window).resize();
	},
	/**
	 * submit提交处理
	 */
	doSaveBySubmit:function($form,fn){
		var noFreshFrame = "iframe_" + curdId;
		if($("#"+noFreshFrame).length < 1){
			$('body').append("<iframe src='' frameborder='0' width='0' height='0' id='" + noFreshFrame +"' name='"+noFreshFrame+"'/>");
		}
		var targetName = $form.attr("target");
		$form.attr("target",noFreshFrame);
		if($.isFunction(fn)){
			fn.call(this);
		}else{
			$form.submit();
		}
		$form.attr("target",targetName);
	}
});
});