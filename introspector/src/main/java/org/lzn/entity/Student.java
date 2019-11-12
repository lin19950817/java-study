package org.lzn.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 实体类
 *
 * @author LinZhenNan lin.zhennan@hand-china.com 2019/11/11 15:23
 */
@Getter
@Setter
@ToString
public class Student {
    private String name;
    private int age;
    private Date birthday;

    public int getTen() {
        return 10;
    }
}
