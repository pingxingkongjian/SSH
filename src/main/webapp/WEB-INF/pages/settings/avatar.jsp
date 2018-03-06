<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更改头像</title>
</head>
<body>
<h1>更改头像</h1>
<hr>
当前头像：<br>
<img alt="" src="${pageContext.request.contextPath }/upload/avatar/${sessionScope.LoginUser.avatar}">
<hr>
上传新头像：
<form action="" method="post" enctype="multipart/form-data">
	<input type="file" name="file" >
	<input type="submit" value="确认上传" >
</form>
</body>
</html>