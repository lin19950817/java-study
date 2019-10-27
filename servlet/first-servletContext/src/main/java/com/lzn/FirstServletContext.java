package com.lzn;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * context作为作用域
 *
 * @author LinZhenNan lin_hehe@qq.com 2019/10/24 11:31
 */
public class FirstServletContext extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 通过调用 GenericServlet的 getServletContext方法得到 ServletContext对象
        ServletContext context = getServletContext();

        // 向 ServletContext添加一个键值对
        context.setAttribute("name", "hehe");

        // 获取全局配置信息
        String encoding = context.getInitParameter("encoding");
        System.out.println(encoding);

        System.out.println("FirstServletContext.doGet()");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
