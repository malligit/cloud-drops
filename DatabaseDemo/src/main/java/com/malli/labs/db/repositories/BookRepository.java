package com.malli.labs.db.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.malli.labs.db.dto.Book;

@Repository
public interface BookRepository extends CrudRepository<Book,Long>{

}
