<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" http-equiv="Content-Type" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" content="text/html; charset=UTF-8">
<meta property="qc:admins" content="1534023072647654066375" />
<title>lovenote</title>
<link href="static/css/bootstrap.css" rel="stylesheet">
<link href="static/css/login.css" rel="stylesheet">
</head>
<body>
<div class="container">
      <div class="form-signin" id="divLogin">
        <h2 class="form-signin-heading">lovenote</h2>
	 	<div class="input-group">
	   		<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
	   		 <input type="text" id="inputAccount" class="form-control" placeholder="用户名/邮箱/手机号" autofocus></input>
	   </div>
	   <p></p>
	   <div class="input-group">
	   		<span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
	   		<input type="text" id="inputPassword" class="form-control" placeholder="密码" autofocus onfocus="this.type='password'" autocomplete="off"></input>
	   </div>
	    <p></p>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me" id="autoLogin">记住密码
          </label>
           <label calss="right" id="findPassword" style="color:#286090">找回密码</label>
        </div>
        <button class="btn btn-lg btn-primary btn-block"  id="login">登录</button>
        <button class="btn btn-lg btn-primary btn-block"  id="reg">注册</button>
      </div>
      <div class="form-signin" id="divFind" style="display:none;">
	       <h2 class="form-signin-heading">lovenote</h2>
		 	<div class="input-group">
		   		<span class="input-group-addon"><span class="glyphicon glyphicon-envelope"></span></span>
		   		 <input type="email" id="findEmail" class="form-control" placeholder="请输入邮箱" autofocus></input>
		   </div>
		   <p></p>
		    <button class="btn btn-lg btn-primary btn-block"  id="btnFind">找回密码</button>
      </div>
    </div> <!-- /container -->
<script src="static/js/jquery-2.2.0.js"></script>
<script src="static/extend/layer/layer.js"></script>
<script src="static/js/md5.js"></script>
<script src="static/js/android/login.js"></script>
</body>
</html>