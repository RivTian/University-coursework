package com.riotian.service.impl;

import com.riotian.domain.Books;
import com.riotian.mapper.BookMapper;
import com.riotian.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService{

    private BookMapper bookMapper;

    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }
    public int addBook(Books book) {
        return bookMapper.addBook(book);
    }
    public int deleteBookById(int id) {
        return bookMapper.deleteBookById(id);
    }
    public int updateBook(Books books) {
        return bookMapper.updateBook(books);
    }
    public Books queryBookById(int id) {
        return bookMapper.queryBookById(id);
    }
    public List<Books> queryAllBook() {
        return bookMapper.queryAllBook();
    }
}
