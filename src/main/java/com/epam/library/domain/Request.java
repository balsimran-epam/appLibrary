package com.epam.library.domain;

public class Request {
	private String userName;
	private String password;
	private String language;
	private String typeOfBook;
	private String bookId;
	private String type;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getTypeOfBook() {
		return typeOfBook;
	}

	public void setTypeOfBook(String typeOfBook) {
		this.typeOfBook = typeOfBook;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
