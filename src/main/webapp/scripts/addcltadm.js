$(function(){
	$("#addconfirm").click(function(){
		add();
	});
	
	 $("body").keydown(function(event) {
         if (event.keyCode == "13") {//keyCode=13是回车键
             $("#addconfirm").click();
         }
     });
	 
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

function add(){
	var name=$("#name").val();
	var passwd=$("#passwd").val();
	var selected="null";
	$(":radio:checked").each(function(){
		selected=$(this).attr("value");
	});
	if(selected=="null"){
		alert("选择新增客户或管理员")
	}else{
		if(selected=="client"){
			$.ajax({  
		        type:"POST",   //http请求方式
		        url:"../client/insert", //发送给服务器的url
		        traditional:true,
		        data:{name:name,passwd:passwd}, //发送给服务器的参数
		        dataType:"json",
		        success:function(data) {
		        	alert("新增成功");
		        },
		        error:function(){
		        	alert("新增失败");
		        }
		    });
		}else{
			$.ajax({  
		        type:"POST",   //http请求方式
		        url:"../admin/insert", //发送给服务器的url
		        traditional:true,
		        data:{name:name,passwd:passwd}, //发送给服务器的参数
		        dataType:"json",
		        success:function(data) {
		        	alert("新增成功");
		        },
		        error:function(){
		        	alert("新增失败");
		        }
		    });
		}
	}
}