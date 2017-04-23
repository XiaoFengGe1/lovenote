<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" http-equiv="Content-Type" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" content="text/html; charset=UTF-8">
<meta name="keywords" content="笔记,爱笔记,记事本,lovenote,note,love" />
<title>个人中心</title>
<link href="/static/css/bootstrap.css" rel="stylesheet">
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
<%  
    if(session.getAttribute("lovelxfName") == null) {  
%>  
        <script type="text/javascript" language="javascript">  
            location.href="/login";  
        </script>   
<%  
    }  
%>  
<%@ include file="header.jsp"%>

   <div class="container lxfMinHeight">
	<div class="blog-header"></div>
     
    <div class="col-sm-2  blog-sidebar">
          <div class="sidebar-module">
            <img class="lxfImgM" id="toxiangimg" src="/static/img/love.jpg"></img>
            <form id="myform" action="toxiang.do" method="post" enctype="multipart/form-data">  
				<button class="file btn">
				<input type="file"  name="file" id="toxaing" onchange="filesize(this)" accept="image/*"/>更换头像
				</button>
			</form>  
          </div>
          <div class="sidebar-module ">
             <button class="btn btn-primary" id="userBtn"><span class="glyphicon glyphicon-wrench"></span>&nbsp信息</button>
          </div>
          <div class="sidebar-module ">
             <button class="btn btn-primary" id="classBtn"><span class="glyphicon glyphicon-wrench"></span>&nbsp分类</button>
          </div>
    </div><!-- /.blog-sidebar -->
      
         <div class="col-sm-8 blog-main">
			<div class="blog-header"></div>
			
          <div class="blog-post" id="userDiv">
            <h2></h2>
	            <table class="table table-bordered">
				   <caption>基本信息</caption>
				   <tbody>
				      <tr>
				         <td>昵称</td>
				         <td><input class="form-control" id="name" disabled="true"></input></td>
				      </tr>
				      <tr>
				         <td>性别</td>
				         <td>
							 <label><input type="radio" name="sexRadio" id="optionsRadios1" value="男">男</label>
							 <label><input type="radio" name="sexRadio" id="optionsRadios2" value="女">女</label>
				         </td>
				      </tr>
				      <tr>
				         <td>邮箱</td>
				         <td><input type="email" class="form-control" id="email" disabled="true"></input></td>
				      </tr>
				      <tr>
				         <td>手机</td>
				         <td><input type="text" class="form-control" id="tel" disabled="true"></input></td>
				      </tr>
				      <tr>
				         <td>密码</td>
				         <td><input type="text" id="password" onfocus="this.type='password'" disabled="true" class="form-control" placeholder="若要修改密码请输入新密码" ></input></td>
				      </tr>
				       <tr>
				         <td>注册时间</td>
				         <td><input type="text" id="regTime" class="form-control" disabled="true" ></input></td>
				      </tr>
					      <tr id="rankDiv" style="display: none">
					         <td>等级</td>
					         <td id="rank">3</td>
					      </tr>
					      <tr id="moneyDiv" style="display: none">
					         <td>余额</td>
					         <td id="money">10.63</td>
					      </tr>
				   </tbody>
				</table>
				 <button class="btn btn-primary btn-block"  id="fixUser">修改信息</button>
            <p></p>
          </div><!-- 个人信息 -->


			<div class="blog-post" id="classDiv">
           	 <h2></h2>
	            <table class="table">
				   <caption>分类设置</caption>
				   <tbody>
				      <tr>
				         <td><input type="text" class="form-control" id="addInput"></input></td>
				         <td><button class="btn btn-info" id="addClass"><span class="glyphicon glyphicon-plus"></span>&nbsp添加</button></td>
				      </tr>
				   </tbody>
				</table>
				<table class="table">
				   <tbody id="classAddEle">
				     
				   </tbody>
				</table>
            <p></p>
          </div><!-- /.blog-post -->
        </div><!-- /.blog-main -->  
        
    </div><!-- /.container -->

   <%@ include file="foot.jsp"%>
<script src="/static/js/jquery-2.2.0.js"></script>
<script src="/static/extend/layer/layer.js"></script>
<script type="text/javascript" src="/static/extend/umeditor/umeditor.config.js"></script>
<script type="text/javascript" src="/static/extend/umeditor/umeditor.min.js"></script>
<script type="text/javascript" src="/static/extend/umeditor/lang/zh-cn/zh-cn.js"></script>
<script src="/static/js/md5.js"></script>
<script type="text/javascript" src="/static/js/moon/header.js"></script>
<script src="/static/js/moon/user.js"></script>
<script >
var fileFlag = 0;
function filesize(ele) {
    // 返回 KB，保留小数点后两位
    if((ele.files[0].size/1024).toFixed(2)>1024){
    	fileFlag = 0;
    	layer.alert("图片不能超过1M");
    }else{
    	fileFlag = 1;
    	$("#myform").submit();
    }
}
</script>
</body>
</html>