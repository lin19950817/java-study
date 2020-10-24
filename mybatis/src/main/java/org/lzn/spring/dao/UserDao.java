package org.lzn.spring.dao;

import org.lzn.po.User;

/**
 * dao
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/10/24 21:25
 */
public interface UserDao {
    /**
     * 通过 userId 查询用户信息
     *
     * @param id 用户id
     * @return org.lzn.po.User
     * @author LinZhenNan lin_hehe@qq.com
     */
    User getUserById(int id);
}
