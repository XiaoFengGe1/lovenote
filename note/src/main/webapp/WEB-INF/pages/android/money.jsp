<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" http-equiv="Content-Type" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" content="text/html; charset=UTF-8">
<meta name="keywords" content="笔记,爱笔记,记事本,lovenote,note,love" />
<title>lovenote</title>
<link href="/static/css/bootstrap.css" rel="stylesheet">
<link href="/static/css/jquery.datetimepicker.css" rel="stylesheet">
<link href="/static/css/blog.css" rel="stylesheet">
<link href="/static/css/lxfcssweixin.css" rel="stylesheet">
</head>
<body  style="background-color:#dfdddd">
	<div class="blog-masthead">
      <div class="">
        <nav class="blog-nav">
          <a class="blog-nav-item clickme" href="#" id="search"><span class="glyphicon glyphicon-search"></span>&nbsp;全网搜</a>
          <a class="blog-nav-item clickme" href="#" id="index"><span class="glyphicon glyphicon-home"></span>&nbsp;笔记</a>
         <a class="blog-nav-item clickme" href="#" id="money"><span class="glyphicon glyphicon-search"></span>&nbsp;账本</a>
        </nav>
      </div>
    </div>
    
    <div class="container">
	 
      <div class="row lxfMinHeight">
        <div class="blog-main" id="noteBody">
       
         

        </div>
      </div>
  
     <div class="blog-header"> 
       <table class="table table-condensed">
       	<tbody>
       		<tr>
       			<td><button class="form-control" id="pageBack" >上一页</button></td>
       			<td>  <button class="form-control" id="pageForward" >下一页</button></td>
       		</tr>
       	</tbody>
       </table>
      </div>
    </div><!-- /.container -->

</body>
<script src="/static/js/jquery-2.2.0.js"></script>
<script src="/static/extend/layer/layer.js"></script>
<script src="/static/js/bootstrap.js"></script>
<script src="/static/js/jquery.datetimepicker.js"></script>
<script type="text/javascript" src="/static/js/android/header.js"></script>
<script src="/static/js/android/money.js"></script>
</html>