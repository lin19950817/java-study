package org.lzn.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * token 防止表单重复提交
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/16 21:08
 */
public class Token2Action extends ActionSupport {

    private String name;

    public String login() {
        System.out.println(name);
        return INPUT;
    }

    //
    // setter/getter
    // ------------------------------------------------------------------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
