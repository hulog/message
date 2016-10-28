var selectedobj=0;
var tr;

$(function(){
	$.ajax({  
        type:"GET",   //http请求方式
        url:"../group/find", //发送给服务器的url
//        data:obj, //发送给服务器的参数
        dataType:"json",
        success:function(data) {
        	returnresult = data.data;
        	showtable(returnresult);
        } 
    });
	
	$("#selectall").click(function(){
		$('.group').not(this).prop('checked',this.checked);
	});
	
	$("#searchgroup").click(function(){
		searchgroup(returnresult);
	});
	
	 $("body").keydown(function(event) {
         if (event.keyCode == "13") {//keyCode=13是回车键
             $("#searchgroup").click();
         }
     });
	
	$('#searchword').on('input',function(){
	    if($("#searchword").val()== ""){
	    	searchgroup(returnresult);
	    }
	})
	
	$("#addconfirm").click(function(){
		/*var cfm=confirm("是否确定提交？");
		if(cfm==true){*/
			addgroup();
		
	});
	
	$("#modconfirm").click(function(){
		/*var cfm=confirm("是否确定提交？");
		if(cfm==true){*/
			modgroup(selectedobj);
		
	});
	
	$("#deletegroup").click(function(){
		/*var cfm=confirm("是否删除所选对象？");
		if(cfm==true){*/
			deletegroup();
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

function showtable(object){
	var obj=object.resultobjgrp;
	var grpset = new Set();
	for(var i=0; i<obj.length; i++){
		grpset.add(obj[i].gid);
	}
	var padata = new Array();
	grpset.forEach(function(item, sameItem, s){
		var gname;
		var table = new Array();
		for(var i=0; i<obj.length; i++){
			if(item==obj[i].gid){
				gname=obj[i].gname;
				break;
			}
		}
		for(var i=0; i<obj.length; i++){
			if(item==obj[i].gid){
				table.push(obj[i].oname);
			}
		}
//		var data = new Map();
//		data.put("item",item);
//		data.put("item",item);
		var data = {
		        item : item,
		        gname:gname,
		        table:table
		}
		padata.push(data);
	});
	console.log(padata);

	$('#pagination-container').pagination({
        dataSource: padata,
        pageSize: 10,
        showGoInput: true,
        showGoButton: true,
        callback: function(data, pagination) {
            // template method of yourself
            var html = "";
            for(var i=0; i<data.length; i++){
                html= html + "<tr id="+data[i].item+"><td><input type='checkbox' class=\"group\" name="+data[i].item+
                "></td><td>"+data[i].gname+"</td><td>"+data[i].table+"</td><td><button value=\""+
                data[i].item+"\"data-toggle='modal' onclick='modclick(event)' " +
                "role='dialog' data-target='#modModal' class='btn btn-primary'>编辑</button></td></tr>";
            }
            $("#grouptable").html(html);
        }
    });
	
	obj=object.resultobj;
	for(var i=0; i<obj.length; i++){
		tr="<tr><th><input type='checkbox' class=\"obj\" name="+obj[i].oid+
		"></th><th>"+obj[i].oname+"</th><th>"+obj[i].brand+"</th></tr>";
		$("#addobjlist").append(tr);
		$("#modobjlist").append(tr);
	}
}

function searchgroup(object){
	var obj=object.resultobjgrp;
	var searchword=$("#searchword").val();
	if(searchword==""){
		$("tr[id!=head]").remove();
		showtable(object);
	}else{
		var searchresult=new Array();
		var map=new Map();
		$("tr[id!=head]").remove();
		for(var i=0; i<obj.length; i++){
			if(obj[i].oname.indexOf(searchword) >= 0||
					obj[i].gname.indexOf(searchword) >= 0){
				searchresult.push(obj[i]);
			}
		}
		
		map={resultobjgrp:searchresult,resultobj:returnresult.resultobj}
		showtable(map);
	}
}

function addgroup(){
	var gname=$("#addgname").val();
	var oid=new Array();
	$("input[class='obj']:checked").each(function(){  
			oid.push($(this).attr("name"));
	});
	$.ajax({  
        type:"POST",   //http请求方式
        url:"../group/add", //发送给服务器的url
        traditional:true,
        data:{gname:gname,oid:oid}, //发送给服务器的参数
        dataType:"json",
        success:function(data) {
        	/*alert("新增成功");*/
        	$.ajax({  
                type:"GET",   //http请求方式
                url:"../group/find", //发送给服务器的url
                dataType:"json",
                success:function(data) {
                	returnresult = data.data;
                	$("tr[class!=head]").remove();
                	showtable(returnresult);
                } 
            });
        },
        error:function(){
        	alert("新增失败");
        }
    });
}

function modclick(event){
	selectedobj=event.target.value;
}

function modgroup(selectedobj){
	var gname=$("#modgname").val();
	var oid=new Array();
	$("input[class='obj']:checked").each(function(){  
			oid.push($(this).attr("name"));
	});
	$.ajax({  
        type:"POST",   //http请求方式
        url:"../group/mod", //发送给服务器的url
        traditional:true,
        data:{gid:selectedobj,gname:gname,oid:oid}, //发送给服务器的参数
        dataType:"json",
        success:function(data) {
        	/*alert("更改成功");*/
        	$.ajax({  
                type:"GET",   //http请求方式
                url:"../group/find", //发送给服务器的url
                dataType:"json",
                success:function(data) {
                	returnresult = data.data;
                	$("tr[class!=head]").remove();
                	showtable(returnresult);
                } 
            });
        },
        error:function(){
        	alert("更改失败");
        }
    });
}

function deletegroup(){
	var gidlist=new Array();
	$('input:checkbox:checked').each(function(){  
	    gidlist.push($(this).attr('name'));  
	});
	$.ajax({  
        type:"POST",   //http请求方式
        url:"../group/delete", //发送给服务器的url
        traditional:true,
        data:{gidlist:gidlist}, //发送给服务器的参数
        dataType:"json",
        success:function(data) {
        	$('input:checkbox:checked').each(function(){  
        	    $(this).parent().parent().remove();
        	});
        	/*alert("删除成功");*/
        },
        error:function(){
        	alert("删除失败");
        }
    });
}