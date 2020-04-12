# struts2 基础

## 加载 strut2 配置顺序

如果多个文件配置了同一个 struts2 常量，则后一个文件中配置的常量值会覆盖前面文件配置的常量值

1. default.properties 该文件保存在 struts2-core.jar 中 org.apache.struts2 包里面
2. struts-default.xml 该文件保存在 struts2-core.jar
3. struts-plugin.xml 该文件保存在 struts-Xxx.jar
4. struts.xml 该文件是 web 应用默认的 struts 配置文件 
5. struts.properties 该文件是 structs 的默认配置文件
6. web.xml 该文件是 web 应用的配置文件

### 例子 - 修改 struts 默认后缀名

通过修改常量配置的值来修改 struts2 的拦截扩展名。以下例子可以通过 [主页](web/welcome.jsp) 中的链接访问测试。

* 在 [struts.xml](src/main/resources/struts.xml) 中修改后缀命为 **abc**

  ```xml
  <constant name="struts.action.extension" value="abc" />
  ```

* 在 [struts.properties](src/main/resources/struts.properties) 中修改后缀命为 **pro**

  ```properties
  struts.action.extension=pro
  ```

* 在 [web.xml](web/WEB-INF/web.xml) 中修改后缀命为 **do**

  ```xml
  <init-param>
      <param-name>struts.action.extension</param-name>
      <param-value>do</param-value>
  </init-param>
  ```

## package 元素

| 属性      | 说明                                                         |
| --------- | ------------------------------------------------------------ |
| name      | 指定包的名称。注意：报名处在配置文件中唯一。                 |
| extends   | 指定当前包的父包。它是按照面向对象的思想管理的体现。         |
| abstract  | 把包声明为一个抽象包。抽象宝就是用来被继承的。只是没有 action 元素的包，才能被定义为抽象包。 |
| namespace | 名称空间。当指定了名称空间之后，访问路径就变成了：访问路径 = 名称空间 + 动作名称。当不指定该属性时，该属性有默认值，默认值是""。注意，不是"/"。 |

namespace 指定为 /p2，请求 /p2/xx/hello.action 也能请求到资源。

1. 在 /p2/xx 中寻找动作 hello.action
2. 找到则执行，否则在 /p2 中寻找动作（逐级寻找）
3. 直到根（是 "" 不是 "/" ） 也找不到则报错

### 例子 - 加名称空间

以下例子可以通过 [主页](web/welcome.jsp) 中的链接访问测试。

* 在 [struts.xml](src/main/resources/struts.xml) 中配置名称空间

  ```xml
  <package name="p2" extends="struts-default" namespace="/p2">
      <action name="hello" class="base.org.lzn.web.action.HelloAction" method="sayHello">
          <result name="success">/success.jsp</result>
      </action>
  </package>
  ```

## 动作类的定义

* [普通类](src/main/java/base/org/lzn/web/action/HelloAction.java)

  创建一个普通的 java 类，就是一个 POJO，原始的老的 java 对象，Plain Old Java Object。

* [实现类](src/main/java/base/org/lzn/web/action/Demo2Action.java)

  实现 Action 接口，实现接口中的方法。接口中已定义常量。

  | 名称    | 值        | 说明                                        |
  | ------- | --------- | ------------------------------------------- |
  | SUCCESS | "success" | 当执行成功后，前往指定的位置                |
  | NONE    | "none"    | 不返回任何结果视图，和 return null 是一样的 |
  | ERROR   | "error"   | 当执行动作方法出现异常后，前往指定的位置    |
  | INPUT   | "input"   | 数据回显                                    |
  | LOGIN   | "login"   | 一般用于返回登录页面                        |

* [继承类](src/main/java/base/org/lzn/web/action/Demo3Action.java)

  创建一个类，继承 ActionSupport。类中已实现默认动作方法：execute()

* 在 [struts.xml](src/main/resources/struts.xml) 中设置默认动作类和动作方法的配置。默认的动作类是：ActionSupport 的类。他是在 struts-default.xml 的配置文件中定义着。

  ```xml
  <action name="defaultAction">
      <result name="success">/success.jsp</result>
  </action>
  ```

* 在 [struts.xml](src/main/resources/struts.xml) 中修改默认动作类（一般不改）

  ```xml
  <default-class-ref class="base.org.lzn.web.action.Demo3Action" />
  ```


## 动作方法调用配置

### 使用通配符，配置动作方法

```xml
<!--拦截所有-->
<action name="*" class="base.org.lzn.web.action.HelloAction" method="sayHello">
    <result name="success">/success.jsp</result>
</action>
```

\* 表示动作的名称，可以使用 {名称出现的位置} 来匹配

```xml
<!--
	拦截所有包含 "_" 的 URL，例如拦截 user_add。
	base.org.lzn.web.action.{1}Action 即是 base.org.lzn.web.action.{1}Action
	method="{1}-{2}" 即是 method="user-add"
	/{1}{2}.jsp 即是 useradd.jsp
-->
<action name="*_*" class="base.org.lzn.web.action.{1}Action" method="{1}-{2}">
    <result name="success">/{1}{2}.jsp</result>
</action>
```

