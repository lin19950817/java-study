<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--

  descript

  @author LinZhen n  lin_hehe@qq.com 2019/11/28
-->
<html>
  <head>
    <title>first</title>
  </head>
  <body>
    this is first jsp
    <%-- 静态包含 --%>
    <%--@ include file="/include/second.jsp" --%>
    <%-- 动态包含 --%>
    <jsp:include page="second.jsp"></jsp:include>
  </body>
</html>
