# spring 基础1

## spring API

### BeanFactory

这是一个工厂，用于生成任意bean。采取延迟加载，第一次getBean时才会初始化Bean。

### ApplicationContext

是BeanFactory的子接口，功能更强大。（国际化处理、事件传递、Bean自动装配、各种不同应用层的Context实现）。当配置文件被加载，就进行对象实例化。

### 演示

代码 [UserService.java](src/main/java/org/lzn/service/UserService.java)，[UserServiceImpl.java](src/main/java/org/lzn/service/impl/UserServiceImpl.java)，[Context.java](src/main/java/org/lzn/Context.java)，[bean.xml](src/main/resources/bean.xml)

bean.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--创建 service-->
    <bean id="userService" class="org.lzn.service.impl.UserServiceImpl"/>
</beans>
```

UserServiceImpl

```java
public class UserServiceImpl implements UserService {
    @Override
    public void addUser() {
        System.out.println("UserServiceImpl.addUser");
    }
    public UserServiceImpl() {
        // 用于观察 bean 是否实例化
        System.out.println("UserServiceImpl 实例化");
    }
}
```

使用 ApplicationContext 实现类，当 ClassPathXmlApplicationContext 实例化时，bean 也实例化。

```java
public class Context {
    public void demo1() {
        // 使用 ClassClassPathXmlApplicationContext
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.addUser();
    }
}

```

使用 BeanFactory，当调用 beanFactory.getBean 时，bean 才实例化。

```java
public class Context {
    public void demo2() {
        // 使用 BeanFactory
        Resource resource = new ClassPathResource("bean.xml");
        BeanFactory beanFactory = new XmlBeanFactory(resource);
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.addUser();
    }
}
```

## 装配 Bean 基于 XML

### 实例化方式

* [默认构造](#default)
* [静态工厂](#staticFactory)
* [实例工厂](#factory)

#### <a name="default" style="text-decoration:none">默认构造</a>

配置文件配置 `<bean id="" class=""/>`，必须提供默认构造。

#### <a name="staticFactory" style="text-decoration:none">静态工厂</a>

常用与spring整合其他框架（工具）。

用于生成实例对象，所有的方法必须是 static。`<bean id=""  class="工厂全限定类名"  factory-method="静态方法">`

```java
public class MyBeanFactory {
    public static UserService createUserService() {
        return new UserServiceImpl();
    }
}
```

配置

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--
        静态工厂创建实例
            class: 确定静态工厂全限定类名
            factory-method: 确定静态方法名
    -->
    <bean id="staticFactory" class="org.lzn.static_factory.MyBeanFactory" factory-method="createUserService"/>

</beans>
```

**代码**

[MyBeanFactory.java](src/main/java/org/lzn/static_factory/StaticFactory.java)，配置 [bean.xml](src/main/resources/bean.xml)，演示 [StaticFactory.java](src/main/java/org/lzn/static_factory/StaticFactory.java)

#### <a name="factory" style="text-decoration:none">实例工厂</a>

必须先有工厂实例对象，通过实例对象创建对象。提供所有的方法都是“非静态”的。

```java
public class MyFactory {
    public UserService createUserService() {
        return new UserServiceImpl();
    }
}
```

配置

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--创建工厂实例-->
    <bean id="factory" class="org.lzn.factory.MyFactory"/>
    <!--
        通过工厂实例获得 userService
            factory-bean: 确定工厂实例
            factory-method: 确定普通方法
    -->
    <bean id="userServiceByFactory" factory-bean="factory" factory-method="createUserService"/>
