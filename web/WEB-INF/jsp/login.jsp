<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <title>登录界面</title>
</head>

<body>
<form action="${pageContext.request.contextPath }/servlet/loginServlet" method="post">
    用户：<input type="text" name="username"/><br/>
    密码：<input type="text" name="password"/><br/>
    <input type="submit" value="登陆"/><br/>
</form>
</body>
</html>
