package org.lzn;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/03/02 0:08
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet");
        resp.setContentType("text/html;charset=utf-8");

        // 将请求头信息的 hehe 作为返回头信息
        resp.setHeader("hehe", req.getHeader("hehe"));
        PrintWriter out = resp.getWriter();
        out.println("this's the LoginServlet.doGet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost");

        resp.setContentType("text/html;charset=utf-8");

        // 将请求头信息的 hehe 作为返回头信息
        resp.setHeader("hehe", req.getHeader("hehe"));
        PrintWriter out = resp.getWriter();
        out.println("this's the LoginServlet.doGet");
    }
}
