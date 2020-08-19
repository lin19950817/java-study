# HttpURLConnection 调用方式

使用 HttpURLConnection 调用方式，调用 [SOAP 接口](SOAP/readme.md#program)。设置的调用参数可查看 [此处](SOAP/readme.md#result)

## 步骤

1. 创建服务地址
2. 打开一个通向服务地址的连接
3. 设置参数。设置 `POST`，**POST** 必须为大写。设置输入输出为 `true`
4. 组织 SOAP 数据，发送请求
5. 接收服务端响应，打印

## 代码

```java
package org.lzn;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * HttpURLConnection 方式
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/08/16 23:41
 */
public class Main {

    public static void main(String[] args) throws Exception {
        // 1. 创建服务地址，不是 WSDL 地址
        URL url = new URL("http://127.0.0.1:54321/weather");

        // 2. 打开一个通向服务地址的连接
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        // 3. 设置参数
        // 3.1 发送方式：POST 必须大写
        httpURLConnection.setRequestMethod("POST");

        // 3.2 设置数据格式：Content-Type
        httpURLConnection.setRequestProperty("content-type", "application/soap+xml;charset=utf-8");

        // 3.3 设置输入输出，因为默认新创建的 connection 没有读写权限
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);

        // 4. 组织 SOAP 数据，发送请求
        String soapXml = getContent("广州");
        OutputStream outputStream = httpURLConnection.getOutputStream();
        outputStream.write(soapXml.getBytes());

        // 5. 接收服务端响应，打印
        int responseCode = httpURLConnection.getResponseCode();
        // 表示服务端响应成功
        if (responseCode == 200) {
            InputStream is = httpURLConnection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            StringBuilder sb = new StringBuilder();
            String temp;
            while(null != (temp = br.readLine())){
                sb.append(temp);
            }
            System.out.println(sb.toString());

            is.close();
            isr.close();
            br.close();
        }
        outputStream.close();
    }

    private static String getContent(String cityName) {
        return "<?xml version=\"1.0\" ?>\n" +
                "<S:Envelope xmlns:S=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                "\t<S:Body>\n" +
                "\t\t<ns2:queryWeather xmlns:ns2=\"http://impl.service.lzn.org/\">\n" +
                "\t\t\t<arg0>" + cityName + "</arg0>\n" +
                "\t\t</ns2:queryWeather>\n" +
                "\t</S:Body>\n" +
                "</S:Envelope>";
    }
}
```

