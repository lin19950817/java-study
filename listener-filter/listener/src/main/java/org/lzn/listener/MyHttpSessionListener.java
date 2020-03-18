package org.lzn.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * ServletContextListener 的实现类
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/03/04 23:23
 */
public class MyHttpSessionListener implements HttpSessionListener {
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("HttpSession 对象创建了");
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("HttpSession 对象销毁了");
    }
}
