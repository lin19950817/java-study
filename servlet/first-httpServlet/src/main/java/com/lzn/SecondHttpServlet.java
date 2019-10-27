package com.lzn;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * description
 *
 * @author LinZhenNan 2019/09/24 22:54
 */
public class SecondHttpServlet extends HttpServlet {
    /**
     * 监控线程
     */
    private int i = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 查看不同请求是否同一实例
        System.out.println(++i);

        System.out.println("this's SecondHttpServlet doGet()");

        // 打印远程请求的 ip地址
        System.out.println(req.getRemoteAddr());

        // 打印本地 ip地址
        System.out.println(req.getLocalAddr());
        System.out.println(req.getRemoteHost());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("this's SecondHttpServlet doPost()");

        // 获得配置文件中的信息
        String encoding = getServletConfig().getInitParameter("encoding");
        System.out.println(encoding);
    }
}
