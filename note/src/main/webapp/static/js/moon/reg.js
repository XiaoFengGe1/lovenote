
$(document).ready(function(){
	$("#sixNumDiv").hide();
	$("#nameDiv").hide();
});

var wait=60; 
var emailreg = /^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$/;
var passwordreg = /^[a-zA-Z0-9]{6,50}$/;
var sixNum = /^[0-9]{6}$/
$("#reg").on("click",function(){
	 if(judgeEmail()==false){
		 return;
	 }
	if($(this).html()=="发送验证码"){
		if(judgeEmail()==true){
			 $.ajax({
	             type: "POST",
	             url: "/findEmail",
	             async:true,
	             data: {email:$("#regEmail").val()},
	             dataType: "json",
	             beforeSend: function(XMLHttpRequest){
	            	 $("#reg").attr('disabled',"true");
	            	 $("#reg").html("验证邮箱中- - -");
	             		},
	             complete: function(XMLHttpRequest,textStatus){
	            	
	             		},
	             success:function(data,textStatus){
			                if(data.status=="true"){
			                	 $("#emailDiv").hide();
			                	 $("#sixNumDiv").show();
				            	 $("#reg").removeAttr("disabled");
				            	 $("#reg").html("提交");
				            	 localStorage.lovelxfEmail = $("#regEmail").val();
			                }else{
			                	 layer.alert("邮箱已注册");
			                	 $("#reg").removeAttr("disabled");
				            	 $("#reg").html("发送验证码");
				            	 $("#regEmail").val("");
			                }
	                    },
	             error:function(XMLHttpRequest, textStatus, errorThrown){
	            	 error();
	              }
	         });
			
		};
		 $("#reg").html("验证成功，正在发送- - -");
	}

	if($(this).html()=="提交"){
		 if(judgeSixNum()==false){
			 return;
		 }
		if(judgeSixNum()==true){
			 $.ajax({
	             type: "POST",
	             url: "/checkSixNum",
	             async:true,
	             data: {sixNum:$("#regSixNum").val()},
	             dataType: "json",
	             beforeSend: function(XMLHttpRequest){
	            	 $("#reg").attr('disabled',"true");
	            	 $("#reg").html("正在发送验证码- - -");
	             		},
	             complete: function(XMLHttpRequest,textStatus){

	             		},
	             success:function(data,textStatus){
			                if(data.status=="true"){
			                	 $("#nameDiv").show();
			                	 $("#sixNumDiv").hide();
				            	 $("#reg").removeAttr("disabled");
				            	 $("#reg").html("注册");
			                }else{
			                	 layer.alert("验证码错误");
			                	 $("#reg").removeAttr("disabled");
			                	 $("#reg").html("提交");
			                }
	                    },
	             error:function(XMLHttpRequest, textStatus, errorThrown){
	            	 error();
	              }
	         });
			
		};
	}
	
	if($(this).html()=="注册"){
		if(judgeUser()==false){
			 return;
		 }
		if(judgeUser()==true){
			 $.ajax({
	             type: "POST",
	             url: "/reg",
	             async:true,
	             data: {email:localStorage.lovelxfEmail,name:$("#regName").val(),password:md5($("#RegPassword").val()+"e2ATh95jd")},
	             dataType: "json",
	             beforeSend: function(XMLHttpRequest){
	            	 $("#reg").attr('disabled',"true");
	            	 $("#reg").html("发送注册信息中- - -");
	             		},
	             complete: function(XMLHttpRequest,textStatus){

	             		},
	             success:function(data,textStatus){
			                if(data.status=="true"){
			                	 $("#nameDiv").hide();
			                	 $("#reg").removeAttr("disabled");
				            	 $("#reg").html("注册成功，去登陆");
			                }else{
			                	 $(this).html()=="注册";
			                	 layer.alert("用户名已注册，请重新填写");
			                	 $("#reg").removeAttr("disabled");
			                }
	                    },
	             error:function(XMLHttpRequest, textStatus, errorThrown){
	            	 error();
	              }
	         });
			
		};
	}
	if($(this).html()=="注册成功，去登陆"){
		location.href= "/login";
	}
});

function init(){
	 $("#emailDiv").show();
	 $("#sixNumDiv").hide();
	 $("#nameDiv").hide();
	 $("#regEmail").val("");
	 $("#regSixNum").val("");
	 $("#regName").val("");
	 $("#RegPassword").val("");
	 $("#reg").removeAttr("disabled");
	 $("#reg").html("发送验证码");
}

function error(){
	 layer.alert("服务器拥挤，请重新提交");
	 init();
}
function judgeEmail(){
	if($("#regEmail").val()==""){
		layer.alert("请输入邮箱")
		return false;
	}
	if(!emailreg.test($("#regEmail").val())){
		layer.alert("邮箱格式不对")
		return false;
	}
	return true;
}

function judgeSixNum(){
	if($("#regSixNum").val()==""){
		layer.alert("请输入验证码")
		return false;
	}
	if(!sixNum.test($("#regSixNum").val())){
		layer.alert("验证码格式不对")
		return false;
	}
	return true;
}

function judgeUser(){
	if($("#regName").val()==""){
		 layer.tips('请输入昵称','#reg',{tips: [1, '#3595CC'],time: 1000});
		return false;
	}
	if($("#regName").val().indexOf("'")>=0){
		 layer.tips('不能输入非法字符,如单引号','#reg',{tips: [1, '#3595CC'],time: 1000});
		return false;
	}
	if(strlen($("#regName").val())>10){
		 layer.tips('不能超过5个中文或者10个字母','#reg',{tips: [1, '#3595CC'],time: 1000});
		 return  false;
	}
	if($("#RegPassword").val()==""){
		 layer.tips('请输入密码','#reg',{tips: [1, '#3595CC'],time: 1000});
		return false;
	}
	if($("#RegPassword").val().indexOf("'")>=0){
		 layer.tips('不能输入非法字符,如单引号','#reg',{tips: [1, '#3595CC'],time: 1000});
		return false;
	}
	if(!passwordreg.test($("#RegPassword").val())){
		 layer.tips('密码不少于6位数字或字母','#reg',{tips: [1, '#3595CC'],time: 1000});
		return false;
	}
	return true;
}

function strlen(str){
	 if (str == null) return 0;
	  if (typeof str != "string"){
	    str += "";
	  }
	  return str.replace(/[^\x00-\xff]/g,"01").length;
}
/*
function tijiao2(){
		var value=$("#value").val();
		time($("#tijiao"));
		$.post("/FindServlet",{action:"findback",value:value},function(data){
			
		});
	}

function time(o) { 
    if (wait == 0) { 
        o.prop("disabled",false);           
        o.val("免费获取验证码"); 
        wait = 60; 
    } else { 
        o.prop("disabled", true); 
        o.val(wait+"秒后可以重新发送"); 
        wait--; 
        setTimeout(function() { 
            time(o) 
        }, 
        1000) 
    } 
} */