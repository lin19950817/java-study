package org.lzn.service;

import org.lzn.dao.UserDao;
import org.lzn.domain.User;

import java.sql.SQLException;

/**
 * service 层
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/03/25 22:40
 */
public class UserService {

    UserDao userDao = new UserDao();

    /**
     * 通过用户名和密码获取 User
     *
     * @param name 用户名
     * @param pwd 密码
     * @author LinZhenNan lin_hehe@qq.com 2020-03-25 22:42
     * @return org.lzn.domain.User
     */
    public User getUser(String name, String pwd) {
        try {
            return userDao.getUserByUsernameWithPassword(name, pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new User();
    }
}
