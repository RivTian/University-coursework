package com.riotian.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.riotian.domain.Book;

public interface IBookService extends IService<Book> {
    // 追加功能和原功能类似，但命名不同
    boolean saveBook(Book book);
    boolean modify(Book book);
    boolean delete(Integer id);

    IPage<Book> getPage(int currentPage, int pageSize);
    IPage<Book> getPage(int currentPage, int pageSize, Book book);
}
