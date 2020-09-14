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
