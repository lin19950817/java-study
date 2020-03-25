# Listener 监听器

`javaweb` 开发中的监听器，用于监听 `web` 常见对象 `HttpServletRequest`, `HttpSession`, `ServletContext`。监听他们的创建于销毁，属性变化。`session` 绑定 `javaBean`。

## 监听机制

* **事件**

  就是一个事情

* **事件源**

  产生这个事情的源头

* **监听器**

  用于监听指定的事件的对象

* **注册监听**

  要想让监听器可以坚挺到事件产生，必须对其进行注册

## <a name="commonListener" style="text-decoration:none" >**Javaweb** 开发中常见监听器</a>

* ### 监听域对象的创建与销毁

  * [ServletContextListener](src/main/java/org/lzn/listener/MyServletContextListener.java)

    监听 `ServletContext` 创建于销毁

  * [HttpSessionListener](src/main/java/org/lzn/listener/MyHttpSessionListener.java)

    监听 `HttpSession` 创建于销毁。

  * [ServletRequestListener](src/main/java/org/lzn/listener/MyServletRequestListener.java)

    监听 `HttpServletRequest` 创建与销毁

* ### 监听域对象的属性变化

  * `ServletContextAttributeListener`

    监听 `ServletContext` 属性变化

  * `HttpSessionAttributeListener`

    监听 `HttpSession` 属性变化

  * [ServletRequestAttributeListener](src/main/java/org/lzn/attributeListener/MyServletRequestAttributeListener.java)

    监听 `HttpServletRequest` 属性变化

* ### 监听  `session` 绑定 `javaBean`

  * [HttpSessionBindingListener](src/main/java/org/lzn/domain/User.java)

    用于监听 `javaBean` 对象是否绑定到了 `session` 域。不用在 `web.xml` 注册监听

  * [HttpSessionActivationListener](src/main/java/org/lzn/domain/Role.java)

    用于监听 `javaBean` 对象的活化与钝化。需要实现 `Serializable` 接口。
    
    当我们正常关闭服务器时，`session` 中的 `javaBean` 对象就会被钝化到我们指定的文件中。
    
    当下一次在启动服务器，因为我们已经将对象写入到文件中，这时就会自动将 `javaBean` 对象活化到`session` 中。
    
    我们还需要个 [context.xml](web/META-INF/context.xml) 文件来配置钝化时存储的文件。

## 监听器快速入门

1. 创建一个类，实现指定的 [监听器接口](#commonListener)。

   [源码 MyServletContextListener](src/main/java/org/lzn/listener/MyServletContextListener.java)

   ```java
   public class MyServletContextListener implements ServletContextListener {
   }
   ```

2. 重写接口中的方法

   [源码 MyServletContextListener](src/main/java/org/lzn/listener/MyServletContextListener.java)

   ```java
   public class MyServletContextListener implements ServletContextListener {
       public void contextInitialized(ServletContextEvent sce) {
           System.out.println("ServletContext 对象创建了");
       }
   
       public void contextDestroyed(ServletContextEvent sce) {
           System.out.println("ServletContext 对象销毁了");
       }
   }
   ```

3. 在 `web.xml` 文件中对监听器进行注册

   [源码 web.xml](web/WEB-INF/web.xml)

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
            version="4.0">
   
       <!--注册监听器-->
       <listener>
           <listener-class>org.lzn.listener.MyServletContextListener</listener-class>
       </listener>
   </web-app>
   ```


## 案例

* [定时销毁 `session`](../timely-destruction-case)

