package org.lzn.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CORS 普通库跨域请求
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/07/08 0:10
 */
public class CorsCommonServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(this.getClass().getSimpleName());
        System.out.println("sessionId：" + req.getSession().getId());
        System.out.println("Referer:" + req.getHeader("Referer"));
        // * 表示接受任何 origin 的请求
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.getWriter().write("ok");
    }
}
