package org.lzn.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * ServletContextListener 的实现类
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/03/04 0:54
 */
public class MyServletContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletContext 对象创建了");
    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContext 对象销毁了");
    }
}
