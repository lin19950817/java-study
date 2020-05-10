package org.lzn.xml.construct;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 构造方法 属性注入
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/09 23:14
 */
public class Construct {
    @Test
    public void demo1() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        User user = (User) applicationContext.getBean("user");
        System.out.println(user);
    }
}
