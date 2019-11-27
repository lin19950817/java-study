package org.lzn.util;

import java.util.HashMap;
import java.util.Map;

import org.lzn.entity.Book;

/**
 * description
 *
 * @author LinZhenNan lin.zhennan@hand-china.com 2019/11/26 15:15
 */
public class DBUtil {
    private static Map<String, Book> books;

    static {
        books = new HashMap<String, Book>(16);
        books.put("1", new Book("1", "乾坤大挪移", 20, "hehe"));
        books.put("2", new Book("2", "葵花宝典", 20, "hehe"));
        books.put("3", new Book("3", "九阴真经", 20, "hehe"));
        books.put("4", new Book("4", "九阳神功", 20, "hehe"));
    }

    /**
     * 查询所有书
     * 
     *
     * @author LinZhenNan lin.zhennan@hand-china.com 2019-11-26 15:47
     * @return java.util.Map<java.lang.String,org.lzn.entity.Book>
     */
    public static Map<String, Book> getBooks() {
        return books;
    }

    /**
     * 根据 id 查询书
     * 
     * @param id id
     * @author LinZhenNan lin.zhennan@hand-china.com 2019-11-26 15:48
     * @return org.lzn.entity.Book
     */
    public static Book findBookById(String id) {
        return books.get(id);
    }
}
