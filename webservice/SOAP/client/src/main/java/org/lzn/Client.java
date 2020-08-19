package org.lzn;

import org.lzn.service.WeatherImpl;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * service 编程调用方式
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/08/09 22:34
 */
public class Client {

    public static void main(String[] args) throws MalformedURLException {
        // 创建 WSDL 的 URL
        URL url = new URL("http://127.0.0.1:4444/weather?wsdl");

        // 创建服务名称
        // 1. namespaceURI，命名空间地址
        // 2. localPart，服务视图名
        QName qName = new QName("http://impl.service.lzn.org/", "WeatherImplService");

        // 创建服务视图
        // 1. wsdlDocumentLocation，wsdl 地址
        // 2. serviceName，服务名称
        Service service = Service.create(url, qName);
        // 获取服务实现类
        WeatherImpl weatherService = service.getPort(WeatherImpl.class);
        // 调用查询方法
        String weather = weatherService.queryWeather("广东");
        System.out.println(weather);
    }
}
