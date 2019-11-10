package org.lzn;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 获得请求行
 *
 * @author LinZhenNan lin_hehe@qq.com 2019/11/10 11:20
 */
public class FirstHttpServletRequest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求方式
        System.out.println(req.getMethod());

        // 获取客户端发出请求时的完整 URL
        System.out.println(req.getRequestURL());

        // 获取请求行中的资源名部分 URI
        System.out.println(req.getRequestURI());

        // 获取当前应用虚拟目录
        System.out.println(req.getContextPath());


        // 获取请求行中的参数部分
        System.out.println(req.getQueryString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 请求实例
        System.out.println(req.getClass());

        // 响应实例
        System.out.println(resp.getClass());
    }
}
