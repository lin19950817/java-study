package org.lzn.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.ServletActionContext;
import org.lzn.domain.Student;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 利用 ValueStack 存数据
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/13 20:26
 */
public class Demo2Action extends ActionSupport {
    /**
     * ValueStack 属性测试，存在两个 name
     */
    private String name = "haha";

    @Override
    public String execute() {
        // 获取 ValueStack 的引用
        // 第一种方式：获取 HttpServletRequest 对象，通过对象的 getAttribute 方法，从域中取
        // 1. 获取 request 对象
        HttpServletRequest request = ServletActionContext.getRequest();
        // 2. 根据 key 获取 ValueStack 对象的引用
        ValueStack valueStack = (ValueStack) request.getAttribute("struts.valueStack");
        System.out.println(valueStack.hashCode());

        // 第二种方式：先获取 ActionContext 对象，在取出 requestMap，然后通过 map 的 get 方法获取
        // 1. 获取 ActionContext 对象
        ActionContext context = ActionContext.getContext();
        // 2. 获取 requestMap
        Map<String, Object> request2 = (Map<String, Object>) context.get("request");
        // 3. 根据 key 获取对象的引用
        ValueStack valueStack2 = (ValueStack) request2.get("struts.valueStack");
        System.out.println(valueStack2.hashCode());

        // 第三种方式：使用 ActionContext 对象方法，直接获取 ValueStack 对象的引用
        ValueStack valueStack3 = context.getValueStack();
        System.out.println(valueStack3.hashCode());

        return SUCCESS;
    }

    /**
     * 栈的操作
     *
     * @return java.lang.String
     * @author LinZhenNan lin_hehe@qq.com 2020-04-13 21:50
     */
    public String operate() {
        ValueStack valueStack = ActionContext.getContext().getValueStack();
        // 压栈操作
        valueStack.push(new Student("hehe", 18));

        // 使用 setValue(String expr, Object value)，expr: 他是一个 OGNL 表达式，value: 我们要操作的数据。
        // expr 使用 #，则把数据存到 ContextMap，否则存到 ValueStack
        // 数据存在 ContextMap 中。key 是 name，value 是 张三
        valueStack.setValue("#name", "张三");
        // 数据存在 ValueStack 中，第一个 name 属性的值换成 李四。如果没有一个 name 属性对应的 setName 方法，报错
        valueStack.setValue("name", "李四");

        // 使用 set(String key, Object o)
        // 如果栈顶是一个 Map 元素，直接把 key 作为 map 的 key，把 Object 作为 map 的 value 存放，存入的栈顶。
        // 如果栈顶不是一个 Map 元素，创建一个 Map 对象，把 key 作为 map 的 key，把 Object 作为 map 的 value，压入栈内。
        valueStack.set("s1", new Student("王五", 20));
        valueStack.set("name", "赵六");

        return SUCCESS;
    }

    //
    // setter/getter
    // ------------------------------------------------------------------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
