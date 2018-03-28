<%--
  Created by IntelliJ IDEA.
  User: yuxi
  Date: 2018/3/27
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body style="padding: 50px; text-align: center;">

    <form action="/login" method="post" autocomplete="off">
        用户名：<input type="text" name="username" /><br/>
        密码：<input type="password" name="password" /><br/>
        <input type="submit" />
    </form>
    <a href="/signup">注册新账号</a>

</body>
</html>