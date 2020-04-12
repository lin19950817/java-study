package org.lzn.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 自定义拦截器
 *      第一步：创建一个普通类，继承 AbstractInterceptor，实现抽象方法 intercept
 *      第二步：在 struts.xml 中配置拦截器
 *          1. 声明拦截器
 *          2. 使用拦截器
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/11 16:52
 */
public class Demo1Interceptor extends AbstractInterceptor {
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        System.out.println(this.getClass().getSimpleName().concat("拦截器：拦截了-执行动作方法之前"));
        // 放行。如果有下一个拦截器，就前往下一个拦截器，如果没有了，就到达动作方法。
        String resultValue = invocation.invoke();
        System.out.println(resultValue);
        System.out.println(this.getClass().getSimpleName().concat("拦截器：拦截了-执行动作方法之后"));
        return resultValue;
    }
}
