# CXF 发布 SOAP 协议的服务（服务端）

[TOC]

## <a name="soap" style="text-decoration:none">CXF 发布 SOAP 协议的服务（服务端）</a>

发布天气查询服务，结束客户端发送的城市名，返回天气数据。

## 代码

### <a name="weather" style="text-decoration:none">创建 SEI 接口，Weather。</a>

[Weather.java](src/main/java/org/lzn/service/Weather.java)

```java
package org.lzn.service;

import javax.jws.WebService;

/**
 * 天气查询 SEI 接口
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/09/04 22:32
 */
@WebService
public interface Weather {

    /**
     * 通过城市名称查询天气
     *
     * @param cityName 城市名称
     * @return java.lang.String
     * @author LinZhenNan lin_hehe@qq.com
     */
    String queryWeather(String cityName);
}
```

### SEI 接口实现类，WeatherImpl

[WeatherImpl.java](src/main/java/org/lzn/service/impl/Weather.java)

```java
package org.lzn.service.impl;

import org.lzn.service.Weather;

/**
 * 天气查询 SEI 实现类
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/09/04 22:34
 */
public class WeatherImpl implements Weather {
    @Override
    public String queryWeather(String cityName) {
        System.out.println(this.getClass().getSimpleName().concat("queryWeather：{").concat("cityName：").concat(cityName).concat("}"));
        return "冷";
    }
}
```

### 发布服务

使用 `JaxWsServerFactoryBean` 发布，设置三个参数

1. 服务接口
2. 服务实现类
3. 服务地址

`Endpoint` 仅支持发布实现类，`JaxWsServerFactoryBean` 支持发布接口。

[Main.java](src/main/java/org/lzn/Main.java)

```java
package org.lzn;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.lzn.service.Weather;
import org.lzn.service.impl.WeatherImpl;

/**
 * 入口
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/09/04 22:37
 */
public class Main {

    public static void main(String[] args) {
        // JaxWsServerFactoryBean 发布服务
        JaxWsServerFactoryBean jaxWsServerFactoryBean = new JaxWsServerFactoryBean();
        // 设置服务接口
        jaxWsServerFactoryBean.setServiceClass(Weather.class);
        // 设置服务实现类
        jaxWsServerFactoryBean.setServiceBean(new WeatherImpl());
        // 设置服务地址
        jaxWsServerFactoryBean.setAddress("http://127.0.0.1:12345/weather");
        // 发布
        jaxWsServerFactoryBean.create();
    }
}
```

## CXF 发布 SOAP1.2 协议的服务

与 [CXF 发布 SOAP 协议的服务](#soap) 一样，只需在 [Weather](#weather) 加上注解 `@BindingType(SOAPBinding.SOAP12HTTP_BINDING)`

[Weather.java](src/main/java/org/lzn/service/Weather.java)

```java
package org.lzn.service;

import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

/**
 * 天气查询 SEI 接口
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/09/04 22:32
 */
@WebService
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
public interface Weather {

    /**
     * 通过城市名称查询天气
     *
     * @param cityName 城市名称
     * @return java.lang.String
     * @author LinZhenNan lin_hehe@qq.com
     */
    String queryWeather(String cityName);
}
```

## 拦截器

### 原理

* 拦截器可以拦截请求和响应
* 拦截器可以有多个
* 拦截器可以根据需要自定义

### 使用

* 拦截器必须加载服务端，在服务端发布之前。
* 获取拦截器列表，将自己的拦截器加入列表中。

[Main.java](src/main/java/org/lzn/Main.java)

```java
package org.lzn;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.lzn.service.Weather;
import org.lzn.service.impl.WeatherImpl;

/**
 * CXF 发布 SOAP 服务
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/09/04 22:37
 */
public class Main {

    public static void main(String[] args) {
        // JaxWsServerFactoryBean 发布服务
        JaxWsServerFactoryBean jaxWsServerFactoryBean = new JaxWsServerFactoryBean();
        // 设置服务接口
        jaxWsServerFactoryBean.setServiceClass(Weather.class);
        // 设置服务实现类
        jaxWsServerFactoryBean.setServiceBean(new WeatherImpl());
        // 设置服务地址
        jaxWsServerFactoryBean.setAddress("http://127.0.0.1:12345/weather");

        // 加入拦截器
        jaxWsServerFactoryBean.getInInterceptors().add(new LoggingInInterceptor());
        jaxWsServerFactoryBean.getOutInterceptors().add(new LoggingOutInterceptor());

        // 发布
        jaxWsServerFactoryBean.create();
    }
}
```

