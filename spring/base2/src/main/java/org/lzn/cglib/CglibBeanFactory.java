package org.lzn.cglib;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib 代理，工厂类
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/12 23:25
 */
public class CglibBeanFactory {
    public static CglibService createCglibService() {
        // 目标类
        final CglibService cglibService = new CglibService();
        // 切面类
        final CglibAspect cglibAspect = new CglibAspect();

        // 代理类，采用 cglib，底层创建目标类的子类
        // 核心类
        Enhancer enhancer = new Enhancer();
        // 确定父类
        enhancer.setSuperclass(cglibService.getClass());
        // 设置毁掉函数，MethodInterceptor 接口等效 jdk InvocationHandler 接口
        enhancer.setCallback((MethodInterceptor) (obj, method, args, methodProxy) -> {
            // 前置
            cglibAspect.cglibBefore();

            // 执行目标方法
            Object invoke = method.invoke(cglibService, args);

            // 后置
            cglibAspect.cglibAfter();

            return invoke;
        });
        // 创建代理
        CglibService proxyService = (CglibService) enhancer.create();

        return proxyService;
    }
}
