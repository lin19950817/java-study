package org.lzn.one2one.domain;

/**
 * 公司<br>
 *     一对一关系，一个公司拥有一个地址
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/28 23:26
 */
public class Company {
    private Integer id;
    private String name;
    
    /**
     * 一对一关系，地址
     */
    private Address address;

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
