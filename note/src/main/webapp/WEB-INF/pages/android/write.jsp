<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" http-equiv="Content-Type" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" content="text/html; charset=UTF-8">
<meta name="keywords" content="笔记,爱笔记,记事本,lovenote,note,love" />
<title>写笔记</title>
<link href="static/css/bootstrap.css" rel="stylesheet">
<link href="static/extend/umeditorweixin/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
<link href="static/css/blog.css" rel="stylesheet">
<link href="static/css/lxfcssweixin.css" rel="stylesheet">
</head >
<body>
<input  type="file" >123</input>
    <div class="container lxfMinHeight">
		<table class="table table-condensed ">
				<tbody >
		     	 <tr>
		     	 <td width="15%"><span>分类</span></td>
		     	 <td width="60%"><select class="form-control" style="display:inline" id="selectClass">
		   
	   				</select>
	   			</td>
		     	<td width="25%"><button id="saveBtn" class="form-control btn-info" value="发布" autofocus>
		   				<span class="glyphicon glyphicon-ok"></span><strong>发布</strong></button></td>
		     	 </tr>
		     	 </tbody>
		 </table>
     <input type="text" id="titleInput" class="form-control"  autofocus placeholder="标题"></input>
    <p></p>
    		<div class="blog-main">
			    <script type="text/plain" id="myEditor" style="width:100%;height:100%;"></script>
   		 </div>
    
    </div><!-- /.container -->

<script src="static/js/jquery-2.2.0.js"></script>
<script src="static/extend/layer/layer.js"></script>
<script type="text/javascript" src="static/extend/umeditorweixin/umeditorweixin.config.js"></script>
<script type="text/javascript" src="static/extend/umeditorweixin/umeditor.min.js"></script>
<script type="text/javascript" src="static/extend/umeditorweixin/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="static/js/android/header.js"></script>
<script src="static/js/android/write.js"></script>
</body>
</html>