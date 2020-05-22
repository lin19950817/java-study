package org.lzn.xml;

/**
 * 基于 xml，目标类
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/12 23:23
 */
public class XmlService {
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
