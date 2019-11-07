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