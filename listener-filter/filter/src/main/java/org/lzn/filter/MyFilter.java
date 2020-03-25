package org.lzn.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Filter 实现类
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/03/19 22:04
 */
public class MyFilter implements Filter {
    public MyFilter() {
        System.out.println("MyFilter 实例化");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("MyFilter 初始化");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("MyFilter 拦截开始");

        // 放行
        chain.doFilter(request, response);

        System.out.println("MyFilter 拦截结束");
    }

    @Override
    public void destroy() {
        System.out.println("MyFilter 销毁了");
    }
}
