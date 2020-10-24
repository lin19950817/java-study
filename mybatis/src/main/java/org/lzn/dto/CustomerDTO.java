package org.lzn.dto;

import org.lzn.po.Order;

import java.io.Serializable;
import java.util.List;

/**
 * 客户DTO
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/10/18 23:29
 */
public class CustomerDTO implements Serializable {
    /**
     * 客户id
     */
    private Long customerId;
    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 客户的订单
     */
    private List<Order> orders;

    //
    // setter/getter/toString、
    // ------------------------------------------------------------------------------

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", orders=" + orders +
                '}';
    }
}
