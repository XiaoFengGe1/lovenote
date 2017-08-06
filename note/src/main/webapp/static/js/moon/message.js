var minId=1000000;
var buttonFlag = 1;
$(document).ready(function(){
	getMessageInit();
});

$("#getMore").on("click",function(){
	if(buttonFlag ==0){return;}buttonFlag =0;
	getMessage();
});

$("#sendMessage").on("click",function(){
	if($("#messageContent").val().trim()==""){
		layer.tips('请输入留言','#sendMessage',{tips: [2, '#3595CC'],time: 2000});
		return
	}
	var content = $("#messageContent").val().trim().replace(/[']/g,"");
	if(buttonFlag ==0){return;}buttonFlag =0;
	 $.ajax({
	        type: "POST",
	        url: "/addMessage",
	        async:true,
	        data: {content:content},
	        dataType: "json",
	        success:function(data,textStatus){
	        	if(data.status=="true"){
	        		 layer.tips('留言成功,刷新可看','#sendMessage',{tips: [2, '#3595CC'],time: 1000});  		 
	        	}else{
	        		 layer.tips('服务器错误，请稍后再试','#sendMessage',{tips: [2, '#3595CC'],time: 1000});
	        	}
	        	setButtonEnabled();
	               },
	         error:function(XMLHttpRequest, textStatus, errorThrown){
	        	 layer.tips('服务器错误，请稍后再试','#sendMessage',{tips: [2, '#3595CC'],time: 1000});
	        	 setButtonEnabled();
	         }
	    });
});

function getMessage(){
	if(minId != 1000000 && minId>1){
		 $.ajax({
		        type: "post",
		        url: "/getMessage",
		        async:true,
		        data: {end:minId-1},
		        dataType: "json",
		        beforeSend: function(XMLHttpRequest){
		       	   load = layer.load(0, {shade: false});
		        		},
		        complete: function(XMLHttpRequest,textStatus){
		        	
		        		},
		        success:function(data,textStatus){
		        	layer.close(load);
		        	var showhtml="";
		        	if(data.status=="true"){
		        		var messageTotal = data.total;
			        	$.each(data.data,function(j, item) {
			        		var name = item["uname"];
			        		if(name == null || name == ""){
			        			name = "匿名";
			        		}
			        		var id = item["id"];
			        		if(id < minId){
			        			minId = id;
			        		}
			     		    var note1='<p><strong>'+id+'楼:</strong>';
			     		    var note2=item["content"];
			     		    var note3='<span style="float: right">';
			     		    var note4=timeFormat(item["time"],'yyyy-MM-dd HH:mm:ss')+' | <span class="glyphicon glyphicon-user">'+name+'</span></span></p>';
			     		    showhtml = showhtml+note1+note2+note3+note4;
			        	});
			        	if(minId == 1){
			        		var endhtml ='<div style="position:relative; width:100%; display:table; *position:absolute; *top:50%; *left:0;"><p style="text-align:center;">-----------我是有底线的-----------</p></div>';
			        		showhtml = endhtml+showhtml;
			        		$("#getMore").hide();
			        	}else{
			        		$("#getMore").show();
			        	}
			        	$("#messageDiv").prepend(showhtml);
		        	}
		        	setButtonEnabled();
		         },
		         error:function(XMLHttpRequest, textStatus, errorThrown){
		        	 layer.close(load);
		        	 setButtonEnabled();
		         }
		    });
	}else{
		 layer.tips('没有更多数据了','#getMore',{tips: [2, '#3595CC'],time: 1000});
		setButtonEnabled();
	}
}


function getMessageInit(){
	 $.ajax({
	        type: "post",
	        url: "/getMessageInit",
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
	        	var showhtml="";
	        	if(data.status=="true"){
	        		var messageTotal = data.total;
		        	$.each(data.data,function(j, item) {
		        		var name = item["uname"];
		        		if(name == null || name == ""){
		        			name = "匿名";
		        		}
		        		var id = item["id"];
		        		if(id < minId){
		        			minId = id;
		        		}
		     		    var note1='<p><strong>'+id+'楼:</strong>';
		     		    var note2=item["content"];
		     		    var note3='<span style="float: right">';
		     		    var note4=timeFormat(item["time"],'yyyy-MM-dd HH:mm:ss')+' | <span class="glyphicon glyphicon-user">'+name+'</span></span></p>';
		     		    showhtml = showhtml+note1+note2+note3+note4;
		        	});
		        	if(minId == 1){
		        		$("#getMore").hide();
		        	}else{
		        		$("#getMore").show();
		        	}
	        	}else{
	        		showhtml = "<p>还没有留言，赶紧留一条吧</p>";
	        		$("#getMore").hide();
	        	}
	        	$("#messageDiv").prepend(showhtml);
	        	setButtonEnabled();
	         },
	         error:function(XMLHttpRequest, textStatus, errorThrown){
	        	 layer.close(load);
	        	 setButtonEnabled();
	         }
	    });
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
    });
}

function setButtonEnabled(){
	window.setTimeout(setButtonFlagEnable,100); 
	
}
function setButtonFlagEnable(){
	buttonFlag = 1;
}