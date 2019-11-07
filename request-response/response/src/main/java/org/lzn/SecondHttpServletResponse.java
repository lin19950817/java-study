package org.lzn;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * second
 *
 * @author LinZhenNan lin.zhennan@hand-china.com 2019/11/01 17:32
 */
public class SecondHttpServletResponse extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 输出流
        ServletOutputStream outputStream = resp.getOutputStream();
        outputStream.write("你好".getBytes("UTF-8"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
