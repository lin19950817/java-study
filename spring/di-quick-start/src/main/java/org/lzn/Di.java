package org.lzn;

import org.junit.Test;
import org.lzn.service.BookService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * description
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/06 22:18
 */
public class Di {

    @Test
    public void demo1() {
        // 从 spring 容器获得
        // 1. 获得容器
        String xmlPath = "bean.xml";
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(xmlPath);
        // 2. 获得内容
        BookService bookService = (BookService) classPathXmlApplicationContext.getBean("bookService");
        bookService.addBook();
    }
}
