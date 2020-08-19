# Webservice

[TOC]

## Webservice

> Web Service 是一个平台独立的，低耦合的，自包含的、基于可编程的 web 的应用程序，可使用开放的XML（标准通用标记语言下的一个子集）标准来描述、发布、发现、协调和配置这些应用程序，用于开发分布式的交互操作的应用程序。
>
> Web Service 技术， 能使得运行在不同机器上的不同应用无须借助附加的、专门的第三方软件或硬件， 就可相互交换数据或集成。依据 Web Service 规范实施的应用之间， 无论它们所使用的语言、 平台或内部协议是什么， 都可以相互交换数据。Web Service 是自描述、 自包含的可用网络模块， 可以执行具体的业务功能。Web Service也很容易部署， 因为它们基于一些常规的产业标准以及已有的一些技术，诸如标准通用标记语言下的子集 XML、HTTP。Web Service 减少了应用接口的花费。Web Service 为整个企业甚至多个组织之间的业务流程的集成提供了一个通用机制。

> Web 服务是一种服务导向架构的技术，通过标准的 Web 协议提供服务，目的是保证不同平台的应用服务可以互操作。
>
> 根据 W3C 的定义，Web 服务（Web service）应当是一个软件系统，用以支持网络间不同机器的互动操作。网络服务通常是许多应用程序接口（API）所组成的，它们透过网络，例如国际互联网（Internet）的远程服务器端，执行客户所提交服务的请求。
>
> 尽管 W3C 的定义涵盖诸多相异且无法介分的系统，不过通常我们指有关于主从式架构（Client-server）之间根据 SOAP 协议进行传递XML格式消息。无论定义还是实现，Web 服务过程中会由服务器提供一个机器可读的描述（通常基于WSDL）以辨识服务器所提供的 WEB 服务。另外，虽然 WSDL 不是 SOAP 服务端点的必要条件，但目前基于 Java 的主流 Web 服务开发框架往往需要WSDL实现客户端的源代码生成。一些工业标准化组织，比如 WS-I，就在 Web 服务定义中强制包含 SOAP 和 WSDL。

### 技术支持

Web Service平台需要一套协议来实现分布式应用程序的创建。任何平台都有它的数据表示方法和类型系统。要实现互操作性，Web Service平台必须提供一套标准的类型系统，用于沟通不同平台、编程语言和组件模型中的不同类型系统。通常包括：`SOAP、WSDL、UDDI`。

#### [SOAP](SOAP)

一个基于 XML 的可扩展消息信封格式，需同时绑定一个网络传输协议。这个协议通常是 HTTP 或 HTTPS，但也可能是 SMTP 或 XMPP。

#### [WSDL](WSDL)

一个 XML 格式文档，用以描述服务端口访问方式和使用协议的细节。通常用来辅助生成服务器和客户端代码及配置信息。

#### UDDI

一个用来发布和搜索WEB服务的协议，应用程序可借由此协议在设计或运行时找到目标WEB服务。
这些标准由这些组织制订：W3C 负责 XML、SOAP 及 WSDL；OASIS 负责 UDDI。

**UDDI 并不像 WSDL 和 SOAP 一样深入人心，因为很多时候，使用者知道 Web 服务的位置（通常位于公司的企业内部网中）。**

## webservice 四种客户端调用方式

### <a name="wsimport" style="text-decoration:none">wsimport</a>

Wsimport 就是 jdk 提供的的一个工具，他作用就是根据 WSDL 地址生成客户端代码。位置 `JAVA_HOME/bin`。**仅支持SOAP1.1客户端的生成**。

Wsimport常用的参数：

* -s：指定 `.java` 文件的输出目录
* -d：指定 `.class` 文件的输出目录
* -p：指定包名的，如果不加该参数，默认包名就是 wsdl 文档中的命名空间的倒序
* -verbose：砸死控制台显示输出信息
* -b：指定 jaxws/jaxb 绑定文件或额外的 schemas
* -extension：使用扩展来支持 SOAP1.2

[使用例子](WSDL/readme.md#wsimport)

### service 编程调用方式

使用 wsimport 生成客户端，通过自己创建服务视图方式来调用服务端。

[使用例子](SOAP/readme.md#program)

### HttpURLConnection 调用方式

一个支持 HTTP 特定功能的 URLConnection。

每个 HttpURLConnection 实例都用于发出单个请求，但与 HTTP 服务器的基础网络连接可以由其他实例透明共享。请求后在 HttpURLConnection 的 InputStream 或 OutputStream 上调用 close() 方法可能会释放与此实例关联的网络资源，但对任何共享的持久连接没有影响。如果持久连接当时处于空闲状态，则调用 disconnect() 方法可能会关闭基础套接字。

[使用例子](http-url-connection/readme.md)

### Ajax 调用方式
