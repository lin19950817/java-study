package org.lzn.hibernate.dao.impl;

import org.lzn.hibernate.cfg.dao.UserDao;
import org.lzn.hibernate.cfg.domain.User;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

/**
 * Spring 整合 Hibernate
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/06/14 22:12
 */
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
    @Override
    public void insert(User user) {
        this.getHibernateTemplate().save(user);
    }
}
