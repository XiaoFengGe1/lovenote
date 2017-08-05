/**
 * 
 */
var noteId=0;
var load;
var buttonFlag = 1;
var  page = 1;
var reviewTotal=0;
$(document).ready(function(){
	 $.ajax({
	        type: "post",
	        url: "/getNote",
	        async:true,
	        data: {},
	        dataType: "json",
	        beforeSend: function(XMLHttpRequest){
	       	   load = layer.load(0, {shade: false});
	        		},
	        complete: function(XMLHttpRequest,textStatus){
	        	
	        		},
	        success:function(data,textStatus){
	        	layer.close(load);
	        	if(data.status=="false"){
	        		alert(333);
	        		 location.href="/index";
	        		 return;
	        	}
	        	$("#title").html(data.data["title"]);
	        	$("meta[name='description']").attr("content","标题:"+data.data["title"]); 
	        	//$("#myweibo").attr("default_text",data.data["title"]);
	        	$("#timeName").html(timeFormat(data.data["createtime"],'yyyy-MM-dd HH:mm:ss')+"  | <span class='glyphicon glyphicon-tree-conifer'>"+data.data["type"]+" | <span class='glyphicon glyphicon-user'>"+data.data["uname"]+"  | <span class='glyphicon glyphicon-eye-open'>"+data.data["click"]+"  | <span class='glyphicon glyphicon-heart'>"+data.data["likenum"]);
	        	$("#content").html("&nbsp;"+data.data["content"]);
	        	if(data.data["part"]==""){
	        		 $("#likeNote").html('<span class="glyphicon glyphicon-heart"></span>&nbsp已赞');
	        		 $("#likeNote").attr("disabled","true");
	        	}
	        	if(data.data["unlike"]==1){
	        		$("#editNote").show();
	        		$("#deleteNote").show();
	        	}
	        	noteId = data.data["id"];
	               },
	         error:function(XMLHttpRequest, textStatus, errorThrown){
	        	 layer.close(load);
	         }
	    });
	 
	 getReview();
	 
});


$("#editNote").on("click",function(){
	sessionStorage.writeNoteId = noteId;
	location.href="/write";
});

