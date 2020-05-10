package org.lzn.xml.annotation;

import org.junit.Test;
import org.lzn.xml.annotation.action.StudentAction;
import org.lzn.xml.annotation.lifecycle_scope.LifecycleAndScope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 注解注入
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/10 22:15
 */
public class Annotation {
    /**
     * 演示依赖注入
     *
     * @author LinZhenNan lin_hehe@qq.com
     */
    @Test
    public void demo1() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        StudentAction studentAction = (StudentAction) applicationContext.getBean("studentAction");
        studentAction.execute();
    }

    /**
     * 演示 生命周期 和 作用域
     *
     * @author LinZhenNan lin_hehe@qq.com
     */
    @Test
    public void demo2() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("bean.xml");
        LifecycleAndScope lifecycleAndScope = (LifecycleAndScope) classPathXmlApplicationContext.getBean("lifecycleAndScope");
        LifecycleAndScope lifecycleAndScope2 = (LifecycleAndScope) classPathXmlApplicationContext.getBean("lifecycleAndScope");
        System.out.println(lifecycleAndScope);
        System.out.println(lifecycleAndScope2);
        lifecycleAndScope.func();
        classPathXmlApplicationContext.close();
    }
}
