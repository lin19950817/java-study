# HttpURLConnection 调用方式

使用 HttpURLConnection 调用方式，调用 [SOAP 接口](SOAP/readme.md#program)。设置的调用参数可查看 [此处](SOAP/readme.md#result)

## 步骤

1. 创建服务地址
2. 打开一个通向服务地址的连接
3. 设置参数。设置 `POST`，**POST** 必须为大写。设置输入输出为 `true`
4. 组织 SOAP 数据，发送请求
5. 接收服务端响应，打印

