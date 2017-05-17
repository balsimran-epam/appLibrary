package com.epam.library.command.impl;

public enum ParamEnum {
	LOGIN_PAGE("loginPage.jsp"), REQUESTED_METHOD_TO_CALL("requestedMethod"), SEND_REDIRECT_REQUEST("sendRedirect");

	private String param;

	ParamEnum(String param) {
		this.param = param;
	}

	public String getParam() {
		return param;
	}
}
