package org.lzn.po;

/**
 * 公司实体，与地址存在一对一关系
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/10/18 22:21
 */
public class Company {
    /**
     * 主键
     */
    private Long id;
    /**
     * 公司名称
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
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
