* # first-servlet
  * ## FirstServlet(implements Servlet)  
    * 查看实现的各方法的调用顺序
  * ## SecondServlet(implements Servlet)
    * 用于查看按照 web.xml配置的映射是否能调用到各自的 servlet
  *  ## web.xml
     * 配置 servlet、映射和优先级(默认懒加载)
* # first-genericSerlvet
  * ## FirstGenericServlet(extends GenericServlet)
    * 重写 service
  * ## web.xml
    * 为 FirstGenericServlet配置了两个映射 
* # first-httpSerlvet
  * ## FirstHttpServlet(extends HttpServlet)
    * 没有重写任何方法，了解 HttpServlet源码运行
  * ## SecondHttpSerlvt(extends HttpServlet)
    * 重写了 doGet、doPost方法
    * 获取请求的远程地址、本地地址
    * 监控线程
    * 获得配置文件信息(init-param)
  * ## web.xml
    * 配置了 init-param局部配置文件信息
* # first-servletContext
  * ## FirstServletContext
    * 获取 SerlvetContext并设置Attribute
    * 获取全局配置信息查看获取的是 init-param还是 context-param
  * ## SecondServletContext
    * 获取 ServletContext并得到 FirstServletContext设置的 Attribute
    * 获取全局配置信息查看获取的是 init-param还是 context-param
  * ## ThirdServletContext
    * 解析获取 properties的信息
    * 转发到 FourthServletContext
  * ## FourthServletContext
    * 接受转发的请求
  * ## web.xml
    * firstSerlvetContext设置了 init-param
    * 设置 context-param
---
# Servlet映射通配符
如果 url-pattern的值是 /, 表示执行默认映射。
> ## 通配符 * 代表任意字符串  
>> url-pattern: *.do 以 .do 结尾的字符串的请求都可以访问 注意: 不要加 /  
>> url-pattern: /* 任意字符串都可以访问  
>> url-pattern: /action/* 以 /action开头的请求都可以访问  
> ## 匹配规则  
>> 优先级：从高到低  
>> 绝对匹配 -> / 开头匹配 -> 扩展名方式匹配