package org.lzn;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 请求包含
 *
 * @author LinZhenNan lin.zhennan@hand-china.com 2019/11/12 14:47
 */
public class SixthHttpServletRequest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("SixthHttpServletRequest start");
        resp.getWriter().write("SixthHttpServletRequest start");

        // 请求包含
        req.getRequestDispatcher("/demo5").include(req, resp);

        resp.getWriter().write("SixthHttpServletRequest end");
        System.out.println("SixthHttpServletRequest end");
    }
}
