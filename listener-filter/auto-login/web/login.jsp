<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--

  登录页

  @author LinZhenNan lin_hehe@qq.com 2020/03/25
-->
<html>
  <head>
    <title>登录</title>
  </head>
  <body>
      ${msg }
      <form action="${pageContext.request.contextPath }/servlet/loginServlet" method="post">
        username：<input type="text" name="username"/><br/>
        password：<input type="password" name="password"/><br/>
        <input type="checkbox" name="autologin" />自动登录<br/>
        <input type="submit" value="登录" /><br/>
      </form>
  </body>
</html>
