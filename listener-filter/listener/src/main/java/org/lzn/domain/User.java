package org.lzn.domain;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.io.Serializable;

/**
 * HttpSessionBindingListener 实现类<br>
 *     监听 session 绑定 javaBean
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/03/17 21:59
 */
public class User implements HttpSessionBindingListener {
    private String name;
    private int age;

    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println("user 对象被绑定了");
    }

    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println("user 对象解除绑定");
    }

    //
    // setter、getter
    // ------------------------------------------------------------------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
