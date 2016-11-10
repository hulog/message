var returnresult;
var selectedclt = 0;
var name = false;
var passwd = false;
var repasswd = false;
var rename = "";
var checkpd = "";
$(function() {
    $.ajax({
        type : "GET", // http请求方式
        url : "../client/query", // 发送给服务器的url
        // data:obj, //发送给服务器的参数
        dataType : "json",
        success : function(data) {
            returnresult = data.data;
            showtable(returnresult);
        }
    });

    $("#addconfirm").click(function() {
        addclt();
    });

    $("#addobject").click(function() {
        $("#addname").val("");
        $("#addpasswd").val("");
        $("#addrepasswd").val("");
        $("#name-tip").html("");
        $("#passwd-tip").html("");
        $("#repasswd-tip").html("");
        name = false;
        passwd = false;
        checkButton();
    });

    $("#modconfirm").click(function() {
        /*
         * var cfm=confirm("是否确定提交？"); if(cfm==true){
         */
        modclt(selectedclt);

    });

    $("#deleteclt").click(function() {
        /*
         * var cfm=confirm("是否删除所选对象？"); if(cfm==true){
         */
        deleteclt();
    });
    
    //点击行勾选
    $("#clttable").on("click", "tr td", function () {
        if (!$(this).find("button").length){
            var input = $(this).parent().find("input");
        //alert($(input).prop("checked"));
        if (!$(input).prop("checked")) {
            $(input).prop("checked",true);
        }else{
             $(input).prop("checked",false);
        }
        }
    });
    
    //多选框防止事件冒泡
    $("#clttable").on("click", "input", function(event) {
        event.stopImmediatePropagation();
    });

    $("body").keydown(function(event) {
        if (event.keyCode == "13") {//keyCode=13是回车键
            $("#searchclt").click();
        }
    });
    
    $("#logout").click(function() {
        $.ajax({
            type : "GET",
            url : "../login/logout",
            dataType : "json",
            success : function(data) {
                if (data.data == "success") {
                    window.location.href = "/msg/admin";
                } else {
                    alert("登出失败");
                }
            }
        })
    })

    $("#selectall").click(function() {
        $('input:checkbox').not(this).prop('checked', this.checked);
    });

    $('#searchclt').click(function() {
        searchclt();
    })
});

$('#searchword').on('input',function(){
    if($("#searchword").val()== ""){
        searchclt();
    }
})

// 需要修改
function showtable(returnresult) {
    if (returnresult.length <= 0) {
        var nodata = "<tr><td colspan='7'>没有数据</td></tr>";
        $("#clttable").html(nodata);
    } else {
        $('#pagination-container')
                .pagination(
                        {
                            dataSource : returnresult,
                            pageSize : 12,
                            showGoInput : true,
                            showGoButton : true,
                            className : 'paginationjs-theme-blue',
                            callback : function(data, pagination) {
                                // template method of yourself
                                var html = "";
                                for (var i = 0; i < data.length; i++) {
                                    html = html
                                            + "<tr id='"
                                            + data[i].cid
                                            + "'><td><input type='checkbox' class='client' name='"
                                            + data[i].cid
                                            + "'></td><td>"
                                            + data[i].cname
                                            + "</td><td><button value=\""
                                            + data[i].cid
                                            + "\"data-toggle='modal' onclick='modclick(event)'"
                                            + "role='dialog' data-target='#modModal' class='btn btn-primary'>编辑</button></td></tr>";
                                }
                                $("#clttable").html(html);
                            }
                        });
    }
}

function searchclt() {
    var searchword = $("#searchword").val();
    if (searchword == "") {
        $("tr[id!=head]").remove();
        showtable(returnresult);
    } else {
        var searchresult = new Array();
        $("tr[id!=head]").remove();
        for (var i = 0; i < returnresult.length; i++) {
            if (returnresult[i].cname.indexOf(searchword) >= 0) {
                searchresult.push(returnresult[i]);
            }
        }
        showtable(searchresult);
    }
}

function addclt() {
    var name = $("#addname").val();
    var passwd = $("#addpasswd").val();

    if (nameTest(name) && passwdTest(passwd)) {
        $.ajax({
            type : "POST", // http请求方式
            url : "../client/insert", // 发送给服务器的url
            traditional : true,
            data : {
                cname : name,
                cpasswd : passwd
            }, // 发送给服务器的参数
            dataType : "json",
            success : function(data) {
                alert("新增成功");
                $("#addname").val("");
                $("#addpasswd").val("");
                $.ajax({
                    type : "GET", // http请求方式
                    url : "../client/query", // 发送给服务器的url
                    // data:obj, //发送给服务器的参数
                    dataType : "json",
                    success : function(data) {
                        returnresult = data.data;
                        $("tr[id!=head]").remove();
                        showtable(returnresult);
                    },
                    error : function() {
                        alert("重新加载失败");
                    }
                });
            },
            error : function() {
                alert("新增失败");
            }
        });

    }
}

