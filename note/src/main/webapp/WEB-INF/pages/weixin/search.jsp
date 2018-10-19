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
<%@ include file="header.jsp"%> 

    <div class="container ">
      
      <table class="table search-header" >
		<tbody >
     	 <tr>
     	 	<td><input type="text" id="inputSearch" class="form-control " placeholder="多个关键词请用空格隔开" autofocus></input></td>
     	 	<td><button id="searchBtn" class="form-control  btn-info " autofocus>
		   	<span class="glyphicon glyphicon-search"></span>&nbsp<strong>搜索</strong></button></td>
     	 </tr>
     	 </tbody>
      </table>
      	<div class="form-horizontal mySearchCheck">
      			
				<label class="lxfInline">
				<span  class="success">显示条数:</span>
	      		  <select class="btn" style="display:inline" id="showNum">
			         <option>1</option>
			         <option>2 </option>
			         <option selected>3 </option>
			         <option>4</option>
			         <option>5</option>
			         <option>6</option>
		      	  </select>
		      	  <button id="selectAll" class="btn btn-info" autofocus>
			   		<span class="glyphicon glyphicon-check"></span>&nbsp<strong>全选</strong></button><span>该页面建议用PC浏览</span>
			   		<label class="checkbox-inline" style="float:right" id="tuijain">网站推荐<span class="glyphicon glyphicon-hand-down"></span></label>
		      	</label>
				<p></p>
		      	<label class="checkbox-inline">
				  <input type="checkbox" id="csdn" checked="checked" value="CSDN">CSDN
				</label>
				<label class="checkbox-inline">
				  <input type="checkbox" id="jioaben" checked="checked" value="脚本之家">脚本之家
				</label>
				<label class="checkbox-inline">
				  <input type="checkbox" id="bokeyuan" checked="checked" value="博客园">博客园
				</label>
				<label class="checkbox-inline">
				  <input type="checkbox" id="sina" checked="checked" value="新浪博客">新浪
				</label>
				<label class="checkbox-inline">
				  <input type="checkbox" id="wangyi" checked="checked" value="网易博客">网易
				</label>
				<label class="checkbox-inline">
				  <input type="checkbox" id="qiandaunli" value="前端里">前端里
				</label>
				<label class="checkbox-inline">
				  <input type="checkbox" id="manong" value="码农网">码农网
				</label>
				<label class="checkbox-inline">
				  <input type="checkbox" id="qiandaunkaifa" value="前端开发网">前端开发
				</label>
		 </div>
      	
      <!-- <div style="display:none" id="myProgressDiv">进度条
	     <div class="progress">
			  <div  id="myProgress" class="progress-bar progress-bar-success progress-bar-striped  active" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 10%;"></div>
		</div>
      </div> -->
      <div class="lxfMinHeight">
        <div  id="noteBody">
       
        </div><!-- /.blog-main -->
      </div>
  
     
    </div><!-- /.container -->

   <%@ include file="foot.jsp"%>
<script src="static/js/jquery-2.2.0.js"></script>
<script src="static/extend/layer/layer.js"></script>
<script src="static/js/bootstrap.js"></script>
<script src="static/js/jquery.datetimepicker.js"></script>
<script type="text/javascript" src="static/js/weixin/header.js"></script>
<script src="static/js/weixin/search.js"></script>
</body>
</html>