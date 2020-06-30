package org.lzn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lzn.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * spring 整合 junit
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/31 23:56
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JunitTest {
    /**
     * 与 junit 整合，不需要再 spring xml 配置扫描
     */
    @Autowired
    private AccountService accountService;

    @Test
    public void demo() {
        accountService.transfer("haha", "hehe", 500);
    }
}
