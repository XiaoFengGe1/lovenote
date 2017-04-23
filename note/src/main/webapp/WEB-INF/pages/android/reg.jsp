<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" http-equiv="Content-Type" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" content="text/html; charset=UTF-8">
<title>lovenote</title>
<link href="/static/css/bootstrap.css" rel="stylesheet">
<link href="/static/css/login.css" rel="stylesheet">
</head>
<body>
<div class="container">
      <div class="form-signin" action="">
        <h2 class="form-signin-heading">lovenote</h2>
		 	<div class="input-group" id="emailDiv">
		   		<span class="input-group-addon"><span class="glyphicon glyphicon-envelope"></span></span>
		   		 <input type="email" id="regEmail" class="form-control" placeholder="请输入邮箱" required autofocus></input>
		   </div>
		   <p></p>
		   	<div class="input-group" id="sixNumDiv">
		   		<span class="input-group-addon"><span class="glyphicon glyphicon-flash"></span></span>
		   		 <input type="text" id="regSixNum" class="form-control" placeholder="请在30分钟内输入验证码" required autofocus></input>
		   </div>
		   <p></p>
		<div id="nameDiv">
		   	<div class="input-group" >
		   		<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
		   		 <input type="text" id="regName" class="form-control" placeholder="请输入昵称" required autofocus></input>
		    </div>
		    <p></p>
		    <div class="input-group">
		   		<span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
		   		<input type="text" id="RegPassword" class="form-control" placeholder="请输入密码" required onfocus="this.type='password'" autocomplete="off">
		    </div>
		    <p></p>
		</div>
        <button class="btn btn-lg btn-primary btn-block"  id="reg">发送验证码</button>
      </div>
    </div> <!-- /container -->
<script src="/static/js/jquery-2.2.0.js"></script>
<script src="/static/extend/layer/layer.js"></script>
<script src="/static/js/md5.js"></script>
<script src="/static/js/android/reg.js"></script>
</body>
</html>