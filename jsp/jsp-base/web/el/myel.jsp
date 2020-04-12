<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--引入标签库--%>
<%@ taglib prefix="myfn" uri="http://www.lzn.org/functions/myfunction" %>
<!--

  自定义 EL 表达式

  @author LinZhenNan lin_hehe@qq.com 2020/04/12
-->
<html>
  <head>
    <title>自定义 EL 表达式</title>
  </head>
  <body>
    <%--EL 表达式：只能调用静态方法--%>
    ${myfn:toUpperCase("ertyvbn")}
  </body>
</html>
