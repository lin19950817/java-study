package org.lzn.hibernate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lzn.hibernate.cfg.domain.User;
import org.lzn.hibernate.cfg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/06/09 23:53
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:org/lzn/hibernate/applicationContext.xml")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void save() {
        User user = new User();
        user.setUsername("Spring 整合 Hibernate");
        user.setPassword("hehe");

        userService.save(user);
    }
}