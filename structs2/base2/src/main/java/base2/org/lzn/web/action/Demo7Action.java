package base2.org.lzn.web.action;

import base2.org.lzn.domain.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang3.StringUtils;

/**
 * 声明式验证：
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/06 22:34
 */
public class Demo7Action extends ActionSupport implements ModelDriven<User> {

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
