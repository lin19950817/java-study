package org.lzn.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 切面类中确定通知，需要实现不同接口，接口就是规范，从而就确定方法名称<br>
 *     采用“环绕通知”MethodInterceptor`
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/13 21:47
 */
public class AopAspect implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println(this.getClass().getSimpleName().concat(".invoke() start"));

        // 手动执行目标方法
        Object proceed = invocation.proceed();

        System.out.println(this.getClass().getSimpleName().concat(".invoke() end"));
        return proceed;
    }
}
