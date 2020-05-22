# Spring 基础 2

## AOP

> AOP 为 Aspect Oriented Programming 的缩写，意为：**面向切面编程**，通过预编译方式和运行期动态代理实现程序功能的统一维护的一种技术。AOP 是 OOP（面向对象编程）的延续，是软件开发中的一个热点，也是Spring框架中的一个重要内容，是函数式编程的一种衍生范型。利用 AOP 可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的 **耦合度降低**，提高程序的 **可重用性**，同时提高了开发的效率。
>
> AOP 采取横向抽取机制，取代了传统纵向继承体系重复性代码
>
> 经典应用：事务管理、性能监视、安全检查、缓存 、日志等
>
> Spring AOP 使用纯 Java 实现，不需要专门的编译过程和类加载器，在运行期通过代理方式向目标类织入增强代码
>
> AspectJ 是一个基于 Java 语言的 AOP 框架，Spring2.0 开始，Spring AOP 引入对 Aspect 的支持，AspectJ 扩展了 Java 语言，提供了一个专门的编译器，在编译时提供横向代码的织入

### AOP 实现原理

* aop 底层将采用代理机制进行实现。
* 接口 + 实现类：spring 采用 jdk 的 **动态代理** Proxy
* 实现类：spring 采用 **cglib 字节码增强**

## JDK 动态代理

JDK 动态代理是对 **装饰者** 设计模式的简化。使用的前提：必须有接口。

