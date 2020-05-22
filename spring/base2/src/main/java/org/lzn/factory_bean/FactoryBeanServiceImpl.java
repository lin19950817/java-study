package org.lzn.factory_bean;

/**
 * spring 编写代理-半自动，目标类
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/13 21:58
 */
public class FactoryBeanServiceImpl implements FactoryBeanService {
    @Override
    public void add() {
        System.out.println(this.getClass().getSimpleName().concat(".add()"));
    }

    @Override
    public void update() {
        System.out.println(this.getClass().getSimpleName().concat(".update()"));
    }

    @Override
    public void delete() {
        System.out.println(this.getClass().getSimpleName().concat(".delete()"));
    }
}
