package base2.org.lzn.web.action;

import base2.org.lzn.domain.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang3.StringUtils;

/**
 * 动态参数封装，模型驱动，建议开发中使用<br>
 * 第三种情况，想使用模型驱动，数据模型与动作类分开写<br>
 * 实现模型驱动的步骤：<br>
 *     1. 实现一个 ModelDriven 的接口<br>
 *     2. 实现接口中的抽象方法 getModel();<br>
 *     3. 在使用模型驱动的时候，数据模型必须有我们自己来实例化
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/06 22:34
 */
public class Demo4Action extends ActionSupport implements ModelDriven<User> {

    /**
     * 定义数据模型对象，使用模型驱动，必须实例化
     */
    private User user = new User();

    @Override
    public User getModel() {
        return user;
    }

    public String addUser() {
        System.out.println(user);
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
