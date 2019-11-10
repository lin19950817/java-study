package com.lzn;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 第三
 *
 * @author LinZhenNan lin_hehe@qq.com 2019/10/25 22:44
 */
public class ThirdServletContext extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 参数以 / 开头，代表当前项目根目录
        String path = getServletContext().getRealPath("/WEB-INF/a.properties");
        System.out.println(path);
        System.out.println("ThirdServletContext.doGet()");

        // 创建一个 Properties
        Properties properties = new Properties();
        properties.load(new FileInputStream(path));
        String value = properties.get("key") instanceof String ? (String)properties.get("key") : null;
        System.out.println(value);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ThirdServletContext.doPost()");

        resp.getWriter().write("ThirdServletContext.doPost() start");

        // 转发到 demo4(FourthServletContext)
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/demo4");
        requestDispatcher.forward(req, resp);

        resp.getWriter().write("ThirdServletContext.doPost() end");


        // 取出 demo4设置的键值对
        System.out.println(req.getAttribute("key"));
        // 请求路径：brower -> ThirdServletContext -> FourthServletContext -> ThirdServletContext -> brower
        System.out.println("ThirdServletContext.doPost() end");
    }
}
