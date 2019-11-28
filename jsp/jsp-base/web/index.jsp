<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--

  jsp 基础语法

  @author LinZhenNan lin.zhennan@hand-china.com 2019/11/28
-->
<html>
  <head>
    <title>jsp基础语法</title>
  </head>
  <body>
  <!-- 这是 html 注释 -->
  <%-- 这是 jsp 注释 --%>
    <%!
      // 全局变量
      int num1 = 10;
      public void showNum1() {
        System.out.println(num1);
      }
    %>
    <%
      int num2 = 10;
      num1++;
      num2++;
    %>

    <%=num1 %>
    <%=num2 %>
  </body>
</html>
