package org.lzn.one2many;

import org.hibernate.Session;
import org.junit.Test;
import org.lzn.one2many.domain.Customer;
import org.lzn.one2many.domain.Order;
import org.lzn.util.HibernateUtils;

/**
 * description
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/23 23:55
 */
public class OneToMany {

    /**
     * 测试 一对多 关系中，保存操作
     *      发送 5 个 sql，3 个 insert，2 个 update
     *      一的一方放弃维护关系后，只发送 3 个 sql，3 个 insert
     *
     * @author LinZhenNan lin_hehe@qq.com 2020-04-24 0:02
     */
    @Test
    public void demo1() {
        Session session = HibernateUtils.opSession();
        session.beginTransaction();

        Customer customer = new Customer();
        customer.setName("hehe");

        Order order1 = new Order();
        order1.setName("蜡烛");

        Order order2 = new Order();
        order2.setName("鞋子");

        // 维护关系
        customer.getOrders().add(order1);
        customer.getOrders().add(order2);

        // 维护关系
        order1.setCustomer(customer);
        order2.setCustomer(customer);

        // 保存
        session.save(customer);
        session.save(order1);
        session.save(order2);

        session.getTransaction().commit();
        session.close();
    }

    /**
     * 一对多关系中，删除
     *      删除顾客时，会先移除 Order 的外键，然后删除 Customer
     *      一的一方放弃维护关系后，删除报外键约束的错误
     *
     * @author LinZhenNan lin_hehe@qq.com 2020-04-24 20:29
     */
    @Test
    public void demo2() {
        Session session = HibernateUtils.opSession();
        session.beginTransaction();

        // 查询顾客
        Customer customer = (Customer) session.get(Customer.class, 1);

        // 删除顾客
        session.delete(customer);

        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void demo3() {
        Session session = HibernateUtils.opSession();
        session.beginTransaction();

        Customer customer = new Customer();
        customer.setName("hehe1");

        Order order1 = new Order();
        order1.setName("蜡烛a");

        Order order2 = new Order();
        order2.setName("鞋子b");

        // 维护关系
        customer.getOrders().add(order1);
        customer.getOrders().add(order2);

        // 保存
        session.save(customer);

        session.getTransaction().commit();
        session.close();
    }
}
