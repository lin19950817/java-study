<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--

structs2 快速入门案例

  @author LinZhenNan lin_hehe@qq.com 2020/04/05
-->
<html>
  <head>
    <title>structs2 快速入门案例</title>
  </head>
  <body>
  <h1>后缀名</h1>
  <a href="${pageContext.request.contextPath}/hello.action">访问 .action 结尾</a><br>
  <a href="${pageContext.request.contextPath}/hello.abc">访问 .abc 结尾</a><br>
  <a href="${pageContext.request.contextPath}/hello.pro">访问 .pro 结尾</a><br>
  <a href="${pageContext.request.contextPath}/hello.do">访问 .do 结尾</a><br>
  <h1>名称空间</h1>
  <a href="${pageContext.request.contextPath}/p2/hello.do">访问 .do 结尾，加名称空间 /p2</a><br>
  <a href="${pageContext.request.contextPath}/p2/hello.do">访问 .do 结尾，加名称空间 /p2/xx</a><br>
  <h1>动作间的转发、重定向</h1>
  <a href="${pageContext.request.contextPath}/chain1.do">访问 chain1，将转发到同包下的 hello</a><br>
  <a href="${pageContext.request.contextPath}/redirectAction1.do">访问 redirectAction1，将重定向到同包下的 hello</a><br>
  <a href="${pageContext.request.contextPath}/chain2.do">访问 chain2，将转发到不同包下的 hello</a><br>
  <a href="${pageContext.request.contextPath}/redirectAction2.do">访问 redirectAction2，将重定向到不同包下的 hello</a><br>
  </body>
</html>
