<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<div class="blog-masthead">
      <div class="">
        <nav class="blog-nav">
         <a class="blog-nav-item clickme" href="#" id="search"><span class="glyphicon glyphicon-search"></span>&nbsp;全网搜</a>
          <a class="blog-nav-item clickme" href="#" id="index"><span class="glyphicon glyphicon-home"></span>&nbsp;首页</a>

          <%
          if(session.getAttribute("lovelxfName") == null) {  
        	  out.print("<a class='blog-nav-item' id='loginAPP' href='/login' style='float:right' ><span class='glyphicon glyphicon-log-in' id='myloginspan' value='登陆'></span>&nbsp;登陆 </a>");
          }else{
        	  out.print("<a class='blog-nav-item' href='#' id='writeBtn'><span class='glyphicon glyphicon-pencil'></span>&nbsp;笔记</a>&nbsp;<a class='blog-nav-item clickme' href='#' style='float:right' id='user'><span class='glyphicon glyphicon-user'></span>&nbsp" + session.getAttribute("lovelxfName")+"</a>");
          }
          %>
        </nav>
      </div>
    </div>