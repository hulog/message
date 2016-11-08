  $(function () {
	  
    $("#login").click(function() {
            var name = $("#username").val();
            var password = $("#password").val();
            var user = {
                "name" : name,
                "password" : password,
                "url" : "/msg/login/admin"
            };
            
            postData(user, function(data) {
                 if(data.code == "00000") {
                    window.location.href="/msg/admin/setobject";
                } else {
                    alert(data.msg);
                }
            });
       });
    
    $("body").keydown(function(event) {
        if (event.keyCode == "13") {//keyCode=13是回车键
            $("#login").click();
        }
    });
  });
    