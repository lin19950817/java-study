package base2.org.lzn.web.action;

import base2.org.lzn.domain.User;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 动态参数封装
 * 第二种情况，数据模型与动作类分开写
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/06 22:34
 */
public class Demo3Action extends ActionSupport {

    /**
     * 定义数据模型对象
     */
    private User user;

    public String addUser() {
        System.out.println(user.getUsername() + "\t" + user.getAge());
        return null;
    }

    //
    // setter/getter
    // ------------------------------------------------------------------------------

    public User getUser() {
        System.out.println("getUser");
        return user;
    }

    public void setUser(User user) {
        System.out.println("setUser");
        this.user = user;
    }
}
