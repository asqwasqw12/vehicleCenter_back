package com.eshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.eshop.pojo.Book;

public interface BookDao {
	
	@Insert("insert into book(name,author) values(#{name},#{author})")
    int addBook(Book book);
	
	@Delete("delete from book where id=#{id}")
    int deleteBookById(Integer id);
	
	@Update("update book set  name=#{name},author=#{author} WHERE id=#{id}")
    int updateBookById(Book book);
	
	@Select("select * from book where id=#{id}")
    Book getBookById(Integer id);
	
	@Select("select * from book")
    List<Book> getAllBooks();

}
