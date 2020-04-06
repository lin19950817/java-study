package base.org.lzn.web.action;

/**
 * Struct2 的案例
 * 创建动作类的第一种方法：
 *  创建一个普通的 java 类
 *  他就是一个 POJO，原始的老的 java 对象
 *  Plain Old Java Object
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/05 17:44
 */
public class HelloAction {

    /**
     * 在动作类中指定的动作方法
     * 动作方法的书写要求：
     * 1. 都是 public 的
     * 2. 返回值必须是一个 String
     * 3. 必须没有参数
     *
     * @return java.lang.String
     * @author LinZhenNan lin_hehe@qq.com 2020-04-05 17:46
     */
    public String sayHello() {
        System.out.println("HelloAction 的 sayHello 方法执行了");

        // 与配置文件中 result 的 name 取值相对应
        return "success";
    }


    public String sayBye() {
        System.out.println("HelloAction 的 sayBye 方法执行了");
        return "bye";
    }
}
