/**
 * 
 */
var classArray = new Array();
var netArray = new Array();
var load ;
var buttonFlag = 1;
var nettype = 1;

Date.prototype.Format = function(fmt)   
{ //author: meizz   
  var o = {   
    "M+" : this.getMonth()+1,                 //月份   
    "d+" : this.getDate(),                    //日   
    "h+" : this.getHours(),                   //小时   
    "m+" : this.getMinutes(),                 //分   
    "s+" : this.getSeconds(),                 //秒   
    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
    "S"  : this.getMilliseconds()             //毫秒   
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
}  
$(document).ready(function(){
	var username = getCookie("username");
	if(username==null||username==""){
		location.href="/login";
		return;
	}
	inputOff();
	getUserMsg();
	getClass();
	var time1 = new Date().Format("yyyy-MM-dd");
	$('#datetimepicker1').val(time1);
	$('#datetimepicker2').val(time1);
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
		if($("#name").val()==""){
			layer.msg("昵称不能为空");
			return false;
		}
		if($("#name").val().indexOf("'")>=0){
			layer.msg("不能输入非法字符,如单引号");
			return false;
		}
		var name = $("#name").val().trim();
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
		        	 if(data.status=="timeout"){
		        		 layer.tips('登录超时','#netSearch',{tips: [1, '#3595CC'],time: 1000});
		 				return;
		 			}else if(data.status=="hasname"){
		       	 		 layer.tips('用户名已存在，请重新输入','#fixUser',{tips: [1, '#3595CC'],time: 1000});
		       	 	}else if(data.status=="password"){
				       	layer.msg('密码修改成功，正在跳转登陆页面');
				       	$("#fixUser").html("修改信息");
				       	inputOff();
				       	location.href= "/login";
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
	if($("#addInput").val().indexOf("'")>=0){
		 layer.tips('不能输入非法字符,如单引号','#addClass',{tips: [1, '#3595CC'],time: 1000});
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
	        			 if(data.status=="timeout"){
	                		 layer.tips('登录超时','#netSearch',{tips: [1, '#3595CC'],time: 1000});
	         				return;
	         			}else if(data.status=="true"){
			       	 		 layer.tips('添加成功','#addClass',{tips: [1, '#3595CC'],time: 1000});
			       	 		 classArray.push($("#addInput").val());
			       	 		location.href="/user";
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

$("#netSelect").on("click",function(){
	var className = $("#netSelect").val();
	if(className == "收藏的网址"){
		$("#blackAddLine").hide();
	}else if(className == "黑名单网址"){
		$("#blackAddLine").show();
	}
});

$("#blackAdd").on("click",function(){
	var str = $("#blackInput").val();
	str = str.trim();
	if(str == "" || str.length < 3 ){
		layer.tips('请输入网址','#blackAdd',{tips: [1, '#3595CC'],time: 1000});
		return;
	}
	 $("#blackAdd").attr("disabled", true);
	 $.ajax({
	        type: "POST",
	        url: "/addBlack",
	        async:true,
	        data: {titleName:"",url:str},
	        dataType: "json",
	        beforeSend: function(XMLHttpRequest){
	        	$("#blackAdd").attr("disabled", true);
	       	  	},
	        complete: function(XMLHttpRequest,textStatus){
	        	
	        		},
	        success:function(data,textStatus){
	        	if(data.status=="false"){
	        		layer.tips('服务器异常',$("#blackAdd"),{tips: [1, '#3595CC'],time: 2000});
	        		 $("#blackAdd").attr("disabled", false);
	        	}else if(data.status=="timeout"){
	        		layer.tips('登录超时',$("#blackAdd"),{tips: [1, '#3595CC'],time: 2000});
	        		 $("#blackAdd").attr("disabled", false);
	        	}else{
	        		layer.tips('已成功拉黑',$("#blackAdd"),{tips: [1, '#3595CC'],time: 2000});
	        	}
	        	   },
	         error:function(XMLHttpRequest, textStatus, errorThrown){
	        	 $("#blackAdd").attr("disabled", false);
	        	 layer.tips('未知异常',$("#blackAdd"),{tips: [1, '#3595CC'],time: 2000});
	         }
	    });
});

$("#netSearch").on("click",function(){
	var className = $("#netSelect").val();
	var searchStr = $("#netInput").val();
	var startTime = $('#datetimepicker1').val();
	var endTime = $('#datetimepicker2').val();
	if(startTime == "" || endTime == ""){
		layer.tips('请输入时间','#netSearch',{tips: [1, '#3595CC'],time: 1000});
		return;
	}
	if(className == "收藏的网址"){
		nettype = 1;
	}else if(className == "黑名单网址"){
		nettype = 2;
	}
	$.ajax({
        type: "POST",
        url: "/getUrls",
        async:true,
        data: {type:nettype,startTime:startTime,endTime:endTime,searchStr:searchStr},
        dataType: "json",
        beforeSend: function(XMLHttpRequest){
       	 load = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
        		},
        complete: function(XMLHttpRequest,textStatus){
       	
        		},
        success:function(data,textStatus){
        	 layer.close(load);
        	 if(data.status=="timeout"){
        		 layer.tips('登录超时','#netSearch',{tips: [1, '#3595CC'],time: 1000});
 				return;
 			}else if(data.status=="timeError"){
 				 layer.tips('时间参数错误','#netSearch',{tips: [1, '#3595CC'],time: 1000});
  				return;
 			}else {
 				if(data.total == "0"){
 					 layer.tips('没有数据','#netSearch',{tips: [1, '#3595CC'],time: 1000});
 				}else{
 					var i = 1;
 					$("#netShowTable").html('');
 					 $.each(data.data,function(j, item) {
 						var id = item["id"];
 						var time = item["date"];
 						var year = time.substring(0,4);
 						var month = time.substring(4,6);
 						var day = time.substring(6,8);
 						var hour = time.substring(8,10);
 						var min = time.substring(10,12);
 						var second = time.substring(12,14);
 						time = year+"-"+month+"-"+day+" "+hour+":"+min+":"+second;
 						var url = item["url"];
 						var titel = item["title"];
 						if(titel == "" || titel.length < 3){
 							titel = url;
 						}
 						var class1='<tr><td><button class = "btn btn-danger netDelete" id="net'+id+'" value="'+id+'">删除</button></td><td><span>'+i+'、</span></td><td>';
 	        		    var class2 = time;
 	        		    var class3 = '</td><td><h3><a href = "'+url+'" target="_blank">';
 	        		    var class4 = titel;
 	        		    var class5 = '</a></h3></td></tr>';
 	        		    i = i+1;
 	        		   netArray.push(id);
 						$("#netShowTable").prepend(class1+class2+class3+class4+class5);
 					});
 					if(data.total > 25){
						 layer.tips('最多只显示50条数据','#netSearch',{tips: [1, '#3595CC'],time: 3000});
					}
 				}
 				
 			}
       	 	 },
         error:function(XMLHttpRequest, textStatus, errorThrown){
       	   layer.close(load);
       	   layer.msg('服务器错误，请稍后再试');
         }
    });
});

$("#userBtn").on("click",function(){
	inputOff();
	$("#fixUser").html("修改信息");
	getUserMsg();
	$("#userDiv").show();
	$("#classDiv").hide();
	$("#netDiv").hide();
});


$("#classBtn").on("click",function(){
	$("#userDiv").hide();
	$("#classDiv").show();
	$("#netDiv").hide();
});

$("#netBtn").on("click",function(){
	$("#netDiv").show();
	$("#userDiv").hide();
	$("#classDiv").hide();
});


$('#datetimepicker1').datetimepicker({
	lang:'ch',//中文化
	format:"Y-m-d",
	timepicker:false,
});
$('#datetimepicker2').datetimepicker({
	lang:'ch',//中文化
	format:"Y-m-d",
	timepicker:false,
});


$("#classDiv").on("click",".classDelete",function(){
	var id = $(this).val();
	var name = $(this).attr("name");
	if(name == "其他"){
		 layer.tips('该类别不能删除',$(this),{tips: [1, '#3595CC'],time: 1000});
		 return;
	}
	layer.confirm('删除后，该分类的笔记会成为"其他"', {
	    btn: ['确定','取消'] //按钮
	}, function(){
		var clsssid ="#class"+id;
		$(clsssid).attr("disabled", true);
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
		        	 if(data.status=="timeout"){
		        		 layer.tips('登录超时','#netSearch',{tips: [1, '#3595CC'],time: 1000});
		        		 $(clsssid).attr("disabled", false);
		 				return;
		 			}else if(data.status=="true"){
		 				layer.msg('删除成功');
		 				return;
		 			}else{
		 				layer.msg('服务器错误，请稍后再试');
		 				$(clsssid).attr("disabled", false);
		 			}
		       	 	 },
		         error:function(XMLHttpRequest, textStatus, errorThrown){
		       	   layer.close(load);
		       	   layer.msg('服务器错误，请稍后再试');
		       	  $(clsssid).attr("disabled", false);
		         }
		    });
	}, function(){
	
	});
});

$("#netDiv").on("click",".netDelete",function(){
	var id = $(this).val();
	layer.confirm('确定删除吗？', {
	    btn: ['确定','取消'] //按钮
	}, function(){
		var netid ="#net"+id;
		$(netid).attr("disabled", true);
		 $.ajax({
		        type: "POST",
		        url: "/deleteUrl",
		        async:true,
		        data: {type:nettype,id:id},
		        dataType: "json",
		        beforeSend: function(XMLHttpRequest){
		       	 load = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
		        		},
		        complete: function(XMLHttpRequest,textStatus){
		       	
		        		},
		        success:function(data,textStatus){
		        	 layer.close(load);
		        	 if(data.status=="timeout"){
		        		 layer.tips('登录超时','#netSearch',{tips: [1, '#3595CC'],time: 1000});
		        		 $(netid).attr("disabled", false);
		 				return;
		 			}else if(data.status=="true"){
		 				layer.msg('删除成功');
		 				return;
		 			}else{
		 				layer.msg('删除失败');
		 				 $(netid).attr("disabled", false);
		 			}
		       	 	 },
		         error:function(XMLHttpRequest, textStatus, errorThrown){
		       	   layer.close(load);
		       	   layer.msg('服务器错误，请稍后再试');
		       	 $(netid).attr("disabled", false);
		         }
		    });
	}, function(){
	
	});
});

function getUserMsg(){
	 $.ajax({
        type: "POST",
        url: "/getUser",
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
        	 if(data.status=="timeout"){
        		 layer.tips('登录超时','#netSearch',{tips: [1, '#3595CC'],time: 1000});
 				return;
 			}else if(data.status=="false"){
       	 		 layer.msg('信息加载失败，请重新登录');
       	 	}else{
       	 		$("#name").val(data.data.name);
       	 		$("#email").val(data.data.email);
       	 		$("#regTime").val(timeFormat(data.data.regtime,'yyyy-MM-dd HH:mm:ss'));
       	 		if(data.data.pic !=""){
       	 			$("#toxiangimg").attr("src",data.data.pic);
       	 		}
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
	        url: "/getClass",
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
	        		 var id = item["id"];
	        		    var class1='<tr><td>';
	        		    var class2 = item["class"];
	        		    var class3 = '</td><td ><button name = "';
	        		    var class4 = '" id = "class'+id+'" value="'+id+'" ';
	        		    var class5 = 'class="btn btn-danger classDelete" ><span class="glyphicon glyphicon-remove"></span>&nbsp删除</button></td></tr>';
	        		    classArray.push(class2);
						$("#classAddEle").prepend(class1+class2+class3+class2+class3+class4+class5);
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