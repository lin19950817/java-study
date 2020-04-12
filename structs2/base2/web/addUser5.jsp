<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--导入 struts2 的标签库--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!--

  add user5，以 add user4 为模板
  类型转化器，使用的是 struts2 的标签

  @author LinZhenNan lin_hehe@qq.com 2020/04/06
-->
<html>
  <head>
    <title>类型转化器，使用的是 struts2 的标签</title>
    <s:head></s:head>
  </head>
  <body>
  <%--动作错误--%>
  <s:actionerror />
  <%--字段错误--%>
  <s:fielderror />
  <%--
    struts2 的 form 标签，他提供了和原始 html 表单标签几乎一致的属性
      action：请求的地址。直接写动作名称。不用写 contextPath
      method：请求的方式。在这里不用写。struts2 的 form 表达默认就是 post
      enctype：表单编码的 MIME 类型
  --%>
  <s:form action="action5" >
    <%--
      requiredLabel：是否必需
      requiredPosition：必需标准的位置
    --%>
    <s:textfield name="username" label="用户名" requiredLabel="true" requiredPosition="left"/>
    <s:password name="password" label="密码" showPassword="true"/>
    <s:textfield name="birthday" label="生日" />
    <s:submit value="注册" />
  </s:form>
  </body>
</html>
