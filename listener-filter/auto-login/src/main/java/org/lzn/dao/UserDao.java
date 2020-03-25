package org.lzn.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.lzn.domain.User;
import org.lzn.util.C3P0Util;

import java.sql.SQLException;

/**
 * dao 层
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/03/25 21:54
 */
public class UserDao {

    /**
     * 通过用户名和密码查询 User
     *
     * @param username 用户名
     * @param password 密码
     * @author LinZhenNan lin_hehe@qq.com 2020-03-25 22:35
     * @return org.lzn.domain.User
     */
    public User getUserByUsernameWithPassword(String username, String password) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDataSource());
        String sql = "select * from user where username=? and password=?";
        return queryRunner.query(sql, new BeanHandler<>(User.class), username, password);
    }
}
