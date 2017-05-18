<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>首页</title>
</head>

<body style="text-align: center;">
<form method="post" action="${pageContext.request.contextPath }/servlet/registerServlet" name="reg">
    <table width="400" border="1" height="172" align="center">
        <tbody>
        <tr>
            <td>&nbsp;姓名</td>
            <td>&nbsp;<input type="text" name="username" value="${form.username }"><span>${form.errors.username }</span>
            </td>
        </tr>
        <tr>
            <td>&nbsp;密码</td>
            <td>&nbsp;<input type="password" name="password"><span>${form.errors.password }</span></td>
        </tr>
        <tr>
            <td>&nbsp;确认密码</td>
            <td>&nbsp;<input type="password" name="password2"><span>${form.errors.password2}</span></td>
        </tr>
        <tr>
            <td>&nbsp;email</td>
            <td>&nbsp;<input type="text" name="email" value="${form.email }"><span>${form.errors.email }</span></td>
        </tr>
        <tr>
            <td>&nbsp;生日</td>
            <td>&nbsp;<input type="text" name="birthday" value="${form.birthday }"><span>${form.errors.birthday }</span>
            </td>
        </tr>
        <tr>
            <td>&nbsp;别名</td>
            <td>&nbsp;<input type="text" name="nickname" value="${form.nickname}"><span>${form.errors.nickname }</span>
            </td>
        </tr>
        <tr>
            <td>&nbsp;<input type="reset" value="重置" name="reset"></td>
            <td>&nbsp;<input type="submit" value="提交" name="submit"></td>
        </tr>
        </tbody>
    </table>
</form>
</body>
</html>
