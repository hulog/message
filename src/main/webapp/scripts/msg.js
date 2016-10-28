var returnresult;
var tr;

$(function(){
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
    $('#pagination-container').pagination({
        dataSource: returnresult,
        pageSize: 10,
        showGoInput: true,
        showGoButton: true,
        callback: function(data, pagination) {
            // template method of yourself
            var html = "";
            for(var i=0; i<data.length; i++){
                html= html +"<tr id=' "+data[i].mid+" '><td><input type='checkbox' name=' "+data[i].mid+
                " '></td><td>"+data[i].cname+"</td><td>"+data[i].oname+
                "</td><td>"+data[i].content+"</td><td>"+data[i].sendway+
                "</td><td>"+data[i].sendtime+"</td></tr>";
            }
            $("#msgtable").html(html);
        }
    });
}

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

function deletemsg(){
	var midlist=new Array();
	$('input:checkbox:checked').each(function(){  
	    midlist.push($(this).attr('name'));  
	});
	$.ajax({  
        type:"POST",   //http请求方式
        url:"../msg/delete", //发送给服务器的url
        traditional:true,
        data:{midlist:midlist}, //发送给服务器的参数
        dataType:"json",
        success:function(data) {
        	$('input:checkbox:checked').each(function(){  
        	    $(this).parent().parent().remove();
        	});
        	/*alert("删除成功");*/
        },
        error:function(){
        	alert("删除失败");
        }
    });
}