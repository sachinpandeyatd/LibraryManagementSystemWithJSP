package com.infinite.LibraryProject;

import java.sql.Date;

public class TranBook {

	private String userName;
	private int bookId;
	private Date fromDate;
	private Date toDate;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return fromDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	
}