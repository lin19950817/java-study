<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

    <%--接收 forward/forward.jsp 转发的参数--%>
    <%="forward.jsp"%>
    <%=request.getParameter("hehe")%>

    <%--接收 pageContext/pageContext.jsp 转发数据--%>
    <%="pageContext.jsp"%>
    <%=request.getAttribute("hehe")%>
    <%=session.getAttribute("session")%>

    <%--设置变量 num--%>
    <c:set var="num" value="<%=23%>" scope="page"></c:set>
    <%--输出数据--%>
    <c:out value="${num}"></c:out>
    <%--移除变量--%>
    <c:remove var="num"/>
    <%--如果 num 不存在，这输出默认值 aaa--%>
    <c:out value="%{num}" default="aaa"></c:out>

    <%--if 条件标签--%>
    <c:if test="${5 > 3}">
      结果为：true
    </c:if>
    <%--chooes 条件标签--%>
    <c:choose>
      <c:when test="${5 == 3}">
        5 等于 3
      </c:when>
      <c:when test="${5 == 5}">
        5 等于 5
      </c:when>
      <c:otherwise>
        其他情况
      </c:otherwise>
    </c:choose>

    <%--foreach 迭代标签。变量 i，初始化为 0， 结束条件为 i < 10，每次循环 i 加 2--%>
    <c:forEach var="i" begin="0" end="10" step="2">
      ${i}
      <hr/>
    </c:forEach>
    <%
      List list = new ArrayList();
      list.add("aaa");
      list.add("bbb");
      list.add("ccc");

      request.setAttribute("list", list);
    %>
    <%--
      c:forEach中的varStatus属性。
      指向一个字符串，该字符串引用一个对象。  map.put("vs",一个对象);
      这个对象记录着当前遍历的元素的一些信息：
      getIndex():返回索引。从0开始
      getCount():返回计数。从1开始
      isLast():是否是最后一个元素
      isFirst():是否是第一个元素
    --%>
    <c:forEach items="${list}" var="item" varStatus="vs">
      ${item}<br>
      ${vs.index}<br>
      ${vs.count}<br>
      ${vs.first}<br>
      ${vs.last}<br>
      <hr/>
    </c:forEach>
  </body>
</html>
