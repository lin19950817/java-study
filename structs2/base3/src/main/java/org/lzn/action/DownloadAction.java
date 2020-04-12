package org.lzn.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 文件下载
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/12 11:57
 */
public class DownloadAction extends ActionSupport {

    /**
     * 在 InputSream 命名时，不能使用 in
     */
    private InputStream inputStream;

    public String download() throws Exception{
        // 1. 找到文件的存储路径
        String filePath = ServletActionContext.getServletContext().getRealPath("/WEB-INF/files/resultDemo1.png");
        // 2. 把文件读到一个 InputSream 中
        inputStream = new FileInputStream(filePath);
        // 3. 返回一个成功
        return SUCCESS;
        // 4. 由一个叫 stream 的结果类型为我们把剩下的事情做完
    }

    //
    // setter/getter
    // ------------------------------------------------------------------------------

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}
