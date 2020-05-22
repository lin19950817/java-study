package org.lzn.aop;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring 编写代理-全自动，测试
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/13 21:56
 */
public class AopTest {
    @Test
    public void demo1() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        // 获取代理类
        AopService aopService = (AopService) applicationContext.getBean("aopService");
        aopService.add();
        aopService.update();
        aopService.delete();
    }
}
