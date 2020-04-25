package org.lzn.cache;

import org.hibernate.Session;
import org.junit.Test;
import org.lzn.domain.User;
import org.lzn.util.HibernateUtils;

import java.util.List;

/**
 * Hibernate 一级缓存
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/22 23:14
 */
public class FirstLevelCache {

    @Test
    public void demo1() {
        Session session = HibernateUtils.opSession();
        session.beginTransaction();

        // 发送 select 语句，从数据库取出记录，并封装成对象并放入缓存中
        User u = (User) session.get(User.class, 1);

        // 再次查询时，会从缓存中查找，不会发送 select
        User u2 = (User) session.get(User.class, 1);

        // 再次查询时，会从缓存中查找，不会发送 select
        User u3 = (User) session.get(User.class, 1);

        session.getTransaction().commit();
        session.close();
    }

    /**
     * HQL 语句批量查询时，查询结果是否会进入缓存
     *
     * @author LinZhenNan lin_hehe@qq.com 2020-04-23 0:05
     */
    @Test
    public void demo2() {
        Session session = HibernateUtils.opSession();
        session.beginTransaction();

        // 执行 hql 语句查询
        List<User> listUser = session.createQuery("from User").list();

        // 再次查询，是否执行 sql
        List<User> listUser2 = session.createQuery("from User").list();

        // 再次查询，是否执行 sql
        List<User> listUser3 = session.createQuery("from User").list();

        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void demo3() {
        Session session = HibernateUtils.opSession();
        session.beginTransaction();

        // 执行 hql 语句查询
        List<User> listUser = session.createQuery("from User").list();

        // 不会执行 sql
        User user = (User) session.get(User.class, 2);

        session.getTransaction().commit();
        session.close();
    }

    /**
     * SQL 语句批量查询时，查询结果是否会进入缓存
     *
     * @author LinZhenNan lin_hehe@qq.com 2020-04-23 0:05
     */
    @Test
    public void demo4() {
        Session session = HibernateUtils.opSession();
        session.beginTransaction();

        // 执行 hql 语句查询
        List<User> listUser = session.createSQLQuery("select * from t_user").list();

        // 再次查询，是否执行 sql
        List<User> listUser2 = session.createSQLQuery("select * from t_user").list();

        // 再次查询，是否执行 sql
        List<User> listUser3 = session.createSQLQuery("select * from t_user").list();

        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void demo5() {
        Session session = HibernateUtils.opSession();
        session.beginTransaction();

        // 执行 hql 语句查询
        List<User> listUser = session.createSQLQuery("select * from t_user").list();

        // 不会执行 sql
        User user = (User) session.get(User.class, 2);

        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void demo6() {
        Session session = HibernateUtils.opSession();
        session.beginTransaction();

        // 执行 hql 语句查询
        List<User> listUser = session.createSQLQuery("select * from t_user").addEntity(User.class).list();

        // 不会执行 sql
        User user = (User) session.get(User.class, 2);

        session.getTransaction().commit();
        session.close();
    }
}
