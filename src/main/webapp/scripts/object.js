var returnresult;
var selectedobj=0;
var tr;
var oname = false;
var branch = false;
var wchat = false;
var email = false;
var phone = false;

$(function(){
	$.ajax({  
        type:"GET",   //http请求方式
        url:"../object/find", //发送给服务器的url
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

	$("#addconfirm").click(function(){
		/*var cfm=confirm("是否确定提交？");
		if(cfm==true){*/
			addobject();
		
	});
	
	$("#modconfirm").click(function(){
		/*var cfm=confirm("是否确定提交？");
		if(cfm==true){*/
			modobject(selectedobj);
	
	});
	
	$("#deleteobject").click(function(){
		/*var cfm=confirm("是否删除所选对象？");
		if(cfm==true){*/
			deleteobject();
   });
	
	$("#searchobject").click(function(){
		searchobject();
	});
	
	 $("body").keydown(function(event) {
         if (event.keyCode == "13") {//keyCode=13是回车键
             $("#searchobject").click();
         }
     });

	//点击行勾选
	$("#objecttable").on("click", "tr td", function () {
	    console.log($(this).parent());
	    var input = $(this).parent().find("input");
	    //alert($(input).prop("checked"));
	    if (!$(input).prop("checked")) {
	        $(input).prop("checked",true);
	    }else{
	         $(input).prop("checked",false);
	    }
	});
/*	//多选框 防止事件冒泡
	$("#objecttable").on("click", "input", function (event) {
	    event.stopImmediatePropagation();
	});*/
	$('#searchword').on('input',function(){
	    if($("#searchword").val()== ""){
	    	searchobject();
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
        var nodata = "<tr><td colspan='7'>没有数据</td></tr>";
        $("#objecttable").html(nodata);
    } else {
        $('#pagination-container').pagination({
            dataSource: returnresult,
            pageSize: 10,
            showGoInput: true,
            showGoButton: true,
            className: 'paginationjs-theme-blue',
            callback: function(data, pagination) {
                // template method of yourself
                var html = "";
                for(var i=0; i<data.length; i++){
                    html = html +"<tr id="+data[i].oid+"><td><input type='checkbox' class='object' name="+data[i].oid+
                    "></td><td>"+data[i].oname+"</td><td>"+data[i].brand+
                    "</td><td>"+data[i].wechat+"</td><td>"+data[i].email+
                    "</td><td>"+data[i].message+"</td><td><button value=\""+
                    data[i].oid+"\"data-toggle='modal' onclick='modclick(event)'" +
                    "role='dialog' data-target='#modModal' class='btn btn-primary'>编辑</button></td></tr>";
                }
                $("#objecttable").html(html);
            }
        });
    }
}

function searchobject(){
	var searchword=$("#searchword").val();
	console.log(searchword);
	if(searchword==""){
		$("tr[id!=head]").remove();
		showtable(returnresult);
	}else{
		var searchresult=new Array();
		$("tr[id!=head]").remove();
		for(var i=0; i<returnresult.length; i++){
			if(returnresult[i].oname.indexOf(searchword) >= 0||
					returnresult[i].brand.indexOf(searchword) >= 0||
					returnresult[i].wechat.indexOf(searchword) >= 0||
					returnresult[i].email.indexOf(searchword) >= 0||
					returnresult[i].message.indexOf(searchword) >= 0){
				searchresult.push(returnresult[i]);
			}
		}
		showtable(searchresult);
	}
}

function addobject(){
	var oname=$("#addoname").val();
	var brand=$("#addbrand").val();
	var wechat=$("#addwechat").val();
	var email=$("#addemail").val();
	var message=$("#addmessage").val();
	$.ajax({  
        type:"POST",   //http请求方式
        url:"../object/add", //发送给服务器的url
        data:{oname:oname, brand:brand, wechat:wechat, email:email, message:message},
        dataType:"json",
        success:function(data) {
        	alert("添加对象成功");
		$("#addoname").val("");
		$("#addbrand").val("");
		$("#addwechat").val("");
		$("#addemail").val("");
		$("#addmessage").val("");
        	$.ajax({  
                type:"GET",   //http请求方式
                url:"../object/find", //发送给服务器的url
//                data:obj, //发送给服务器的参数
                dataType:"json",
                success:function(data) {
                	returnresult = data.data;
                	$("tr[id!=head]").remove();
                	showtable(returnresult);
                } 
            });
        },
        error:function(data) {
        	alert("添加对象失败");
        }
    });
}

function modclick(event){
	selectedobj=event.target.value;
	for(var i=0;i<returnresult.length;i++){
		if(returnresult[i].oid==selectedobj){
			$("#modoname").val(returnresult[i].oname);
			$("#modbrand").val(returnresult[i].brand);
			$("#modwechat").val(returnresult[i].wechat);
			$("#modemail").val(returnresult[i].email);
			$("#modmessage").val(returnresult[i].message);
		}
	}
}

function modobject(selectedobj){
	var oname=$("#modoname").val();
	var brand=$("#modbrand").val();
	var wechat=$("#modwechat").val();
	var email=$("#modemail").val();
	var message=$("#modmessage").val();
	$.ajax({  
        type:"POST",   //http请求方式
        url:"../object/mod", //发送给服务器的url
        data:{oid:selectedobj, oname:oname, brand:brand, wechat:wechat, email:email, message:message},
        dataType:"json",
        success:function(data) {
        	alert("更改对象成功");
        	$.ajax({  
                type:"GET",   //http请求方式
                url:"../object/find", //发送给服务器的url
                dataType:"json",
                success:function(data) {
                	returnresult = data.data;
                	$("tr[id!=head]").remove();
                	showtable(returnresult);
                } 
            });
        },
        error:function(data) {
        	alert("更改对象失败");
        }
    });
}

function deleteobject(){
	var oidlist=new Array();
	$("input[class='object']:checked").each(function(){  
	    oidlist.push($(this).attr('name'));  
	});
	$.ajax({  
        type:"POST",   //http请求方式
        url:"../object/delete", //发送给服务器的url
        traditional:true,
        data:{oidlist:oidlist}, //发送给服务器的参数
        dataType:"json",
        success:function(data) {
        	alert("删除成功");
        	$("#selectall").prop("checked",false);
        	$.ajax({  
                type:"GET",   //http请求方式
                url:"../object/find", //发送给服务器的url
                dataType:"json",
                success:function(data) {
                    returnresult = data.data;
                    $("tr[id!=head]").remove();
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


//检查对象名
function checkName(name){
    oname = false;
    if(name == "") {
        $("#name-tip").html("名字不能为空");
    } else {
        $("#name-tip").html("");
        oname = true;
    }
    button();
}
//检查部门名
function checkBranch(name){
    console.log(name);
    branch = false;
    if(name == "") {
        $("#branch-tip").html("部门不能为空");
        $("#branch-tip2").html("部门不能为空");
    } else {
        $("#branch-tip").html("");
        $("#branch-tip2").html("");
        branch = true;
    }
    button();
}
//检查邮箱
function checkEmail(name){
    if(name == "") {
        $("#email-tip").html("邮箱不能为空");
        $("#email-tip2").html("邮箱不能为空");
    } else if(name.match(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/)){
        $("#email-tip").html("");
        $("#email-tip2").html("");
        email = true;
    }else{
        $("#email-tip").html("邮箱不符合规范");
        $("#email-tip2").html("邮箱不符合规范");
    }
    button();
}
//检查微信
function checkWchat(name){
    wchat = false;
    if(name == "") {
        $("#wchat-tip").html("微信不能为空");
        $("#wchat-tip2").html("微信不能为空");
    } else {
        $("#wchat-tip").html("");
        $("#wchat-tip2").html("");
        wchat = true;
    }
    button();
}
//检查手机号
function checkPhone(name){
    phone = false;
    if(name == "") {
        $("#phone-tip").html("手机不能为空");
        $("#phone-tip2").html("手机不能为空");
    } else if(name.match(/0?(13|14|15|17|18)[0-9]{9}/)){
        $("#phone-tip").html("");
        $("#phone-tip2").html("");
        phone = true;
    }else{
        $("#phone-tip").html("手机号不符合规则");
        $("#phone-tip2").html("手机号不符合规则");
    }
    button();
}

function button() {
    var stamp = document.getElementById("addconfirm");
    var stamp2 = document.getElementById("modconfirm");
    stamp.disabled = true;
    stamp2.disabled = true;
    if (phone && oname && wchat && branch && email) {
        stamp.disabled = false;
        stamp2.disabled = false;
    }
}
