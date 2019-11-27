package org.lzn.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 书 实体
 *
 * @author LinZhenNan lin.zhennan@hand-china.com 2019/11/26 15:12
 */
@ToString
@Setter
@Getter
@AllArgsConstructor
public class Book {
    private String id;
    private String name;
    private double price;
    private String author;
}
