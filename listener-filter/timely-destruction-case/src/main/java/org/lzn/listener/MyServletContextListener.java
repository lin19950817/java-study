package org.lzn.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * ServletContextListener 实现类<br>
 *     我们可以做一个 HttpSessionListener，当 session 对象创建时，就将这个 session 对象装入到一个集合中.<br>
 *     将集合 List<HttpSession> 保存到 ServletContext 域中。
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/03/18 22:23
 */
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 通过事件源对象得到事件源（ServletContext）
        ServletContext application = sce.getServletContext();
        // 创建一个集合用于存储所有 session 对象，需要线程安全
        final List<HttpSession> httpSessions = Collections.synchronizedList(new ArrayList<>(16));
        // 把集合放到 application 域中
        application.setAttribute("sessions", httpSessions);

        // 创建一个计时器
        Timer t = new Timer();
        // 延迟 2 秒执行，间隔 5 秒执行一次
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("开始扫描。。。");
                for (Iterator iterator = httpSessions.iterator(); iterator.hasNext();) {
                    HttpSession session = (HttpSession) iterator.next();
                    Long l = System.currentTimeMillis() - session.getLastAccessedTime();
                    // 如果时间打印 5 秒，把 session 销毁
                    if (l > 5000) {
                        System.out.println("session 移除了" + session.getId());
                        // 销毁 session
                        session.invalidate();
                        // 从集合中移除
                        iterator.remove();
                    }
                }
            }
        }, 2000, 5000);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
