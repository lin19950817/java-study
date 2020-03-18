package org.lzn.attributeListener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;

/**
 * ServletRequestAttributeListener 实现类
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/03/17 21:32
 */
public class MyServletRequestAttributeListener implements ServletRequestAttributeListener {

    public void attributeAdded(ServletRequestAttributeEvent srae) {
        System.out.println("ServletRequest 添加属性了");
    }

    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        System.out.println("ServletRequest 移除属性了");
    }

    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        System.out.println("ServletRequest 替换属性了");

        // srea 参数为事件源对象
        System.out.println(srae.getName() + "\t" + srae.getValue());
    }
}
