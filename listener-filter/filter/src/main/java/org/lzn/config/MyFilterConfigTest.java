package org.lzn.config;


import javax.servlet.*;
import java.io.IOException;

/**
 * Filter 实现类<br>
 *     作为配置过滤
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/03/23 23:03
 */
public class MyFilterConfigTest implements Filter {
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 通过 FilterConfig 对象获取到配置文件中的初始化信息
        String encoding = filterConfig.getInitParameter("encoding");
        System.out.println(encoding);

        request.setCharacterEncoding(encoding);
        // 放行
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
