 $(function() {
        $("#setwchat").click(function() {
           var wchat = {
                "wname" : $("#wname").val(),
                "wpassword" : $("#wpassword").val(),
                "wurl" : $("#wurl").val(),
                "url" : "/msg/set/setwchat"
            };
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
        	bar = $("#setwchat");
        	console.log(bar);
        });
        
        $("#youxiang").click(function(){
        	bar = $("#setemail");
        	console.log(bar);
        });
        
        $("#duanxin").click(function(){
        	bar  = $("#setsms");
        	console.log(bar);
        });
        
   	 $("body").keydown(function(event) {
         if (event.keyCode == "13") {//keyCode=13是回车键
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
            })
 });  
      