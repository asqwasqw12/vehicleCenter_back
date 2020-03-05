package com.eshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.dao.BookDao;
import com.eshop.pojo.Book;
import com.eshop.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	BookDao bookDao;
	
	@Override
	public int addBook(Book book) {
		return bookDao.addBook(book);
	}
	
   public  int deleteBookById(Integer id) {
	   return bookDao.deleteBookById(id);
   }
    public int updateBookById(Book book) {
    	return bookDao.updateBookById(book);
    }
   public  Book getBookById(Integer id) {
    	return bookDao.getBookById(id);
    }
  public   List<Book> getAllBooks(){
	  return bookDao.getAllBooks();
  }

}
