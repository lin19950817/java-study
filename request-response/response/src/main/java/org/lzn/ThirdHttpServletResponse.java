package org.lzn;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 文件下载
 *
 * @author LinZhenNan lin.zhennan@hand-china.com 2019/11/07 15:36
 */
public class ThirdHttpServletResponse extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 通过路径得到一个输入流
        String path = getServletContext().getRealPath("/WEB-INF/imags/收到.jfif");
        FileInputStream fileInputStream = new FileInputStream(path);

        // 创建字节输出流
        ServletOutputStream servletOutputStream = resp.getOutputStream();

        // 得到下载文件的文件名
        String filename = path.substring(path.lastIndexOf('\\') + 1);
        // 设置文件名的编码（中文等不安全的字符转化 UTF-8）
        filename = URLEncoder.encode(filename, "UTF-8");
        // 告知客户端要下载文件
//        resp.setHeader("content-disposition", "attachment;filename=" + filename);
        resp.setContentType("image/png");

        // 执行输出操作
        int len = 0;
        byte[] b = new byte[1024];
        while ((len = fileInputStream.read(b)) != -1) {
            servletOutputStream.write(b, 0, len);
        }

        // 关闭
        servletOutputStream.close();
        fileInputStream.close();
    }
}
