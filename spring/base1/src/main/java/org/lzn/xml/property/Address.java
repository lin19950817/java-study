package org.lzn.xml.property;

/**
 * 地址
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/10 16:24
 */
public class Address {
    private String detailAddress;
    private Integer telephone;

    //
    // setter/getter/toString
    // ------------------------------------------------------------------------------

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public Integer getTelephone() {
        return telephone;
    }

    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Address{" +
                "detailAddress='" + detailAddress + '\'' +
                ", telephone=" + telephone +
                '}';
    }
}
