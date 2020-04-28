package org.lzn.many2many.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate 工具类
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/20 23:19
 */
public class HibernateUtils {

    private static SessionFactory sessionFactory;

    static {
        // 1. 加载配置
        Configuration configuration = new Configuration().configure();
        // 2. 根据 Configuration 配置信息创建 SessionFactory
        sessionFactory = configuration.buildSessionFactory();
        // 虚拟机关闭时释放资源
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("虚拟机关闭！释放资源！");
            sessionFactory.close();
        }));
    }

    /**
     * 获取 session
     *
     * @return org.hibernate.Session
     * @author LinZhenNan lin_hehe@qq.com 2020-04-20 23:26
     */
    public static Session opSession() {
        return sessionFactory.openSession();
    }

    /**
     * 获取与当前线程绑定的 session
     *
     * @return org.hibernate.Session
     * @author LinZhenNan lin_hehe@qq.com 2020-04-20 23:26
     */
    public static Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