$("#sendreview").on("click",function(){
	if(buttonFlag ==0){return;}buttonFlag =0;
	if(sessionStorage.isLogin==0){
		layer.tips('请登录','#sendreview',{tips: [2, '#3595CC'],time: 2000});
		return
	}
	if($("#reviewContent").val().trim()==""){
		layer.tips('请输入评论','#sendreview',{tips: [2, '#3595CC'],time: 2000});
		return
	}
	 $.ajax({
	        type: "POST",
	        url: "/addreview",
	        async:true,
	        data: {content:$("#reviewContent").val().trim().replace(/[']/g,"")},
	        dataType: "json",
	        success:function(data,textStatus){
	        	if(data.status=="true"){
	        		 layer.tips('评论成功，正在刷新页面','#sendreview',{tips: [2, '#3595CC'],time: 1000});
	        		 location.href="/note?id="+data.noteid;
	        		 return;
	        	}else{
	        		 layer.tips('服务器错误，请稍后再试','#sendreview',{tips: [2, '#3595CC'],time: 1000});
	        	}
	        	setButtonEnabled();
	               },
	         error:function(XMLHttpRequest, textStatus, errorThrown){
	        	 layer.tips('服务器错误，请稍后再试','#deleteNote',{tips: [2, '#3595CC'],time: 1000});
	        	 setButtonEnabled();
	         }
	    });
});

$("#deleteNote").on("click",function(){
	if(buttonFlag ==0){return;}buttonFlag =0;
	layer.confirm('确定删除吗？', {
	    btn: ['确定','取消'] //按钮
	}, function(){
		 $.ajax({
		        type: "POST",
		        url: "/deleteNote",
		        async:true,
		        data: {},
		        dataType: "json",
		        success:function(data,textStatus){
		        	if(data.status=="true"){
		        		 layer.tips('删除成功，正在跳转首页','#deleteNote',{tips: [2, '#3595CC'],time: 1000});
		        		 location.href="/index";
		        		 return;
		        	}else{
		        		 layer.tips('服务器错误，请稍后再试','#deleteNote',{tips: [2, '#3595CC'],time: 1000});
		        	}
		        	setButtonEnabled();
		               },
		         error:function(XMLHttpRequest, textStatus, errorThrown){
		        	 layer.tips('服务器错误，请稍后再试','#deleteNote',{tips: [2, '#3595CC'],time: 1000});
		        	 setButtonEnabled();
		         }
		    });
	}, function(){
	
	});
	
});

$("#likeNote").on("click",function(){
	if(buttonFlag ==0){return;}buttonFlag =0;
	 $.ajax({
		        type: "POST",
		        url: "/likeNote",
		        async:true,
		        data: {},
		        dataType: "json",
		        success:function(data,textStatus){
		        	if(data.status=="true"){
		        		 $("#likeNote").html('<span class="glyphicon glyphicon-heart"></span>&nbsp已赞');
		        		 layer.tips('ok','#likeNote',{tips: [2, '#3595CC'],time: 1000});
		        	}else{
		        		 layer.tips('服务器错误，请稍后再试','#likeNote',{tips: [2, '#3595CC'],time: 1000});
		        	}
		        	setButtonEnabled();
		               },
		         error:function(XMLHttpRequest, textStatus, errorThrown){
		        	 layer.tips('服务器错误，请稍后再试','#likeNote',{tips: [2, '#3595CC'],time: 1000});
		        	 setButtonEnabled();
		         }
		    });
	
});

$("#pageForward").on("click",function(){
	if(buttonFlag ==0){return;}buttonFlag =0;
	page=page+1;
	getReview();
});

$("#pageBack").on("click",function(){
	if(buttonFlag ==0){return;}buttonFlag =0;
	page=page-1;
	getReview();
});

function getReview(){
	 $.ajax({
	        type: "post",
	        url: "/getreview",
	        async:true,
	        data: {page:page},
	        dataType: "json",
	        beforeSend: function(XMLHttpRequest){
	       	   load = layer.load(0, {shade: false});
	       	   $("#reviews").html('');
	        		},
	        complete: function(XMLHttpRequest,textStatus){
	        	
	        		},
	        success:function(data,textStatus){
	        	layer.close(load);
	        	if(data.status=="false"){
	        		alert(222);
	        		 location.href="/index";
	        		 return;
	        	}
	        	var showhtml="";
	        	reviewTotal = data.total;
	        	$.each(data.data,function(j, item) {
	     		    var note1='<p><strong>'+((page-1)*10+j+1)+'#楼 : </strong>';
	     		    var note2=item["content"]+'</p>';
	     		    var note3='<p><span style="float: right">'
	     		    var note4=timeFormat(item["time"],'yyyy-MM-dd:HH')+' | <span class="glyphicon glyphicon-user">'+item["uname"]+'</span></p><hr style="height:1px;border:none;border-top:1px dashed #0066CC;"/>';
	     		    showhtml = showhtml+note1+note2+note3+note4;
	        	});
	        	$("#reviews").append(showhtml);
	        	setButtonEnabled();
	        	pageInit();
	         },
	         error:function(XMLHttpRequest, textStatus, errorThrown){
	        	 layer.close(load);
	        	 setButtonEnabled();
	         }
	    });
}

function pageInit(){
	 if(page<2){
		 $("#pageBack").attr("disabled",true);
	 }else{
		 $("#pageBack").removeAttr("disabled");
	 }
	 if(reviewTotal > page*10){
		 $("#pageForward").removeAttr("disabled");
	 }else{
		 $("#pageForward").attr("disabled",true);
	 }
}

function setButtonEnabled(){
	window.setTimeout(setButtonEnable,100); 
	
}
function setButtonEnable(){
	buttonFlag = 1;
}


var timeFormat = function(time, format){
    var t = new Date(time);
    var tf = function(i){return (i < 10 ? '0' : '') + i};
    return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a){
        switch(a){
            case 'yyyy':
                return tf(t.getFullYear());
                break;
            case 'MM':
                return tf(t.getMonth() + 1);
                break;
            case 'mm':
                return tf(t.getMinutes());
                break;
            case 'dd':
                return tf(t.getDate());
                break;
            case 'HH':
                return tf(t.getHours());
                break;
            case 'ss':
                return tf(t.getSeconds());
                break;
        }
    })
}

