package org.lzn;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 转发接收者
 *
 * @author LinZhenNan lin.zhennan@hand-china.com 2019/11/12 11:05
 */
public class FifthHttpServletRequest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 从 request对象中获取域的值
        System.out.println(req.getAttribute("hehe"));
        resp.getWriter().write("FifthHttpServletRequest");
        System.out.println("FifthHttpServletRequest");
    }
}
