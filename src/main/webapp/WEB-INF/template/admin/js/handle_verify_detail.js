var _detail = {
	 _win : '',
	 _toggle:function(args1,args2,hidden){
		var _type ;
		var $detail_win = $(_detail._win);
		if($detail_win){
			if($detail_win.is(":hidden")){
				for(var key in args1){
					_type = typeof(args1[key]);
					if(_type == "undefined") continue;
					if(_type == "string") $('#'+key).html(args1[key]);
					//if(_type == "object") $('#'+key+'\\.name').html(goods[key]['name']);
				}
				for(var key in args2){
					_type = typeof(args2[key]);
					if(_type == "undefined") continue;
					if(_type == "string") $('#'+key).html(args2[key]);
				}
				if(args2){
					$('.applyInfo').show();
				} else {
					$('.applyInfo').hide();
				}
				for(var tr in hidden){
					$("#"+hidden[tr]).hide();
				}
				$detail_win.window('open');
			}else{
				$detail_win.window('close');
			}
		}
	}
}