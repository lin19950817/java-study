package org.lzn.lifecycle;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 声明周期
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/08 22:29
 */
public class Lifecycle {

    @Test
    public void demo1() throws Exception{
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        BeanLifecycle beanLifecycle = (BeanLifecycle) applicationContext.getBean("lifecycle");
        beanLifecycle.lifecycle();

        // 需要单例且容器 close，销毁方法执行。反射调用 ClassPathXmlApplicationContext 的 close
        applicationContext.getClass().getMethod("close").invoke(applicationContext);
    }
}
