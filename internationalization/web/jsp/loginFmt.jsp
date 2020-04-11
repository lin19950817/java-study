<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--引入 jstl 的国际化库--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 TRansitional//EN">
<!--

  jsp 国际化
    使用国际化标签

  @author LinZhenNan lin_hehe@qq.com 2020/04/10 21
9 
-->

<fmt:setLocale value="${pageContext.request.locale}"/>
<fmt:setBundle basename="message" var="bundle"/>
<html>
  <head>
    <title><fmt:message key="jsp.login.title" bundle="${bundle}"/></title>
  </head>
  <body>
    <form action="#">
      <fmt:message key="jsp.login.username" bundle="${bundle}"/>：<input type="text" name="username">
      <fmt:message key="jsp.login.password" bundle="${bundle}"/>：<input type="password" name="password">
      <input type="submit" value="<fmt:message key="jsp.login.submit" bundle="${bundle}"/>">
    </form>
  </body>
</html>
