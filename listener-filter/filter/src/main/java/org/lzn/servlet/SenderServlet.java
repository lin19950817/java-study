package org.lzn.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 发送者 servlet<br>
 *     用来发送转发请求
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/03/24 21:27
 */
public class SenderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("SenderServlet get");
        req.getRequestDispatcher("/recipient").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("SenderServlet post");
    }
}
