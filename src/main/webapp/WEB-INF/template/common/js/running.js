//显示模拟层
function showWin(w,h,t){//高度,宽度,文字
		var win = document.getElementById('_mask_div');
		var mask = document.getElementById('_mask');
		var width = '500'; //默认宽度
		var height = '200'; //默认高度
		var text = '后台努力加载中,请稍后...'; //默认文字
		if(w!='') width = w;
		if(h!='') height = h;
		if(t!='') text = t;
		if(win != null && win.style.display == 'none'){
			mask.style.display = 'block';
			win.style.display = 'block';
		}else{
			//浏览器
			var ua = navigator.userAgent.toLowerCase();
			var isIE = ua.indexOf('ie')>=0;
			//新激活图层
			var _newDiv = document.createElement('div');
			var loading = "<img src='../template/common/images/loading.gif' alt="+text+">&nbsp;";
			_newDiv.id='_mask_div';
			_newDiv.style.width=width+'px';
			_newDiv.style.height=height+'px';
			_newDiv.style.left='50%';
			_newDiv.style.top='50%';
			_newDiv.style.zIndex=1001;
			_newDiv.style.background = "#EEEEEE";
			_newDiv.style.border='solid 3px #898989';
			_newDiv.style.padding = "5px";
			_newDiv.style.position='absolute';
			_newDiv.style.marginLeft=-(width/2)+'px';
			_newDiv.style.marginTop=-(height/2)+'px';
			_newDiv.style.fontSize='12px';
			_newDiv.style.lineHeight=height+'px';
			_newDiv.style.textAlign = 'center';
			_newDiv.innerHTML = loading + text;
			//关闭控件
			/*var _newField = document.createElement('a');
			_newField.setAttribute('href','javascript:void(0);');
			_newField.appendChild(document.createTextNode('close'));
			_newDiv.appendChild(_newField);
			//事件绑定
			if(isIE){
				_newField.setCapture();
				_newField.attachEvent('onclick',closeWin);
			}else{
				document.captureEvents(Event.CLICK);  
				_newField.addEventListener('click',closeWin);
			}*/
			//mask图层
			var _newMask = document.createElement('div');
			_newMask.id='_mask';
			if(isIE){
				_newMask.style.position='absolute';
				_newMask.style.width=Math.max(document.documentElement.scrollWidth, document.documentElement.clientWidth)+'px';
				_newMask.style.height=Math.max(document.documentElement.scrollHeight, document.documentElement.clientHeight)+'px';
			}else{
				_newMask.style.position='fixed';
				_newMask.style.width = "100%";
				_newMask.style.height= "100%";
			}
			_newMask.style.left='0px';
			_newMask.style.top='0px';
			_newMask.style.filter='alpha(opacity=30)';
			_newMask.style.mozOpacity=0.3;
			_newMask.style.opacity=0.3;
			_newMask.style.backgroundColor='#000000';
			_newMask.style.zIndex=1000;
			//添加元素到body
			document.body.appendChild(_newMask);
			document.body.appendChild(_newDiv);
	}
}
//关闭模拟层
function closeWin(){
	var win = document.getElementById('_mask_div');
	var mask = document.getElementById('_mask');
	if(win){
		win.style.display = 'none';
		mask.style.display = 'none';
	}
}