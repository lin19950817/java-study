package org.lzn.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 自定义拦截器，不拦截 action1
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/11 16:52
 */
public class Demo1MethodInterceptor extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        System.out.println(this.getClass().getSimpleName().concat("拦截器：拦截了-执行动作方法之前"));
        // 可以判断 session 中是否有登录信息，没有则跳转到登录页面
        // 放行
        String result = invocation.invoke();
        System.out.println(this.getClass().getSimpleName().concat("拦截器：拦截了-执行动作方法之后"));
        return result;
    }
}
