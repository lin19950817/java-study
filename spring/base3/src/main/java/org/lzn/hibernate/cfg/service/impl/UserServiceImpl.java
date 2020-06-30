package org.lzn.hibernate.cfg.service.impl;

import org.lzn.hibernate.cfg.dao.UserDao;
import org.lzn.hibernate.cfg.domain.User;
import org.lzn.hibernate.cfg.service.UserService;

/**
 * UserService 实现类
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/06/08 23:41
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    @Override
    public void save(User user) {
        userDao.insert(user);
    }

    //
    // setter/getter
    // ------------------------------------------------------------------------------

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
