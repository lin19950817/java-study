<%@ page import="org.lzn.entity.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--

  descript

  @author LinZhenNan lin.zhennan@hand-china.com 2019/12/26 17
0 
--><html>
  <head>
    <title>useBean</title>
  </head>
  <body>
    <%
      Student stu = new Student();
      stu.setName("tom");

      out.print(stu.getName());
    %>
    <%-- 等同于 --%>
    <jsp:useBean id="stu1" class="org.lzn.entity.Student"></jsp:useBean>
    <jsp:setProperty name="stu1" property="name" value="jetty" />
    <jsp:getProperty name="stu1" property="name"/>

  </body>
</html>
