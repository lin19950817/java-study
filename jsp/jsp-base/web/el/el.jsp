<%@ page import="org.lzn.entity.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--

  descript

  @author LinZhenNan lin.zhennan@hand-china.com 2020/01/01 22
5 
--><html>
  <head>
    <title>el</title>
    <%--基础路径--%>
<%--    <base href="<%=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/"%>" />--%>
  </head>
<body>
  <%--1. 获取数据--%>
  ${"<h1>获取数据</h1>"}
  <%
    Student s = (Student) request.getAttribute("student");
    out.print(s);
  %>
  <%--等同于--%>
  ${student}
  <%--原理--%>
  <%=pageContext.findAttribute("student")%>

  <%--通过（.）获取 name 属性--%>
  ${"<br>"}
  ${student.name}

  <%--null 数据将视为空字符串--%>
  ${"<br>"}
  ${null}

  <%--[] 运算符--%>
  ${"<br>"}
  <%--获取属性--%>
  ${student["name"]}
  ${student['name']}
  <%--List 操作--%>
  ${list[1]}
  <%--Map 操作--%>
  ${map["c"]}
  ${map.c}

  <%--2. 运算--%>
  ${"<h1>运算</h1>"}
  <%
    String nullStr = null;
    String emptyStr = "";
    String str = "string";

    List emptyList = new ArrayList();

    request.setAttribute("nullStr", nullStr);
    request.setAttribute("emptyStr", emptyStr);
    request.setAttribute("str", str);
    request.setAttribute("emptyList", emptyList);
  %>

  ${empty nullStr}
  ${empty emptyStr}
  ${empty str}
  ${empty emptyList}
  <%--三元运算符--%>
  ${empty nullStr ? "空" : "有值"}

  <%--3. 11 个隐式对象--%>
  ${"<h1>隐式对象</h1>"}
  <%
    pageContext.setAttribute("att", "page");
//    pageContext.setAttribute("att", "page", PageContext.PAGE_SCOPE);

    request.setAttribute("att", "request");
//    pageContext.setAttribute("att", "request", PageContext.REQUEST_SCOPE);

    session.setAttribute("att", "session");
//    pageContext.setAttribute("att", "session", PageContext.SESSION_SCOPE);

    application.setAttribute("att", "application");
//    pageContext.setAttribute("att", "application", PageContext.APPLICATION_SCOPE);
  %>

  ${pageScope.att}
  ${requestScope.att}
  ${sessionScope.att}
  ${applicationScope.att}
  ${header["User-agent"]}
  </body>
</html>
