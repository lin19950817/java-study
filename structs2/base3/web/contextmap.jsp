<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--

  descript

  @author LinZhenNan lin_hehe@qq.com 2020/04/12
-->
<html>
  <head>
    <title>contextMap</title>
  </head>
  <body>
    <%--在 session 中增加一行数据测试--%>
    <%session.setAttribute("name", "hehe");%>
    <%--借助 struts2 的 s:debug 标签查询里面的 neir--%>
    <s:debug/>
  </body>
</html>
