# struts2 基础4

## ActionContext

可以通过 [主页](web/welcome.jsp) 查看下面的例子。

* 在动作类 [Demo1Action](src/main/java/org/lzn/action/Demo1Action.java) 中演示给 `ActionContext` 中对象赋值和观察对象。

    ```java
    public class Demo1Action extends ActionSupport {
        @Override
        public String execute() {
            // 1. 得到 ActionContext 对象
            // 这是从当前线程的局部变量中获取引用
            ActionContext context = ActionContext.getContext();
            // 2. 往 map 中存入数据，数据直接存到大 map 中
            context.put("contextMap", "hello contextMap");
            // 往 session 中存数据
            // 第一种方式：获取 key 为 session 的 map。得到 key 值为 session 的小 map
            Map<String, Object> session = context.getSession();
            session.put("sessionMap", "hello sessionMap");
            // 第二种方式：直接使用原始的 HttpSession 对象
            HttpSession session2 = ServletActionContext.getRequest().getSession();
            session.put("sessionMap2", "hello sessionMap2");
            // 往 ServletContext 域中存数据
            // 第一种方式：获取 key 值为 application 的 map
            Map<String, Object> application = context.getApplication();
            application.put("applicationMap", "hello applicationMap");
            // 第二种方式：获取原始的 ServletContext 对象
            ServletContext application2 = ServletActionContext.getServletContext();
            application2.setAttribute("applicationMap2", "hello applicationMap2");
            return SUCCESS;
        }
    }
    ```
    
* 在 [struts.xml](src/main/resources/struts.xml) 中配置 `action`

    ```xml
    <package name="p1" extends="struts-default">
        <action name="action1" class="org.lzn.action.Demo1Action">
            <result>/demo1.jsp</result>
        </action>
    </package>
    ```

* 在 [demo1.jsp](web/demo1.jsp) 中点击 **[Debug]**，查看对象信息。

### 通过标签 s:property 获取 ActionContext  对象

使用 s:property 来获取 ActionContext 中的数据，value 属性的取值是一个 OGNL 表达式。

需引入 `struts` 标签库

```jsp
<%@ taglib prefix="s" uri="/struts-tags" %>
```

* 获取大 Map 中的数据，取的时候是 #key 名称

  ```jsp
  <s:property value="#contextMap"/>
  ```

* 获取大 Map 中小 Map 的数据，使用 #大Map 的 key，小 Map 的 key

  ```jsp
  <s:property value="#session.sessionMap"/>
  ```

## ValueStack

可以通过 [主页](web/welcome.jsp) 查看下面的例子。

### 获取 `ValueStack`，[Demo2Action](src/main/java/org/lzn/action/Demo2Action)

```jsp
public class Demo2Action extends ActionSupport {
    @Override
    public String execute() {
        // 获取 ValueStack 的引用
        // 第一种方式：获取 HttpServletRequest 对象，通过对象的 getAttribute 方法，从域中取
        // 1. 获取 request 对象
        HttpServletRequest request = ServletActionContext.getRequest();
        // 2. 根据 key 获取 ValueStack 对象的引用
        ValueStack valueStack = (ValueStack) request.getAttribute("struts.valueStack");
        System.out.println(valueStack.hashCode());
        // 第二种方式：先获取 ActionContext 对象，在取出 requestMap，然后通过 map 的 get 方法获取
        // 1. 获取 ActionContext 对象
        ActionContext context = ActionContext.getContext();
        // 2. 获取 requestMap
        Map<String, Object> request2 = (Map<String, Object>) context.get("request");
        // 3. 根据 key 获取对象的引用
        ValueStack valueStack2 = (ValueStack) request2.get("struts.valueStack");
        System.out.println(valueStack2.hashCode());
        // 第三种方式：使用 ActionContext 对象方法，直接获取 ValueStack 对象的引用
        ValueStack valueStack3 = context.getValueStack();
        System.out.println(valueStack3.hashCode());
        return SUCCESS;
    }
}
```

### 操作 `ValueStack`，[Demo2Action](src/main/java/org/lzn/action/Demo2Action) 的 `operate()` 方法

使用 `setValue(String expr, Object value)` 方法对 `ValueStack` 进行操作。**expr** : 是一个 `OGNL` 表达式，**value** : 我们要操作的数据。`expr` 使用 #，则把数据存到 `ContextMap`，否则存到 `ValueStack`。

* 数据存在 ContextMap 中。key 是 name，value 是 张三

  ```java
  valueStack.setValue("#name", "张三");
  ```

* 数据存在 ValueStack 中，第一个 name 属性的值换成 李四。如果没有一个 name 属性对应的 setName 方法则会报错。

  ```java
  valueStack.setValue("name", "李四");
  ```

使用 `set(String key, Object o)` 方法对 `ValueStack` 进行操作。**key** : `Map` 的 `key`，**o** : `Map` 的 `Value`。如果栈顶是一个 `Map` 元素，直接把 `key` 作为 `map` 的 `key`，把 `Object` 作为 `map` 的 `value` 存放，存入的栈顶。如果栈顶不是一个 `Map` 元素，创建一个 `Map` 对象，把 `key` 作为 `map` 的 `key`，把 `Object` 作为 `map` 的 `value`，压入栈内。

### jsp 通过标签 s:property 获取 ValueStack 数据

使用 `s:property` 来获取 `ValueStack` 中的数据，`value` 属性的取值是一个 `OGNL` 表达式，它只能获取元素中的属性。注意：取 `ValueStack` 中的对象属性时，不使用 #，从栈顶逐个对象查找指定属性名称。只要找到了，就不在继续查找。

引入 `struts` 标签库

```jsp
<%@ taglib prefix="s" uri="/struts-tags" %>
```

