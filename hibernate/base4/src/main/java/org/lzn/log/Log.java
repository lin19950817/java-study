package org.lzn.log;

import org.hibernate.Session;
import org.junit.Test;
import org.lzn.util.HibernateUtils;

/**
 * Hibernate 日志
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/28 23:06
 */
public class Log {
    @Test
    public void demo1() {
        Session session = HibernateUtils.opSession();
        session.beginTransaction();

        System.out.println(session);

        session.getTransaction().commit();
        session.close();
    }
}
