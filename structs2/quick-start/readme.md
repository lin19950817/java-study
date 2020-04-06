# struts2 入门案例

1. 导入 [jar](pom.xml) 包

2. 在 [web.xml](web/WEB-INF/web.xml) 中配置 `struts2` 的核心控制器

   ```xml
   <filter>
       <filter-name>struts2</filter-name>
       <filter-class>org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter</filter-class>
   </filter>
   <filter-mapping>
       <filter-name>struts2</filter-name>
       <url-pattern>/*</url-pattern>
   </filter-mapping>
   ```

3. 新建 [struts.xml](src/main/resources/struts.xml) 来源文件

## 流程

[主页](web/welcome.jsp) 中 `<a href="${pageContext.request.contextPath}/hello.action">访问 Struts2 的入门案例</a>` 的请求 `hello.action` 来源于 [struts.xml](src/main/resources/struts.xml) `<action name="hello" class="org.lzn.web.action.HelloAction" method="sayHello">` 的 `name = "hello"`

[struts.xml](src/main/resources/struts.xml) 的 **action** 会访问 [org.lzn.web.action.HelloAction](src/main/java/org/lzn/web/action/HelloAction.java) 类的 `sayHello` 方法

[org.lzn.web.action.HelloAction](src/main/java/org/lzn/web/action/HelloAction.java) 类的 `sayHello` 方法返回的 `success` 会在 [struts.xml](src/main/resources/struts.xml) 中寻找相应 **action** 下的 `<result name="success">/success.jsp</result>`，请求 `/success.jsp`

