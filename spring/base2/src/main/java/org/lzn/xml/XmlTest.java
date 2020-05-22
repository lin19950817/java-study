package org.lzn.xml;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 基于 xml 演示
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/19 23:20
 */
public class XmlTest {

    @Test
    public void demo1() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        XmlService xmlService = (XmlService) applicationContext.getBean("xmlService");
        xmlService.addUser();
        xmlService.updateUser();
        xmlService.deleteUser();
    }
    @Test
    public void demo2() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        XmlService xmlService = (XmlService) applicationContext.getBean("xmlService");
        xmlService.exception();
    }
}
