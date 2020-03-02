# Ajax

**Ajax（Asynchronous JavaScript And XML）** 指异步 `JavaScript` 及 `XML` 
**不是一种新的编程语言**，而是一种用于创建更好更快以及交互性更强的 `Web` 应用程序的技术，是基于 `JavaScript、XML、HTML、CSS` 新用法。

* 基于标准的标识技术，使用 `XHTML` 和 `CSS`
* 动态显示和交互技术，使用 `Document` 和 `Object Model`
* 数据交换和操作技术，使用 `XML` 和 `XSLT`
* 异步数据获取技术，使用 `XMLHttpRequest`
* 而 `JavaScript` 将以上都结合在一起

## XMLHttpRequest

`JavaScript` 的对象，是整个 `Ajax` 技术的核心，提供了异步发送请求的能力。

### 构造方法

不同浏览器，甚至相同浏览器的不同版本，获取该对象的方式是不同的。

```javascript
var xmlhttp;
if (window.XMLHttpRequest) {
    // code for IE7+, Firefox, Chrome, opera, Safari
    xmlhttp = new XMLHttpRequest();
} else {
    // code for IE6, IE5
    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
}
```

### 常用方法

| 方法名                          | 说明                                                         |
| ------------------------------- | ------------------------------------------------------------ |
| open(method, URL, async)        | **建立与服务器的连接**<br />`method` 参数指定请求的 `HTTP` 方法，典型的值是 `GET` 或 `POST`<br />`URL` 参数指请求的地址<br />`async` 参数指定是否使用异步请求，其值为 `true` 或 `false`, `true` 是默认值 |
| send(content)                   | **发送请求**<br />`content` 参数指定请求的参数               |
| setRequestHeader(header, value) | **设置请求的头信息**<br />必须在 `open()` 之后，`send()` 调用 |

### 常用属性

* `onreadystatechange` : 指定回调函数

* `readyStste` : `XMLHttpRequest` 的状态信息，只读，类型为 `String`

  | 就绪状态码 | 说明                                                         |
  | ---------- | ------------------------------------------------------------ |
  | 0          | **`XMLHttpRequest` 对象没有完成初始化（刚刚创建）**          |
  | 1          | **`XMLHttpRequest` 对象开始发送请求**<br />调用了 `open` 方法，但还没有调用 `send` 方法。请求还没有发出 |
  | 2          | **`XMLHttpRequest` 对象的请求发送完成**<br />`send` 方法已经调用，数据已经提交到服务器。但没有任何响应 |
  | 3          | **`XMLHttpRequest` 对象开始读取响应，还没有结束**<br />收到了所有的相应信息头，但正文还没有完全受到 |
  | 4          | **`XMLHttpRequest` 对象读取响应结束**<br />全部信息都已接收  |
  
* `status` : `HTTP` 的状态码，只读，类型为 `short`

  | 状态码 | 说明               |
  | ------ | ------------------ |
  | 200    | 服务器响应正常     |
  | 400    | 无法找到请求的资源 |
  | 403    | 没有访问权限       |
  | 404    | 访问的资源部存在   |
  | 500    | 服务器内部错误     |

* `responseText` : 获取响应的文本内容，只读，类型为 `String`

* `responseXML` : 获得响应的 `XML` 文档对象 `document`，只读，类型为 `Document`

## 学习 Ajax

* [javascript](web/js/myJS.js)
* [服务端](src/main/java/org/lzn/LoginServlet.java)