<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--引入 Struts2 标签库--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!--

  struts2 国际化

  @author LinZhenNan lin_hehe@qq.com 2020/04/10
-->
<html>
  <head>
    <title>struts2 国际化</title>
  </head>
  <body>
    <h1>查看控制台</h1>

    struts2 中的国际化，在 jsp 页面中访问信息资源包，必须使用 struts2 的标签<br>
    <%--
      使用 s:text 获取信息资源包的值
        当直接访问 jsp 时，应为没有经过动作类，只会去查找全局信息资源包。
        如果经过了动作类，则先去找动作类的。
    --%>
    <s:text name="key"/><hr>

    <%--当在消息资源包中都找不到 key 值时，直接把 name 属性的值输出到页面上--%>
    <s:text name="abc"/><hr>

    <%--s:i18n 标签，读取指定信息资源包--%>
    <s:i18n name="message">
      <s:text name="key"/>
    </s:i18n>
    <hr>

    <%--当自由读取消息资源包不存在时，按照资源包的搜索顺序去查找--%>
    <s:i18n name="not_message">
      <s:text name="key"/>
    </s:i18n>
  </body>
</html>
