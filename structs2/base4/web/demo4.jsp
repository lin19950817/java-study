<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!--

  常用标签

  @author LinZhenNan lin_hehe@qq.com 2020/04/13
-->
<html>
  <head>
    <title>常用标签</title>
  </head>
  <body>
      <h1>set 标签</h1>
      <%--
        s:set 标签
            value 属性：取值是一个 OGNL 表达式
            var 属性：字符串
            把 value 属性的只作为 value，把 var 属性的值作为 key，存到 ActionContext 大 Map 中
      --%>
      <s:set var="key" value="'value'"/>
      <h1>action 标签</h1>
      <%--
        s:action 标签
            name 属性：指定一个 action 动作名称。
            executeResult 属性：是否执行 action。默认值是 false。
      --%>
      <s:action name="action1" executeResult="true"/>
      <h1>url 标签</h1>
      <%--
        s:url 标签
            value 属性：是把直接输出到页面上
            action 属性：是把动作的请求地址输出到页面上 ${pageContext.request.contextPath}/action1
            var 属性：把 action 的取值作为 value，把 var 的取值作为 key，放到 ActionContext 中
      --%>
      <s:url value="action1.action"/><br>
      <s:url action="action1" var="url">
          <%--name 作为 key，value 作为值，get 方式拼接参数--%>
          <s:param name="name" value="'hehe'"/>
      </s:url>

  <s:debug/>
  </body>
</html>
