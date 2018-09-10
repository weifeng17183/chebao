/**
 * 描述:单位代码和账号联动实现功能
 * 作者:文春宏
 */
var isOnLoad = false;
var comBox = {
	//以下参数如果默认相同可以不重载
	/* 按照以下方法在自己JS里进行参数重载
	comBox.init({
			url:$("form:first").attr("action"),
			hiddenId : "id_inneracc"
		});
	*/
	oriId : "corpCode",//单位ID
	param : "corpCode", //根据单位查询账号时的后台参数 ;后台用 request.getParameter(param)去获取
	targetId : "inneracc",//账号ID
	targetParam : "inneracc",//页面刷新时传到后台的参数;用 request.getParameter(param)去获取
	url : "",
	method : "getAccByCorpCode",//根据单位取账号的业务处理方法
	refreshMed : "addRev", //选择账号后的页面刷新方法
	hiddenId : "", //页面隐藏域的id;用于刷新页面后取选择的账号.
	isRefresh : "true", //是否重新刷新页面.默认为是
	other : "accType"//其它参数
};

/**
 * 描述:页面初化方法根据单位代码加载账号
 * @param _self
 * @param url
 * @param oriId
 * @param method
 * @param targetId
 * @param hiddenId
 */
function loadTarget(_self,url,oriId,method,targetId,hiddenId,isRefresh,param,other){
	var corpCodeVal = $("#" + oriId).combobox("getValue");
	if (corpCodeVal !== undefined) {
		if (isRefresh == 'true') {
			_self.toLoad(_self, method, url, oriId, targetId,corpCodeVal,param,other,$("#"+hiddenId).val());
		}
	}
};
$.extend(comBox,
		{
			init : function(params) {
				comBox = $.extend(comBox,params);
				this.enter(this, comBox.url, comBox.oriId,comBox.method,
						comBox.targetId ,comBox.hiddenId,comBox.refreshMed,comBox.isRefresh,
						comBox.param,comBox.targetParam,comBox.other);
				loadTarget(this, comBox.url,comBox.oriId,comBox.method, 
						comBox.targetId,comBox.hiddenId,comBox.isRefresh,
						comBox.param,comBox.other);
			},
			
			enter : function(_self, url, oriId, method, targetId,hiddenId,refreshMed,isRefresh,param,targetParam,other) {
				var preCorpCode;
				//combobox绑定单位代码处理框
				$('#' + oriId).combobox({
					//添加网点的处理方法
					onLoadSuccess : function(){
						if (isOnLoad == true) {
							_self.toLoad(_self, method, url, oriId, targetId,$(this).combobox("getValue"),param,other);
						}
						isOnLoad= true;
					},
					//键盘按下处理方法
					onEnter : function() {
						loadTarget(_self,url,oriId,method,targetId,hiddenId,isRefresh,param,other);
					},
					//鼠标选择处理方法
					onSelect : function() {
						var corpCodeVal = $(this).combobox("getValue");
						if (preCorpCode != corpCodeVal) {
							_self.toLoad(_self, method, url, oriId, targetId,corpCodeVal,param,other);
							$(this).combobox("textbox").focus();
						}
						preCorpCode = corpCodeVal;
						setTimeout(function() {
							$("#" + targetId).combobox("textbox").focus();
						}, 100);
					}
				});
				//combobox绑定账号处理框
				$('#' + targetId).combobox({
					//键盘按下处理方法
					onEnter : function() {
						var accVal = $(this).combobox("getValue");
						if (accVal !== undefined) {
							if (isRefresh == 'true') {
								_self.doRefresh(_self, refreshMed, url, targetParam,accVal,oriId,other);
							}
						}
					},
					//鼠标选择处理方法
					onSelect : function() {
						var accVal = $(this).combobox("getValue");
						if (isRefresh == 'true') {
							_self.doRefresh(_self, refreshMed, url, targetParam,accVal,oriId,other);
						}
					}
				});
				//账号加载成功将光标置于账号输入框
				if (isRefresh == 'true') {
					$('#' + targetId).combobox({
						valueField : 'id',
						textField : 'text',
						onLoadSuccess : function() {
							setTimeout(function() {
								$("#" + targetId).combobox("textbox").focus();
							}, 100);
	
						}
					});
				}
			},
			//根据单位代码加载账号
			toLoad : function(_self, method, url, oriId, targetId,val,param,other,targetSelectedValue) {
				if (val != '' && val !== undefined) {
					$("#" + targetId).combobox(
							"reload",
							url + "?method=" + method + "&"+param+"="
									+ val+"&defaultValue="+targetSelectedValue+"&"+other+"="+$("#"+other).val()+"&tk="+(new Date()).getTime());
				}else{
					$("#" + targetId).combobox("loadData",[]);
				}
			},
			//根据选择的账号重新刷新页面
			doRefresh : function(_self, refreshMed, url, targetParam,val,oriId,other) {
				var util = com.utils.Utils;
				util.showMask();
				window.location.href=url + "?method=" + refreshMed + "&" + targetParam + "="+ val+"&"+other+"="+$("#"+other).val();
			}
		});