</beans>
```

**代码**

[MyFactory.java](src/main/java/org/lzn/factory/MyFactory.java)，配置 [bean.xml](src/main/resources/bean.xml)，演示 [Factory.java](src/main/java/org/lzn/factory/Factory.java)

### Bean 种类

* 普通 bean

  之前操作的都是普通 bean。`<bean id="" class="A"> `，spring 直接创建 A 实例，并返回。

* FactoryBean

  是一个特殊的bean，具有工厂生成对象能力，只能生成特定的对象。

  bean 必须使用 FactoryBean 接口，此接口提供方法 `getObject()` 用于获得特定 bean。

  `<bean id="" class="FB">` 先创建 FB 实例，使用调用 `getObject()` 方法，并返回方法的返回值。

* BeanFactory

  工厂，用于生成任意bean。

### <a name="scope" style="text-decoration:none">作用域</a>

| 类别          | 说明                                                         |
| ------------- | ------------------------------------------------------------ |
| singleton     | 在 Spring IOC 容器中仅存在一个 Bean 实例，Bean 以单例方式存在，默认值。 |
| prototype     | 每次从容器中调用 Bean 时，都返回一个新的实例，即每次调用 `getBean()` 时，相当于执行 `new XxBean()`。 |
| request       | 每次 HTTP 请求都会创建一个新的 Bean，该作用域仅适用于 WebApplicationContext 环境。 |
| session       | 同一个 HTTP Session 共享一个 Bean，不同 Session 适用不同 Bean，仅适用于 WebApplicationContext 环境。 |
| globalSession | 一般用于 Portlet 应用环境，该做用于仅适用于 WebApplicationContext 环境。 |

配置信息

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--单例 bean，默认值-->
    <bean id="single" class="org.lzn.scope.impl.SingleBeanScopeImpl"/>
    <!--多例 bean-->
    <bean id="prototype" class="org.lzn.scope.impl.PrototypeBeanScopeImpl" scope="prototype"/>
</beans>
```

BeanScope

```java
public interface BeanScope {
    
}
```

PrototypeBeanScopeImpl

```java
public class PrototypeBeanScopeImpl implements BeanScope {
    public PrototypeBeanScopeImpl() {
        System.out.println("PrototypeBeanScopeImpl 实例化了");
    }
}
```

SingleBeanScopeImpl

```java
public class SingleBeanScopeImpl implements BeanScope {
    public SingleBeanScopeImpl() {
        System.out.println("SingleBeanScopeImpl 实例化了");
    }
}
```

演示

```java
public class Scope {
    public void demo1() {
        // bean 单例
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        System.out.println(applicationContext.getBean("single"));
        // 第二次 getBean 是否跟第一个 bean 是同一个
        System.out.println(applicationContext.getBean("single"));
    }

    public void demo2() {
        // bean 多例
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        System.out.println(applicationContext.getBean("prototype"));
        // 第二次 getBean 是否跟第一个 bean 是同一个
        System.out.println(applicationContext.getBean("prototype"));
    }
}
```

**代码**

[BeanScope.java](src/main/java/org/lzn/scope/BeanScope.java)，[SingleBeanScopeImpl.java](src/main/java/org/lzn/scope/impl/SingleBeanScopeImpl.java)，[PrototypeBeanScopeImpl.java](src/main/java/org/lzn/scope/impl/PrototypeBeanScopeImpl.java)，配置 [bean.xml](src/main/resources/bean.xml)，演示 [Scope.java](src/main/java/org/lzn/scope/Scope.java)

### <a name="lifecycle" style="text-decoration:none">生命周期</a>

#### 初始化和销毁

BeanLifecycle

```java
public interface BeanLifecycle {
    void lifecycle();
}
```

BeanLifecycleImpl

```java
public class BeanLifecycleImpl implements BeanLifecycle {
    @Override
    public void lifecycle() {
        System.out.println("BeanLifecycleImpl.lifecycle() 执行了");
    }

    public void myInit() {
        System.out.println("BeanLifecycleImpl.myInit()");
    }

    public void myDestroy() {
        System.out.println("BeanLifecycleImpl.myDestroy()");
    }
}
```

配置

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--
        生命周期
            init-method：用于配置初始化方法
            destroy-method：用于；配置销毁方法
    -->
    <bean id="lifecycle" class="org.lzn.lifecycle.impl.BeanLifecycleImpl" init-method="myInit" destroy-method="myDestroy"/>
