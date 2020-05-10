package org.lzn.lifecycle.impl;

import org.lzn.lifecycle.BeanLifecycle;

/**
 * description
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/08 22:25
 */
public class BeanLifecycleImpl implements BeanLifecycle {
    @Override
    public void lifecycle() {
        System.out.println("BeanLifecycleImpl.lifecycle() 执行了");
    }

    public void myInit() {
        System.out.println("BeanLifecycleImpl.myInit()");
    }

    public void myDestroy() {
        System.out.println("BeanLifecycleImpl.myDestroy()");
    }
}
