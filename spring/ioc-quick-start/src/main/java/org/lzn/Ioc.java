package org.lzn;

import org.junit.Test;
import org.lzn.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * IOC 案例
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/06 0:11
 */
public class Ioc {

    @Test
    public void demo1() {
        // 从 spring 容器获得
        // 1. 获得容器
        String xmlPath = "bean.xml";
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(xmlPath);
        // 2. 获得内容
        UserService userServiceId = (UserService) classPathXmlApplicationContext.getBean("userServiceId");
        userServiceId.addUser();
    }
}
