$(document).ready(function(){
	if($("#myspan").attr("value") == "登陆"){
		sessionStorage.isLogin = 0;
		$("#user").hide();
	}else if($("#myspan").attr("value") == "注销"){
		sessionStorage.isLogin = 1;
		$("#user").show();
	}
	setActive();
	$("#quitAPP").on("click",function(){
		if($("#myspan").attr("value") == "登陆"){
			location.href="/login";
		}else if($("#myspan").attr("value") == "注销"){
			$.ajax({
		        type: "POST",
		        url: "/quit",
		        async:true,
		        data: {},
		        dataType: "json",
		        success:function(data,textStatus){
			        location.href= "/login";
		        },
		    });
		}
	});
});

var show=1;
$("#search").on("click",function(){
	location.href="/search";
});
$("#mysearch").on("click",function(){
	location.href="/mysearch";
});
$("#user").on("click",function(){
	location.href="/user";
});
$("#index").on("click",function(){
	location.href="/index";
});
$("#weixin").on("click",function(){
	if(show == 1){
		$("#weixinPic").show();
		show =0;
	}else{
		$("#weixinPic").hide();
		show =1;
	}
});
function setActive(){
	var url = window.location.href;
	if(url.indexOf("search")>=0){
		$("#search").attr("class","blog-nav-item active clickme");
	}else if(url.indexOf("user")>=0){
		$("#user").attr("class","blog-nav-item active clickme");
	}else {
		$("#index").attr("class","blog-nav-item active clickme");
	}
}