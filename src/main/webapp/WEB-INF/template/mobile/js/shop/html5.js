(function() {
    // 页面头部
    var a = ['section', 'article', 'nav', 'header', 'footer' /* 其他HTML5元素 */];
    for (var i = 0, j = a.length; i < j; i++) {
        document.createElement(a[i]);
    }	
})();





        //记录是否阻止滚动
        var disableScroll = false;
 
        //如果弹出对话框时，底层的视图就不让滚动了
        document.addEventListener('touchmove', function(e) {
          if(disableScroll){
              e.preventDefault();
          }
         }, false);
 
        //显示弹出框
        function showInfoBox(){
            //禁止滚动
            disableScroll = true;
 
            //设置背景遮罩的尺寸
            var ch=document.documentElement.clientHeight;//屏幕的高度
            var cw=document.documentElement.clientWidth;//屏幕的宽度
            var bgWidth = Math.max(document.body.scrollWidth,cw);
            var bgHeight = Math.max(document.body.scrollHeight,ch);
            document.getElementById("bgMask").style.width = bgWidth+"px";
            document.getElementById("bgMask").style.height = bgHeight+"px";
 
            //设置消息弹出框位置（屏幕居中）
            var st=document.documentElement.scrollTop||document.body.scrollTop;//滚动条距顶部距离
            var sl=document.documentElement.scrollLeft||document.body.scrollLeft;//滚动条距左距离
            var objH=document.getElementById("infoBox").style.height;//浮动对象的高度
            var objW=document.getElementById("infoBox").style.width;//浮动对象的宽度
            var objT=Number(st)+(Number(ch)-parseFloat(objH))/2;
            var objL=Number(sl)+(Number(cw)-parseFloat(objW))/2;
            document.getElementById("infoBox").style.left = objL+"px";
            document.getElementById("infoBox").style.top = objT+"px";
 
            //显示背景遮罩
            document.getElementById("bgMask").style.display="block";
            //显示消息弹出框
            document.getElementById("infoBox").style.display="block";
        }
 
        //隐藏弹出框
        function hideInfoBox(){
            //允许滚动
            disableScroll = false;
            //隐藏背景遮罩
            document.getElementById("bgMask").style.display="none";
            //隐藏消息弹出框
            document.getElementById("infoBox").style.display="none";
        }
 