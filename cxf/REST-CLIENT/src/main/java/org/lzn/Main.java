package org.lzn;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * CXF 发布 REST 的服务（客户端）
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/09/13 19:32
 */
public class Main {

    public static void main(String[] args) throws Exception {
        // 第一步：创建服务地址，不是 WSDL 地址
        URL url = new URL("http://127.0.0.1:12345/user/student/23");
        // 第二步：打开一个通向服务地址的连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // 第三部：设置参数
        // 3.1 发送方式设置：GET 大写
        connection.setRequestMethod("GET");
        // 3.2 设置数据格式：connect-type
        // 3.3 设置输入输出，应为默认新创建的 connection 没有读写权限
        connection.setDoInput(true);

        // 第五步，接收服务端响应，打印
        int responseCode = connection.getResponseCode();

        // 服务端响应成功
        if (responseCode == 200) {
            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder sb = new StringBuilder();
            String temp = null;
            while ((temp = bufferedReader.readLine()) != null) {
                sb.append(temp);
            }
            System.out.println(sb.toString());

            inputStream.close();
            inputStreamReader.close();
            bufferedReader.close();
        }
    }
}
