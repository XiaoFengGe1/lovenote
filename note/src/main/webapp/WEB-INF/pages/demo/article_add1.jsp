<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="http://fex.baidu.com/webuploader/css/demo.css">
<link rel="stylesheet" type="text/css" href="/js/webuploader/webuploader.css">
<script src="//cdn.bootcss.com/jquery/2.2.0/jquery.min.js"></script>
<head>
	<title></title>
</head>
<body onLoad="loadPage()">
	<h2>New Article1</h2>
	<form:form method="post" commandName="article" enctype="multipart/form-data" onsubmit="checkForm()">
	   <table>
	    <tr>
	        <td><form:label path="title">title</form:label></td>
	        <td><form:input path="title" size="100" id="title" /></td>
	    </tr>
	    <tr>
	        <td><form:label path="content">content</form:label></td>
	        <td><form:hidden path="content" id="content" rows="50" cols="20"/><script id="editor" type="text/plain" style="width:970px;height:300px;"></script></td>
	    </tr>
	    <tr>
	        <td><form:label path="pic">upload image</form:label></td>
	        <td><div id="uploader-demo">
			    <div id="fileList" class="uploader-list"></div>
			    <!-- <div id="filePicker">选择图片</div> --><input type="file" id="filePicker"/><img src="/upload/logo/${article.pic}_250x350.jpg"/></div>
			</td>
	    </tr>
	    <tr>
	        <td><form:label path="status">course time</form:label></td>
	        <td><form:input path="courseTime" size="8"/></td>
	    </tr>
	    <tr>
	        <td><form:label path="status">status</form:label></td>
	        <td><form:radiobutton path="status" value="0"/>未发布<form:radiobutton path="status" value="1"/>已发布</td>
	    </tr>
	    <tr>
	        <td colspan="2">
	        	<form:hidden path="id"/>
	        	<form:hidden path="summary"/>
	        	<form:input path="pic"/>
	            <input type="submit" value="Submit"/>
	        </td>
	    </tr>
	</table>  
	</form:form>
</body>
</html>

	<script type="text/javascript" charset="utf-8" src="/js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="/js/ueditor/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="/js/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" src="/js/webuploader/webuploader.min.js"></script>
    
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <link href="/js/fileinput/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />
    <!--<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>-->
    <!-- canvas-to-blob.min.js is only needed if you wish to resize images before upload.
         This must be loaded before fileinput.min.js -->
    <script src="/js/fileinput/js/plugins/canvas-to-blob.min.js" type="text/javascript"></script>
    <script src="/js/fileinput/js/fileinput.min.js"></script>
    <!-- bootstrap.js below is only needed if you wish to the feature of viewing details
         of text file preview via modal dialog -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js" type="text/javascript"></script>
    <!-- optionally if you need translation for your language then include 
        locale file as mentioned below -->
    <script src="/js/fileinput/js/fileinput_locale_zh.js"></script>

    <script type="text/javascript">
    	
    	//var ue = UE.getEditor('editor');
        $(document).ready(function(){
	        var editor_a = new baidu.editor.ui.Editor();
	        editor_a.render('editor');
	        editor_a.ready(function() {
	            editor_a.setContent($('#content').val());
	        });
        });
		function checkForm(){
			var arr = [];
	        arr.push(UE.getEditor('editor').getContent());
	        var content = arr.join("\n");
	        $("#content").val(content);
	        $("#summary").val(getPlainTxt().substr(0, 400));
		}
	</script>
    <script type="text/javascript">
    var BASE_URL = "http://localhost:8080",
    $list = $('#fileList'),
    // 优化retina, 在retina下这个值是2
    ratio = window.devicePixelRatio || 1,
    // 缩略图大小
    thumbnailWidth = 100 * ratio,
    thumbnailHeight = 100 * ratio;
	
 	// 初始化Web Uploader
    var uploader = WebUploader.create({

        // 选完文件后，是否自动上传。
        auto: false,

        // swf文件路径
        swf: '/js/Uploader.swf',

        // 文件接收服务端。
        server: '/admin/article/upload_action',

        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#filePicker',

        // 只允许选择图片文件。
        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/*'
        }
    });
 	// 当有文件添加进来的时候
    uploader.on( 'fileQueued', function( file ) {
        var $li = $(
                '<div id="' + file.id + '" class="file-item thumbnail">' +
                    '<img>' +
                    '<div class="info">' + file.name + '</div>' +
                '</div>'
                ),
            $img = $li.find('img');


        // $list为容器jQuery实例
        $list.append( $li );

        // 创建缩略图
        // 如果为非图片文件，可以不用调用此方法。
        // thumbnailWidth x thumbnailHeight 为 100 x 100
        uploader.makeThumb( file, function( error, src ) {
            if ( error ) {
                $img.replaceWith('<span>不能预览</span>');
                return;
            }

            $img.attr( 'src', src );
        }, thumbnailWidth, thumbnailHeight );
    });
 	// 文件上传过程中创建进度条实时显示。
    uploader.on( 'uploadProgress', function( file, percentage ) {
        var $li = $( '#'+file.id ),
            $percent = $li.find('.progress span');

        // 避免重复创建
        if ( !$percent.length ) {
            $percent = $('<p class="progress"><span></span></p>')
                    .appendTo( $li )
                    .find('span');
        }

        $percent.css( 'width', percentage * 100 + '%' );
    });

    // 文件上传成功，给item添加成功class, 用样式标记上传成功。
    uploader.on( 'uploadSuccess', function( file ) {
        $( '#'+file.id ).addClass('upload-state-done');
    });

    // 文件上传失败，显示上传出错。
    uploader.on( 'uploadError', function( file ) {
        var $li = $( '#'+file.id ),
            $error = $li.find('div.error');

        // 避免重复创建
        if ( !$error.length ) {
            $error = $('<div class="error"></div>').appendTo( $li );
        }

        $error.text('上传失败');
    });

    // 完成上传完了，成功或者失败，先删除进度条。
    uploader.on( 'uploadComplete', function( file ) {
        $( '#'+file.id ).find('.progress').remove();
    });
    </script>