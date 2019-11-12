package org.lzn;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 请求转发和操作非表单数据的方法
 *
 * @author LinZhenNan lin.zhennan@hand-china.com 2019/11/12 9:56
 */
public class FourthHttpServletRequest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        System.out.println("FourthHttpServletRequest start");
        // 没有响应给前端
        resp.getWriter().write("FourthHttpServletRequest start");

        // 将非表单的数据添加到 request的域中
        req.setAttribute("hehe", "123asdf阿斯蒂芬");
        // 请求转发不能跳转到其他应用
        req.getRequestDispatcher("/response/demo1").forward(req, resp);

        // 没有响应给前端
        resp.getWriter().write("FourthHttpServletRequest end");
        System.out.println("FourthHttpServletRequest end");
    }
}
