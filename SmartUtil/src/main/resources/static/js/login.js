function loginSubmit() {
	 var pwd=hex_md5($("#password").val());

    var requestData = JSON.stringify({
        id: $("#username").val(),
        password: pwd
    });

	$.ajax({
        method: 'POST',
        contentType: "application/json",//指定返回类型
        url: "/login",
        data:requestData,
        cache: false,  //禁用缓存
        dataType : "json",//预期返回数据类型
        type: "POST",
        success: function(data){
            if(data.code == "000000"){
                window.location = 'index';
            }else {
                alert(data.msg);
            }
        },
        error: function () {
            alert("发送失败", "系统内部错误！", "error");
        }
    });
}
