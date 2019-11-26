package org.lzn.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.lzn.entity.Book;

/**
 * 购物车页面
 *
 * @author LinZhenNan lin.zhennan@hand-china.com 2019/11/26 22:26
 */
public class ShowCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;char=UTF-8");
        PrintWriter writer = resp.getWriter();
        // 得到 session 对象
        HttpSession session = req.getSession();
        writer.write("购物车有以下商品：<br>");
        // 获取购物车数据
        List<Book> books = (List<Book>)session.getAttribute("cart");
        if (books == null) {
            writer.write("你还什么都没买呢");
//            resp.sendRedirect(req.getContextPath() + "/showBooks");
            resp.setHeader("refresh", "2;url=" + req.getContextPath() + "/showBooks");
            return;
        }
        for (Book book : books) {
            writer.write(book.getName() + "<br>");
        }
    }
}
