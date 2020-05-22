package org.lzn.jdk.aop;

import org.lzn.jdk.service.UserService;
import org.lzn.jdk.service.impl.UserServiceImpl;

import java.lang.reflect.Proxy;

/**
 * jdk 动态代理，工厂类
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/11 23:53
 */
public class MyBeanFactory {
    public static UserService createUserService() {
        // 目标类
        final UserServiceImpl userService = new UserServiceImpl();
        // 切面类
        final MyAspect myAspect = new MyAspect();
        // 代理类，将目标类（切入点）和切面类（通知）结合
        // Proxy.newProxyInstance 参数
        UserService proxyService = (UserService) Proxy.newProxyInstance(
                userService.getClass().getClassLoader(),
                userService.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    // 前方法
                    myAspect.before();

                    // 执行目标方法
                    Object invoke = method.invoke(userService, args);

                    // 后方法
                    myAspect.after();

                    return invoke;
                }
        );
        return proxyService;
    }
}
