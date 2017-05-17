package com.epam.library.command.impl;

public enum BookEnum {
	TYPE_OF_BOOK("bookSelectedType");
	private String param;

	BookEnum(String param) {
		this.param = param;
	}

	public String getParam() {
		return param;
	}
}

