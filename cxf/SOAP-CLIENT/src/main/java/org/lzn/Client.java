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