获取 `ValueStack` 数据

```jsp
<s:property value="name"/>
```

获取 `ValueStack` 中指定位置的属性，使用的是 [索引].属性名称。没有则往下找，都没有则找 contextMap，[查找顺序](#findOrder)。

```jsp
<s:property value="[1].name"/>
```

获取栈顶 `Map` 中的元素

```jsp
<s:property value="s1.name"/>
```

如果 `s:property` 标签什么属性都不写，默认取栈顶数据。

```jsp
<s:property/>
```

以上的原理：全是 `ValueStack` 的 `findValue` 和 `findString`

```jsp
<%
    ValueStack valueStack = ActionContext.getContext().getValueStack();
    String name = valueStack.findString("name");
    out.print("name<br>");
    out.print(name);
    String name1 = valueStack.findString("[1].name");
    out.print("<br>[1].name<br>");
    out.print(name1);
%>
```

### <a name="findOrder" style="text-decoration:none">Struts2 中 EL 的查找顺序</a>

**EL 表达式(原来的)**

1. page
2. request
3. session
4. application

**OGNL 表达式**

1. page
2. request
3. valueStack
4. contextMap
5. session
6. application

## 常用标签

### iterator

参考 [demo3](web/demo3.jsp)，`begin, end, step` 和 `jstl` 的 `forEach` 标签是一样的。

**属性**

* value : 要遍历的集合，是 `OGNL` 表达式

* var : 字符串

  写了该属性：把 `var` 的值作为 `key`，把当前遍历的元素作为 `value`。存到 `ActionContext` 这个大 `Map` 中
  不写该属性：把当前遍历的元素压入栈顶

* status : 遍历时的一些计数信息

  * int getIndex(): 从 0 开始
  * int getCount(): 从 1 开始
  * boolean isFirst(): 是否第一个
  * boolean isLast(): 是否最后一个
  * boolean isOdd(): 是否奇数
  * boolean isEven(): 是否偶数

### OGNL 投影

参考 [demo3](web/demo3.jsp)

* ?# : 过滤所有符合条件的集合，如：users.{?#this.age > 19}
* ^# : 过滤第一个符合条件的集合，如：users.{^#this.age > 19}
* $# : 过滤最后一个符合条件的集合，如：users.{$#this.age > 19}

### set

参考 [demo4](web/demo4.jsp)，把 value 属性的只作为 value，把 var 属性的值作为 key，存到 ActionContext 大 Map 中

* var : 字符串
* value : 取值是一个 OGNL 表达式

### action

参考 [demo4](web/demo4.jsp)

* name : 指定一个 action 动作名称。
* executeResult : 是否执行 action。默认值是 false。

### url

参考 [demo4](web/demo4.jsp)

* value : 是把直接输出到页面上
* action : 是把动作的请求地址输出到页面上 ${pageContext.request.contextPath}/action1
* var : 把 action 的取值作为 value，把 var 的取值作为 key，放到 ActionContext 中

## token

防止表单重复提交。以下例子可以通过 [主页](web/welcome.jsp) 查看。

### token 标签

参考 [token1](web/token1.jsp)

```jsp
<%@ taglib prefix="s" uri="/struts-tags" %>
<%--使用 struts2 的内置标签 s:token--%>
<s:token />
```

### 方式一

使用这个方式，当 `token` 校验到重复提交后会返回 `invalid.token`。相关代码 [token1.jsp](web/token1.jsp)，[struts.xml](src/main/resources/struts.xml)，[Token1Action.java](src/main/java/org/lzn/action/Token1Action.java)，[success.jsp](web/success.jsp)，[message.jsp](web/message.jsp)

参考 [token1.jsp](web/token1.jsp)

```jsp
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:form action="token1">
    <%--使用 struts2 的内置标签 s:token--%>
    <s:token />
    <s:textfield name="name" label="用户名"/>
    <s:submit value="提交"/>
</s:form>
```

action 配置，参考 [struts.xml](src/main/resources/struts.xml)

```xml
<package name="p2" extends="struts-default">
    <action name="token1" class="org.lzn.action.Token1Action" method="login">
        <!--由于默认的拦截器栈中没有令牌的拦截器，所以需要把默认的拦截器栈配上-->
        <interceptor-ref name="defaultStack" />
        <!--使用 struts2 提供的一个令牌的拦截器：token-->
        <interceptor-ref name="token" />
        <result type="redirect">/success.jsp</result>
        <result name="invalid.token">/message.jsp</result>
    </action>
</package>
```

方式二

使用这个方式，当 `token` 校验到重复提交后不会执行动作，直接响应结果视图。相关代码 [token2.jsp](web/token2.jsp)，[struts.xml](src/main/resources/struts.xml)，[Token2Action.java](src/main/java/org/lzn/action/Token2Action.java)，[success.jsp](web/success.jsp)。

参考 [token2.jsp](web/token2.jsp)

```jsp
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:form action="token2">
    <%--使用 struts2 的内置标签 s:token--%>
    <s:token />
    <s:textfield name="name" label="用户名"/>
    <s:submit value="提交"/>
</s:form>
```

action 配置，参考 [struts.xml](src/main/resources/struts.xml)

```xml
<package name="p2" extends="struts-default">
    <action name="token2" class="org.lzn.action.Token2Action" method="login">
        <!--由于默认的拦截器栈中没有 tokenSession 的拦截器，所以需要把默认的拦截器栈配上-->
        <interceptor-ref name="defaultStack" />
        <!--使用 struts2 提供一个 tokenSession 的拦截器：token-->
        <interceptor-ref name="tokenSession" />
        <result type="redirect">/success.jsp</result>
    </action>
</package>
```



