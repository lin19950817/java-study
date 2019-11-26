# cookie-session
1. [cookie说明](cookie)
2. [session说明](session)
## 会话
### 什么是会话
> 会话可简单理解为：用户开一个浏览器，点击多个超链接，访问服务器多个web资源，然后关闭浏览器，整个过程称之为一个会话。
### 会话过程中要解决的问题
> 每个用户在使用浏览器与服务器进行会话的过程中，不可避免各自会产生一些数据，程序要想办法为每个用户保存这些数据。<br>
> 例如：用户点击超链接通过一个servlet购买了一个商品，程序应该想办法保存用户购买的商品，以便于用户点结帐servlet时，结帐servlet可以得到用户购买的商品为用户结帐。
## Session 和 cookie 区别
1. cookie 是把用户的数据写给用户的浏览器。
2. session 技术吧用户的数据写到用户独占的 session 中。
3. session 对象由服务器创建，开发人员可以调用 request 对象的 getSession 方法得到 session 对象