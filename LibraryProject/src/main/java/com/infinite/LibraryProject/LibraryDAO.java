package com.infinite.LibraryProject;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.util.Util;


public class LibraryDAO {
	
	public int issueOrNot(String userName, int bookId) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionHelper.getConnection();
		String sql = "select count(*) cnt from TranBook where UserName=? and BookId=?";
		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setString(1, userName);
		pst.setInt(2, bookId);
		ResultSet rs = pst.executeQuery();
		rs.next();
		int count =rs.getInt("cnt");
		return count;
	}
	
	public String issueBook(String userName, int bookId) throws ClassNotFoundException, SQLException {
		int count = issueOrNot(userName, bookId);
		if (count==0) {
			Connection connection = ConnectionHelper.getConnection();
			String sql = "Insert into TranBook(UserName,BookId) values(?,?)";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, userName);
			pst.setInt(2, bookId);
			pst.executeUpdate();
			sql="Update Books set TotalBooks=TotalBooks-1 where id=?";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, bookId);
			pst.executeUpdate();
			return "Book with Id " +bookId + " Issued Successfully...";
		} else {
			return "Book Id " +bookId+ " for User " +userName + " Already Issued...";
		}
	}
	
	public List<Books> searchBooks(String searchType, String searchValue) throws ClassNotFoundException, SQLException {
		String sql;
		boolean isValid = true;
		if (searchType.equals("id")) {
			sql = "select * from books where id=?";
		}else if (searchType.equals("bookname")) {
			sql = "select * from books where name=?";
		}else if (searchType.equals("authorname")) {
			sql = "select * from books where author=?";
		}else if (searchType.equals("dept")) {
			sql = "select * from books where dept=?";
		}else {
			isValid = false;
			sql = "select * from books";
		}
		 
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		if (isValid) {
			preparedStatement.setString(1, searchValue);
		}
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		Books books = null;
		List<Books> bookList = new ArrayList<Books>();
		while (resultSet.next()) {
			books = new Books();
		
			books.setId(resultSet.getInt("id"));
			books.setName(resultSet.getString("name"));
			books.setAuthor(resultSet.getString("author"));
			books.setEdition(resultSet.getString("edition"));
			books.setDept(resultSet.getString("dept"));
			books.setNoOfBooks(resultSet.getInt("totalbooks"));
			
			bookList.add(books);
		}
		
		return bookList;
	}
	
	public int authenticate(String user, String password) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("select count(*) cnt from libusers where username=? and password=?");
		preparedStatement.setString(1, user);
		preparedStatement.setString(2, password);
		
		ResultSet resultset = preparedStatement.executeQuery();
		resultset.next();
		
		int count = resultset.getInt("cnt");
		return count;
	}

	public TranBook searchTranBook(String user, int bookId) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from tranbook where username=? and bookId=?");
		preparedStatement.setInt(2, bookId);
		preparedStatement.setString(1, user);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		TranBook tranBook = null;
		
		if (resultSet.next()) {
			tranBook = new TranBook();
			tranBook.setBookId(resultSet.getInt("bookid"));
			tranBook.setUserName(user);
			tranBook.setFromDate(resultSet.getDate("fromdate"));
		}
		
		return tranBook;
	}
	
	public String returnBooks(String user, int bookId) throws ClassNotFoundException, SQLException {
		TranBook tranBook = searchTranBook(user, bookId);
		java.util.Date date = new java.util.Date();
		java.sql.Date sqlDate = new Date(date.getTime());
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("Insert into transreturn (username, bookid, fromdate, todate) values(?,?,?,?)");
		preparedStatement.setString(1, user);
		preparedStatement.setInt(2, bookId);
		preparedStatement.setDate(3, tranBook.getFromDate());
		preparedStatement.setDate(4, sqlDate);
		preparedStatement.executeUpdate();
		
		preparedStatement = connection.prepareStatement("Update books set totalbooks=totalbooks+1 where id=?");
		preparedStatement.setInt(1, bookId);
		preparedStatement.executeUpdate();
		
		preparedStatement = connection.prepareStatement("delete from tranbook where username=? and bookid=?");
		preparedStatement.setInt(2, bookId);
		preparedStatement.setString(1, user);
		preparedStatement.executeUpdate();
		
		return "Your Book with ID " + bookId + " returned successfully. I hope you learned something.";
	}
	
	public List<TranBook> issueBooks(String user) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionHelper.getConnection();
		String sql = "select * from TranBook where UserName=?";
		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setString(1, user);
		ResultSet rs = pst.executeQuery();
		TranBook tranBook = null;
		List<TranBook> tranBookList = new ArrayList<TranBook>();
		while(rs.next()) {
			tranBook = new TranBook();
			tranBook.setBookId(rs.getInt("BookId"));
			tranBook.setUserName(user);
			tranBook.setFromDate(rs.getDate("FromDate"));
			tranBookList.add(tranBook);
		}
		return tranBookList;
	}
	
	public List<TranBook> returnHistory(String user) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from transreturn where username=?");
		preparedStatement.setString(1, user);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		TranBook tranBook = null;
		List<TranBook> tranHistoryList = new ArrayList<TranBook>();
		while(resultSet.next()) {
			tranBook = new TranBook();
			tranBook.setBookId(resultSet.getInt("bookid"));
			tranBook.setUserName(user);
			tranBook.setFromDate(resultSet.getDate("fromdate"));
			tranBook.setToDate(resultSet.getDate("todate"));
			tranHistoryList.add(tranBook);
		}
		return tranHistoryList;
	}
}
