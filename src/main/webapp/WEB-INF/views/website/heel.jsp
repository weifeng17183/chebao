<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
	<head>
		<%@include file="/common/head.jsp"%>
		<%@include file="/common/head2.jsp"%>
		<meta charset="UTF-8">
		<title>首页</title>
		<!-- Link Swiper's CSS -->
		<link rel="stylesheet" href="${base}/views/website/css/common.css?j=0" />
		<link rel="stylesheet" href="${base}/views/website/css/reset.css" />
		<link rel="stylesheet" href="${base}/views/website/css/heel.css" />
		<script type="text/javascript" src="${base}/template/dictionary/js/plugins/jquery.form.js"></script>
		<script type="text/javascript" src="${base}/template/dictionary/js/plugins/jquery.twbsPagination.min.js"></script>
        
		<!--[if lt IE9]> 
		<script src="js/html5.js"></script>
		<![endif]-->
		
		<script type="text/javascript">
			$(function(){
				$("figure").find("a").click(function(){
					//先获取他们的父级，父级的同级，同级的子级
					$(this).parents().siblings().siblings().find("a").removeClass("curr");
					$(this).addClass("curr");
				});
				
				
				//分页
				$("#pagination").twbsPagination({
					totalPages:${pageResult.totalPage},
					visiblePages:5,
					startPage:${qo.currentPage},
					first:"首页",
					prev:"上一页",
					next:"下一页",
					last:"尾页",
					onPageClick:function(event,page){
						$("#currentPage").val(page);
						$("#searchForm").submit();
					}
				});
				
				//点击分组,查询分组下的明细
				$(".group_item").click(function(){
					$("#currentPage").val(1);
					$(this).addClass("active");
					$("#stuffTypeId").val($(this).data("dataid"));
					$("#searchForm").submit();
				});
				//点击二级分组，查询分组下的明细
				$(".item-a").click(function(){
					$("#currentPage").val(1);
					$(this).addClass("active");
					$("#stuffTypeItemId").val($(this).data("dataid"));
					$("#searchForm").submit();
				});
				//点击全网
				$(".btnheel").click(function(){
					$("#currentPage").val(1);
					$(this).addClass("active");
					$("#submitType").val($(this).data("dataid"));
					$("#searchForm").submit();
				});
				//点击合作
				$(".btn2").click(function(){
					$("#currentPage").val(1);
					$(this).addClass("active");
					$("#submitType").val($(this).data("dataid"));
					$("#searchForm").submit();
				});
			});
			
			
		</script>
		
	</head>
	<body>
		<!--start header-->
		<header class="public-header">
			<div class="public-container clearfloat">
				<!--start logo-->
				<div class="header-logo">
					<!--strat left 背景logo-->
					<div class="logo-l"><a href="${base}/website/index"></a></div><!--end left 背景logo-->
					<!--start left logo font-->
					<div class="logo-r"><p class="logo-font">找材宝</p><p>www.zhaocai.top</p></div><!--start right logo font-->
				</div>
				<!--end logo-->
			 	<!--strat header nav-->
				<ul class="header-nav">
					<li class="item"><a class="item-nav" href="${base}/admin/releaseqiugou/releaseqiugou">发布求购</a></li>
					<li class="item"><a class="item-nav" href="Group-purchase.html">团购</a></li>
					<li class="item">商家中心</li>
					<li class="item"><a class="item-nav" href="me-material.html">我的找材</a></li>
					<li class="item"><a class="item-nav activity" href="register.html">免费注册</a></li>
					<li class="item"><a class="item-nav" href="login.html">登录</a></li>
				</ul><!--end header nav-->
			</div>
		</header>
		<!--end header-->
		<!--start content-->
		<div class="bg">
			<div class="public-container">
				<span class="bg-nav">主页>${stuffTypeName.stuffTypeName}</span>
				<form action="" targer="_blank"  id="search-form" class="heel-form clearfloat">
					<input type="text" class="search-input-text clearfloat" name="keyword" id="search_input" value="${qo.keyword}" autocomplete="off" placeholder="请输入关键字" />
					<input type="submit" class="search-input-button clearfloat" value="" />
				</form>
			</div>
		</div>
		<!--end bg-->
		<!--start content-->
		<section class="public-container clearfloat">
			<form id="searchForm" action="${base}/heel/heel" method="post">
			<!--strat 大分类-->
				<article class="bigclassify">
					<input type="hidden" id="currentPage" name="currentPage" value="${qo.currentPage}"/>
					<input type="hidden" id="stuffTypeId" name="stuffTypeId" value="${qo.stuffTypeId}"/>
					<div class="bigdiv">
					<figure class="goods2">
						<img src="${base}/views/website/img/classify_03.jpg" />
						<p class="text"><a class="group_item" data-dataid="1001" href="javascript:;">鞋跟</a></p>
					</figure>
	                
	                <figure class="goods2">
						<img src="${base}/views/website/img/classify_03.jpg" />
						<p class="text"><a class="group_item" data-dataid="1006" href="javascript:;">鞋扣</a></p>
					</figure>
	                
	                 <figure class="goods2">
						<img src="${base}/views/website/img/classify_03.jpg" />
						<p class="text"><a class="group_item" data-dataid="1002" href="javascript:;">鞋底</a></p>
					</figure>
	                
	                 <figure class="goods2">
						<img src="${base}/views/website/img/classify_03.jpg" />
						<p class="text"><a class="group_item" data-dataid="1004" href="javascript:;">面料</a></p>
					</figure>
	                
	                 <figure class="goods3">
						<img src="${base}/views/website/img/classify_03.jpg" />
						<p class="text"><a class="group_item" data-dataid="1007" href="javascript:;">其他</a></p>
					</figure>
					</div>
				</article>
			<!--end 大分类-->
				<!--细分类-->
				<input type="hidden" id="stuffTypeItemId" name="stuffTypeItemId" value="${qo.stuffTypeItemId}"/>
				<ul class="classify clearfloat">                                
					<li class="classify-item"><span>分类：</span></li>
				</ul>
				<input type="hidden" id="submitType" name="submitType" value="${qo.submitType}"/>
				<a data-dataid="0" href="javascript:;" class="btnheel">全部</a>
				<a data-dataid="1" href="javascript:;" class="btn2">合作作品</a>
			</form>
				<!--细分类-->
				<!--strat 大分类-->
			<article class="bigclassify2">
				<!--商品-->
				<c:forEach items="${pageResult.listData}" var="vo">
					<figure class="goods">
						<a href="goods-details.html"><img src="${base}${(vo.stuffImageList)[0].stuffImageUrl}" alt="classify_03.jpg" /></a>
						<p class="text">高档${stuffTypeName.stuffTypeName}</p>
						<a class="text" href="goods-details.html">${vo.shop.shopName}</a>
					</figure>
				</c:forEach>
				<!--商品-->
			</article>
            
		   <div style="text-align:center;">
				<ul id="pagination" class="pagination"></ul>
			</div>
		</div>
			<!--end 大分类-->
		</section>
		<!--end conent-->
	<!--start footer-->
		<footer class="public-footer">
			<div class="public-container">
				<ul class="panel clearfloat">
					<li class="panel-item panel-item0">品质保证</li>
					<li class="panel-item panel-item1">质量保证</li>
					<li class="panel-item panel-item2">发货保证</li>
					<li class="panel-item panel-item3">海量保证</li>
				</ul>
				<div class="footer-box clearfloat">
					<ul class="footer-nav footer-l">
						<li class="footer-nav-item footer-title">新手指南</li>
						<li class="footer-nav-item footer-title">常见问题</li>
						<li class="footer-nav-item footer-title">采购商服务</li>
						<li class="footer-nav-item footer-title">关于我们</li>
						<li class="footer-nav-item"><a href="${base}/about/help?type=register">如何注册</a></li>
						<li class="footer-nav-item"><a href="${base}/about/help?type=buy">如何购买</a></li>
						<li class="footer-nav-item">搜商家</li>
						<li class="footer-nav-item"><a href="${base}/about/about?type=company">公司简介</a></li>
						<li class="footer-nav-item"><a href="${base}/about/help?type=purchase">如何采购</a></li>
						<li class="footer-nav-item"><a href="${base}/about/help?type=password">忘记密码</a></li>
						<li class="footer-nav-item">找产品</li>
						<li class="footer-nav-item"><a href="${base}/about/about?type=contact">联系我们</a></li>
						<li class="footer-nav-item"><a href="${base}/about/help?type=introduction">买家入门</a></li>
						<li class="footer-nav-item nav-margin"><a href="${base}/about/about?type=course">发展历程</a></li>
					</ul>
					<div class="footer-r">
						<div class="boxs">
							<img src="${base}/views/website/img/code_20.jpg" alt="code_20.jpg" />
							<p class="text">关注公众号</p>
						</div>
						<div class="boxs">
							<img src="${base}/views/website/img/code_22.jpg" alt="code_22.jpg" />
							<p class="text">下载找材宝</p>
						</div>
					</div>
				</div>
				<div class="copyright clearfloat">©2016 zhaocaibao.com 深圳市找材宝有限公司－营业执照注册号：330106000129000－粤ICP备10</div>
				<article class="bulk">
					<figure class="copyright-img"><img src="${base}/views/website/img/copyright_20.jpg" alt="copyright_20.jpg" /></figure>
					<figure class="copyright-img"><img src="${base}/views/website/img/copyright_22.jpg" alt="copyright_22.jpg" /></figure>
					<figure class="copyright-img"><img src="${base}/views/website/img/copyright_24.jpg" alt="copyright_24.jpg" /></figure>
					<figure class="copyright-img"><img src="${base}/views/website/img/copyright_26.jpg" alt="copyright_26.jpg" /></figure>
				</article>
			</div>
		</footer>
		<!--end footer-->

	</body>
</html>