</beans>
```

演示

```java
public class Lifecycle {
    public void demo1() throws Exception{
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        BeanLifecycle beanLifecycle = (BeanLifecycle) applicationContext.getBean("lifecycle");
        beanLifecycle.lifecycle();

        // 容器 close，销毁方法执行。反射调用 ClassPathXmlApplicationContext 的 close
        applicationContext.getClass().getMethod("close").invoke(applicationContext);
    }
}
```

**代码**

[BeanLifecycle.java](src/main/java/org/lzn/lifecycle/BeanLifecycle.java)，[BeanLifecycleImpl.java](src/main/java/org/lzn/lifecycle/impl/BeanLifecycleimpl.java)，配置 [bean.xml](src/main/resources/bean.xml)，演示 [Lifecycle.java](src/main/java/org/lzn/lifecycle/Lifecycle.java)

### BeanPostProcessor 后处理 Bean

spring 提供一种机制，只用实现接口 BeanPostProcessor，并将实现类提供给 spring 容器，spring 容器将自动执行。在 bean 初始化前执行 `before()`，在初始化后执行 `after()`。

代码 [MyBeanPostProcessor.java](src/main/java/org/lzn/post_processor/MyBeanPostProcessor.java)

```java
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("MyBeanPostProcessor：bean 初始化之前，" + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("MyBeanPostProcessor：bean 初始化之后，" + beanName);
        return bean;
    }
}
```

配置 [bean.xml](src/main/resources/bean.xml)

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--BeanPostProcessor 实现类注册给 spring-->
    <bean class="org.lzn.post_processor.MyBeanPostProcessor"/>
</beans>
```

### 属性依赖注入

* 依赖注入方式：**手动装配** 和 **自动装配**
* 手动装配：一般进行配置信息都采用手动
  * 基于 **XML** 装配：构造方法、setter 方法
  * 基于 **注解** 装配
* 自动装配：struts 和 spring 整合可以自动装配
  * byType：按类型装配
  * byName：按名称装配
  * constructor：装配
  * auto：不确定装配

#### 构造方法注入

通过 **构造方法** 注入，**代码** [User.java](src/main/java/org/lzn/xml/construct/User.java)，配置 [bean.xml](src/main/resources/bean.xml)，演示 [Construct.java](src/main/java/org/lzn/xml/construct/Construct.java)

使用 name 和 value

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--
        属性注入，构造方法
        constructor-arg：用于配置构造方法一个参数 argument
            name：参数的名称
            value：设置普通数据
                例如：
                    <constructor-arg name="username" value="hehe"/>
                    <constructor-arg name="age" value="18"/>
    -->
    <bean id="user" class="org.lzn.xml.construct.User">
    	<constructor-arg name="username" value="hehe"/>
        <constructor-arg name="age" value="18"/>
    </bean>
</beans>
```

使用 index，默认会使用 **第一个构造方法**

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--
        属性注入，构造方法
        constructor-arg：用于配置构造方法一个参数 argument
            index：参数的索引号，从 0 开始。如果只有索引，默认使用第一个构造方法
    -->
    <bean id="user" class="org.lzn.xml.construct.User">
        <constructor-arg index="0" value="1"/>
        <constructor-arg index="1" value="2"/>
    </bean>
</beans>
```

使用 index 和 type

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--
        属性注入，构造方法
        constructor-arg：用于配置构造方法一个参数 argument
            index：参数的索引号，从 0 开始。如果只有索引，默认使用第一个构造方法
            type：确定参数类型 和 index 一起使用
    -->
    <bean id="user" class="org.lzn.xml.construct.User">
        <constructor-arg index="0" type="java.lang.Integer" value="1"/>
        <constructor-arg index="1" type="java.lang.String" value="2"/>
    </bean>
</beans>
```

### 属性方法注入

通过 **属性** 注入，就是通过 **setter** 方法注入。**代码** [Person.java](src/main/java/org/lzn/xml/property/Person.java)，[Address.java](src/main/java/org/lzn/xml/property/Address.java)，配置 [bean.xml](src/main/resources/bean.xml)，演示 [Property.java](src/main/java/org/lzn/xml/property/Property.java)

普通数据

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--
        setter 属性注入
            普通数据
                <property name="name" value="hehe"/>
                等同
                <property name="name">
                    <value>hehe</value>
                </property>
    -->
    <bean id="person" class="org.lzn.xml.property.Person">
        <property name="name" value="hehe"/>
        <property name="age">
            <value>18</value>
        </property>
    </bean>
</beans>
```

