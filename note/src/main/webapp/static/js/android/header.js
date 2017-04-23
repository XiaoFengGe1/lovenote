 $(document).ready(function(){
	setActive();
});

	$("#search").on("click",function(){
		location.href="/android/search";
	});
	$("#money").on("click",function(){
		location.href="/android/money";
	});
	$("#index").on("click",function(){
		location.href="/android/index";
	});
	function setActive(){
		var url = window.location.href;
		if(url.indexOf("search")>=0){
			$("#search").attr("class","blog-nav-item active clickme");
		}else if(url.indexOf("index")>=0){
			$("#index").attr("class","blog-nav-item active clickme");
		}else{
			$("#money").attr("class","blog-nav-item active clickme");
		}
	}