package org.lzn.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.lzn.entity.Book;
import org.lzn.util.DBUtil;

/**
 * 添加到购物车
 *
 * @author LinZhenNan lin.zhennan@hand-china.com 2019/11/26 22:19
 */
public class AddCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        // 根据 id 得到书
        String id = req.getParameter("id");
        Book book = DBUtil.findBookById(id);

        // 得到 session 对象
        HttpSession session = req.getSession();
        // 从 session 中取出 list（购物车）
        List<Book> carts = (List<Book>)session.getAttribute("cart");
        if (carts == null) {
            carts = new ArrayList<Book>();
        }
        carts.add(book);
        session.setAttribute("cart", carts);

        writer.write("购买成功！2 秒后回到主页");
        resp.setHeader("refresh", "2;url=" + req.getContextPath() + "/showAllBooks");
    }
}
