package org.lzn.secondcache.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 顾客实体
 *      一个顾客可以拥有多个订单
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/23 23:23
 */
public class Customer {
    private Integer id;
    private String name;
    
    /**
     * 在 1 的一方使用集合表达持有多的一方
     */
    private Set<Order> orders = new HashSet<>();

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

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
