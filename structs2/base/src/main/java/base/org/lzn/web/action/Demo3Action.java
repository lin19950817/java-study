package base.org.lzn.web.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 创建动作类第三种方式
 * 创建一个类，继承 ActionSupport。类中已实现默认动作方法：execute()
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/06 0:44
 */
public class Demo3Action extends ActionSupport {
    /**
     * 默认动作方法
     *
     * @return java.lang.String
     * @author LinZhenNan lin_hehe@qq.com 2020-04-06 12:02
     */
    public String defaultAction() throws Exception {
        return super.execute();
    }
}
