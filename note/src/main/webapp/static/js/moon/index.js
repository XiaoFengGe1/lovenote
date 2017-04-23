/**
 * 
 */
var searchToolFlag = 0;
var keyword;
var page = 1;
var noteNum = 0;
var buttonFlag = 1;
	
$(document).ready(function(){
	init();
	getMainBlock();
	if(sessionStorage.isLogin == 1){
		getToolMsg();
		$("#writeBtn").show();
	}else{
		 layer.tips('爱笔记是一个致力于创建和共享自己的技术笔记的技术网站。登录后能体验更多个性功能。','#searchBtn',{tips: [3, '#3595CC'],time: 5500});
		$("#selectClass").hide();
		$("#selectUser").hide();
	}
});


$("#writeBtn").on("click",function(){
	if(buttonFlag ==0){return;}buttonFlag =0;
	sessionStorage.writeNoteId = 0;
	setButtonEnabled();
	window.open("/write");
});

$("#searchToolBtn").on("click",function(){
	if(searchToolFlag ==0){
		$("#searchTool").show();
		searchToolFlag = 1;
		$("#datetimepicker1").val(timeFormat(1457525537000,'yyyy-MM-dd'));
		$("#datetimepicker2").val(timeFormat( new Date().getTime(),'yyyy-MM-dd'));
	}else{
		$("#searchTool").hide();
		searchToolFlag = 0;
	}
	
});
$("#selectUser").change(function(){
	if($("#selectUser").val()=="自定义"){
		$(this).hide();
		$("#searchToolUser").show();
	}
});
$("#selectTime").change(function(){
	var newDate = new Date()
	var mydate1 = 0;
	var mydate2 = newDate.getTime();
	if($("#selectTime").val()=="自定义"){
		$(this).hide();
		$("#timePickerDiv").show();
	}else if($("#selectTime").val()=="一周内"){
		mydate1 = mydate2-604800000;
		$("#datetimepicker1").val(timeFormat(mydate1,'yyyy-MM-dd'));
		$("#datetimepicker2").val(timeFormat(mydate2,'yyyy-MM-dd'));
	}else if($("#selectTime").val()=="一月内"){
		mydate1 = mydate2-2592000000;
		$("#datetimepicker1").val(timeFormat(mydate1,'yyyy-MM-dd'));
		$("#datetimepicker2").val(timeFormat(mydate2,'yyyy-MM-dd'));
	}else if($("#selectTime").val()=="半年内"){
		mydate1 = mydate2-110073600000;
		$("#datetimepicker1").val(timeFormat(mydate1,'yyyy-MM-dd'));
		$("#datetimepicker2").val(timeFormat(mydate2,'yyyy-MM-dd'));
	}else if($("#selectTime").val()=="时间"){
		$("#datetimepicker1").val(timeFormat(1457525537000,'yyyy-MM-dd'));
		$("#datetimepicker2").val(timeFormat(mydate2,'yyyy-MM-dd'));
	}
});
$("#btnBack").on("click",function(){
	$("#selectUser").show();
	$("#selectUser").val("仅自己");
	$("#searchToolUser").hide();
	$("#inputUser").val("");
	
});
$("#btnBackTime").on("click",function(){
	$("#selectTime").show();
	$("#selectTime").val("时间");
	$("#timePickerDiv").hide();
	$("#datetimepicker1").val("");
	$("#datetimepicker2").val("");
});

$("#searchBtn").on("click",function(){
	if(buttonFlag ==0){return;}buttonFlag =0;
	page=1;
	searchMain();
});

