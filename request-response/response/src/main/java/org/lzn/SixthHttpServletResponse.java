package org.lzn;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 重定向
 *
 * @author LinZhenNan lin.zhennan@hand-china.com 2019/11/08 17:22
 */
public class SixthHttpServletResponse extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("SixthHttpServletResponse start");

        // 告诉客户端要重定向资源
//        resp.setStatus(302);
        // 告诉浏览器访问哪个 URL
//        resp.setHeader("location", "/response/demo1");

//        resp.sendRedirect(req.getContextPath() + "/demo1");
        // 可以跳转到其他应用
        resp.sendRedirect("https://www.baidu.com");

        System.out.println("SixthHttpServletResponse end");
    }
}
