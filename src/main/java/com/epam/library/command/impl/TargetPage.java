package com.epam.library.command.impl;

public enum TargetPage {
	ADMIN_PAGE("WEB-INF/jsp/adminPage.jsp"), USER_PAGE("WEB-INF/jsp/userPage.jsp"), ERROR_PAGE(
			"WEB-INF/error/error.jsp"), BOOK_INFO_PAGE("WEB-INF/jsp/selectedBookInfo.jsp"), LOGIN_PAGE("loginPage.jsp"),ADD_BOOK_PAGE("WEB-INF/jsp/addBook.jsp"),EDIT_BOOK_PAGE("WEB-INF/jsp/editBook.jsp");

	private String param;

	TargetPage(String param) {
		this.param = param;
	}

	public String getParam() {
		return param;
	}
}
