<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人中心</title>
</head>
<body>
<h1>个人中心</h1>
账号：${sessionScope.LoginUser.username }<br>
姓名：${sessionScope.LoginUser.realname }<br>
头像：<br>
<img alt="" src="${pageContext.request.contextPath }/upload/avatar/${sessionScope.LoginUser.avatar }">
<br>
<a href="<%=request.getContextPath()%>/settings/avatar">更换头像</a>
<br><hr>
<a href="${pageContext.request.contextPath }/user/logout">注销（退出登录）</a>
<a href="${pageContext.request.contextPath }/settings/password">修改密码</a>
</body>
</html>