<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" http-equiv="Content-Type" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" content="text/html; charset=UTF-8">
<meta property="qc:admins" content="1534023072647654066375" />
<meta name="keywords" content="笔记,爱笔记,记事本,lovenote,note,love" />
<title>lovenote</title>
<link href="/static/css/bootstrap.css" rel="stylesheet">
<link href="/static/css/jquery.datetimepicker.css" rel="stylesheet">
<link href="/static/css/blog.css" rel="stylesheet">
<link href="/static/css/lxfcss.css" rel="stylesheet">
</head>
<body  style="background-color:#dfdddd">
<%@ include file="header.jsp"%> 

    <div class="container">
	
      <div class="blog-header">
     	 <div class="form-inline">
		   	<input type="text" id="inputSearch" class="form-control lxfInputLenL" placeholder="多个关键词请用空格隔开" autofocus></input>
		   	<button id="searchBtn" class="form-control btn-info" autofocus>
		   	<span class="glyphicon glyphicon-search"></span>&nbsp<strong>搜索</strong></button>
		    <button id="writeBtn" class="form-control lxfInline"  style="display:inline;float:right"><span class="glyphicon glyphicon-pencil"></span>&nbsp写笔记</button>
      	</div>
      	<div class="form-inline">
      		<p></p>
      		<button class="form-control"id="searchToolBtn" ><span class="glyphicon glyphicon-chevron-down"></span>&nbsp搜索工具</button>
      		
      		<div id="searchTool" class="form-inline lxfInline lxfMinWidth" style="display:none">
	      		  <select class="form-control" style="display:inline" id="selectClass">
			        <option>全部</option>
		      	  </select>
		      	  <select class="form-control" style="display:inline" id="selectTitle">
			         <option>标题正文 </option>
			         <option>标题</option>
			         <option>正文 </option>
		      	 </select>
	      		 <select class="form-control" style="display:inline" id="selectTime">
			         <option>时间</option>
			         <option>一周内 </option>
			         <option>一月内 </option>
			         <option>半年内</option>
			         <option>自定义</option>
		     	 </select>
		     	 <div id="timePickerDiv" class="lxfInline" style="display:none">
			     	<button id="btnBackTime" class="form-control btn-info" autofocus>
			   		<span class="glyphicon glyphicon-backward"></span>&nbsp<strong>返回</strong></button>
			     	 <span>从</span>
	                 <input class="form-control lxfInputLenS" id="datetimepicker1" type="text" > 
					 <span>到</span>
					 <input class="form-control lxfInputLenS" id="datetimepicker2" type="text" > 
		      	 </div>
		      	 <select class="form-control" style="display:inline" id="selectUser">
			         <option>所有用户</option>
			         <option>仅自己</option>
			         <option>自定义</option>
		      	</select>
		      	<div id="searchToolUser" class="lxfInline" style="display:none">
			      	<button id="btnBack" class="form-control btn-info" autofocus>
			   		<span class="glyphicon glyphicon-backward"></span>&nbsp<strong>返回</strong></button>
			   		<input type="text" id="inputUser" class="form-control lxfInputLenS" placeholder="用户名" autofocus></input>
	      		</div>
	      	</div>
	      	<span><sub>搜索结果</sub><sub id="resultNum">0</sub><sub>条</sub></span>
      	</div>
      </div>
      <div class="row lxfMinHeight">
        <div class="blog-main" id="noteBody">
       
         

        </div><!-- /.blog-main -->
      </div>
  
     <div class="blog-header"> 
       <div class="form-inline">
          <button class="form-control" id="pageBack" >上一页</button>
          <button class="form-control" id="pageForward" >下一页</button>
       </div>
    </div>
    </div><!-- /.container -->

   <%@ include file="foot.jsp"%>
<script src="/static/js/jquery-2.2.0.js"></script>
<script src="/static/extend/layer/layer.js"></script>
<script src="/static/js/bootstrap.js"></script>
<script src="/static/js/jquery.datetimepicker.js"></script>
<script type="text/javascript" src="/static/js/moon/header.js"></script>
<script src="/static/js/moon/index.js"></script>
</body>
</html>