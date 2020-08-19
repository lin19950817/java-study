package org.lzn.service;

/**
 * SEI（Service Endpoint Interface）接口，本质上就是Java接口
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/07/21 23:47
 */
public interface Weather {
    String queryWeather(String cityName);
}
