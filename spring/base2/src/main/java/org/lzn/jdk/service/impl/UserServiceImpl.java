package org.lzn.jdk.service.impl;

import org.lzn.jdk.service.UserService;

/**
 * jdk 动态代理，目标借口实现类
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/11 23:38
 */
public class UserServiceImpl implements UserService {
    @Override
    public void addUser() {
        System.out.println(this.getClass().getSimpleName().concat(".addUser()"));
    }

    @Override
    public void updateUser() {
        System.out.println(this.getClass().getSimpleName().concat(".updateUser()"));
    }

    @Override
    public void deleteUser() {
        System.out.println(this.getClass().getSimpleName().concat(".deleteUser()"));
    }
}
