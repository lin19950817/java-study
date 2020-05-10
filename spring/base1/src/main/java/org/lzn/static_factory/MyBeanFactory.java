package org.lzn.static_factory;

import org.lzn.service.UserService;
import org.lzn.service.impl.UserServiceImpl;

/**
 * 静态工厂
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/07 23:40
 */
public class MyBeanFactory {

    /**
     * 静态工厂，创建实例
     *
     * @return org.lzn.service.UserService
     * @author LinZhenNan lin_hehe@qq.com  
     */
    public static UserService createUserService() {
        return new UserServiceImpl();
    }
}
