package org.lzn.xml.annotation.lifecycle_scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 注解，生命周期和作用域
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/10 22:29
 */
@Component("lifecycleAndScope")
@Scope("prototype")
public class LifecycleAndScope {
    public void func() {
        System.out.println("LifecycleAndScope.func()");
    }

    @PostConstruct
    public void myInit() {
        System.out.println("LifecycleAndScope 初始化");
    }

    @PreDestroy
    public void myDestroy() {
        System.out.println("LifecycleAndScope 销毁");
    }
}
