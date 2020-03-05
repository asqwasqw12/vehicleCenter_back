package com.eshop.service;

import java.util.List;

import com.eshop.pojo.Book;

public interface BookService {
	
	int addBook(Book book);
    int deleteBookById(Integer id);
    int updateBookById(Book book);
    Book getBookById(Integer id);
    List<Book> getAllBooks();

}
