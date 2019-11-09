package org.lzn;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * 刷新功能
 *
 * @author LinZhenNan lin.zhennan@hand-china.com 2019/11/08 16:19
 */
public class FifthHttpServletResponse extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 一秒刷新一次
//        resp.setIntHeader("refresh", 1);
//        resp.getWriter().write(Long.toString(System.currentTimeMillis()));

        // 3秒跳转
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write("3秒后跳转！！！");
        resp.setHeader("refresh", "3;url=/response/demo1");
    }
}
