package org.lzn.history;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lzn.entity.Book;
import org.lzn.util.DBUtil;

/**
 * 书详情 servlet
 *
 * @author LinZhenNan lin.zhennan@hand-china.com 2019/11/26 15:42
 */
public class ShowBookDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        // 显示图书的详细信息

        // 获得 id
        String id = req.getParameter("id");
        Book book = DBUtil.findBookById(id);
        writer.write(book.toString());

        // 把当前浏览过的书的 id 写回到客户端
        String historyBookId = organizedId(id, req);
        Cookie cookie = new Cookie("historyBookId", historyBookId);
        cookie.setPath("/");
        cookie.setMaxAge(Integer.MAX_VALUE);

        // 响应客户端
        resp.addCookie(cookie);
    }

    private String organizedId(String id, HttpServletRequest req) {
        // 得到客户端的 cookie
        Cookie[] cookies = req.getCookies();
        if (cookies == null) {
            return id;
        }

        // 查找有没有历史记录的 cookie
        Cookie historyBook = null;
        for (int i = 0; i < cookies.length; i++) {
            // 格式： "id"-"id"(1-2-3)
            if ("historyBookId".equals(cookies[i].getName())) {
                historyBook = cookies[i];
            }
        }
        // 如果 cookie 为空，则返回 id
        if (historyBook == null) {
            return id;
        }
        String value = historyBook.getValue();
        String[] values = value.split("-");
        LinkedList<String> list = new LinkedList<String>(Arrays.asList(values));

        if (list.size() < 3) {
            // 如果包含当前 id 的值，则删除这个 id
            if (list.contains(id)) {
                list.remove(id);
            }
        } else {
            if (list.contains(id)) {
                list.remove(id);
            } else {
                // 已经有 3 本书了，则把最后一个 id 删除
                list.removeLast();
            }
        }
        // 将最新书的 id 添加到最前面
        list.addFirst(id);

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                sb.append("-");
            }
            sb.append(list.get(i));
        }
        return sb.toString();
    }
}
