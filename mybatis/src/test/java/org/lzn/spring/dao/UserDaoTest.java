package org.lzn.spring.dao;

import org.junit.Before;
import org.junit.Test;
import org.lzn.po.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 * mybatis整合spring测试
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/10/24 21:37
 */
public class UserDaoTest {

    private ApplicationContext context;

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("spring/applicationConfig.xml");
    }

    @Test
    public void getUserById() {
        // 创建 UserDao
        UserDao userDao = (UserDao) context.getBean("userDao");
        User user = userDao.getUserById(1);
        System.out.println(user);
    }
}