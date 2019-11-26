package org.lzn;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * description
 *
 * @author LinZhenNan lin.zhennan@hand-china.com 2019/11/26 17:08
 */
public class SecondSession extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 得到一个 HttpSession 对象
        HttpSession session = req.getSession();
        String name = (String)session.getAttribute("name");
        if (name != null) {
            System.out.println(name);
        } else {
            System.out.println("你不能直接访问此资源！");
        }

        resp.getWriter().write(session.getId());
    }
}
