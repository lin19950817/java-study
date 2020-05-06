package org.lzn.service.impl;

import org.lzn.service.UserService;

/**
 * 用户服务实现类，IOC 案例
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/02 20:57
 */
public class UserServiceImpl implements UserService {

    @Override
    public void addUser() {
        System.out.println("UserServiceImpl.addUser()");
    }
}
