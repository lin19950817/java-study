package org.lzn.hibernate.cfg.dao;

import org.lzn.hibernate.cfg.domain.User;

/**
 * userDao
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/06/08 23:14
 */
public interface UserDao {
    /**
     * 添加用户实体
     *
     * @param user 实体
     * @author LinZhenNan lin_hehe@qq.com
     */
    void insert(User user);
}
