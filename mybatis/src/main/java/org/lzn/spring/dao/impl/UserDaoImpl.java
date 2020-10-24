package org.lzn.spring.dao.impl;

import org.lzn.po.User;
import org.lzn.spring.dao.UserDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * dao实现类
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/10/24 21:26
 */
public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {
    @Override
    public User getUserById(int id) {
        String statementId = "quickstart.findUserById";
        return this.getSqlSession().selectOne(statementId, id);
    }
}
