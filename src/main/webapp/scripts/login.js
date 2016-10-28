        $(function () {
            $("#btnLogin").click(function () { 
                //获取用户名称
                var cname = $("#cname").val();
                //获取输入密码
                var cpwd = $("#cpwd").val();
                //开始发送数据
                if(checkInput()) {
                $.ajax
                ({
                    type: "POST",
                    url: "/msg/login/login", 
                    dataType: "json",
                    data: { cname: cname, cpassword: cpwd },
                    success: function (data) { 
                        console.log(data);
                        if (data.data == "success") {//注意是True,不是true
//                            $(".clsShow").html("操作提示，登录成功！" + strValue);
                            window.location.href="/msg/user/send";
                        }
                        else {
//                            $("#divError").show().html("用户名或密码错误！" + strValue);
                            alert("用户名或密码错误！");
//                            window.location.href="login.html";
                        }
                    }
                })
                }else{
                    return false;
                }
            })
             $("body").keydown(function(event) {
                    if (event.keyCode == "13") {//keyCode=13是回车键
                        $("#btnLogin").click();
                    }
                });
            function checkInput(){
                //判断用户名
                if($("input[id=cname]").val() == null || $("input[id=cname]").val() == ""){
                    alert("用户名不能为空");
                    $("input[id=cname]").focus();
                    return false;
                }
                //判断密码
                if($("input[id=cpwd]").val() == null || $("input[id=cpwd]").val() == ""){
                    alert("密码不能为空");
                    $("input[id=cpwd]").focus();
                    return false;
                }
                return true;
            }
        })