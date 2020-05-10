package org.lzn.post_processor;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * bean 后处理
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/08 23:40
 */
public class PostProcessor {

    @Test
    public void demo1() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
    }
}
