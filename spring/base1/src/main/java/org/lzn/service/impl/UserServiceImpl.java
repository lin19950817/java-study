package org.lzn.service.impl;

import org.lzn.service.UserService;

/**
 * UserService 实现类
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/06 23:55
 */
public class UserServiceImpl implements UserService {
    @Override
    public void addUser() {
        System.out.println("UserServiceImpl.addUser");
    }

    public UserServiceImpl() {
        // 用于观察 bean 是否实例化
        System.out.println("UserServiceImpl 实例化");
    }
}
