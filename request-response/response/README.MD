# resonse
`HttpServletResponse` 中没有 `setCharacterEncoding()` ，原因是 `servlet-api.jar` 在 **2.3版本** 中没有这个方法。 **2.4版本** 才有
## [FirstHttpServletResponse](/request-response/response/src/main/java/org/lzn/FirstHttpServletResponse.java)
* `resp.setContentType("UTF-8");` <br/>
    等于:
    ```
    // 告诉服务器应用使用 UTF-8 解析文本
    resp.setCharacterEncoding("UTF-8");
    // 告诉客户端要使用什么编码
    resp.setHeader("content-type", "text/html;charset=UTF-8");
    ```
## [SecondHttpServletResponse](/request-response/response/src/main/java/org/lzn/SecondHttpServletResponse.java)
* 输出流
## [ThirdHttpSerlvetResponse(文件下载)](/request-response/response/src/main/java/org/lzn/ThirdHttpServletResponse.java)
* 显示与下载图片的差异，下载需要加上：
    ```
    // 告知客户端要下载文件
    resp.setHeader("content-disposition", "attachment;filename=" + filename);
    ```
## [FourthHttpServletResponse(验证码)](/request-response/response/src/main/java/org/lzn/FourthHttpServletResponse.java)
* 生成 4 个随机数的验证码<br/>
![验证码](../images/response/four.png)
* **[login.html](/request-response/response/src/main/webapp/login.html)** 模拟使用验证码
  * 前端不使用缓存：使用可变的参数让请求不同。<br>
  `img.src = "/response/demo4?time=" + new Date().getTime();` 
  * 后端不使用缓存<br>
  ```
  resp.setHeader("pragma", "no-cache");
  resp.setHeader("cache-control", "no-cache");
  resp.setIntHeader("expires", 0);
  ```
## [FifthHttpServletResponse(刷新功能)](/request-response/response/src/main/java/org/lzn/FifthHttpServletResponse.java)
* 设置响应头进行刷新 `resp.setHeader("refresh", "1");` 
* 设置响应头进行跳转 `resp.setHeader("refresh", "3;url=/response/demo1");`
## [SixthHttpServletResponse(重定向)](/request-response/response/src/main/java/org/lzn/SixthHttpServletResponse.java)
* `resp.setHeader("location", "/response/demo1");`<br>
    等于：<br>
    ```
    // 告诉客户端要重定向资源
    resp.setStatus(302);
    // 告诉浏览器访问哪个 URL
    resp.setHeader("location", "/response/demo1");
    ```