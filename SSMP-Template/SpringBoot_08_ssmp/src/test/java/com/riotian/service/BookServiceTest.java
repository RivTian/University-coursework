package com.riotian.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.riotian.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private IBookService iBookService;

    @Test
    void testGetAll2() {
        System.out.println(iBookService.list());
    }

    @Test
    void testGetById() {
        System.out.println(bookService.getById(4));
    }

    @Test
    void testSave() {
        Book book = new Book();
        book.setType("测试数据44");
        book.setName("测试数据44");
        book.setDescription("测试数据44");
        bookService.save(book);
    }

    @Test
    void testUpdate() {
        Book book = new Book();
        book.setId(9);
        book.setType("测试数据44");
        book.setName("测试数据33");
        book.setDescription("测试数据33");
        bookService.update(book);
    }

    @Test
    void testDelete() {
        bookService.delete(9);
    }

    @Test
    void testGetAll() {
        System.out.println(bookService.findAll());
    }

    @Test
    void testGetPage() {
        IPage<Book> iPage = bookService.getPage(2, 5);
        System.out.println(iPage.getPages());
        System.out.println(iPage.getTotal());
        System.out.println(iPage.getClass());
        System.out.println(iPage.getCurrent());
        System.out.println(iPage.getRecords());
        System.out.println(iPage.getSize());
    }

}