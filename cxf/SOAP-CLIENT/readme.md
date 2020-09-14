# CXF 发布 SOAP 协议的服务（客户端）

[TOC]

## CXF 发布 SOAP 协议的服务（客户端）

### <a name="wsdl2java" style="text-decoration:none">生成客户端代码</a>

* `Wsdl2java` 命令是 `CXF` 提供的生成客户端的工具，他和 `wsimport` 类似，可以根据 `WSDL` 生成客户端代码
* `Wsdl2java` 常用参数。例如 `wsdl2java -p org.lzn.service -d . http://127.0.0.1:12345/weather?wsdl`
  * -d：指定输出目录
  * -p：指定报名，如果不指定该参数，默认包名是 `WSDL` 的命名空间的倒叙
* `Wsdl2java` 支持 `SOAP1.1` 和 `SOAP1.2`

### 使用生成代码调用服务端

`JaxWsProxyFactoryBean` 调用服务端，设置两个参数

1. 设置接口服务地址
2. 设置服务地址

[Client.java](src/main/java/org/lzn/Client.java)

```java
package org.lzn;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.lzn.service.Weather;

/**
 * CXF 客户端
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/09/06 15:55
 */
public class Client {

    public static void main(String[] args) {
        // JaxWsProxyFactoryBean 调用服务端
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        // 设置服务接口
        jaxWsProxyFactoryBean.setServiceClass(Weather.class);
        // 设置服务地址
        jaxWsProxyFactoryBean.setAddress("http://127.0.0.1:12345/weather");
        // 获取服务接口实例
        Weather weather = jaxWsProxyFactoryBean.create(Weather.class);
        // 调用查询方法
        String result = weather.queryWeather("京都");
        System.out.println(result);
    }
}
```

