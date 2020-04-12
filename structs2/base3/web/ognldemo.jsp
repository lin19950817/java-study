<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!--

  OGNL
    EL 自定义表达式方法：只能调用静态方法

  @author LinZhenNan lin_hehe@qq.com 2020/04/12
-->
<html>
  <head>
    <title>OGNL 表达式</title>
  </head>
  <body>
    <%--
        OGNL 表达式：他可以访问普通方法
        OGNL 表达式必须写在 struts2 的标签中
        s:property 它就类似于 jsp 的表达式(<%=%>)，把值输出到浏览器上
    --%>
    这是一个 OGNL 表达式:
    <s:property value="OGNL-Expression"/><br>
    这是一个普通的字符串:
    <s:property value="'OGNL-Expression'"/><br>
    使用普通字符串调用了获取长度的方法:
    <s:property value="'OGNL-Expression'.length()"/><br>

    <%--
        OGNL 访问静态属性
        访问静态属性的方式：@全类名@静态属性名称
    --%>
    访问 Integer 静态属性 MAX_VALUE:
    <s:property value="@java.lang.Integer@MAX_VALUE"/><br>
    <%--
        OGNL 访问静态方法
        访问静态方法的方式：@全类名@静态属性名称
        struts2 的框架默认是静态方法调用的。可以通过配置开启
    --%>
    访问 Math 的静态方法 randon():
    <s:property value="@java.lang.Math@random()"/><br>

    <h1>OGNL 操作 map 和 list</h1>
    <%--
        使用 s:radio 的标签，创建 list 集合
        {} 相当于创建一个 list 集合
        list 属性中的取值是一个 OGNL 表达式
    --%>
    <s:radio name="gender" list="{'男','女'}"/><br>
    <%--
          使用 s:radio 的标签，创建 map 集合
          #{} 表示创建一个 map
          1 和 0 作为 value 给 radio 标签的 value 属性赋值
          男和女作为 key，显示到页面的内容
    --%>
    <s:radio name="gender2" list="#{'1':'男','0':'女'}"/>
  </body>
</html>
