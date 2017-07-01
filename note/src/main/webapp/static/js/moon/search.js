var selectFlag = 0;
var keyword = "";
var buttonFlag = 1;
var selectMsg = 0;
var toAddress="";
var showNum =0;
var progressNum=0;
var load;
var nodataAdress = "";
var keys = 1;
var titelMap = new Map();
var contentMap = new Map();
$("#selectAll").on("click",function(){
	if(selectFlag == 0){
		selectFlag = 1;
		$('input:checkbox').each(function() {
	        $(this).prop('checked',true);
		});
	}else{
		selectFlag = 0;
		$('input:checkbox').each(function () {
	        $(this).prop('checked',false);
		});
	}
});

$("#tuijain").on("click",function(){
	 layer.tips('请发邮件至442123897@qq.com','#tuijain',{tips: [1, '#3595CC'],time: 2200});
});

$("#searchBtn").on("click",function(){
	if(buttonFlag ==0){return;}buttonFlag =0;
	searchMain();
});

$("#noteBody").on("click",".myblack",function(){
	var username = getCookie("username");
	if(username!=null&&username!=""){
		var title =titelMap.get($(this).val());
		var content = contentMap.get($(this).val());
		var url = title.split('href="')[1].split('"')[0];
		var titleName = title.replace(/<[^>]+>/g,"").trim().replace(/\s+/g,"");
		var id ="#black"+$(this).val();
		 $.ajax({
		        type: "POST",
		        url: "/addBlack",
		        async:true,
		        data: {titleName:titleName,url:url},
		        dataType: "json",
		        beforeSend: function(XMLHttpRequest){
		        	$(id).attr("disabled", true);
		       	  	},
		        complete: function(XMLHttpRequest,textStatus){
		        	
		        		},
		        success:function(data,textStatus){
		        	if(data.status=="false"){
		        		layer.tips('服务器异常',$(id),{tips: [1, '#3595CC'],time: 2000});
		        		 $(id).attr("disabled", false);
		        	}else if(data.status=="timeout"){
		        		layer.tips('登录超时',$(id),{tips: [1, '#3595CC'],time: 2000});
		        		 $(id).attr("disabled", false);
		        	}else{
		        		layer.tips('已成功拉黑',$(id),{tips: [1, '#3595CC'],time: 2000});
		        	}
		        	   },
		         error:function(XMLHttpRequest, textStatus, errorThrown){
		        	 $(id).attr("disabled", false);
		        	 layer.tips('未知异常',$(id),{tips: [1, '#3595CC'],time: 2000});
		         }
		    });
	}else{
		layer.tips('请先登录',$(id),{tips: [1, '#3595CC'],time: 2000});
	}

});

$("#noteBody").on("click",".mylove",function(){
	var username = getCookie("username");
	if(username!=null&&username!=""){
		var title =titelMap.get($(this).val());
		var content = contentMap.get($(this).val());
		var url = title.split('href="')[1].split('"')[0];
		var titleName = title.replace(/<[^>]+>/g,"").trim().replace(/\s+/g,"");
		var part = content.replace(/<[^>]+>/g,"").trim().replace(/\s+/g,"");
		var id ="#love"+$(this).val();
		part = part.substr(0,150);
		 $.ajax({
		        type: "POST",
		        url: "/addLove",
		        async:true,
		        data: {titleName:titleName,url:url,content:content,part:part},
		        dataType: "json",
		        beforeSend: function(XMLHttpRequest){
		        	$(id).attr("disabled", true);
		       	  	},
		        complete: function(XMLHttpRequest,textStatus){
		        	
		        		},
		        success:function(data,textStatus){
		        	if(data.status=="false"){
		        		layer.tips('服务器异常',$(id),{tips: [1, '#3595CC'],time: 2000});
		        		 $(id).attr("disabled", false);
		        	}else if(data.status=="timeout"){
		        		layer.tips('登录超时',$(id),{tips: [1, '#3595CC'],time: 2000});
		        		 $(id).attr("disabled", false);
		        	}else if(data.status=="toomany"){
		        		layer.tips('同名笔记太多,无法保存笔记',$(id),{tips: [1, '#3595CC'],time: 2000});
			        	
		        	}else{
		        		layer.tips('已成功收藏并存入笔记',$(id),{tips: [1, '#3595CC'],time: 2000});
		        	}
		        	   },
		         error:function(XMLHttpRequest, textStatus, errorThrown){
		        	 layer.tips('未知异常',$(id),{tips: [1, '#3595CC'],time: 2000});
		        	 $(id).attr("disabled", false);
		         }
		    });
	}else{
		layer.tips('请先登录',$("#"+id),{tips: [1, '#3595CC'],time: 2000});
	}
});

$(this).keydown(function (e){
	if(e.which == "13"){
		var focusActId = document.activeElement.id;
		if(focusActId == 'inputSearch'){
			if(buttonFlag ==0){return;}buttonFlag =0;
			searchMain();
		}
	}
});

