package org.lzn.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lzn.entity.Book;
import org.lzn.util.DBUtil;

/**
 * 购物车功能主页
 *
 * @author LinZhenNan lin.zhennan@hand-china.com 2019/11/26 21:43
 */
public class ShowAllBooksServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        writer.write("本网站有以下好书：<br>");
        Map<String, Book> books = DBUtil.getBooks();
        for (Map.Entry<String, Book> book : books.entrySet()) {
            writer.write("<a href='" + req.getContextPath() + "/addCart?id=" + book.getKey() + "'>" + book.getValue().getName() + "</a><br>");
        }
        writer.write("<hr><a href='" + req.getContextPath() + "/showCart'>查看购物车</a>");
    }
}
