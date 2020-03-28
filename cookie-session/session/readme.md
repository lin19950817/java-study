# session
> Session是 **服务器端** 技术，利用这个技术，服务器在运行时可以为每一个用户的浏览器创建一个 **独享的HttpSession对象**，由于session为用户浏览器独享，所以用户在访问服务器的web资源时，可以把各自的数据放在各自的session中，当用户再去访问服务器中的其它web资源时，其它web资源再从用户各自的session中取出数据为用户服务。

`session` 存放对象时，对象需要实现 `Serializable`，停止 `tomcat` 后，对象会被保存在本地。

`idea` 配置 `tomcat` 的 `session` 对象存放路径：`C:\Users\用户名\.IntelliJIdea2019.1\system\tomcat\项目名\work\Catalina\localhost\listener`

## 原理图
session 的实现原理<br>
![session](images/session.png)<br>
cookie 中的 jsessionid<br>
![jsessionid](images/jsessionid.png)

### getSession: 内部执行原理
1. 获取名称为 jsessionid 的 cookie 的值
2. 没有这样的 cookie，创建一个新的 HttpSession 对象，分配一个唯一的 sessionID，并且向客户端卸了一个名为 jsessionid=sessionId 的 cookie
3. 有这样的 cookie，获取 cookie 的值（HttpSession 对象的值），从服务器的内存中根据 id 找到那个 HttpSession 对象
### getSession(boolan create)
参数：
* true: 和 getSession() 功能一样
* false: 根据客户端 jsessionid 的 cookie 的值，找对应的 HttpSession 对象，找不到返回 null（只是查询）。
## [firstSession](src/main/java/org/lzn/FirstSession.java)
存放 session
## [secondSession](src/main/java/org/lzn/SecondSession.java)
读取 session
### 研究
与 [firstSession](src/main/java/org/lzn/FirstSession.java) 配合研究，多个浏览器是否能访问同一个 session<br>
1. 不同浏览器访问的 session 不同
2. 浏览器关闭则 session 清除，则关闭浏览器标签则不会清除
## [showAllBooks](src/main/java/org/lzn/cart/ShowAllBooksServlet.java)
使用 session 实现购物车功能
### 架构
| 类名 | 说明 | package |
| :- | :- | :- |
| [Book](src/main/java/org/lzn/entity/Book.java) | 实体类 | entity |
| [DBUtil](src/main/java/org/lzn/util/DBUtil.java) | 工具类，模拟从数据库获取数据 | util |
| [ShowAllBooksServlet](src/main/java/org/lzn/cart/ShowAllBooksServlet.java) | Servlet，主页 | cart |
| [ShowCart](src/main/java/org/lzn/cart/ShowCart.java) | Servlet，显示购物车 | cart |
| [AddCart](src/main/java/org/lzn/cart/AddCart.java) | Servlet，添加到购物车 | cart |

## sessionid 什么时候生成

* 直接访问 `html` 文件，不会返回给浏览器 `jsessionid`

* 访问 `servlet` 也不会返回浏览器 `jsessionid`。当调用 `HttpServletRequest` 的 `getSession()`，例如 `req.getSession()`，则返回浏览器 `jessionid`。

* 访问 `jsp`，则返回浏览器 `jsessionid`。原因是 `jsp` 会创建内置对象。

  [jsp github学习连接](https://github.com/lin19950817/java-study/tree/master/jsp/jsp-base)

