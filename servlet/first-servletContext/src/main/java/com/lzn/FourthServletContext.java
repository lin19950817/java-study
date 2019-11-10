package com.lzn;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 第四
 *
 * @author LinZhenNan lin_hehe@qq.com 2019/10/27 22:00
 */
public class FourthServletContext extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FourthServletContext.doGet()");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FourthServletContext.doPost()");

        // 给 requset设置键值对
        req.setAttribute("key", "hehe");

        resp.getWriter().write("FourthServletContext.doPost()");

        System.out.println("FourthServletContext end");
    }
}
