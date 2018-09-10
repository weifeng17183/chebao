﻿﻿/*
*
*
*/

jy.ns("jy.intshop.coupon.ui");

var ic = jy.intshop.coupon.ui || {};

jy.util.extend(ic,{
	me : {
		mainClass : "coupon",
		wrapClass : "dialog_wrap",
		titleClass : "dialog_title",
		closeClass : "dialog_close",
		contentClass : "dialog_content",
		footerClass : "dialog_footer",
		categoryWrapClass : "category_select_wrap",
		couponWrapClass : "coupon_select_wrap",
		selectwrapClass : "dialog_selectwrap",
		queryClass : "query_select",
		selectClass : "dialog_select",
		showTextContainer : "coupon_display",
		maxSelectLength : 14,
		innerTextUnSelected : "\u8bbe\u7f6e\u4f18\u60e0\u5238",
		innerTextSelected : "\u66f4\u6539\u4f18\u60e0\u5238",
		onclickContainer: "setting_coupon",
		hiddenValueContainer : "coupon_hidden",
		couponCategoryAction : "coupon!ajaxCategory.action"
	},
	modal : jy.widget.modal,
	drag : jy.widget.drag,
	init : function(title){
		if(this.wrap && this.title && this.content && this.footer)return;
		this.modal.init();
		this.wrap = $("<div id='"+this.me.mainClass+"_"+this.me.wrapClass+"' class='"+this.me.wrapClass+"'></div>");
		this.title = $("<div id='"+this.me.mainClass+"_"+this.me.titleClass+"' class='"+this.me.titleClass+"'><span class='"+this.me.titleClass+"_inner'>"+title+"</span></div>");
		this.content = $("<div id='"+this.me.mainClass+"_"+this.me.contentClass+"' class='"+this.me.contentClass+"'><strong>如果该商品没有优惠券,请选择[不选]选项并确认.</strong></div>");
		this.selectwrap = $("<div id='"+this.me.mainClass+"_"+this.me.selectwrapClass+"' class='"+this.me.selectwrapClass+"'></div>");
		this.categorywrap= $("<div style='float:left' id='"+this.me.mainClass+"_"+this.me.categoryWrapClass+"' class='"+this.me.categoryWrapClass+"'><dl><dt></dt><dd></dd></dl></div>");
		this.couponwrap= $("<div style='float:left' id='"+this.me.mainClass+"_"+this.me.couponWrapClass+"' class='"+this.me.couponWrapClass+"'><dl><dt></dt><dd></dd></dl></div>");
		this.footer= $("<div id='"+this.me.mainClass+"_"+this.me.footerClass+"' class='"+this.me.footerClass+"'></div>");
		var _ok_button = $("<input type='button' value='确认' class='formButton'>");
		var _close_class = this.me.closeClass;
		var _close = $("<div id='"+this.me.mainClass+"_"+_close_class+"' class='"+_close_class+"'></div>");
		this.drag.call(this.title,this.wrap.get(0));
		this.categorywrap.appendTo(this.selectwrap);
		this.couponwrap.appendTo(this.selectwrap);
		this.selectwrap.appendTo(this.content);
		this.title.appendTo(this.wrap);
		this.content.appendTo(this.wrap);
		this.footer.appendTo(this.wrap);
		_close.mouseover(function(e){
			$(this).addClass(_close_class+"_hover");
		});
		_close.mouseout(function(e){
			$(this).removeClass(_close_class+"_hover");
		});
		_close.click((function(that){
			return function(){
				that.close();
			}
		}(this)));
		_ok_button.click((function(that){
			return function(){
				if(that.me.showTextContainer){
					var _text = that.getText();
					$("#"+that.me.showTextContainer).html(_text.text).attr("title",_text.title);
					$("#"+that.me.hiddenValueContainer).val(_text.value);
					if(that.aclick && _text.value && _text.value.length > 0){
						that.aclick.innerHTML = that.me.innerTextSelected;
					}else{
						that.aclick.innerHTML = that.me.innerTextUnSelected;
					}
				}
				that.close();
			}
		}(this)));
		_ok_button.appendTo(this.footer);
		_close.appendTo(this.title);
		this.ajax.call(this,null);
		this.modal.shortcut.call(this.modal,this,this.close);
		this.wrap.appendTo(jy.widget.config.base.body);
	},
	show : function(title){
		var _height =  Math.floor((this.wrap.css("height").match(/(\d+)/g)[0])/2);
		var _width = Math.floor((this.wrap.css("width").match(/(\d+)/g)[0])/2);
		this.wrap.css({
			top :(jy.widget.config.base.offset.y/2 - _height)+"px",
			left :(jy.widget.config.base.offset.x/2 - _width)+"px"
		});
		this.title.find("span").html(title);
		this.modal.show();
		this.wrap.show();
	},
	close : function(){
		this.wrap.hide();
		this.modal.close.call(this.modal,null);
		this.resetSelect();
	},
	ajax : function(){
		var _this = this;
		$.ajax({
			type : "POST",
			url : _this.me.couponCategoryAction,
			dataType : "json",
			error : function(xhr, status, thrown){
				//console.log(xhr);
			},
			success : function(result,status){
				var obj = result;
				if(!jy.util.isObject(obj)){
					obj = eval("("+result+")");
				}
				if(obj.status == 'success'){
					_this.buildOptions.call(_this,obj.data);
				}else{
					$.dialog({type: "error", content: obj.message, ok: "确 定", modal: true});
				}
			}
		})
	},
	buildOptions : function(data){
		//var _size = data.length > this.me.maxSelectLength ? this.me.maxSelectLength : data.length;
		this.categorySelect = $("<select id='"+this.me.mainClass+"_"+this.me.selectClass+"_category' class='"+this.me.selectClass+"' size="+(this.me.maxSelectLength-3)+"></select>");
		this.couponSelect = $("<select id='"+this.me.mainClass+"_"+this.me.selectClass+"_coupon' class='"+this.me.selectClass+"' size="+(this.me.maxSelectLength-3)+"></select>");
		this.queryCategory = $("<input style='display:block' type='text' placeholder='输入关键字' id='"+this.me.mainClass+"_"+this.me.queryClass+"_category' class='"+this.me.mainClass+"_"+this.me.queryClass+"'></input>");
		this.queryCoupon = $("<input type='text' placeholder='输入关键字' id='"+this.me.mainClass+"_"+this.me.queryClass+"_coupon' class='"+this.me.mainClass+"_"+this.me.queryClass+"'></input>");
		
		var _category_coupon = {};
		$.each(data,(function(that){
			return function(index,item){
				$("<option value="+item.id+">"+item.name+"</option>").appendTo(that.categorySelect);
				_category_coupon[item.id] = item.items;
			}
		})(this));
		this.queryCategory.appendTo(this.categorywrap.find('dt'));
		this.queryCoupon.appendTo(this.couponwrap.find('dt'));
		this.categorySelect.get(0).selectedIndex = 0;
		this.categorySelect.change(function(that){
			return function(){
				var _me = $("#"+that.me.mainClass+"_"+that.me.selectClass+"_coupon");
				if(_category_coupon[this.value] && _category_coupon[this.value].length > 0){
					that.queryCoupon.val('').show();
					that.couponSelect.get(0).options.length = 0;
					$.each(_category_coupon[this.value],function(index,item){
						$("<option value="+item.id+">"+item.name+"</option>").appendTo(that.couponSelect);
					});
					that.couponSelect.get(0).selectedIndex = 0;
					if(!_me.length){
						that.couponSelect.appendTo(that.couponwrap.find("dd"));
						that.couponSelect.sortSelect({
							keywordId : that.queryCoupon.attr('id')
						});
					}
				}else{
					that.queryCoupon.hide();
					_me.remove();
				}
			}
		}(this));
		this.categorySelect.appendTo(this.categorywrap.find("dd"));
		this.categorySelect.sortSelect({
			keywordId : this.queryCategory.attr('id'),
			hasDefault : true
		});
	},
	resetSelect : function(){
		var _coupon = $("#"+this.me.mainClass+"_"+this.me.selectClass+"_coupon");
		if(_coupon.length)_coupon.remove();
		this.categorySelect.get(0).selectedIndex = 0;
		if(this.couponSelect.get(0))this.couponSelect.get(0).options.length = 0;
		this.queryCategory.val('');
		this.queryCoupon.hide().val('');
	},
	getText : function(){
		var _coupon = $("#"+this.me.mainClass+"_"+this.me.selectClass+"_coupon");
		var _empty = {title:"",text:"",value:""};
		if(!_coupon.length) {
			return _empty
		}
		var _category_option = this.categorySelect.find('option:selected');
		if(_category_option.val() == -1 || _category_option.val().trim().length == 0)
			return _empty;
		var _coupon_option = _coupon.find('option:selected');
		return  eval("("+"{title:\"["+ _category_option.text() + " -> " + _coupon_option.text() +"]\",text:\"["+ _category_option.text() + " -> " + _coupon_option.text() +"]&nbsp;\",value:\""+_coupon_option.val()+"\"}"+")");
	}
});
$(function(){
	$("#"+ic.me.onclickContainer).bind("click",function(e){
		ic.init(this.innerHTML);
		ic.show(this.innerHTML);
		ic.aclick = this;
	});
});