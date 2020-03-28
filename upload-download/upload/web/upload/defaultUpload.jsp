<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--

  上传

  @author LinZhenNan lin_hehe@qq.com 2020/03/27
-->
<html>
  <head>
    <title>defaultUpload</title>
  </head>
  <body>
    <form action="${pageContext.request.contextPath}/defaultUpload" method="post">
      <input type="text" name="name" /><br>
      <input type="file" name="photo" /><br>
      <input type="submit" value="上传" /><br>
    </form>
  </body>
</html>
