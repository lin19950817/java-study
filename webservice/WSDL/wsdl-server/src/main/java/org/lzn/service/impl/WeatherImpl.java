package org.lzn.service.impl;

import org.lzn.service.Weather;

import javax.jws.WebService;

/**
 * SEI 实现类<br>
 *     @WebService 表示该类是一个服务类，需要发布其中的 public
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/07/21 23:48
 */
@WebService
public class WeatherImpl implements Weather {
    @Override
    public String queryWeather(String cityName) {
        System.out.println("WeatherImpl.queryWeather");
        System.out.println("查询的城市名称：".concat(cityName));
        return "晴";
    }
}
