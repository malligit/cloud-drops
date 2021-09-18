package com.malli.labs.db.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.malli.labs.db.dto.Book;
import com.malli.labs.db.repositories.BookRepository;

@Service
public class BookService {
	@Autowired
	BookRepository repo;
	
	public Book saveBook(Book book) {
		Book b = repo.save(book);
	
		return b;		
	}
	
	public Book findById(long id) {
		return repo.findById(id).get();
	}
	public Iterable<Book> findAllBooks() {
		return repo.findAll();
	}
	
}
