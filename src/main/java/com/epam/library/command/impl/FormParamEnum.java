package com.epam.library.command.impl;


 enum FormParamEnum {
	ACTION("action"), USER_INFO("userInfo"), ADMIN("admin"), USER(
					"user"), ERROR_MESSAGE("errorMessage"), DATA_NOT_FOUND("Wrong Credentials!!"), USER_ROLE(
							"userRole"), ADMIN_ROLE("adminRole"), BOOK_INFO("bookInfo"), LANGUAGE(
									"language"), TYPE_OF_BOOK(
											"typeOfBook"),EXCEPTION_CAUGHT("exceptionOccured");

	private String param;

	FormParamEnum(String param) {
		this.param = param;
	}

	public String getParam() {
		return param;
	}
	}

