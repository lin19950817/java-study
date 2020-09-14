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
