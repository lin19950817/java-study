package org.lzn;

import org.lzn.service.impl.WeatherImpl;

import javax.xml.ws.Endpoint;

/**
 * 发布服务
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/07/21 23:54
 */
public class Server {

    public static void main(String[] args) {
        Endpoint.publish("http://127.0.0.1:12345/weather", new WeatherImpl());
    }
}
