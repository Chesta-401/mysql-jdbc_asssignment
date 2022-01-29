package com.bookstoreapplication.model.service;

import java.util.List;

import com.bookstoreapplication.model.exceptions.BookNotFoundException;
import com.bookstoreapplication.model.exceptions.DataAccessException;
import com.bookstoreapplication.model.persistence.Book;

public interface BookService {
	
	public List<Book> getAllBooks() throws DataAccessException;
	public Book getBookById(int bookId) throws BookNotFoundException,DataAccessException;
	public void addBook(Book book) throws DataAccessException;
	public void updateBook(Book book) throws DataAccessException;
	public void removeBook(int bookId) throws DataAccessException;
	
}