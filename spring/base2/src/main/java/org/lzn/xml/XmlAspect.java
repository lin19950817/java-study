package org.lzn.xml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 基于 xml，切面类
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/12 23:26
 */
public class XmlAspect {
    public void xmlAfter() {
        System.out.println(this.getClass().getSimpleName().concat(".xmlAfter()"));
    }
    public void xmlBefore() {
        System.out.println(this.getClass().getSimpleName().concat(".xmlBefore()"));
    }
    public void xmlAfterWithParameter(JoinPoint joinPoint, Object rtn) {
        System.out.println(this.getClass().getSimpleName().concat(".xmlAfterWithParameter()"));
        System.out.println("方法返回值：".concat(String.valueOf(rtn)));
    }
    public void xmlBeforeWithParameter(JoinPoint joinPoint) {
        System.out.println(this.getClass().getSimpleName().concat(".xmlAfterWithParameter()"));
        System.out.println("方法名称：".concat(joinPoint.getSignature().getName()));
    }
    public Object xmlAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println(this.getClass().getSimpleName().concat(".xmlAround() start"));

        // 手动执行目标方法
        Object obj = proceedingJoinPoint.proceed();

        System.out.println(this.getClass().getSimpleName().concat(".xmlAround() end"));
        return obj;
    }
    public void xmlAfterThrowing(JoinPoint joinPoint, Throwable throwable) {
        System.out.println(this.getClass().getSimpleName().concat(".xmlAfterThrowing()"));
        System.out.println("异常信息：".concat(throwable.getMessage()));
    }
}
