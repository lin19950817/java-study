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