### 动态动作方法调用

不建议使用，报错时前端会显示。

需要配置常量开启。

```xml
<constant name="struts.enable.DynamicMethodInvocation" value="true" />
```

配置 action

```xml
<action name="hello" class="base.org.lzn.web.action.UserAction" method="addUser">
    <result name="success">/success.jsp</result>
</action>
```

前端使用，**动作名称!动作方法.action** 或者 **动作名称!动作方法**

```jsp
<a href="${pageContext.request.contextPath}/user!addUser.do">动态动作方法 user!addUser.do</a><br>
<a href="${pageContext.request.contextPath}/p2/user!updateUser.do">动态动作方法 user!updateUser</a><br>
```

## result 元素

| 属性 | 说明                                                         |
| ---- | ------------------------------------------------------------ |
| name | 结果视图名称。与动作方法的返回值对应视图                     |
| type | 结果视图类型。<br>常用值有：<br>    dispatcher：请求转发，默认值<br>    redirect：重定向<br/>    chain：转发到另一个动作<br/>        转发向到同包（同名称空间）下的另一个动作<br/>        转发向到不同包（不同名称空间）下的另一个动作<br>    redirectAction：重定向到另一个动作<br/>        重定向到同包（同名称空间）下的另一个动作<br/>        重定向到不同包（不同名称空间）下的另一个动作 |

### 例子 - 动作方法的转发和重定向

以下例子，在 [主页](web/welcome.jsp) 链接中访问测试。配置：[struts.xml](src/main/resources/struts.xml)，[HelloAction.java](src/main/java/base/org/lzn/web/action/HelloAction.java)

* **同包** 间的 **转发**

  ```xml
  <action name="chain1" class="base.org.lzn.web.action.HelloAction" method="sayBye">
      <result name="bye" type="chain">hello</result>
  </action>
  <!--将转发到下方动作-->
  <action name="hello" class="base.org.lzn.web.action.HelloAction" method="sayHello">
      <result name="success">/success.jsp</result>
  </action>
  ```

* **同包** 间的 **重定向**

  ```xml
  <action name="redirectAction1" class="base.org.lzn.web.action.HelloAction" method="sayBye">
      <result name="bye" type="redirectAction">hello</result>
  </action>
  <!--将重定向到下方动作-->
  <action name="hello" class="base.org.lzn.web.action.HelloAction" method="sayHello">
      <result name="success">/success.jsp</result>
  </action>
  ```

* **不同包** 间的 **转发**

  ```xml
  <package name="p1" extends="struts-default">
      <action name="chain2" class="base.org.lzn.web.action.HelloAction" method="sayBye">
          <result name="bye" type="chain">
              <param name="namespace">p2</param>
              <param name="actionName">hello</param>
          </result>
      </action>
  </package>
  <!--转发到下方动作-->
  <package name="p2" extends="struts-default" namespace="/p2">
      <action name="hello" class="base.org.lzn.web.action.HelloAction" method="sayHello">
          <result name="success">/success.jsp</result>
      </action>
  </package>
  ```

* **不同包** 间的 **重定向**

  ```xml
  <package name="p1" extends="struts-default">
      <action name="redirectAction2" class="base.org.lzn.web.action.HelloAction" method="sayBye">
          <result name="bye" type="redirectAction">
              <param name="namespace">p2</param>
              <param name="actionName">hello</param>
          </result>
      </action>
  </package>
  <!--重定向到下方动作-->
  <package name="p2" extends="struts-default" namespace="/p2">
      <action name="hello" class="base.org.lzn.web.action.HelloAction" method="sayHello">
          <result name="success">/success.jsp</result>
      </action>
  </package>
  ```

## 获取 ServletApi

参考：[Demo2Action.java](src/main/java/base/org/lzn/web/action/Demo2Action.java)

* 使用 `ServletActionContext` 对象获取（推荐）。

  ```java
  HttpServletRequest request = ServletActionContext.getRequest();
  HttpServletResponse response = ServletActionContext.getResponse();
  ServletContext application = ServletActionContext.getServletContext();
  ```

* 使用通过依赖注入形式，需实现 ServletRequestAware, ServletResponseAware, ServletContextAware 接口

  ```java
  public class Demo2Action implements ServletRequestAware, ServletResponseAware, ServletContextAware {
      private HttpServletRequest req;
      private HttpServletResponse resp;
      private ServletContext app;
  
      @Override
      public void setServletRequest(HttpServletRequest request) {
          this.req = request;
      }
  
      @Override
      public void setServletResponse(HttpServletResponse response) {
          this.resp = response;
      }
  
      @Override
      public void setServletContext(ServletContext context) {
          this.app = context;
      }
  }
  ```
