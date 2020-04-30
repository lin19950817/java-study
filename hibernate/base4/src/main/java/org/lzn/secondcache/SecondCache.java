package org.lzn.secondcache;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Test;
import org.lzn.secondcache.domain.Customer;
import org.lzn.secondcache.domain.Order;
import org.lzn.util.HibernateUtils;

import java.util.List;

/**
 * 二级缓存
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/29 22:00
 */
public class SecondCache {
    @Test
    public void demo1() {
        Session session = HibernateUtils.opSession();
        session.beginTransaction();

        Customer customer = session.get(Customer.class, 1);

        // 清空一级缓存
        session.clear();

        // 查二级缓存
        Customer customer2 = session.get(Customer.class, 1);

        // 结果 false，二级缓存在缓存数据时，并不是以对象的形式缓存。缓存的是对象数据的散列。每次从二级缓存拿，会在一级缓存中组装成对象
        System.out.println(customer == customer2);

        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void demo2() {
        Session session = HibernateUtils.opSession();
        session.beginTransaction();

        Customer customer = session.get(Customer.class, 1);

        for (Order order : customer.getOrders()) {
            System.out.println(order.getName());
        }

        // 清空缓存
        session.clear();

        Customer customer2 = session.get(Customer.class, 1);

        // 集合只缓存 id，然后通过 id 查询缓存，缓存没有则查询数据库
        for (Order order : customer2.getOrders()) {
            System.out.println(order.getName());
        }

        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void demo3() {
        Session session = HibernateUtils.opSession();
        session.beginTransaction();

        Query query = session.createQuery("from Customer");

        // 使用二级缓存的查询缓存，查询时，会先从二级缓存那个取结果，取不到则执行语句并将结果放入二级缓存中
        query.setCacheable(true);

        List<Customer> list = query.list();

        session.clear();

        Query query2 = session.createQuery("from Customer");

        query2.setCacheable(true);

        List<Customer> list2 = query2.list();


        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void demo4() {
        Session session = HibernateUtils.opSession();
        session.beginTransaction();

        Customer customer = session.get(Customer.class, 1);

        session.createQuery("update Customer set name = :name where id = :id")
                .setParameter("name", "rose").setParameter("id", 1).executeUpdate();

        session.clear();

        // 发送 sql 语句查询，确保二级缓存中的数据是有效的
        Customer customer2 = session.get(Customer.class, 1);

        session.getTransaction().commit();
        session.close();
    }
}
