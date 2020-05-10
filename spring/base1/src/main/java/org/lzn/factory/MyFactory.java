package org.lzn.factory;

import org.lzn.service.UserService;
import org.lzn.service.impl.UserServiceImpl;

/**
 * 实例工厂
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/08 0:02
 */
public class MyFactory {

    /**
     * 实例工厂，创建实例
     *
     * @return org.lzn.service.UserService
     * @author LinZhenNan lin_hehe@qq.com
     */
    public UserService createUserService() {
        return new UserServiceImpl();
    }
}
