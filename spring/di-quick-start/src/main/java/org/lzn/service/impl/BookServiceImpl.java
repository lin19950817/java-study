package org.lzn.service.impl;

import org.lzn.dao.BookDao;
import org.lzn.service.BookService;

/**
 * description
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/06 21:58
 */
public class BookServiceImpl implements BookService {
    private BookDao bookDao;
    
    @Override
    public void addBook() {
        System.out.println("BookServiceImpl.addBook");
        bookDao.addBook();
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}
