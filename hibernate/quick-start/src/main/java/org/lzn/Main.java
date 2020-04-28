package org.lzn;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.lzn.domain.User;

/**
 * 入口
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/18 23:12
 */
public class Main {
    public static void main(String[] args) {
        // 1. 读取配置文件
        Configuration configure = new Configuration().configure();
        // 2. 根据配置创建 Factory
        SessionFactory sessionFactory = configure.buildSessionFactory();
        // 3. 共拓获得操作数据库的 session 对象
        Session session = sessionFactory.openSession();
        // 4. 操作数据库
        User user = new User();
        user.setUsername("hehe");
        user.setPassword("18");
        session.save(user);
        // 5. 关闭资源
        session.close();
        sessionFactory.close();
    }
}
