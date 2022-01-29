package com.bookstoreapplication.model.persistence;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bookstoreapplication.model.exceptions.BookNotFoundException;
import com.bookstoreapplication.model.exceptions.DataAccessException;

public class BookDaoImpl implements BookDao {
private Connection connection;
	
	public BookDaoImpl() {
		connection = ConnectionFactory.getConnection();
		if(!(tableExists(connection, "book"))) {
			createTable(connection);
		}
	}
	
	private static boolean tableExists(Connection connection, String tableName) throws DataAccessException{
		DatabaseMetaData meta;
		try {
			meta = connection.getMetaData();
			ResultSet resultSet = meta.getTables(null, null, tableName, new String[] {"TABLE"});
			return resultSet.next();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}
	
	private static void createTable(Connection connection) throws DataAccessException {
		try {
			Statement statement = connection.createStatement();
			statement.execute("create table book(id int primary key auto_increment,"
					+ "isbn varchar(15),title varchar(50) not null,author varchar(50) not null,"
					+ "pubdate date not null,price double not null,unique key (isbn))");
			System.out.println("Table created!!");
		}catch(SQLException e) {
			throw new DataAccessException(e);
		}
	}

	@Override
	public List<Book> getAllBooks() throws DataAccessException {
		List<Book> books = new ArrayList<>();
		try {
			Book book = null;
			PreparedStatement statement = connection.prepareStatement("select * from book");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				book = new Book(result.getInt(1), result.getString(2), result.getString(3), result.getString(4),
						result.getDate(5),result.getDouble(6));
				books.add(book);
			}
		}catch(SQLException e) {
			throw new DataAccessException(e);
		}
		return books;
	}

	@Override
	public Book getBookById(int bookId) throws BookNotFoundException,DataAccessException {
		Book book = null;
		try {
			PreparedStatement statement = connection.prepareStatement("select * from book where id=?");
			statement.setInt(1, bookId);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				book = new Book(result.getInt(1), result.getString(2), result.getString(3), result.getString(4),
						result.getDate(5),result.getDouble(6));
			} else {
				throw new BookNotFoundException("employee with id =" + bookId + " is not found");
			}
		} catch (SQLException ex) {
			throw new DataAccessException(ex);
		}
		return book;
	}

	@Override
	public void addBook(Book book) throws DataAccessException{
		try {
			PreparedStatement insertData = connection
					.prepareStatement("insert into book(" + 
			"isbn,title,author,pubdate,price) values (?,?,?,?,?)");
			insertData.setString(1, book.getIsbn());
			insertData.setString(2, book.getTitle());
			insertData.setString(3, book.getAuthor());
			insertData.setDate(4, book.getDate());
			insertData.setDouble(5, book.getPrice());
			insertData.executeUpdate();
			System.out.println("Record added !!");
		} catch (SQLException ex) {
			throw new DataAccessException(ex);
		}
	}

	@Override
	public void updateBook(Book book) throws DataAccessException{
		try {
			PreparedStatement updateData = connection.prepareStatement("update book set isbn=?, title=?, author=?, pubdate=?, price=? where id=?");
			updateData.setString(1, book.getIsbn());
			updateData.setString(2, book.getTitle());
			updateData.setString(3, book.getAuthor());
			updateData.setDate(4, book.getDate());
			updateData.setDouble(5, book.getPrice());
			updateData.setInt(6, book.getId());
			updateData.executeUpdate();
			System.out.println("Details Updated !!");
		} catch (SQLException ex) {
			throw new DataAccessException(ex);
		}
	}

	@Override
	public void removeBook(int bookId) throws DataAccessException{
		try {
			PreparedStatement deleteData = connection.prepareStatement("delete from book where id=?");
			deleteData.setInt(1, bookId);
			deleteData.executeUpdate();
			System.out.println("Book Record removed !!");
		} catch (SQLException ex) {
			throw new DataAccessException(ex);
		}	
	}

}
