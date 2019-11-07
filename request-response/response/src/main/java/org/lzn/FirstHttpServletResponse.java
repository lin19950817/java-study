package org.lzn;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * response
 *
 * @author LinZhenNan lin.zhennan@hand-china.com 2019/11/01 15:46
 */
public class FirstHttpServletResponse extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 服务器中默认编码为：ISO-8859-1
        System.out.println(resp.getCharacterEncoding());
        // 告诉服务器应用使用 UTF-8 解析文本
//        resp.setCharacterEncoding("UTF-8");
        // 告诉客户端要使用什么编码
//        resp.setHeader("content-type", "text/html;charset=UTF-8");

        // 告诉服务器应用使用 UTF-8 解析文本，告诉客户端要使用什么编码
        resp.setContentType("text/html;charset=UTF-8");
        // 得到一个字符输出流
        PrintWriter writer = resp.getWriter();
        // 向客户端响应文本
        writer.write("你好");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
