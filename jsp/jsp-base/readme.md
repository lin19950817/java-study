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
1. page 指令
2. include 指令
3. taglib 指令
### 语法
```
<%@ 指令名称 属性1=“属性值1”属性值2=“属性值2”。。。 %>
// 例如
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
```
### [page](web/page.jsp)
用于定义 jsp 页面的各种属性
1. import 和 java 代码中的 import 是一样的
    ```
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
   ```
   <%@ page language="java" %>
   ```
3. extends
   ```
   <%@ page extends="package.class" %>
   ```
4. session
   ```
   // true 为默认值，表示创建对象
   <%@ page session="fasle | true" %>
   ```
5. buffer
   ```
   // 8kb 为默认值
   <%@ page buffer="none | 8kb | sizekb" %>
   ```
6. autoFlush
   ```
   // true 为默认值
   <%@ page autoFlush="true | false" %>
   ```
7. isThreadSafe（已过时）
   ```
   // true 为默认值，false 才继承 SingleThreadModel 接口
   <%@ page isThreadSafe="true | false" %>
   ```
8. info
   ```
   <%@ page info="text" %>
   ```
9. [errorPage](web/error.jsp)<br>
    可以在 web.xml 使用 `<error-page>` 为整个应用程序设置错误处理页面，其中 `<exception-type>` 指定异常类的完全限定名， `<location>` 指定以 "/" 开头的错误处理页面的路径<br>
    如果页面设置了 `errorPage` 则覆盖 web.xml 的设置
   ```
   <%@ page errorPage="relative_url" %>
   ```
10. [isErrorPage](web/error.jsp)
   ```
   // 默认值为 false
   <%@ page isErrorPage="true | false" %>
   ```
11. contentType
    ```
       <%@ page contentType="mimeType [ ;charset=characterSet ] | text/html;charset=UTF-8" %>
       // 例如
       <%@ page contentType="text/html;charset=UTF-8" %>
       // 等同于
       response.setContentType("text/html;charset=UTF-8");
    ```
12. pageEncoding
    ```
       // 服务器使用什么编码转译文件
       <%@ page pageEncoding="UTF-8" %>
    ```
13. isisELIgnored
    ```
       // 忽视 EL 表达式，默认值 true
       <%@ page isisELIgnored="true | false" %>
    ```
### include
把其他资源包含到当前页面中
1. [first.jsp](web/include/first.jsp)
2. [second.jsp](web/include/second.jsp)
#### 静态包含（推荐）
```
<%@ include file="/include/header.jsp" %>
```
#### 动态包含
```
<jsp:include file="/include/header.jsp"></jsp>
```
#### 两者区别
翻译的时间段不同
##### 静态包含
在翻译是就把两个文件合并
##### 动态包含
不会合并文件，当代码执行到 include 时，才包含另一个文件的内容
### taglib
在 jsp 页面中导入 JSTL 标签库。替换 jsp 中的 java 代码片段
```
// prefix 是别名，需要 jar： jstl
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
```
## 6 个动作
1. `<jsp:include>` 动态包含
2. `<jsp:forward>` 请求转发
3. `<jsp:param>` 设置请求参数
4. `<jsp:userBean>` 创建一个对象
5. `<jsp:setProperty>` 给指定的对象属性赋值
6. `<jsp:getProperty>` 取出指定对象的属性值