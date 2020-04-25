package org.lzn.session;

import org.hibernate.Session;
import org.junit.Test;
import org.lzn.domain.User;
import org.lzn.util.HibernateUtils;

/**
 * 了解 Session 其他 Api
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/23 22:12
 */
public class SessionApi {
    @Test
    public void demo1() {
        Session session = HibernateUtils.opSession();
        session.beginTransaction();

        // 通过 get 方法，得到持久态对象
        User u = (User) session.get(User.class, 1);

        // 将 User 对象与 session 的关联移除
        session.evict(u);

        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void demo2() {
        Session session = HibernateUtils.opSession();
        session.beginTransaction();

        // 通过 get 方法，得到持久态对象
        User u = (User) session.get(User.class, 1);

        // 清空一级缓存
        session.clear();

        // 再次查询对象，结果没有使用一级缓存而是发送 sql
        User u2 = (User) session.get(User.class, 1);

        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void demo3() {
        Session session = HibernateUtils.opSession();
        session.beginTransaction();

        // 通过 get 方法，得到持久态对象
        User u = (User) session.get(User.class, 1);

        // 将缓存中的对象立刻与数据库同步，会再发送一个 sql 语句
        session.refresh(u);

        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void demo4() {
        Session session = HibernateUtils.opSession();
        session.beginTransaction();

        // 通过 get 方法，得到持久态对象
        User u = (User) session.get(User.class, 5);
        u.setUsername("demo4");

        // 立刻提交 session 缓存中的对象
        session.flush();

        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void demo5() {
        Session session = HibernateUtils.opSession();
        session.beginTransaction();

        User user = new User();
        user.setUsername("demo5");
        user.setPassword("demo5");

        session.saveOrUpdate(user);

        User user2 = new User();
        user2.setUid(7);
        user2.setUsername("demo5-007");
        user2.setPassword("demo5-007");

        session.saveOrUpdate(user2);

        session.getTransaction().commit();
        session.close();
    }
}
