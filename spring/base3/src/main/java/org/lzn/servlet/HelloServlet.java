package org.lzn.servlet;

import org.lzn.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 整合 web
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/06/01 23:52
 */
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 从 application 作用域（ServletContext）获得 spring 容器
        // 方式1
        ApplicationContext applicationContext = (ApplicationContext) this.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        System.out.println(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        // 方式2
        WebApplicationContext applicationContext2 = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());

        // 操作
        AccountService accountService = (AccountService) applicationContext.getBean("accountService");
        accountService.transfer("haha", "hehe", 500);
    }
}
