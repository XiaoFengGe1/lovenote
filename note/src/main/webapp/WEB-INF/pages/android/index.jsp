<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" http-equiv="Content-Type" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" content="text/html; charset=UTF-8">
<meta name="keywords" content="笔记,爱笔记,记事本,lovenote,note,love" />
<title>lovenote</title>
<link href="static/css/bootstrap.css" rel="stylesheet">
<link href="static/css/jquery.datetimepicker.css" rel="stylesheet">
<link href="static/css/blog.css" rel="stylesheet">
<link href="static/css/lxfcssweixin.css" rel="stylesheet">
</head>
<body  style="background-color:#dfdddd">
	<div class="blog-masthead">
      <div class="">
        <nav class="blog-nav">
          <a class="blog-nav-item clickme" href="#" id="search"><span class="glyphicon glyphicon-search"></span>&nbsp;全网搜</a>
          <a class="blog-nav-item clickme" href="#" id="index"><span class="glyphicon glyphicon-home"></span>&nbsp;笔记</a>
           <a class="blog-nav-item clickme" href="#" id="money"><span class="glyphicon glyphicon-home"></span>&nbsp;账本</a>
        </nav>
      </div>
    </div>
    
    <div class="container">
	 <table class="table table-condensed search-header" >
		<tbody >
     	 <tr>
     	 	<td><input type="text" id="inputSearch" class="form-control " placeholder="多个关键词请用空格隔开" autofocus></input></td>
     	 	<td><button id="searchBtn" class="form-control  btn-info " autofocus>
		   	<span class="glyphicon glyphicon-search"></span>&nbsp<strong>搜索</strong></button></td>
     	 </tr>
     	 </tbody>
      </table>
   
     <table class="table table-condensed mySearchCheck" >
		<tbody >
     	 <tr>
     	 	<td><button class="btn" id="searchToolBtn" ><span class="glyphicon glyphicon-chevron-down"></span>工具</button></td>
		    <td id="selectClassDiv" style="display:none"> <select class="btn" style="display:inline" id="selectClass">
			          <option>全部</option>
		      	  </select>
		     </td>
     	 	<td id="selectTimeDiv" style="display:none"> <select class="btn" style="display:inline" id="selectTime">
			         <option>时间</option>
			         <option>一周内 </option>
			         <option>一月内 </option>
			         <option>半年内</option>
			         <option>自定义</option>
		     	 </select>
		     </td>
		      <td  class="timeChoose"><button id="btnBackTime" class="btn btn-info" autofocus><strong>确定</strong></button></td>
		     <td  class="timeChoose"><input class="form-control" id="datetimepicker1" type="text"  placeholder="开始" autofocus></input></td>
		     <td  class="timeChoose"><input class="form-control" id="datetimepicker2" type="text"  placeholder="结束" autofocus></input></td>
     	 </tr>
     	 </tbody>
      </table>
      <p>
	      	<span><sub>搜索结果</sub><sub id="resultNum">0</sub><sub>条</sub></span>
      	</p>
     
      <div class="row lxfMinHeight">
        <div class="blog-main" id="noteBody">
       
         

        </div><!-- /.blog-main -->
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
<script src="static/js/jquery-2.2.0.js"></script>
<script src="static/extend/layer/layer.js"></script>
<script src="static/js/bootstrap.js"></script>
<script src="static/js/jquery.datetimepicker.js"></script>
<script type="text/javascript" src="static/js/android/header.js"></script>
<script src="static/js/android/index.js"></script>
</html>