# jsp 基本语法
## jsp 的脚本 ☞ [index](web/index.jsp)
1. 小脚本 `<% java 代码 %>`
2. 表达式 `<%=2 + 3 %>` 等价于 `out.print(2 + 3);`
3. 声明 `<%! %>` 表示在类中定义全局成员，和静态块
## jsp 注释
1. jsp注释： `<%-- 被注释的内容 --%>` 特点： 安全，省流量
2. 网页注释： `<%-- 网页注释 --%>` 特点： 不安全，费流量
## jsp 的 3 个指令
> JSP指令（directive）是为JSP引擎而设计的，它们并不直接产生任何可见输出，而只是告诉引擎如何处理JSP页面中的其余部分。
1. <a href="#page">page 指令</a>
2. <a href="#include">include 指令</a>
3. <a href="#taglib">taglib 指令</a>
### 语法
```jsp
<%@ 指令名称 属性1=“属性值1”属性值2=“属性值2”。。。 %>
// 例如
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
```
### <a name="page" style="text-decoration:none">[page](web/page.jsp)</a>
用于定义 jsp 页面的各种属性
1. import 和 java 代码中的 import 是一样的
    ```jsp
    <%@ page import="java.util.Date,java.util.List" %>
    // 或者
    <%@ page import="java.util.Date" %>
    <%@ page import="java.util.List" %>
    ```
    jsp 会自动导入一下的包
    * import java.lang.*;
    * import javax.servlet.*;
    * import javax.servlet.http.*;
    * import javax.servlet.jsp.*;
2. language
   ```jsp
   <%@ page language="java" %>
   ```
3. extends
   ```jsp
   <%@ page extends="package.class" %>
   ```
4. session
   ```jsp
   // true 为默认值，表示创建对象
   <%@ page session="fasle | true" %>
   ```
5. buffer
   ```jsp
   // 8kb 为默认值
   <%@ page buffer="none | 8kb | sizekb" %>
   ```
6. autoFlush
   ```jsp
   // true 为默认值
   <%@ page autoFlush="true | false" %>
   ```
7. isThreadSafe（已过时）
   ```jsp
   // true 为默认值，false 才继承 SingleThreadModel 接口
   <%@ page isThreadSafe="true | false" %>
   ```
8. info
   ```jsp
   <%@ page info="text" %>
   ```
9. [errorPage](web/error.jsp)<br>
    可以在 web.xml 使用 `<error-page>` 为整个应用程序设置错误处理页面，其中 `<exception-type>` 指定异常类的完全限定名， `<location>` 指定以 "/" 开头的错误处理页面的路径<br>
    如果页面设置了 `errorPage` 则覆盖 web.xml 的设置
   
   ```jsp
   <%@ page errorPage="relative_url" %>
   ```
10. [isErrorPage](web/error.jsp)
   ```jsp
   // 默认值为 false
   <%@ page isErrorPage="true | false" %>
   ```
11. contentType
    ```jsp
       <%@ page contentType="mimeType [ ;charset=characterSet ] | text/html;charset=UTF-8" %>
       // 例如
       <%@ page contentType="text/html;charset=UTF-8" %>
       // 等同于
       response.setContentType("text/html;charset=UTF-8");
    ```
12. pageEncoding
    ```jsp
       // 服务器使用什么编码转译文件
       <%@ page pageEncoding="UTF-8" %>
    ```
13. isisELIgnored
    ```jsp
       // 忽视 EL 表达式，默认值 true
       <%@ page isisELIgnored="true | false" %>
    ```
