package org.lzn.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;
import java.io.File;

/**
 * struts2 多文件上传
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/12 0:35
 */
public class Upload2Action extends ActionSupport {

    /**
     * 表单上提供的字段
     */
    private String username;
    private File[] photo;
    
    /**
     * 上传的文件名。上传字段名称+FileName（注意大小写）
     */
    private String[] photoFileName;
    
    /**
     * 上传文件的 MIME 类型。上传字段名称+ContentType（注意大小写）
     */
    private String[] photoContentType;

    public String upload() {
        // 1. 拿到 ServletContext
        ServletContext application = ServletActionContext.getServletContext();
        // 2. 调用 realPath 方法，获取根据一个虚拟目录得到的真实目录
        String filePath = application.getRealPath("/WEB-INF/files");
        // 3. 如果这个真实目录不存在，需要创建
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        // 4. 把 photo 存过去。剪切：把临时文件剪切指定的位置，并且给他重命名。注意：临时文件没有了
        for (int i = 0, len = photo.length; i < len ; i++) {
            photo[i].renameTo(new File(file, photoFileName[i]));
        }

        return INPUT;
    }

    //
    // setter/getter
    // ------------------------------------------------------------------------------

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public File[] getPhoto() {
        return photo;
    }

    public void setPhoto(File[] photo) {
        this.photo = photo;
    }

    public String[] getPhotoFileName() {
        return photoFileName;
    }

    public void setPhotoFileName(String[] photoFileName) {
        this.photoFileName = photoFileName;
    }

    public String[] getPhotoContentType() {
        return photoContentType;
    }

    public void setPhotoContentType(String[] photoContentType) {
        this.photoContentType = photoContentType;
    }
}
