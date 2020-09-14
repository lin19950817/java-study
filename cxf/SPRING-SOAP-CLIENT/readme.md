# CXF + Spring 整合发布 SOAP 协议的服务（客户端）

[TOC]

## CXF + Spring 整合发布 SOAP 协议的服务（客户端）

根据 [CXF + Spring 整合发布 SOAP 协议服务](../SPRING-SOAP-SERVER/readme.md) 发布的服务，编写客户端。

## 生成客户端代码

使用 `wsdl2java` 命令生成客户端代码，[使用说明](../SOAP-CLIENT/readme.md#wsdl2java)。例如 `wsdl2java -p org.lzn.service -d . http://localhost:8080/SPRING_SOAP_SERVER/cxf-spring/weather?wsdl`。

## 代码

### 客户端

[Main.java](src/main/java/org/lzn/Main.java)

```java
package org.lzn;

import org.lzn.service.Weather;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * CXF + Spring 整合发布 SOAP 协议的服务（客户端）
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/09/10 21:34
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        Weather weather = (Weather) applicationContext.getBean("weather");
        String result = weather.queryWeather("魔都");
        System.out.println(result);
    }
}
```

### Spring 配置

[applicationContext.xml](src/main/resources/applicationContext.xml)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:jaxws="http://cxf.apache.org/jaxws"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context
                           http://www.springframework.org/schema/context/spring-context.xsd
                           http://cxf.apache.org/jaxws
                           http://cxf.apache.org/schemas/jaxws.xsd">

    <!--jaxws:client 实现客户端，对 JaxWsProxyFactoryBean 类封装-->
    <jaxws:client id="weather" address="http://localhost:8080/SPRING_SOAP_SERVER/cxf-spring/weather" serviceClass="org.lzn.service.Weather" />
</beans>
```

