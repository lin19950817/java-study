package org.lzn.domain;

/**
 * 实体
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/03/25 21:57
 */
public class User {

    private int id;
    private String username;
    private String password;

    //
    // setter/getter
    // ------------------------------------------------------------------------------

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
