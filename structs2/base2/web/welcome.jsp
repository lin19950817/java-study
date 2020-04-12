<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--

  主页

  @author LinZhenNan lin_hehe@qq.com 2020/04/06
-->
<html>
  <head>
    <title>主页</title>
  </head>
  <body>
  <h1>静态参数封装</h1>
  <a href="${pageContext.request.contextPath}/action1.action">静态参数封装</a><br>
  <h1>动态参数封装</h1>
  <a href="${pageContext.request.contextPath}/addUser.jsp">动态参数封装，数据模型与动作类一起</a><br>
  <a href="${pageContext.request.contextPath}/addUser2.jsp">动态参数封装，数据模型与动作类分开写</a><br>
  <a href="${pageContext.request.contextPath}/addUser3.jsp">动态参数封装，使用模型驱动</a><br>
  <h1>类型转换器</h1>
  <a href="${pageContext.request.contextPath}/addUser4.jsp">局部/全局 类型转化器，需要通过修改配置来启动测试局部/全局类型转换器</a><br>
  <h1>类型转换失败后的异常处理，struts2 的标签</h1>
  <a href="${pageContext.request.contextPath}/addUser5.jsp">不使用自定义类型转化器，转换失败后的异常处理</a><br>
  <%--编程式验证--%>
  <a href="${pageContext.request.contextPath}/addUser6.jsp">编程式验证，全部动作校验</a><br>
  <a href="${pageContext.request.contextPath}/addUser7.jsp">编程式验证，跳过校验的动作</a><br>
  <a href="${pageContext.request.contextPath}/addUser8.jsp">编程式验证，指定校验的动作</a><br>
  <a href="${pageContext.request.contextPath}/addUser9.jsp">编程式验证，声明式全局验证</a><br>
  <a href="${pageContext.request.contextPath}/addUser10.jsp">编程式验证，声明式指定动作验证</a><br>
  </body>
</html>