引用数据

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--
        setter 属性注入
            引用类型
                <property name="companyAddress" ref="companyAddress"/>
                等同
                <property name="companyAddress">
                    <ref bean="companyAddress"/>
                </property>

    -->
    <bean id="person" class="org.lzn.xml.property.Person">
        <!--普通类型-->
        <property name="name" value="hehe"/>
        <property name="age">
            <value>18</value>
        </property>

        <!--应用类型-->
        <property name="companyAddress" ref="companyAddress"/>
        <property name="homeAddress">
            <ref bean="homeAddress"/>
        </property>
    </bean>
    <bean id="homeAddress" class="org.lzn.xml.property.Address">
        <property name="detailAddress" value="北上广深123"/>
        <property name="telephone" value="119"/>
    </bean>
    <bean id="companyAddress" class="org.lzn.xml.property.Address">
        <property name="detailAddress" value="北上广深abc"/>
        <property name="telephone" value="120"/>
    </bean>
</beans>
```

### P 命名空间

* 对 setter 方法注入进行简化，替换 \<property\>，而是在 \<bean p:属性名="普通值" p:属性名-ref="引用值"\>
* p 命名空间使用前提，必须添加命名空间

**代码** [Person.java](src/main/java/org/lzn/xml/property/Person.java)，[Address.java](src/main/java/org/lzn/xml/property/Address.java)，配置 [bean.xml](src/main/resources/bean.xml)，演示 [P.java](src/main/java/org/lzn/xml/p/P.java)

需要添加约束 `xmlns:p="http://www.springframework.org/schema/p"`

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="homeAddress" class="org.lzn.xml.property.Address">
        <property name="detailAddress" value="北上广深123"/>
        <property name="telephone" value="119"/>
    </bean>
    <bean id="companyAddress" class="org.lzn.xml.property.Address">
        <property name="detailAddress" value="北上广深abc"/>
        <property name="telephone" value="120"/>
    </bean>

    <!--p 命名空间-->
    <bean id="p_person" class="org.lzn.xml.property.Person"
          p:name="p 命名空间" p:age="18"
          p:homeAddress-ref="homeAddress" p:companyAddress-ref="companyAddress"/>
</beans>
```

### SpEL

* 对 \<property\> 进行统一编码，所有的内容都是用 value

  \<property name="" value="#{表达式}" \> 

| 含义           | 例子                               |
| -------------- | ---------------------------------- |
| 常量           | #{10}，#{3.14}，#{2e5}，#{'string} |
| 引用 Bean      | #{beanId}                          |
| 引用 Bean 属性 | #{beanId.propertyName}             |
| 引用 Bean 方法 | #{beanId.methodName()}             |
| 静态方法或字段 | #{T(类).字段\|方法}                |
| 运算符支持     | #{3 It 4 == 4 ge 3}                |
| 正则表达式支持 | #{user.name matches '[a-z]{6,}'}   |
| 集合支持       | #{likes[3]}                        |

### 集合注入

集合类型注入。**代码** [CollectionData.java](src/main/java/org/lzn/xml/collection/CollectionData.java)，配置 [bean.xml](src/main/resources/bean.xml)，演示 [Collection.java](src/main/java/org/lzn/xml/collection/Collection.java)

CollectionData

```java
public class CollectionData {
    private String[] arrayData;
    private List<String> listData;
    private Set<String> setData;
    private Map<String, String> mapData;
    private Properties propertiesData;

    //
    // setter/getter 省略
    // ------------------------------------------------------------------------------
}
```

