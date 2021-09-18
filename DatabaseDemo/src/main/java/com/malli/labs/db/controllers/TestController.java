package com.malli.labs.db.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.malli.labs.db.dto.Book;
import com.malli.labs.db.services.BookService;

@RestController
public class TestController {

	@Autowired
	BookService service;
	
	@GetMapping("/test")
	public String getMSg() {
		return "Hello world";
	}
	
	@GetMapping("/get-book-by-id")
	public Book findBookById(@RequestParam long id ) {
		return service.findById(id);
	}
	
	@GetMapping("/find-all-books")
	public Iterable<Book> getAllBooks() {
		return service.findAllBooks();
	}
	
	@PostMapping("/save-book")
	public Book saveBook(@RequestBody Book b) {
		return service.saveBook(b);
	}
	
	
}
