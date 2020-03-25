package org.lzn.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 接收者 servlet<br>
 *     接收 senderServlet 的转发的请求
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/03/24 21:30
 */
public class RecipientServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("RecipientServlet get");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("RecipientServlet post");
    }
}
