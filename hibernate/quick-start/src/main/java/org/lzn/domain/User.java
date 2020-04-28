package org.lzn.domain;

/**
 * user 实体
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/18 23:03
 */
public class User {
    private Integer uid;
    private String username;
    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
