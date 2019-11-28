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
10. isErrorPage
   ```
   // 默认值为 false
   <%@ page isErrorPage="true | false" %>
   ```
