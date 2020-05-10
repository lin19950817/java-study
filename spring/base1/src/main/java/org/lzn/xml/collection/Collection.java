package org.lzn.xml.collection;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 集合注入
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/10 17:45
 */
public class Collection {
    @Test
    public void demo1() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        CollectionData collectionData = (CollectionData) applicationContext.getBean("collectionData");
        System.out.println(collectionData);
    }
}
