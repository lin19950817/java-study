package org.lzn;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 请求头
 *
 * @author LinZhenNan lin_hehe@qq.com 2019/11/10 12:05
 */
public class SecondHttpServletRequest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获得请求消息头的信息（浏览器类型）
        showUserAgent(req);

        // 获得所有请求消息头
        showHeader(req);

        resp.getWriter().write("SecondHttpServletRequest");
    }

    /**
     * 显示所有的请求消息头
     *
     * @param req 请求
     * @author LinZhenNan lin_hehe@qq.com 2019-11-10 12:41
     */
    private void showHeader(HttpServletRequest req) {
        // 获取所有消息头的 name
        Enumeration names = req.getHeaderNames();

        // 遍历获取消息头的 name
        while (names.hasMoreElements()) {
            String name = String.valueOf(names.nextElement());

            // 获取消息头 name对应的所有值
            Enumeration values = req.getHeaders(name);

            //遍历获取消息头的 name对应的值
            while (values.hasMoreElements()) {
                String value = String.valueOf(values.nextElement());
                System.out.println(name + " : " + value);
            }
        }
    }

    /**
     * 获得浏览器类型
     *
     * @param req 请求
     * @author LinZhenNan lin_hehe@qq.com 2019-11-10 12:23
     */
    private void showUserAgent(HttpServletRequest req) {
        // 获得请求消息头的信息（浏览器类型）
        String header = req.getHeader("User-Agent");
        System.out.println(header);

        String ie = "msie";
        String firefox = "firefox";
        String chrome = "chrome";

        if (header.toLowerCase().contains(ie)) {
            System.out.println("使用的是 IE浏览器");
        } else if (header.toLowerCase().contains(firefox)) {
            System.out.println("使用的是火狐浏览器");
        } else if (header.toLowerCase().contains(chrome)) {
            System.out.println("使用的是谷歌浏览器");
        } else {
            System.out.println("使用的是其他浏览器");
        }
    }
}
