package base2.org.lzn.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据模型
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/07 20:46
 */
public class User implements Serializable {

    private String username;
    private int age;
    private Date birthday;

    //
    // setter/getter、toString
    // ------------------------------------------------------------------------------

    public String getUsername() {
        System.out.println("getUsername");
        return username;
    }

    public void setUsername(String username) {
        System.out.println("setUsername");
        this.username = username;
    }

    public int getAge() {
        System.out.println("getAge");
        return age;
    }

    public void setAge(int age) {
        System.out.println("setAge");
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }
}
