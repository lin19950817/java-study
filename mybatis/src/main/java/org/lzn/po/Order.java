package org.lzn.po;

import java.io.Serializable;

/**
 * 订单实体，与客户存在一对一关系
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/10/18 23:28
 */
public class Order implements Serializable {
    /**
     * 主键
     */
    private Long id;
    /**
     * 商品名称
     */
    private String name;

    //
    // setter/getter/toString
    // ------------------------------------------------------------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
