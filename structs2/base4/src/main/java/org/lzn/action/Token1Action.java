package org.lzn.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * token 防止表单重复提交
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/16 21:08
 */
public class Token1Action extends ActionSupport {

    private String name;

    public String login() {
        System.out.println(name);
        return SUCCESS;
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
