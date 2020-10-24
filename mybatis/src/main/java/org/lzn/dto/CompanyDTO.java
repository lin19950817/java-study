package org.lzn.dto;

import org.lzn.po.Address;

/**
 * 公司DTO
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/10/18 22:44
 */
public class CompanyDTO {
    /**
     * 公司主键
     */
    private Long id;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 地址名称
     */
    private String addressName;

    /**
     * 地址信息
     */
    private Address address;

    //
    // setter/getter/toString
    // ------------------------------------------------------------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "CompanyDTO{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", addressName='" + addressName + '\'' +
                ", address=" + address +
                '}';
    }
}
