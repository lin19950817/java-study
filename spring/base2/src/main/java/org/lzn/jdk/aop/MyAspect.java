package org.lzn.jdk.aop;

/**
 * jdk 动态代理，切面类
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/11 23:48
 */
public class MyAspect {
    public void before() {
        System.out.println(this.getClass().getSimpleName().concat(".before()"));
    }
    public void after() {
        System.out.println(this.getClass().getSimpleName().concat(".after()"));
    }
}
