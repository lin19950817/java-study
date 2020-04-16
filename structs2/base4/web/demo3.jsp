<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!--

  iterator 标签

  @author LinZhenNan lin_hehe@qq.com 2020/04/13
-->
<html>
  <head>
    <title>iterator 标签</title>
  </head>
  <body>
  <h1>var 属性有值</h1>
    <table width="500px" border="1" align="center">
        <tr>
            <th>序号</th>
            <th>姓名</th>
            <th>年龄</th>
        </tr>
        <%--
            s:iterator，struts2 的迭代标签
            属性：
                begin, end, step 和 jstl 的 forEach 标签是一样的
                value 属性：要遍历的集合，是 OGNL 表达式
                var 属性：字符串
                    写了该属性：把 var 的值作为 key，把当前遍历的元素作为 value。存到 ActionContext 这个大 Map 中
                    不写该属性：把当前遍历的元素压入栈顶
                status 属性：遍历时的一些计数信息
                    int getIndex(): 从 0 开始
                    int getCount(): 从 1 开始
                    boolean isFirst(): 是否第一个
                    boolean isLast(): 是否最后一个
                    boolean isOdd(): 是否奇数
                    boolean isEven(): 是否偶数
        --%>
        <s:iterator value="students" var="s" status="status">
            <tr>
                <td><s:property value="#status.index" /></td>
                <td><s:property value="#s.name" /></td>
                <td><s:property value="#s.age" /></td>
            </tr>
        </s:iterator>
    </table>

    <h1>var 属性没有值</h1>
    <table width="500px" border="1" align="center">
        <tr>
            <th>序号</th>
            <th>姓名</th>
            <th>年龄</th>
        </tr>
        <s:iterator value="students" status="status">
            <tr>
                <td><s:property value="#status.count" /></td>
                <td><s:property value="name" /></td>
                <td><s:property value="age" /></td>
            </tr>
        </s:iterator>
    </table>

    <h1>OGNL 投影</h1>
    <%--
        OGNL 的投影：添加过滤条件
            ?# : 过滤所有符合条件的集合，如：users.{?#this.age > 19}
            ^# : 过滤第一个符合条件的集合，如：users.{^#this.age > 19}
            $# : 过滤最后一个符合条件的集合，如：users.{$#this.age > 19}
    --%>
  <h2 align="center">过滤年龄 > 18</h2>
  <table width="500px" border="1" align="center">
      <tr>
          <th>序号</th>
          <th>姓名</th>
          <th>年龄</th>
      </tr>
      <s:iterator value="students.{?#this.age > 18}" status="status">
          <tr>
              <td><s:property value="#status.index" /></td>
              <td><s:property value="name" /></td>
              <td><s:property value="age" /></td>
          </tr>
      </s:iterator>
  </table>
    <s:debug/>
  </body>
</html>
