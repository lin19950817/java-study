<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--

  descript

  @author LinZhen n  lin_hehe@qq.com 2019/11/28
-->
<html>
  <head>
    <title>taglib</title>
  </head>
  <body>
    <%
      if (5 > 3) {
          out.print("taglib");
      }
    %>
    <%-- 等同 --%>
    <c:if test="${ 5 > 3 }">
        taglib
    </c:if>
  </body>
</html>
