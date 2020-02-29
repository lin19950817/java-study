package org.lzn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * description
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/02/11 16:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private Integer id;
    private String name;
    private BigDecimal money;
}
