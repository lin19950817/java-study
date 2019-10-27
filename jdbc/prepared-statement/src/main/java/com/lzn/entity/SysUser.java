package com.lzn.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.Test;

/**
 * description
 *
 * @author LinZhenNan 2019/08/19 15:15
 */
@Getter
@Setter
@ToString
public class SysUser {
    private Integer id;
    private String username;
    private String password;

}
