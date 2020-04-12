<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--

  add user
  动态参数封装

  @author LinZhenNan lin_hehe@qq.com 2020/04/06
-->
<html>
  <head>
    <title>动态参数封装</title>
  </head>
  <body>
  <form action="${pageContext.request.contextPath}/action2.action" method="post">
    <%--表单中的 name 属性的值必须和动作类中数据模型字段一致且该字段拥有 setter 属性--%>
    用户名：<input type="text" name="username" /><br>
    年龄：<input type="text" name="age" /><br>
    <input type="submit" value="提交" />
  </form>
  </body>
</html>
