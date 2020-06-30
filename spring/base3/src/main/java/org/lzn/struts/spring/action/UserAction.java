package org.lzn.struts.spring.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.lzn.hibernate.cfg.domain.User;
import org.lzn.hibernate.cfg.service.UserService;

/**
 * struts 整合 spring：spring 创建 action
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/06/15 20:53
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {

    /**
     * 1. 封装数据
     */
    private User user = new User();

    /**
     * 2. service
     */
    private UserService userService;

    @Override
    public User getModel() {
        return user;
    }

    //
    // 功能
    // ------------------------------------------------------------------------------

    /**
     * 注册
     *
     * @return java.lang.String
     * @author LinZhenNan lin_hehe@qq.com
     */
    public String register() {
        userService.save(user);
        return SUCCESS;
    }

    //
    // setter/getter
    // ------------------------------------------------------------------------------

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
