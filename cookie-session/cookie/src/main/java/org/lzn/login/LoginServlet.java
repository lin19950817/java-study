package org.lzn.login;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录
 *
 * @author LinZhenNan lin.zhennan@hand-china.com 2019/11/25 16:32
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        String userName = "";
        String checked = "";
        // 得到客户端保存的 Cookie 数据
        Cookie[] cookies = req.getCookies();
        for (int i = 0; cookies != null && i < cookies.length; i++) {
            if ("userName".equals(cookies[i].getName())) {
                userName = cookies[i].getValue();
                checked = "checked='checked'";
            }
        }

        writer.write("<form action='/cookie/doLogin' method='post'>");
        writer.write("用户名：<input type='text' name='userName' value='" + userName + "'/><br>");
        writer.write("密码：<input type='password' name='pwd'/><br>");
        writer.write("记住用户名：<input type='checkbox' name='remember' " + checked + "/><br>");
        writer.write("<input type='submit' value='登录'/><br>");
        writer.write("</form>");
    }
}
