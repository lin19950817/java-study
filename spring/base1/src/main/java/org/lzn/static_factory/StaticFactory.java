package org.lzn.static_factory;

import org.junit.Test;
import org.lzn.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 静态工厂
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/07 23:44
 */
public class StaticFactory {
    /**
     * 为整合 spring 之前
     *
     * @author LinZhenNan lin_hehe@qq.com
     */
    @Test
    public void demo1(){
        // 自定义工厂
        UserService userService = MyBeanFactory.createUserService();
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
        UserService userService = (UserService) applicationContext.getBean("staticFactory");
        userService.addUser();
    }
}
