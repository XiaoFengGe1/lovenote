
$(document).ready(function(){
	searchMain();
});

var noteNum = 0;
var page = 1;
$("#pageBack").on("click",function(){
	page = page-1;
	searchMain();
});
$("#pageForward").on("click",function(){
	page = page+1;
    searchMain();
});

function searchMain(){
	 $.ajax({
	        type: "POST",
	        url: "/android/getmoney",
	        async:true,
	        data: {id:page},
	        dataType: "json",
	        success:function(data,textStatus){
	        	if(data.data["total"]==0){
	        		 $("#noteBody").prepend("<h1>没有账本数据，赶紧写记账吧。</h1>");
	        		 return;
	        	}
	        	 $.each(data.data["rows"],function(j, item) {
	        		    var note1='<div class="myblog">';
	        		    var note2='<p">'
	        		    var note3='';
	        		    if(item["inout2"] == 1){
	        		    	note3 = '收入';
	        		   }else{
	        			   note3 = '支出';
	        		   }
	        		    var note4=' |类别:'+item["type"];
	        		    var note5=' |金额:'+item["money"];
	        		    
	        		    var note6='</p><p >时间:'+timeFormat(item["createtime"],'yyyy-MM-dd:HH-mm-ss');
	        		    var note7=' |备注:'+item["instruction"];
	        		    var	note8='</p></div>';
						$("#noteBody").prepend(note1+note2+note3+note4+note5+note6+note7+note8);
					});
	        		 noteNum = data.data["total"];
	        		 pageInit();
	        	 
	               },
	         error:function(XMLHttpRequest, textStatus, errorThrown){
	         }
	    });
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
