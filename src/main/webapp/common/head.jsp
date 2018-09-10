<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<title>车宝管家平台管理系统</title>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta name="Author" content="chebao Team" />
<meta name="Copyright" content="chebao" />
<link rel="Shortcut icon"  href="favicon.ico" type="image/x-icon" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<c:set value="${pageContext.request.contextPath}" var="base" />
<link href="${base}/template/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/admin.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/master.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/common/css/datepicker.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/common/css/uploadify.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/common/css/uploadify.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/common/jquery-ui-1.12.1/jquery-ui.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/management.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/reset.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/common/jquery-ui-1.12.1/external/jquery/jquery.js"></script>
<script type="text/javascript" src="${base}/template/common/jquery-ui-1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="${base}/template/common/js/jquery.pager.js"></script>
<script type="text/javascript" src="${base}/template/common/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/template/common/js/jquery.lSelect.js"></script>
<script type="text/javascript" src="${base}/template/common/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${base}/template/common/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/template/common/js/jquery.validate.methods.js"></script>
<script type="text/javascript" src="${base}/template/common/js/autoComplete/jquery.autocomplete.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/base.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/admin.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${base}/views/website/js/html5.js"></script>
<%-- <script type="text/javascript" src="${base}/views/website/js/jquery-1.9.1.min.js"></script> --%>
<script type="text/javascript" src="${base}/views/website/js/jquery.imagezoom.min.js"></script>
<script type="text/javascript" src="${base}/views/website/js/kefu.js"></script>
<script type="text/javascript" src="${base}/views/website/js/swiper.min.js"></script>
