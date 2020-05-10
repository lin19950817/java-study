package org.lzn.factory;

import org.junit.Test;
import org.lzn.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 工厂
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/08 0:05
 */
public class Factory {
    /**
     * 整合 spring 之前
     *
     * @author LinZhenNan lin_hehe@qq.com
     */
    @Test
    public void demo1() {
        // 自定义实例化工厂
        // 1. 创建工厂
        MyFactory myFactory = new MyFactory();
        // 2. 通过工厂实例，获取对象
        UserService userService = myFactory.createUserService();
        userService.addUser();
    }

    /**
     * 使用 spring 之后
     *
     * @author LinZhenNan lin_hehe@qq.com
     */
    @Test
    public void demo2() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        UserService userService = (UserService) applicationContext.getBean("userServiceByFactory");
        userService.addUser();
    }
}
