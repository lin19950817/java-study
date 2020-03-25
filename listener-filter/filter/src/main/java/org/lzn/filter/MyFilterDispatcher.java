package org.lzn.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Filter 实现类<br>
 *     研究配置 dispatcher 标签
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/03/24 0:02
 */
public class MyFilterDispatcher implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("MyFilterDispatcher".concat("拦截：过滤器配置为FORWARD,REQUEST"));
        // 放行
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
