package org.lzn.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.List;

/**
 * HttpSessionListener 实现类<br>
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/03/18 22:42
 */
public class MySessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        // 得到 application 对象中的 list 集合
        ServletContext application = session.getServletContext();
        // 得到 session 对象，并放入到 list 集合中
        List<HttpSession> list = (List<HttpSession>) application.getAttribute("sessions");

        list.add(session);
        System.out.println("添加了" + session.getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }
}
