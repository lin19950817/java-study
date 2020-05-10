package org.lzn;

import org.junit.Test;
import org.lzn.service.UserService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * springContext
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/06 23:37
 */
public class Context {
    @Test
    public void demo1() {
        // 使用 ClassClassPathXmlApplicationContext
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.addUser();
    }

    @Test
    public void demo2() {
        // 使用 BeanFactory
        Resource resource = new ClassPathResource("bean.xml");
        BeanFactory beanFactory = new XmlBeanFactory(resource);
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.addUser();
    }
}
