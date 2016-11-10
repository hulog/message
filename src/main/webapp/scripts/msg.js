var returnresult;
var tr;
var time=99999;
var sendway="all";
$(function(){
    $('#msgshow').parent().addClass('loading');
	$.ajax({  
        type:"GET",   //http请求方式
        url:"../msg/find", //发送给服务器的url
//        data:obj, //发送给服务器的参数
        dataType:"json",
        success:function(data) {
        	returnresult = data.data;
        	showtable(returnresult);
        } 
    });
	  $("#selectall").click(function(){
          $('input:checkbox').not(this).prop('checked',this.checked);
      });


	$("#deletemsg").click(function(){
	/*	var cfm=confirm("是否删除所选消息？");
		if(cfm==true){ */
			deletemsg();
		
	});
	
	$("#searchmsg").click(function(){
		searchmsg();
	});
	
	$("body").keydown(function(event) {
        if (event.keyCode == "13") {//keyCode=13是回车键
            $("#searchmsg").click();
        }
    });
	
	$('#searchword').on('input',function(){
	    if($("#searchword").val()== ""){
	    	searchmsg();
	    }
	})
	
	$("#logout").click(function () {
        $.ajax
        ({ 
            type: "GET",
            url: "../login/logout", 
            dataType: "json",
            success: function (data) {
                if (data.data == "success") {
                    window.location.href="/msg/admin";
                }
                else {
                	 alert("登出失败");
                }
            }
        })
        })
	
});

function showtable(returnresult){
    if(returnresult.length <= 0) {
        var nodata = "<tr><td colspan = '6'>没有数据</td></tr>";
        $('#msgshow').parent().removeClass('loading');
        $("#msgtable").html(nodata);
    } else {
    $('#pagination-container').pagination({
        dataSource: returnresult,
        pageSize: 12,
        showGoInput: true,
        showGoButton: true,
        className: 'paginationjs-theme-blue',
        callback: function(data, pagination) {
            // template method of yourself
            var html = "";
            for(var i=0; i<data.length; i++){
                html= html +"<tr id=' "+data[i].mid+" '><td><input type='checkbox' class='msg' name=' "+data[i].mid+
                " '></td><td>"+data[i].cname+"</td><td>"+data[i].oname+
                "</td><td>"+data[i].content+"</td><td>"+data[i].sendway+
                "</td><td>"+data[i].sendtime+"</td></tr>";
            }
            $('#msgshow').parent().removeClass('loading');
            $("#msgtable").html(html);
        }
    });
    }
}

function changetime(data){
    var result = new Array();
    var now = new Date();
    var len = 0.0;
    time = data;
    for(var i = 0; i < returnresult.length; i++) {
        var starttime = new Date(returnresult[i].sendtime.replace(/-/g,'/'));
        len = (Date.parse(now) - Date.parse(starttime)) / 2592000000;
        if((String(returnresult[i].sendway) == sendway || sendway == "all") &&(len <= time)) {
            result.push(returnresult[i]);
        }
    }
    showtable(result);
};

function changetype(data){
    var result = new Array();
    var now = new Date();
    var len = 0.0;
    sendway = data;
    for(var i = 0; i < returnresult.length; i++) {
        var starttime = new Date(returnresult[i].sendtime.replace(/-/g,'/'));
        len = (Date.parse(now) - Date.parse(starttime)) / 2592000000;
        if((String(returnresult[i].sendway) == sendway || sendway == "all") &&(len <= time)){
            result.push(returnresult[i]);
        }
    }
    showtable(result);
};

function searchmsg(){
	var searchword=$("#searchword").val();
	console.log(searchword);
	if(searchword==""){
		$("tr[id!=head]").remove();
		showtable(returnresult);
	}else{
		var searchresult=new Array();
		$("tr[id!=head]").remove();
		for(var i=0; i<returnresult.length; i++){
			if(returnresult[i].cname.indexOf(searchword) >= 0||
					returnresult[i].oname.indexOf(searchword) >= 0||
					returnresult[i].content.indexOf(searchword) >= 0||
					returnresult[i].sendway.indexOf(searchword) >= 0||
					returnresult[i].sendtime.indexOf(searchword) >= 0){
				searchresult.push(returnresult[i]);
			}
		}
		showtable(searchresult);
	}
}

//点击行勾选
$("#msgtable").on("click", "tr", function () {
    var input = $(this).find("input");
    //alert($(input).prop("checked"));
    if (!$(input).prop("checked")) {
        $(input).prop("checked",true);
    }else{
         $(input).prop("checked",false);
    }
});
//多选框 防止事件冒泡
$("#msgtable").on("click", "input", function (event) {
    event.stopImmediatePropagation();
});
function deletemsg(){
	var midlist=new Array();
	$("input[class='msg']:checked").each(function(){  
	    midlist.push($(this).attr('name'));  
	});
	$.ajax({  
        type:"POST",   //http请求方式
        url:"../msg/delete", //发送给服务器的url
        traditional:true,
        data:{midlist:midlist}, //发送给服务器的参数
        dataType:"json",
        success:function(data) {
        	alert("删除成功");
        	$("#selectall").prop("checked",false);
        	$.ajax({  
                type:"GET",   //http请求方式
                url:"../msg/find", //发送给服务器的url
                dataType:"json",
                success:function(data) {
                    returnresult = data.data;
                    $("tr[id!='head']").remove();
                    showtable(returnresult);
                } ,
                error:function(){
                    alert("重新加载失败");
                }
            });
        },
        error:function(){
        	alert("删除失败");
        }
    });
}