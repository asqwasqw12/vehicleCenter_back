package com.eshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eshop.pojo.Book;
import com.eshop.service.BookService;

@RestController
public class BookController {
	/*@Autowired
    BookService bookService;
	
	@GetMapping("/bookOps")
    public void bookOps() {
        Book b1 = new Book();
        b1.setName("西厢记");
        b1.setAuthor("王实甫");
        int i = bookService.addBook(b1);
        System.out.println("addBook>>>" + i);
        Book b2 = new Book();
        b2.setId(1);
        b2.setName("朝花夕拾");
        b2.setAuthor("鲁迅");
        int updateBook = bookService.updateBookById(b2);
        System.out.println("updateBook>>>"+updateBook);
        Book b3 = bookService.getBookById(1);
        System.out.println("getBookById>>>"+b3);
        int delete = bookService.deleteBookById(2);
        System.out.println("deleteBookById>>>"+delete);
        List<Book> allBooks = bookService.getAllBooks();
        System.out.println("getAllBooks>>>"+allBooks);
    }*/
	
	@Autowired
	RedisTemplate redisTemplate;
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	@GetMapping("/bookOps")
	public void test1() {
		ValueOperations<String,String> ops1 = stringRedisTemplate.opsForValue();
		ops1.set("name","三国演绎");
		String name = ops1.get("name");
		System.out.println(name);
		ValueOperations ops2 = redisTemplate.opsForValue();
		Book b1 = new Book();
		b1.setId(1);
		b1.setName("红楼梦");
		b1.setAuthor("曹雪芹");
		ops2.set("b1",b1);
		Book book = (Book) ops2.get("b1");
		System.out.println(book);
	}

}
