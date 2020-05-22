package org.lzn.cglib;

/**
 * cglib 代理，目标类
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/12 23:23
 */
public class CglibService {
    public void addUser() {
        System.out.println(this.getClass().getSimpleName().concat(".addUser()"));
    }
    public void updateUser() {
        System.out.println(this.getClass().getSimpleName().concat(".updateUser()"));
    }
    public void deleteUser() {
        System.out.println(this.getClass().getSimpleName().concat(".deleteUser()"));
    }
}
