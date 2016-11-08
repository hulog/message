$(function () { 
  
	 $("#selectall").click(function(){
	        $(".select").not(this).prop('checked',this.checked);
	    }); 
	 
	 var sendto2 = [];
	 var showin;
	 
	 function selectto(){
		    var cont;
		    d = document.getElementById("list");//通过id获取div节点对象
		    if($("#sel").val()==0 ) {
		    	 document.getElementById("showto").innerHTML= "";
		    	 $("#sendto").attr("disabled",true);
		    } else if($("#sel").val()==1) {
		    	document.getElementById("showto").innerHTML= "";
		    	 $("#sendto").attr("disabled",false);
	        	document.getElementById("one").innerHTML= "对象名称";
	        	document.getElementById("two").innerHTML= "所属部门";
	            $.ajax
	            ({ 
	                type: "GET",
	                url: "../object/query", 
	                dataType: "json",
	                success: function (data) { 
	                	d.innerHTML = "";
	                    var contdata = data.data;
	                    cont = contdata;
	                    for(var i = 0; i < contdata.length; i++) {
	                        d.innerHTML = d.innerHTML + "<tr id='" +  contdata[i].oid + " '><td><input class='select' name='sendid' type='checkbox' value=' "
	                        + contdata[i].oid+" ' ></td><td>" +  contdata[i].oname+"</td><td>" + contdata[i].brand + "</td></tr>";
	                    }
	                }
	            })
	           
	              function showto() {
	            	 var sendto =  new Set();
	                 $("input[name='sendid']:checked").each(function () {
	                	 var user =  $(this).val();
	                	 for(var i = 0; i < cont.length; i++) {
	                        if(cont[i].oid ==  user ){
	                        	 sendto.add( cont[i].oname);
	                        }  	
	                	 }
	                 });
	                sendto2 = [];
	                sendto.forEach(function(item, sameItem, s){
	                	sendto2.push(item);
	                })
	            }
	                 showin = showto;
	                 
	        } else if($("#sel").val()==2) {
	        	document.getElementById("showto").innerHTML= "";
	        	 $("#sendto").attr("disabled",false);
	        	document.getElementById("one").innerHTML= "群组名称";
	        	document.getElementById("two").innerHTML= "成员对象";
	            $.ajax
	            ({ 
	                type: "GET",
	                url: "../group/query", 
	                dataType: "json",
	                success: function (data) { 
	                	d.innerHTML = "";
	                	 var contdata = data.data;
	                	 cont = contdata;
	                	var grpset = new Set();
	                	for(var i=0; i<contdata.length; i++){
	                		grpset.add(contdata[i].gid);
	                	}
	                	grpset.forEach(function(item, sameItem, s){
	                		var gname;
	                		var table = new Array();
	                		for(var i=0; i<contdata.length; i++){
	                			if(item==contdata[i].gid){
	                				gname=contdata[i].gname;
	                				break;
	                			}
	                		}
	                		for(var i=0; i<contdata.length; i++){
	                			if(item==contdata[i].gid){
	                				table.push(contdata[i].oname);
	                			}
	                		}
	                		tr="<tr id=' "+item+" '><td><input class='select' name='groupid' type='checkbox' value='"
	                        + item +"' ></td><td>"+gname+"</td><td>"+table+"</td></tr>";
	                    	$("#list").append(tr);
	                	});
	                }
	            })
	            
	            function showto2() {
	            	 var sendto =  new Set();
	                 $("input[name='groupid']:checked").each(function () {
	                	 var user =  $(this).val();
	                	 for(var i = 0; i < cont.length; i++) {
	                        if(cont[i].gid ==  user ){
	                        	 sendto.add( cont[i].gname);
	                        }  	
	                	 }
	                 });
	                sendto2 = [];
	                sendto.forEach(function(item, sameItem, s){
	                	sendto2.push(item);
	                })       
	            }
	            showin = showto2;  
	        } 
	 }
	 
	 selectto();
	 
	 $("#sel").change(function(){  
		    selectto();
	 }); 
	 
	 $("#addconfirm").click(function(){ 
  	   showin();
    	console.log(sendto2);
  	   document.getElementById("showto").innerHTML= sendto2;
   })
   
 /*$("tbody").click(function(event) {
        if (event.target.type !== 'checkbox') {
            $(':checkbox', this).trigger('click');
        }
    });*/
	 
/* $("tbody tr").click(function(){
	 console.log("aaaaaaaaaa");
	  var tr =  $(this).attr("id");
	  console.log(tr);
	  $("[type== 'checkbox' ] [value == tr]").attr("checked",true);
   });*/
   
            $("#logout").click(function () {
                $.ajax
                ({ 
                    type: "GET",
                    url: "../login/logout", 
                    dataType: "json",
                    success: function (data) {
                        if (data.data == "success") {
                            window.location.href="/msg/user";
                        }
                        else {
                        	  alert("登出失败");
                        }
                    }
                })
                })
             
                $("#submit").click(function () { 
               	 
                var msgType =[];
                $("input[name='msgType']:checked").each(function () {
                     msgType.push($(this).val());
                });
                console.log(msgType);
                var sendid =[];
                $("input[name='sendid']:checked").each(function () {
                    sendid.push($(this).val());
                });
                console.log(sendid);
                var groupid =[];
                $("input[name='groupid']:checked").each(function () {
                    groupid.push($(this).val());
                });
                console.log(groupid);
                var context = $("#context").val();
                if(checkedTest()&&selTest()&&toTest()&&checkInput()) {
                
                	 $("#submit").attr("disabled",true);
                	/*document.getElementById("context").innerHTML= "";*/
                	 /*console.log(document.getElementById("context").innerHTML);
                	 console.log( $("#context").val());*/
               $.ajax
                ({ 
                    type: "POST",
                    url: "../object/sendmsg", 
                    dataType: "json",
                    traditional:true,
                    data: { msgType: msgType, sendid: sendid, groupid: groupid, context: context },
                    success: function (data) {
                        if (data.code == "00000") {
                            alert("消息发送成功！");
                        }
                        else {
                            alert(data.msg);
                        }
                        $("#context").val("");
                        $("#submit").attr("disabled",false);
                    }
                })
                }else{
                    return false;
                }
            })
            
           function checkInput(){

		  if($("#context").val().replace(/(^\s*)|(\s*$)/g, "").length ==0){
                    alert("请填写消息内容！");
                    $("#context").focus();
                    return false;
                }
                return true;
            }

            function checkedTest(){
            var count = 0;
            var checkArry = document.getElementsByName("msgType");
                    for (var i = 0; i < checkArry.length; i++) { 
                        if(checkArry[i].checked == true){
                            count++; 
                        }
                    }
            if( count == 0 ){
                alert("请选择发送通道！");
                $("input[name=msgType]").focus();
                return false;
            }
                return true;
            }
            
            function selTest(){
                var slt=document.getElementById("sel");
                if(slt.value=="0"){
                alert("请选择发送方式！");
                $("input[id=sel]").focus();
                return false;
                }
                return true;
                }
            
            function toTest(){
                if(sendto2.length == 0){
                alert("请选择发送对象！");
                $("input[id=sendto]").focus();
                return false;
                }
                return true;
                }
            
        })