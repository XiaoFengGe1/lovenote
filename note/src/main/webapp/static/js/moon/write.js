/**
 * 
 */
var um = UM.getEditor('myEditor');
window.UEDITOR_HOME_URL = "/upload";
var load;
var noteId=0;
var title;
var nowType;
var buttonFlag = 1;
$(document).ready(function(){
	var username = getCookie("username");
	if(username==null||username==""){
		location.href="/login";
		return;
	}
	if(sessionStorage.writeNoteId == 0){
		
	}else{
		noteId = sessionStorage.writeNoteId;
		 $("#saveBtn").html('<span class="glyphicon glyphicon-wrench"></span>&nbsp修改');
		 $("#saveBtn").val("修改");
		 $.ajax({
		        type: "POST",
		        url: "/getNote",
		        async:false,
		        data: {noteId:noteId},
		        dataType: "json",
		        beforeSend: function(XMLHttpRequest){
		       	   load = layer.load(0, {shade: false});
		        		},
		        complete: function(XMLHttpRequest,textStatus){
		        	
		        		},
		        success:function(data,textStatus){
		        	layer.close(load);
		        	if(data.status=="false"){
		        		 location.href="/write";
		        		 return;
		        	}
		        	title = data.data["title"];
		        	$("#titleInput").val(title);
		        	UM.getEditor('myEditor').setContent(data.data["content"]);
		        	nowType = data.data["type"];
		               },
		         error:function(XMLHttpRequest, textStatus, errorThrown){
		        	 layer.close(load);
		        	 layer.tips('服务器错误，请稍后再试','#searchBtn',{tips: [2, '#3595CC'],time: 1000});
		         }
		    });
	}
	 getClass();
});

$("#saveBtn").on("click",function(){
	if($("#titleInput").val()==""){
		layer.tips('请输入标题', '#saveBtn', {
		    tips: [1, '#3595CC'],
		    time: 1000
		});
		return;
	}
	if(UM.getEditor('myEditor').getContent()==""){
		layer.tips('请输入正文', '#saveBtn', {
		    tips: [1, '#3595CC'],
		    time: 1000
		});
		return;
	}
	if(buttonFlag ==0){return;}buttonFlag =0;
	var part = UM.getEditor('myEditor').getContentTxt();
	part = part.replace(/<[^>]+>/g,"");
	part = part.replace(/[\r\n]/g,"");
	part = part.replace(/[ ]/g,"");
	part = part.substr(0,150);
	if(part.length>145){
		part = part+"。。。";
	}
	var mytitle = $("#titleInput").val();
	mytitle = mytitle.replace(/[']/g,"");
	if($("#saveBtn").val()=="发布"){
		 $.ajax({
		        type: "POST",
		        url: "/addNote",
		        async:true,
		        data: {type:$("#selectClass").val(),title:mytitle,content:UM.getEditor('myEditor').getContent(),part:part},
		        dataType: "json",
		        beforeSend: function(XMLHttpRequest){
		       	   load = layer.load(0, {shade: false});
		        		},
		        complete: function(XMLHttpRequest,textStatus){
		          
		        		},
		        success:function(data,textStatus){
		        	layer.close(load);
		        	if(data.status == "true"){
		        		layer.tips('恭喜您，发布成功','#saveBtn',{tips: [2, '#3595CC'],time: 2000});
		        		$("#titleInput").val("");
		        		UM.getEditor('myEditor').execCommand('cleardoc');
		        	}else if(data.status == "has"){
		        		 layer.tips('标题已存在，请重命名','#saveBtn',{tips: [2, '#3595CC'],time: 1000});
		        	}else if(data.status=="timeout"){
		        		layer.msg('页面过期，正在刷新页面');
		        		location.href= "/index";
		        		return;
		        	}else{
		        		layer.tips('服务器错误，请稍后再试','#saveBtn',{tips: [2, '#3595CC'],time: 1000});
		        	}
		        	setButtonEnabled();
		               },
		         error:function(XMLHttpRequest, textStatus, errorThrown){
		        	 layer.close(load);
		        	 layer.tips('服务器错误，请稍后再试','#saveBtn',{tips: [2, '#3595CC'],time: 1000});
		        	 setButtonEnabled();
		         }
		    });
	}else{
		 $.ajax({
		        type: "POST",
		        url: "/fixNote",
		        async:true,
		        data: {type:$("#selectClass").val(),title:mytitle,content:UM.getEditor('myEditor').getContent(),id:noteId,part:part},
		        dataType: "json",
		        beforeSend: function(XMLHttpRequest){
		       	   load = layer.load(0, {shade: false});
		        		},
		        complete: function(XMLHttpRequest,textStatus){
		          
		        		},
		        success:function(data,textStatus){
		        	layer.close(load);
		        	if(data.status == "true"){
		        		layer.tips('恭喜您，修改成功','#saveBtn',{tips: [2, '#3595CC'],time: 2000});
		        		location.href= "/note?id="+noteId;
		        		return;
		        	}else if(data.status == "has"){
		        		 layer.tips('标题已存在，请重命名','#saveBtn',{tips: [2, '#3595CC'],time: 1000});
		        	}else if(data.status=="timeout"){
		        		layer.msg('页面过期，正在刷新页面');
		        		location.href= "/index";
		        		return;
		        	}else{
		        		location.href="/write";
		        		return;
		        	}
		        	setButtonEnabled();
		               },
		         error:function(XMLHttpRequest, textStatus, errorThrown){
		        	 layer.close(load);
		        	 layer.tips('服务器错误，请稍后再试','#saveBtn',{tips: [2, '#3595CC'],time: 1000});
		        	 setButtonEnabled();
		         }
		    });
	}
	
});

function getClass(){
	 $.ajax({
	        type: "POST",
	        url: "/getClass",
	        async:false,
	        data: {},
	        dataType: "json",
	        beforeSend: function(XMLHttpRequest){
	       	   load = layer.load(0, {shade: false});
	        		},
	        complete: function(XMLHttpRequest,textStatus){
	           
	        		},
	        success:function(data,textStatus){
	        	 layer.close(load);
	        	 if(data.status=="timeout"){
		        		layer.msg('页面过期，正在刷新页面');
		        		location.href= "/index";
		        		return;
		        	}else{
		        		 $.each(data.data,function(j, item) {
		        			 var class2 = item["class"];
		        			 $("#selectClass").append("<option value='"+class2+"'>"+class2+"</option>");
		        		 });
				   if(noteId!=0){
				        $("#selectClass").val(nowType);
				   }
		        	}
	        	
	               },
	         error:function(XMLHttpRequest, textStatus, errorThrown){
	        	 layer.close(load);
	        	 layer.tips('服务器错误，请稍后再试','#saveBtn',{tips: [2, '#3595CC'],time: 1000});
	         }
	    });
}

function setButtonEnabled(){
	window.setTimeout(setButtonEnable,100); 
	
}
function setButtonEnable(){
	buttonFlag = 1;
}

function getCookie(name){
	if(document.cookie.length>0){
		var arr = document.cookie.split("; ");
		for(var i=0;arr.length;i++){
			var cookie = arr[i].split("=");
			if(cookie[0]==name){
				return cookie[1];
				break;
			}
		}
	}
	return "";
}