package org.lzn;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 清楚 Cookie
 *
 * @author LinZhenNan lin.zhennan@hand-china.com 2019/11/25 10:14
 */
public class ClearCookie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 创建一个 Cookie 对象
        Cookie cookie = new Cookie("lastAccessTime", "");
        // 要设置被删除的 Cookie 的 path，否则可能会错删对象。
        cookie.setPath(req.getContextPath());
        // 删除
        cookie.setMaxAge(0);
        // 将 cookie 响应给客户端
        resp.addCookie(cookie);
    }
}
