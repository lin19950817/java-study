package org.lzn;

import org.junit.Test;
import org.lzn.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/29 0:12
 */
public class AccountTest {

    @Test
    public void demo1() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountService accountService = (AccountService) applicationContext.getBean("accountService");
        accountService.transfer("haha", "hehe", 500);
    }

    @Test
    public void demo2() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountService accountService = (AccountService) applicationContext.getBean("manualAccountService");
        accountService.transfer("haha", "hehe", 500);
    }

    @Test
    public void demo3() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountService accountService = (AccountService) applicationContext.getBean("proxyAccountService");
        accountService.transfer("haha", "hehe", 500);
    }

    @Test
    public void demo4() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("annotationApplicationContext.xml");
        AccountService accountService = (AccountService) applicationContext.getBean("accountService");
        accountService.transfer("haha", "hehe", 500);
    }

    public static void getException() {
        throw new RuntimeException("断电了");
    }
}
