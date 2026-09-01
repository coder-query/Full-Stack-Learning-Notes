<%--
  Created by IntelliJ IDEA.
  User: zsh
  Date: 2024/12/21 星期六
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/login" method="get">
   姓名:   <input type="text" name="username"><br>
   密码 :  <input type="password" name="password"><br>
    <input type="submit" value="登录">
</form>

</body>
</html>
