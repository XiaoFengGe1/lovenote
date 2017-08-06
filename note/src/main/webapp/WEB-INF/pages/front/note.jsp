<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" http-equiv="Content-Type" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" content="text/html; charset=UTF-8">
<meta name="description" content="" id="mymeta"/>
<meta name="keywords" content="技术,博客,分享,笔记,爱笔记,记事本,lovenote,note,love" />
<title>lovenote</title>
<link href="/static/css/bootstrap.css" rel="stylesheet">
<link href="/static/css/blog.css" rel="stylesheet">
<link href="/static/css/lxfcss.css" rel="stylesheet">
<style type="text/css">
img{
  max-height: 80%;
  max-Width: 80%;
}
</style>
</head>
<body >
<%@ include file="header.jsp"%> 
    <div class="container">
     <div class="blog-header"></div>
      <div class="row">
        <div class="blog-main lxfMinHeight">
        
          <div class="blog-post">
          	<div>
           		 <h2 style="display:inline" id="title"></h2>
           		 <div class="form-inline" style="display:inline;float:right" >
<!--            		 	<button class="form-control btn-info" id="backNote"><span class="glyphicon glyphicon-backward"></span>&nbsp返回</button> -->
           		 	<button class="form-control" style="display: none" id="editNote"><span class="glyphicon glyphicon-pencil"></span>&nbsp编辑</button>
            		<button class="form-control" style="display: none" id="deleteNote"><span class="glyphicon glyphicon-remove"></span>&nbsp删除</button>
            		<button class="form-control" id="likeNote"><span class="glyphicon glyphicon-heart-empty"></span>&nbsp点赞</button>
            		<!-- <button class="form-control" id="unlikeNote"><span class="glyphicon glyphicon-thumbs-down"></span>&nbsp吐槽</button> -->
            	 </div>
            </div>
            <p class="blog-post-meta myFontFooter lxfInline" id="timeName"></p>
            
              <div  id="content">
			
              </div>
           
          </div><!-- /.blog-post -->

        </div><!-- /.blog-main -->
      </div><!-- /.row -->
	

				<div class="-mob-share-ui-button -mob-share-open myShare">分享</div>
				<div class="-mob-share-ui -mob-share-ui-theme -mob-share-ui-theme-slide-bottom" style="display: none">
				    <ul class="-mob-share-list">
				  		<li class="-mob-share-weixin"><p>微信</p></li>
				        <li class="-mob-share-weibo"><p>新浪微博</p></li>
				        <li class="-mob-share-qzone"><p>QQ空间</p></li>
				        <li class="-mob-share-qq"><p>QQ好友</p></li>
				        <li class="-mob-share-renren"><p>人人网</p></li>
				    </ul>
				    <div class="-mob-share-close">取消</div>
				</div>
				<div class="-mob-share-ui-bg"></div>
				<script id="-mob-share" src="http://f1.webshare.mob.com/code/mob-share.js?appkey=10f6434da9445"></script>
				<!--MOB SHARE END-->                 
 		<div class="myreviewdiv">
 			<h4 class="myreviewHead"><strong>评论区</strong></h4>
 			<table>
 				<tbody>
 					<tr>
 					 <td><textarea rows="3" cols="90" class="myreview" id="reviewContent"></textarea></td>
 					 <td><button id="sendreview" class="btn btn-primary">发表评论</button></td>
 					</tr>
 				</tbody>
 			</table>
 			<hr style=" height:1px;border:none;border-top:2px dotted #185598;" />
 			<div id="reviews">
 				
 			</div>
 		</div>
 		<div id="pagediv">
 			<button id="pageForward" class="btn" style="float: right;margin-left: 5px;">下一页</button>
 			<button id="pageBack" class="btn"  style="float: right">上一页</button>
 		</div>
 	</div><!-- /.container -->
    <%@ include file="foot.jsp"%>
<script src="/static/js/jquery-2.2.0.js"></script>
<script src="/static/extend/layer/layer.js"></script>
<script type="text/javascript" src="/static/js/moon/header.js"></script>
<script src="/static/js/moon/note.js"></script>
</body>
</html>