bean.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--
        集合注入：给 <property> 添加子标签
            数组：<array>
            List：<list>
            Set：<set>
            Map：<map>
            Properties：<props>，底层是 Map<String, String>
        普通数据：<value>
        引用数据：<ref>
    -->
    <bean id="collectionData" class="org.lzn.xml.collection.CollectionData">
        <property name="arrayData">
            <array>
                <value>array1</value>
                <value>array2</value>
                <value>array3</value>
            </array>
        </property>

        <property name="listData">
            <list>
                <value>list1</value>
                <value>list2</value>
                <value>list3</value>
            </list>
        </property>

        <property name="setData">
            <set>
                <value>set1</value>
                <value>set2</value>
                <value>set3</value>
            </set>
        </property>

        <property name="mapData">
            <map>
                <entry key="key1" value="value1"/>
                <entry>
                    <key><value>key2</value></key>
                    <value>value2</value>
                </entry>
            </map>
        </property>
        
        <property name="propertiesData">
            <props>
                <prop key="propsKey1">propsValue1</prop>
                <prop key="propsKey2">propsValue2</prop>
                <prop key="propsKey3">propsValue3</prop>
            </props>
        </property>
    </bean>
</beans>
```

## 基于 **注解** 注入

* 注解：就是一个类，使用 @ 注解名称
* 开发中：使用 **注解** 取代 xml 配置文件

需要添加 **命名空间** 和 **约束**

```xml
<beans xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/context
                           http://www.springframework.org/schema/context/spring-context.xsd">
```

开启扫描

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context
                           http://www.springframework.org/schema/context/spring-context.xsd">
    <!--组件扫描，扫描含有注解的类-->
    <context:component-scan base-package="org.lzn"/>
</beans>
```

* @Component 取代 `<bean class="">`

  @Component("id") 取代 `<bean id="" class="">`

  [LifecycleAndScope.java](src/main/java/org/lzn/xml/annotation/lifecycle_scope/LifecycleAndScope.java)

* web 开发，提供 3 个 @Compnonent 注解衍生注解（功能一样）取代 `<bean class="">`

  * @Repository : dao 层。[StudentDaoImpl.java](src/main/java/org/lzn/xml/annotation/dao/impl/StudentDaoImpl.java)
  * @Service : service 层。[StudentServiceImpl.java](src/main/java/org/lzn/xml/annotation/service/impl/StudentServiceImpl.java)
  * @Controller : seb 层。[StudentAction.java](src/main/java/org/lzn/xml/annotation/action/StudentAction.java)

* 依赖注入，给私有字段设置，也可以给 setter 方法设置

  * 普通值：@Value("")

  * 引用值：

    * 方式 1：按照 **类型** 注入

      @Autowired

    * 方式 2：按照 **名称** 注入

      @Autowired

      @Qualifer("名称")

      [StudentServiceImpl.java](src/main/java/org/lzn/xml/annotation/service/impl/StudentServiceImpl.java)

    * 方式 3：按照 **名称** 注入

      @Resource("名称")

* [声明周期](#lifecycle)

  [LifecycleAndScope.java](src/main/java/org/lzn/xml/annotation/lifecycle_scope/LifecycleAndScope.java)

  * 初始化：@PostContruct
  * 销毁：@PreDestroy

* [作用域](#scope)

  [LifecycleAndScope.java](src/main/java/org/lzn/xml/annotation/lifecycle_scope/LifecycleAndScope.java)

  @Scope("prototype")，多例

**代码**

action : [StudentAction.java](src/main/java/org/lzn/xml/annotation/action/StudentAction.java)

service : [StudentService.java](src/main/java/org/lzn/xml/annotation/service/StudentService.java)，[StudentServiceImpl.java](src/main/java/org/lzn/xml/annotation/service/impl/StudentServiceImpl.java)

dao : [StudentDao.java](src/main/java/org/lzn/xml/annotation/dao/StudentDao.java)，[StudentDaoImpl.java](src/main/java/org/lzn/xml/annotation/dao/impl/StudentDaoImpl.java)

作用域和生命周期 : [LifecycleAndScope.java](src/main/java/org/lzn/xml/annotation/lifecycle_scope/LifecycleAndScope.java)

演示 : [Annotation.java](src/main/java/org/lzn/xml/annotation/Annotation.java)