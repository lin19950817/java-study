package org.lzn.secondcache.domain;

import java.util.Objects;

/**
 * 订单实体
 * 一个订单属于一个顾客
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/23 23:24
 */
public class Order {
    private Integer id;
    private String name;

    private Customer customer;

    //
    // setter/getter
    // ------------------------------------------------------------------------------

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    //
    // hashCode/equals
    // ------------------------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(name, order.name) &&
                Objects.equals(customer, order.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, customer);
    }
}
