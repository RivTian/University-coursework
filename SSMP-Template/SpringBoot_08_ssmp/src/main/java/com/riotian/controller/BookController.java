package com.riotian.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.riotian.domain.Book;
import com.riotian.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController
// @RequestMapping("/books")
public class BookController {

    @Autowired
    private IBookService bookService;

    @GetMapping
    public List<Book> findAll() {
        return bookService.list();
    }

    @PostMapping
    public boolean save(@RequestBody Book book) {
        return bookService.save(book);
    }

    @PutMapping
    public boolean update(@RequestBody Book book) {
        return bookService.modify(book);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
        return bookService.delete(id);
    }

    // http://localhost/books/2
    @GetMapping("/{id}")
    public Book getById(@PathVariable Integer id) {
        return bookService.getById(id);
    }

    @GetMapping("{current}/{pageSize}")
    public List<Book> getPage(@PathVariable Integer current,@PathVariable Integer pageSize) {
        IPage<Book> page = bookService.getPage(current, pageSize, null);
        return page.getRecords();
    }

}
