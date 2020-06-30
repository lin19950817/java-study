package org.lzn.hibernate.cfg.dao.impl;

import org.lzn.hibernate.cfg.dao.UserDao;
import org.lzn.hibernate.cfg.domain.User;
import org.springframework.orm.hibernate5.HibernateTemplate;

/**
 * UserDao 实现类
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/06/08 23:34
 */
public class UserDaoImpl implements UserDao {
    /**
     * spring 注入模板
     */
    private HibernateTemplate hibernateTemplate;
    @Override
    public void insert(User user) {
        this.hibernateTemplate.save(user);
    }

    //
    // setter/getter
    // ------------------------------------------------------------------------------

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
}
