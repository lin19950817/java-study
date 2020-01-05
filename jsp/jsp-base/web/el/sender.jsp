<%@ page import="org.lzn.entity.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--

  descript

  @author LinZhenNan lin.zhennan@hand-china.com 2020/01/01 22
6 
--><html>
  <head>
    <title>el sender</title>
  </head>
  <body>
  <%
    Student s = new Student();
    s.setName("hehe");

    List list = new ArrayList();
    list.add("aaa");
    list.add("bbb");
    list.add("ccc");

    Map m = new HashMap();
    m.put("a", "aaa");
    m.put("b", "bbb");
    m.put("c", "ccc");

    // request 作用域
//    request.setAttribute("student", s);
    // session 作用域
    session.setAttribute("student", s);

    request.setAttribute("list", list);
    request.setAttribute("map", m);

    request.getRequestDispatcher("/el/el.jsp").forward(request, response);
  %>
  </body>
</html>
