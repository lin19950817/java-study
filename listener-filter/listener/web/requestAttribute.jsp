<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--

  演示 requestAttributeListenner

  @author LinZhenNan lin_hehe@qq.com 2020/03/17
-->
<html>
  <head>
    <title>requestAttribute</title>
  </head>
  <body>
  <h1>requestAttribute</h1>
  <%
    // 替换
    request.setAttribute("name", "hehe");
    request.setAttribute("name", "haha");

    // 移除
    request.removeAttribute("name");
  %>
  </body>
</html>
