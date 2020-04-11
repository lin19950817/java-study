<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ResourceBundle" %>
<%
  Locale locale = request.getLocale();
  ResourceBundle bundle = ResourceBundle.getBundle("message", locale);
%>
<!--

  jsp 国际化

  @author LinZhenNan lin_hehe@qq.com 2020/04/09
-->
<html>
  <head>
    <title><%=bundle.getString("jsp.login.title")%></title>
  </head>
  <body>
    <form action="#">
      <%=bundle.getString("jsp.login.username")%>：<input type="text" name="username" /><br>
      <%=bundle.getString("jsp.login.password")%>：<input type="password" name="password" /><br>
      <input type="submit" value="<%=bundle.getString("jsp.login.submit")%>" />
    </form>
  </body>
</html>
