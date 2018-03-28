<%--
  Created by IntelliJ IDEA.
  User: yuxi
  Date: 2018/3/28
  Time: 9:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <title>注册新账号</title>
</head>
<body style="padding: 50px; text-align: center;">

    <form method="post" action="/submit" autocomplete="off">
        用户名：<input name="username" type="text" /><br/>
        密码：<input name="password" type="text" /><br/>
        <input type="submit" />
    </form>

</body>
</html>
