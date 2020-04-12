package base2.org.lzn.web.action;

import base2.org.lzn.domain.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.validation.SkipValidation;

/**
 * 编程式验证：
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/06 22:34
 */
public class Demo6Action extends ActionSupport implements ModelDriven<User> {

    /**
     * 定义数据模型对象，使用模型驱动，必须实例化
     */
    private User user = new User();

    @Override
    public User getModel() {
        return user;
    }

    /**
     * 在 struts2 的框架中，它也提供了一个 Map<表单的字段名，错误提示>
     *     我们要做的：
     *          往 map 中存放错误信息
     *
     *  编程式验证：
     *      1. 动作类必须继承 ActionSupport
     *      2. 重写 validate 方法
     *
     *  voidate 方法会在所有动作方法之前，进行校验
     *  解决验证所有动作方法的问题：
     *      第一种方式：
     *          使用 @SkipValidation 的注解
     *      第二种方式：
     *          定义验证方法的名称：validate + 动作名称（首字母大写）
     *
     * @author LinZhenNan lin_hehe@qq.com 2020-04-08 16:07
     */
    public void validateAddUser() {
        System.out.println("validate 执行了");
        if (StringUtils.isEmpty(user.getUsername())) {
            // 存入错误信息，直接调用父类的 addFieldError 方法，存入错误信息。第一参数是表单 name 属性的值。第二个参数是错误提示
            addFieldError("username", "请输入用户名");
        }
    }

    public String addUser() {
        System.out.println(user);
        return null;
    }

    public String addUserNotValid() {
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
