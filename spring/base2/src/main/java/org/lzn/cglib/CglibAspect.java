package org.lzn.cglib;

/**
 * cglib 代理，切面类
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/12 23:26
 */
public class CglibAspect {
    public void cglibAfter() {
        System.out.println(this.getClass().getSimpleName().concat(".cglibAfter()"));
    }
    public void cglibBefore() {
        System.out.println(this.getClass().getSimpleName().concat(".cglibBefore()"));
    }
}
