<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--

structs2 快速入门案例

  @author LinZhenNan lin_hehe@qq.com 2020/04/05
-->
<html>
  <head>
    <title>structs2 快速入门案例</title>
  </head>
  <body>
  <%--在 Structs2 中，控制器会默认拦截扩展名 .action 的请求（以 .action 为后缀的 url）。除此之外，什么都不写也可以--%>
  <a href="${pageContext.request.contextPath}/hello.action">访问 Structs2 的入门案例</a>
  </body>
</html>
