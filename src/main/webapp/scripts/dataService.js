var getData = function(obj, callBack) {
	console.log(obj);
    $.ajax({  
        type:"GET",   //http请求方式
        url:obj.url, //发送给服务器的url
        data:obj, //发送给服务器的参数
        dataType:"json",
        success:function(data) {
            if (typeof callBack === 'function') {
                callBack(data);
          }
        } 
    });
};

var postData = function(obj, callBack) {
    $.ajax({  
        type:"POST",   //http请求方式
        url:obj.url, //发送给服务器的url
        data:obj, //发送给服务器的参数
        dataType:"json",
        success:function(data) {
            if (typeof callBack === 'function') {
              callBack(data);
          }
        }
    });
};
