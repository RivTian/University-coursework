package com.riotian.mapper;

import com.riotian.domain.Books;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BookMapper {

    //增加一个Book
    @Insert("insert into books(bookName,bookCounts,detail) values (#{bookName}, #{bookCounts}, #{detail})")
    public int addBook(Books book);

    //根据id删除一个Book
    @Delete("delete from books where bookID=#{bookID}")
    public int deleteBookById(int id);

    //更新Book
    @Update("update books set bookName = #{bookName},bookCounts = #{bookCounts},detail = #{detail} where bookID = #{bookID}")
    public int updateBook(Books books);

    //根据id查询,返回一个Book
    @Select("select * from books where bookID = #{bookID}")
    public Books queryBookById(int id);

    //查询全部Book,返回list集合
    @Select("select * from books")
    public List<Books> queryAllBook();

}
