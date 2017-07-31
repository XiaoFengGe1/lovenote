/**
 * 
 */
$(document).ready(function(){
	$("#divFind").hide();
	$("#autoLogin").attr("checked","checked");
	autoLogin();
});

var check = 0;
$("#login").on("click",function(){
	if($("#inputAccount").val().trim()==""){
		layer.alert("请输入账号");
		return false;
	}
	if($("#inputAccount").val().indexOf("'")>=0){
		layer.msg("不能输入非法字符,如单引号");
		return false;
	}
	if($("#inputPassword").val().trim()==""){
		layer.alert("请输入密码");
		return false;
	}
	if($("#inputPassword").val().indexOf("'")>=0){
		layer.msg("不能输入非法字符,如单引号");
		return false;
	}
	if($("#autoLogin").prop("checked")==true){
		check=1
	}
		 $.ajax({
             type: "POST",
             url: "/login",
             async:true,
             data: {account:$("#inputAccount").val().trim(),password:md5($("#inputPassword").val().trim()+"e2ATh95jd"),autoLogin:check},
             dataType: "json",
             beforeSend: function(XMLHttpRequest){
            	 $("#login").attr('disabled',"true");
            	 $("#reg").attr('disabled',"true");
            	 $("#inputAccount").attr('disabled',"true");
            	 $("#inputPassword").attr('disabled',"true");
            	 $("#findPassword").attr('disabled',"true");
            	 $("#autoLogin").attr('disabled',"true");
            	 $("#login").html("验证信息中- - -");
             		},
             complete: function(XMLHttpRequest,textStatus){
            	
             		},
             success:function(data,textStatus){
		                if(data.status=="true"){
		                	location.href= "/index";
		                	return;
		                }if(data.status=="noname"){
		                	 layer.tips('账号不存在','#login',{tips:[1,'#3595CC'],time:1000});
		                	 $("#login").removeAttr("disabled");
		                	 $("#reg").removeAttr("disabled");
		                	 $("#inputAccount").removeAttr("disabled");
		                	 $("#inputPassword").removeAttr("disabled");
		                	 $("#findPassword").removeAttr("disabled");
		                	 $("#autoLogin").removeAttr("disabled");
		                	 $("#login").html("登录");
		                }else{
		                	 layer.tips('密码错误','#login',{tips:[1,'#3595CC'],time:1000});
		                	 $("#login").removeAttr("disabled");
		                	 $("#reg").removeAttr("disabled");
		                	 $("#inputAccount").removeAttr("disabled");
		                	 $("#inputPassword").removeAttr("disabled");
		                	 $("#findPassword").removeAttr("disabled");
		                	 $("#autoLogin").removeAttr("disabled");
		                	 $("#login").html("登录");
		                }
                    },
             error:function(XMLHttpRequest, textStatus, errorThrown){
            	 error();
              }
         });


});

$("#btnFind").on("click",function(){
	var emailreg = /^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$/;
	if($("#findEmail").val().trim()==""){
		layer.alert("请输入邮箱")
		return false;
	}
	if(!emailreg.test($("#findEmail").val().trim())){
		layer.alert("邮箱格式不对")
		return false;
	}
	if($("#btnFind").html()=="找回密码"){
		 $.ajax({
             type: "POST",
             url: "/findPassword",
             async:true,
             data: {email:$("#findEmail").val().trim()},
             dataType: "json",
             beforeSend: function(XMLHttpRequest){
            	 $("#btnFind").attr('disabled',"true");
            	 $("#btnFind").html("验证邮箱中- - -");
             		},
             complete: function(XMLHttpRequest,textStatus){
            		
             		},
             success:function(data,textStatus){
		                if(data.status=="true"){
		                	$("#btnFind").removeAttr("disabled");
			            	$("#btnFind").html("密码发送成功，去登录");
		                }else{
		                	layer.alert("邮箱未注册");
		                	$("#btnFind").removeAttr("disabled");
			            	$("#btnFind").html("找回密码");
		                }
                    },
             error:function(XMLHttpRequest, textStatus, errorThrown){
            	 error();
              }
         });
		 $("#btnFind").html("验证通过，正在发送新密码- - -");
	}
	if($("#btnFind").html()=="密码发送成功，去登录"){
		 init();
	}

});

$("#reg").on("click",function(){
	location.href= "/reg";
	return false;
});


$("#findPassword").on("click",function(){
	$("#divLogin").hide();
	$("#divFind").show();
});
var load;
function autoLogin(){
	var username = getCookie("username");
	if(username!=null&&username!=""){
		$("#inputAccount").val(decodeURI(username).replace(/%40/, "@"));
	}else{
		return;
	}
	var password = getCookie("password");
	/*if(password!=null&&password!=""){
		$("#inputPassword").val(password);
	}*/
	if(username!="" && password!="") {
		$("#autoLogin").attr("checked","checked");
		 $.ajax({
             type: "POST",
             url: "/login",
             async:true,
             data: {account:$("#inputAccount").val().trim(),password:password,autoLogin:"1"},
             dataType: "json",
             beforeSend: function(XMLHttpRequest){
            	  load = layer.load(0, {shade: false});
            	  $("#login").html("自动登录中- - ");
            	  $("#login").attr('disabled',"true");
             	 $("#reg").attr('disabled',"true");
             	 $("#inputAccount").attr('disabled',"true");
             	 $("#inputPassword").attr('disabled',"true");
             	 $("#findPassword").attr('disabled',"true");
             	 $("#autoLogin").attr('disabled',"true");
             		},
             complete: function(XMLHttpRequest,textStatus){
            	
             		},
             success:function(data,textStatus){
            	 layer.close(load);
		                if(data.status=="true"){
		                	location.href= "/index";
		                	return;
		                }else{
		                	$("#login").html("登录");
		                	$("#inputAccount").removeAttr("disabled");
		                	$("#inputPassword").removeAttr("disabled");
		                	 $("#reg").removeAttr("disabled");
		                	 $("#login").removeAttr("disabled");
		                 	 $("#findPassword").removeAttr("disabled");
		                 	 $("#autoLogin").removeAttr("disabled");
		                	layer.alert("自动登录异常");
		                }
                    },
             error:function(XMLHttpRequest, textStatus, errorThrown){
            	 error();
              }
         });
	}
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

function init(){
	$("#inputAccount").val("");
	$("#RegPassword").val("");
	$("#findEmail").val("");
	$("#divFind").hide();
	$("#divLogin").show();
}
function error(){
	 layer.alert("服务器拥挤，请重新提交");
	 location.href= "/login";
}