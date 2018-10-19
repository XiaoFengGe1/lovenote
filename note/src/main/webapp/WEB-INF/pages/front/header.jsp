<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<div class="blog-masthead lxfHeaderHeight">
      <div class="container">
        <nav class="blog-nav">
          <a class="blog-nav-item clickme" id="quitAPP">
          
          <%
          if(session.getAttribute("lovelxfName") == null) {  
        	  out.print("<span class='glyphicon glyphicon-log-in' id='myspan' value='登陆'></span>&nbsp;&nbsp;登陆");
          }else{
        	  out.print("欢迎您:" + session.getAttribute("lovelxfName")+" &nbsp;&nbsp;<span class='glyphicon glyphicon-off' id='myspan' value='注销'></span>注销");
          }
          %>
          </a>
          <a class="blog-nav-item clickme" href="#" id="search"><span class="glyphicon glyphicon-search"></span>&nbsp全网搜</a>
          <a class="blog-nav-item clickme" href="#" id="index"><span class="glyphicon glyphicon-home"></span>&nbsp首页</a>
          <a class="blog-nav-item clickme" href="#" id="user" style="display:none"><span class="glyphicon glyphicon-user"></span>&nbsp个人中心</a>
          <a class="blog-nav-item btn" id="weixin" style="float:right"><span class="glyphicon glyphicon-qrcode"></span>&nbsp;微信公众号</a>
          <!-- <a class="blog-nav-item" style="float:right" href="static/res/lovenote.apk"><span class="glyphicon glyphicon-save"></span>&nbsp;Android版下载</a> -->
          <a class="blog-nav-item" id="about" style="float:right" href="#">&nbsp;关于</a>
      	  <a class="blog-nav-item" id="message" style="float:right" href="#">留言板</a>
        </nav>
      </div>
        <img alt="" id="weixinPic" class="weixinPic" src="static/img/weixin.jpg">
    </div>
