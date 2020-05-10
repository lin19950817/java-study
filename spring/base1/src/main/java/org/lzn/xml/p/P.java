package org.lzn.xml.p;

import org.junit.Test;
import org.lzn.xml.property.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * p 命名空间，偷懒使用 setter 属性注入的实体类做演示
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/10 16:52
 */
public class P {
    @Test
    public void demo1() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        Person p = (Person) applicationContext.getBean("p_person");
        System.out.println(p);
    }
}
