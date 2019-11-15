package org.lzn;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * demo2, path/demo2
 * 用来了解 Cookie 的 path
 *
 * @author LinZhenNan lin.zhennan@hand-china.com 2019/11/15 15:34
 */
public class SecondCookie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        // 获取客户端所有的 cookie对象
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for(Cookie item : cookies) {
                // 获取 cookie
                if ("lastAccessTime".equals(item.getName())) {
                    writer.write("你的最后访问时间为：" + new Date().toLocaleString());
                    break;
                }
            }
        }
        // 创建一个 cookie，key：lastAccessTime，value：当前时间戳
        Cookie cookie = new Cookie("lastAccessTime", String.valueOf(System.currentTimeMillis()));
        // 生存时间为 5 分钟
        cookie.setMaxAge(60 * 5);
        // 设置路径
        cookie.setPath("/cookie");
        // 响应
        resp.addCookie(cookie);
    }
}
