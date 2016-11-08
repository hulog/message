var name;
var passwd;
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
	
	if (selTest(selected)&&nameTest(name)&&passwdTest(passwd)){
		if(selected=="client"){
			$.ajax({  
		        type:"POST",   //http请求方式
		        url:"../client/insert", //发送给服务器的url
		        traditional:true,
		        data:{name:name,passwd:passwd}, //发送给服务器的参数
		        dataType:"json",
		        success:function(data) {
		        	alert("新增成功");
				$("input[name='choose']").prop("checked",false);
		        	$("#name").val("");
		        	$("#passwd").val("");
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
				$("input[name='choose']").prop("checked",false);
		        	$("#name").val("");
		        	$("#passwd").val("");
		        },
		        error:function(){
		        	alert("新增失败");
		        }
		    });
		}
	}
}

function selTest(sel){
    if(sel =="null"){
    	alert("请选择用户类型！")
    $("input[type=radio]").focus();
    return false;
    }
    return true;
    }

function nameTest(name){
    if(name == ""){
    	alert("请输入用户名！")
    $("input[id=name]").focus();
    return false;
    }
    return true;
    }

function passwdTest(passwd){
    if(passwd == ""){
    	alert("请输入密码！")
    $("input[id=passwd]").focus();
    return false;
    }
    return true;
    }


function checkName(bar){
    name = false;
    if(bar == "") {
        $("#name-tip").html("用户名不能为空");
    } else{
        $("#name-tip").html("");
        name = true;
   }
    checkButton();
}

function checkPasswd(bar){
    passwd = false;
    if(bar == "") {
        $("#passwd-tip").html("密码不能为空");
    } else{
        $("#passwd-tip").html("");
        passwd = true;
   }
    checkButton();
}

function checkButton() {
    var stamp = document.getElementById("addconfirm");
    if(name && passwd) {
        stamp.disabled = false;
    }
}
