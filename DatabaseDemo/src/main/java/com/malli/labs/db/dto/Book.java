package com.malli.labs.db.dto;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;


@Data
@Entity
public class Book {
	@Id
	@GeneratedValue
	private long id;
	private String title;
	private Date publishedDate;
	private String author;
}
