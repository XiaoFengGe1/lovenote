/**
 * 
 */
var classArray = new Array();
var load ;
var buttonFlag = 1;
$(document).ready(function(){
	$("#classDiv").hide();
	inputOff();
	getUserMsg();
	//getClass();
});
$("#fixUser").on("click",function(){
	if($("#fixUser").html()=="修改信息"){
		inputOn();
		if($('input:radio[name=sexRadio]:checked').val()==null){
			$("#optionsRadios1").attr('checked',true);
		}
		$("#fixUser").html("提交");
	}else{
		var emailreg = /^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$/;
		var telreg = /^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/;
		var passwordreg = /^[a-zA-Z0-9]{6,50}$/;
		var tel=0;
		var password="";
		var email="";
		
		if(!emailreg.test($("#email").val())){
			layer.tips('邮箱格式不对','#fixUser',{tips: [1, '#3595CC'],time: 1000});
			return;
		}else{
			email = $("#email").val();
		}
		if($("#tel").val()!=""){
			if(!telreg.test($("#tel").val())){
				layer.tips('手机格式不对','#fixUser',{tips: [1, '#3595CC'],time: 1000});
				return;
			}else{
				tel = $("#tel").val();
			}
		}
		if($("#password").val()!=""){
			if(!passwordreg.test($("#password").val())){
				layer.tips('密码最少6位','#fixUser',{tips: [1, '#3595CC'],time: 1000});
				return;
			}else{
				password = $("#password").val();
			}
		}
		var sex = $('input:radio[name=sexRadio]:checked').val();
		var name = $("#name").val();
		
		if(buttonFlag ==0){return;}buttonFlag =0;
		
		$.ajax({
		        type: "POST",
		        url: "/setUser",
		        async:true,
		        data: {name:name,sex:sex,email:email,tel:tel,password:md5(password+"e2ATh95jd")},
		        dataType: "json",
		        beforeSend: function(XMLHttpRequest){
		       	   load = layer.load(0, {shade: false});
		        		},
		        complete: function(XMLHttpRequest,textStatus){
		       	
		        		},
		        success:function(data,textStatus){ 
		        	layer.close(load);
		       	 	if(data.status=="hasname"){
		       	 		 layer.tips('用户名已存在，请重新输入','#fixUser',{tips: [1, '#3595CC'],time: 1000});
		       	 	}else if(data.status=="password"){
				       	layer.msg('密码修改成功，正在跳转登陆页面');
				       	$("#fixUser").html("修改信息");
				       	inputOff();
				       	location.href= "/android/login";
				       	return;
		       	 	}else{
				       	layer.msg('修改成功');
				       	$("#fixUser").html("修改信息");
				       	inputOff();
		       	 	}
		       	 setButtonEnabled();
		               },
		         error:function(XMLHttpRequest, textStatus, errorThrown){
		        	 layer.close(load);
		        	 layer.msg('服务器错误，请稍后再试');
		        	 setButtonEnabled();
		         }
		    });
	}
});

$("#addClass").on("click",function(){
	if($("#addInput").val()==""){
		 layer.tips('请输入类别','#addClass',{tips: [1, '#3595CC'],time: 1000});
		 return;
	}
	if(strlen($("#addInput").val())>10){
		 layer.tips('不能超过5个中文或者10个字母','#addClass',{tips: [1, '#3595CC'],time: 1000});
		 return;
	}
	if($.inArray($("#addInput").val(),classArray)<0){
		if(buttonFlag ==0){return;}buttonFlag =0;
		$.ajax({
	        type: "POST",
	        url: "/addClass",
	        async:true,
	        data: {add:$("#addInput").val()},
	        dataType: "json",
	        beforeSend: function(XMLHttpRequest){
	       	   load = layer.load(0, {shade: false});
	        		},
	        complete: function(XMLHttpRequest,textStatus){
	       	
	        		},
	        success:function(data,textStatus){
	        			layer.close(load);
			       	 	if(data.status=="true"){
			       	 		 layer.tips('添加成功','#addClass',{tips: [1, '#3595CC'],time: 1000});
			       	 		 classArray.push($("#addInput").val());
			       	 		location.href="/android/user";
			       	 		return;
			       	 	}else{
		       	 		 layer.tips('添加失败，请重试','#addClass',{tips: [1, '#3595CC'],time: 1000});
			       	 	}
			       	 setButtonEnabled();
	               },
	         error:function(XMLHttpRequest, textStatus, errorThrown){
	        	 layer.close(load);
	        	 layer.msg('服务器错误，请稍后再试');
	        	 setButtonEnabled();
	         }
	    });
	}else{
		 layer.tips('类别已存在，请重新输入','#addClass',{tips: [1, '#3595CC'],time: 1000});
	}
	 
});

$("#quit").on("click",function(){
	window.mycontrol.loginout();
});
$("#userBtn").on("click",function(){
	inputOff();
	$("#fixUser").html("修改信息");
	getUserMsg();
	$("#userBtn").attr("class","btn btn-primary");
	$("#classBtn").attr("class","btn");
	$("#userDiv").show();
	$("#classDiv").hide();
});


