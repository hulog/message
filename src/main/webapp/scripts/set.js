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
        
   	 /*$("body").keydown(function(event) {
         if (event.keyCode == "13") {// keyCode=13是回车键
        	bar.click();
         }
     });*/
        
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
                checkUrl(resp.data.infurl);
                checkWname(resp.data.infname);
                checkWpasswd(resp.data.infpassword);
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
                checkEname(resp.data.infname);
                checkEpasswd(resp.data.infpassword);
                checkTitle(resp.data.title);
                checkHost(resp.data.host);
                checkPort(resp.data.port);
            });
        };
        wxfoo();
 });  
      
 function checkUrl(name){
     url = false;
     if(name == "") {
         $("#url-tip").html("URL不能为空");
     } else{
         $("#url-tip").html("");
         url = true;
    }
     checkWchatButton();
 }

 function checkWname(name){
     wname = false;
     if(name == "") {
         $("#wname-tip").html("帐号不能为空");
     } else{
         $("#wname-tip").html("");
         wname = true;
    }
     checkWchatButton();
 }

 function checkWpasswd(name){
     wpasswd = false;
     if(name == "") {
         $("#wpasswd-tip").html("密码不能为空");
     } else{
         $("#wpasswd-tip").html("");
        wpasswd = true;
    }
  checkWchatButton();
 }

 function checkEname(name){
     ename = false;
     if(name == "") {
         $("#ename-tip").html("帐号不能为空");
     } else if(name.match(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/)){
         $("#ename-tip").html("");
         ename = true;
    }else{
         $("#ename-tip").html("帐号不符合规范");
     }
     checkEmailButton();
 }

 function checkEpasswd(name){
     epasswd = false;
     if(name == "") {
         $("#epasswd-tip").html("密码不能为空");
     } else{
         $("#epasswd-tip").html("");
        epasswd = true;
    }
     checkEmailButton();
 }

 function checkTitle(name){
     title = false;
     if(name == "") {
         $("#title-tip").html("主题不能为空");
     } else{
         $("#title-tip").html("");
        title = true;
    }
     checkEmailButton();
 }

 function checkHost(name){
     host = false;
     if(name == "") {
         $("#host-tip").html("主机地址不能为空");
     } else if(!name.match(/[\u4e00-\u9fa5]+/)){
         $("#host-tip").html("");
         host = true;
    }else{
        $("#host-tip").html("主机地址不符合规范");
    }
     checkEmailButton();
 }

 function checkPort(name){
     console.log(name);
     port = false;
     if(name == "") {
         $("#port-tip").html("接口号不能为空");
     } else if(name > (-1) && name< 65536){
         $("#port-tip").html("");
         port = true;
    }else{
         $("#port-tip").html("接口号不符合规范");
    }
     checkEmailButton();
 }
 
 
 function checkWchatButton() {
     var stamp = document.getElementById("setwchat");
     stamp.disabled = true;
     if(url && wname && wpasswd) {
         stamp.disabled = false;
     }
 }
 
 function checkEmailButton() {
     var stamp = document.getElementById("setemail");
     stamp.disabled = true;
     if(ename && epasswd && title && host && port) {
         stamp.disabled = false;
     }
 }
