var url;
var wname;
var wpasswd;
var ename;
var epasswd;
var title;
var hose;
var port;

$(function() {
        $("#setwchat").click(function() {
           var wchat = {
                "wname" : $("#wname").val(),
                "wpassword" : $("#wpassword").val(),
                "wurl" : $("#wurl").val(),
                "url" : "/msg/set/setwchat"
            };
           console.log(wchat);
            postData(wchat, function(data) {
                if (data.code == "00000") {
                    alert("设置成功");
                } else {
                    alert(data.msg);
                }
            });
        });
        $("#setemail").click(function(){
            var email = {
                    "ename" : $("#ename").val(),
                    "epassword" : $("#epassword").val(),
                    "title" : $("#title").val(),
                    "host" : $("#host").val(),
                    "port" : $("#port").val(),
                    "url" : "/msg/set/setemail"
            };
            postData(email, function(data) {
                if (data.code == "00000") {
                    alert("设置成功");
                } else {
                    alert(data.msg);
                }
            });
        });
        
        var bar;
        bar = $("#setwchat");
        console.log(bar);
        
        $("#weixin").click(function(){
            wxfoo();
        	bar = $("#setwchat");
        	console.log(bar);
        });
        
        $("#youxiang").click(function(){
            emailfoo();
        	bar = $("#setemail");
        	console.log(bar);
        });
        
        $("#duanxin").click(function(){
        	bar  = $("#setsms");
        	console.log(bar);
        });
        
   	 $("body").keydown(function(event) {
         if (event.keyCode == "13") {// keyCode=13是回车键
        	bar.click();
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
            });
        var wxfoo = function(){
            var d = {
                    "inftype":"1",
                    "url":"/msg/set/findconfig"
            };
            getData(d,function(resp){
                $("#wurl").val(resp.data.infurl);
                $("#wname").val(resp.data.infname);
                $("#wpassword").val(resp.data.infpassword);
            });
        };
        var emailfoo = function(){
            var e = {
                    "inftype":"2",
                    "url":"/msg/set/findconfig"
            };
            getData(e,function(resp){
                $("#ename").val(resp.data.infname);
                $("#epassword").val(resp.data.infpassword);
                $("#title").val(resp.data.title);
                $("#host").val(resp.data.host);
                $("#port").val(resp.data.port);
            });
        };
        wxfoo();
 });  
      
 function checkUrl(bar){
     url = false;
     if(bar == "") {
         $("#url-tip").html("URL不能为空");
     } else{
         $("#url-tip").html("");
         url = true;
    }
     checkWchatButton();
 }

 function checkWname(bar){
     wname = false;
     if(bar == "") {
         $("#wname-tip").html("帐号不能为空");
     } else{
         $("#wname-tip").html("");
         wname = true;
    }
     checkWchatButton();
 }

 function checkWpasswd(bar){
     wpasswd = false;
     if(bar == "") {
         $("#wpasswd-tip").html("密码不能为空");
     } else{
         $("#wpasswd-tip").html("");
        wpasswd = true;
    }
     checkWchatButton();
 }

 function checkEname(bar){
     ename = false;
     if(bar == "") {
         $("#ename-tip").html("帐号不能为空");
     } else{
         $("#ename-tip").html("");
         ename = true;
    }
     checkEmailButton();
 }

 function checkEpasswd(bar){
     epasswd = false;
     if(bar == "") {
         $("#epasswd-tip").html("密码不能为空");
     } else{
         $("#epasswd-tip").html("");
        epasswd = true;
    }
     checkEmailButton();
 }

 function checkTitle(bar){
     title = false;
     if(bar == "") {
         $("#title-tip").html("主题不能为空");
     } else{
         $("#title-tip").html("");
        title = true;
    }
     checkEmailButton();
 }

 function checkHost(bar){
     host = false;
     if(bar == "") {
         $("#host-tip").html("主机地址不能为空");
     } else{
         $("#host-tip").html("");
         host = true;
    }
     checkEmailButton();
 }

 function checkPort(bar){
     port = false;
     if(bar == "") {
         $("#port-tip").html("接口号不能为空");
     } else{
         $("#port-tip").html("");
         port = true;
    }
     checkEmailButton();
 }
 
 function checkWchatButton() {
     var stamp = document.getElementById("setwchat");
     if(url && wname && wpasswd) {
         stamp.disabled = false;
     }
 }
 
 function checkEmailButton() {
     var stamp = document.getElementById("setemail");
     if(ename && epasswd && title && host && port) {
         stamp.disabled = false;
     }
 }
