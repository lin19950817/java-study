package org.lzn.xml.property;

/**
 * description
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/10 16:23
 */
public class Person {
    private String name;
    private Integer age;

    private Address companyAddress;
    private Address homeAddress;

    //
    // setter/getter/toString
    // ------------------------------------------------------------------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Address getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(Address companyAddress) {
        this.companyAddress = companyAddress;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", companyAddress=" + companyAddress +
                ", homeAddress=" + homeAddress +
                '}';
    }
}
