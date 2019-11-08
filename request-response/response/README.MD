# resonse
`HttpServletResponse` 中没有 `setCharacterEncoding()` ，原因是 `servlet-api.jar` 在 **2.3版本** 中没有这个方法。 **2.4版本** 才有
## FirstHttpServletResponse
* `resp.setContentType("UTF-8");` <br/>
    等于:
    ```
    // 告诉服务器应用使用 UTF-8 解析文本
    resp.setCharacterEncoding("UTF-8");
    // 告诉客户端要使用什么编码
    resp.setHeader("content-type", "text/html;charset=UTF-8");
    ```
## SecondHttpServletResponse
* 输出流
## ThirdHttpSerlvetResponse(文件下载)
* 显示与下载图片的差异，下载需要加上：
    ```
    // 告知客户端要下载文件
    resp.setHeader("content-disposition", "attachment;filename=" + filename);
    ```
## FourHttpServletResponse(验证码)
* 生成 4 个随机数的验证码<br/>
![验证码](../images/response/four.png)