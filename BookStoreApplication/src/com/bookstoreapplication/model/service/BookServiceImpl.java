package com.bookstoreapplication.model.service;

import java.util.ArrayList;
import java.util.List;

import com.bookstoreapplication.model.exceptions.BookNotFoundException;
import com.bookstoreapplication.model.exceptions.DataAccessException;
import com.bookstoreapplication.model.persistence.Book;
import com.bookstoreapplication.model.persistence.BookDao;
import com.bookstoreapplication.model.persistence.BookDaoImpl;

public class BookServiceImpl implements BookService{

	private BookDao bookDao = null;
	
	public BookServiceImpl() {
		bookDao = new BookDaoImpl();
	}

	@Override
	public List<Book> getAllBooks() throws DataAccessException {
		List<Book> books = new ArrayList<>();
		books = bookDao.getAllBooks();
		return books;
	}

	@Override
	public Book getBookById(int bookId) throws BookNotFoundException, DataAccessException {
		Book book = null;
		book = bookDao.getBookById(bookId);
		return book;
	}

	@Override
	public void addBook(Book book) throws DataAccessException {
		bookDao.addBook(book);
	}

	@Override
	public void updateBook(Book book) throws DataAccessException {
		bookDao.updateBook(book);
	}

	@Override
	public void removeBook(int bookId) throws DataAccessException {
		bookDao.removeBook(bookId);
	}

}