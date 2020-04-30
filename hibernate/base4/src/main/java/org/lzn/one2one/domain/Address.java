package org.lzn.one2one.domain;

/**
 * 地址<br>
 *     一对一关系，一个地址属于一个公司
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/28 23:27
 */
public class Address {
    private Integer id;
    private String name;
    
    /**
     * 一对一关系，公司
     */
    private Company company;

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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
