package org.lzn.jdk;

import org.junit.Test;
import org.lzn.jdk.aop.MyBeanFactory;
import org.lzn.jdk.service.UserService;

/**
 * jdk 动态代理，测试
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/12 0:03
 */
public class JdkTest {
    @Test
    public void demo1() {
        UserService userService = MyBeanFactory.createUserService();
        userService.addUser();
        userService.updateUser();
        userService.deleteUser();
    }
}
