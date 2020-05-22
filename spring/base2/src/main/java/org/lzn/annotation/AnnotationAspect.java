package org.lzn.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 基于注解，切面类
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/12 23:26
 */
@Component
@Aspect
public class AnnotationAspect {

    /**
     * 声明公共切入点，方法名就是 id
     * 相当于：<aop:pointcut id="myPointcut" expression="execution(* org.lzn.annotation.AnnotationService.*(..))"/>
     *
     * @author LinZhenNan lin_hehe@qq.com
     */
    @Pointcut("execution(* org.lzn.annotation.AnnotationService.*(..))")
    public void myPointcut() {}

    /**
     * 切入点当前有效
     * 相当于：<aop:before method="annotationAfter" pointcut="execution(* org.lzn.annotation.AnnotationService.*(..))"/>
     *
     * @author LinZhenNan lin_hehe@qq.com
     */
//    @Before("execution(* org.lzn.annotation.AnnotationService.*(..))")
    public void annotationAfter() {
        System.out.println(this.getClass().getSimpleName().concat(".annotationAfter()"));
    }
    public void annotationBefore() {
        System.out.println(this.getClass().getSimpleName().concat(".annotationBefore()"));
    }
    /**
     * 相当于：<aop:after-returning method="annotationAfterWithParameter" pointcut-ref="myPointcut" returning="rtn"/>
     *
     * @param joinPoint 用于描述连接点（目标方法）
     * @param rtn       目标方法返回值
     * @author LinZhenNan lin_hehe@qq.com
     */
//    @AfterReturning(value = "myPointcut()", returning = "rtn")
    public void annotationAfterWithParameter(JoinPoint joinPoint, Object rtn) {
        System.out.println(this.getClass().getSimpleName().concat(".annotationAfterWithParameter()"));
        System.out.println("方法返回值：".concat(String.valueOf(rtn)));
    }
    public void annotationBeforeWithParameter(JoinPoint joinPoint) {
        System.out.println(this.getClass().getSimpleName().concat(".annotationAfterWithParameter()"));
        System.out.println("方法名称：".concat(joinPoint.getSignature().getName()));
    }
    /**
     * 相当于：<aop:around method="xmlAround" pointcut-ref="myPointcut"/>
     *
     * @param proceedingJoinPoint 处理连接点对象
     * @return java.lang.Object 
     * @author LinZhenNan lin_hehe@qq.com  
     */
    @Around("myPointcut()")
    public Object annotationAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println(this.getClass().getSimpleName().concat(".annotationAround() start"));

        // 手动执行目标方法
        Object obj = proceedingJoinPoint.proceed();

        System.out.println(this.getClass().getSimpleName().concat(".annotationAround() end"));
        return obj;
    }
    public void annotationAfterThrowing(JoinPoint joinPoint, Throwable throwable) {
        System.out.println(this.getClass().getSimpleName().concat(".annotationAfterThrowing()"));
        System.out.println("异常信息：".concat(throwable.getMessage()));
    }
}
