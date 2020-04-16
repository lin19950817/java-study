<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!--

  token2

  @author LinZhenNan lin_hehe@qq.com 2020/04/13
-->
<html>
  <head>
    <title>Struts2 中的 token</title>
  </head>
  <body>
      <s:form action="token2">
          <%--使用 struts2 的内置标签 s:token--%>
          <s:token />
          <s:textfield name="name" label="用户名"/>
          <s:submit value="提交"/>
      </s:form>
  </body>
</html>
