package org.lzn.annotation;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 基于注解演示
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/19 23:20
 */
public class AnnotationTest {

    @Test
    public void demo1() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        AnnotationService annotationService = (AnnotationService) applicationContext.getBean("annotationService");
        annotationService.addUser();
        annotationService.updateUser();
        annotationService.deleteUser();
    }

    @Test
    public void demo2() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        AnnotationService annotationService = (AnnotationService) applicationContext.getBean("annotationService");
        annotationService.exception();
    }
}
