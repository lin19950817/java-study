package org.lzn.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * demo1
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/11 16:48
 */
public class Demo1Action extends ActionSupport {

    public String save() {
        System.out.println("Demo1Action save() 执行了");
        return SUCCESS;
    }

    public String update() {
        System.out.println("Demo1Action update() 执行了");
        return SUCCESS;
    }
}
