package org.lzn.scope.impl;

import org.lzn.scope.BeanScope;

/**
 * bean 作用域为单例
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/08 21:53
 */
public class SingleBeanScopeImpl implements BeanScope {
    public SingleBeanScopeImpl() {
        System.out.println("SingleBeanScopeImpl 实例化了");
    }
}
