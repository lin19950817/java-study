package com.lzn;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 第二个
 *
 * @author LinZhenNan lin.zhennan@hand-china.com 2019/10/24 11:42
 */
public class SecondServletContext extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获得 Attribute
        ServletContext context = getServletContext();
        Object object = context.getAttribute("name");
        String name = object instanceof String && object != null ? (String) object : null;
        System.out.println(name);

        // 获取全局配置信息
        String encoding = context.getInitParameter("encoding");
        System.out.println(encoding);

        System.out.println("SecondServletContext.doGet()");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
