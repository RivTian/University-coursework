package com.riotian.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.riotian.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookDaoTest {

    @Autowired
    private BookDao bookDao;

    @Test
    void testGetById() {
        System.out.println(bookDao.selectById(1));
    }

    @Test
    void testSave() {
        Book book = new Book();
        // mybatis-plus 雪花算法
        // id 需要配置才能成功 insert
        book.setName("测试数据123");
        book.setType("测试数据123");
        book.setDescription("测试数据123");
        bookDao.insert(book);
    }

    @Test
    void testUpdate() {
        Book book = new Book();
        book.setId(8);
        book.setName("测试数据222");
        book.setType("测试数据222");
        book.setDescription("测试数据222");
        bookDao.updateById(book);
    }

    @Test
    void testDelete() {
        bookDao.deleteById(8);
    }

    @Test
    void testFindAll() {
        bookDao.selectList(null);
    }

    @Test
    void testGetPage() {
        // 分页操作需要设定的分页对象 Ipage
        // 分页要想使用需要配置拦截器
        IPage iPage = new Page(2,5);
        bookDao.selectPage(iPage,null);
        System.out.println(iPage.getPages());
        System.out.println(iPage.getTotal());
        System.out.println(iPage.getClass());
        System.out.println(iPage.getCurrent());
        System.out.println(iPage.getRecords());
        System.out.println(iPage.getSize());
    }

    @Test
    // 条件查询
    void testGetBy() {
        // 1. 创建 queryWrapper 对象
        QueryWrapper<Book> qw = new QueryWrapper<>();
        // 2. 添加条件, 但要注意 "name" 要别写错
        qw.like("name", "Spring");
        bookDao.selectList(qw);
    }

    @Test
    // 条件查询
    void testGetBy2() {
        String var = "Spring";
        // 1. 创建 queryWrapper 对象
        LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<>();
        // 2. 添加条件, 利用 lambda 防止写错条件, 但是参数2 不会自己进行 null 检测
        lqw.like(var != null ,Book::getName, var);
        bookDao.selectList(lqw);
    }

}
