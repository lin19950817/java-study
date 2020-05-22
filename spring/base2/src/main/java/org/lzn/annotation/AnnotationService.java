package org.lzn.annotation;

import org.springframework.stereotype.Service;

/**
 * 基于注解，目标类
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/12 23:23
 */
@Service("annotationService")
public class AnnotationService {
    public void addUser() {
        System.out.println(this.getClass().getSimpleName().concat(".addUser()"));
    }
    public void updateUser() {
        System.out.println(this.getClass().getSimpleName().concat(".updateUser()"));
    }
    public void deleteUser() {
        System.out.println(this.getClass().getSimpleName().concat(".deleteUser()"));
    }
    public void exception() {
        System.out.println(this.getClass().getSimpleName().concat(".exception()"));
        int i = 1 / 0;
    }
}