$("#classBtn").on("click",function(){
	$("#classBtn").attr("class","btn btn-primary");
	$("#userBtn").attr("class","btn");
	$("#userDiv").hide();
	$("#classDiv").show();
});

$(document).on("click",".classDelete",function(){
	var id = $(this).val();
	var name = $(this).attr("name");
	if(name == "其他"){
		 layer.tips('该类别不能删除',$(this),{tips: [1, '#3595CC'],time: 1000});
		 return;
	}
	layer.confirm('删除后，该分类的笔记会成为"其他"', {
	    btn: ['确定','取消'] //按钮
	}, function(){
		 $.ajax({
		        type: "POST",
		        url: "/deleteClass",
		        async:true,
		        data: {id:id,name:name},
		        dataType: "json",
		        beforeSend: function(XMLHttpRequest){
		       	 load = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
		        		},
		        complete: function(XMLHttpRequest,textStatus){
		       	
		        		},
		        success:function(data,textStatus){
		        	 layer.close(load);
		        	 if(data.status=="true"){
		 				layer.msg('删除成功');
		 				location.href="/android/user";
		 				return;
		 			}else{
		 				layer.msg('服务器错误，请稍后再试');
		 			}
		       	 	 },
		         error:function(XMLHttpRequest, textStatus, errorThrown){
		       	   layer.close(load);
		       	   layer.msg('服务器错误，请稍后再试');
		         }
		    });
	}, function(){
	
	});
});
function getUserMsg(){
	 $.ajax({
        type: "POST",
        url: "/android/getUser",
        async:true,
        data: {},
        dataType: "json",
        beforeSend: function(XMLHttpRequest){
       	 load = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
        		},
        complete: function(XMLHttpRequest,textStatus){
       	
        		},
        success:function(data,textStatus){
        	 layer.close(load);
       	 	if(data.status=="false"){
       	 		 layer.msg('信息加载失败，请重新登录');
       	 	}else{
       	 		$("#name").val(data.data.name);
       	 		$("#email").val(data.data.email);
       	 		$("#regTime").val(timeFormat(data.data.regtime,'yyyy-MM-dd HH:mm:ss'));
       	 		if(data.data.sex==null){
       	 			
       	 		}else if(data.data.sex=="男"){
       	 			$("#optionsRadios1").attr('checked',true);
       	 		}else if(data.data.sex=="女"){
       	 			$("#optionsRadios2").attr('checked',true);
       	 		}
       	 		if(data.data.tel!="0"){
       	 			$("#tel").val(data.data.tel);
       	 		}
       	 		$("#tel").attr("disabled",true);
       	 		if(data.data.rank=="0"){
       	 			$("#rankDiv").hide();
       	 			$("#moneyDiv").hide();
       	 		}else{
       	 			$("#rank").val(data.data.rank);
       	 			$("#money").val(data.data.money);
       	 		}
       	 	}
       	    getClass();
               },
         error:function(XMLHttpRequest, textStatus, errorThrown){
       	   layer.close(load);
       	   layer.msg('服务器错误，请稍后再试');
         }
    });
}
function getClass(){
	 $.ajax({
	        type: "POST",
	        url: "/android/getClass",
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
	        	 $.each(data.data,function(j, item) {
	        		    var class1='<tr><td>'
	        		    var class2 = item["class"];
	        		    var class3 = '</td><td ><button name = "';
	        		    var class33 = '" value="';
	        		    var class4 =item["id"];
	        		    var class5 = '"class="btn btn-danger classDelete" ><span class="glyphicon glyphicon-remove"></span>删除</button></td></tr>'
	        		    classArray.push(class2);
						$("#classAddEle").prepend(class1+class2+class3+class2+class33+class4+class5);
					});
	               },
	         error:function(XMLHttpRequest, textStatus, errorThrown){
	        	 layer.close(load);
	        	 layer.msg('服务器错误，请稍后再试');
	         }
	    });
}
function inputOn(){
	$("#name").removeAttr("disabled");
	$("#email").removeAttr("disabled");
	$("#tel").removeAttr("disabled");
	$("#password").removeAttr("disabled");
	$('input:radio[name=sexRadio]').removeAttr("disabled");
}
function inputOff(){
		$("#name").attr("disabled",true);
		$("#email").attr("disabled",true);
		$("#tel").attr("disabled",true);
		$("#password").attr("disabled",true);
		$('input:radio[name=sexRadio]').attr("disabled",true);
}

function setButtonEnabled(){
	window.setTimeout(setButtonEnable,100); 
	
}
function setButtonEnable(){
	buttonFlag = 1;
}

function strlen(str){
	 if (str == null) return 0;
	  if (typeof str != "string"){
	    str += "";
	  }
	  return str.replace(/[^\x00-\xff]/g,"01").length;
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

