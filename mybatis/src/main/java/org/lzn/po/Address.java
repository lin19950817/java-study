package org.lzn.po;

/**
 * 地址实体，与公司存在一对一关系
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/10/18 22:25
 */
public class Address {
    /**
     * 主键
     */
    private Long id;
    /**
     * 地址名称
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
        return "Address{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
