<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--

  add user4
  类型转化器

  @author LinZhenNan lin_hehe@qq.com 2020/04/06
-->
<html>
  <head>
    <title>类型转化器</title>
  </head>
  <body>
  <form action="${pageContext.request.contextPath}/action5.action" method="post">
    用户名：<input type="text" name="username" /><br>
    年龄：<input type="text" name="age" /><br>
    生日：<input type="text" name="birthday" /><br>
      <input type="submit" value="提交" />
  </form>
  </body>
</html>
