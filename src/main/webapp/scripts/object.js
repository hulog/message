var returnresult = [];
var selectedobj=0;
var tr;
var padata = new Array();

$(function(){
	$.ajax({  
        type:"GET",   //http请求方式
        url:"../object/find", //发送给服务器的url
//        data:obj, //发送给服务器的参数
        dataType:"json",
        success:function(data) {
        	returnresult = data.data;
        	for(var i = 0; i < returnresult.length; i++) {
        	    padata.push(returnresult[i]);
        	}
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
    $('#pagination-container').pagination({
        dataSource: returnresult,
        pageSize: 10,
        showGoInput: true,
        showGoButton: true,
        callback: function(data, pagination) {
            // template method of yourself
            var html = "";
            for(var i=0; i<data.length; i++){
                html = html +"<tr id="+data[i].oid+"><td><input type='checkbox' name="+data[i].oid+
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
	$('input:checkbox:checked').each(function(){  
	    oidlist.push($(this).attr('name'));  
	});
	$.ajax({  
        type:"POST",   //http请求方式
        url:"../object/delete", //发送给服务器的url
        traditional:true,
        data:{oidlist:oidlist}, //发送给服务器的参数
        dataType:"json",
        success:function(data) {
        	$('input:checkbox:checked').each(function(){  
        	    $(this).parent().parent().remove();
        	});
        	alert("删除成功");
        },
        error:function(){
        	alert("删除失败");
        }
    });
}