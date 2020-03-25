package org.lzn.domain;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;
import java.io.Serializable;

/**
 * HttpSessionActivationListener 实现类
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/03/17 22:59
 */
public class Role implements Serializable, HttpSessionActivationListener {

    private String name;
    private String position;

    @Override
    public void sessionWillPassivate(HttpSessionEvent se) {
        System.out.println("钝化");
    }

    @Override
    public void sessionDidActivate(HttpSessionEvent se) {
        System.out.println("活化");
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
