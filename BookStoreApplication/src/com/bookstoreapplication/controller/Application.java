package com.bookstoreapplication.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.bookstoreapplication.model.persistence.Book;
import com.bookstoreapplication.model.service.BookService;
import com.bookstoreapplication.model.service.BookServiceImpl;

public class Application {

	public static void main(String[] args) {
		BookService bookService = new BookServiceImpl();

		// Adding new book records
		System.out.println("Adding book records");
		bookService.addBook(new Book("1234","Java","Chesta",Date.valueOf("2022-01-04"),214.55));
		bookService.addBook(new Book("4567","C++","Shruti",Date.valueOf("2022-01-10"),404.55));
		bookService.addBook(new Book("4205",".NET","Neha",Date.valueOf("2022-01-29"),234.55));

		//Printing all books
		printBooks(bookService);

		//Updating Book record
		System.out.println("Updating book record");
		bookService.updateBook(new Book("1234","JAVA","Chesta",Date.valueOf("2022-01-30"),600.55));
		System.out.println(bookService.getBookById(1));

		//Deleting a Book record
		System.out.println("Deleting book record");
		bookService.removeBook(1);
		printBooks(bookService);

	}

	private static void printBooks(BookService bookServ) {
		System.out.println("Printing all book records");
		List<Book> books = new ArrayList<>();
		books = bookServ.getAllBooks();
		for(Book book: books) {
			System.out.println(book);
		}

	}

}
