package org.lzn.state;

import org.hibernate.Session;
import org.junit.Test;
import org.lzn.domain.User;
import org.lzn.util.HibernateUtils;

/**
 * Hibernate 对象的三种状态
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/20 23:27
 */
public class HibernateState {

    @Test
    public void demo1() {
        Session session = HibernateUtils.opSession();
        session.beginTransaction();

        // 瞬时态
        User user = new User();
        // 瞬时态
        user.setUsername("demo1");
        // 瞬时态
        user.setPassword("pwd001");

        // 持久态
        session.save(user);

        // 持久态
        session.getTransaction().commit();
        // 游离态
        session.close();
    }

    /**
     * 瞬时态 转为 游离态<br>
     *     瞬时态：没有关联，没有 id
     *     游离态：没有关联，有 id（与数据库中对应的 id）
     *
     * @author LinZhenNan lin_hehe@qq.com 2020-04-22 22:21
     */
    @Test
    public void demo2() {
        Session session = HibernateUtils.opSession();
        session.beginTransaction();

        // 瞬时态
        User user = new User();

        // 游离态
        user.setUid(1);

        session.getTransaction().commit();
        session.close();
    }

    /**
     * 持久态 转化 瞬时态<br>
     *     持久态：有关联，有 id
     *     瞬时态：无关联，无 id
     *
     * @author LinZhenNan lin_hehe@qq.com 2020-04-22 22:24
     */
    @Test
    public void demo3() {
        Session session = HibernateUtils.opSession();
        session.beginTransaction();

        // 通过 get 方法，得到持久态对象
        User u = (User) session.get(User.class, 1);

        session.getTransaction().commit();
        session.close();

        // 瞬时态
        u.setUid(null);
    }

    /**
     * 持久态 转为 瞬时态<br>
     *     持久态：有关联，有 id
     *     瞬时态：无关联，无 id
     *
     * @author LinZhenNan lin_hehe@qq.com 2020-04-22 22:27
     */
    @Test
    public void demo4() {
        Session session = HibernateUtils.opSession();
        session.beginTransaction();

        // 通过 get 方法，得到持久态对象
        User u = (User) session.get(User.class, 1);

        // 将 User 对象与 session 的关联移除
        session.evict(u);

        // 瞬时态
        u.setUid(null);

        // 持久态
        session.save(u);

        session.getTransaction().commit();
        session.close();
    }

    /**
     * 持久态 转为 游离态<br>
     *     只需要将 session 的关联取消
     *
     * @author LinZhenNan lin_hehe@qq.com 2020-04-22 22:30
     */
    @Test
    public void demo5() {
        Session session = HibernateUtils.opSession();
        session.beginTransaction();

        // 通过 get 方法，得到持久态对象
        User u = (User) session.get(User.class, 1);

        // 游离态，将 User 对象与 session 的关联移除
        session.evict(u);

        session.getTransaction().commit();
        session.close();
    }

    /**
     * 游离态 转为 瞬时态<br>
     *     移除 id
     *
     * @author LinZhenNan lin_hehe@qq.com 2020-04-22 22:32
     */
    @Test
    public void demo6() {
        Session session = HibernateUtils.opSession();
        session.beginTransaction();

        // 通过 get 方法，得到持久态对象
        User u = (User) session.get(User.class, 1);

        // 游离态，将 User 对象与 session 的关联移除
        session.evict(u);

        // 瞬时态
        u.setUid(null);

        session.getTransaction().commit();
        session.close();
    }

    /**
     * 游离态 转为 持久态<br>
     *     是否与 session 关联
     *
     * @author LinZhenNan lin_hehe@qq.com 2020-04-22 22:33
     */
    @Test
    public void demo7() {
        Session session = HibernateUtils.opSession();
        session.beginTransaction();

        // 通过 get 方法，得到持久态对象
        User u = (User) session.get(User.class, 1);

        // 游离态，将 User 对象与 session 的关联移除
        session.evict(u);

        // 持久态，会执行 sql
        session.update(u);

        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void demo8() {
        Session session = HibernateUtils.opSession();
        session.beginTransaction();

        // 通过 get 方法，得到持久态对象
        User u = (User) session.get(User.class, 1);

        // 修改 id
        u.setUid(10086);

        // 持久态
        session.update(u);

        session.getTransaction().commit();
        session.close();
    }
}