1. [目标类](#jdkTargetClass)：接口 + 实现类。代码 [UserService.java](src/main/java/org/lzn/jdk/service/UserService.java)，[UserServiceImpl.java](src/main/java/org/lzn/jdk/service/impl/UserServiceImpl.java)
2. [切面类](#jdkAspectClass)：用于存通知 **MyAspect**。代码 [MyAspect.java](src/main/java/org/lzn/jdk/aop/MyAspect.java)
3. [工厂类](#jdkFactory)：编写工厂生成代理。[MyBeanFactory.java](src/main/java/org/lzn/jdk/aop/MyBeanFactory.java)
4. [测试](#jdkTest)，代码 [JdkTest.java](src/main/java/org/lzn/jdk/JdkTest.java)

### <a name="jdkTargetClass" style="text-decoration:none">目标类</a>

**接口**，代码 [UserService.java](src/main/java/org/lzn/jdk/service/UserService.java)

```java
public interface UserService {
    void addUser();
    void updateUser();
    void deleteUser();
}
```

**实现类**，代码 [UserServiceImpl.java](src/main/java/org/lzn/jdk/service/impl/UserServiceImpl.java)

```java
public class UserServiceImpl implements UserService {
    @Override
    public void addUser() {
        System.out.println(this.getClass().getSimpleName().concat("addUser()"));
    }
    @Override
    public void updateUser() {
        System.out.println(this.getClass().getSimpleName().concat("updateUser()"));
    }
    @Override
    public void deleteUser() {
        System.out.println(this.getClass().getSimpleName().concat("deleteUser()"));
    }
}
```

### <a name="jdkAspectClass" style="text-decoration:none">切面类</a>

代码 [MyAspect.java](src/main/java/org/lzn/jdk/aop/MyAspect.java)

```java
public class MyAspect {
    public void before() {
        System.out.println(this.getClass().getSimpleName().concat("before()"));
    }
    public void after() {
        System.out.println(this.getClass().getSimpleName().concat("before()"));
    }
}
```

### <a name="jdkFactory" style="text-decoration:none">工厂类</a>

使用 jdk 动态代理。使用 `java.lang.reflect.Proxy` 的方法 `Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)`，参数如下。

* loader：类加载器，动态代理。运行时创建，任何类都需要类加载器将其加载到内存。

  一般情况下使用

  * 当前类.class.getClassLoader()
  * 目标类实例.getClass().getClassLoader()

* interfaces：代理类需要实现的所有接口

  * 目标类实例.getClass().getInterfaces()。**注意：只能获得自己接口，不能获得父元素接口**
  * new Class[]{目标类.class}

* h：处理类，接口，必须进行实现类，一般采用匿名内部类

  `InvocationHandler.invoke(Object proxy, Method method, Object[] args)`，参数如下

  * proxy：代理对象
  * method：代理对象当前执行的方法的描述对象
  * args：方法实际参数

代码 [MyBeanFactory.java](src/main/java/org/lzn/jdk/aop/MyBeanFactory.java)

```java
public class MyBeanFactory {
    public static UserService createUserService() {
        // 目标类
        final UserServiceImpl userService = new UserServiceImpl();
        // 切面类
        final MyAspect myAspect = new MyAspect();
        // 代理类，将目标类（切入点）和切面类（通知）结合
        // Proxy.newProxyInstance 参数
        UserService proxyService = (UserService) Proxy.newProxyInstance(
                userService.getClass().getClassLoader(),
                userService.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    // 前方法
                    myAspect.before();
                    // 执行目标方法
                    Object invoke = method.invoke(userService, args);
                    // 后方法
                    myAspect.after();
                    return invoke;
                }
        );
        return proxyService;
    }
}
```

### <a name="jdkTest" style="text-decoration:none">测试</a>

代码 [JdkTest.java](src/main/java/org/lzn/jdk/JdkTest.java)

```java
public class JdkTest {
    public void demo1() {
        UserService userService = MyBeanFactory.createUserService();
        userService.addUser();
        userService.updateUser();
        userService.deleteUser();
    }
}
```

## cglib 字节码增强

* 没有借口，只有类

* 采用字节码增强框架 cglib，在运行时 创建目标类的子类，从而对目标类进行增强。

* 导入 jar

  * 核心：hibernate-distribution-3.6.10.Final\lib\bytecode\cglib\cglib-2.2.jar
  * 依赖：struts-2.3.15.3\apps\struts2-blank\WEB-INF\lib\asm-3.3.jar

  spring-core.jar 已经整合以上两个内容

  ```xml
  <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-core</artifactId>
      <version>4.3.13.RELEASE</version>
  </dependency>
  ```

1. [目标类](#cglibTargetClass)：实现类，没有接口。代码 [UserService.java](src/main/java/org/lzn/jdk/service/UserService.java)，[CglibService.java](src/main/java/org/lzn/cglib/CglibService.java)
2. [切面类](#cglibAspectClass)：用于存通知 **MyAspect**。代码 [CglibAspect.java](src/main/java/org/lzn/cglib/CglibAspect.java)
3. [工厂类](#cglibFactory)：编写工厂生成代理。[CglibBeanFactory.java](src/main/java/org/lzn/cglib/CglibBeanFactory.java)
4. [测试](#cglibTest)，代码 [JdkTest.java](src/main/java/org/lzn/cglib/CglibTest.java)

### <a name="cglibTargetClass" style="text-decoration:none">目标类</a>

CglibService

```java
public class CglibService {
    public void addUser() {
        System.out.println(this.getClass().getSimpleName().concat(".addUser()"));
    }
    public void updateUser() {
        System.out.println(this.getClass().getSimpleName().concat(".updateUser()"));
    }
    public void deleteUser() {
        System.out.println(this.getClass().getSimpleName().concat(".deleteUser()"));
    }
}
```

### <a name="cglibAspectClass" style="text-decoration:none">切面类</a>

CglibAspect

```java
public class CglibAspect {
    public void cglibAfter() {
        System.out.println(this.getClass().getSimpleName().concat(".cglibAfter()"));
    }
    public void cglibBefore() {
        System.out.println(this.getClass().getSimpleName().concat(".cglibBefore()"));
    }
}
```

### <a name="cglibFactory" style="text-decoration:none">工厂类</a>

CglibBeanFactory

```java
public class CglibBeanFactory {
    public static CglibService createCglibService() {
        // 目标类
        final CglibService cglibService = new CglibService();
        // 切面类
        final CglibAspect cglibAspect = new CglibAspect();

        // 代理类，采用 cglib，底层创建目标类的子类
        // 核心类
        Enhancer enhancer = new Enhancer();
        // 确定父类
        enhancer.setSuperclass(cglibService.getClass());
        // 设置毁掉函数，MethodInterceptor 接口等效 jdk InvocationHandler 接口
        enhancer.setCallback((MethodInterceptor) (obj, method, args, methodProxy) -> {
            // 前置
            cglibAspect.cglibBefore();

            // 执行目标方法
            Object invoke = method.invoke(cglibService, args);

            // 后置
            cglibAspect.cglibAfter();

            return invoke;
        });
        // 创建代理
        CglibService proxyService = (CglibService) enhancer.create();

        return proxyService;
    }
}
```

### <a name="cglibTest" style="text-decoration:none">测试</a>

CglibTest

```java
public class CglibTest {
    public void demo1() {
        CglibService cglibService = CglibBeanFactory.createCglibService();
        cglibService.addUser();
        cglibService.updateUser();
        cglibService.deleteUser();
    }
}
```

## AOP 联盟通知类型

* AOP 联盟为通知 Advice 定义了 `org.aopalliance.aop.Advice`
* Spring 按照通知 Advice 在目标类方法的连接点位置，可以分为 5 类
  * 前置通知：`org.springframework.aop.MethodBeforeAdvice`，在目标方法执行前实施增强
  * 后置通知：`org.springframework.aop.AfterReturningAdvice`，在目标方法执行后实施增强
  * 环绕通知：`org.aopalliance.intercept.MethodInterceptor`，在目标方法执行前后实施增强
  * 异常抛出通知：`org.springframework.aop.ThrowsAdvice`，在方法抛出异常后实施增强
  * 引介通知：`org.springframework.aop.IntroductionInterceptor`，在目标类中添加一些新的方法和属性

## Spring 编写代理：半自动

* 让 spring 创建代理对象，从 spring 容器中手动的获取代理对象

* 导入 jar

  ```xml
  <!--aop 联盟-->
  <dependency>
      <groupId>aopalliance</groupId>
      <artifactId>aopalliance</artifactId>
      <version>1.0</version>
  </dependency>
  <!--spring aop 实现-->
  <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-aop</artifactId>
      <version>4.3.13.RELEASE</version>
  </dependency>
  <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-beans</artifactId>
      <version>4.3.13.RELEASE</version>
  </dependency>
  <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>4.3.13.RELEASE</version>
  </dependency>
  <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-core</artifactId>
      <version>4.3.13.RELEASE</version>
  </dependency>
  <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-expression</artifactId>
      <version>4.3.13.RELEASE</version>
  </dependency>
  ```

1. [目标类](#factoryBeanTargetClass)：接口 + 实现类。代码 [FactoryBeanService.java](src/main/java/org/lzn/factory_bean/FactoryBeanService.java)，[FactoryBeanServiceImpl.java](src/main/java/org/lzn/factory_bean/FactoryBeanServiceImpl.java)
2. [切面类](#factoryBeanAspect)：实现接口 MethodInterceptor。代码 [FactoryBeanAspect.java](src/main/java/org/lzn/factory_bean/FactoryBeanAspect.java)
3. [spring 配置](#factoryBeanConfig)：代码 [bean.xml](src/main/resources/bean.xml)
4. [测试](#factoryBeanTest)：代码 [FactoryBeanTest.java](src/main/java/org/lzn/factory_bean/FactoryBeanTest.java)

### <a name="factoryBeanTargetClass" style="text-decoration:none">目标类</a>

FactoryBeanService

```java
public interface FactoryBeanService {
    void add();
    void update();
    void delete();
}
```

FactoryBeanServiceImpl

```java
public class FactoryBeanServiceImpl implements FactoryBeanService {
    @Override
    public void add() {
        System.out.println(this.getClass().getSimpleName().concat(".add()"));
    }

    @Override
    public void update() {
        System.out.println(this.getClass().getSimpleName().concat(".update()"));
    }

    @Override
    public void delete() {
        System.out.println(this.getClass().getSimpleName().concat(".delete()"));
    }
}
```

### <a name="factoryBeanAspect" style="text-decoration:none">切面类</a>

FactoryBeanAspect

```java
public class FactoryBeanAspect implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println(this.getClass().getSimpleName().concat(".invoke() start"));

        // 手动执行目标方法
        Object proceed = invocation.proceed();

        System.out.println(this.getClass().getSimpleName().concat(".invoke() end"));
        return proceed;
    }
}
```

### <a name="factoryBeanConfig" style="text-decoration:none">spring 配置</a>

```xml
<!--创建目标类-->
<bean id="aopService" class="org.lzn.factory_bean.FactoryBeanServiceImpl"/>

<!--创建一个切面类-->
<bean id="factoryBeanAspect" class="org.lzn.factory_bean.FactoryBeanAspect"/>

<!--
    创建代理类
    使用工厂 bean FactoryBean，底层调用 getObject() 返回特殊 bean
    ProxyFactoryBean 用于创建代理工厂 bean，生成特殊代理对象
        interface：确定接口，只有一个值时使用属性 value
            array：设置多个值
                value：值
        target：确定目标类
        interceptorNames：通知切面类的名称，类型 String[]，只有一个值时使用属性 value
-->
<bean id="proxyService" class="org.springframework.aop.framework.ProxyFactoryBean">
    <property name="interfaces" value="org.lzn.factory_bean.FactoryBeanService"/>
    <property name="target" ref="aopService"/>
    <property name="interceptorNames" value="factoryBeanAspect"/>
    <!--
        强制使用 cglib
            如果目标类有借口，采用 jdk 动态代理
            如果没有接口，采用 cglib 字节码增强
            如果声明 optimize = true，无论是否有接口都采用 cglib
    -->
    <property name="optimize" value="true"/>
</bean>
```

### <a name="factoryBeanTest" style="text-decoration:none">测试</a>

```java
public class FactoryBeanTest {
    public void demo1() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        // 获取代理类
        FactoryBeanService aopService = (FactoryBeanService) applicationContext.getBean("proxyService");
        aopService.add();
        aopService.update();
        aopService.delete();
    }
}
```

## <a name="auto" style="text-decoration:none">Spring 编写代理：全自动</a>

* 从 spring 容器获得目标类，如果配置 aop，spring 将自动生成代理。

* 要确定目标类，aspectj 切入点表达式，导入 jar 包

  ```xml
  <dependency>
      <groupId>org.aspectj</groupId>
      <artifactId>aspectjweaver</artifactId>
      <version>1.8.13</version>
  </dependency>
  <!--aop 联盟-->
  <dependency>
      <groupId>aopalliance</groupId>
      <artifactId>aopalliance</artifactId>
      <version>1.0</version>
  </dependency>
  <!--spring aop 实现-->
  <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-aop</artifactId>
      <version>4.3.13.RELEASE</version>
  </dependency>
  <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-beans</artifactId>
      <version>4.3.13.RELEASE</version>
  </dependency>
  <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>4.3.13.RELEASE</version>
  </dependency>
  <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-core</artifactId>
      <version>4.3.13.RELEASE</version>
  </dependency>
  <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-expression</artifactId>
      <version>4.3.13.RELEASE</version>
  </dependency>
  ```

1. [目标类](#factoryBeanTargetClass)：接口 + 实现类。代码 [AopService.java](src/main/java/org/lzn/aop/AopService.java)，[AopServiceImpl.java](src/main/java/org/lzn/aop/AopServiceImpl.java)
2. [切面类](#AopAspect)：实现接口 MethodInterceptor。代码 [AopAspect.java](src/main/java/org/lzn/aop/AopAspect.java)
3. [spring 配置](#AopConfig)：代码 [bean.xml](src/main/resources/bean.xml)
4. [测试](#AopTest)：代码 [AopTest.java](src/main/java/org/lzn/aop/AopTest.java)

### <a name="AopTargetClass" style="text-decoration:none">目标类</a>

AopService

```java
public interface AopService {
    void add();
    void update();
    void delete();
}
```

AopServiceImpl

```java
public class AopServiceImpl implements AopService {
    @Override
    public void add() {
        System.out.println(this.getClass().getSimpleName().concat(".add()"));
    }

    @Override
    public void update() {
        System.out.println(this.getClass().getSimpleName().concat(".update()"));
    }

    @Override
    public void delete() {
        System.out.println(this.getClass().getSimpleName().concat(".delete()"));
    }
}
```

### <a name="aopAspect" style="text-decoration:none">切面类</a>

AopAspect

```java
public class AopAspect implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println(this.getClass().getSimpleName().concat(".invoke() start"));

        // 手动执行目标方法
        Object proceed = invocation.proceed();

        System.out.println(this.getClass().getSimpleName().concat(".invoke() end"));
        return proceed;
    }
}
```

### <a name="AopConfig" style="text-decoration:none">spring 配置</a>

```xml
<!--创建目标类-->
<bean id="aopService" class="org.lzn.aop.AopServiceImpl"/>
<!--创建切面类-->
<bean id="aopAspect" class="org.lzn.aop.AopAspect"/>
<!--
    aop 编程
        导入命名空间
        使用 <aop:config> 进行配置
            proxy-target-class="true"：声明时使用 cglib 代理
                <aop:pointcut> 切入点，从目标对象获得具体方法
                <aop:advisor> 特殊的切面，只有一个通知和一个切入点
                advice-ref：通知引用
                pointcut-ref：切入点引用
        切入点表达式
            execution(* org.lzn.aop.*.*(..))
            选择方法(返回值 包 类名.方法名(参数))，* 代表任意
-->
<aop:config proxy-target-class="true">
    <aop:pointcut id="aopPointCut" expression="execution(* org.lzn.aop.*.*(..))"/>
    <aop:advisor advice-ref="aopAspect" pointcut-ref="aopPointCut"/>
</aop:config>
```

### <a name="factoryBeanTest" style="text-decoration:none">测试</a>

```java
public class AopTest {
    public void demo1() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        // 获取代理类
        AopService aopService = (AopService) applicationContext.getBean("aopService");
        aopService.add();
        aopService.update();
        aopService.delete();
    }
}
```

## AspectJ

### 切入点表达式

#### 1. execution() 

用于描述方法。语法 execution(修饰符 返回值 包.类.方法名(参数)throws 异常)

**修饰符**，一般省略

| 修饰符 | 说明     |
| ------ | -------- |
| public | 公有方法 |
| *      | 任意     |

**返回值**，不能省略

| 返回值 | 说明           |
| ------ | -------------- |
| void   | 返回没有值     |
| String | 返回值为字符串 |
| *      | 任意           |

**包**，可省略

| 包                  | 说明                                                     |
| ------------------- | -------------------------------------------------------- |
| org.lzn             | 固定包                                                   |
| org.lzn.*.service   | lzn 包下面子包任意。例如 org.lzn.aop.service             |
| org.lzn..           | lzn 包下面的所有子包。包含自己                           |
| org.lzn.*.service.. | lzn 包下面任意子包。固定目录 service，service 目录任意包 |

**类**，可省略

| 类              | 说明         |
| --------------- | ------------ |
| UserServiceImpl | 指定类       |
| *Impl           | 以 Impl 结尾 |
| User*           | 以 User 开头 |
| *               | 任意         |

**方法名**，不能省略

| 方法名  | 说明        |
| ------- | ----------- |
| addUser | 固定方法    |
| add*    | 以 add 开头 |
| *Do     | 以 Do 结尾  |
| *       | 任意        |

**(参数)**

| 参数       | 说明     |
| ---------- | -------- |
| ()         | 无参     |
| (int)      | 一个整型 |
| (int, int) | 两个     |
| (..)       | 参数任意 |

**throws**，可省略，一般不写

#### 2. within

匹配包或子包中的方法。例如 within(org.lzn..*)

#### 3. this

匹配实现接口的代理对象中的方法。例如 this(org.lzn.Hehe)

#### 4. target

匹配实现接口的目标对象中的方法。例如 target(org.lzn.Hehe)

#### 5. args

匹配参数格式符合标准的方法。例如 args(int, int)

#### 6. bean(id)

对指定的 bean 所有方法。例如 bean('userService')

### Aspect 通知类型

* aop 联盟定义通知类型，具有特性接口，必须实现，从而确定方法名称。

* aspectj 通知类型，只定义类型名称。已经方法格式。

  | 类型           | 说明                                                         |
  | -------------- | ------------------------------------------------------------ |
  | before         | 前置通知，应用于各种校验。<br>在方法执行前执行，如果通知抛出异常，阻止方法运行 |
  | afterReturning | 后置通知，应用于常规数据处理。<br>方法正常返回后执行，如果方法中抛出异常，通知无法执行<br>必须在方法执行后才执行，所以可以获得方法的返回值。 |
  | **around**     | 环绕通知，自由度很高。<br>方法执行前后分别执行，可以阻止方法的执行<br>必须手动执行目标方法 |
  | afterThrowing  | 抛出异常通知，应用于包装异常信息。<br>方法抛出异常后执行，如果方法没有抛出异常，无法执行 |
  | after          | 最终通知，应用于清理现场。<br>方法执行完毕后执行，无论方法中是否出现异常 |

### 导入包

4 个

| 包             | 说明               |
| -------------- | ------------------ |
| aopalliance    | aop联盟规范        |
| spring-aop     | spring aop 实现    |
| aspectjweaver  | aspect 规范        |
| spring-aspects | spring aspect 实现 |

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-aspects</artifactId>
    <version>4.3.13.RELEASE</version>
</dependency>

<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>1.8.13</version>
</dependency>
<dependency>
    <groupId>aopalliance</groupId>
    <artifactId>aopalliance</artifactId>
    <version>1.0</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-aop</artifactId>
    <version>4.3.13.RELEASE</version>
</dependency>
```

### <a name="xml" style="text-decoration:none">基于 XML</a>

基于 XML 配置 aop。与上方 [Spring 编写代理：全自动](#auto) 一样。

1. [目标类](#XmlTargetClass)：代码 [XmlService.java](src/main/java/org/lzn/xml/XmlService.java)
2. [切面类](#XmlAspect)：代码 [XmlAspect.java](src/main/java/org/lzn/xml/XmlAspect.java)
3. [spring 配置](#XmlConfig)：代码 [bean.xml](src/main/resources/bean.xml)
4. [测试](#XmlTest)：代码 [XmlTest.java](src/main/java/org/lzn/xml/XmlTest.java)

#### <a name="XmlTargetClass" style="text-decoration:none">目标类</a>

XmlService

```java
public class XmlService {
    public void addUser() {
        System.out.println(this.getClass().getSimpleName().concat(".addUser()"));
    }
    public void updateUser() {
        System.out.println(this.getClass().getSimpleName().concat(".updateUser()"));
    }
    public void deleteUser() {
        System.out.println(this.getClass().getSimpleName().concat(".deleteUser()"));
    }
    public void exception() {
        System.out.println(this.getClass().getSimpleName().concat(".exception()"));
        int i = 1 / 0;
    }
}
```

#### <a name="XmlAspect" style="text-decoration:none">切面类</a>

XmlAspect

```java
public class XmlAspect {
    public void xmlAfter() {
        System.out.println(this.getClass().getSimpleName().concat(".xmlAfter()"));
    }
    public void xmlBefore() {
        System.out.println(this.getClass().getSimpleName().concat(".xmlBefore()"));
    }
    public void xmlAfterWithParameter(JoinPoint joinPoint, Object rtn) {
        System.out.println(this.getClass().getSimpleName().concat(".xmlAfterWithParameter()"));
        System.out.println("方法返回值：".concat(String.valueOf(rtn)));
    }
    public void xmlBeforeWithParameter(JoinPoint joinPoint) {
        System.out.println(this.getClass().getSimpleName().concat(".xmlAfterWithParameter()"));
        System.out.println("方法名称：".concat(joinPoint.getSignature().getName()));
    }
    public Object xmlAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println(this.getClass().getSimpleName().concat(".xmlAround() start"));

        // 手动执行目标方法
        Object obj = proceedingJoinPoint.proceed();

        System.out.println(this.getClass().getSimpleName().concat(".xmlAround() end"));
        return obj;
    }
    public void xmlAfterThrowing(JoinPoint joinPoint, Throwable throwable) {
        System.out.println(this.getClass().getSimpleName().concat(".xmlAfterThrowing()"));
        System.out.println("异常信息：".concat(throwable.getMessage()));
    }
}
```

#### <a name="XmlConfig" style="text-decoration:none">spring 配置</a>

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/aop
       					   http://www.springframework.org/schema/aop/spring-aop.xsd">
    <!--基于 xml-->
    <!--创建目标类-->
    <bean id="xmlService" class="org.lzn.xml.XmlService"/>
    <!--创建切面类-->
    <bean id="xmlAspect" class="org.lzn.xml.XmlAspect"/>
    <!--
        aop 编程
            <aop:aspect>：将切面类声明“切面”，从而获得通知（方法）
                ref：切面类引用
                <aop:after>：最终
                <aop:after-returning>：后置
                    method：通知，方法名
                        方法可带参数 1：org.aspectj.lang.JoinPoint，用于描述连接点（目标方法）
                        方法可带参数 2：Object，使用 returning 属性配置
                    pointcut：切入点表达式，此表达式只能当前通知使用
                    pointcut-ref：切入点引用，可以与其他通知共享切入点
                <aop:after-throwing>：抛出异常
                    参数 1：连接点；描述对象
                    参数 2：获取异常信息，类型 java.lang.Throwable，使用 throwing 属性配置
                <aop:around>：环绕
                    方法格式：public Object xmlAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
                        返回类型：Object
                        方法名：自定义
                        参数：org.aspectj.lang.ProceedingJoinPoint，方法 proceed 为执行目标方法
                        抛出异常：Throwable
                <aop:before>：前置
                    method：通知，方法名
                        方法可带参数 1：org.aspectj.lang.JoinPoint，用于描述连接点（目标方法）
                    pointcut：切入点表达式，此表达式只能当前通知使用
                    pointcut-ref：切入点引用，可以与其他通知共享切入点
                <aop:declare-parents>：引介
                <aop:pointcut>：切入点
                    expression：切入点表达式
                    id：名称，用于其他通知引用
    -->
    <aop:config>
        <aop:aspect ref="xmlAspect">
            <aop:pointcut id="xmlPointcut" expression="execution(* org.lzn.xml.XmlService.*(..))"/>

            <!--前置通知-->
            <!--<aop:before method="xmlBeforeWithParameter" pointcut-ref="xmlPointcut"/>-->

            <!--后置通知-->
            <!--<aop:after-returning method="xmlAfterWithParameter" pointcut-ref="xmlPointcut" returning="rtn"/>-->

            <!--环绕通知-->
            <!--<aop:around method="xmlAround" pointcut-ref="xmlPointcut"/>-->

            <!--抛出异常-->
            <!--<aop:after-throwing method="xmlAfterThrowing" pointcut-ref="xmlPointcut" throwing="throwable"/>-->

            <!--最终通知-->
            <aop:after method="xmlAfter" pointcut-ref="xmlPointcut"/>
        </aop:aspect>
    </aop:config>
</beans>
```

#### <a name="XmlTest" style="text-decoration:none">测试</a>

XmlTest

```java
public class XmlTest {
    public void demo1() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        XmlService xmlService = (XmlService) applicationContext.getBean("xmlService");
        xmlService.addUser();
        xmlService.updateUser();
        xmlService.deleteUser();
    }
    public void demo2() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        XmlService xmlService = (XmlService) applicationContext.getBean("xmlService");
        xmlService.exception();
    }
}
```

### 基于注解

使用注解配置 aop，与上方 [基于 xml](#xml) 一样。

1. [目标类](#annotationTargetClass)：代码 [AnnotationService.java](src/main/java/org/lzn/annotation/AnnotationService.java)
2. [切面类](#annotationAspect)：代码 [AnnotationAspect.java](src/main/java/org/lzn/annotation/AnnotationAspect.java)
3. [spring 配置](#annotationConfig)：代码 [bean.xml](src/main/resources/bean.xml)
4. [测试](#annotationTest)：代码 [AnnotationTest.java](src/main/java/org/lzn/annotation/AnnotationTest.java)

#### <a name="annotationTargetClass" style="text-decoration:none">目标类</a>

AnnotationService

```java
@Service("annotationService")
public class AnnotationService {
    public void addUser() {
        System.out.println(this.getClass().getSimpleName().concat(".addUser()"));
    }
    public void updateUser() {
        System.out.println(this.getClass().getSimpleName().concat(".updateUser()"));
    }
    public void deleteUser() {
        System.out.println(this.getClass().getSimpleName().concat(".deleteUser()"));
    }
    public void exception() {
        System.out.println(this.getClass().getSimpleName().concat(".exception()"));
        int i = 1 / 0;
    }
}

```

#### <a name="annotationAspect" style="text-decoration:none">切面类</a>

AnnotationAspect

```java
@Component
@Aspect
public class AnnotationAspect {

    /**
     * 声明公共切入点，方法名就是 id
     * 相当于：<aop:pointcut id="myPointcut" expression="execution(* org.lzn.annotation.AnnotationService.*(..))"/>
     *
     * @author LinZhenNan lin_hehe@qq.com
     */
    @Pointcut("execution(* org.lzn.annotation.AnnotationService.*(..))")
    public void myPointcut() {}

    /**
     * 切入点当前有效
     * 相当于：<aop:before method="annotationAfter" pointcut="execution(* org.lzn.annotation.AnnotationService.*(..))"/>
     *
     * @author LinZhenNan lin_hehe@qq.com
     */
//    @Before("execution(* org.lzn.annotation.AnnotationService.*(..))")
    public void annotationAfter() {
        System.out.println(this.getClass().getSimpleName().concat(".annotationAfter()"));
    }
    public void annotationBefore() {
        System.out.println(this.getClass().getSimpleName().concat(".annotationBefore()"));
    }
    /**
     * 相当于：<aop:after-returning method="annotationAfterWithParameter" pointcut-ref="myPointcut" returning="rtn"/>
     *
     * @param joinPoint 用于描述连接点（目标方法）
     * @param rtn       目标方法返回值
     * @author LinZhenNan lin_hehe@qq.com
     */
//    @AfterReturning(value = "myPointcut()", returning = "rtn")
    public void annotationAfterWithParameter(JoinPoint joinPoint, Object rtn) {
        System.out.println(this.getClass().getSimpleName().concat(".annotationAfterWithParameter()"));
        System.out.println("方法返回值：".concat(String.valueOf(rtn)));
    }
    public void annotationBeforeWithParameter(JoinPoint joinPoint) {
        System.out.println(this.getClass().getSimpleName().concat(".annotationAfterWithParameter()"));
        System.out.println("方法名称：".concat(joinPoint.getSignature().getName()));
    }
    /**
     * 相当于：<aop:around method="xmlAround" pointcut-ref="myPointcut"/>
     *
     * @param proceedingJoinPoint 处理连接点对象
     * @return java.lang.Object 
     * @author LinZhenNan lin_hehe@qq.com  
     */
    @Around("myPointcut()")
    public Object annotationAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println(this.getClass().getSimpleName().concat(".annotationAround() start"));

        // 手动执行目标方法
        Object obj = proceedingJoinPoint.proceed();

        System.out.println(this.getClass().getSimpleName().concat(".annotationAround() end"));
        return obj;
    }
    public void annotationAfterThrowing(JoinPoint joinPoint, Throwable throwable) {
        System.out.println(this.getClass().getSimpleName().concat(".annotationAfterThrowing()"));
        System.out.println("异常信息：".concat(throwable.getMessage()));
    }
}
```

#### <a name="annotationConfig" style="text-decoration:none">spring 配置</a>

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/aop
       					   http://www.springframework.org/schema/aop/spring-aop.xsd
                           http://www.springframework.org/schema/context
       					   http://www.springframework.org/schema/context/spring-context.xsd">
    <!--扫描注解-->
    <context:component-scan base-package="org.lzn.annotation"/>
    <!--aop 注解生效-->
    <aop:aspectj-autoproxy/>
</beans>
```

#### <a name="annotationTest" style="text-decoration:none">测试</a>

AnnotationTest

```java
public class AnnotationTest {
    public void demo1() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        AnnotationService annotationService = (AnnotationService) applicationContext.getBean("annotationService");
        annotationService.addUser();
        annotationService.updateUser();
        annotationService.deleteUser();
    }
    public void demo2() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        AnnotationService annotationService = (AnnotationService) applicationContext.getBean("annotationService");
        annotationService.exception();
    }
}
```
