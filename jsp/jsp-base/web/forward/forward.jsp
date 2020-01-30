<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--

  descript

  @author LinZhenNan lin.zhennan@hand-china.com 2019/12/26 17
5
--><html>
  <head>
    <title>forward</title>
  </head>
  <body>
    <%
//      request.getRequestDispatcher("../taglib/taglib.jsp").forward(request, response);
    %>
    <%-- 等同于 --%>
    <%--<jsp:forward page="/taglib/taglib.jsp"></jsp:forward>--%>

    <%--带参数转发--%>
    <jsp:forward page="/taglib/taglib.jsp">
      <%-- taglib.jsp?hehe=123 --%>
      <jsp:param name="hehe" value="123"/>
    </jsp:forward>
  </body>
</html>