function modclick(event) {
    $("#modname").val("");
    $("#modpasswd").val("");
    $("#modrepasswd").val("");
    $("#name-tip2").html("");
    $("#passwd-tip2").html("");
    $("#repasswd-tip2").html("");
    selectedclt = event.target.value;
    for (var i = 0; i < returnresult.length; i++) {
        if (returnresult[i].cid == selectedclt) {
            $("#modname").val(returnresult[i].cname);
            $("#modpasswd").val();
            rename = returnresult[i].cname;
            checkName2(returnresult[i].cname);
            passwd = false;
            repasswd = false;
            checkButton();
        }
    }
}

function modclt(selectedclt) {
    var cid = selectedclt;
    var name = $("#modname").val();
    var passwd = $("#modpasswd").val();
    if (nameTest(name) && passwdTest(passwd)) {
        $.ajax({
            type : "POST", // http请求方式
            url : "../client/modify", // 发送给服务器的url
            traditional : true,
            data : {
                cid : cid,
                cname : name,
                cpasswd : passwd
            }, // 发送给服务器的参数
            dataType : "json",
            success : function(data) {
                alert("编辑成功");
                $("#modname").val("");
                $("#modpasswd").val("");
                $.ajax({
                    type : "GET", // http请求方式
                    url : "../client/query", // 发送给服务器的url
                    // data:obj, //发送给服务器的参数
                    dataType : "json",
                    success : function(data) {
                        returnresult = data.data;
                        $("tr[id!=head]").remove();
                        showtable(returnresult);
                    },
                    error : function() {
                        alert("重新加载失败");
                    }
                });
            },
            error : function() {
                alert("编辑失败");
            }
        });
    }
}

function deleteclt() {
    var cltlist = [];
    $("input[class='client']:checked").each(function() {
        cltlist.push($(this).attr('name'));
    });
    $.ajax({
        type : "POST", // http请求方式
        url : "../client/delete", // 发送给服务器的url
        traditional : true,
        data : {
            cids : cltlist
        }, // 发送给服务器的参数
        dataType : "json",
        success : function(data) {
            alert("删除成功");
            $("#selectall").prop("checked", false);
            $.ajax({
                type : "GET", // http请求方式
                url : "../client/query", // 发送给服务器的url
                dataType : "json",
                success : function(data) {
                    returnresult = data.data;
                    $("tr[id!=head]").remove();
                    showtable(returnresult);
                },
                error : function() {
                    alert("重新加载失败");
                }
            });
        },
        error : function() {
            alert("删除失败");
        }
    });
}

function selTest(sel) {
    if (sel == "null") {
        alert("请选择用户类型！")
        $("input[type=radio]").focus();
        return false;
    }
    return true;
}

function nameTest(name) {
    if (name == "") {
        alert("请输入用户名！")
        $("input[id=name]").focus();
        return false;
    } else {
        return true;
    }
}

function passwdTest(passwd) {
    if (passwd == "") {
        alert("请输入密码！")
        $("input[id=passwd]").focus();
        return false;
    } else {
        return true;
    }
}

function checkName(bar) {
    name = false;
    if (bar == "") {
        $("#name-tip").html("用户名不能为空");
    } else {
        var params = {
            url : "../login/check",
            name : bar
        }
            postData(params, function(data) {
                if (data.code == "00000") {
                    $("#name-tip").html("用户名已存在");
                } else {
                    $("#name-tip").html("");
                    name = true;
                }
            });
    }
    checkButton();
}

function checkName2(bar) {
    name = false;
    if (bar == "") {
        $("#name-tip2").html("用户名不能为空");
    } else {
        var params = {
            url : "../login/check",
            name : bar
        }
        if (bar != rename) {
            postData(params, function(data) {
                if (data.code == "00000") {
                    $("#name-tip2").html("用户名已存在");
                } else {
                    $("#name-tip2").html("");
                    name = true;
                }
            });
        }
    }
    checkButton();
}

function checkPasswd(bar) {
    passwd = false;
    if (bar == "") {
        $("#passwd-tip").html("密码不能为空");
        $("#passwd-tip2").html("密码不能为空");
    } else if(bar.match(/^[0-9a-zA-Z]{9,16}$/)) {
        $("#passwd-tip").html("");
        $("#passwd-tip2").html("");
        checkpd = bar;
        passwd = true;
    } else {
        $("#passwd-tip").html("密码不符合规范");
        $("#passwd-tip2").html("密码不符合规范");
    }
    if($("#addrepasswd").val() != "") {
        checkRepasswd($("#addrepasswd").val());
    }
    checkButton();
}

function checkRepasswd(bar) {
    repasswd = false;
    if (bar == "") {
        $("#repasswd-tip").html("确认密码不能为空");
        $("#repasswd-tip2").html("确认密码不能为空");
    } else if(bar != checkpd){
        $("#repasswd-tip").html("两次密码不一致");
        $("#repasswd-tip2").html("两次密码不一致");
    } else {
        $("#repasswd-tip").html("");
        $("#repasswd-tip2").html("");
        repasswd = true;
    }
    checkButton();
}

function checkButton() {
    var stamp = document.getElementById("addconfirm");
    var stamp2 = document.getElementById("modconfirm");
    stamp.disabled = true;
    stamp2.disabled = true;
    if (name && passwd && repasswd) {
        stamp.disabled = false;
        stamp2.disabled = false;
    }

}
