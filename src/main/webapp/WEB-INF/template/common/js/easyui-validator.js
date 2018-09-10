jQuery().ready(function(){
$ = jQuery;
EasyUIValidator = {
	en : function(){
		$.extend($.fn.validatebox.defaults.rules, {
			enType :{
				validator: function(value, param){
					var regExp = /[^a-zA-Z_]/g;
					return !regExp.test(value);
				},
				message: '请输入英文或英文与下划线的组合字符。'
			}
		});
	},
	cnLength : function(){
		function strlen(str) {
  			var len = 0;
  		 	for (var i = 0; i < str.length; i++) {
	       		if (str.charCodeAt(i) > 255){
	       			len += 2; 
	       		}else {
	       			len ++;
		   	 	}
  		 	}
		   	return len;
		}
		$.extend($.fn.validatebox.defaults.rules, {
			cnLength :{
				validator: function(value, param){
					var len = strlen(value);
					return len>=param[0]&&len<=param[1];
				},
				message:"输入内容长度必须介于{0}和{1}之间。（注：中文字符占两位）"
			}
		});
	},
	date : function(){
		function trans2date(value){
			value = value.replace(/-/g,'/')
			return new Date(value);
		}
		$.extend($.fn.validatebox.defaults.rules, {
			date:{   
			   validator:function(value,param){
					if(!value){
						return false
					}
					var beginDate = $("#" + param[0]).val();
					if(trans2date(value) < trans2date(beginDate)){						
						return false ;
					}					
					return true ;
				},   
			   message:'结束日期不能小于开始日期'  
		   }
		});
	},
	alpha : function(){
		$.extend($.fn.validatebox.defaults.rules, {
			alpha:{   
			       validator:function(value,param){   
			            if (value){   
			            	return /^\w{1,}$/.test(value);   
			            } else {   
			               return true;   
			            }   
			        },   
			       message:'只能由字母、数字或者下划线组成.'  
			   }
		});
	},
	_alpha : function(){
		$.extend($.fn.validatebox.defaults.rules, {
			_alpha:{   
		       validator:function(value,param){   
		            if (value){   
		            	return /^[A-Za-z0-9]+$/.test(value);
		            } else {   
		               return true;   
		            }   
		        },   
		       message:'只能由字母和数字构成.'  
		   }
		});
	},
	addRule : function(ruleName){
		var rule = EasyUIValidator[ruleName];
		if(!rule){
			alert("规则名无效。");
			return ;
		}
		rule();
	}
}
});