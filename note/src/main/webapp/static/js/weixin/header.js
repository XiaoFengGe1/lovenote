 $(document).ready(function(){
	if($("#myloginspan").attr("value") == "登陆"){
		sessionStorage.isLogin = 0;
	}else{
		sessionStorage.isLogin = 1;
	}
	setActive();
});

	$("#search").on("click",function(){
		location.href="/search";
	});
	$("#user").on("click",function(){
		location.href="/user";
	});
	$("#index").on("click",function(){
		location.href="/index";
	});
	$("#writeBtn").on("click",function(){
		sessionStorage.writeNoteId = 0;
		location.href="/write";
	});
	function setActive(){
		var url = window.location.href;
		if(url.indexOf("search")>=0){
			$("#search").attr("class","blog-nav-item active clickme");
		}else if(url.indexOf("user")>=0){
			$("#user").attr("class","blog-nav-item active clickme");
		}else if(url.indexOf("write")>=0){
			$("#writeBtn").attr("class","blog-nav-item active clickme");
		}else{
			$("#index").attr("class","blog-nav-item active clickme");
		}
	}