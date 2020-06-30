<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--

  注册

  @author LinZhenNan lin_hehe@qq.com 2020/06/15
-->
<html>
  <head>
    <title>注册</title>
  </head>
  <body>
    <form action="${pageContext.request.contextPath}/userAction_register.action" method="post">
      用户名：<input type="text" name="username"/><br>
      密码：<input type="password" name="password"/><br>
      <input type="submit"/>
    </form>
  </body>
</html>
