var TT = HG = {
	checkLogin : function(){
//		alert(11);
		var _ticket = $.cookie("TICKET");
		if(!_ticket){
			return ;
		}
		alert(_ticket);
		$.ajax({
			url : "http://localhost:8084/user/token/" + _ticket,
			dataType : "jsonp",
			type : "GET",
			
			success : function(data){
				if(data.status == 200){
//					alert(data);
//					var _data = JSON.parse(data.data);
//					alert(_data.username);
//					var html =_data.username+"，欢迎来到霞飞！<a href=\"http://www.xiafei.com/user/logout.html\" class=\"link-logout\">[退出]</a>";
					var html =data.data.username+"，欢迎来到霞飞！<a href=\"http://www.xiafei.com/user/logout.html\" class=\"link-logout\">[退出]</a>";
//					alert(html);
					$("#loginbar").html(html);
				}
			},
			error:function(data){
				alert(data);
			}
		});
	}
}

$(function(){
	alert(111);
	// 查看是否已经登录，如果已经登录查询登录信息
	TT.checkLogin();
});