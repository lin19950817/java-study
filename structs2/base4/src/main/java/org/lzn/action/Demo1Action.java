package org.lzn.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 利用 ActionContext 存数据
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/13 20:26
 */
public class Demo1Action extends ActionSupport {
    @Override
    public String execute() {
        // 1. 得到 ActionContext 对象
        // 这是从当前线程的局部变量中获取引用
        ActionContext context = ActionContext.getContext();
        // 2. 往 map 中存入数据，数据直接存到大 map 中
        context.put("contextMap", "hello contextMap");

        // 往 session 中存数据
        // 第一种方式：获取 key 为 session 的 map。得到 key 值为 session 的小 map
        Map<String, Object> session = context.getSession();
        session.put("sessionMap", "hello sessionMap");
        // 第二种方式：直接使用原始的 HttpSession 对象
        HttpSession session2 = ServletActionContext.getRequest().getSession();
        session.put("sessionMap2", "hello sessionMap2");

        // 往 ServletContext 域中存数据
        // 第一种方式：获取 key 值为 application 的 map
        Map<String, Object> application = context.getApplication();
        application.put("applicationMap", "hello applicationMap");
        // 第二种方式：获取原始的 ServletContext 对象
        ServletContext application2 = ServletActionContext.getServletContext();
        application2.setAttribute("applicationMap2", "hello applicationMap2");

        return SUCCESS;
    }
}
