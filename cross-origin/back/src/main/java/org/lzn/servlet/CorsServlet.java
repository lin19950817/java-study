package org.lzn.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CORS
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/07/09 21:53
 */
public class CorsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(this.getClass().getSimpleName());
        System.out.println("sessionId：" + req.getSession().getId());
        System.out.println("Referer:" + req.getHeader("Referer"));

        // cookie
        System.out.println("Cookies:");
        Cookie[] cookies = req.getCookies();
        boolean flag = true;
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName() + "\t" + cookie.getValue());
            if ("hehe".equals(cookie.getName())) {
                flag = false;
            }
        }
        if (flag) {
            Cookie cookie = new Cookie("hehe", "asd123");
            cookie.setMaxAge(60 * 5);
            resp.addCookie(cookie);
        }

        // 跨域响应头
        // 允许来源 url，http://localhost:8081
        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");
        // 前端设置 XMLHttpRequest.withCredentials 时，后端需要设置 Access-Control-Allow-Credentials
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        // 允许前端获取头信息
        resp.setHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, Access-Control-Allow-Credentials");
        resp.getWriter().write("ok");
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 允许来源 url，http://localhost:8081
        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");
        // 前端设置 XMLHttpRequest.withCredentials 时，后端需要设置 Access-Control-Allow-Credentials
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        // 允许前端获取头信息
        resp.setHeader("Access-Control-Allow-Headers", "content-type, hehe");
        // 允许使用的访问的资源方法列表
        resp.setHeader("Access-Control-Allow-Methods", "POST, OPTIONS");
        // 可以被缓存多久，单位秒
        resp.setHeader("Access-Control-Max-Age", "300");
    }
}
