<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" http-equiv="Content-Type" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" content="text/html; charset=UTF-8">
<meta name="keywords" content="技术,博客,分享,笔记,爱笔记,记事本,lovenote,note,love" />
<title>写笔记</title>
<link href="static/css/bootstrap.css" rel="stylesheet">
<link href="static/extend/ueditor/themes/default/css/ueditor.css" type="text/css" rel="stylesheet">
<link href="static/css/blog.css" rel="stylesheet">
<link href="static/css/lxfcss.css" rel="stylesheet">
</head >
<body>
<%@ include file="header.jsp"%> 
    <div class="container lxfMinHeight">
     <div class="blog-header"></div>
     
      <div class="form-inline">
        <select class="form-control" style="display:inline" id="selectClass">
		   
	    </select>
	      <div class="input-group">
	     	<span class="input-group-addon"><span class="glyphicon glyphicon-pencil"></span></span>
	      	<input type="text" id="titleInput" class="form-control lxfInputLenL"  autofocus placeholder="标题"></input>
	  	 </div>
	  	 	<button id="saveBtn" class="form-control btn-info" value="发布" autofocus>
		   	<span class="glyphicon glyphicon-ok"></span>&nbsp<strong>发布</strong></button>
	  </div>
   		<p></p><p></p>
    		<div class="blog-main">
    		<!-- <form action="/dd" method="post"> -->
			    <script type="text/plain" id="myEditor" style="width:1000px;height:480px;"></script>
			<!-- </form> -->
   		 </div>
    
    </div><!-- /.container -->

   <%@ include file="foot.jsp"%>
<script src="static/js/jquery-2.2.0.js"></script>
<script src="static/extend/layer/layer.js"></script>
<script type="text/javascript" src="static/extend/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="static/extend/ueditor/ueditor.all.js"></script>
<script type="text/javascript" src="static/extend/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="static/js/moon/header.js"></script>
<script src="static/js/moon/write.js"></script>
</body>
</html>