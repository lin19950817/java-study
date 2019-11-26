package org.lzn.login;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description
 *
 * @author LinZhenNan lin.zhennan@hand-china.com 2019/11/25 16:38
 */
public class DoLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        // 获取表单数据
        String userName = req.getParameter("userName");
        String pwd = req.getParameter("pwd");
        String remember = req.getParameter("remember");
        // 处理业务逻辑
        // 分发转向
        if ("tom".equals(userName) && "123".equals(pwd)){
            Cookie cookie = new Cookie("userName", userName);
            cookie.setPath("/");
            if (remember != null) {
                cookie.setMaxAge(Integer.MAX_VALUE);
            } else {
                // 删除 Cookie
                cookie.setMaxAge(0);
            }
            resp.addCookie(cookie);
            writer.write("登陆成功！");
        } else {
            writer.write("登录失败！");
            // 设置 2 秒跳转重新登录
            resp.setHeader("refresh", "2;url=" +req.getContextPath() + "/login");
        }

        // 分发转向
    }
}
