<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="com.opensymphony.xwork2.util.ValueStack" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!--

  取 ValueStack 数据

  @author LinZhenNan lin_hehe@qq.com 2020/04/13
-->
<html>
  <head>
    <title>取 ValueStack 数据</title>
  </head>
  <body>
    <s:debug/>

    <%--
        使用 s:property 来获取 ValueStack 中的数据
            value 属性的取值是一个 OGNL 表达式，它只能获取元素中的属性
        注意：
            取 ValueStack 中的对象属性时，不使用 #
            从栈顶逐个对象查找指定属性名称。只要找到了，就不在继续查找
    --%>
    <h1>获取 ValueStack 数据</h1>
    <s:property value="name"/>
    <h1>获取 ValueStack 中指定位置的属性</h1>
    <%--使用的是 [索引].属性名称--%>
    <s:property value="[1].name"/>
    <h1>获取栈顶 Map 中的元素</h1>
    <s:property value="s1.name"/>
    <h1>s:property 什么属性都不写</h1>
    <%--默认取栈顶元素--%>
    <s:property/>

    <h1>findValue</h1>
    <%--以上模拟原理：全是 ValueStack 的 findValue 和 findString--%>
    <%
        ValueStack valueStack = ActionContext.getContext().getValueStack();
        String name = valueStack.findString("name");
        out.print("name<br>");
        out.print(name);
        String name1 = valueStack.findString("[1].name");
        out.print("<br>[1].name<br>");
        out.print(name1);
    %>
  </body>
</html>
