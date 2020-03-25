package org.lzn.web.servlet;

import org.lzn.domain.User;
import org.lzn.service.UserService;
import org.lzn.util.MD5Util;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * servlet
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/03/25 22:59
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // MD5 加密
        password = MD5Util.toMd5(password);
        UserService userService = new UserService();
        User user = userService.getUser(username, password);

        if (user != null) {
            String autologin = req.getParameter("autologin");
            Cookie cookie = new Cookie("user", user.getUsername()+"&"+user.getPassword());
            cookie.setPath("/");
            if (autologin != null) {
                // 一周
                cookie.setMaxAge(60 * 60 * 24 * 7);
            } else {
                cookie.setMaxAge(0);
            }
            // 添加 cookie
            resp.addCookie(cookie);
            req.getSession().setAttribute("user", user);
            req.getRequestDispatcher("/home.jsp").forward(req, resp);
        } else {
            req.setAttribute("msg", "用户名或密码错误,请重新登录！");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
