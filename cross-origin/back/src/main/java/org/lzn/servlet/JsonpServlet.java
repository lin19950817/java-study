package org.lzn.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JSONP servlet
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/07/01 23:37
 */
public class JsonpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(this.getClass().getSimpleName());
        System.out.println("sessionId：" + req.getSession().getId());
        // 请求类型
        resp.setHeader("Content-Type", "application/json;charset=utf-8");
        // 请求参数获取回调函数名称
        String callback = req.getParameter("callback");
        // 数据
        String json = "{\"success\":\"操作成功\", \"info\":\"hehe\"}";
        // 响应，执行回调函数 "(函数(参数))"
        String response = callback + "(" + json + ")";
        resp.getWriter().print(response);
    }
}