$("#pageBack").on("click",function(){
	if(buttonFlag ==0){return;}buttonFlag =0;
	page = page-1;
	searchMain();
});
$("#pageForward").on("click",function(){
	if(buttonFlag ==0){return;}buttonFlag =0;
	page = page+1;
    searchMain();
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


$(this).keydown(function (e){
	if(e.which == "13"){
		var focusActId = document.activeElement.id;
		if(focusActId == 'inputSearch'){
			if(buttonFlag ==0){return;}buttonFlag =0;
			page=1;
			searchMain();
		}
	}
});
	
$(document).on("click",".addClick",function(){
	if(buttonFlag ==0){return;}buttonFlag =0;
	var id = $(this).attr("value");
	 $.ajax({
	        type: "POST",
	        url: "/addClick",
	        async:true,
	        data: {noteId:id},
	        dataType:"json",
	        success:function(data,textStatus){
	        	setButtonEnabled();
	               },
	         error:function(XMLHttpRequest, textStatus, errorThrown){
	        	
	         }
	    });
	window.open("/note?id="+id);
});

function searchMain(){
	keyword = $("#inputSearch").val();
	keyword = $.trim(keyword);
	keyword = keyword.replace(/[']/g,"");
	if(keyword==""){
		getMainBlock();
	}else{
		getMain();
	}
}


function init(){
	$("#searchTool").hide();
	$("#searchToolUser").hide();
}

function getToolMsg(){
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
        	 if(data.status=="timeout"){
	        		layer.msg('页面过期，正在刷新页面');
	        		document.execCommand('Refresh');
	        	}else{
	        		 $.each(data.data,function(j, item) {
	        			 var class2 = item["class"];
	        			 $("#selectClass").append("<option value='"+class2+"'>"+class2+"</option>");
	        		 });
	        	}
        	
               },
         error:function(XMLHttpRequest, textStatus, errorThrown){
        	 layer.close(load);
        	 layer.tips('服务器错误，请稍后再试','#searchBtn',{tips: [2, '#3595CC'],time: 1000});
        	 setButtonEnabled();
         }
    });
}


function getMain(){
	if(searchToolFlag ==0){
		 $("#noteBody").html("");
		 $.ajax({
		        type: "POST",
		        url: "/getMain",
		        async:true,
		        data: {keyWord:keyword,page:page},
		        dataType: "json",
		        beforeSend: function(XMLHttpRequest){
		       	   load = layer.load(0, {shade: false});
		        		},
		        complete: function(XMLHttpRequest,textStatus){
		        	
		        		},
		        success:function(data,textStatus){
		        	layer.close(load);
		        	 setButtonEnabled();
		        	if(data.status=="false"){
		        		 layer.tips('页面过期，正在刷新页面','#searchBtn',{tips: [2, '#3595CC'],time: 1000});
		        		location.href="/index"
		        		 return;
		        	}
		        	if(data.data["total"]==0){
		        		 layer.tips('没有匹配数据','#searchBtn',{tips: [2, '#3595CC'],time: 1000});
		        		 $("#resultNum").html(0);
		        		 $("#noteBody").prepend("<h1>没有查询到数据，赶紧写笔记丰富数据库吧。</h1>");
		        		 noteNum = 0;
		        		 return;
		        	}
		        	 $("#resultNum").html(data.data["total"]);
		        	 $.each(data.data["rows"],function(j, item) {
		        		    var note1='<div class="myblog lxfHeight"><a class="myFontTitle  btn addClick" style="display:inline;" value="';
		        		    var note2='"><strong>'+item["title"]+'</strong></a>';
		        		    var note3='<p style="display:inline;" class="blog-post-meta myFontFooter myblogFooter">'
		        		    var note4=timeFormat(item["createtime"],'yyyy-MM-dd:HH');
		        		    var note5=' | <span class="glyphicon glyphicon-user">'+item["uname"];
		        		    var note6=' | <span class="glyphicon glyphicon-eye-open">'+item["click"];
		        		    var note7=' | <span class="glyphicon glyphicon-heart">'+item["likenum"];
		        		    var note9=item["id"];
		        		    var	note10='</p></div>';
		        		    var note11='<div class="myFontContent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+item["part"];
		        		    var note12='</div>';
							$("#noteBody").prepend(note1+note9+note2+note11+note12+note3+note4+note5+note6+note7+note10);
						});
		        		 noteNum = data.data["total"];
		        		 pageInit();
		        	 
		               },
		         error:function(XMLHttpRequest, textStatus, errorThrown){
		        	 layer.close(load);
		        	 layer.tips('服务器错误，请稍后再试','#searchBtn',{tips: [2, '#3595CC'],time: 1000});
		        		setButtonEnabled();
		         }
		    });
	}else{
	
		var selectUser = $("#selectUser").val();
		if(selectUser=="自定义"){
			selectUser =  $("#inputUser").val();
			if(selectUser == ""){
				 layer.tips('请输入用户名','#searchBtn',{tips: [2, '#3595CC'],time: 1000});
			}
		}
		 $("#noteBody").html("");
		 $.ajax({
		        type: "POST",
		        url: "/getMainTool",
		        async:true,
		        data: {keyWord:keyword,page:page,classify:$("#selectClass").val(),title:$("#selectTitle").val(),user:selectUser,fromTime:$("#datetimepicker1").val(),toTime:$("#datetimepicker2").val()},
		        dataType: "json",
		        beforeSend: function(XMLHttpRequest){
		       	   load = layer.load(0, {shade: false});
		        		},
		        complete: function(XMLHttpRequest,textStatus){
		        	
		        		},
		        success:function(data,textStatus){
		        	layer.close(load);
		        	 setButtonEnabled();
		        	if(data.status=="false"){
		        		 layer.tips('页面过期，正在刷新页面','#searchBtn',{tips: [2, '#3595CC'],time: 1000});
		        		location.href="/index"
		        		 return;
		        	}
		        	if(data.data["total"]==0){
		        		 layer.tips('没有匹配数据','#searchBtn',{tips: [2, '#3595CC'],time: 1000});
		        		 $("#resultNum").html(0);
		        		 $("#noteBody").prepend("<h1>没有查询到数据，赶紧写笔记丰富数据库吧。</h1>");
		        		 noteNum = 0;
		        		
		        		 return;
		        	}
		        	 $("#resultNum").html(data.data["total"]);
		        	 noteNum = data.data["total"];
	        		 pageInit();
		        	 $.each(data.data["rows"],function(j, item) {
		        		 var note1='<div class="myblog lxfHeight"><a class="myFontTitle btn addClick" style="display:inline;" value="';
		        		    var note2='"><strong>'+item["title"]+'</strong></a>';
		        		    var note3='<p style="display:inline;" class="blog-post-meta myFontFooter myblogFooter">'
		        		    var note4=timeFormat(item["createtime"],'yyyy-MM-dd:HH');
		        		    var note5=' | <span class="glyphicon glyphicon-user">'+item["uname"];
		        		    var note6=' | <span class="glyphicon glyphicon-eye-open">'+item["click"];
		        		    var note7=' | <span class="glyphicon glyphicon-heart">'+item["likenum"];
		        		    var note9=item["id"];
		        		    var	note10='</p></div>';
		        		    var note11='<div class="myFontContent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+item["part"];
		        		    var note12='</div>';
							$("#noteBody").prepend(note1+note9+note2+note11+note12+note3+note4+note5+note6+note7+note10);
						});
		               },
		         error:function(XMLHttpRequest, textStatus, errorThrown){
		        	 layer.close(load);
		        	 layer.tips('服务器错误，请稍后再试','#searchBtn',{tips: [2, '#3595CC'],time: 1000});
		        		setButtonEnabled();
		         }
		    });
	}
}

function getMainBlock(){
	if(searchToolFlag ==0){
		 $("#noteBody").html("");
		 $.ajax({
		        type: "POST",
		        url: "/getMainBlock",
		        async:true,
		        data: {page:page},
		        dataType: "json",
		        beforeSend: function(XMLHttpRequest){
		       	   load = layer.load(0, {shade: false});
		        		},
		        complete: function(XMLHttpRequest,textStatus){
		        	
		        		},
		        success:function(data,textStatus){
		        	layer.close(load);
		        	 setButtonEnabled();
		        	if(data.status=="false"){
		        		 layer.tips('页面过期，正在刷新页面','#searchBtn',{tips: [2, '#3595CC'],time: 1000});
		        		location.href="/index"
		        		 return;
		        	}
		        	if(data.data["total"]==0){
		        		 layer.tips('没有匹配数据','#searchBtn',{tips: [2, '#3595CC'],time: 1000});
		        		 $("#resultNum").html(0);
		        		 $("#noteBody").prepend("<h1>没有查询到数据，赶紧写笔记丰富数据库吧。</h1>");
		        		 noteNum = 0;
		        		 return;
		        	}
		        	 $("#resultNum").html(data.data["total"]);
	        		 noteNum = data.data["total"];
	        		 pageInit();
		        	 $.each(data.data["rows"],function(j, item) {
		        		 var note1='<div class="myblog lxfHeight"><a class="myFontTitle btn addClick" style="display:inline;" value="';
		        		    var note2='"><strong>'+item["title"]+'</strong></a>';
		        		    var note3='<p style="display:inline;" class="blog-post-meta myFontFooter myblogFooter">'
		        		    var note4=timeFormat(item["createtime"],'yyyy-MM-dd:HH');
		        		    var note5=' | <span class="glyphicon glyphicon-user">'+item["uname"];
		        		    var note6=' | <span class="glyphicon glyphicon-eye-open">'+item["click"];
		        		    var note7=' | <span class="glyphicon glyphicon-heart">'+item["likenum"];
		        		    var note9=item["id"];
		        		    var	note10='</p></div>';
		        		    var note11='<div class="myFontContent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+item["part"];
		        		    var note12='</div>';
							$("#noteBody").prepend(note1+note9+note2+note11+note12+note3+note4+note5+note6+note7+note10);
						});
		               },
		         error:function(XMLHttpRequest, textStatus, errorThrown){
		        	 layer.close(load);
		        	 layer.tips('服务器错误，请稍后再试','#searchBtn',{tips: [2, '#3595CC'],time: 1000});
		        		setButtonEnabled();
		         }
		    });
	}else{
	
		var selectUser = $("#selectUser").val();
		if(selectUser=="自定义"){
			selectUser =  $("#inputUser").val();
			if(selectUser == ""){
				 layer.tips('请输入用户名','#searchBtn',{tips: [2, '#3595CC'],time: 1000});
			}
		}
		 $("#noteBody").html("");
		 $.ajax({
		        type: "POST",
		        url: "/getMainToolBlock",
		        async:true,
		        data: {page:page,classify:$("#selectClass").val(),title:$("#selectTitle").val(),user:selectUser,fromTime:$("#datetimepicker1").val(),toTime:$("#datetimepicker2").val()},
		        dataType: "json",
		        beforeSend: function(XMLHttpRequest){
		       	   load = layer.load(0, {shade: false});
		        		},
		        complete: function(XMLHttpRequest,textStatus){
		        	
		        		},
		        success:function(data,textStatus){
		        	layer.close(load);
		        	 setButtonEnabled();
		        	if(data.status=="false"){
		        		 layer.tips('页面过期，正在刷新页面','#searchBtn',{tips: [2, '#3595CC'],time: 1000});
		        		location.href="/index"
		        		 return;
		        	}
		        	if(data.data["total"]==0){
		        		 layer.tips('没有匹配数据','#searchBtn',{tips: [2, '#3595CC'],time: 1000});
		        		 $("#resultNum").html(0);
		        		 $("#noteBody").prepend("<h1>没有查询到数据，赶紧写笔记丰富数据库吧。</h1>");
		        		 noteNum = 0;
		        		
		        		 return;
		        	}
		        	 $("#resultNum").html(data.data["total"]);
	        		 noteNum = data.data["total"];
		        	  pageInit();
		        	 $.each(data.data["rows"],function(j, item) {
		        		 var note1='<div class="myblog lxfHeight"><a class="myFontTitle btn addClick" style="display:inline;" value="';
		        		    var note2='"><strong>'+item["title"]+'</strong></a>';
		        		    var note3='<p style="display:inline;" class="blog-post-meta myFontFooter myblogFooter">'
		        		    var note4=timeFormat(item["createtime"],'yyyy-MM-dd:HH');
		        		    var note5=' | <span class="glyphicon glyphicon-user">'+item["uname"];
		        		    var note6=' | <span class="glyphicon glyphicon-eye-open">'+item["click"];
		        		    var note7=' | <span class="glyphicon glyphicon-heart">'+item["likenum"];
		        		    var note9=item["id"];
		        		    var	note10='</p></div>';
		        		    var note11='<div class="myFontContent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+item["part"];
		        		    var note12='</div>';
							$("#noteBody").prepend(note1+note9+note2+note11+note12+note3+note4+note5+note6+note7+note10);
						});
		               },
		         error:function(XMLHttpRequest, textStatus, errorThrown){
		        	 layer.close(load);
		        	 layer.tips('服务器错误，请稍后再试','#searchBtn',{tips: [2, '#3595CC'],time: 1000});
		        		setButtonEnabled();
		         }
		    });
	}
}

function pageInit(){
	 if(page<2){
		 $("#pageBack").attr("disabled",true);
	 }else{
		 $("#pageBack").removeAttr("disabled");
	 }
	 if(noteNum > page*10){
		 $("#pageForward").removeAttr("disabled");
	 }else{
		 $("#pageForward").attr("disabled",true);
	 }
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


function setButtonEnabled(){
	window.setTimeout(setButtonEnable,100); 
	
}
function setButtonEnable(){
	buttonFlag = 1;
}