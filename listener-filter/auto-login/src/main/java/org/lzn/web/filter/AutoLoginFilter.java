package org.lzn.web.filter;

import org.lzn.domain.User;
import org.lzn.service.UserService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自动登录过滤器
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/03/25 22:43
 */
public class AutoLoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 1. 装换两个对象 HttpServletRequest、HttpServletResponse
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        // 获取请求 url 最后 uri
        String uri = req.getRequestURI();
        String path = req.getContextPath();
        path = uri.substring(path.length());

        String login = "/login.jsp";
        String loginServlet = "/servlet/loginServlet";
        // 如果请求资源不是 login.jsp，也不是 /servlet/loginServlet，才执行
        if (!login.equals(path) && !loginServlet.equals(path)) {
            User user = (User) req.getSession().getAttribute("user");
            // 如果 session 得到了 user 对象，说明已经登录或自动登录过，则不用执行自动登录，否则执行自动登录
            if (user == null) {
                // 2. 处理业务
                // 得到 cookies 数组
                Cookie[] cookies = req.getCookies();
                String username = "";
                String password = "";
                // 从数组中找到想要的user对象的信息
                for (int i = 0; cookies != null && i < cookies.length; i++) {
                    if ("user".equals(cookies[i].getName())) {
                        // tom&123
                        String value = cookies[i].getValue();
                        String[] values = value.split("&");
                        username = values[0];
                        password = values[1];
                    }
                }

                // 登录操作
                UserService us = new UserService();
                User u = us.getUser(username, password);
                // 如果登录成功，把用户信息存到session中
                if (u != null) {
                    req.getSession().setAttribute("user", u);
                }
            }
        }

        // 3.放行
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
