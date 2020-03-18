<%@ page import="org.lzn.domain.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--

  演示 httpSessionBinding

  @author LinZhenNan lin_hehe@qq.com 2020/03/17
-->
<html>
  <head>
    <title>httpSessionBinding</title>
  </head>
  <body>
  <h1>httpSessionBinding</h1>
  <%
    // 绑定 bean
    session.setAttribute("user", new User());

    // 解除绑定 bean
    session.removeAttribute("user");
  %>
  </body>
</html>
