package com.lzn;

import javax.servlet.*;
import java.io.IOException;

/**
 * servlet学习
 *
 * @author LinZhenNan 2019/09/20 15:45
 */
public class FirstServlet implements Servlet {

    /**
     * 实例化
     *  在 Servlet访问时调用
     *
     * @author LinZhenNan 2019-09-20 17:09
     */
    public FirstServlet() {
        System.out.println("====================================================FirstServlet FirstServlet START====================================================");
        System.out.println("FirstServlet()");
        System.out.println("====================================================FirstServlet FirstServlet END====================================================");
    }

    /**
     * 初始化
     *  在 Servlet访问时调用，在实例化之后
     *
     * @param config 配置
     * @author LinZhenNan 2019-09-20 17:09
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("====================================================FirstServlet init START====================================================");
        System.out.println("init(ServletConfig config)");
        System.out.println("====================================================FirstServlet init END====================================================");
    }

    @Override
    public ServletConfig getServletConfig() {
        System.out.println("====================================================FirstServlet getServletConfig START====================================================");
        System.out.println("getServletConfig()");
        System.out.println("====================================================FirstServlet getServletConfig END====================================================");
        return null;
    }

    /**
     * 服务
     *  每次访问都会被调用
     *
     * @param req servlet请求
     * @param res servlet回应
     * @author LinZhenNan 2019-09-20 17:09
     */
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("hello servlet!!!");
    }

    /**
     * 提供自身相关的基本信息。比如作者、版本和版权
     *
     *
     * @author LinZhenNan 2019-09-20 17:12
     * @return java.lang.String servlet信息
     */
    @Override
    public String getServletInfo() {
        System.out.println("====================================================FirstServlet getServletInfo START====================================================");
        System.out.println("getServletInfo()");
        System.out.println("====================================================FirstServlet getServletInfo END====================================================");
        return null;
    }

    /**
     * 销毁
     *  Servlet停止时调用
     *
     *
     * @author LinZhenNan 2019-09-20 17:10
     */
    @Override
    public void destroy() {
        System.out.println("====================================================FirstServlet destroy START====================================================");
        System.out.println("destroy()");
        System.out.println("====================================================FirstServlet destroy END====================================================");

    }
}
