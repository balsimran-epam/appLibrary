package com.epam.library.service.util;

import com.epam.library.domain.DisplayBookDTO;

import com.epam.library.domain.SearchBookDTO;
import com.epam.library.service.exception.ValidatorException;

public class Validator {
	

	public static void validatingSelectedBookType(SearchBookDTO request) throws ValidatorException {
		if (request.getTypeOfBook()==null) {

			throw new ValidatorException("Please Select Type of book");
		}
		
	}

	public static void validatingSelectedBookType(DisplayBookDTO bookType) throws ValidatorException {
		if (bookType==null) {

			throw new ValidatorException("Please Select Type of book");
		}
		
	}
}
