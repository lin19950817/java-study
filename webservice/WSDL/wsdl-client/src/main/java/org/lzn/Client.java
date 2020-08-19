package org.lzn;

import org.lzn.service.impl.WeatherImpl;
import org.lzn.service.impl.WeatherImplService;

/**
 * 客户端代码调用服务器
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/07/23 22:10
 */
public class Client {

    public static void main(String[] args) {
        // 创建服务视图
        WeatherImplService weatherImplService = new WeatherImplService();
        // 获取服务实现类
        WeatherImpl weatherImpl = weatherImplService.getPort(WeatherImpl.class);
        // 调用查询方法
        String cityWeather = weatherImpl.queryWeather("广州");
        System.out.println(cityWeather);
    }
}
