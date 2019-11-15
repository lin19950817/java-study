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
 * demo1, path/demo1
 *
 * @author LinZhenNan lin.zhennan@hand-china.com 2019/11/15 14:20
 */
public class FirstCookie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        // 获取客户端所有的 cookie对象
        Cookie[] cookies = req.getCookies();
        for (int i = 0; cookies != null && i < cookies.length; i++) {
            if ("lastAccessTime".equals(cookies[i].getName())) {
                // 获得客户端保存的最后访问时间
                Long lastAccessTime = Long.parseLong(cookies[i].getValue());
                writer.write("你的最后访问时间为：" + new Date().toLocaleString());
                break;
            }
        }

        // 创建 cookie
        Cookie cookie = new Cookie("lastAccessTime", String.valueOf(System.currentTimeMillis()));

        // 设置生存时间
        cookie.setMaxAge(60 * 5);

        // 把 cookie信息写回到客户端
        resp.addCookie(cookie);
    }
}
