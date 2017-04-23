<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" http-equiv="Content-Type" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" content="text/html; charset=UTF-8">
<meta name="description" content="" id="mymeta"/>
<meta name="keywords" content="笔记,爱笔记,记事本,lovenote,note,love" />
<title>lovenote</title>
<link href="/static/css/bootstrap.css" rel="stylesheet">
<link href="/static/css/blog.css" rel="stylesheet">
<link href="/static/css/lxfcssweixin.css" rel="stylesheet">
<style type="text/css">
img{
  max-height: 80%;
  max-Width: 80%;
}
</style>
</head>
<body>

<%@ include file="header.jsp"%> 

    <div class="container">
      <div class="row">
        <div class="blog-main lxfMinHeight">
        
          <div class="blog-post">
          	 <table class="table table-condensed">
				<tbody >
		     	 <tr>
		     	 	<td id="editNoteTd" style="visibility:hidden"><button class="form-control lxfInline" id="editNote"><span class="glyphicon glyphicon-pencil"></span>&nbsp编辑</button></td>
		     	 	<td id="deleteNoteTd" style="visibility:hidden"><button class="form-control lxfInline" id="deleteNote"><span class="glyphicon glyphicon-remove"></span>&nbsp删除</button></td>
		     	 	<td><button class="form-control lxfInline" id="likeNote"><span class="glyphicon glyphicon-heart-empty"></span>&nbsp点赞</button></td>
		     	 </tr>
		     	 </tbody>
		      </table>
          		
            <h2 id="title"></h2>
            
            <p class="blog-post-meta myFontFooter lxfInline" id="timeName"></p>
            
              <div id="content">
			
              </div>
           
          </div><!-- /.blog-post -->

        </div><!-- /.blog-main -->
      </div><!-- /.row -->
	

			
				<div class="-mob-share-ui-button -mob-share-open myshare">分享</div>
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
 			<p></p>
 			<h4 class="myreviewHead"><strong>评论区</strong></h4>
 			<hr style=" height:1px;border:none;border-top:2px dotted #185598;" />
 			<div id="reviews">
 				
 			</div>
 		</div>
 		<div id="pagediv">
 			<button id="pageForward" class="btn" style="float: right;margin-left: 5px;">下一页</button>
 			<button id="pageBack" class="btn"  style="float: right">上一页</button>
 		</div>
 		<div class="clearfix"></div>
 		<hr style="height:1px;border:none;border-top:1px dashed #0066CC;"/>
 		<form class="form-inline">
		   <div class="form-group">
		      <textarea type="text" class="form-control" id="reviewContent"
		         placeholder="请输入评论"></textarea>
		   </div>
		  <button id="sendreview" type="button" class="form-control btn btn-primary" style="float: right">发表评论</button>
		</form>
 		
 	</div><!-- /.container -->
   <%@ include file="foot.jsp"%>
<script src="/static/js/jquery-2.2.0.js"></script>
<script src="/static/extend/layer/layer.js"></script>
<script src="http://tjs.sjs.sinajs.cn/open/api/js/wb.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="/static/js/weixin/header.js"></script>
<script src="/static/js/weixin/note.js"></script>
</body>
</html>