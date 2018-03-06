<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码(AJAX)</title>
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>
<h1>修改密码</h1>
<hr>
当前密码：<input>
新的密码：<input>
<input id="btn1" type="button" value="确认修改">
<hr>
<script type="text/javascript">
$(function(){
	$('#btn1').click(function(){
		$.post('${pageContext.request.contextPath}/settings/password',{
			'pw1':$('input:eq(0)').val(),
			'pw2':$('input:eq(1)').val()
		},function(data){
			if(data=='OldError'){
				alert('当前密码不正确');
			}
			if(data=='success'){
				alert('修改成功');
			}
		});
	});
});
</script>
</body>
</html>