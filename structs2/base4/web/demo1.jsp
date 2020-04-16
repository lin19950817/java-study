<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!--

  取 ActionContext 数据

  @author LinZhenNan lin_hehe@qq.com 2020/04/13
-->
<html>
  <head>
    <title>取 ActionContext 数据</title>
  </head>
  <body>
    <s:debug/>

    <%--
        使用 s:property 来获取 ActionContext 中的数据
            value 属性的取值是一个 OGNL 表达式
    --%>
    <h1>获取大 Map 中的数据</h1>
    <%--取的时候是 #key 名称--%>
    <s:property value="#contextMap"/>
    <h1>获取大 Map 中小 Map 的数据</h1>
    <%--使用 #大Map 的 key，小 Map 的 key--%>
    <s:property value="#session.sessionMap"/>
  </body>
</html>
