<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--

  multipartUpload

  @author LinZhenNan lin_hehe@qq.com 2020/03/27
-->
<html>
  <head>
    <title>multipartUpload</title>
  </head>
  <body>
      <form enctype="multipart/form-data" action="${pageContext.request.contextPath}/multipartUpload" method="post">
        <input type="text" name="name" /><br>
        <input type="file" name="photo" /><br>
        <input type="submit" value="上传" /><br>
      </form>
      </body>
</html>
