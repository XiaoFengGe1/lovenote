var selectFlag = 0;
var keyword = "";
var buttonFlag = 1;
var selectMsg = 0;
var toAddress="";
var showNum =0;
var progressNum=0;
var load;
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
	 layer.tips('请发邮件至1174254785@qq.com','#tuijain',{tips: [1, '#3595CC'],time: 2200});
});

$("#searchBtn").on("click",function(){
	if(buttonFlag ==0){return;}buttonFlag =0;
	searchMain();
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
	keyword = $("#inputSearch").val();
	keyword = $.trim(keyword);
	keyword = keyword.replace(/[']/g,"");
	if(keyword==""){
		 layer.tips('请输入关键词','#searchBtn',{tips: [2, '#3595CC'],time: 1000});
		 buttonFlag = 1;
	}else{
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
	        	if(data.total==0){
	        		 layer.tips(toAddress+'没有匹配数据','#searchBtn',{tips: [2, '#3595CC'],time: 1000});
	        		 return;
	        	}
	        	var showhtml="";
	        	$.each(data.data,function(j, item) {
	        		    var note1='<div class="mySearch mySearchHeight"><h3 class="myFontTitle" style="display:inline;">';
	        		    var note2=item["title"]+'</h3>';
	        		    var note3='<div class="blog-post-meta myFontFooter myblogFooter">'
	        		    var note4=item["author"]+"</div>";
	        		    var note5='<div class="myFontContent">';
	        		    var note6=item["content"]+"</div>";
	        		    var	note7='</div>';
	        		   showhtml = showhtml+note1+note2+note5+note6+note3+note4+note7;
						
	        	 });
	        	
	        	$("#noteBody").append('<div class="mySearchList"><h2 class="mySearchListTitle"><span class="glyphicon glyphicon-map-marker"></span>'+Address+'</h2>'+showhtml+'</div>');
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
