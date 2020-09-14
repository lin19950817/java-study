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
