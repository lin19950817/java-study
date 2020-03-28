package org.lzn.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 简单实用 fileUpload
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/03/28 16:51
 */
public class MyFileUpload extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(this.getClass().getName());

        // 执行文件上传操作
        // 判断表单是否支持文件上传。enctype = "multipart/form-data"
        boolean multipartContent = ServletFileUpload.isMultipartContent(req);
        if (!multipartContent) {
            throw new RuntimeException("your form is not multipart/form-data");
        }
        // 创建一个 DiskFileItemFactory 工厂类
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置临时文件位置
        File f = new File(getServletContext().getRealPath("/WEB-INF/temp-file"));
        if (!f.exists()) {
            f.mkdirs();
        }
        factory.setRepository(f);
        // 创建一个 ServletFileUpload 核心对象
        ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
        try {
            // 限制文件大小为 3M
            servletFileUpload.setFileSizeMax(1024 * 1024 * 3);
            // 限制总文件大小为 6M
            servletFileUpload.setSizeMax(1024 * 1024 * 6);
            // 解析 request 对象，并得到一个表单项的集合
            List<FileItem> fileItems = servletFileUpload.parseRequest(req);
            // 遍历表单项
            fileItems.forEach(item -> {
                if (item.isFormField()) {
                    // 普通表单项
                    processFormField(item);
                } else {
                    // 上传表单项
                    processUploadField(item);
                }
            });
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }


    //
    // 私有方法
    // ------------------------------------------------------------------------------

    /**
     * 处理普通表单项<br>
     *     等同 processFormField2
     *
     * @param fileItem 表单项
     * @author LinZhenNan lin_hehe@qq.com 2020-03-28 17:12
     */
    private void processFormField(FileItem fileItem) {
        // 字段名
        String fileName = fileItem.getFieldName();
        // 字段值
        String fileValue = fileItem.getString();
        System.out.println(fileName.concat("=").concat(fileValue));
    }

    private void processUploadField(FileItem fileItem) {
        // 得到上传文件的名称
        String fileName = fileItem.getName();
        try {
            // 得到文件流
            InputStream inputStream = fileItem.getInputStream();
            // 文件保存路径
            String directoryRealPath = this.getServletContext().getRealPath("/WEB-INF/upload-file");
            // 创建一个文件存盘的目录
            File storeDirectory = new File(directoryRealPath);
            if (!storeDirectory.exists()) {
                storeDirectory.mkdirs();
            }
            File file = new File(storeDirectory, fileName);
            // 通过文件输出流将上传的文件保存到磁盘
            FileOutputStream outputStream = new FileOutputStream(file);
            int len;
            byte[] b = new byte[1024];
            while ((len = inputStream.read(b)) != -1) {
                outputStream.write(b, 0, len);
            }
            inputStream.close();
            outputStream.close();
            // 删除临时文件
            fileItem.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processUploadField2(FileItem fileItem) {
        // 得到上传文件的名称
        String fileName = fileItem.getName();
        try {
            // 得到文件流
            InputStream inputStream = fileItem.getInputStream();
            // 文件保存路径
            String directoryRealPath = this.getServletContext().getRealPath("/WEB-INF/upload-file");
            // 创建一个文件存盘的目录
            File storeDirectory = new File(directoryRealPath);
            if (!storeDirectory.exists()) {
                storeDirectory.mkdirs();
            }
            // 上传文件，自动删除临时文件
            fileItem.write(storeDirectory);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
