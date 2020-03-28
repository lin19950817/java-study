package org.lzn.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * enctype 为 multipart/form-data
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/03/27 19:56
 */
public class MultipartUploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(this.getClass().getName());
        // 读取 multipart/form-data 数据
        ServletInputStream inputStream = req.getInputStream();
        byte[] b = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int len;
        while ((len = inputStream.read(b, 0, 1024)) != -1) {
            byteArrayOutputStream.write(b, 0, len);
        }
        // 以 utf-8 编码的字符串显示
        System.out.println(new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8));
    }
}
