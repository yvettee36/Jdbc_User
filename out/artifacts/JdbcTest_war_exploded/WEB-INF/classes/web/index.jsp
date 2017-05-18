<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>首页</title>
</head>

<body style="text-align: center;">
<c:if test="${user!=null }">
    欢迎您：${user.nickname } <a href="${pageContext.request.contextPath }/servlet/loginOutServlet">注销</a><br/>
</c:if>
<hr>
<c:if test="${user==null }">
<a href="${pageContext.request.contextPath }/servlet/registerUIServlet">注册</a> <br>
<a href="${pageContext.request.contextPath }/servlet/loginUIServlet">登录</a> <br>
</body>
</c:if>
</html>
