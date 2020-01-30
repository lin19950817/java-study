<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--

  descript

  @author LinZhenNan lin.zhennan@hand-china.com 2020/01/01 21
8 
--><html>
  <head>
    <title>pageContext</title>
  </head>
  <body>
    <%
      pageContext.setAttribute("att", "page");
      // 等价于
      pageContext.setAttribute("att", "page", PageContext.PAGE_SCOPE);

      request.setAttribute("att", "request");
      // 等价于
      pageContext.setAttribute("att", "request", PageContext.REQUEST_SCOPE);

      session.setAttribute("att", "session");
      // 等价于
      pageContext.setAttribute("att", "session", PageContext.SESSION_SCOPE);

      application.setAttribute("att", "application");
      // 等价于
      pageContext.setAttribute("att", "application", PageContext.APPLICATION_SCOPE);

      // 重定向
      // response.sendRedirect("/taglib/taglib.jsp");

      request.getRequestDispatcher("/taglib/taglib.jsp").forward(request, response);

      // 创建 8 个隐式对象，自定义标签时使用
      pageContext.getPage();
      pageContext.getRequest();
      pageContext.getResponse();
      pageContext.getException();
      pageContext.getSession();
      pageContext.getServletContext();
      pageContext.getServletConfig();
      pageContext.getOut();
    %>
  </body>
</html>
