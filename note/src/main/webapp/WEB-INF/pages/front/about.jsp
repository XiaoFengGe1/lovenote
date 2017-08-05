<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" http-equiv="Content-Type" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" content="text/html; charset=UTF-8">
<meta name="keywords" content="笔记,爱笔记,记事本,lovenote,note,love" />
<title>关于</title>
<link href="/static/css/bootstrap.css" rel="stylesheet">
<link href="/static/css/jquery.datetimepicker.css" rel="stylesheet">
<link href="/static/css/blog.css" rel="stylesheet">
<link href="/static/css/lxfcss.css" rel="stylesheet">
<style>
.file {
    position: relative;
    display: inline-block;
    background: #D0EEFF;
    border: 1px solid #99D3F5;
    border-radius: 4px;
    padding: 4px 12px;
    overflow: hidden;
    color: #1E88C7;
    text-decoration: none;
    text-indent: 0;
    line-height: 20px;
}
.file input {
    position: absolute;
    font-size: 100px;
    right: 0;
    top: 0;
    opacity: 0;
}
.file:hover {
    background: #AADFFD;
    border-color: #78C3F3;
    color: #004974;
    text-decoration: none;
}
</style>
</head>
<body>
<%@ include file="header.jsp"%>
   <div class="container lxfMinHeight">
	<div class="blog-header"></div>
     
      
         <div class="col-sm-10 blog-main">
			<div class="blog-header"></div>
			
          <div class="blog-post" id="userDiv">
            <h2>关于本站</h2>
	            <span>1爱笔记是一个公共技术技术笔记库，希望大家能够在这里分享自己的技术笔记。账户密码已经进行md5加盐保存，足够安全，请放心注册。</span>
	            <p></p>
	            <span>2登录后，全网搜页面搜索到的结果可以一键保存。</span>
	            <p></p>
	            <span>3本着开放的原则，本站源码已经开放。</span><a href="https://github.com/XiaoFengGe1/lovenote" target="black_">github/xiaofengge1</a>
	             <p></p>
	            <span>4请不要恶意攻击本站^_^。</span>
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
</body>
</html>