<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!--

  struts2 中的文件上传

  @author LinZhenNan lin_hehe@qq.com 2020/04/12
-->
<html>
  <head>
    <title>struts2 中的文件上传</title>
  </head>
  <body>
    <%--
        文件上传的必要前提
            1. 请求方式必须是 post
            2. enctype 属性取值必须是 multipart/form-data
            3. 提供一个文件选择域
    --%>
    <s:actionerror/>
    <s:form action="upload1" enctype="multipart/form-data">
      <s:textfield name="username" label="用户名"/>
      <s:file name="photo" label="照片"/>
      <s:submit value="上传"/>
    </s:form>
  </body>
</html>
