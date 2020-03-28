package org.lzn.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

/**
 * 下载
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/03/28 22:53
 */
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(this.getClass().getName());
        // 设置一个要下载的文件
        String fileName = "hello世界.txt";
        // 设置文件名的编码（中文等不安全的字符转化 UTF-8）
        fileName = URLEncoder.encode(fileName, "UTF-8");
        // 告知浏览器要下载文件
        resp.setHeader("content-disposition", "attachment;filename=".concat(fileName));
        // 根据文件名自动获取文件类型
        resp.setContentType(this.getServletContext().getMimeType(fileName));
        // 创建一个文件输出流
        PrintWriter writer = resp.getWriter();
        writer.println("hello world!!!");
    }
}
