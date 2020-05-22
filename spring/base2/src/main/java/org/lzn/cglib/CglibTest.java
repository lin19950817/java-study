package org.lzn.cglib;

import org.junit.Test;

/**
 * cglib 代理，测试
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/12 23:23
 */
public class CglibTest {
    @Test
    public void demo1() {
        CglibService cglibService = CglibBeanFactory.createCglibService();
        cglibService.addUser();
        cglibService.updateUser();
        cglibService.deleteUser();
    }
}