### <a name="include" style="text-decoration:none">include</a>
把其他资源包含到当前页面中
1. [first.jsp](web/include/first.jsp)
2. [second.jsp](web/include/second.jsp)
#### 静态包含（推荐）
```jsp
<%@ include file="/include/header.jsp" %>
```
#### 动态包含
```jsp
<jsp:include file="/include/header.jsp"></jsp>
```
#### 两者区别
翻译的时间段不同
##### 静态包含
在翻译是就把两个文件合并
##### 动态包含
不会合并文件，当代码执行到 include 时，才包含另一个文件的内容
### <a name="taglib" style="text-decoration:none">[taglib](web/taglib/taglib.jsp)</a>
在 jsp 页面中导入 JSTL 标签库。替换 jsp 中的 java 代码片段。
```jsp
// prefix 是别名，需要 jar： jstl
<%--JSTL 1.0 声明：--%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%--JSTL 1.1 之后声明：--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
```
## 6 个动作
1. <a href="#include">`<jsp:include>` 动态包含</a>
2. [`<jsp:forward>` 请求转发](web/forward/forward.jsp)
3. [`<jsp:param>` 设置请求参数](web/forward/forward.jsp)
4. [`<jsp:userBean>` 创建一个对象](web/useBean/useBean.jsp)
5. [`<jsp:setProperty>` 给指定的对象属性赋值](web/useBean/useBean.jsp)
6. [`<jsp:getProperty>` 取出指定对象的属性值](web/useBean/useBean.jsp)

## 9 个内置对象

只在 `JSP` 的 `<%=%>` 和 `<% %>` 中可以直接使用的对象

| 对象        | 类型                                   | 说明                                  |
| ----------- | -------------------------------------- | ------------------------------------- |
| request     | javax.servlet.http.HttpServletRequest  |                                       |
| response    | javax.servlet.http.HttpServletResponse |                                       |
| session     | javax.servlet.http.HttpSession         | 由 `session="true"` 开关              |
| application | javax.servlet.ServletContext           |                                       |
| exception   | java.lang.Throwable                    | 由 `isErrorPage="false"` 开关         |
| page        | java.lang.Object 当前对象 this         | 当前 `servlet` 实例                   |
| config      | javax.servlet.ServletConfig            |                                       |
| out         | javax.servlet.jsp.JspWriter            | 字符输出流，相当于 `printWriter` 对象 |
| pageContext | javax.servlet.jsp.PageContext          |                                       |

## 四大域对象

1. <a href="#pageContext">pageContext</a> : 存放的数据在当前页面有效。
2. ServletRequest : `request` 存放的数据在一次请求（转发）内有效。
3. HttpSession : `session` 存放的数据在一次会话中有效。
4. ServletContext : 存放的数据在整个应用范围内都有效。

### <a name="pageContext">[pageContext](web/pageContext/pageContext.jsp)</a>

1. 本身也是一个域对象：它可以操作其它三个域对象 `request`、`response`、`application` 的数据

   ```
   void setAttribute(String name, Object o);
   Object getAttribute(String name);
   void removeAttribute(String name);
   // 操作其他域对象方法，cope 代表操作哪一个对象
   void setAttribute(String name, Object o, int scope);
   Object getAttribute(String name, int scope);
   void removeAttribute(String name, int scope);
   // scope 的值
   PageContext.PAGE_SCOPE;
   PageContext.REQUEST_SCOPE;
   PageContext.SESSION_SCOPE;
   PageContext.APPLICATION_SCOPE;
   // 从 page, request, session, application 依次查找，找到了就取值，结束查找
   Object findAttribute(String name);
   ```
   
2. 它可以创建 8 个隐式对象

   在普通类中可以通过PageContext获取其他JSP隐式对象。自定义标签时就使用。

## EL 表达式

EL 表达式：expression language 表达式语言

简化 jsp 中 java 代码开发

不是一种语言而是 jsp 中 **获取数据** 的一种规范

1. [sender.jsp](web/el/sender.jsp) : 用于发生数据给 `el.jsp`
2. [el.jsp](web/el/el.jsp) : 用于学习 `EL` 表达式

### 获取数据

1. El 表达式只能获取存在 4 个作用域中的数据
2. 获取到 null 这样的数据，在页面中表现为空字符串
3. 通过 `.` 去获取属性
4. 支持 `[]` 运算符，点能做的，它也能做; 它能做的，点不一定能做  

