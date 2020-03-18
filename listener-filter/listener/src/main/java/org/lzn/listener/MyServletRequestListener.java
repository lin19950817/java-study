package org.lzn.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * ServletRequestListener 的实现类
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/03/04 23:50
 */
public class MyServletRequestListener implements ServletRequestListener {
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("ServletRequest 销毁了");
    }

    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("ServletRequest 创建了");
    }
}
