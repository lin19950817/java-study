package org.lzn.post_processor;

import org.lzn.lifecycle.BeanLifecycle;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * BeanPostProcessor 实现类
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/08 23:01
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("MyBeanPostProcessor：bean 初始化之前，" + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("MyBeanPostProcessor：bean 初始化之后，" + beanName);

        // BeanLifecycleImpl 生成 jdk 代理
        if (bean instanceof BeanLifecycle) {
            return Proxy.newProxyInstance(
                    MyBeanPostProcessor.class.getClassLoader(),
                    bean.getClass().getInterfaces(),
                    (proxy, method, args) -> {
                        // 执行目标方法
                        Object invoke = method.invoke(bean, args);
                        return invoke;
                    });
        }
        return bean;
    }
}
