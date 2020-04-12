package base2.org.lzn.web.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 动态参数封装
 * 第一种情况，数据模型与动作类写在一起
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/06 22:34
 */
public class Demo2Action extends ActionSupport {

    private String username;
    private int age;

    public String addUser() {
        System.out.println(this.username + "\t" + age);
        return null;
    }

    //
    // setter/getter
    // ------------------------------------------------------------------------------

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
