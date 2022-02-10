package com.riotian.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.riotian.domain.Book;

import java.util.List;

public interface BookService {
    public boolean save(Book book);
    public boolean update(Book book);
    public boolean delete(Integer id);
    public Book getById(Integer id);
    public List<Book> findAll();
    public IPage<Book> getPage(int currentPage, int PageSize);
}
