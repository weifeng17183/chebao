/**
 * 显示遮盖层
 * @param options 遮盖层的参数{loadMsg:'',wrap:object},loadMsg为显示的信息；wrap为遮盖层的覆盖对象，默认为body
 */
function showCover(options){
	var $ = jQuery ;
	var _options = options||{};
	var loadMsg = _options.loadMsg||"正在处理，请稍候。。。";
	var wrap = _options.wrap||$("body");
	var $cover = $("#coverlayer") ;
	var $coverlayerMsg = $("#coverlayerMsg") ;
	if($cover.length > 0){//显示遮盖层
		$cover.css({
			"display" : "block",
			"width" : wrap.width(),
			"height" : wrap.height()
		});
		$coverlayerMsg.css({
			"display" : "block",
			"left" :(wrap.width()-$("#coverlayerMsg",wrap).outerWidth())/2,
			"top" :(wrap.height()-$("#coverlayerMsg",wrap).outerHeight())/2
		});
	}else{//构造遮盖层
		$("<div id='coverlayer' class=\"datagrid-mask\"></div>").css({
			display:"block",
			width:wrap.width(),
			height:wrap.height(),
			"z-index" : 10000
		}).appendTo(wrap);
		if($.browser.msie && $.browser.version < 7){
			$("#coverlayer").append("<iframe src='' frameborder='0' width='100%' height='100%'></iframe>");
		}
		$("<div id='coverlayerMsg' class=\"datagrid-mask-msg\"></div>").html(loadMsg).appendTo(wrap).css({
			display:"block",
			left:(wrap.width()-$("#coverlayerMsg",wrap).outerWidth())/2,
			top:(wrap.height()-$("#coverlayerMsg",wrap).outerHeight())/2,
			"z-index" : 10001
		});
		$(window).resize(function(){//设置窗口触发resize时遮盖层大小的改变
			var _c = $("#coverlayer") ;
			if(_c.css("display") == "block"){
				_c.css({
					width:wrap.width(),
					height:wrap.height()
				});
				$("#coverlayerMsg").css({
					left:(wrap.width()-$("#coverlayerMsg",wrap).outerWidth())/2,
					top:(wrap.height()-$("#coverlayerMsg",wrap).outerHeight())/2
				});
			}
		});
	}
}
function hideCover(){
	var $ = jQuery ;
	$("#coverlayer").css({"display" : "none"});
	$("#coverlayerMsg").css({"display" : "none"});
}
jQuery().ready(function(){
var $ = jQuery ;	
/**
 * 工具类
 */
var Utils = {
	/**
	 * 获取光标位置信息
	 */
	getCursorPosInfo : function(txtview){
		if($.browser.msie){
			var workRange = document.selection.createRange();
			var len = workRange.text.length ;
			var allRange = txtview.createTextRange();
			workRange.setEndPoint("StartToStart",allRange);
			var pos = workRange.text.length;
			return {"begin" : pos-len,"end" : pos};
		} else {
			return {"begin" : txtview.selectionStart,"end" : txtview.selectionEnd};
		}
		
	},
	/**
	 * 设置命名空间
	 */
	ns : function(path,clazz,name){
		var fobj = window;
		var ps = path.split(".");
		for(var i = 0 ; i < ps.length ; i++){
			if(!fobj[ps[i]]){
				fobj[ps[i]] = {};				
			}
			fobj = fobj[ps[i]];
		}
		if(clazz && name){
			if(typeof(fobj[name]) != "undefined"){
				fobj[name] = $.extend(fobj[name],clazz);
			}else{
				fobj[name] = $.extend(clazz,{});
			}
		}
	},
	saveSuccTip : function(msg){
		alert(msg||"保存成功");
	},
	/**
	 * 添加label的鼠标效果
	 */
	fixLinkLabel : function($el){
		$el = $el||$("label[inAndOut='true']");
		$el.hover(
		  function () {
			$(this).addClass("mousein");
		  },
		  function () {
			$(this).removeClass("mousein");
		  }
		);
	},
	/**
	 * 添加table 列表的tr鼠标滑动背景颜色
	 */
	fixTrBgc : function(tableId,$tr){
		var tableId = tableId ? "#" + tableId : ".list";
		$tr = $tr||$(tableId + " tr[bgfix='true']");
		$tr.hover(
		  function () {
			$(this).addClass("bgc");
		  },
		  function () {
			$(this).removeClass("bgc");
		  }
		);
	},
	/**
	 * 提示信息
	 * @param title 标题
	 * @param msg 提示信息
	 */
	showMsg : function (title,msg,isEasyUI,showType,isLazy){
		if(isEasyUI){
			$.messager.show({
				title : title,
				msg : msg,
				timeout : 4000,
				showType : showType||'show'
			})
		}else{
			isLazy = isLazy === undefined || isLazy!==false   ? true : false;
			if(isLazy === true){
				setTimeout(function(){
					alert(msg);
				},100);
			}else{
				alert(msg);
			}
		}
	},
	showMsgNotLazy : function (title,msg,isEasyUI,showType){
		this.showMsg(title,msg,isEasyUI,showType,false);
	},
	/**
	 * comfirm
	 */
	confirm : function(title,msg,callback){
		//$.messager.confirm(title,msg,callback);
		callback(confirm(msg));
	},
	fixSingleRowSelect : function(trChb,isClickRow2Select){
		var obj = trChb;
		var $tr = $(trChb).parent().parent();
		if(isClickRow2Select===true){
			$tr.bind("click.selectrow",function(){
				if(obj.disabled){
					return;
				}				
				obj.checked = !obj.checked;
			});
		}
		$(trChb).click(function(event){
			event.stopPropagation();
		}).bind("propertychange.selectrow",function(){
			if(obj.checked){
				$tr.css("background-color" , "#FBEC88");
				$tr.attr("row-selected",true);
				//$tr.addClass("row-selected");
			}else{
				//$tr.removeClass("row-selected");
				$tr.css("background-color" , "#FFFFFF");
				$tr.attr("row-selected",false);
			}/*
			//处理全选的checkbox
			if($tb.find(".row-selected").length == rows-1){
				$firstCheckbox.attr("checked",true);
			}else{
				$firstCheckbox.attr("checked",false);
			}*/
		})
	},
	/**
	 * 注册table行选择的处理
	 */
	registRowSelect : function(options){
		var util = this;
		var isClickRow2Select = options && options.isClickRow2Select;
		$("table[selectAll]").each(function(){
			var $tb = $(this);
			var rows = this.rows.length;
			var $firstCheckbox = $tb.find("tr:first td:first :checkbox");
			$firstCheckbox.bind("click.selectall",function(){
				var checked = this.checked;
				$tb.find("tr:gt(0)").each(function(){					
					$(this).find("td:first :checkbox").each(function(){
						if(this.disabled){
							return;
						}
						this.checked = checked;
					})
				});
			});

			$tb.find("tr:gt(0) td :checkbox").each(function(){
				util.fixSingleRowSelect(this,isClickRow2Select);
			});
		});
		
	},	
	/**
	 * 判断v是否为Object对象
	 */
	isObject : function(v){
		return !!v && Object.prototype.toString.call(v) === '[object Object]';
	},
	/**
	 * 判断val是否为空（包括：null、undefined和空字符串）
	 */
	isNotBlank : function(val){
		return val !== null && val !== undefined && val !=="";
	}
	,
	/**
	 * Function 的继承方法
	 */
	extendFn : function(){		
		var oc = Object.prototype.constructor;
		return function(sb, sp, overrides){
			if(Utils.isObject(sp)){
				overrides = sp;
				sp = sb;
				sb = overrides.constructor != oc ? overrides.constructor : function(){sp.apply(this, arguments);};
			}
			var F = function(){},
				sbp,
				spp = sp.prototype;

			F.prototype = spp;
			sbp = sb.prototype = new F();
			sbp.constructor=sb;
			sb.superclass=spp;
			if(spp.constructor == oc){
				spp.constructor=sp;
			}			
			sbp.superclass = sbp.supr = (function(){
				return spp;
			});
			$.extend(sb.prototype, overrides);
			return sb;
		};
	}(),
	heightFn : function(percent){return $(window).height() * (percent || 0.6);},
	widthFn : function(percent){return $(window).width() * (percent || 0.8);},
	/**
	 * 显示遮盖层
	 * @param options 遮盖层的参数{loadMsg:'',wrap:object},loadMsg为显示的信息；wrap为遮盖层的覆盖对象，默认为body
	 */
	showMask : function(options){
		showCover(options);
	},
	hideMask : function(){
		hideCover();
	},
	/**
	 * 设置form中的form元素为disable，假如isReadonly为true，则将textarea和text设置为readonly而不设置为disabled
	 * @param $form 表单的jquery对象
	 * @param isReadonly 是否只读
	 */
	disableForm : function($form,attrName,isReadonly){
		var is_d = "true" ;
		if($form===undefined){
			$form = $form || $("form:first");
			is_d = $form.attr("is_d");
			attrName = $form.attr("dis_r");
			isReadonly = $form.attr("is_r");
		}
		if(is_d != "true"){
			return;
		}
		if(attrName =='true'){
			$form.find("[dis_r='d']").each(function(){
				$(this).attr("disabled",true);
			});
			$form.find("[dis_r='r']").each(function(){
				$(this).attr("readonly",true);
				//$(this).attr("unselectable","on");
			});
		}else{
			$.each($form.get(0).elements,function(){
				var $el = $(this) ;
				if($el.attr("nodeName") == "button" || $el.attr("type") == "button" || $el.attr("type") == "hidden"){
					return ;
				}
				if(isReadonly && ($el.attr("nodeName") == "TEXTAREA" || $el.attr("type") == "text")){
					$el.attr("readonly",true);
					//$el.attr("unselectable","on");
				}else{ 
					$el.attr("disabled",true);
				}			
			});
		}
	},
	/**
	 * 阻止事件冒泡
	 * @param jqueryExp jquery的查找表达式
	 **/
	stopPropagation : function(jqueryExp){
		$(jqueryExp||"label[stoppropagation='true']").each(function(){
			if(!this.etype){
				$(this).bind("click.st",function(event){
					event.stopPropagation();
				})
			}else{
				var _self = this ;
				$.each(_self.etype.split(","),function(i,v){
					$(_self).bind(v + ".st",function(event){
						event.stopPropagation();
					});
				});
			}
		});		
	},
	/**
	 * 金额格式
	 * @param money 金额字符
	 * @paran isDot 是否带逗号的格式化
	 **/
	fomatMoney : function(money,isDot){
		money += "";
        if(/[^0-9\.]/.test(money)) return money;
        money=money.replace(/^(\d*)$/,"$1.");
        money=(money+"00").replace(/(\d*\.\d\d)\d*/,"$1");
        isDot = isDot===undefined ? "true" : isDot;
        if(isDot){
       		money=money.replace(".",",");
        }
        var re=/(\d)(\d{3},)/;
        while(re.test(money)){
          money=money.replace(re,"$1,$2");
		}
        money=money.replace(/,(\d\d)$/,".$1");
        return  money.replace(/^\./,"0.")
    },
    parseMoney : function(vStr){
    	 return vStr.replace(/[^\d\.-]/g, "");   
    },
    /**
     * 判断是否日期
     * @param value 日期表达式
     */
    isDate : function(value){
		 var reg = /^(\d+)-(\d{1,2})-(\d{1,2})$/; 
		 var r = value.match(reg); 
		 if(r==null)return false; 
		 r[2]=r[2]-1; 
		 var d= new Date(r[1], r[2],r[3]); 
		 if(d.getFullYear()!=r[1])return false; 
		 if(d.getMonth()!=r[2])return false; 
		 if(d.getDate()!=r[3])return false;     
		 return true;
	},
	/**
	 * 将数字转换为中文金额
	 * @param num 数字
	 */
	num2chinese : function(num){
		if(!num){
			return;
		}
		num = num.toString();
		for (i = num.length - 1; i >= 0; i--) {
			num = num.replace(",", "")// 替换tomoney()中的“,”
			num = num.replace(" ", "")// 替换tomoney()中的空格
		}
		num = num.replace("￥", "")// 替换掉可能出现的￥字符
		if (isNaN(num)) {
			// 验证输入的字符是否为数字
			alert("请检查小写金额是否正确");
			return;
		}
		// ---字符处理完毕，开始转换，转换采用前后两部分分别转换---//
		var part = num.split(".");
		var newchar = "";
		// 小数点前进行转化
		for (i = part[0].length - 1; i >= 0; i--) {
			if (part[0].length > 10) {
				alert("位数过大，无法计算");
				return "";
			}// 若数量超过拾亿单位，提示
			var tmpnewchar = ""
			var perchar = part[0].charAt(i);
			switch (perchar) {
			case "0": tmpnewchar="零" + tmpnewchar ;break;
			   case "1": tmpnewchar="壹" + tmpnewchar ;break;
			   case "2": tmpnewchar="贰" + tmpnewchar ;break;
			   case "3": tmpnewchar="叁" + tmpnewchar ;break;
			   case "4": tmpnewchar="肆" + tmpnewchar ;break;
			   case "5": tmpnewchar="伍" + tmpnewchar ;break;
			   case "6": tmpnewchar="陆" + tmpnewchar ;break;
			   case "7": tmpnewchar="柒" + tmpnewchar ;break;
			   case "8": tmpnewchar="捌" + tmpnewchar ;break;
			   case "9": tmpnewchar="玖" + tmpnewchar ;break;
			}
			switch (part[0].length - i - 1) {
			 case 0: tmpnewchar = tmpnewchar +"元" ;break;
			   case 1: if(perchar!=0)tmpnewchar= tmpnewchar +"拾" ;break;
			   case 2: if(perchar!=0)tmpnewchar= tmpnewchar +"佰" ;break;
			   case 3: if(perchar!=0)tmpnewchar= tmpnewchar +"仟" ;break;
			   case 4: tmpnewchar= tmpnewchar +"万" ;break;
			   case 5: if(perchar!=0)tmpnewchar= tmpnewchar +"拾" ;break;
			   case 6: if(perchar!=0)tmpnewchar= tmpnewchar +"佰" ;break;
			   case 7: if(perchar!=0)tmpnewchar= tmpnewchar +"仟" ;break;
			   case 8: tmpnewchar= tmpnewchar +"亿" ;break;
			   case 9: tmpnewchar= tmpnewchar +"拾" ;break;
			}
			newchar = tmpnewchar + newchar;
		}
		// 小数点之后进行转化
		if (num.indexOf(".") != -1) {
			if (part[1].length > 2) {
				alert("小数点之后只能保留两位,系统将自动截段");
				part[1] = part[1].substr(0, 2)
			}
			for (i = 0; i < part[1].length; i++) {
				var tmpnewchar = ""
				var perchar = part[1].charAt(i)
				switch (perchar) {
				case "0": tmpnewchar="零" + tmpnewchar ;break;
			    case "1": tmpnewchar="壹" + tmpnewchar ;break;
			    case "2": tmpnewchar="贰" + tmpnewchar ;break;
			    case "3": tmpnewchar="叁" + tmpnewchar ;break;
			    case "4": tmpnewchar="肆" + tmpnewchar ;break;
			    case "5": tmpnewchar="伍" + tmpnewchar ;break;
			    case "6": tmpnewchar="陆" + tmpnewchar ;break;
			    case "7": tmpnewchar="柒" + tmpnewchar ;break;
			    case "8": tmpnewchar="捌" + tmpnewchar ;break;
			    case "9": tmpnewchar="玖" + tmpnewchar ;break;
				}
				if (i == 0)
					tmpnewchar = tmpnewchar + "角";
				if (i == 1)
					tmpnewchar = tmpnewchar + "分";
				newchar = newchar + tmpnewchar;
			}
		}
		// 替换所有无用汉字
		while (newchar.search("零零") != -1){
			newchar = newchar.replace("零零", "零");
		}
	    newchar = newchar.replace("零亿", "亿");
	    newchar = newchar.replace("亿万", "亿");
	    newchar = newchar.replace("零万", "万");
	    newchar = newchar.replace("零元", "元");
	    newchar = newchar.replace("零角", "");
	    newchar = newchar.replace("零分", "");
		if (newchar.charAt(newchar.length - 1) == "元"
				|| newchar.charAt(newchar.length - 1) == "角"){
			newchar = newchar + "整"
		}
	    return newchar; 
	},
    bindEasyUIENValidate : function(){
    	EasyUIValidator.addRule("en");
    },
    /**
     * 限制回退按钮，退回上一次操作页面的操作
     */
    stopBackspace : function(){
    	function isEditElement(event){
    		var target = event.target;
    		var editEl = (target.type == "text" || target.type == "textarea" || target.type == "password");
    		return (event.keyCode == 8) && (!editEl || (editEl && target.readOnly));
    	}
	    $("body").keydown(function(event){
	    	if ((event.altKey) || isEditElement(event)) { 
				event.preventDefault();
			} 
		});
    },
    /**
     * 绑定输入框回车时，触发其他元素的click事件，适用于查询输入框回车执行查询的操作
     * @param elIds 需要绑定元素的id数组
     * @param targetId 需要触发click事件的元素id
     */
    bindEnter2Target : function(elIds,targetId){
    	if(!$.isArray(elIds)){
    		elIds = [elIds];
    	}
    	$.each(elIds,function(){
    		$("#"+this).bind("keydown.enter",function(event){
    			if(event.keyCode==13){
    				$("#"+targetId).click();
    			}
    		});
    	});
    },
    /**
     * 构建crud对象
     */
    createCrud : function(options){
    	var utils = this;
    	var crudObj = this.extendFn(com.utils.CRUD,{
    		baseurl : $("form:first").attr("action"),
    		draggable : false,
    		beforeTip : true
    	});
    	 function create(opts){
    		opts = opts || {};
    		var $ = jQuery;
    		if(opts.isBindEnterQuery===true){
    			utils.bindEnter2Target(opts.enterElementIds,opts.queryBt);
    		}
    		if(opts.isBindRowSelect!==false){
    			utils.registRowSelect();
    		}
    		if(opts.isFixLinkLabel!==false){
    			utils.fixLinkLabel();
    		}
    		var crudOptions = $.extend({
    			isFixTbBgc : true,
    			dataTableId : "dataTable",
    			doReset : function(){
    				var _self = this ;
    				$(":input[search='true']").each(function(){
    					$(this).val("");
    					_self.doQuery();
    				});
    			}
    		},opts.crudOptions);
    		return new crudObj(crudOptions);
    	}
    	return create(options);
    }
};
//其他地方可以通过com.utils.Utils来访问Utils类
Utils.ns("com.utils",Utils,"Utils");
Utils = com.utils.Utils;
//Utils.disableForm();
});

