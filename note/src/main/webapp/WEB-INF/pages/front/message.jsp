<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" http-equiv="Content-Type" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" content="text/html; charset=UTF-8">
<meta name="keywords" content="技术,博客,分享,笔记,爱笔记,记事本,lovenote,note,love" />
<title>关于</title>
<link href="/static/css/bootstrap.css" rel="stylesheet">
<link href="/static/css/jquery.datetimepicker.css" rel="stylesheet">
<link href="/static/css/blog.css" rel="stylesheet">
<link href="/static/css/lxfcss.css" rel="stylesheet">

</head>
<body>
<%@ include file="header.jsp"%>
   <div class="container lxfMinHeight">
	<div class="blog-header"></div>
         <div class="col-sm-12 blog-main">
			<h4 class="myreviewHead"><strong>留言板</strong></h4>
			<div>
				<div style="position:relative; width:100%; display:table; *position:absolute; *top:50%; *left:0;">
					<p style="text-align:center;" id="getMore"><button class="btn">查看更多</button></p>
				</div>
			</div>
 			<div id="messageDiv">
 				
 			</div>
 			
          <table>
 				<tbody>
 					<tr>
 					 <td><textarea rows="2" cols="90" class="myreview" id="messageContent"></textarea></td>
 					 <td><span>  </span><button id="sendMessage" class="btn btn-primary">发表留言</button></td>
 					</tr>
 				</tbody>
 			</table>
 		</div>
      </div><!-- /.blog-main -->  
        
    </div><!-- /.container -->

   <%@ include file="foot.jsp"%>
<script src="/static/js/jquery-2.2.0.js"></script>
<script src="/static/extend/layer/layer.js"></script>
<script type="text/javascript" src="/static/extend/umeditor/umeditor.config.js"></script>
<script type="text/javascript" src="/static/extend/umeditor/umeditor.min.js"></script>
<script type="text/javascript" src="/static/extend/umeditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="/static/js/moon/header.js"></script>
<script type="text/javascript" src="/static/js/moon/message.js"></script>
</body>
</html>