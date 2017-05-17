package com.epam.library.dao.impl;

public enum BookParamEnum {
	BOOK_ID("b_id"),BOOK_QUANTITY("b_quantity"),BOOK_PRICE("b_price"),BOOK_VERSION("e_b_version"),BOOK_TITLE("b_t_title"),BOOK_DESCRIPTION("b_t_description"),BOOK_AUTHOR("b_t_author"),TYPE_OF_COVER("p_b_type");
	
	private String param;
	BookParamEnum(String param) {
		this.param = param;
	}

	public String getParam() {
		return param;
	}
	
}

