package org.lzn.xml.construct;

/**
 * 属性注入 构造方法注入
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/09 23:15
 */
public class User {
    private Integer uid;
    private String username;
    private Integer age;

    //
    // 构造方法
    // ------------------------------------------------------------------------------

    public User(Integer uid, String username) {
        this.uid = uid;
        this.username = username;
    }

    public User(String username, Integer age) {
        this.username = username;
        this.age = age;
    }

    //
    // setter/getter
    // ------------------------------------------------------------------------------

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    //
    // toString
    // ------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}
