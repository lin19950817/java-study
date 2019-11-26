package org.lzn.history;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lzn.entity.Book;
import org.lzn.util.DBUtil;

/**
 * 所有书 Servlet
 *
 * @author LinZhenNan lin.zhennan@hand-china.com 2019/11/26 15:27
 */
public class ShowAllBooksServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        writer.write("本网站有以下好书：<br>");
        Map<String, Book> books = DBUtil.getBooks();
        for (Map.Entry<String, Book> book : books.entrySet()) {
            writer.write("<a href='" + req.getContextPath() + "/showBookDetail?id=" + book.getKey() + "' target='_blank'>" + book.getValue().getName() + "</a><br>");
        }

        writer.write("<hr/>你最近浏览过的书有：<br>");
        Cookie[] cookies = req.getCookies();
        for (int i = 0; cookies != null && i < cookies.length; i++) {
            if ("historyBookId".equals(cookies[i].getName())) {
                String value = cookies[i].getValue();
                String[] ids = value.split("-");
                for (int j = 0; j < ids.length; j++) {
                    // 根据 id 获取指定的书
                    Book book = DBUtil.findBookById(ids[j]);
                    writer.write("<br>" + book.getName());
                }
            }
        }
    }
}
