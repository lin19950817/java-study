package org.lzn.po;

/**
 * 客户实体，与订单存在一对多关系
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/10/18 23:26
 */
public class Customer {
    /**
     * 主键
     */
    private Long id;
    /**
     * 客户名称
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
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
