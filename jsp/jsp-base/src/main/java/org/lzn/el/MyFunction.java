package org.lzn.el;

/**
 * EL 表达式的自定义方法
 *      1. 编写一个普通类，提供一个实现功能的静态方法
 *      2. 在 WEB-INF 目录中创建一个扩展名 .tld 的 xml 文件，文件不能放在 classes 和 lib 目录中
 *      3. 在 jsp 页面中使用 taglib 指令引入外部的标签库或方法库
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/12 16:29
 */
public class MyFunction {

    public static String toUpperCase(String str) {
        return str.toUpperCase();
    }
}
