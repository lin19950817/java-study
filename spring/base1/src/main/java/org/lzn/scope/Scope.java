package org.lzn.scope;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * bean 作用域
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/08 21:58
 */
public class Scope {
    @Test
    public void demo1() {
        // bean 单例
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        System.out.println(applicationContext.getBean("single"));
        // 第二次 getBean 是否跟第一个 bean 是同一个
        System.out.println(applicationContext.getBean("single"));
    }

    @Test
    public void demo2() {
        // bean 多例
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        System.out.println(applicationContext.getBean("prototype"));
        // 第二次 getBean 是否跟第一个 bean 是同一个
        System.out.println(applicationContext.getBean("prototype"));
    }
}
