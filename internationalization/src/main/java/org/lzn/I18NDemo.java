package org.lzn;

import org.junit.Test;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 国际化的小测试
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/08 23:14
 */
public class I18NDemo {

    @Test
    public void demo1() {
        // 使用 ResourceBundle 的 getBundle 方法获取一个对象，参数使用消息资源包文件路径 + 名称和所在的语言环境
        // 主页在给定信息资源包路径时，不需要指定语言代码和国家代码
        ResourceBundle resourceBundle = ResourceBundle.getBundle("message", Locale.SIMPLIFIED_CHINESE);
        String key = resourceBundle.getString("key");
        System.out.println(key);

        // 更换语言环境
        resourceBundle = ResourceBundle.getBundle("message", Locale.US);
        key = resourceBundle.getString("key");
        System.out.println(key);
    }
}
