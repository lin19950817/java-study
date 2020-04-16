package org.lzn.domain;

import java.io.Serializable;

/**
 * 演示值栈的存取
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/13 21:45
 */
public class Student implements Serializable {
    private String name;
    private int age;

    //
    // setter/getter
    // ------------------------------------------------------------------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    //
    // constructor
    // ------------------------------------------------------------------------------


    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
