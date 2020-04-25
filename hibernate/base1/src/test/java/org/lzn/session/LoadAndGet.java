package org.lzn.session;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.lzn.domain.User;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 查询 load 和 get 区别
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/19 21:01
 */
public class LoadAndGet {
    @Test
    public void get() {
        // 读取配置文件
        Configuration configure = new Configuration().configure();
        // 根据配置创建 Factory
        SessionFactory sessionFactory = configure.buildSessionFactory();
        // 获得操作数据库的 session 对象
        Session session = sessionFactory.openSession();
        // 打开事务
        Transaction transaction = session.beginTransaction();
        User user = (User) session.get(User.class, 1);
        // 提交事务
        transaction.commit();
        // 关闭资源
        session.close();
        sessionFactory.close();

        System.out.println(user);
    }

    @Test
    public void load() {
        // 读取配置文件
        Configuration configure = new Configuration().configure();
        // 根据配置创建 Factory
        SessionFactory sessionFactory = configure.buildSessionFactory();
        // 获得操作数据库的 session 对象
        Session session = sessionFactory.openSession();
        // 打开事务
        Transaction transaction = session.beginTransaction();
        User user = (User) session.load(User.class, 1);
        // 提交事务
        transaction.commit();
        // 关闭资源
        session.close();
        sessionFactory.close();

        System.out.println(user);
    }

    @Test
    public void hql() {
        // 读取配置文件
        Configuration configure = new Configuration().configure();
        // 根据配置创建 Factory
        SessionFactory sessionFactory = configure.buildSessionFactory();
        // 获得操作数据库的 session 对象
        Session session = sessionFactory.openSession();
        // 打开事务
        Transaction transaction = session.beginTransaction();
        // Hql 语言
        Query query = session.createQuery("from User");
        // list 将语句执行，返回结果
        List<User> list = query.list();
        System.out.println(Arrays.toString(list.toArray()));
        // 提交事务
        transaction.commit();
        // 关闭资源
        session.close();
        sessionFactory.close();
    }

    @Test
    public void sql() {
        // 读取配置文件
        Configuration configure = new Configuration().configure();
        // 根据配置创建 Factory
        SessionFactory sessionFactory = configure.buildSessionFactory();
        // 获得操作数据库的 session 对象
        Session session = sessionFactory.openSession();
        // 打开事务
        Transaction transaction = session.beginTransaction();
        // sql 查询
        SQLQuery sqlQuery = session.createSQLQuery("select * from t_user");
        // addEntity 将查询结果封装到指定对象中
        sqlQuery.addEntity(User.class);
        // list 将语句执行，返回结果。没有 addEntity 方法时返回 List<Object>
        List list = sqlQuery.list();
        System.out.println(Arrays.toString(list.toArray()));
        // 提交事务
        transaction.commit();
        // 关闭资源
        session.close();
        sessionFactory.close();
    }

    @Test
    public void transaction() {
        // 读取配置文件
        Configuration configure = new Configuration().configure();
        // 根据配置创建 Factory
        SessionFactory sessionFactory = configure.buildSessionFactory();
        // 获得当前线程绑定的 session
        Session session = sessionFactory.getCurrentSession();
        Session session1 = sessionFactory.getCurrentSession();
        System.out.println(session == session1);
        // 事务关闭时，会自动把当前线程关联的 session 关闭，并删除
        session.beginTransaction().commit();
        Session session2 = sessionFactory.getCurrentSession();
        System.out.println(session == session2);
        // 关闭资源
        session.close();
        sessionFactory.close();
    }

    @Test
    public void cretiaria() {
        // 读取配置文件
        Configuration configure = new Configuration().configure();
        // 根据配置创建 Factory
        SessionFactory sessionFactory = configure.buildSessionFactory();
        // 获得操作数据库的 session 对象
        Session session = sessionFactory.openSession();
        // 打开事务
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(User.class);

        // criteria无条件查询则等于 select * from t_user
        List<User> list = criteria.list();
        System.out.println(Arrays.toString(list.toArray()));

        // 查找 username = 'hehe' 的记录。Restrictions 类包含多个查询条件的方法
        criteria.add(Restrictions.eq("username", "hehe"));

        // 查询一条记录，如果返回多条记录则报错
        User user = (User) criteria.uniqueResult();
        System.out.println(user);

        // 提交事务
        transaction.commit();
        // 关闭资源
        session.close();
        sessionFactory.close();
    }
}
