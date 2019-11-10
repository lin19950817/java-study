package org.lzn.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 表单数据实体类
 *
 * @author LinZhenNan lin_hehe@qq.com 2019/11/10 19:03
 */
@Getter
@Setter
@ToString
public class Register {
    private String userName;
    private String pwd;
    private String sex;
    private String[] hobby;
    private String city;
}
