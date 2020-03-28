<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--

第三方的上传组件实现文件上传

  @author LinZhenNan lin_hehe@qq.com 2020/03/28
-->
<html>
  <head>
    <title>第三方的上传组件实现文件上传</title>
  </head>
  <body>
    <form enctype="multipart/form-data" action="${pageContext.request.contextPath}/fileupload" method="post">
      <input type="text" name="name" /><br>
      <input type="file" name="photo" /><br>
      <input type="submit" value="上传" /><br>
    </form>
  </body>
</html>
