<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- 导入包 --%>
<%@ page import="java.util.HashMap" %>

<%--关闭 session 对象--%>
<%@ page session="false" %>
<%
  // 报错
  // session.setAttribute("aaa", "asdf");
%>

<%-- 忽略 EL 表达式 --%>
<%@ page isELIgnored="true" %>
${ 2 }

<!--

3 个指令

@author LinZhenNan lin.zhennan@hand-china.com 2019/11/28
-->
<html>
  <body>
  <%-- 发生错误时跳转到错误页面 --%>
  <%@ page errorPage="error.jsp" %>
  <%
    // 报错
    int e = 10 / 0;
  %>

  </body>
</html>
