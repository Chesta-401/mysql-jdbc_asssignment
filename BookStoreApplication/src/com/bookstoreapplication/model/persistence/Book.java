package com.bookstoreapplication.model.persistence;

import java.sql.Date;

public class Book {
	private int id;
	private String isbn;
	private String title;
	private String author;
	private double price;
	private Date date;

	public Book(String isbn, String title, String author, Date date, double price) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.price = price;
		this.date = date;
	}
	
	public Book(int id, String isbn, String title, String author, Date date, double price) {
		super();
		this.id = id;
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.price = price;
		this.date = date;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Book [id=").append(id).append(", isbn=").append(isbn).append(", title=").append(title)
				.append(", author=").append(author).append(", price=").append(price).append(", date=").append(date)
				.append("]");
		return builder.toString();
	}

}
