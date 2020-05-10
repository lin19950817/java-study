package org.lzn.scope.impl;

import org.lzn.scope.BeanScope;

/**
 * bean 作用域为多例
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/08 21:56
 */
public class PrototypeBeanScopeImpl implements BeanScope {
    public PrototypeBeanScopeImpl() {
        System.out.println("PrototypeBeanScopeImpl 实例化了");
    }
}