function searchMain(){
	nodataAdress = "";
	keyword = $("#inputSearch").val();
	keyword = $.trim(keyword);
	keyword = keyword.replace(/[']/g,"");
	if(keyword==""){
		 layer.tips('请输入关键词','#searchBtn',{tips: [2, '#3595CC'],time: 1000});
		 buttonFlag = 1;
	}else{
		keys = 1;
		titelMap = new Map();
		contentMap = new Map();
		getResult();
	}
}

function getResult(){
	var flag=0;
	$('input:checkbox').each(function() {
        if($(this).prop('checked')==true){
        	selectMsg = selectMsg + 1;
        	flag = 1;
        }
	});
	if(flag == 0){
		layer.tips('请勾选需要搜索的网站','#searchBtn',{tips: [2, '#3595CC'],time: 1500});
		buttonFlag = 1;
		return;
	}
	showNum = $("#showNum").val();
	$("#noteBody").html('');
	if($("#baidu").prop('checked')==true){
		toAddress = "百度";
		progressNum = progressNum+1;
		searchData(toAddress);
	} 
	if($("#mynet").prop('checked')==true){
		toAddress = "本站";
		progressNum = progressNum+1;
		searchData(toAddress);
	} 
	if($("#csdn").prop('checked')==true){
		toAddress = "CSDN";
		progressNum = progressNum+1;
		searchData(toAddress);
	}
	if($("#jioaben").prop('checked')==true){
		toAddress = "脚本之家";
		progressNum = progressNum+1;
		searchData(toAddress);
	}
	if($("#bokeyuan").prop('checked')==true){
		toAddress = "博客园";
		progressNum = progressNum+1;
		searchData(toAddress);
	}
	if($("#sina").prop('checked')==true){
		toAddress = "新浪博客";
		progressNum = progressNum+1;
		searchData(toAddress);
	}
	if($("#wangyi").prop('checked')==true){
		toAddress = "网易博客";
		progressNum = progressNum+1;
		searchData(toAddress);
	}
	if($("#qiandaunli").prop('checked')==true){
		toAddress = "前端里";
		progressNum = progressNum+1;
		searchData(toAddress);
	}
	if($("#manong").prop('checked')==true){
		toAddress = "码农网";
		progressNum = progressNum+1;
		searchData(toAddress);
	}
	if($("#qiandaunkaifa").prop('checked')==true){
		toAddress = "前端开发网";
		progressNum = progressNum+1;
		searchData(toAddress);
	} 
	
	if(nodataAdress.length>1){
		layer.tips(nodataAdress.substring(0, nodataAdress.length-1)+'没有匹配数据','#searchBtn',{tips: [2, '#3595CC'],time: 1000});
	}
}
 

function searchData(Address){
	 $.ajax({
	        type: "POST",
	        url: "/getSearch",
	        async:true,
	        data: {keyword:keyword,to:Address,num:showNum},
	        dataType: "json",
	        beforeSend: function(XMLHttpRequest){
	       	   load = layer.load(0, {shade: false});
	        		},
	        complete: function(XMLHttpRequest,textStatus){
	        	
	        		},
	        success:function(data,textStatus){
	        	layer.close(load);
	        	buttonFlag =1;
	        	var allnum=0;
	        	if(data.status=="false"){
	        		 layer.tips('查询异常','#searchBtn',{tips: [2, '#3595CC'],time: 1500});
	        		 return;
	        	}
	        	if(data.total=="0"){
	        		nodataAdress = nodataAdress + Address+",";
	        		  return;
	        	}
	        	var showhtml="";
	        	$.each(data.data,function(j, item) {
	        			var isLike = item["isLike"];
	        			var note0 = "";
	        			keys = keys+1;
	        			if(isLike == "true"){
	        				note0 = '<button class="btn btn-xs glyphicon glyphicon-heart">已收藏</button>';
	        			}else{
	        				var ketTemp = ""+keys;
	        				var bid = "black"+keys;
	        				var lid = "love"+keys;
	        				titelMap.set(ketTemp,item["title"]);
	        				contentMap.set(ketTemp,item["content"]);
	        				note0 = '<button class="btn btn-xs glyphicon glyphicon-remove myblack" id ='+bid+' value="'+ketTemp+'"></button>&nbsp;<button class="btn btn-xs glyphicon glyphicon-heart-empty mylove" id ='+lid+' value="'+ketTemp+'"></button>&nbsp;&nbsp;';
	        			}
	        		    var note1='<div class="mySearch mySearchHeight">'+note0+'<h3 class="myFontTitle" style="display:inline;">';
	        		    var note2=item["title"]+'</h3>';
	        		    var note3='<div class="myFontContent">';
	        		    var note4=item["content"]+"</div>";
	        		    var note5='<div class="blog-post-meta myFontFooter myblogFooter">';
	        		    var note6=item["author"]+"</div>";
	        		    var	note7='</div>';
	        		   showhtml = showhtml+note1+note2+note3+note4+note5+note6+note7;
						
	        	 });
	        	
	        	$("#noteBody").append('<div class="mySearchList"><h3 class="mySearchListTitle"><span class="glyphicon glyphicon-map-marker">'+Address+'</h3>'+showhtml+'</div>');
	        	//能够解决append自动补全标签的bug.
	        	//$("#noteBody").html('<div class="mySearchList"><h3 class="mySearchListTitle">来自'+to+'</h3>'+$("#noteBody").html()+'</div>');
	               },
	         error:function(XMLHttpRequest, textStatus, errorThrown){
	        	 layer.close(load);
	        	 buttonFlag =1;
	        	 layer.tips('服务器错误，请稍后再试','#searchBtn',{tips: [2, '#3595CC'],time: 1500});
	         }
	    });
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