<%@ page contentType="text/html;UTF-8" pageEncoding="UTF-8" %>
<!--

  发生错误时显示这个页面

  @author LinZhenNan lin.zhennan@hand-china.com 2019/11/28
--><html>
  <head>
    <title>Title</title>
  </head>
  <body>
  <%-- 暴露异常类 --%>
  <%@ page isErrorPage="true" %>
  <%=exception.getMessage() %>

    服务器繁忙！
  </body>
</html>
