package com.epam.library.command.impl;

enum LoginParamEnum {
	ACTION("action"), USER_NAME("userName"), PASSWORD("password"), FORWARD_REQUEST("forward"), ADMIN_PAGE(
			"WEB-INF/jsp/adminPage.jsp"), USER_PAGE("WEB-INF/jsp/userPage.jsp"), WELCOME_PAGE("welcome"), USER_INFO("userInfo"), ADMIN(
					"admin"), USER("user"), ERROR_MESSAGE("errorMessage"), DATA_NOT_FOUND(
							"Wrong Credentials!!"), USER_ROLE("userRole"), ADMIN_ROLE("adminRole"), REDIRECT(
									"redirect"), TO_NEW_LINK("toForwarded"), BOOK_INFO("bookInfo"),LANGUAGE("language"),BOOK_INFO_PAGE("WEB-INF/jsp/selectedBookInfo.jsp");

	private String param;

	LoginParamEnum(String param) {
		this.param = param;
	}

	public String getParam() {
		return param;
	}
}
