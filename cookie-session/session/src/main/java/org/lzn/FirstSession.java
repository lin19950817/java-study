package org.lzn;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 在 session 里存放数据
 *
 * @author LinZhenNan lin.zhennan@hand-china.com 2019/11/26 17:02
 */
public class FirstSession extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        // 得到一个 HttpSession 对象
        HttpSession session = req.getSession();
        session.setAttribute("name", name);

        resp.getWriter().write(session.getId());
    }
}
