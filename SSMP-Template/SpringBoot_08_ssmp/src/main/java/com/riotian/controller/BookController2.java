package com.riotian.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.riotian.controller.utils.R;
import com.riotian.domain.Book;
import com.riotian.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/books")
public class BookController2 {

    @Autowired
    private IBookService bookService;

    @GetMapping
    public R findAll() {
        return new R(true, bookService.list());
    }

    @PostMapping
    public R save(@RequestBody Book book) throws IOException {
        if(book.getName().equals("123")) throw new IOException();
        boolean flag = bookService.save(book);
        return new R(flag, flag ? "添加成功^_^" : "添加失败-_-!");
        // return new R(false); // 测试添加失败
    }

    @PutMapping
    public R update(@RequestBody Book book) throws IOException {
        if(book.getName().equals("123")) throw new IOException();
        boolean flag = bookService.modify(book);
        return new R(flag, flag ? "修改成功^_^" : "修改失败-_-!");
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        return new R(bookService.delete(id));
    }

    // http://localhost/books/2
    @GetMapping("/{id}")
    public R getById(@PathVariable Integer id) {
        return new R(true,bookService.getById(id));
    }

    // @GetMapping("{current}/{pageSize}")
    // public R getPage(@PathVariable Integer current,@PathVariable Integer pageSize) {
    //     IPage<Book> page = bookService.getPage(current, pageSize);
    //     // 如果当前页码值大于了总页码值，那么重新执行查询操作，使用当前最大页码值作为查询值
    //     if(current >  page.getPages()) {
    //         page = bookService.getPage((int) page.getPages(), pageSize);
    //     }
    //     return new R(true, page);
    // }

    // 处理带查询的分页
    @GetMapping("{current}/{pageSize}")
    public R getPage(@PathVariable Integer current,@PathVariable Integer pageSize, String name, Book book) {
        // System.out.println("参数===>" + name);
        // System.out.println("参数===>" + book);

        IPage<Book> page = bookService.getPage(current, pageSize, book);
        // 如果当前页码值大于了总页码值，那么重新执行查询操作，使用当前最大页码值作为查询值
        if(current >  page.getPages()) {
            page = bookService.getPage((int) page.getPages(), pageSize, book);
        }
        return new R(true, page);
    }

}