```jsp
  <%
    Student s = (Student) request.getAttribute("student");
    out.print(s);
  %>
  <%--等同于--%>
  ${student}
  <%--原理--%>
  <%=pageContext.findAttribute("student")%>

  <%--通过（.）获取 name 属性--%>
  ${"<br>"}
  ${student.name}

  <%--null 数据将视为空字符串--%>
  ${"<br>"}
  ${null}

  <%--[] 运算符--%>
  ${"<br>"}
  <%--获取属性--%>
  ${student["name"]}
  ${student['name']}
  <%--List 操作--%>
  ${list[1]}
  <%--Map 操作--%>
  ${map["c"]}
  ${map.c}
  
```

### 运算

#### empty

判断 `null`，空字符串和没有元素的集合（即使集合本身不为 `null`）都返回 `true`

```jsp
 <%
    String nullStr = null;
    String emptyStr = "";
    String str = "string";

    List emptyList = new ArrayList();

    request.setAttribute("nullStr", nullStr);
    request.setAttribute("emptyStr", emptyStr);
    request.setAttribute("str", str);
    request.setAttribute("emptyList", emptyList);
  %>

  ${empty nullStr}
  ${empty emptyStr}
  ${empty str}
  ${empty emptyList}
  <%--三元运算符--%>
  ${empty nullStr ? "空" : "有值"}
```

### 11 个隐式对象

| EL 隐式对象引用名称 | 类型                          | JSP 内置对象名称 | 说明                                                         |
| ------------------- | ----------------------------- | ---------------- | ------------------------------------------------------------ |
| pageContext         | javax.servlet.jsp.PageContext | pageContext      | 一样                                                         |
| pageScope           | java.util.Map<String,Object>  | 没有对应的       | pageContext范围中存放的数据,页面范围                         |
| requestScope        | java.util.Map<String,Object>  | 没有对应的       | 请求范围数据                                                 |
| sessionScope        | java.util.Map<String,Object>  | 没有对应的       | 会话范围数据                                                 |
| applicationScope    | java.util.Map<String,Object>  | 没有对应的       | 应用范围数据                                                 |
| param               | java.util.Map<String,Object>  | 没有对应的       | 一个请求参数。${param.name}=request.getParameter("name")     |
| paramValues         | java.util.Map<String,Object>  | 没有对应的       | 重名请求参数。${paramValues.hobby[0]}=request.getParameterValues("hobby")[0] |
| header              | java.util.Map<String,Object>  | 没有对应的       | 一个请求消息头。${header["User-agent"]}=request.getHeader("User-agent") |
| headerValues        | java.util.Map<String,Object>  | 没有对应的       | 重名请求消息头。同 paramValues                               |
| initParam           | java.util.Map<String,Object>  | 没有对应的       | web.xml中全局参数                                            |
| cookie              | java.util.Map<String,Object>  | 没有对应的       | key:cookie对象的name值                                       |

## JSTL

`JSTL(JavaServerPages Standard Tag Library)` `JSTL` 标准标签库。使用 `JSTL` 实现 `JSP` 页面中逻辑处理。如判断、循环等。

1. <a href="#taglib">引入如 tablib 指令</a>

2. 使用 `JSTL` 标签

   ```jsp
   <c:if test=""></c:if>
   ```

### 核心标签库

1. 通用标签库。`set`，`out`，`remove`

   ```jsp
   	<%--设置变量 num--%>
       <c:set var="num" value="<%=23%>" scope="page"></c:set>
       <%--输出数据--%>
       <c:out value="${num}"></c:out>
       <%--移除变量--%>
       <c:remove var="num"/>
       <%--如果 num 不存在，这输出默认值 aaa--%>
       <c:out value="%{num}" default="aaa"></c:out>
   ```

2. 条件标签。`if`，`choose`

   ```jsp
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
   ```

3. 迭代标签。`forEach`

   ```jsp
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
       <c:forEach items="${list}" var="item">
         ${item}<br>
         ${vs.index}<br>
         ${vs.count}<br>
         ${vs.first}<br>
         ${vs.last}<br>
         <hr/>
       </c:forEach>
   ```

   

